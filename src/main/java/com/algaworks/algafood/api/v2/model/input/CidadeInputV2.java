package com.algaworks.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CidadeInput")
@Getter
@Setter
public class CidadeInputV2 {
    
    @ApiModelProperty(example = "Uberlândia", required = true)
    @NotBlank
    private String nomeCidade;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
