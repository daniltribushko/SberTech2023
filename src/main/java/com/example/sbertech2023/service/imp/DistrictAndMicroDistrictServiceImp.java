package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.districts.DistrictAlreadyExistException;
import com.example.sbertech2023.exceptions.districts.DistrictByIdNotFoundException;
import com.example.sbertech2023.exceptions.districts.DistrictNotFoundException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictAlreadyExistException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictNotFoundException;
import com.example.sbertech2023.models.dto.request.DistrictOrMicroDistrictRequestDto;
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
     * @param request запрос на работу с районом
     */
    @Override
    public void saveDistrict(DistrictOrMicroDistrictRequestDto request) {
        District district = districtRepository.findByName(request.getName())
                .orElse(null);
        if (district != null){
            throw new DistrictAlreadyExistException(request.getName());
        } else {
            districtRepository.save(new District(request.getName()));
        }
    }

    /**
     * Сохранение микрорайона в бд
     *
     * @param request запрос на работу с микрорайоном
     */
    @Override
    public void saveMicroDistrict(DistrictOrMicroDistrictRequestDto request) {
        String name = request.getName();
        MicroDistrict microDistrict = findMicroDistrictByName(name);
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
     * @param request запрос на работу с микрорайоном
     */
    @Override
    public void addMicroDistrictInDistrict(Integer id, DistrictOrMicroDistrictRequestDto request) {
        District district = findDistrictById(id);
        MicroDistrict microDistrict = findMicroDistrictByName(request.getName());
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
        return null;
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
     * @param request запрос на работу с районами и микрорайонами
     * @return список микрорайонов
     */
    @Override
    public List<MicroDistrict> findAllMicroDistrictsByDistrict(DistrictOrMicroDistrictRequestDto request) {
        List<MicroDistrict> microDistricts;
        if (request != null){
            District district = findDistrictByName(request.getName());
            microDistricts = microDistrictRepository.findAllByDistrict(district);
        }else {
            microDistricts = microDistrictRepository.findAllByDistrict(null);
        }
        return microDistricts;
    }
}
