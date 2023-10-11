package com.feedback.serviceImplementation;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.custom_exception.UserNotFoundException;
import com.feedback.entities.Department;
import com.feedback.entities.ERole;
import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.repository.DepartmentRepository;
import com.feedback.repository.UserRepository;
import com.feedback.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepositoryy The repository for user operations.
     */
  public UserServiceImpl(final UserRepository userRepositoryy) {
    super();
    this.userRepository = userRepositoryy;
  }

  /**
   *userRepository.
   */
  @Autowired
  private final UserRepository userRepository;

  /**
   * DepartmentRepository object.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * Logger initialization.
   */
  private static final Logger LOGGER = LogManager
        .getLogger(UserServiceImpl.class);

  /**
   * Save a user to the repository.
   *
   * @param user The user to be saved (should be final).
   * @return The saved user.
   */
  @Override
  public User saveUser(final AddUserDto user) {
    User newUser = new User();
    LOGGER.info("ser1 " + newUser);
    newUser.setName(user.getName());
    //  String decodedUserName = new String(Base64.getDecoder()
    //    .decode(user.getUserName()));
    newUser.setUserName(user.getUserName());
    newUser.setPassword(user.getPassword());
    newUser.setfinalPassword(false);
    newUser.setUserType(user.getUserType());
    if (departmentRepository
        .findByDeptName(user.getDepartmentName()) == null) {
      LOGGER.error("Department not found.");
      throw new DepartmentNotFoundException(user.getDepartmentName());
    }
    Department department = departmentRepository
          .findByDeptName(user.getDepartmentName());
    newUser.setDepartment(department);
    LOGGER.info("Saved user successfully.");
    return userRepository.save(newUser);
  }

  /**
   * Check if a user with the provided userName already exists.
   *
   * @param user The user information to be checked.
   * @return True if the user already exists, otherwise false.
   */
  public boolean checkAlreadyExist(final AddUserDto user) {
    User u1 = new User();
    u1.setUserName(user.getUserName());
    if (userRepository.existsByUserName(u1.getUserName())) {
      LOGGER.info("User exist = " + true);
      return true;
    }
    LOGGER.info("User exist = " + false);
    return false;
  }

  /**
   * Get a list of all users.
   *
   * @return A list of all users.
   */
  @Override
  public List<GetAllUsersDtoOut> getAllUsers(final Integer pageNo) {
    final int totalNumberOfContent = 5;
    Pageable pageable = PageRequest.of(pageNo, totalNumberOfContent);
    Page<User> usersPage = userRepository.findAll(pageable);
    List<User> users = usersPage.getContent();
    List<GetAllUsersDtoOut> dtoList = new ArrayList<>();

    for (User user : users) {
      GetAllUsersDtoOut dto = new GetAllUsersDtoOut(
            user.getUserId(),
            user.getName(),
            user.getUserName(),
            user.getUserType().toString(),
            user.getDepartment() != null ? user.getDepartment()
                .getDeptName() : null
        );
      dtoList.add(dto);
    }
    LOGGER.info("Returned list of users, success");
    return dtoList;
  }

  /**
   * Get a user by their ID.
   *
   * @param id The ID of the user to retrieve (should be final).
   * @return The user with the specified ID.
   */
  @Override
  public User getUserById(final Integer id) {
    LOGGER.info("Returned user by id = " + id);
    return userRepository.findById(id).get();
  }

  /**
   * Check if a user with the given userName and password exists.
   *
   * @param userName The userName
   *
   * @param password The password.
   *
   * @return True if a user exists, false otherwise.
   */
  @Override
  public String getByUserAndPassword(
      final String userName, final String password) {
    try {
      if (!userRepository.existsByUserName(userName)) {
        return "false";
      }
      User u1 = userRepository.getUserByUsername(userName);
      //////////////////////////////////////////////////////
      LOGGER.info("Actual emial = " + u1.getUserName());
      String decodedPass = new String(Base64.getDecoder()
          .decode(u1.getPassword()), StandardCharsets.UTF_8); // no need
      LOGGER.info("Actual password = " + decodedPass);
      ////////////////////////////////////////////////////////
      ERole roll = null;
      if (password.equals(u1.getPassword())) {
        roll = u1.getUserType();
        if (!u1.getfinalPassword()) {
          LOGGER.info("Success login, but need to"
                + "change password.");
          return "true_" + roll.name() + "_cp";
        }
        LOGGER.info("Success login");
        return "true_" + roll.name();
      }
    } catch (Exception e) {
      LOGGER.info("Error = " + e.getMessage());
      return "Error : " + e.getMessage();
    }
    LOGGER.info("Login failed.");
    return "false";
  }

  /**
   * Delete a user by their ID.
   *
   * @param userId of the user to delete.
   *
   * @return True if the user was successfully deleted, false otherwise.
   */
  @Override
  public String deleteUser(final Integer userId) {
    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
      LOGGER.info("Deleted successfully.");
      return "Deleted Successfully";
    } else {
      LOGGER.error("Failed to delete");
      throw new UserNotFoundException(userId);
    }
  }

  /**
   * Password change function.
   *
   * @param request
   *
   * @return Success or not not.
   */
  public String passwordChangedSuccess(final PasswordChangeDtoIn request) {
    String userName = new String(Base64.getDecoder()
        .decode(request.getUserName()), StandardCharsets.UTF_8);
    LOGGER.info("User name = " + userName);
    String oldPassword = request.getOldPassword();
    String newPassword = request.getNewPassword();
    String confirmNewPassword = request.getConfirmNewPassword();
    LOGGER.info("Old Password = " + oldPassword);
    LOGGER.info("New Password = " + newPassword);
    LOGGER.info("Confirm change Pass = " + confirmNewPassword);
    if (!userRepository.existsByUserName(userName)) {
      LOGGER.error("User not exist.");
      throw new UserNotFoundException(userName);
    }
    User user = userRepository.getUserByUsername(userName);
    String userPassword = user.getPassword();
    if (!userPassword.equals(oldPassword)) {
      LOGGER.info("Equal old and new Password = "
          + userPassword.equals(oldPassword));
      LOGGER.info("Correct password = " + user.getPassword());
      LOGGER.info("Incorrect old password.");
      return "Incorrect old password.";
    }
    if (!newPassword.equals(confirmNewPassword)) {
      LOGGER.info("New Password and old does not match.");
      return "newPassword does not match with confirm password";
    }
    if (userPassword.equals(oldPassword)
            && newPassword.equals(confirmNewPassword)) {
        user.setPassword(newPassword);
        user.setfinalPassword(true);
        userRepository.save(user);
        if (user.getUserType().equals(ERole.admin)) {
          LOGGER.info("Successfully changed password.");
          return "Password changed successfully";
        } else {
          LOGGER.info("Successfully changed password.");
          return "Password changed successfully.";
        }
    }
    LOGGER.info("Failed to change password.");
    return "Sorry, could not change the password.";
  }

  /**
   * getByUserByUserName.
   *
   * @param userName
   *
   * @return userProfileDTOout
   */
  public UserProfileDtoOut getByUserByUserName(final String userName) {
    UserProfileDtoOut userProfileDtoOut = new UserProfileDtoOut();
    if (userRepository.existsByUserName(userName)) {
      User user = userRepository.getUserByUsername(userName);
      userProfileDtoOut.setName(user.getName());
      userProfileDtoOut.setUserName(user.getUserName());
      userProfileDtoOut.setPassword(user.getPassword());
      userProfileDtoOut.setUserType(user.getUserType().toString());
      userProfileDtoOut.setDepartmentName(user.getDepartment().getDeptName());
      LOGGER.info("Got Profile of user success.");
      return userProfileDtoOut;
    }
    LOGGER.info("Could not found user.");
    return null;
  }
}
