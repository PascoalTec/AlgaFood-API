package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;


@Service
public class FluxoPedidoService {
 
    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;


    @Transactional
    public void confimar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.confirmar();

        pedidoRepository.save(pedido);

        // var mensagem = Mensagem.builder()
        // .assunto(pedido.getRestaurante().getNome() + "- Pedido confirmado")
        // .corpo("pedido-confirmado.html")
        // .variavel("pedido", pedido)
        // .destinatario(pedido.getCliente().getEmail())
        // .build();

        // envioEmailService.enviar(mensagem);
    }


    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.entregar();
    }
}
