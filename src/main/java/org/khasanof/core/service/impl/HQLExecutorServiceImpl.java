package org.khasanof.core.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.khasanof.core.service.HQLExecutorService;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.impl
 * @since 1/27/2025 4:58 PM
 */
@Service
@SuppressWarnings("unchecked")
public class HQLExecutorServiceImpl implements HQLExecutorService {

    private final EntityManager entityManager;

    public HQLExecutorServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @param hql
     * @param params
     * @return
     * @param <T>
     */
    @Override
    public <T> T executeQuery(String hql, Object... params) {
        String formattedHql = String.format(hql, params);
        Query query = entityManager.createQuery(formattedHql);
        return (T) query.getSingleResult();
    }

    /**
     *
     * @param hql
     * @param params
     * @return
     * @param <T>
     */
    @Override
    public <T> T executeQuery(String hql, Map<String, Object> params) {
        Query query = entityManager.createQuery(hql);
        setParameters(query, params);
        return (T) query.getSingleResult();
    }

    /**
     *
     * @param query
     * @param params
     */
    private void setParameters(Query query, Map<String, Object> params) {
        params.forEach(query::setParameter);
    }
}
