package org.khasanof.core.service.data;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.data
 * @since 12/17/2024 5:07 PM
 */
public abstract class CsvDataProcessor<T> extends AbstractDataProcessor<T> {

    private static final Logger log = LoggerFactory.getLogger(CsvDataProcessor.class);

    protected final CsvMapper csvMapper;

    public CsvDataProcessor(CsvMapper csvMapper) {
        this.csvMapper = csvMapper;
    }

    /**
     * @param filePath
     * @return
     */
    public List<T> readAllValues(String filePath) {
        ClassPathResource resource = loadResource(filePath);
        return tryReadAllValues(resource, CsvSchema.builder().build());
    }

    /**
     *
     * @param filePath
     * @param csvSchema
     * @return
     */
    public List<T> readAllValues(String filePath, CsvSchema csvSchema) {
        ClassPathResource resource = loadResource(filePath);
        return tryReadAllValues(resource, csvSchema);
    }

    /**
     * @param resource
     * @param csvSchema
     * @return
     */
    private List<T> tryReadAllValues(ClassPathResource resource, CsvSchema csvSchema) {
        try {
            try (MappingIterator<T> objectMappingIterator = csvMapper.readerFor(persistenceClass)
                    .with(csvSchema)
                    .readValues(resource.getContentAsByteArray())) {

                return objectMappingIterator.readAll();
            }
        } catch (IOException e) {
            log.warn("Error occurred while loading object list from : {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * @return
     */
    @Override
    public int getOrder() {
        return 10;
    }
}
