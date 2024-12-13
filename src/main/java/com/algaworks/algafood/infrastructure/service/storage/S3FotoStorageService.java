package com.algaworks.algafood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.exception.StorageException;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazons3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try{

        String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
        
        var objectMetaData = new ObjectMetadata();

        var putObjectRequest = new PutObjectRequest(
            storageProperties.getS3().getBucket(), 
            caminhoArquivo,
            novaFoto.getInputStream(),
            objectMetaData);

            amazons3.putObject(putObjectRequest);

        } catch (Exception e) {
            throw new StorageException("Não foi possivel enviar arquivo para Amazon S3.", e);
        }
    }
        
        private String getCaminhoArquivo(String nomeArquivo) {
            return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
        }
        
            @Override
    public void remover(String nomeArquivo) {
        
    }
    
}