package com.example.sbertech2023.controllers;

import com.example.sbertech2023.exceptions.users.UserAlreadyExistException;
import com.example.sbertech2023.models.dto.request.LoginUserRequestDto;
import com.example.sbertech2023.models.dto.request.SaveUserRequestDto;
import com.example.sbertech2023.models.enums.Role;
import com.example.sbertech2023.service.auth.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Контроллер для авторизации и аутенфикации
 */
@Validated
@Controller
public class AuthController {
    private final AuthUserService authUserService;

    @Autowired
    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    /**
     * Отображение страницы входа
     */
    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("request", new LoginUserRequestDto());
        return "login";
    }

    /**
     * Отображение страницы регистрации
     */
    @GetMapping("/registration")
    public String viewRegistrationPage(Model model) {
        model.addAttribute("roles", List.of(Role.USER, Role.ORGANIZATION));
        model.addAttribute("request", new SaveUserRequestDto());
        return "registration";
    }

    /**
     * Регистрация пользователя с переадресацией на страницу входа
     * @param request запрос на регистрацию пользователя
     */
    @PostMapping("/registration")
    public String registr(
            @Valid SaveUserRequestDto request,
            Model model
    ) {
        System.out.println(request);
        try {
            authUserService.saveUser(request);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("error", "Такой пользователь уже существует");
        }
        return "redirect:/login";
    }
}
