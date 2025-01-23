package org.khasanof.core.service.data;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.data
 * @since 12/17/2024 6:15 PM
 */
public abstract class ExcelDataProcessor<T> extends AbstractDataProcessor<T> {

    /**
     *
     * @param filePath
     * @return
     */
    public List<T> readAllValues(String filePath) {
        ClassPathResource resource = loadResource(filePath);
        return tryReadAllValues(resource);
    }

    /**
     *
     * @param resource
     * @return
     */
    private List<T> tryReadAllValues(ClassPathResource resource) {
        try {
            return Poiji.fromExcel(resource.getFile(), persistenceClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
