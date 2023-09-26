package com.feedback.payloads.comment_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class getCommentDTOoutTest {

 @Test
    public void testGettersAndSetters() {
        getCommentDTOout comment = new getCommentDTOout();
        comment.setCommentedByUser("Jagat Naskar");
        comment.setCommentMessage("This is my first comment");
        comment.setCommentId(1);

        assertEquals("Jagat Naskar", comment.getCommentedByUser());
        assertEquals("This is my first comment", comment.getCommentMessage());
        assertEquals(1, comment.getCommentId());
    }

    @Test
    public void testEqualsAndHashCode() {
        getCommentDTOout comment1 = new getCommentDTOout();
        comment1.setCommentedByUser("Jagat Naskar");
        comment1.setCommentMessage("This is my first comment");
        comment1.setCommentId(1);

        getCommentDTOout comment2 = new getCommentDTOout();
        comment2.setCommentedByUser("Jagat Naskar");
        comment2.setCommentMessage("This is my first comment");
        comment2.setCommentId(1);

        assertTrue(comment1.equals(comment2));
        assertTrue(comment2.equals(comment1));
        assertEquals(comment1.hashCode(), comment2.hashCode());

        comment2.setCommentId(2);

        assertFalse(comment1.equals(comment2));
        assertFalse(comment2.equals(comment1));
        assertNotEquals(comment1.hashCode(), comment2.hashCode());
    }
    
    @Test
     public void testConstructor() {
        getCommentDTOout comment = new getCommentDTOout("Jagat Naskar", "This is my first comment", 1);

        assertEquals("Jagat Naskar", comment.getCommentedByUser());
        assertEquals("This is my first comment", comment.getCommentMessage());
        assertEquals(1, comment.getCommentId());
    }
}
