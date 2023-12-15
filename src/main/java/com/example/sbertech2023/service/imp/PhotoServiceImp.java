package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.EmptyFileException;
import com.example.sbertech2023.exceptions.WrongPhotoExtensionException;
import com.example.sbertech2023.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 *
 * Реализация сервиса по работе с фотографиями обращений
 */
@Service
public class PhotoServiceImp implements PhotoService {
    private final static String DIRECTORY = "src/main/resources/files/photos/appeal";

    @Override
    public Optional<String> savePhoto(String userName, String appeal, MultipartFile file) {
        String result = null;
        if (file.isEmpty()){
            throw new EmptyFileException();
        }

        if (!file.getName().endsWith(".png")){
            throw new WrongPhotoExtensionException();
        }

        try (InputStream inputStream = file.getInputStream()){
            String filePath = DIRECTORY + "/" + userName + "_" + appeal + ".png";
            Path path = Path.of(filePath);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            result = filePath;
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return Optional.ofNullable(result);
    }
}
