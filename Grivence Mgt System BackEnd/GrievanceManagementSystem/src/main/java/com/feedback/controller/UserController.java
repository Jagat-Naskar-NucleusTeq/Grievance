package com.feedback.controller;

import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.LoginDtoIn;
import com.feedback.payloads.user_dto.LoginDtoOut;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.service.UserService;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * User Controller class for managing user-related HTTP end-points.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/users")
public class UserController {

  /**
   *User Service.
   */
  @Autowired
  private UserService userService;

  /**
   * Logger initialization.
   */
  private static final Logger LOGGER = LogManager
        .getLogger(UserController.class);

  /**
   * Add new User by admin.
   *
   * @param user
   *
   * @return string if added or not.
   */
  @PostMapping("/addUser")
  public ResponseEntity<?> addUser(@Valid @RequestBody final AddUserDto user) {
//    if (user == null) {
//      return ResponseEntity
//          .status(HttpStatus.BAD_REQUEST)
//          .body("Sent  User is null.");
//    }
    LOGGER.info("___________Add_user_CONTROLLER_______________");
    String message = "";
    if (userService.checkAlreadyExist(user)) {
      message = "UserName(email) already exist!!!";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    User savedUser = null;
    try {
      savedUser = userService.saveUser(user);
      if (savedUser != null) {
        message = "Saved Successfully!!!";
      }
    } catch (Exception e) {
      LOGGER.info("Error = " + e.getMessage());
      message = "Could not saved into database!!! " + e.getMessage();
    }
    if (savedUser == null) {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    LOGGER.info("User saved.");
    return ResponseEntity.status(HttpStatus.OK).body("User saved!!!");
  }

  /**
   * Change password by user.
   *
   * @param request
   *
   * @return string, whether you can change it or not.
   *
   * @throws Exception.
   *
   */
  @PostMapping("/changePassword")
  public ResponseEntity<String> changePassword(
      @Valid @RequestBody final PasswordChangeDtoIn request) throws Exception {
    LOGGER.info("___________change_password_____________");
//    if (request == null) {
//      return ResponseEntity
//          .status(HttpStatus.BAD_REQUEST)
//          .body("Input not found.");
//    }
    String passwordChanged;
    LOGGER.info("Change Password Conntroller 1");
    try {
      passwordChanged = userService.passwordChangedSuccess(request);
      LOGGER.info("New Password = " + passwordChanged);
      return ResponseEntity.ok(passwordChanged);
    } catch (Exception e) {
      LOGGER.info("Error = " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An error occurred while changing the password.");
    }
  }

  /**
   * login constructor.
   *
   * @param user
   *
   * @return logined or not.
   */
//  @PostMapping("/login")
//  public ResponseEntity<?> login(@Valid
//        @RequestBody final LoginDtoIn user) {
//    LOGGER.info("_________login_Controller__________");
//    String decodedEmail = new String(Base64.getDecoder()
//        .decode(user.getEmail()), StandardCharsets.UTF_8);
//    LOGGER.info("Email got = " + decodedEmail);
//    LOGGER.info("Password got = " + new String(Base64.getDecoder()
//            .decode(user.getPassword()), StandardCharsets.UTF_8));
//    String dataAndRole = (String) userService.getByUserAndPassword(
//          decodedEmail,
//          user.getPassword()
//      );
//    LOGGER.info("Password got success");
//    return ResponseEntity.status(HttpStatus.OK).body(dataAndRole);
//  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid
        @RequestBody final LoginDtoIn user) {
    LOGGER.info("_________login_Controller__________");
    LoginDtoOut loginDtoOut = userService.getByUserAndPassword(user
      );
    LOGGER.info("Password got success");
    return ResponseEntity.status(HttpStatus.OK).body(loginDtoOut);
  }

  /**
   * Get all users to display.
   *
   *@param currentPage
   *
   * @return string.
   */
  @GetMapping("/allUsers/{currentPage}")
  public ResponseEntity<?> getAllUsers(
      @PathVariable final Integer currentPage) {
    LOGGER.info("_____get all users controller____");
    List<GetAllUsersDtoOut> userList = userService.getAllUsers(currentPage);
    return ResponseEntity.status(HttpStatus.OK).body(userList);
  }

  /**
   * Delete a user by its' id.
   *
   * @param id
   *
   * @return string whether deleted or not.
   */
  @DeleteMapping("/deleteUser/{id}")
  public ResponseEntity<?> deleteUserById(@PathVariable final Integer id) {
    LOGGER.info("_________delete user by id_____________");
    String deletedUser = userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
  }

  /**
   * Getting user by its' userName.
   *
   * @param userName
   *
   * @return userName
   */
  @GetMapping("/getByUsrName/{userName}")
  public ResponseEntity<?> getUserByUserName(
      @PathVariable final String userName) {
    LOGGER.info("_______GetUserProfile_controller______");
    UserProfileDtoOut userProfileDtoOut = userService
        .getByUserByUserName(userName);
    if (userProfileDtoOut != null) {
      LOGGER.info("Returned User");
      return ResponseEntity.status(HttpStatus.OK).body(userProfileDtoOut);
    }
    LOGGER.info("User not found");
    return ResponseEntity.status(HttpStatus.OK).body("User not found");
  }
}
