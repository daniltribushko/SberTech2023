package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.appeals.AppealByIdNotFoundException;
import com.example.sbertech2023.exceptions.appeals.AppealByNameNotFoundException;
import com.example.sbertech2023.exceptions.files.PhotoNotSavedException;
import com.example.sbertech2023.exceptions.microdistricts.MicroDistrictIsNotInDistrictException;
import com.example.sbertech2023.exceptions.users.AdminChangeStatusHisAppealException;
import com.example.sbertech2023.exceptions.users.UserNotAdminException;
import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.models.entities.*;
import com.example.sbertech2023.models.enums.AppealStatus;
import com.example.sbertech2023.repositories.AppealRepository;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import com.example.sbertech2023.service.PhotoService;
import com.example.sbertech2023.service.ViolationTypeService;
import com.example.sbertech2023.service.auth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private final AuthUserService authUserService;
    @Autowired
    public AppealServiceImp(
            AppealRepository appealRepository,
            ViolationTypeService violationTypeService,
            DistrictAndMicroDistrictService districtAndMicroDistrictService,
            PhotoService photoService,
            AuthUserService authUserService) {
        this.appealRepository = appealRepository;
        this.violationTypeService = violationTypeService;
        this.districtAndMicroDistrictService = districtAndMicroDistrictService;
        this.photoService = photoService;
        this.authUserService = authUserService;
    }

    @Override
    public void saveAppeal(SaveAppealRequestDto request, String username) {
        MicroDistrict microDistrict = districtAndMicroDistrictService
                .findMicroDistrictByName(request
                        .getMicroDistrict());
        District district = microDistrict.getDistrict();
        if (district == null){
            throw new MicroDistrictIsNotInDistrictException(microDistrict.getName());
        }
        User user = authUserService.findUserByUserName(username);
        ViolationType violationType = violationTypeService.findViolationTypeByName(request
                .getViolationType()
                .getName()
        );
        Appeal appeal = new Appeal(request.getTitle(), request.getText(), request.getAddress());
        district.addAppeal(appeal);
        microDistrict.addAppeal(appeal);
        user.addAppeal(appeal);
        violationType.addAppeal(appeal);
        MultipartFile file = request.getPhoto();
        //Если файл не пустой, то сохраняем его
        if (!file.isEmpty()){
            appeal.setPhoto(photoService.savePhoto(username, request.getTitle(), file)
                    .orElseThrow(() -> new PhotoNotSavedException()));
        }
        appealRepository.save(appeal);
    }

    @Override
    public Appeal findAppealByTitle(String title) {
        return appealRepository.findByTitle(title)
                .orElseThrow(() -> new AppealByNameNotFoundException(title));
    }

    @Override
    public Appeal findAppealById(Long id) {
        return appealRepository.findById(id)
                .orElseThrow(() -> new AppealByIdNotFoundException(id));
    }

    @Override
    public void acceptAppeal(Long id, String userName) {
        Appeal appeal = findAppealById(id);
        isAdminAndDontWorkWithHisAppeal(userName, appeal);
        appeal.setStatus(AppealStatus.ACCEPTED);
        appealRepository.save(appeal);
    }

    @Override
    public void rejectedAppeal(Long id, String userName) {
        Appeal appeal = findAppealById(id);
        isAdminAndDontWorkWithHisAppeal(userName, appeal);
        appeal.setStatus(AppealStatus.REJECTED);
        appealRepository.save(appeal);
    }

    @Override
    public List<Appeal> findAppealsByStatus(AppealStatus appealStatus) {
        return appealRepository.findAllByStatus(appealStatus);
    }

    /**
     * Проверка, является ли пользователь администратором и не работает со своим обращением
     * @param userName имя пользователя
     * @param appeal сущность обращения
     */
    private void isAdminAndDontWorkWithHisAppeal(String userName, Appeal appeal){
        User user = authUserService.findUserByUserName(userName);
        if (!authUserService.isAdmin(user)){
            throw new UserNotAdminException(userName);
        }
        if (user.getId().equals(appeal.getAuthor().getId())){
            throw new AdminChangeStatusHisAppealException();
        }
    }
}
