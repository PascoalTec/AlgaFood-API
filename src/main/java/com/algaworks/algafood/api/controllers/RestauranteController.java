package com.algaworks.algafood.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssember;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelAssember restauranteModelAssember;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;


    @GetMapping
    public List<RestauranteModel> listar(){
        return restauranteModelAssember.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId){
        Restaurante restaurante =  cadastroRestauranteService.buscarOuFalhar(restauranteId);

        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setPrecoFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);

        return restauranteModelAssember.toModel(restaurante);
    }


    @PostMapping
    @Transactional
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

        return restauranteModelAssember.toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){
        try {
          //  Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

          //  BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteModelAssember.toModel(cadastroRestauranteService.salvar(restauranteAtual));
            } catch (CozinhaNaoEncontradaException e) {
                throw new NegocioException(e.getMessage());
            }
    }

    // @PatchMapping("/{restauranteId}")
    // @Transactional
    // public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request) {

    //     Restaurante restauranteAtual =  cadastroRestauranteService.buscarOuFalhar(restauranteId);

    //     merge(campos, restauranteAtual, request);
    //     validate(restauranteAtual, "restaurante");

    //     return atualizar(restauranteId, restauranteAtual);
    // }

    // private void validate(Restaurante restaurante, String objectName) {
    //     BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

    //     validator.validate(restaurante, bindingResult);

    //     if (bindingResult.hasErrors()) {
    //         throw new ValidacaoException(bindingResult);
    //     }
    // }


    // private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
    //     ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    //         Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
            
    //         dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
    //             Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
    //             field.setAccessible(true);
                
    //             Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                
    // //			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
                
    //             ReflectionUtils.setField(field, restauranteDestino, novoValor);
    //         });
	//     } catch (IllegalArgumentException e) {
    //         Throwable rootCause = ExceptionUtils.getRootCause(e);
    //         throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
    //     }
    // }

    
}
