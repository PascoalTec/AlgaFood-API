package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

    InputStream recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntivo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoAntivo != null) {
            this.remover(nomeArquivoAntivo);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private InputStream inputStream; // a partir de um InputStream, eu consigo salvar a foto
    }
}
