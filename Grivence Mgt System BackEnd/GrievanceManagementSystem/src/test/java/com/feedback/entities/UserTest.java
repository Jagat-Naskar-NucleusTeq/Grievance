package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
  @Test
  public void testGettersAndSetters() {
    User user = new User();
    user.setUserId(1);
    user.setName("Jagat Naskaer");
    user.setUserName("johndoe");
    user.setPassword("password");
    user.setUserType(ERole.admin);
    user.setfinalPassword(true);

    assertEquals(1, user.getUserId());
    assertEquals("Jagat Naskaer", user.getName());
    assertEquals("johndoe", user.getUserName());
    assertEquals("password", user.getPassword());
    assertEquals(ERole.admin, user.getUserType());
    assertTrue(user.getfinalPassword());
  }

  @Test
  public void testDepartmentAssociation() {
    Department department = new Department();
    department.setDeptId(1);
    department.setDeptName("IT");

    User user = new User();
    user.setDepartment(department);

    assertEquals(department, user.getDepartment());
  }

  @Test
  public void testTicketListAssociation() {
    // Assuming you have a Ticket class similar to Department and Comment
    // For this example, let's assume Ticket has a property ticketId and getter and setter

    Ticket ticket1 = new Ticket();
    ticket1.setTicketId((long) 1);

    Ticket ticket2 = new Ticket();
    ticket2.setTicketId((long) 2);

    List<Ticket> ticketList = new ArrayList<>();
    ticketList.add(ticket1);
    ticketList.add(ticket2);

    User user = new User();
    user.setTicketList(ticketList);

    assertEquals(ticketList, user.getTicketList());
  }

  @Test
  public void testCommentListAssociation() {
    Comment comment1 = new Comment();
    comment1.setCommentId(1);

    Comment comment2 = new Comment();
    comment2.setCommentId(2);

    List<Comment> commentList = new ArrayList<>();
    commentList.add(comment1);
    commentList.add(comment2);

    User user = new User();
    user.setCommentList(commentList);

    assertEquals(commentList, user.getCommentList());
  }


  @Test
  public void userConstructorTest() {
      // Create a user using the constructor
      int userId = 1;
      String name = "Jagat Naskaer";
      String userName = "jmee@nucleusteq.com";
      String password = "jagat123";
      ERole userType = ERole.admin;
      Boolean finalPassword = true;
      List<Comment> commentList = new ArrayList<>();
      Department department = new Department();

      User user = new User(userId, name, userName, password, userType, finalPassword, null, commentList, department);

      // Check if user is not null
      assertNotNull(user);

      // Check if user properties are set correctly
      assertEquals(userId, user.getUserId());
      assertEquals(name, user.getName());
      assertEquals(userName, user.getUserName());
      assertEquals(password, user.getPassword());
      assertEquals(userType, user.getUserType());
      assertEquals(finalPassword, user.getfinalPassword());
      assertEquals(commentList, user.getCommentList());
      assertEquals(department, user.getDepartment());
  }
}
