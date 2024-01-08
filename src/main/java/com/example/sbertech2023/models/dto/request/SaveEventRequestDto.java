package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @author Tribuhko Danil
 * @since 07.01.2024
 *
 * Dto запрос на создание события
 */
public class SaveEventRequestDto {
    @NotBlank
    private String name;
    @Future
    private LocalDateTime startDate;
    @NotBlank
    private String description;
    @Min(value = 5, message = "Max count participant can not be less than 5")
    private Integer maxCountParticipant;
    @NotBlank
    private String address;
}
