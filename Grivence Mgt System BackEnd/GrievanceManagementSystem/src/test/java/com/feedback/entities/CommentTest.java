package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {
  private Comment comment;

  @BeforeEach
  public void setUp() {
    comment = new Comment();
  }

  @Test
  public void testConstructor() {
    int commentId = 1;
    String commentMessage = "My name is Jagat Naskar";
    User user1 = new User();
    user1.setUserId(2);
    user1.setName("Jagat");

    comment = new Comment(commentId, commentMessage, user1);

    assertEquals(commentId, comment.getCommentId());
    assertEquals(commentMessage, comment.getCommentMessage());
    assertEquals(user1, comment.getUser1());
  }

  @Test
  public void testGettersAndSetters() {
    int commentId = 3;
    String commentMessage = "Hey, How are you";
    User user1 = new User();
    user1.setUserId(4);
    user1.setName("Biswas");

    comment.setCommentId(commentId);
    comment.setCommentMessage(commentMessage);
    comment.setUser1(user1);

    assertEquals(commentId, comment.getCommentId());
    assertEquals(commentMessage, comment.getCommentMessage());
    assertEquals(user1, comment.getUser1());
  }

  @Test
  public void setTicketTest() {
    Comment instance = new Comment();

    Ticket ticket = new Ticket();
    ticket.setTicketId(1L);

    instance.setTicket(ticket);

    assertEquals(ticket, instance.getTicket());
  }
  

  @Test
  public void getTicketTest() {
    Comment instance = new Comment();

    Ticket ticket = new Ticket();
    ticket.setTicketId(1L);

    instance.setTicket(ticket);

    Ticket retrievedTicket = instance.getTicket();

    assertEquals(ticket, retrievedTicket);
  }
}
