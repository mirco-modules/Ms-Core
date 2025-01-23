package org.khasanof.core.service.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.dto
 * @since 12/12/2024 11:05 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NextNumberDTO implements Serializable {

    private Long nextNumber;
}
