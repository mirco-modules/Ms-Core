package org.khasanof.core.service.scanner;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;
import org.khasanof.core.service.ApplicationPackagesService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @see org.khasanof.core.service.scanner
 * @author Nurislom
 * @since 12/11/2024 4:49 PM
 */
@Service
public class DynamicClassScanningComponentProviderImpl implements DynamicClassScanningComponentProvider {

    private final ApplicationPackagesService applicationPackagesService;

    public DynamicClassScanningComponentProviderImpl(ApplicationPackagesService applicationPackagesService) {
        this.applicationPackagesService = applicationPackagesService;
    }

    /**
     *
     * @param typeFilter
     * @return
     */
    @Override
    public Set<BeanDefinition> findComponents(TypeFilter typeFilter) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(typeFilter);

        List<String> packages = applicationPackagesService.getPackages();
        return packages.stream()
                .map(scanner::findCandidateComponents)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
