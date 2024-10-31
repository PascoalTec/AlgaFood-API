package com.algaworks.algafood.infrastructure;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
    
    @PersistenceContext
     // Esta interface gerencia o contexto de persistencia, gerencia os comandos pela tradução SQL
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar(){
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
        
    }

    @Override
    public Cozinha buscar(Long id){
        return entityManager.find(Cozinha.class, id);
        // vai fazer um select from cozinha where = id, o id que eu estou passando
    }

    
    @Transactional
    @Override
    public void remover(Cozinha cozinha){
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

}
