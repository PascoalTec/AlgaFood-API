package com.algaworks.algafood.infrastructure;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class CidadeRepositoryImpl implements CidadeRepository {


    @PersistenceContext
     // Esta interface gerencia o contexto de persistencia, gerencia os comandos pela tradução SQL
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar(){
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
        
    }

    @Override
    public Cidade buscar(Long id){
        return entityManager.find(Cidade.class, id);
        // vai fazer um select from cidade where = id, o id que eu estou passando
    }

    
    @Transactional
    @Override
    public void remover(Long id){
        Cidade cidade = buscar(id);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(cidade);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return entityManager.merge(cidade);
    }
}
