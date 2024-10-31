package com.algaworks.algafood.infrastructure;

import java.util.List;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        return entityManager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    public Restaurante salvar(Restaurante restauranteModel) {
        return entityManager.merge(restauranteModel);
    }

    @Override
    public void remover(Restaurante restauranteModel) {
        restauranteModel = buscar(restauranteModel.getId());
        entityManager.remove(restauranteModel);
    }
    
}
