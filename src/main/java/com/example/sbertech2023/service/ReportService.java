package com.example.sbertech2023.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 18.12.2023
 * <p>
 * Сервис для создание отчетов по обращениям
 */
@Validated
public interface ReportService {
    /**
     * Получение отчета по обращению пользователя
     *
     * @param userName имя пользователя
     * @param userId   id пользователя
     * @param appealId id обращения
     * @return Resource файла
     */
    Resource downloadReport(String userName,
                            @Min(value = 1, message = "Id can not be less than 1") Long userId,
                            @Min(value = 1, message = "Id can not be less than 1") Long appealId) throws MalformedURLException;

    List<Resource> downloadAllReportsByUser(String userName,
                                            @Min(value = 1, message = "Id can not be less than 1") Long userId,
                                            @Min(value = 0, message = "Page can not be less than 0") Integer page,
                                            @Max(value = 1000, message = "Count can not be more than 1000")
                                            @Min(value = 1, message = "Count can not be less than 1") Integer count);
}
