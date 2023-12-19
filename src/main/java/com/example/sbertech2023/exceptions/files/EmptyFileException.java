package com.example.sbertech2023.exceptions.files;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 *
 * Искоючение: Пустой файл
 */
public class EmptyFileException extends GlobalAppException {
    public EmptyFileException() {
        super(400, "file is empty");
    }
}
