package com.algaworks.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NotBlank
public class CidadeInput {
    
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
