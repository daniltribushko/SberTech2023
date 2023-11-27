package com.example.sbertech2023.models.dto.request;

import com.example.sbertech2023.models.entities.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Dto запроса на регистрацию пользователя
 */
@Getter
@Setter
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String sureName;
    @NotBlank
    private String name;
    @NotBlank
    private String role;

    @NotBlank
    @Min(value = 8, message = "password length can not be less than 8")
    private String password;

    public User mapToEntity(){
        return new User(login, sureName, name, password);
    }
}
