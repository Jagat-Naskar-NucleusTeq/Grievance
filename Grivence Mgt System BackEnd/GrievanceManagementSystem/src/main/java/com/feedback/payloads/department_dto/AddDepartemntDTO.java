package com.feedback.payloads.department_dto;

import java.util.Objects;

/**
 * AddDepartemntDTO class.
 */
public class AddDepartemntDTO {

  /**
   * The name of the department.
   */
  private String deptName;

  /**
   * Getter for the department name.
   *
   * @return The department name.
   */
  public String getDeptName() {
    return deptName;
  }

  /**
   * Setter for the department name.
   *
   * @param deptNamee
   *
   */
  public void setDeptName(final String deptNamee) {
    this.deptName = deptNamee;
  }

  /**
   * Constructor with a parameter for department name.
   *
   * @param deptNamee
   *
   */
  public AddDepartemntDTO(final String deptNamee) {
    super();
    this.deptName = deptNamee;
  }

  /**
   * Override of toString method.
   */
  @Override
  public String toString() {
    return "AddDepartemntDTO [deptName="
      + deptName + "]";
  }

  /**
   * Default constructor.
   */
  public AddDepartemntDTO() {
    super();
  }

  /**
   * Override of hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(deptName);
  }

  /**
   * Override of equals method.
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AddDepartemntDTO other = (AddDepartemntDTO) obj;
    return Objects.equals(deptName, other.deptName);
  }
}
