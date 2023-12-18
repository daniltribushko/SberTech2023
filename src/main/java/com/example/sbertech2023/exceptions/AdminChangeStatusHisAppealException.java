package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 18.12.2023
 *
 * Исключение: Администратор пытается изменить статус своего обращения
 */
public class AdminChangeStatusHisAppealException extends GlobalAppException{
    public AdminChangeStatusHisAppealException() {
        super(409, "admin cannot change the status of his appeal");
    }
}
