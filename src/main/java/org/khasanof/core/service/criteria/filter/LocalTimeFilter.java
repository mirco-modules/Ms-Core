package org.khasanof.core.service.criteria.filter;

import tech.jhipster.service.filter.RangeFilter;

import java.time.LocalTime;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.criteria.filter
 * @since 8/10/2025 7:49 AM
 */
public class LocalTimeFilter extends RangeFilter<LocalTime> {

    public LocalTimeFilter() {
    }

    public LocalTimeFilter(RangeFilter<LocalTime> filter) {
        super(filter);
    }

    @Override
    public LocalTimeFilter copy() {
        return new LocalTimeFilter(this);
    }
}
