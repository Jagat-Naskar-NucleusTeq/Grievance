package com.feedback.service;

import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
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
   * getByUserAndPassword service.
   *
   * @param userName
   *
   * @param password
   *
   * @return true or false.
   */
  String getByUserAndPassword(String userName, String password);

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
