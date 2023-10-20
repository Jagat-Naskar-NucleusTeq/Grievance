package com.feedback.service;

import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.payloads.user_dto.LoginDtoIn;
import com.feedback.payloads.user_dto.LoginDtoOut;

import java.util.List;

/**
 * User service interface to declare all the class.
 */
public interface UserService {

  /**
   * saveUser service.
   *
   * @param user
   *
   * @return user to save.
   */
  User saveUser(AddUserDto user);

  /**
   * getAllUsers service.
   *
   * @param pageNo
   *
   * @return list of user.
   */
  List<GetAllUsersDtoOut> getAllUsers(Integer pageNo);

  /**
   * getUserById service.
   *
   * @param id
   *
   * @return user.
   */
  User getUserById(Integer id);

  /**
   * deleteUser service.
   *
   * @param id
   *
   * @return true or false based on the action.
   */
  String deleteUser(Integer id);

  /**
   * getByUserAndPassword.
   *
   * @param user
   *
   * @return LoginDtoOut.
   */
  LoginDtoOut getByUserAndPassword(LoginDtoIn user);

  /**
   * checkAlreadyExist service.
   *
   * @param user
   *
   * @return if the user exist or not by id or by mail.
   */
  boolean checkAlreadyExist(AddUserDto user);

  /**
   * interface of passwordChangedSuccess.
   *
   * @param request
   *
   * @return true or false.
   */
  String passwordChangedSuccess(PasswordChangeDtoIn request);

  /**
   * get user by UserName.
   *
   * @param userName
   *
   * @return UserProfileDTOout.
   */
  UserProfileDtoOut getByUserByUserName(String userName);
}
