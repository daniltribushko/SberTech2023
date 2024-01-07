package com.example.sbertech2023.service;

import com.example.sbertech2023.models.entities.District;
import com.example.sbertech2023.models.entities.MicroDistrict;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 * <p>
 * Сервис для работы с районами и микрорайонами
 */
@Validated
public interface DistrictAndMicroDistrictService {

    /**
     * Сохранение района
     *
     * @param name имя района
     */
    void saveDistrict(@NotBlank String name);

    /**
     * Сохранение микрорайона
     *
     * @param name имя микрорайона
     */
    void saveMicroDistrict(@NotBlank String name);

    /**
     * Удаление района
     *
     * @param id id района
     */
    void deleteDistrict(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Удаление микрорайона
     *
     * @param id id микрорайона
     */
    void deleteMicroDistrict(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Сохранение микрорайона в район
     *
     * @param id      id района
     * @param name имя микрорайона
     */
    void addMicroDistrictInDistrict(
            @Min(value = 1, message = "Id can not be less than 1") Integer id,
            @NotBlank String name);

    /**
     * Удаление микрорайона из рацона
     *
     * @param id   id района
     * @param name название микрорайона
     */
    void deleteMicroDistrictFromDistrict(
            @Min(value = 1, message = "Id can not be less than 1") Integer id,
            @NotBlank String name);

    /**
     * Получение района по названию
     *
     * @param name название района
     * @return сущность района
     */
    District findDistrictByName(@NotBlank String name);

    /**
     * Получение микрорайона по названию
     *
     * @param name название микрорайона
     * @return сущность микрорайона
     */
    MicroDistrict findMicroDistrictByName(@NotBlank String name);

    /**
     * Получение района по id
     *
     * @param id идентификатор района
     * @return сущность района
     */
    District findDistrictById(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Получение микрорайона по id
     *
     * @param id идентификатор микрорайона
     * @return сущность микрорайона
     */
    MicroDistrict findMicroDistrictById(@Min(value = 1, message = "Id can not be less than 1") Integer id);

    /**
     * Получение всех районов из бд
     *
     * @return список районов
     */
    List<District> findAllDistricts();

    /**
     * Получение всех микрорайонов
     */
    List<MicroDistrict> findAllMicroDistricts();

    /**
     * Получение всех микрорайонов, которые включены в район
     */
    List<MicroDistrict> findAllMicroDistrictsWithDistricts();

    /**
     * Получение всех районов, в которых есть микрорайоны
     */
    List<District> findAllDistrictsWithMicroDistricts();

    /**
     * Получение всех микрорацонов по району
     */
    List<MicroDistrict> findAllMicroDistrictsByDistrict(@NotBlank String name);
}
