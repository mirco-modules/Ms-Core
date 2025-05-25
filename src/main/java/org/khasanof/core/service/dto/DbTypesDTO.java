package org.khasanof.core.service.dto;

import jakarta.validation.constraints.NotNull;
import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.service.dto.base.IDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link DbTypes} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DbTypesDTO implements IDto, Serializable {

    private Long id;

    @NotNull
    private String prefix;

    @NotNull
    private String name;

    @NotNull
    private String synonym;

    @NotNull
    private Boolean isPrimitive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public Boolean getIsPrimitive() {
        return isPrimitive;
    }

    public void setIsPrimitive(Boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DbTypesDTO)) {
            return false;
        }

        DbTypesDTO dbTypesDTO = (DbTypesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dbTypesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DbTypesDTO{" +
            "id=" + getId() +
            ", prefix='" + getPrefix() + "'" +
            ", name='" + getName() + "'" +
            ", synonym='" + getSynonym() + "'" +
            ", isPrimitive='" + getIsPrimitive() + "'" +
            "}";
    }
}
