package com.feedback.controller;

import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.LoginDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoin;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.service.UserService;
import java.util.Base64;
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

/**
 * User Controller class for managing user-related HTTP endpoints.
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
   * Add new User by admin.
   *
   * @param user
   *
   * @return string if added or not.
   */
  @PostMapping("/addUser")
  public ResponseEntity<?> addUser(@Valid @RequestBody final AddUserDto user) {
    if (user == null) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Sent  User is null.");
    }
    System.out.println("_______________Add_user______________________");
    System.out.println("Controller, userDTo = " + user);
    String message = "";
    if (user.getDepartmentName().equals(null) || user.getName().equals(null)
        || user.getPassword().equals(null) || user.getUserName().equals(null)
        || user.getUserType().equals(null)) {
      message = "All the data are not filled.";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    if ((userService.checkAlreadyExist(user))) {
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
      message = "Could not saved into database!!! " + e.getMessage();
    }
    if (savedUser == null) {
      return ResponseEntity.status(HttpStatus.OK).body(message);
    }
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
      @RequestBody final PasswordChangeDtoin request) throws Exception {
    System.out.println("___________change_password__________________________");
    if (request == null) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Input not found.");
    }
    String passwordChanged;
    System.out.println("Change Password Conntroller 1");
    try {
      passwordChanged = userService.passwordChangedSuccess(request);
      System.out.println("success sending frontend => " + passwordChanged);
      return ResponseEntity.ok(passwordChanged);
    } catch (Exception e) {
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
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody final LoginDto user) {
    System.out.println("______________login______________________");
    String decodedEmail = new String(Base64.getDecoder()
        .decode(user.getEmail()));
    System.out.println("Email got = " + decodedEmail);
    String decodedPass = new String(Base64.getDecoder()
        .decode(user.getPassword()));
    System.out.println("Password got = " + decodedPass);
    String dataAndRole = (String) userService.getByUserAndPassword(
          decodedEmail,
          user.getPassword()
      );
    return ResponseEntity.status(HttpStatus.OK).body(dataAndRole);
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
    System.out.println("get all users controller 1");
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
    System.out.println("__________________delete user by id__________________");
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
    System.out.println("GetUserProfile_________________controller");
    UserProfileDtoOut userProfileDtoOut = userService
        .getByUserByUserName(userName);
    if (userProfileDtoOut != null) {
      return ResponseEntity.status(HttpStatus.OK).body(userProfileDtoOut);
    }
    return ResponseEntity.status(HttpStatus.OK).body("User not found");
  }
}
