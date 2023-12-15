package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 *
 * Искоючение: Пустой файл
 */
public class EmptyFileException extends GlobalAppException{
    public EmptyFileException() {
        super(400, "file is empty");
    }
}
