package com.feedback.repository;

import com.feedback.entities.Department;
import com.feedback.entities.EStatus;
import com.feedback.entities.Ticket;
import com.feedback.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing Ticket entities.
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  /**
   * findByUser repository.
   *
   * @param user
   *
   * @param pageable
   *
   * @return list of ticket.
   */
  @Query("SELECT t FROM Ticket t WHERE t.createdBy = :user")
  Page<Ticket> findByUser(
      @Param("user") final User user, final Pageable pageable);

  /**
   * findByDepartment repository.
   *
   * @param department
   *
   * @param pageable
   *
   * @return ticket filter by department.
   */
  @Query("SELECT t FROM Ticket t WHERE t.assignedDepartment = :department")
  Page<Ticket> findByDepartment(
      @Param("department") final Department department,
      final Pageable pageable);

  /**
   * findByDepartmentAndStatus repository.
   *
   * @param department
   *
   * @param status
   *
   * @param pageable
   *
   * @return ticket.
   */
  @Query("SELECT t FROM Ticket t WHERE t.assignedDepartment = :"
      + "department AND t.ticketStatus = :status")
  Page<Ticket> findByDepartmentAndStatus(
      @Param("department") final Department department,
      @Param("status") final EStatus status,
      final Pageable pageable);

  /**
   * findByCreatedByAndTicketStatus repository.
   *
   * @param user
   *
   * @param filterStatus
   *
   * @param pageable
   *
   * @return ticket.
   */
  Page<Ticket> findByCreatedByAndTicketStatus(final User user,
      final EStatus filterStatus, final Pageable pageable);

  /**
   * findByTicketStatus repository.
   *
   * @param status
   *
   * @param pageable
   *
   * @return Ticket.
   */
  Page<Ticket> findByTicketStatus(
      final EStatus status, final Pageable pageable);
}
