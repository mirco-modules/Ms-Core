package org.khasanof.core.service.data;

import org.springframework.core.io.ClassPathResource;
import org.khasanof.core.service.processor.RootProcessor;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.data
 * @since 12/17/2024 6:17 PM
 */
public abstract class AbstractDataProcessor<T> implements RootProcessor {

    protected Class<T> persistenceClass;

    public AbstractDataProcessor() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param filePath
     * @return
     */
    public abstract List<T> readAllValues(String filePath);

    /**
     *
     * @param filePath
     * @return
     */
    protected ClassPathResource loadResource(String filePath) {
        return new ClassPathResource(filePath);
    }

    /**
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 10;
    }
}
