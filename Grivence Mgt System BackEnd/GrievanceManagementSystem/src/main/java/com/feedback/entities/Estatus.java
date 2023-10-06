package com.feedback.entities;

import java.util.Comparator;

/**
 * EStatus class.
 */
public enum Estatus {

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
  public static Comparator<Estatus> getStatusComparator() {
    return Comparator.comparingInt(Estatus::getSortOrder);
  }

  /**
   * variable.
   */
  private static final int THREE = 3;

  /**
   * variable.
   */
  private static final int TWO = 2;

  /**
   * variable.
   */
  private static final int ONE = 1;

  /**
   * getSortOrder.
   *
   *@return number according to randking of e
   */
  private int getSortOrder() {
    if (this == Estatus.Resolved) {
      return THREE;
    } else if (this == Estatus.Being_Addressed) {
      return TWO;
    } else if (this == Estatus.Open) {
      return ONE;
    } else {
      throw new IllegalStateException("Unexpected value: " + this);
    }
  }
}
