package com.example.sbertech2023.controllers;

import com.example.sbertech2023.models.dto.request.DistrictOrMicroDistrictRequestDto;
import com.example.sbertech2023.models.enums.AppealStatus;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import com.example.sbertech2023.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Tribushko Danil
 * @since 28.11.2023
 *
 * Контроллер администратора
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DistrictAndMicroDistrictService districtAndMicroDistrictService;
    private final UserService userService;
    private final AppealService appealService;

    @Autowired
    public AdminController(
            DistrictAndMicroDistrictService districtAndMicroDistrictService,
            UserService userService,
            AppealService appealService){
        this.districtAndMicroDistrictService = districtAndMicroDistrictService;
        this.userService = userService;
        this.appealService = appealService;
    }

    @GetMapping("")
    public String viewAdminPage(Model model, Principal principal){
        model.addAttribute("districtOrMicroDistrictRequest",
                new DistrictOrMicroDistrictRequestDto()
        );
        model.addAttribute("username", userService.findByUserName(principal.getName()));
        model.addAttribute("time", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm - dd:MM:yyyy")));
        model.addAttribute("nulMicroDistricts",
                districtAndMicroDistrictService.findAllMicroDistrictsByDistrict(null));
        model.addAttribute("districts", districtAndMicroDistrictService.findAllDistricts());
        model.addAttribute("appeals", appealService.findAppealsByStatus(AppealStatus.WAITING));
        return "admin";
    }

    @PostMapping("/districts/create")
    public String createNewDistrict(@Valid DistrictOrMicroDistrictRequestDto request){
        districtAndMicroDistrictService.saveDistrict(request);
        return "redirect:/admin";
    }

    @PostMapping("/micro-districts/create")
    public String createNewMicroDistrict(@Valid DistrictOrMicroDistrictRequestDto request){
        districtAndMicroDistrictService.saveMicroDistrict(request);
        return "redirect:/admin";
    }

    @PostMapping("/districts/{id}/delete")
    public String deleteDistrict(@Min(value = 1, message = "id can not be less than 1") @PathVariable Integer id){
        districtAndMicroDistrictService.deleteDistrict(id);
        return "redirect:/admin";
    }

    @PostMapping("/micro-districts/{id}/delete")
    public String deleteMicroDistrict(@Min(value = 1, message = "id can not be less than 1") @PathVariable Integer id){
        districtAndMicroDistrictService.deleteMicroDistrict(id);
        return "redirect:/admin";
    }

    @PostMapping("/districts/{id}/add/micro-district")
    public String addMicroDistrictInDistrict(
            @Min(value = 1, message = "id can not be less than 1") @PathVariable Integer id,
            @Valid DistrictOrMicroDistrictRequestDto request){
        districtAndMicroDistrictService.addMicroDistrictInDistrict(id, request);
        return "redirect:/admin";
    }

    @PostMapping("/district/{id}/delete/micro-district")
    public String deleteMicroDistrictFromDistrict(
            @Min(value = 1, message = "id can not be less than 1") @PathVariable Integer id,
            @NotBlank String name){
        districtAndMicroDistrictService.deleteMicroDistrictFromDistrict(id, name);
        return "redirect:/admin";
    }

    @PostMapping("/appeals/{id}/accept")
    public String acceptAppeal(@PathVariable Long id, Principal principal){
        appealService.acceptAppeal(id, principal.getName());
        return "redirect:/admin";
    }

    @PostMapping("/appeals/{id}/rejected")
    public String rejectedAppeal(@PathVariable Long id, Principal principal){
        appealService.rejectedAppeal(id, principal.getName());
        return "redirect:/admin";
    }
}
