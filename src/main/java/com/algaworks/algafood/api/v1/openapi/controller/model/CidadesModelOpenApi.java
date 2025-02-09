package com.algaworks.algafood.api.v1.openapi.controller.model;

import java.util.List;
import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.CidadeModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CidadesModelOpenApi {
    
    private CidadeEmbeddedModelOpenApi embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadeEmbeddedModelOpenApi {
        private List<CidadeModel> cidades;
    }
}
