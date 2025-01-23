package org.khasanof.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.khasanof.core.domain.types.IEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.domain
 * @since 12/11/2024 12:24 PM
 */
@Entity
@Table(name = "numbers")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Numbers implements IEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "next_number", nullable = false)
    private Long nextNumber;

    @Size(min = 1, max = 450)
    @Column(name = "dimensions", length = 450)
    private String dimensions;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private DbTypes dbType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Long nextNumber) {
        this.nextNumber = nextNumber;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public DbTypes getDbType() {
        return dbType;
    }

    public void setDbType(DbTypes dbType) {
        this.dbType = dbType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Numbers numbers = (Numbers) object;
        return Objects.equals(id, numbers.id) && Objects.equals(nextNumber, numbers.nextNumber) && Objects.equals(dimensions, numbers.dimensions) && Objects.equals(dbType, numbers.dbType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nextNumber, dimensions, dbType);
    }

    @Override
    public String toString() {
        return "Numbers{" +
                "id=" + id +
                ", nextNumber=" + nextNumber +
                ", dimensions='" + dimensions + '\'' +
                ", dbType=" + dbType +
                '}';
    }
}
