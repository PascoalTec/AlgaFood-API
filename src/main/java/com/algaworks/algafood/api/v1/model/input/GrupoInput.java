package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {
    
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Gerente")
    private String nome;
}
