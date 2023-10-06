package com.feedback.mapper;

import java.util.Locale;

import com.feedback.entities.Estatus;

/**
 * This class provides a method for converting a String to an EStatus enum.
 */
public class EstatusConverter {

    /**
     * EStatus converter constructor.
     */
    public EstatusConverter() {
        super();
    }

    /**
     * Converts a String to an EStatus enum.
     * @param eStatus The String representation of EStatus.
     * @return The corresponding EStatus enum value, or null if not found.
     */
    public static Estatus convertStringToEStatus(final String eStatus) {
        switch (eStatus.toLowerCase(Locale.ENGLISH)) {
            case "open" :
                return Estatus.Open;
            case "being_addressed" :
                return Estatus.Being_Addressed;
            case "resolved" :
                return Estatus.Resolved;
            default :
                return null;
        }
    }
}
