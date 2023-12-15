package com.example.sbertech2023.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 *
 * Сервис для работы с фотографиями из обращений
 */
public interface PhotoService {
    /**
     * Сохранение фотографии
     * @param file фотография
     * @return адрес файла
     */
    Optional<String> savePhoto(String userName, String appeal, MultipartFile file);
}
