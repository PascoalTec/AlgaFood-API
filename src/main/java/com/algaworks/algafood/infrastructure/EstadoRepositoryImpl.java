package com.algaworks.algafood.infrastructure;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar(){
        return entityManager.createQuery("from Estado", Estado.class).getResultList();
        
    }

    @Override
    public Estado buscar(Long id){
        return entityManager.find(Estado.class, id);
        // vai fazer um select from cozinha where = id, o id que eu estou passando
    }

    
    @Transactional
    @Override
    public void remover(Long id){
        Estado estado = buscar(id);

        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(estado);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);
    }
}
