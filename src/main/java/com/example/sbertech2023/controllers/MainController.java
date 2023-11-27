package com.example.sbertech2023.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String viewMainPage(){
        return "main";
    }
}
