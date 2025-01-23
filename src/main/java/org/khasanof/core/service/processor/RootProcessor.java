package org.khasanof.core.service.processor;

import org.springframework.core.Ordered;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor
 * @since 11/20/2024 2:47 PM
 */
public interface RootProcessor extends Ordered {

    /**
     *
     */
    void process();

    @Override
    default int getOrder() {
        return 5;
    }
}
