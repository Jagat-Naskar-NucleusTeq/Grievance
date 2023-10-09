package com.feedback.payloads.department_dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * AddDepartemntDTO class.
 */
public class AddDepartemntDto {

  /**
   * The name of the department.
   */
  @NotNull
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
  public AddDepartemntDto(final String deptNamee) {
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
  public AddDepartemntDto() {
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
    AddDepartemntDto other = (AddDepartemntDto) obj;
    return Objects.equals(deptName, other.deptName);
  }
}
