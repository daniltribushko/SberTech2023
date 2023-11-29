package com.example.sbertech2023.controllers;

import com.example.sbertech2023.models.dto.request.DistrictOrMicroDistrictRequestDto;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    public AdminController(DistrictAndMicroDistrictService districtAndMicroDistrictService){
        this.districtAndMicroDistrictService = districtAndMicroDistrictService;
    }

    @GetMapping("/")
    public String viewAdminPage(Model model){
        model.addAttribute("districtOrMicroDistrictRequest",
                new DistrictOrMicroDistrictRequestDto()
        );
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
            @Valid DistrictOrMicroDistrictRequestDto request
    ){
        districtAndMicroDistrictService.deleteMicroDistrictFromDistrict(id, request);
        return "redirect:/admin";
    }
}
