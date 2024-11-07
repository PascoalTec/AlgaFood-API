package com.algaworks.algafood.infrastructure;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class FormaPagamentoImpl implements FormaPagamentoRepository {
    

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> listar(){
        return entityManager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
        
    }

    @Override
    public FormaPagamento buscar(Long id){
        return entityManager.find(FormaPagamento.class, id);
        // vai fazer um select from FormaPagamento where = id, o id que eu estou passando
    }

    
    @Transactional
    @Override
    public void remover(FormaPagamento formaPagamento){
        formaPagamento = buscar(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return entityManager.merge(formaPagamento);
    }
}
