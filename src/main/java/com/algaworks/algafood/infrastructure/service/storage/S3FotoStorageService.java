package com.algaworks.algafood.infrastructure.service.storage;

import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.exception.StorageException;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazons3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

        URL url = amazons3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

        return FotoRecuperada.builder().url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try{

        String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
        
        var objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(novaFoto.getContentType());

        var putObjectRequest = new PutObjectRequest(
            storageProperties.getS3().getBucket(), 
            caminhoArquivo,
            novaFoto.getInputStream(),
            objectMetaData)
            .withCannedAcl(CannedAccessControlList.PublicRead);

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
        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
            
            var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);

            amazons3.deleteObject(deleteObjectRequest);

        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }
    
}
