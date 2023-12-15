package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Запрос DTO на работу с типом нарушения
 */
@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ViolationTypeRequestDto {
    @NotBlank
    private String name;
}
