package com.feedback.payloads.user_dto;

import java.util.Objects;

/**
 * Data Transfer Object for password change information.
 */
public class PasswordChangeDTOin {

  /**
   * userName.
   */
  private String userName;

  /**
   * oldPassword.
   */
  private String oldPassword;

  /**
   * newPassword.
   */
  private String newPassword;

  /**
   * confirmNewPassword.
   */
  private String confirmNewPassword;

  /**
   * Constructor for PasswordChangeDTOin with all fields.
   *
   * @param userNamee The user's userName.
   *
   * @param oldPasswordd The user's old password.
   *
   * @param newPasswordd The user's new password.
   *
   * @param confirmNewPasswordd of the new password.
   *
   */
  public PasswordChangeDTOin(final String userNamee, 
          final String oldPasswordd,
          final String newPasswordd,
          final String confirmNewPasswordd) {
    super();
    this.userName = userNamee;
    this.oldPassword = oldPasswordd;
    this.newPassword = newPasswordd;
    this.confirmNewPassword = confirmNewPasswordd;
  }

  /**
   * Default constructor for PasswordChangeDTOin.
   */
  public PasswordChangeDTOin() {
    super();
  }

  /**
   * Get the user's userName.
   *
   * @return The userName.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Set the user's userNamee.
   *
   * @param userNamee The userNamee.
   *
   */
  public void setUserName(final String userNamee) {
    this.userName = userNamee;
  }

  /**
   * Get the user's old password.
   *
   * @return The old password.
   */
  public String getOldPassword() {
    return oldPassword;
  }

  /**
   * Set the user's old password.
   *
   * @param oldPasswordd The old password.
   *
   */
  public void setOldPassword(final String oldPasswordd) {
    this.oldPassword = oldPasswordd;
  }

  /**
   * Get the user's new password.
   *
   * @return The new password.
   */
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * Set the user's new password.
   *
   * @param newPasswordd The new password.
   *
   */
  public void setNewPassword(final String newPasswordd) {
    this.newPassword = newPasswordd;
  }

  /**
   * Get the confirmation of the new password.
   *
   * @return The confirmation of the new password.
   */
  public String getConfirmNewPassword() {
    return confirmNewPassword;
  }

  /**
   * Set the confirmation of the new password.
   *
   * @param confirmNewPasswordd
   *
   */
  public void setConfirmNewPassword(final String confirmNewPasswordd) {
    this.confirmNewPassword = confirmNewPasswordd;
  }

  /**
   * Override of toString method.
   *
   * @return A string representation of PasswordChangeDTOin object.
   */
  @Override
  public String toString() {
    return "PasswordChangeDTOin [oldPassword=" + oldPassword
        + ", newPassword=" + newPassword
        + ", confirmNewPassword=" + confirmNewPassword + "]";
  }

  /**
   * Override of hashCode method to generate a hash code for the object.
   *
   * @return The generated hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(confirmNewPassword, newPassword, oldPassword, userName);
  }

  /**
   * Override of equals method.
   *
   * @param obj The object to compare with.
   *
   * @return True if the objects are equal, otherwise False.
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
    PasswordChangeDTOin other = (PasswordChangeDTOin) obj;
    return Objects.equals(confirmNewPassword, other.confirmNewPassword)
            && Objects.equals(newPassword, other.newPassword)
            && Objects.equals(oldPassword, other.oldPassword)
            && Objects.equals(userName, other.userName);
  }
}
