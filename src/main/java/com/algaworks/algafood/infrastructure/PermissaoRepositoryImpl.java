package com.algaworks.algafood.infrastructure;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar(){
        return entityManager.createQuery("from Permissao", Permissao.class).getResultList();
        
    }

    @Override
    public Permissao buscar(Long id){
        return entityManager.find(Permissao.class, id);
        // vai fazer um select from Permissao where = id, o id que eu estou passando
    }

    
    @Transactional
    @Override
    public void remover(Permissao permissao){
        permissao = buscar(permissao.getId());
        entityManager.remove(permissao);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {
        return entityManager.merge(permissao);
    }
}
