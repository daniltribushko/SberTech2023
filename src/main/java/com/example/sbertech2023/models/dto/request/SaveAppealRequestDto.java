package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Запрос DTO на сохранение обращения
 */
@Getter
@Setter
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveAppealRequestDto {
    /**
     * Заголовок обращения
     */
    @NotBlank
    private String title;

    /**
     * Адрес, на котором было зафиксировано нарушение
     */
    @NotBlank
    private String address;

    /**
     * Текст обращения
     */
    @NotBlank
    private String text;

    /**
     * Фото, на котором зафиксировано нарушение
     */
    private MultipartFile photo;

    /**
     * Запрос DTO на работы с типом нарушения
     */
    @NotNull
    private String violationType;

    /**
     * Запрос DTO на работы с микрорайоном
     */
    @NotNull
    private String microDistrict;
}
