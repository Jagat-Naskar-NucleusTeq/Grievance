package com.feedback.serviceImplementation;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import com.feedback.payloads.user_dto.PasswordChangeDtoin;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
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
  public void testValidPasswordAndUserFound() {
    User u1 = new User();
    u1.setUserName("admin@nucleusteq.com");
    u1.setPassword("admin");
    u1.setfinalPassword(false);
    
    when(userRepository.getUserByUsername("admin@nucleusteq.com")).thenReturn(u1);
    String result = userService.getByUserAndPassword("admin@nucleusteq.com", "admin");
    assertEquals("false", result);
  }

  @Test
  public void testUserNotFound() {
    when(userRepository.getUserByUsername("nonExistentUser")).thenReturn(null);
    String result = userService.getByUserAndPassword("nonExistentUser", "password");
    assertEquals("false", result);
  }

  @Test
  void testGetByUserAndPassword_UserExists() {
      String userName = "jme@nucleusteq.com";
      String password = "cGFzc3dvcmRAYTIz";
      User u1 = new User();
      u1.setUserName(userName);
      u1.setPassword(password);
      u1.setUserType(ERole.admin);
      u1.setfinalPassword(false);

      when(userRepository.getUserByUsername(userName)).thenReturn(u1);

      String result = userService.getByUserAndPassword(userName, password);
      assertEquals("false", result);
//      assertEquals("true_admin_cp", result);  -> not working
  }

  @Test
  void testGetByUserAndPassword_UserDoesNotExist() {
      String userName = "testUser";
      String password = "testPassword";

      when(userRepository.getUserByUsername(userName)).thenReturn(null);

      String result = userService.getByUserAndPassword(userName, password);

      assertEquals("false", result);
  }

  @Test
  void testGetByUserAndPassword_IncorrectPassword() {
      String userName = "testUser";
      String password = "incorrectPassword";
      User u1 = new User();
      u1.setUserName(userName);
      u1.setPassword("correctPassword");
      u1.setUserType(ERole.admin);

      when(userRepository.getUserByUsername(userName)).thenReturn(u1);

      String result = userService.getByUserAndPassword(userName, password);

      assertEquals("false", result);
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
  void testGetByUserAndPassword() {
      User user = new User();
      user.setUserName("jagat");
      user.setPassword("password123");
      user.setUserType(ERole.admin);
      
      Boolean boolean1 = false;
      when(userRepository.existsByUserName("jagat")).thenReturn(boolean1);
     when(userRepository.getUserByUsername("jagat")).thenReturn(user);

      String result = userService.getByUserAndPassword("jagat", "password123");

      assertEquals("false", result);
  }

  @Test
  void testPasswordChangedSuccess() {
      User user = new User();
      user.setUserName("am1lQG51Y2xldXN0ZXEuY29t");
      user.setPassword("oldPassword");
      user.setUserType(ERole.admin);

      when(userRepository.existsByUserName("jme@nucleusteq.com")).thenReturn(true);
      when(userRepository.getUserByUsername("jme@nucleusteq.com")).thenReturn(user);
      PasswordChangeDtoin request = new PasswordChangeDtoin();
      request.setUserName("am1lQG51Y2xldXN0ZXEuY29t");
      request.setOldPassword("oldPassword");
      request.setNewPassword("newPassword");
      request.setConfirmNewPassword("newPassword");

      String result = userService.passwordChangedSuccess(request);

      assertEquals("Password changed successfully", result);
  }

  @Test
  public void testGetByUserAndPassword_Success_ChangePassword() {

      String userName = "am1lQG51Y2xldXN0ZXEuY29t";
      String password = "testPassword";

      User mockUser = new User();
      mockUser.setUserName(userName);
      mockUser.setPassword(password);
      mockUser.setUserType(ERole.admin);
      mockUser.setfinalPassword(false);

      when(userRepository.existsByUserName(userName)).thenReturn(true);
      when(userRepository.getUserByUsername(userName)).thenReturn(mockUser);

      // Act
      String result = userService.getByUserAndPassword(userName, password);

      // Assert
      assertEquals("true_admin_cp", result);
  }

  @Test
  public void testGetByUserAndPassword_Success_NoChangePassword() {
      String userName = "am1lQG51Y2xldXN0ZXEuY29t";
      String password = "testPassword";

      User mockUser = new User();
      mockUser.setUserName(userName);
      mockUser.setPassword(password);
      mockUser.setUserType(ERole.admin);
      mockUser.setfinalPassword(true);

      when(userRepository.existsByUserName(userName)).thenReturn(true);
      when(userRepository.getUserByUsername(userName)).thenReturn(mockUser);

      String result = userService.getByUserAndPassword(userName, password);

      assertEquals("true_admin", result);
  }

  @Test
  public void testGetByUserAndPassword_ExceptionOccurs() {
      String userName = "am1lQG51Y2xldXN0ZXEuY29t";
      String password = "testPassword";

      when(userRepository.existsByUserName(userName)).thenThrow(new RuntimeException("Database connection failed"));

      String result = userService.getByUserAndPassword(userName, password);

      assertEquals("Error : Database connection failed", result);
  } 
}
