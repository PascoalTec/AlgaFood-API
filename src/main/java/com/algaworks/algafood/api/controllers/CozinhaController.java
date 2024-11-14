package com.algaworks.algafood.api.controllers;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;


@RestController
@RequestMapping("/cozinha")
public class CozinhaController {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId){
        return cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        // if (cozinha.isPresent()) {
        //     return ResponseEntity.ok(cozinha.get());
        // }

        //     return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
		return cadastroCozinhaService.salvar(cozinhaAtual);
			
		
    }

    // @DeleteMapping("/{cozinhaId}")
    // public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
    //     try {
    //         cadastroCozinhaService.excluir(cozinhaId);
        
    //         return ResponseEntity.noContent().build();
                
    //     } catch (EntidadeNaoEncontradaException e) {
    //         return ResponseEntity.notFound().build();

    //     } catch (EntidadeEmUsoException e) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).build();
    //     }
    // }


    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId){
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
