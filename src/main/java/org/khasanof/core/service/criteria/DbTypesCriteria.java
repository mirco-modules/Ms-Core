package org.khasanof.core.service.criteria;

import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.service.criteria.base.ICriteria;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Criteria class for the {@link DbTypes} entity. This class is used
 * in {@link org.khasanof.core.web.rest.DbTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /db-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DbTypesCriteria implements Serializable, ICriteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter prefix;

    private StringFilter name;

    private StringFilter synonym;

    private BooleanFilter isPrimitive;

    private Boolean distinct;

    public DbTypesCriteria() {}

    public DbTypesCriteria(DbTypesCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.prefix = other.optionalPrefix().map(StringFilter::copy).orElse(null);
        this.name = other.optionalName().map(StringFilter::copy).orElse(null);
        this.synonym = other.optionalSynonym().map(StringFilter::copy).orElse(null);
        this.isPrimitive = other.optionalIsPrimitive().map(BooleanFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DbTypesCriteria copy() {
        return new DbTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPrefix() {
        return prefix;
    }

    public Optional<StringFilter> optionalPrefix() {
        return Optional.ofNullable(prefix);
    }

    public StringFilter prefix() {
        if (prefix == null) {
            setPrefix(new StringFilter());
        }
        return prefix;
    }

    public void setPrefix(StringFilter prefix) {
        this.prefix = prefix;
    }

    public StringFilter getName() {
        return name;
    }

    public Optional<StringFilter> optionalName() {
        return Optional.ofNullable(name);
    }

    public StringFilter name() {
        if (name == null) {
            setName(new StringFilter());
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getSynonym() {
        return synonym;
    }

    public Optional<StringFilter> optionalSynonym() {
        return Optional.ofNullable(synonym);
    }

    public StringFilter synonym() {
        if (synonym == null) {
            setSynonym(new StringFilter());
        }
        return synonym;
    }

    public void setSynonym(StringFilter synonym) {
        this.synonym = synonym;
    }

    public BooleanFilter getIsPrimitive() {
        return isPrimitive;
    }

    public Optional<BooleanFilter> optionalIsPrimitive() {
        return Optional.ofNullable(isPrimitive);
    }

    public BooleanFilter isPrimitive() {
        if (isPrimitive == null) {
            setIsPrimitive(new BooleanFilter());
        }
        return isPrimitive;
    }

    public void setIsPrimitive(BooleanFilter isPrimitive) {
        this.isPrimitive = isPrimitive;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DbTypesCriteria that = (DbTypesCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(prefix, that.prefix) &&
            Objects.equals(name, that.name) &&
            Objects.equals(synonym, that.synonym) &&
            Objects.equals(isPrimitive, that.isPrimitive) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prefix, name, synonym, isPrimitive, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DbTypesCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalPrefix().map(f -> "prefix=" + f + ", ").orElse("") +
            optionalName().map(f -> "name=" + f + ", ").orElse("") +
            optionalSynonym().map(f -> "synonym=" + f + ", ").orElse("") +
            optionalIsPrimitive().map(f -> "isPrimitive=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
