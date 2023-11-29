package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.*;
import com.example.sbertech2023.models.dto.request.DistrictOrMicroDistrictRequestDto;
import com.example.sbertech2023.models.entities.District;
import com.example.sbertech2023.models.entities.MicroDistrict;
import com.example.sbertech2023.repositories.DistrictRepository;
import com.example.sbertech2023.repositories.MicroDistrictRepository;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
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

    @Override
    public void saveMicroDistrict(DistrictOrMicroDistrictRequestDto request) {
        MicroDistrict microDistrict = microDistrictRepository.findByName(request.getName())
                .orElse(null);
        if (microDistrict != null){
            throw new MicroDistrictAlreadyExistException(request.getName());
        } else {
            microDistrictRepository.save(new MicroDistrict(request.getName()));
        }
    }

    @Override
    public void deleteDistrict(Integer id) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictByIdNotFoundException(id));
        districtRepository.delete(district);
    }

    @Override
    public void deleteMicroDistrict(Integer id) {
        MicroDistrict microDistrict = microDistrictRepository.findById(id)
                .orElseThrow(() -> new MicroDistrictByIdNotFoundException(id));
        microDistrictRepository.delete(microDistrict);
    }

    @Override
    public void addMicroDistrictInDistrict(Integer id, DistrictOrMicroDistrictRequestDto request) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictByIdNotFoundException(id));
        MicroDistrict microDistrict = microDistrictRepository.findByName(request.getName())
                .orElseThrow(() -> new MicroDistrictNotFoundException(request.getName()));
        district.addMicroDistrict(microDistrict);
        districtRepository.save(district);
    }

    @Override
    public void deleteMicroDistrictFromDistrict(Integer id, DistrictOrMicroDistrictRequestDto request) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictByIdNotFoundException(id));
        MicroDistrict microDistrict = microDistrictRepository.findByName(request.getName())
                .orElseThrow(() -> new MicroDistrictNotFoundException(request.getName()));
        district.removeMicroDistrict(microDistrict);
        districtRepository.save(district);
    }
}
