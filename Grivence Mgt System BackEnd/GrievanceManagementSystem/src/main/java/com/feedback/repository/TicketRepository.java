package com.feedback.repository;

import com.feedback.entities.Department;
import com.feedback.entities.Estatus;
import com.feedback.entities.Ticket;
import com.feedback.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Ticket entities.
 */
@Repository
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
      @Param("user") User user, Pageable pageable);

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
      @Param("department") Department department,
      Pageable pageable);

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
      @Param("department") Department department,
      @Param("status") Estatus status,
      Pageable pageable);

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
  Page<Ticket> findByCreatedByAndTicketStatus(User user,
      Estatus filterStatus, Pageable pageable);

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
      Estatus status, Pageable pageable);
}
