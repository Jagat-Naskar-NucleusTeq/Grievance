package com.feedback.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Department class.
 *
 * @version 1.0
 * @author Jagat Naskar
 */

@Entity
@Table(name = "Department")
public class Department {
  /**
   * deptId to store the department id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int deptId;

  /**
   * deptName to store the department name.
   */
  @Column
  private String deptName;

  /**
   * Mapping Department to User (one to many).
   */
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<User> users = new ArrayList<>();

  /**
   * Mapping Department to Ticket (one to many).
   */
  @OneToMany(mappedBy = "assignedDepartment", cascade = CascadeType.ALL)
  private List<Ticket> assignedTickets = new ArrayList<>();

  /**
   * get Id of the department.
   *
   * @return deptId of the department.
   */
  public int getDeptId() {
    return deptId;
  }

  /**
   * setting department id.
   *
   * @param deptIdd
   *
   */
  public void setDeptId(final int deptIdd) {
    this.deptId = deptIdd;
  }

  /**
   * get List of users.
   *
   * @return users
   */
  public List<User> getUser() {
    return users;
  }

  /**
   * set UserList.
   *
   * @param userss
   *
   */
  public void setUser(final List<User> userss) {
    this.users = userss;
  }

  /**
   * field constructor of field.
   *
   * @param deptIdd
   *
   * @param deptNamee
   *
   * @param userss
   *
   * @param assignedTicketss
   *
   */
  public Department(
      final int deptIdd,
      final String deptNamee,
      final List<User> userss,
      final List<Ticket> assignedTicketss
  ) {
    super();
    this.deptId = deptIdd;
    this.deptName = deptNamee;
    this.users = userss;
    this.assignedTickets = assignedTicketss;
  }

  /**
   * get TicketList.
   *
   * @return assignedTickets
   */
  public List<Ticket> getTicketList() {
    return assignedTickets;
  }

  /**
   * setTicketList.
   *
   * @param assignedTicketss
   *
   */
  public void setTicketList(final List<Ticket> assignedTicketss) {
    this.assignedTickets = assignedTicketss;
  }

  /**
   * getting name of the department.
   *
   * @return deptName
   */
  public String getDeptName() {
    return deptName;
  }

  /**
   * setting department name.
   *
   * @param deptNamee
   *
   */
  public void setDeptName(final String deptNamee) {
    this.deptName = deptNamee;
  }

  /**
   * Argument constructor.
   *
   * @param deptIdd
   *
   * @param deptNamee
   *
   */
  public Department(final int deptIdd, final String deptNamee) {
    super();
    this.deptId = deptIdd;
    this.deptName = deptNamee;
  }

  /**
   * NoArgsConstructor.
   */
  public Department() {
    super();
  }
}
