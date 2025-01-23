package org.khasanof.core.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.khasanof.core.service.dto.base.IDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.dto
 * @since 12/11/2024 2:02 PM
 */
public class NumbersDTO implements IDto, Serializable {

    private Long id;

    @NotBlank
    private Long nextNumber;

    @Size(min = 1, max = 450)
    private String dimensions;

    @NotNull
    private DbTypesDTO dbType;

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

    public DbTypesDTO getDbType() {
        return dbType;
    }

    public void setDbType(DbTypesDTO dbType) {
        this.dbType = dbType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        NumbersDTO that = (NumbersDTO) object;
        return Objects.equals(id, that.id) && Objects.equals(nextNumber, that.nextNumber) && Objects.equals(dimensions, that.dimensions) && Objects.equals(dbType, that.dbType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nextNumber, dimensions, dbType);
    }

    @Override
    public String toString() {
        return "NumbersDTO{" +
                "id=" + id +
                ", nextNumber=" + nextNumber +
                ", dimensions='" + dimensions + '\'' +
                ", dbType=" + dbType +
                '}';
    }
}
