package org.khasanof.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DbTypes.
 */
@Entity
@Table(name = "db_types")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DbTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "prefix", nullable = false)
    private String prefix;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "synonym", nullable = false)
    private String synonym;

    @NotNull
    @Column(name = "is_primitive", nullable = false)
    private Boolean isPrimitive;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DbTypes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public DbTypes prefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return this.name;
    }

    public DbTypes name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynonym() {
        return this.synonym;
    }

    public DbTypes synonym(String synonym) {
        this.setSynonym(synonym);
        return this;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public Boolean getIsPrimitive() {
        return this.isPrimitive;
    }

    public DbTypes isPrimitive(Boolean isPrimitive) {
        this.setIsPrimitive(isPrimitive);
        return this;
    }

    public void setIsPrimitive(Boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DbTypes)) {
            return false;
        }
        return getId() != null && getId().equals(((DbTypes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DbTypes{" +
            "id=" + getId() +
            ", prefix='" + getPrefix() + "'" +
            ", name='" + getName() + "'" +
            ", synonym='" + getSynonym() + "'" +
            ", isPrimitive='" + getIsPrimitive() + "'" +
            "}";
    }
}
