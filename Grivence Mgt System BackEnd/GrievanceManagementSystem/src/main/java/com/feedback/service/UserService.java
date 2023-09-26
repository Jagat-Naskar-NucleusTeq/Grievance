package com.feedback.service;

import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.PasswordChangeDTOin;
import com.feedback.payloads.user_dto.UserProfileDTOout;
import com.feedback.payloads.user_dto.getAllUsersDTOout;
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
  User saveUser(final AddUserDto user);

  /**
   * getAllUsers service.
   *
   * @param pageNo
   *
   * @return list of user.
   */
  List<getAllUsersDTOout> getAllUsers(final Integer pageNo);

  /**
   * getUserById service.
   *
   * @param id
   *
   * @return user.
   */
  User getUserById(final Integer id);

  /**
   * deleteUser service.
   *
   * @param id
   *
   * @return true or false based on the action.
   */
  String deleteUser(final Integer id);

  /**
   * getByUserAndPassword service.
   *
   * @param userName
   *
   * @param Password
   *
   * @return true or false.
   */
  String getByUserAndPassword(final String userName, final String Password);

  /**
   * checkAlreadyExist service.
   *
   * @param user
   *
   * @return if the user exist or not by id or by mail.
   */
  boolean checkAlreadyExist(final AddUserDto user);

  /**
   * interface of passwordChangedSuccess.
   *
   * @param request
   *
   * @return true or false.
   */
  String passwordChangedSuccess(final PasswordChangeDTOin request);

  /**
   * get user by UserName.
   *
   * @param userName
   *
   * @return UserProfileDTOout.
   */
  UserProfileDTOout getByUserByUserName(final String userName);
}
