package com.feedback.payloads.user_dto;

import java.util.Objects;

/**
 * GetAllUsersDtoOut class.
 */
public class GetAllUsersDtoOut {
  /**
   * The unique ID of the user.
  */
  private int id;

  /**
   * The name of the user.
   */
  private String name;

  /**
   * The userName of the user.
   */
  private String userName;

  /**
   * The userType of the user.
   */
  private String userType;

  /**
   * The name of the department to which the user belongs.
   */
  private String departmentName;

  /**
   * Getter for the ID of the user.
   *
   * @return The ID of the user.
   */
  public int getId() {
    return id;
  }

  /**
   * Setter for the ID of the user.
   *
   * @param idd The ID of the user to be set.
   *
   */
  public void setId(final int idd) {
    this.id = idd;
  }

  /**
   * Getter for the name of the user.
   *
   * @return The name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for the name of the user.
   *
   * @param namee The name of the user to be set.
   *
   */
  public void setName(final String namee) {
    this.name = namee;
  }

  /**
   * Getter for the userName of the user.
   *
   * @return The userName of the user.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Setter for the userName of the user.
   *
   * @param userNamee The userName of the user.
   *
   */
  public void setUserName(final String userNamee) {
    this.userName = userNamee;
  }

  /**
   * Getter for the userType of the user.
   *
   * @return userType.
   */

  public String getUserType() {
    return userType;
  }

  /**
   * Setter for the type of the user.
   *
   * @param userTypee The type of the user to be set.
   *
   */
  public void setUserType(final String userTypee) {
    this.userType = userTypee;
  }

  /**
   * Getter for the name of the department.
   *
   * @return The name of the department.
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Setter for the name of the department.
   *
   * @param departmentNamee
   *
   */
  public void setDepartmentName(final String departmentNamee) {
    this.departmentName = departmentNamee;
  }

  /**
   * Override of the hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentName, id, name, userName, userType);
  }

  /**
   * Override of the equals method.
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
    GetAllUsersDtoOut other = (GetAllUsersDtoOut) obj;
    return Objects.equals(departmentName, other.departmentName)
            && id == other.id && Objects.equals(name, other.name)
            && Objects.equals(userName, other.userName)
            && Objects.equals(userType, other.userType);
  }

  /**
  * Override of the toString method.
  */
  @Override
  public String toString() {
    return "GetAllUsersDtoOut [id=" + id
            + ", name=" + name
            + ", userName=" + userName
            + ", userType=" + userType
            + ", departmentName=" + departmentName + "]";
  }

  /**
   * Constructor with parameters.
   *
   * @param idd The ID of the user.
   *
   * @param namee The name of the user.
   *
   * @param userNamee The userName of the user.
   *
   * @param userTypee The type of the user.
   *
   * @param departmentNamee The name of the department.
   *
   */
  public GetAllUsersDtoOut(final int idd,
          final String namee,
          final String userNamee,
          final String userTypee,
          final String departmentNamee) {
    super();
    this.id = idd;
    this.name = namee;
    this.userName = userNamee;
    this.userType = userTypee;
    this.departmentName = departmentNamee;
  }

  /**
   * Default constructor.
   */
  public GetAllUsersDtoOut() {
    super();
  }
}
