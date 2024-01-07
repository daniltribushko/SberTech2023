package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.districts.DistrictAlreadyExistException;
import com.example.sbertech2023.exceptions.districts.DistrictByIdNotFoundException;
import com.example.sbertech2023.exceptions.districts.DistrictNotFoundException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictAlreadyExistException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictByIdNotFoundException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictNotFoundException;
import com.example.sbertech2023.models.entities.District;
import com.example.sbertech2023.models.entities.MicroDistrict;
import com.example.sbertech2023.repositories.DistrictRepository;
import com.example.sbertech2023.repositories.MicroDistrictRepository;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Реализация сервиса для работы с районами и микрорайонами
 */
@Service
public class DistrictAndMicroDistrictServiceImp implements DistrictAndMicroDistrictService {
    private final DistrictRepository districtRepository;
    private final MicroDistrictRepository microDistrictRepository;

    @Autowired
    public DistrictAndMicroDistrictServiceImp(
            DistrictRepository districtRepository,
            MicroDistrictRepository microDistrictRepository) {
        this.districtRepository = districtRepository;
        this.microDistrictRepository = microDistrictRepository;
    }

    /**
     * Сохранение района в бд
     *
     * @param name имя района
     */
    @Override
    public void saveDistrict(String name) {
        District district = districtRepository.findByName(name)
                .orElse(null);
        if (district != null){
            throw new DistrictAlreadyExistException(name);
        } else {
            districtRepository.save(new District(name));
        }
    }

    /**
     * Сохранение микрорайона в бд
     *
     * @param name название микрорайона
     */
    @Override
    public void saveMicroDistrict(String name) {
        MicroDistrict microDistrict = microDistrictRepository.findByName(name)
                .orElse(null);
        if (microDistrict != null){
            throw new MicroDistrictAlreadyExistException(name);
        } else {
            microDistrictRepository.save(new MicroDistrict(name));
        }
    }

    /**
     * Удаление района
     *
     * @param id id района
     */
    @Override
    public void deleteDistrict(Integer id) {
        District district = findDistrictById(id);
        districtRepository.delete(district);
    }

    /**
     * Удаление микрорайона
     *
     * @param id id микрорайона
     */
    @Override
    public void deleteMicroDistrict(Integer id) {
        MicroDistrict microDistrict = findMicroDistrictById(id);
        microDistrictRepository.delete(microDistrict);
    }

    /**
     * Добавление микрорайона в район
     *
     * @param id id района
     * @param name имя микрорайона
     */
    @Override
    public void addMicroDistrictInDistrict(Integer id, String name) {
        District district = findDistrictById(id);
        MicroDistrict microDistrict = findMicroDistrictByName(name);
        district.addMicroDistrict(microDistrict);
        districtRepository.save(district);
    }

    /**
     * Удаление микрорайона из района
     *
     * @param id id района
     * @param name название микрорайона
     */
    @Override
    public void deleteMicroDistrictFromDistrict(Integer id, String name) {
        District district = findDistrictById(id);
        MicroDistrict microDistrict = findMicroDistrictByName(name);
        district.removeMicroDistrict(microDistrict);
        districtRepository.save(district);
    }

    @Override
    public District findDistrictByName(String name) {
        return districtRepository.findByName(name)
                .orElseThrow(() -> new DistrictNotFoundException(name));
    }

    @Override
    public MicroDistrict findMicroDistrictByName(String name) {
        return microDistrictRepository.findByName(name)
                .orElseThrow(() -> new MicroDistrictNotFoundException(name));
    }

    @Override
    public District findDistrictById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new DistrictByIdNotFoundException(id));
    }

    @Override
    public MicroDistrict findMicroDistrictById(Integer id) {
        return microDistrictRepository.findById(id)
                .orElseThrow(() -> new MicroDistrictByIdNotFoundException(id));
    }

    /**
     * Получение всех районов
     *
     * @return список районов
     */
    @Override
    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    /**
     * Получение всех микрорайонов
     *
     * @return список микрорайонов
     */
    @Override
    public List<MicroDistrict> findAllMicroDistricts() {
        return microDistrictRepository.findAll();
    }

    @Override
    public List<MicroDistrict> findAllMicroDistrictsWithDistricts() {
        return microDistrictRepository.findAllByDistrictNotNull();
    }

    @Override
    public List<District> findAllDistrictsWithMicroDistricts() {
        return districtRepository.findAllByMicroDistrictsNotEmpty();
    }

    /**
     * Получение всех микрорайонов по району
     *
     * @param name имя региона
     * @return список микрорайонов
     */
    @Override
    public List<MicroDistrict> findAllMicroDistrictsByDistrict(String name) {
        List<MicroDistrict> microDistricts;
        if (name != null){
            District district = findDistrictByName(name);
            microDistricts = microDistrictRepository.findAllByDistrict(district);
        }else {
            microDistricts = microDistrictRepository.findAllByDistrict(null);
        }
        return microDistricts;
    }
}
