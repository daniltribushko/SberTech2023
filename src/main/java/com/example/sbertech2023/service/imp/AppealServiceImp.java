package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.PhotoNotSavedException;
import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.models.entities.*;
import com.example.sbertech2023.repositories.AppealRepository;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import com.example.sbertech2023.service.PhotoService;
import com.example.sbertech2023.service.ViolationTypeService;
import com.example.sbertech2023.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 * <p>
 * Реализация сервиса по работе с обращениями
 */
@Service
public class AppealServiceImp implements AppealService {
    private final AppealRepository appealRepository;
    private final ViolationTypeService violationTypeService;
    private final DistrictAndMicroDistrictService districtAndMicroDistrictService;
    private final PhotoService photoService;
    private final UserService userService;
    @Autowired
    public AppealServiceImp(
            AppealRepository appealRepository,
            ViolationTypeService violationTypeService,
            DistrictAndMicroDistrictService districtAndMicroDistrictService,
            PhotoService photoService,
            UserService userService) {
        this.appealRepository = appealRepository;
        this.violationTypeService = violationTypeService;
        this.districtAndMicroDistrictService = districtAndMicroDistrictService;
        this.photoService = photoService;
        this.userService = userService;
    }

    @Override
    public void saveAppeal(SaveAppealRequestDto request, String username) {
        District district = districtAndMicroDistrictService
                .findDistrictByName(request
                        .getDistrict()
                        .getName());
        MicroDistrict microDistrict = districtAndMicroDistrictService
                .findMicroDistrictByName(request
                        .getMicroDistrict()
                        .getName());
        User user = userService.findUserByUserName(username);
        ViolationType violationType = violationTypeService.findViolationTypeByName(request
                .getViolationType()
                .getName()
        );
        Appeal appeal = new Appeal(request.getTitle(), request.getText());
        district.addAppeal(appeal);
        microDistrict.addAppeal(appeal);
        user.addAppeal(appeal);
        violationType.addAppeal(appeal);
        MultipartFile file = request.getPhoto();
        if (file != null){
            appeal.setPhoto(photoService.savePhoto(username, request.getTitle(), file)
                    .orElseThrow(() -> new PhotoNotSavedException()));
        }
        appealRepository.save(appeal);
    }

    @Override
    public Optional<Appeal> findAppealByTitle(String title) {
        return appealRepository.findByTitle(title);
    }
}
