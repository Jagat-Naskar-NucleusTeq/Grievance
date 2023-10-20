package com.feedback.payloads.user_dto;

import java.util.Objects;

/**
 * LoginDtoOut class.
 */
public class LoginDtoOut {

  /**
   * name variable.
   */
  private String name;

  /**
   * userName variable.
   */
  private String userName;

  /**
   * password variable.
   */
  private String password;

  /**
   * userType variable.
   */
  private String userType;

  /**
   * finalPassword variable.
   */
  private String finalPassword;

  /**
   * departmentName variable.
   */
  private String departmentName;

  /**
   * getName.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * setName.
   *
   * @param namee the name to set
   *
   */
  public void setName(String namee) {
    this.name = namee;
  }

  /**
   * getUserName.
   *
   * @return the userName
   *
   */
  public String getUserName() {
    return userName;
  }

  /**
   * setUserName.
   *
   * @param userNamee the userName to set
   *
   */
  public void setUserName(String userNamee) {
    this.userName = userNamee;
  }

  /**
   * getPassword.
   *
   * @return the password
   *
   */
  public String getPassword() {
    return password;
  }

  /**
   * setPassword.
   *
   * @param passwordd the password to set
   *
   */
  public void setPassword(String passwordd) {
    this.password = passwordd;
  }

  /**
   * getUserType.
   *
   * @return the userType
   *
   */
  public String getUserType() {
    return userType;
  }

  /**
   * setUserTypee.
   *
   * @param userTypee the userType to set
   *
   */
  public void setUserType(final String userTypee) {
    this.userType = userTypee;
  }

  /**
   * getFinalPassword.
   *
   * @return the finalPassword
   *
   */
  public String getFinalPassword() {
    return finalPassword;
  }

  /**
   * setFinalPassword.
   *
   * @param finalPasswordd the finalPassword to set
   *
  */
  public void setFinalPassword(final String finalPasswordd) {
    this.finalPassword = finalPasswordd;
  }

  /**
   * getDepartmentName.
   *
  * @return the departmentName
  *
  */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * setDepartmentName.
   *
   * @param departmentNamee the departmentName to set
   *
  */
  public void setDepartmentName(final String departmentNamee) {
    this.departmentName = departmentNamee;
  }

  /**
   * LoginDtoOut field constructor.
   *
   * @param namee
   *
   * @param userNamee
   *
   * @param passwordd
   *
   * @param userTypee
   *
   * @param finalPasswordd
   *
   * @param departmentNamee
   *
   */
  public LoginDtoOut(final String namee, final String userNamee,
        final String passwordd, final String userTypee,
        final String finalPasswordd, final String departmentNamee) {
    super();
    this.name = namee;
    this.userName = userNamee;
    this.password = passwordd;
    this.userType = userTypee;
    this.finalPassword = finalPasswordd;
    this.departmentName = departmentNamee;
  }

  /**
   * hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentName, finalPassword,
            name, password, userName,
            userType);
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
    LoginDtoOut other = (LoginDtoOut) obj;
    return Objects.equals(departmentName, other.departmentName)
            && Objects.equals(finalPassword, other.finalPassword)
            && Objects.equals(name, other.name)
            && Objects.equals(password, other.password)
            && Objects.equals(userName, other.userName)
            && Objects.equals(userType, other.userType);
  }

  /**
   * LoginDtoOut constructor.
   */
  public LoginDtoOut() {
    super();
  }

  /**
   * toString.
   */
  @Override
  public String toString() {
    return "LoginDtoOut [name=" + name + ", userName=" + userName
            + ", password=" + password + ", userType=" + userType
            + ", finalPassword=" + finalPassword + ", departmentName="
            + departmentName + "]";
  }
}
