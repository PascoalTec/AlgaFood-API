package com.algaworks.algafood.api.v1.openapi.controller.model;

import java.util.List;
import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PedidosResumoModel")
@Data
public class PedidosResumoModelOpenApi {

    private PedidosResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("PedidosResumoEmbeddedModel")
    @Data
    public class PedidosResumoEmbeddedModelOpenApi {

        private List<PedidoResumoModel> pedidoResumoModels;
    }
}
