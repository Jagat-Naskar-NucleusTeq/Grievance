package com.feedback.payloads.comment_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.feedback.entities.Ticket;
import com.feedback.entities.User;

class CommentDtoTest {
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
    CommentDto commentDto = new CommentDto(commentId, commentMessage, user, ticket);

    // Check if CommentDTO is not null
    assertNotNull(commentDto);

    // Check if CommentDTO properties are set correctly
    assertEquals(commentId, commentDto.getCommentId());
    assertEquals(commentMessage, commentDto.getCommentMessage());
    assertEquals(user, commentDto.getUser1());
    assertEquals(ticket, commentDto.getTicket());
  }

  @Test
  public void commentDTOGetterSetterTest() {
    // Create a CommentDTO for testing
    CommentDto commentDto = new CommentDto();

    // Set properties using setters
    int commentId = 2;
    String commentMessage = "Another Comment";
    User user = new User();
    Ticket ticket = new Ticket();

    commentDto.setCommentId(commentId);
    commentDto.setCommentMessage(commentMessage);
    commentDto.setUser1(user);
    commentDto.setTicket(ticket);

    // Check if getters return the correct values
    assertEquals(commentId, commentDto.getCommentId());
    assertEquals(commentMessage, commentDto.getCommentMessage());
    assertEquals(user, commentDto.getUser1());
    assertEquals(ticket, commentDto.getTicket());
  }
  

  
  


  @Test
  void testParameterizedConstructor() {
      User user = new User();
      Ticket ticket = new Ticket();

      CommentDto commentDto = new CommentDto(1, "This is a comment", user, ticket);

      assertEquals(1, commentDto.getCommentId());
      assertEquals("This is a comment", commentDto.getCommentMessage());
      assertEquals(user, commentDto.getUser1());
      assertEquals(ticket, commentDto.getTicket());
  }

  @Test
  void testConstructor() {
      User user = new User();
      user.setUserId(1);
      user.setUserName("JohnDoe");

      CommentDto commentDto = new CommentDto(1, "This is a comment", user);

      assertEquals(1, commentDto.getCommentId());
      assertEquals("This is a comment", commentDto.getCommentMessage());
      assertEquals(user, commentDto.getUser1());
  }
  
  @Test
  void testFieldConstructor() {
      User user = new User();
      Ticket ticket = new Ticket();

      CommentDto commentDto = new CommentDto(1, "This is a comment", user, ticket);

      assertEquals(1, commentDto.getCommentId());
      assertEquals("This is a comment", commentDto.getCommentMessage());
      assertEquals(user, commentDto.getUser1());
      assertEquals(ticket, commentDto.getTicket());
  }

}
