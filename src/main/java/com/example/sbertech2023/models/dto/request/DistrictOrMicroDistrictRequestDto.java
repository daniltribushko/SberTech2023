package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Dto на запрос работы с районами и микрорайонами
 */
@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class DistrictOrMicroDistrictRequestDto {
    @NotBlank
    private String name;
}
