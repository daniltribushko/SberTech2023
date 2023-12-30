package com.example.sbertech2023.exceptions.files;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 19.12.2023
 *
 * Исключение: Отчет не найден
 */
public class ReportDontFindException extends GlobalAppException {
    public ReportDontFindException(String fileName) {
        super(404, "Report " + fileName + "do not find");
    }
}
