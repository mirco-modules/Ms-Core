package org.khasanof.core.service;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.service
 * @since 1/27/2025 4:57 PM
 */
public interface HQLExecutorService {

    /**
     *
     * @param hql
     * @param params
     * @return
     * @param <T>
     */
    <T> T executeQuery(String hql, Object... params);

    /**
     *
     * @param hql
     * @param params
     * @return
     * @param <T>
     */
    <T> T executeQuery(String hql, Map<String, Object> params);
}
