package com.example.sbertech2023.exceptions.files;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 14.12.2023
 *
 * Исключение: Неверное расширение фотографии
 */
public class WrongPhotoExtensionException extends GlobalAppException {
    public WrongPhotoExtensionException() {
        super(409, "Extension photo must be png");
    }
}
