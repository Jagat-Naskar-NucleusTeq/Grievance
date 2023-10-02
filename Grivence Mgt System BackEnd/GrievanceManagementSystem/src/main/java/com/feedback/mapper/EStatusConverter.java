package com.feedback.mapper;

import java.util.Locale;

import com.feedback.entities.EStatus;

/**
 * This class provides a method for converting a String to an EStatus enum.
 */
public class EStatusConverter {
    protected EStatusConverter() {
        super();
    }

    /**
     * Converts a String to an EStatus enum.
     * @param eStatus The String representation of EStatus.
     * @return The corresponding EStatus enum value, or null if not found.
     */
    public static EStatus convertStringToEStatus(final String eStatus) {
        switch (eStatus.toLowerCase(Locale.ENGLISH)) {
            case "open" :
                return EStatus.Open;
            case "being_addressed" :
                return EStatus.Being_Addressed;
            case "resolved" :
                return EStatus.Resolved;
            default :
                return null;
        }
    }
}
