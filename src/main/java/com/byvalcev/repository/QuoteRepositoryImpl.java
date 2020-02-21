package com.byvalcev.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class QuoteRepositoryImpl implements QuoteRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Double> selectElvls(int limit) throws ClassCastException {
        return entityManager
                .createNativeQuery("SELECT elvl FROM QUOTES LIMIT :limit")
                .setParameter("limit", limit)
                .getResultList();
    }

    @Override
    public void updateElvl(Double elvl, int id) {
        entityManager
                .createNativeQuery("UPDATE QUOTES SET elvl = ? WHERE id = ?")
                .setParameter(1, elvl)
                .setParameter(2, id)
                .executeUpdate();
    }
}
