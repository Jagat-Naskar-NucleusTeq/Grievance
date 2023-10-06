package com.feedback.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.feedback.mapper.EroleConverter;

/**
 * Represents a user entity in the system.
 * This class defines properties and methods related to users.
 *
 * @version 1.0
 * @author Jagat Naskar
 */
@Entity
@Table(name = "users")
public class User {

  /**
   * The unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;

  /**
   * The name of the user.
   */
  @Column
  private String name;

  /**
   * The userName of the user.
   */
  @Column(unique = true)
  private String userName;

  /**
   * The password of the user.
   */
  @Column
  private String password;

  /**
   * The type of the user (e.g., admin, regular).
   */
  @Column
  @Convert(converter = EroleConverter.class)
  private ERole userType;

  /**
   * The type of the user (e.g., admin, regular).
   */
  @Column
  private Boolean finalPassword;

  /**
   * Maping Users with tickets -> oneToMany.
   */
  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
  private List<Ticket> createdTicket = new ArrayList<>();

  /**
   * Mapping User to Comment (one to many).
  */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  /**
   * Department object for mapping.
   */
  @ManyToOne
  @JoinColumn(name = "deptId")
  private Department department;

  /**
   * Get the user's unique identifier.
   *
   * @return The user's ID.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Set the user's unique identifier.
   *
   * @param userIdd The new user ID.
   */
  public void setUserId(final int userIdd) {
    this.userId = userIdd;
  }

  /**
   * getfinalPassword.
   *
   * @return finalPassword
   */
  public Boolean getfinalPassword() {
    return finalPassword;
  }

  /**
   * setting the finalPassword of the user.
   *
   * @param finalPasswordd
   *
   */
  public void setfinalPassword(final Boolean finalPasswordd) {
    this.finalPassword = finalPasswordd;
  }

  /**
   * Get the user's name.
   *
   * @return The user's name.
   */

  public String getName() {
    return name;
  }

  /**
   * Set the user's name.
   *
   * @param namee The new name.
   */
  public void setName(final String namee) {
    this.name = namee;
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
   * Set the user's userName.
   *
   * @param userNamee The new userName.
   */
  public void setUserName(final String userNamee) {
    this.userName = userNamee;
  }

  /**
   * Get the user's password.
   *
   * @return The password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the user's password.
   *
   * @param passwordd The new password.
   */
  public void setPassword(final String passwordd) {
    this.password = passwordd;
  }

  /**
   * Get the user's type.
   *
   * @return The user's type.
   */
  public ERole getUserType() {
    return userType;
  }

  /**
   * Set the user's type.
   *
   * @param userTypee
   *
   */
  public void setUserType(final ERole userTypee) {
    this.userType = userTypee;
  }

  /**
   * getDepartment method.
   *
   * @return department.
  */
  public Department getDepartment() {
    return department;
  }

  /**
   * set Department.
   *
   * @param departmentt
   *
   */
  public void setDepartment(final Department departmentt) {
    this.department = departmentt;
  }

  /**
   * List of tickets in getTickets method.
   *
   * @return createdTicket
   */
  public List<Ticket> getTicketList() {
    return createdTicket;
  }

  /**
   * set Ticket List.
   *
   * @param ticketList
   *
   */
  public void setTicketList(final List<Ticket> ticketList) {
    this.createdTicket = ticketList;
  }

  /**
   * get Comment.
   *
   * @return comments.
   */
  public List<Comment> getCommentList() {
    return comments;
  }

  /**
   * setting List of Comment.
   *
   * @param commentList
   *
   */
  public void setCommentList(final List<Comment> commentList) {
    this.comments = commentList;
  }

  /**
   * field constructor.
   *
   * @param userIdd
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
   * @param createdTickett
   *
   * @param commentListt
   *
   * @param departmentt
   *
   */
  public User(final int userIdd,
      final String namee,
      final String userNamee,
      final String passwordd,
      final ERole userTypee,
      final Boolean finalPasswordd,
      final List<Ticket> createdTickett,
      final List<Comment> commentListt,
      final Department departmentt) {
    super();
    this.userId = userIdd;
    this.name = namee;
    this.userName = userNamee;
    this.password = passwordd;
    this.userType = userTypee;
    this.finalPassword = finalPasswordd;
    this.createdTicket = createdTickett;
    this.comments = commentListt;
    this.department = departmentt;
  }

  /**
   * No argument constructor.
   */
  public User() {
    super();
  }

  /**
   * To string method.
   */
  @Override
  public String toString() {
    return "User [userId=" + userId
            + ", name=" + name
            + ", userName=" + userName
            + ", password=" + password
            + ", userType=" + userType
            + ", finalPassword=" + finalPassword + "]";
  }
}
