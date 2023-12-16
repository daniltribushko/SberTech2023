package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.ViolationTypeAlreadyExistException;
import com.example.sbertech2023.exceptions.ViolationTypeByNameNotFoundException;
import com.example.sbertech2023.exceptions.ViolationTypeNotFoundException;
import com.example.sbertech2023.models.entities.ViolationType;
import com.example.sbertech2023.repositories.ViolationTypeRepository;
import com.example.sbertech2023.service.ViolationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Реализация сервиса по работе с типами нарушений
 */
@Service
public class ViolationTypeServiceImp implements ViolationTypeService {
    private final ViolationTypeRepository violationTypeRepository;

    @Autowired
    public ViolationTypeServiceImp(ViolationTypeRepository violationTypeRepository){
        this.violationTypeRepository = violationTypeRepository;
    }

    @Override
    public void saveViolationType(String name) {
        ViolationType violationType = findViolationTypeByName(name);
        if (violationType != null){
            throw new ViolationTypeAlreadyExistException(name);
        }
        violationTypeRepository.save(new ViolationType(name));
    }

    @Override
    public void deleteViolationType(Integer id) {
        violationTypeRepository.delete(findViolationTypeById(id));
    }

    @Override
    public ViolationType findViolationTypeById(Integer id) {
        return violationTypeRepository.findById(id)
                .orElseThrow(() -> new ViolationTypeNotFoundException(id));
    }

    @Override
    public ViolationType findViolationTypeByName(String name) {
        return violationTypeRepository.findByName(name)
                .orElseThrow(() -> new ViolationTypeByNameNotFoundException(name));
    }

    @Override
    public List<ViolationType> findAll() {
        return violationTypeRepository.findAll();
    }
}
