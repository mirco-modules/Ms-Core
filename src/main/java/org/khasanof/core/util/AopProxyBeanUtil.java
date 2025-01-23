package org.khasanof.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * @author Nurislom
 * @see org.khasanof.core.util
 * @since 1/4/2025 11:58 AM
 */
@Slf4j
public final class AopProxyBeanUtil {

    /**
     *
     * @param proxyBean
     * @return
     */
    public static Object unwrapProxyBean(Object proxyBean) {
        try {
            if (AopUtils.isAopProxy(proxyBean) && proxyBean instanceof Advised) {
                return ((Advised) proxyBean).getTargetSource().getTarget();
            }
        } catch (Exception e) {
            log.warn("unwrapProxyBean exception thrown : {}", e.getMessage());
            e.printStackTrace();
        }
        return proxyBean;
    }
}
