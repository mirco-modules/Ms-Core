package org.khasanof.core.service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.khasanof.core.domain.Variables} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VariablesDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VariablesDTO)) {
            return false;
        }

        VariablesDTO variablesDTO = (VariablesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, variablesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VariablesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
