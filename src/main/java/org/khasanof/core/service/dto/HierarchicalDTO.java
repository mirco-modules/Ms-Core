package org.khasanof.core.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.dto
 * @since 1/22/2025 1:56 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HierarchicalDTO implements Serializable {

    private Long id;
    private String name;
    private HierarchicalDTO parent;
    private List<HierarchicalDTO> children;
}
