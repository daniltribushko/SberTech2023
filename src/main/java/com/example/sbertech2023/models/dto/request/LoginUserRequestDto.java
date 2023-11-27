package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Dto запроса на аутенфикацию пользователя
 */
@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
