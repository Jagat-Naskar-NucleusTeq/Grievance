package com.feedback.payloads.user_dto;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for user login information.
 */
public class LoginDto {

  /**
   * email variable.
   */
  private String email;

  /**
   * The password associated with the member's account.
   * Password must be between 5 and 12 characters in length.
   */
  private String password;

  /**
   * isFirstLogin variable.
   */
  private Boolean isFirstLogin;

  /**
   * get firstLogin.
   *
   * @return returns boolean value.
   */
  public Boolean getIsFirstLogin() {
    return isFirstLogin;
  }

  /**
   * set first boolean value.
   *
   * @param isFirstLoginn
   *
   */
  public void setIsFirstLogin(final Boolean isFirstLoginn) {
    this.isFirstLogin = isFirstLoginn;
  }

  /**
   * get email.
   *
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * set email.
   *
   * @param emaill
   *
   */
  public void setEmail(final String emaill) {
    this.email = emaill;
  }

  /**
   * get password.
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * set password.
   *
   * @param passwordd
   *
   */
  public void setPassword(final String passwordd) {
    this.password = passwordd;
  }

  /**
   * Override of toString method.
   *
   * @return A string representation of the LoginDto object.
   *
   */
  @Override
  public String toString() {
    return "LoginDto [email=" + email
      + ", password=" + password
      + "]";
  }

  /**
   * Override of hashCode method.
   *
   * @return The generated hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  /**
   * Override of equals method.
   *
   * @param obj The object to compare with
   *
   * @return True if the objects are equal, otherwise False.
   */
  @Override public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LoginDto other = (LoginDto) obj;
    return Objects.equals(email, other.email)
      && Objects.equals(password, other.password);
  }

  /**
   * email minimum length.
   */
  private static  final int MIN_EMAIL_LENGTH = 16;

  /**
   * email max length.
   */
  private static final int MAX_EMAIL_LENGTH = 35;

  /**
   * email max length.
   */
  private static final int MIN_PASSWORD_LENGTH = 8;

  /**
   * Constructor for LoginDto with email and password.
   *
   * @param emaill The user's emaill.
   *
   * @param passwordd The user's password.
   *
   */
  public LoginDto(@Email @Size(min = MIN_EMAIL_LENGTH, max = MAX_EMAIL_LENGTH)
      @Pattern(regexp = "^[A-Za-z0-9_.-]+@nucleusteq\\.com$",
      message = "email format: ...@nucleusteq.com")
      final String emaill,
      @NotEmpty @Size(min = MIN_PASSWORD_LENGTH,
      message = "Password should be greater than 8 char.")
      final String passwordd) {
    super();
    this.email = emaill;
    this.password = passwordd;
  }

  /**
   * Default constructor for LoginDto.
   */
  public LoginDto() {
    super();
  }
}
