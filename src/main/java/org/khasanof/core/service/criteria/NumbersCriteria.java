package org.khasanof.core.service.criteria;

import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import org.khasanof.core.service.criteria.base.ICriteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.criteria
 * @since 12/11/2024 2:05 PM
 */
public class NumbersCriteria implements Serializable, ICriteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter nextNumber;

    private StringFilter dimensions;

    private LongFilter dbTypeId;

    private Boolean distinct;

    public NumbersCriteria() {

    }

    public NumbersCriteria(NumbersCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.nextNumber = other.optionalNextNumber().map(LongFilter::copy).orElse(null);
        this.dimensions = other.optionalDimensions().map(StringFilter::copy).orElse(null);
        this.dbTypeId = other.optionalDbTypeId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public NumbersCriteria copy() {
        return null;
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(this.id);
    }

    public LongFilter getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(LongFilter nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Optional<LongFilter> optionalNextNumber() {
        return Optional.ofNullable(this.nextNumber);
    }

    public StringFilter getDimensions() {
        return dimensions;
    }

    public void setDimensions(StringFilter dimensions) {
        this.dimensions = dimensions;
    }

    public Optional<StringFilter> optionalDimensions() {
        return Optional.ofNullable(this.dimensions);
    }

    public LongFilter getDbTypeId() {
        return dbTypeId;
    }

    public void setDbTypeId(LongFilter dbTypeId) {
        this.dbTypeId = dbTypeId;
    }

    public Optional<LongFilter> optionalDbTypeId() {
        return Optional.ofNullable(this.dbTypeId);
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        NumbersCriteria that = (NumbersCriteria) object;
        return Objects.equals(id, that.id) && Objects.equals(nextNumber, that.nextNumber) && Objects.equals(dimensions, that.dimensions) && Objects.equals(dbTypeId, that.dbTypeId) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nextNumber, dimensions, dbTypeId, distinct);
    }

    @Override
    public String toString() {
        return "NumbersCriteria{" +
                "id=" + id +
                ", nextNumber=" + nextNumber +
                ", dimensions=" + dimensions +
                ", dbTypeId=" + dbTypeId +
                ", distinct=" + distinct +
                '}';
    }
}
