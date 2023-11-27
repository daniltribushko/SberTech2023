package com.example.sbertech2023.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 */
@Getter
@AllArgsConstructor
public class GlobalAppException extends RuntimeException{
    private Integer status;
    private String message;
}
