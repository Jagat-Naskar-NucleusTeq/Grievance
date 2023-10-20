package com.feedback.serviceImplementation;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.feedback.custom_exception.TicketNotFoundException;
import com.feedback.custom_exception.UserNotFoundException;
import com.feedback.entities.Comment;
import com.feedback.entities.Department;
import com.feedback.entities.Estatus;
import com.feedback.entities.Ticket;
import com.feedback.entities.User;
import com.feedback.mapper.EstatusConverter;
import com.feedback.payloads.comment_dto.GetCommentDtoOut;
import com.feedback.payloads.ticket_dto.GetTicketsDtoIn;
import com.feedback.payloads.ticket_dto.TicketDto;
import com.feedback.payloads.ticket_dto.UpdateTicketDtoIn;
import com.feedback.payloads.ticket_dto.GetTicketDtoOut;
import com.feedback.repository.DepartmentRepository;
import com.feedback.repository.TicketRepository;
import com.feedback.repository.UserRepository;
import com.feedback.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TicketServiceImpl class.
 */
@Service
public class TicketServiceImpl implements TicketService {

  /**
   * TicketRepository object.
   */
  @Autowired
  private TicketRepository ticketRepository;

  /**
   * DepartmentRepository object.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * UserRepository object.
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * Logger initialization.
   */
  private static final Logger LOGGER = LogManager
          .getLogger(TicketServiceImpl.class);

  //@Autowired
//  private CommentRepository commentRepository;

  /**
   * saving a new ticket.
   *
   * @param ticket
   *
   * @return saved ticket.
   */
  @Override
  public Ticket saveTicket(final TicketDto ticket) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    Ticket newTicket = new Ticket();
    newTicket.setTicketCreationTime(currentDateTime);
    newTicket.setLastUpdatedTime(currentDateTime);
    newTicket.setTicketTitle(ticket.getTicketTitle());
    newTicket.setTicketType(ticket.getTicketType());
    newTicket.setTicketStatus(ticket.getTicketStatus());
    newTicket.setTicketDescription(ticket.getTicketDescription());
    newTicket.setDepartment(departmentRepository
        .findByDeptName(ticket.getDeptName()));
    byte[] decodedBytes = Base64.getDecoder()
        .decode(ticket.getSenderEmail());
    String decodedEmail = new String(decodedBytes, StandardCharsets.UTF_8);
    if (userRepository.existsByUserName(decodedEmail)) {
      newTicket.setUser(userRepository
          .getUserByUsername(decodedEmail));
      newTicket.setTicketAssignedBy(newTicket.getUser().getName());
    } else {
      LOGGER.error("Ticket not found.");
      throw new UserNotFoundException(ticket.getSenderEmail());
    }
    LOGGER.info("Saved Ticket successful.");
    return ticketRepository.save(newTicket);
  }

  /**
   * getting list of ticket.
   *
   * @param getTicketsDTOin
   *
   * @return list of ticket.
   */
  @Override
  public List<GetTicketDtoOut> getTickets(
      final GetTicketsDtoIn getTicketsDTOin) {
    final int noOfElementInPage = 5;
    boolean isDepartmentBased = false;
    boolean isAssignByOwn = false;
    Estatus filterStatus = null;
    if (getTicketsDTOin.getFilterStatus() != null
            && !getTicketsDTOin.getFilterStatus().isEmpty()
            && !getTicketsDTOin.getFilterStatus().equals("Select status")) {
            filterStatus = EstatusConverter
                .convertStringToEStatus(getTicketsDTOin.getFilterStatus());
        }
    if (getTicketsDTOin.getAssignByOwn().equals("true")) {
      isAssignByOwn = true;
    }
    if (getTicketsDTOin.getDepartmentBased().equals("true")) {
      isDepartmentBased = true;
    }
    String decodedSenderEmail = new String(Base64.getDecoder()
            .decode(getTicketsDTOin.getEmail()), StandardCharsets.UTF_8);
    if (userRepository.existsByUserName(decodedSenderEmail)) {
      User user = userRepository.getUserByUsername(decodedSenderEmail);
      Pageable pageable = PageRequest
            .of(getTicketsDTOin.getPageNumber(), noOfElementInPage);
      List<Ticket> outPutlist = null;
      int departId = user.getDepartment().getDeptId();
      if (isDepartmentBased) {
        Department newDepartment = departmentRepository
            .findById(departId).get();
        Page<Ticket> pageList  = ticketRepository
            .findByDepartment(newDepartment, pageable);
        outPutlist = pageList.getContent();
        if (filterStatus != null) {
          Page<Ticket> pageList1 = ticketRepository
              .findByDepartmentAndStatus(newDepartment, filterStatus, pageable);
          outPutlist = pageList1.getContent();
        }
      } else if (isAssignByOwn) {
        Page<Ticket>  pageList2 = ticketRepository
            .findByUser(user, pageable);
        outPutlist = pageList2.getContent();
        if (filterStatus != null) {
          Page<Ticket> pageList3 = ticketRepository
              .findByCreatedByAndTicketStatus(user, filterStatus, pageable);
          outPutlist = pageList3.getContent();
        }
      } else if (user.getUserType().toString().equals("admin")) {
        Page<Ticket> pageList4 = ticketRepository.findAll(pageable);
        outPutlist = pageList4.getContent();
        if (filterStatus != null) {
          Page<Ticket> pageList5 = ticketRepository
              .findByTicketStatus(filterStatus, pageable);
          outPutlist = pageList5.getContent();
        }
      }
      if (outPutlist == null) {
        LOGGER.error("Ticket not found.");
        throw new TicketNotFoundException("Ticket not found.");
      }
      List<GetTicketDtoOut> outPutList = outPutlist.stream()
              .map(this::convertToDTO)
              .sorted(Comparator.comparing(GetTicketDtoOut::getTicketStatus,
                  Estatus.getStatusComparator()))
              .collect(Collectors.toList());
      LOGGER.info("Returned list of ticket.");
      return outPutList;
    } else {
      LOGGER.error("User not found.");
      throw new UserNotFoundException(decodedSenderEmail);
    }
  }

  /**
   * convertToDTO.
   *
   * @param ticket
   *
   * @return ticket information.
   */
  private GetTicketDtoOut convertToDTO(final Ticket ticket) {
    if (ticket == null) {
      LOGGER.error("User not found from frontend.");
      throw new TicketNotFoundException("No ticket available.");
    }
    GetTicketDtoOut dto = new GetTicketDtoOut();
    dto.setTicketId(ticket.getTicketId());
    dto.setTitle(ticket.getTicketTitle());
    dto.setCreationTime(ticket.getTicketCreationTime());
    dto.setUpdationTime(ticket.getLastUpdatedTime());
    dto.setTicketStatus(ticket.getTicketStatus());
    dto.setTicketType(ticket.getTicketType());
    dto.setCreatedBy(ticket.getTicketAssignedBy());
    dto.setDepartmentName(ticket.getDepartment().getDeptName());
    dto.setComments(null);
    LOGGER.info("Ticket returned successfully.");
    return dto;
  }

  /**
   * updatingTicket.
   */
  @Override
  public Boolean updatingTicket(final UpdateTicketDtoIn updateTicketDtoIn) {

    Estatus newStatus = EstatusConverter.convertStringToEStatus(
        updateTicketDtoIn.getTicketStatus().toString());
    if (!ticketRepository.existsById(updateTicketDtoIn.getTicketId())) {
      LOGGER.error("Ticket not found with id = "
        + updateTicketDtoIn.getTicketId());
      throw new TicketNotFoundException((int) updateTicketDtoIn.getTicketId());
    }
    Optional<Ticket> ticket = ticketRepository
        .findById(updateTicketDtoIn.getTicketId());
    Ticket ticket2 = ticket.get();
    if (newStatus != null) {
      ticket2.setTicketStatus(newStatus);
      if (newStatus.equals(Estatus.Resolved)
          && updateTicketDtoIn.getCommentMessage().equalsIgnoreCase("")) {
         LOGGER.info("To resolve add a"
               + " comment.");
        return false;
      }
    }
    LocalDateTime lastUpdateTime = LocalDateTime.now();
    ticket2.setLastUpdatedTime(lastUpdateTime);
    if (updateTicketDtoIn.getCommentMessage() != null
        && !updateTicketDtoIn.getCommentMessage().equals("")) {
      ticket2.addComment(updateTicketDtoIn.getCommentMessage());
    }
    Comment comment = new Comment();
    comment.setCommentMessage(updateTicketDtoIn.getCommentMessage());
    comment.setTicket(ticket2);
    comment.setUser1(userRepository
          .getUserByUsername(updateTicketDtoIn.getUserName()));
    List<Comment> list = new ArrayList<>();
    list.add(comment);
    ticket2.setComments(list);
    ticketRepository.save(ticket2);
    LOGGER.info("Ticket updated successfully.");
    return true;
  }

  /**
   * Get a ticket by its ID.
   *
   * @param ticketId The ID of the ticket to retrieve.
   * @return The ticket information.
   */
  @Override
  public GetTicketDtoOut getByTicketById(final Long ticketId) {
    if (!ticketRepository.existsById(ticketId)) {
      LOGGER.info("Ticket not found in repo with id= "
        + ticketId);
      return null;
    }
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    GetTicketDtoOut t1 = new GetTicketDtoOut();
    t1.setTicketId(ticketId);
    t1.setTitle(ticket.get().getTicketTitle());
    t1.setCreatedBy(ticket.get().getTicketAssignedBy());
    t1.setCreationTime(ticket.get().getTicketCreationTime());
    t1.setUpdationTime(ticket.get().getLastUpdatedTime());
    t1.setTicketStatus(ticket.get().getTicketStatus());
    t1.setTicketType(ticket.get().getTicketType());
    t1.setCreatedBy(ticket.get().getTicketAssignedBy());
    t1.setDepartmentName(ticket.get().getDepartment().getDeptName());
    t1.setTicketDescription(ticket.get().getTicketDescription());
    List<GetCommentDtoOut> dtoList = convertToDTOList(ticket
        .get()
        .getComments());
    t1.setComments(dtoList);
    LOGGER.info("Returned ticked successfully.");
    return t1;
  }

  /**
   * Convert a list of comments to a list of DTOs.
   *
   * @param commentList The list of comments to convert.
   * @return The list of comment DTOs.
   */
  public List<GetCommentDtoOut> convertToDTOList(
      final List<Comment> commentList) {
    List<GetCommentDtoOut> dtoList = new ArrayList<>();
    for (Comment comment : commentList) {
      GetCommentDtoOut dto = new GetCommentDtoOut();
      dto.setCommentedByUser(comment.getUser1().getName());
      dto.setCommentMessage(comment.getCommentMessage());
      dto.setCommentId(comment.getCommentId());
      dtoList.add(dto);
    }
    return dtoList;
  }
}
