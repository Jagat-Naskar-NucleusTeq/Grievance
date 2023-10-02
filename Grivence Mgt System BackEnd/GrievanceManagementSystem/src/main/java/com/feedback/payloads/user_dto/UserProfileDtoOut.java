package com.feedback.payloads.user_dto;

import java.util.Objects;

/**
 * UserProfileDTOout class.
 */
public class UserProfileDtoOut {

  /**
   * name.
   */
  private String name;

  /**
   * userName.
   */
  private String userName;

  /**
   * password.
   */
  private String password;

  /**
   * userType.
   */
  private String userType;

  /**
   * departmentName.
   */
  private String departmentName;

  /**
   * getName of user.
   *
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * setName of user.
   *
   * @param namee
   *
   */
  public void setName(final String namee) {
    this.name = namee;
  }

  /**
   * getUserName of userName.
   *
   * @return userName.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * setUserName of user.
   *
   * @param userNamee
   *
   */
  public void setUserName(final String userNamee) {
    this.userName = userNamee;
  }

  /**
   * getPassword of user.
   *
   * @return password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * setPassword of user.
   *
   * @param passwordd
   *
   */
  public void setPassword(final String passwordd) {
    this.password = passwordd;
  }

  /**
   * getUserType of user.
   *
   * @return userType.
   */
  public String getUserType() {
    return userType;
  }

  /**
   * setUserType of user.
   *
   * @param userTypee
   *
   */
  public void setUserType(final String userTypee) {
    this.userType = userTypee;
  }

  /**
   * getDepartmentName of user.
   *
   * @return departmentName.
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * setDepartmentName.
   *
   * @param departmentNamee
   *
   */
  public void setDepartmentName(final String departmentNamee) {
    this.departmentName = departmentNamee;
  }

  /**
   * To string method.
   */
  @Override
  public String toString() {
    return "UserProfileDTOout [name=" + name
        + ", userName=" + userName
        + ", password=" + password
        + ", userType=" + userType
        + ", departmentName=" + departmentName + "]";
  }

  /**
   * UserProfileDTOout field constructor.
   *
   * @param namee
   *
   * @param userNamee
   *
   * @param passwordd
   *
   * @param userTypee
   *
   * @param departmentNamee
   *
   */
  public UserProfileDtoOut(final String namee,
      final String userNamee,
      final String passwordd,
      final String userTypee,
      final String departmentNamee) {
    super();
    this.name = namee;
    this.userName = userNamee;
    this.password = passwordd;
    this.userType = userTypee;
    this.departmentName = departmentNamee;
  }

  /**
   * UserProfileDTOout, no args constructor.
   */
  public UserProfileDtoOut() {
    super();
  }

  /**
   * HashCode function.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentName, name, password, userName, userType);
  }

  /**
   * object equals function.
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
    UserProfileDtoOut other = (UserProfileDtoOut) obj;
    return Objects.equals(departmentName, other.departmentName)
        && Objects.equals(name, other.name)
        && Objects.equals(password, other.password)
        && Objects.equals(userName, other.userName)
        && Objects.equals(userType, other.userType);
  }
}
