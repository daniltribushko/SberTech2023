package com.example.sbertech2023.exceptions;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 */
public class PhotoNotSavedException extends GlobalAppException{
    public PhotoNotSavedException() {
        super(409, "Photo not saved");
    }
}
