 package com.feedback.serviceImplementation;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.custom_exception.UserNotFoundException;
import com.feedback.entities.Department;
import com.feedback.entities.ERole;
import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.payloads.user_dto.LoginDtoIn;
import com.feedback.payloads.user_dto.LoginDtoOut;
import com.feedback.repository.DepartmentRepository;
import com.feedback.repository.UserRepository;
import com.feedback.service.UserService;

@SpringBootTest
class UserServiceImplTest {

  @Autowired
  @MockBean
  UserRepository userRepository;
  @Autowired
  @MockBean
  DepartmentRepository departmentRepository;
  @Autowired
  UserService userService;

@Test
public void testSaveUser_Success() {
    // Arrange
    AddUserDto addUserDto = new AddUserDto("jagat", "YWRtaW5AbnVjbGV1c3RlcS5jb20=", "cGFzc3dvcmQ=", ERole.admin, "Civil");

    User newUser = new User();
    newUser.setName(addUserDto.getName());
    newUser.setPassword(addUserDto.getPassword());
    newUser.setfinalPassword(false);
    String email = new String(Base64.getDecoder().decode(addUserDto.getUserName()));
    newUser.setUserName(email);
    newUser.setUserType(addUserDto.getUserType());

    Department d1 = new Department();
    d1.setDeptName(addUserDto.getDepartmentName());
    d1.setDeptId(1);
    newUser.setDepartment(d1);

    when(departmentRepository.findByDeptName("Civil")).thenReturn(d1);
    when(userRepository.save(any(User.class))).thenReturn(newUser);

    User savedUser = userService.saveUser(addUserDto);

    verify(userRepository, times(1)).save(any(User.class));
    assertEquals(newUser, savedUser);
}

@Test
public void testSaveUser_Failure() {
    AddUserDto addUserDto = new AddUserDto("jagat", "YWRtaW5AbnVjbGV1c3RlcS5jb20=", "cGFzc3dvcmQ=", ERole.admin, "NonExistentDepartment");

    when(departmentRepository.findByDeptName("NonExistentDepartment")).thenReturn(null);

    Exception exception = assertThrows(DepartmentNotFoundException.class, () -> {
        userService.saveUser(addUserDto);
    });

    assertTrue(exception.getMessage().contains("NonExistentDepartment"));

    verify(userRepository, never()).save(any(User.class));
}
  

@Test
public void testCheckAlreadyExist_UserExists() {
    // Arrange
    AddUserDto addUserDto = new AddUserDto("jagat", "admin@nucleusteq.com", "password", ERole.admin, "Civil");

    User existingUser = new User();
    existingUser.setUserName(addUserDto.getUserName());

    when(userRepository.existsByUserName(addUserDto.getUserName())).thenReturn(true);

    boolean result = userService.checkAlreadyExist(addUserDto);

    assertTrue(result);
}

@Test
void testGetAllUsers() {
    User user1 = new User(1, "Jagat", "jme@nucleusteq.com", "cGFzc3dvcmQ=", ERole.admin, false, null, null, null);
    User user2 = new User(2, "Jagat", "admin@nucleusteq.com", "cGFzc3dvcmQ=", ERole.admin, false, null, null, null);
    List<User> userList = Arrays.asList(user1, user2);

    Page<User> expectedUsers = new PageImpl<>(userList);
    Pageable pageable = PageRequest.of(0, 5);
    
    when(userRepository.findAll(pageable)).thenReturn(expectedUsers);

    List<GetAllUsersDtoOut> expectedDTOList = expectedUsers.stream()
            .map(user -> new GetAllUsersDtoOut(
                    user.getUserId(),
                    user.getName(),
                    user.getUserName(),
                    user.getUserType().toString(),
                    user.getDepartment() != null ? user.getDepartment().getDeptName() : null
            ))
            .collect(Collectors.toList());

    List<GetAllUsersDtoOut> result = userService.getAllUsers(0);

    assertEquals(expectedDTOList, result);
}

@Test
void testGetUserById() {
    int userId = 1;
    User expectedUser = new User(1, "Jagat", "jme@nucleusteq.com", "cGFzc3dvcmQ=", ERole.admin, false, null, null, null);

    when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

    User result = userService.getUserById(userId);

    assertNotNull(result);
    assertEquals(expectedUser, result);
}

@Test
public void testCheckAlreadyExist_UserDoesNotExist() {
    AddUserDto addUserDto = new AddUserDto("jagat", "admin@nucleusteq.com", "password", ERole.admin, "Civil");

    User newUser = new User();
    newUser.setUserName(addUserDto.getUserName());

    when(userRepository.existsByUserName(newUser.getUserName())).thenReturn(false);

    boolean result = userService.checkAlreadyExist(addUserDto);

    assertFalse(result);
}

  @Test
  public void testUserNotFound() {
    LoginDtoIn loginDtoIn = new LoginDtoIn("YWRtaW5AbnVjbGV1c3RlcS5jb20=", "QWRtaW5AMTIz");
    when(userRepository.getUserByUsername("nonExistentUser")).thenReturn(null);
    LoginDtoOut result = userService.getByUserAndPassword(loginDtoIn);
    assertEquals(null, result);
  }

  @Test
  void testDeleteUser_UserExists() {

      Integer userId = 1;

      when(userRepository.existsById(userId)).thenReturn(true);

      String result = userService.deleteUser(userId);

      assertEquals("Deleted Successfully", result);
      verify(userRepository, times(1)).deleteById(userId);
  }

  @Test
  void testDeleteUser_UserDoesNotExist() {
      Integer userId = 1;

      when(userRepository.existsById(userId)).thenReturn(false);

      assertThrows(UserNotFoundException.class, () -> {
          userService.deleteUser(userId);
      });
      verify(userRepository, never()).deleteById(userId);
  }

  @Test
  void testGetByUserByUserName_UserExists() {
      String userName = "jme@nucleusteq.com";
      User user = new User();
      user.setName("Jme Naskar");
      user.setUserName(userName);
      user.setPassword("password123");
      user.setUserType(ERole.admin);

      Department department = new Department();
      department.setDeptName("IT");
      user.setDepartment(department);

      when(userRepository.existsByUserName(userName)).thenReturn(true);
      when(userRepository.getUserByUsername(userName)).thenReturn(user);

      UserProfileDtoOut userProfileDTOout = userService.getByUserByUserName(userName);

      assertNotNull(userProfileDTOout);
      assertEquals("Jme Naskar", userProfileDTOout.getName());
      assertEquals(userName, userProfileDTOout.getUserName());
      assertEquals("password123", userProfileDTOout.getPassword());
      assertEquals("admin", userProfileDTOout.getUserType());
      assertEquals("IT", userProfileDTOout.getDepartmentName());
  }

  @Test
  void testGetByUserByUserName_UserDoesNotExist() {
      String userName = "nonexistentuser";

      when(userRepository.existsByUserName(userName)).thenReturn(false);

      UserProfileDtoOut userProfileDTOout = userService.getByUserByUserName(userName);

      assertNull(userProfileDTOout);
  }

  @Test
  void testPasswordChangedSuccess() {
      User user = new User();
      user.setUserName("am1lQG51Y2xldXN0ZXEuY29t");
      user.setPassword("oldPassword");
      user.setUserType(ERole.admin);

      when(userRepository.existsByUserName("jme@nucleusteq.com")).thenReturn(true);
      when(userRepository.getUserByUsername("jme@nucleusteq.com")).thenReturn(user);
      PasswordChangeDtoIn request = new PasswordChangeDtoIn();
      request.setUserName("am1lQG51Y2xldXN0ZXEuY29t");
      request.setOldPassword("oldPassword");
      request.setNewPassword("newPassword");
      request.setConfirmNewPassword("newPassword");

      String result = userService.passwordChangedSuccess(request);

      assertEquals("Password changed successfully", result);
  }

  @Test
  public void testGetByUserAndPassword_ExceptionOccurs() {
      String encodedEmail = Base64.getEncoder().encodeToString("admin@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("QWRtaW5AMTIz".getBytes(StandardCharsets.UTF_8));

      LoginDtoIn validLogin = new LoginDtoIn(encodedEmail, encodedPassword);

      // Mock userRepository behavior
      when(userRepository.existsByUserName(anyString())).thenReturn(true);
      when(userRepository.getUserByUsername(anyString())).thenThrow(new RuntimeException("Something went wrong."));

      LoginDtoOut result = userService.getByUserAndPassword(validLogin);

      // Assert that the result is null when an exception occurs
      assertNull(result);
  }
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Test
  void testGetByUserAndPassword_ValidCredentials() {
      String encodedEmail = Base64.getEncoder().encodeToString("admin@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("Admin@123".getBytes(StandardCharsets.UTF_8));

      Department department = new Department(1, "HR");
      LoginDtoIn validLogin = new LoginDtoIn(encodedEmail, encodedPassword);
      User user = new User(1, "Jagat", "admin@nucleusteq.com", "QWRtaW5AMTIz", ERole.admin, true, null, null, department);

      when(userRepository.existsByUserName("admin@nucleusteq.com")).thenReturn(true);
      when(userRepository.getUserByUsername("admin@nucleusteq.com")).thenReturn(user);

      LoginDtoOut result = userService.getByUserAndPassword(validLogin);

      assertNotNull(result);
      assertEquals("Jagat", result.getName());
      assertEquals("admin@nucleusteq.com", result.getUserName());
      assertEquals(encodedPassword, result.getPassword());
      assertEquals("admin", result.getUserType());
      assertEquals("true", result.getFinalPassword());
      assertEquals("HR", result.getDepartmentName());
  }

  @Test
  void testGetByUserAndPassword_InvalidUser() {
      String encodedEmail = Base64.getEncoder().encodeToString("jme@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("jme@1234".getBytes(StandardCharsets.UTF_8));

      LoginDtoIn invalidLogin = new LoginDtoIn(encodedEmail, encodedPassword);

      when(userRepository.existsByUserName(anyString())).thenReturn(false);

      LoginDtoOut result = userService.getByUserAndPassword(invalidLogin);

      assertNull(result);
  }

  @Test
  void testGetByUserAndPassword_InvalidPassword() {
      String encodedEmail = Base64.getEncoder().encodeToString("admin@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("InvalidPassword".getBytes(StandardCharsets.UTF_8));

      LoginDtoIn invalidLogin = new LoginDtoIn(encodedEmail, encodedPassword);

      User user = new User();
      user.setUserName("admin@nucleusteq.com");
      user.setPassword(encodedPassword);

      when(userRepository.existsByUserName(anyString())).thenReturn(true);
      when(userRepository.getUserByUsername(anyString())).thenReturn(user);

      LoginDtoOut result = userService.getByUserAndPassword(invalidLogin);

      assertNull(result);
  }

  @Test
  void testGetByUserAndPassword_IncorrectPassword() {
      String encodedEmail = Base64.getEncoder().encodeToString("admin@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("IncorrectPassword".getBytes(StandardCharsets.UTF_8));

      LoginDtoIn invalidLogin = new LoginDtoIn(encodedEmail, encodedPassword);

      User user = new User();
      user.setName("Jagat");
      user.setUserName("admin@nucleusteq.com");
      user.setPassword("QWRtaW5AMTIz"); // Correct password
      user.setUserType(ERole.admin);
      user.setfinalPassword(true);

      when(userRepository.existsByUserName(anyString())).thenReturn(true);
      when(userRepository.getUserByUsername(anyString())).thenReturn(user);

      LoginDtoOut result = userService.getByUserAndPassword(invalidLogin);

      assertNull(result);
  }

  @Test
  void testGetByUserAndPassword_UserDoesNotExist() {
      String encodedEmail = Base64.getEncoder().encodeToString("admin@nucleusteq.com".getBytes(StandardCharsets.UTF_8));
      String encodedPassword = Base64.getEncoder().encodeToString("IncorrectPassword".getBytes(StandardCharsets.UTF_8));

      LoginDtoIn invalidLogin = new LoginDtoIn(encodedEmail, encodedPassword);

      when(userRepository.getUserByUsername("admin@nucleusteq.com")).thenReturn(null);

      LoginDtoOut result = userService.getByUserAndPassword(invalidLogin);

      assertEquals(null, result);
  }
}
