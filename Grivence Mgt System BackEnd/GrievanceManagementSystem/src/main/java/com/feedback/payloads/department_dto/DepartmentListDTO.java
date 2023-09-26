package com.feedback.payloads.department_dto;

import java.util.Objects;

/**
 * DepartmentListDTO class.
 */
public class DepartmentListDTO {

  /**
   * deptId variable.
   */
  private int deptId;

  /**
   * deptName variable.
   */
  private String deptName;

  /**
   * getDeptId.
   *
   * @return deptId
   */
  public int getDeptId() {
    return deptId;
  }

  /**
   * setDeptId.
   *
   * @param deptIdd
   *
   */
  public void setDeptId(final int deptIdd) {
    this.deptId = deptIdd;
  }

  /**
   * getDeptName.
   *
   * @return deptName
   */
  public String getDeptName() {
    return deptName;
  }

  /**
   * setDeptName.
   *
   * @param deptNamee
   *
   */
  public void setDeptName(final String deptNamee) {
    this.deptName = deptNamee;
  }

  /**
   * toString.
   */
  @Override
  public String toString() {
    return "DepartmentListDTO [deptId=" + deptId
        + ", deptName=" + deptName
        + "]";
  }

  /**
   * DepartmentListDTO.
   *
   * @param l
   *
   * @param deptNamee
   *
   */
  public DepartmentListDTO(final int l, final String deptNamee) {
    super();
    this.deptId = l;
    this.deptName = deptNamee;
  }

  /**
   * DepartmentListDTO.
   */
  public DepartmentListDTO() {
    super();
  }

  /**
   * hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(deptId, deptName);
  }

  /**
   * equals object.
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
    DepartmentListDTO other = (DepartmentListDTO) obj;
    return deptId == other.deptId && Objects.equals(deptName, other.deptName);
  }
}
