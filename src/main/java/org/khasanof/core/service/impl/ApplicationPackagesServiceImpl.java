package org.khasanof.core.service.impl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.khasanof.core.annotation.ScanPackages;
import org.khasanof.core.service.ApplicationPackagesService;
import org.khasanof.core.util.AopProxyBeanUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.impl
 * @since 12/11/2024 3:09 PM
 */
@Service
public class ApplicationPackagesServiceImpl implements ApplicationPackagesService {

    private final ApplicationContext applicationContext;

    public ApplicationPackagesServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getPackages() {
        return Stream.of(getSpringBootApplicationPackages(), getScanPackages())
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     *
     * @return
     */
    private List<String> getSpringBootApplicationPackages() {
        Map<String, Object> applicationBeans = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
        if (applicationBeans.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<Object> values = applicationBeans.values();
        return values.stream()
                .map(this::getBeanPackageName)
                .collect(Collectors.toList());
    }

    /**
     *
     * @return
     */
    private List<String> getScanPackages() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ScanPackages.class);
        return beans.values()
                .stream()
                .map(AopProxyBeanUtil::unwrapProxyBean)
                .map(scanPackageBean -> scanPackageBean.getClass()
                        .getAnnotation(ScanPackages.class)
                        .value())
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     *
     * @param bean
     * @return
     */
    private String getBeanPackageName(Object bean) {
        Class<?> beanClass = bean.getClass();
        Package classPackage = beanClass.getPackage();
        return classPackage.getName();
    }
}
