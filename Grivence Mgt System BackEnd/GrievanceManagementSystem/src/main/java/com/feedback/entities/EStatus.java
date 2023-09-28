package com.feedback.entities;

import java.util.Comparator;

/**
 * enum class for eStatus.
 */
public enum EStatus {
    /**
     * first ot default value.
     */
    Open,
    /**
     * current position.
     */
    Being_Addressed,
    /**
     * done.
     */
    Resolved;

    /**
     * getStatusComparator.
     * @return Comparator
     */
    public static Comparator<EStatus> getStatusComparator() {
        return Comparator.comparingInt(EStatus::getSortOrder);
    }

    private int getSortOrder() {
        final int one = 1;
        final int two = 2;
        final int three = 3;
        switch (this) {
            case Resolved :
                return three;
            case Being_Addressed :
                return two;
            case Open :
                return one;
            default :
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
