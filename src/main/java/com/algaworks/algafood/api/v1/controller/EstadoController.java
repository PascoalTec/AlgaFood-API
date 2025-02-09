package com.algaworks.algafood.api.v1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/v1/estados")
public class EstadoController {
    
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    
    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        List<Estado> todosEstados = estadoRepository.findAll();
    
        return estadoModelAssembler.toCollectionModel(todosEstados);
}


    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return cadastroEstadoService.buscarOuFalhar(estadoId);
    }


    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    // @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

        estado = cadastroEstadoService.salvar(estado);

        return estadoModelAssembler.toModel(estado);
    }


    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = cadastroEstadoService.salvar(estadoAtual);
        // BeanUtils.copyProperties(estado, estadoAtual, "id");

        return cadastroEstadoService.salvar(estadoAtual);
        
    }

    @CheckSecurity.Cidades.PodeEditar
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }
}
