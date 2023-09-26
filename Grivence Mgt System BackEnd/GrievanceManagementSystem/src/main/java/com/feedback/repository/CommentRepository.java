package com.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entities.Comment;
import com.feedback.entities.Ticket;

/**
 * Repository interface for managing Comment entities.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

  /**
   * Find all comments associated with a specific ticket.
   *
   * @param ticket The ticket to retrieve comments for.
   *
   * @return A list of comments associated with the given ticket.
   */
  List<Comment> findByTicket(final Ticket ticket);
}
