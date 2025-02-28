package com.algaworks.algafood.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {
    
    @Schema(example = "400")
    private Integer status;

    @Schema(example = "https://algafood.com.br/dados-invalidos")
    private String type;

    @Schema(example = "Dados Invalidos")
    private String title;

    @Schema(example = "Um ou mais campos estão inválidos.")
    private String detail;

    @Schema(example = "Um ou mais campos estão inválidos.")
    private String userMessage;

    @Schema(example = "2025-02-15T11:05:00.902245498Z")
    private OffsetDateTime timeStamp;

    @Schema(description = "Lista de objetos ou campos que geraram o erro.")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "ObjetoProblema")
    public static class Object {

        @Schema(example = "preco")
        private String name;

        @Schema(example = "O preço é inválido.")
        private String userMessage;
    }
}
