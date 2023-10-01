package com.feedback.entities;

import java.util.Comparator;

/**
 * EStatus class.
 */
public enum EStatus {

  /**
   * open status.
   */
  Open,

  /**
   * Being_Addressed status.
   */
  Being_Addressed,

  /**
   * Resolved status.
   */
  Resolved;

  /**
   * comparator.
   *
   * @return sorted list.
   */
  public static Comparator<EStatus> getStatusComparator() {
    return Comparator.comparingInt(EStatus::getSortOrder);
  }

  /**
   * var.
   */
  private static final int THREE = 3;

  /**
   * var.
   */
  private static final int TWO = 2;

  /**
   * var.
   */
  private static final int ONE = 1;

  /**
   * getSortOrder.
   *
   *@return number according to randking of e
   */
  private int getSortOrder() {
    if (this == EStatus.Resolved) {
      return THREE;
    } else if (this == EStatus.Being_Addressed) {
      return TWO;
    } else if (this == EStatus.Open) {
      return ONE;
    } else {
      throw new IllegalStateException("Unexpected value: " + this);
    }
  }
}
