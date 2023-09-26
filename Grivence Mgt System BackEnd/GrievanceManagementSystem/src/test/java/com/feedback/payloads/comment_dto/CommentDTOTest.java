package com.feedback.payloads.comment_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.feedback.entities.Ticket;
import com.feedback.entities.User;

class CommentDTOTest {
  @Test
  public void commentDTOConstructorTest() {
    // Create User and Ticket for testing
    User user = new User();
    user.setUserId(1);
    user.setName("jagat");
    user.setUserName("naskar@nucleusteq.com");
    user.setPassword("password123");

    Ticket ticket = new Ticket();
    ticket.setTicketId(1L);
    ticket.setTicketTitle("Sample Ticket");

    // Create a CommentDTO using the constructor
    int commentId = 1;
    String commentMessage = "Sample Comment";
    CommentDTO commentDTO = new CommentDTO(commentId, commentMessage, user, ticket);

    // Check if CommentDTO is not null
    assertNotNull(commentDTO);

    // Check if CommentDTO properties are set correctly
    assertEquals(commentId, commentDTO.getCommentId());
    assertEquals(commentMessage, commentDTO.getCommentMessage());
    assertEquals(user, commentDTO.getUser1());
    assertEquals(ticket, commentDTO.getTicket());
  }

  @Test
  public void commentDTOGetterSetterTest() {
    // Create a CommentDTO for testing
    CommentDTO commentDTO = new CommentDTO();

    // Set properties using setters
    int commentId = 2;
    String commentMessage = "Another Comment";
    User user = new User();
    Ticket ticket = new Ticket();

    commentDTO.setCommentId(commentId);
    commentDTO.setCommentMessage(commentMessage);
    commentDTO.setUser1(user);
    commentDTO.setTicket(ticket);

    // Check if getters return the correct values
    assertEquals(commentId, commentDTO.getCommentId());
    assertEquals(commentMessage, commentDTO.getCommentMessage());
    assertEquals(user, commentDTO.getUser1());
    assertEquals(ticket, commentDTO.getTicket());
  }
  

  
  


  @Test
  void testParameterizedConstructor() {
      User user = new User();
      Ticket ticket = new Ticket();

      CommentDTO commentDTO = new CommentDTO(1, "This is a comment", user, ticket);

      assertEquals(1, commentDTO.getCommentId());
      assertEquals("This is a comment", commentDTO.getCommentMessage());
      assertEquals(user, commentDTO.getUser1());
      assertEquals(ticket, commentDTO.getTicket());
  }

  @Test
  void testConstructor() {
      User user = new User();
      user.setUserId(1);
      user.setUserName("JohnDoe");

      CommentDTO commentDTO = new CommentDTO(1, "This is a comment", user);

      assertEquals(1, commentDTO.getCommentId());
      assertEquals("This is a comment", commentDTO.getCommentMessage());
      assertEquals(user, commentDTO.getUser1());
  }
  
  @Test
  void testFieldConstructor() {
      User user = new User();
      Ticket ticket = new Ticket();

      CommentDTO commentDTO = new CommentDTO(1, "This is a comment", user, ticket);

      assertEquals(1, commentDTO.getCommentId());
      assertEquals("This is a comment", commentDTO.getCommentMessage());
      assertEquals(user, commentDTO.getUser1());
      assertEquals(ticket, commentDTO.getTicket());
  }

}
