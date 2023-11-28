package com.example.sbertech2023.service;

import com.example.sbertech2023.models.dto.request.DistrictOrMicroDistrictRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Сервис для работы с районами и микрорайонами
 */
@Validated
public interface DistrictAndMicroDistrictService {

    /**
     * Сохранение района
     * @param request запрос на работу с районом
     */
    void saveDistrict(@Valid DistrictOrMicroDistrictRequestDto request);

    /**
     * Сохранение миерорайона
     * @param request запрос на работу с микрорайоном
     */
    void saveMicroDistrict(@Valid DistrictOrMicroDistrictRequestDto request);

    /**
     * Удаление района
     * @param id id района
     */
    void deleteDistrict(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Удаление микрорайона
     * @param id id микрорайона
     */
    void deleteMicroDistrict(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Сохранение микрорайона в район
     * @param id id района
     * @param request запрос на работу с микрорайоном
     */
    void addMicroDistrictInDistrict(
            @Min(value = 1, message = "Id can not be less than 1") Integer id,
            @Valid DistrictOrMicroDistrictRequestDto request);

    /**
     * Удаление микрорайона из рацона
     * @param id id района
     * @param request запрос на работу с микрорайоном
     */
    void deleteMicroDistrictFromDistrict(
            @Min(value = 1, message = "Id can not be less than 1") Integer id,
            @Valid DistrictOrMicroDistrictRequestDto request);
}
