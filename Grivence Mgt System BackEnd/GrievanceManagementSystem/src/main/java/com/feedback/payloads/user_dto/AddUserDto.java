package com.feedback.payloads.user_dto;

import java.util.Objects;

import com.feedback.entities.ERole;

/**
 * DTO class for adding a user.
 */
public class AddUserDto {
  /**
   * The name of the user.
   */
  private String name;

  /**
   * The username of the user.
   */
  private String userName;

  /**
   * The password of the user.
   */
  private String password;

  /**
   * The type of the user (e.g., ADMIN, USER, etc.).
   */
  private ERole userType;

  /**
   * The name of the department to which the user belongs.
   */
  private String departmentName;

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
   * @param userNamee The userName of the user to be set.
   *
   */
  public void setUserName(final String userNamee) {
    this.userName = userNamee;
  }

  /**
   * Getter for the password of the user.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for the password of the user.
   *
   * @param passwordd The password of the user to be set.
   *
   */
  public void setPassword(final String passwordd) {
    this.password = passwordd;
  }

  /**
   * Getter for the type of the user.
   *
   * @return The type of the user.
   */
  public ERole getUserType() {
    return userType;
  }

  /**
   * Setter for the type of the user.
   *
   * @param userTypee The type of the user to be set.
   */
  public void setUserType(final ERole userTypee) {
    this.userType = userTypee;
  }

  /**
   * Getter for the name of the department to which the user belongs.
   *
   * @return The name of the department.
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Setter for the name of the department.
   *
   * @param departmentNamee The name of the department.
   *
   */
  public void setDepartmentName(final String departmentNamee) {
    this.departmentName = departmentNamee;
  }

  /**
   * Override of the toString method.
   */
  @Override
  public String toString() {
    return "AddUser [name=" + name
        + ", userName=" + userName
        + ", password=" + password
        + ", userType=" + userType
      + ", department=" + departmentName + "]";
  }

  /**
   * Constructor with parameters.
   *
   * @param namee The name of the user.
   *
   * @param userNamee The userName of the user.
   *
   * @param passwordd The password of the user.
   *
   * @param userTypee The type of the user.
   *
   * @param departmentNamee The name of the department.
   *
   */
  public AddUserDto(final String namee,
          final String userNamee,
          final String passwordd,
          final ERole userTypee,
          final String departmentNamee) {
    super();
    this.name = namee;
    this.userName = userNamee;
    this.password = passwordd;
    this.userType = userTypee;
    this.departmentName = departmentNamee;
  }

  /**
   * Default constructor.
   */
  public AddUserDto() {
    super();
  }

  /**
   * Override of the hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentName, name, password, userName, userType);
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
    AddUserDto other = (AddUserDto) obj;
    return Objects.equals(departmentName, other.departmentName)
          && Objects.equals(name, other.name)
          && Objects.equals(password, other.password)
          && Objects.equals(userName, other.userName)
          && userType == other.userType;
  }
}
