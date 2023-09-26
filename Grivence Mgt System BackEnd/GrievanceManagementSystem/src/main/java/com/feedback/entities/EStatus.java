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
   *
   * @return Comparator
   */
  public static Comparator<EStatus> getStatusComparator() {
    return Comparator.comparingInt(EStatus::getSortOrder);
  }

  private int getSortOrder() {
    switch (this) {
      case Resolved:
        return 3;
      case Being_Addressed:
        return 2;
      case Open:
        return 1;
      default:
        throw new IllegalStateException("Unexpected value: " + this);
    }
  }
}
