package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.api.controllers.CidadeController;
import com.algaworks.algafood.api.controllers.FormaPagamentoController;
import com.algaworks.algafood.api.controllers.PedidosController;
import com.algaworks.algafood.api.controllers.RestauranteProdutoController;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;
    

    public PedidoModelAssembler() {
        super(PedidosController.class, PedidoModel.class);
    }

    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getFormaPagamento().getId())).withSelfRel());

        pedidoModel.getCliente()
            .add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a construção da URL do recurso de forma de pagamento.
        pedidoModel.getFormaPagamento()
            .add(linkTo(methodOn(FormaPagamentoController.class).buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        // Não é necessario usar o WebMvcLinkBuilder, por que a nossa IDE já busca o linkTo para gente
        pedidoModel.getEnderecoEntrega().getCidade()
            .add(linkTo(methodOn(CidadeController.class).buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoModel.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId())).withRel("produto"));
        });

        return pedidoModel;
    }
}
