package com.example.sbertech2023.exceptions.files;

import com.example.sbertech2023.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 15.12.2023
 */
public class PhotoNotSavedException extends GlobalAppException {
    public PhotoNotSavedException() {
        super(409, "Photo not saved");
    }
}
