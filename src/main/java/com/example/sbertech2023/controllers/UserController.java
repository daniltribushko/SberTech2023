package com.example.sbertech2023.controllers;

import com.example.sbertech2023.models.dto.request.SaveAppealRequestDto;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.DistrictAndMicroDistrictService;
import com.example.sbertech2023.service.UserService;
import com.example.sbertech2023.service.ViolationTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private final ViolationTypeService violationTypeService;
    private final AppealService appealService;
    private final DistrictAndMicroDistrictService districtAndMicroDistrictService;
    private final UserService userService;

    @Autowired
    public UserController(ViolationTypeService violationTypeService,
                          AppealService appealService,
                          DistrictAndMicroDistrictService districtAndMicroDistrictService,
                          UserService userService) {
        this.appealService = appealService;
        this.violationTypeService = violationTypeService;
        this.districtAndMicroDistrictService = districtAndMicroDistrictService;
        this.userService = userService;
    }

    @GetMapping("")
    public String viewMainPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        model.addAttribute("username", principal.getName());
        model.addAttribute("violationTypes", violationTypeService.findAll());
        model.addAttribute("districts",
                districtAndMicroDistrictService.findAllDistrictsWithMicroDistricts());
        model.addAttribute("saveAppealRequest", new SaveAppealRequestDto());
        return "main";
    }

    @PostMapping("/add/appeal")
    public String addAppeal(@Valid SaveAppealRequestDto saveAppealRequest, Principal principal){
        System.out.println(saveAppealRequest);
        appealService.saveAppeal(saveAppealRequest, principal.getName());
        return "redirect:/users";
    }
}
