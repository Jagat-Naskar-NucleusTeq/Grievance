package com.feedback.controller;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feedback.entities.Comment;
import com.feedback.entities.Department;
import com.feedback.entities.ERole;
import com.feedback.entities.EStatus;
import com.feedback.entities.Ticket;
import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.LoginDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoin;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.service.UserService;

public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser_Success() {
        AddUserDto validUser = new AddUserDto("Admin", "admin@nucleusteq.com",
                "Admin@123", ERole.admin, "Sales");
        User user = new User();
        user.setUserId(1);
        user.setName("John Doe");
        user.setUserName("john.doe@example.com");
        user.setPassword("password123");
        user.setUserType(ERole.admin);
        user.setfinalPassword(true);
        Department department = new Department();
        department.setDeptId(1);
        department.setDeptName("IT");
        user.setDepartment(department);
        Ticket ticket1 = new Ticket();
        ticket1.setTicketId(1L);
        ticket1.setTicketTitle("Issue 1");
        ticket1.setTicketStatus(EStatus.Open);
        ticket1.setCreatedBy(user);
        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(2L);
        ticket2.setTicketTitle("Issue 2");
        ticket2.setTicketStatus(EStatus.Being_Addressed);
        ticket2.setCreatedBy(user);
        List<Ticket> ticketList = Arrays.asList(ticket1, ticket2);
        user.setTicketList(ticketList);
        Comment comment1 = new Comment();
        comment1.setCommentId(1);
        comment1.setCommentMessage("Comment 1");
        comment1.setUser1(user);
        Comment comment2 = new Comment();
        comment2.setCommentId(2);
        comment2.setCommentMessage("Comment 2");
        comment2.setUser1(user);
        List<Comment> commentList = Arrays.asList(comment1, comment2);
        user.setCommentList(commentList);
        when(userService.checkAlreadyExist(any())).thenReturn(false);
        when(userService.saveUser(any())).thenReturn(user);
        ResponseEntity<?> response = userController.addUser(validUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User saved!!!", response.getBody());
    }

    @Test
    public void testAddUser_Failure_AlreadyExist() {
        AddUserDto existingUser = new AddUserDto("Admin",
                "admin@nucleusteq.com", "Admin@123", ERole.admin, "Sales");
        when(userService.checkAlreadyExist(any())).thenReturn(true);
        ResponseEntity<?> response = userController.addUser(existingUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("UserName(email) already exist!!!", response.getBody());
    }

    @Test
    public void testAddUser_Failure_InvalidData() {
        AddUserDto invalidUser = new AddUserDto("Virus", "virus@nucleusteq.com",
                "Virus@123", ERole.admin, "Sales");
        ResponseEntity<?> response = userController.addUser(invalidUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody());
    }

    @Test
    public void testChangePassword_Success() throws Exception {
        PasswordChangeDtoin validPasswordChange = new PasswordChangeDtoin(
                "admin@nucleusteq.com", "oldPassword", "newPassword",
                "newPassword");
        when(userService.passwordChangedSuccess(any()))
                .thenReturn("Password changed successfully!");
        ResponseEntity<String> response = userController
                .changePassword(validPasswordChange);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password changed successfully!", response.getBody());
    }

    @Test
    public void testChangePassword_Failure() throws Exception {
        PasswordChangeDtoin invalidPasswordChange = new PasswordChangeDtoin(
                "admin@nucleusteq.com", "oldPassword", "newPassword",
                "confirmPassword");
        when(userService.passwordChangedSuccess(any()))
                .thenAnswer(invocation -> {
                    throw new Exception("Password change failed");
                });
        ResponseEntity<String> response = userController
                .changePassword(invalidPasswordChange);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());
        assertEquals("An error occurred while changing the password.",
                response.getBody());
    }

    @Test
    public void testGetByUserPassword_Success() {
        LoginDto validLogin = new LoginDto("YWRtaW5AbnVjbGV1c3RlcS5jb20=",
                "QWRtaW5AMTIz");// admin@nu..., Admin@123
        when(userService.getByUserAndPassword(any(), any())).thenReturn("Role");
        ResponseEntity<?> response = userController.login(validLogin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Role", response.getBody());
    }

    @Test
    public void testAddUser_Failure_NullUser() {
        AddUserDto nullUser = null;
        ResponseEntity<?> response = userController.addUser(nullUser);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Sent  User is null.", response.getBody());
    }

    @Test
    public void testChangePassword_Failure_NullRequest() throws Exception {
        PasswordChangeDtoin nullRequest = null;
        ResponseEntity<String> response = userController
                .changePassword(nullRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Input not found.", response.getBody());
    }

    @Test
    public void testChangePassword_Failure_PasswordMismatch() throws Exception {
        PasswordChangeDtoin mismatchPasswordChange = new PasswordChangeDtoin(
                "am1lQG51Y2xldXN0ZXEuY29t", "UGFzc3dvcmRAMTIz",
                "UGFzc3dvcmRANTIz", "UGFzc3dvcmRAMTIz");
        when(userService.passwordChangedSuccess(mismatchPasswordChange))
                .thenReturn("Passwords do not match.");
        ResponseEntity<String> response = userController
                .changePassword(mismatchPasswordChange);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Passwords do not match.", response.getBody());
    }

    @Test
    public void testGetByUserPassword_Failure_InvalidCredentials() {
        LoginDto invalidLogin = new LoginDto("am1lQG51Y2xldXN0ZXEuY29t",
                "UGFzc3dvcmRAMTIz");
        when(userService.getByUserAndPassword(any(), any())).thenReturn(null);
        ResponseEntity<?> response = userController.login(invalidLogin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUserById_Success() {
        Integer userId = 1;
        when(userService.deleteUser(any(Integer.class)))
                .thenReturn("User deleted successfully!");
        ResponseEntity<?> response = userController.deleteUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully!", response.getBody());
    }

    @Test
    public void testDeleteUserById_Failure() {
        Integer userId = 2;
        when(userService.deleteUser(any(Integer.class))).thenReturn(null);
        ResponseEntity<?> response = userController.deleteUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllUsers_Success() {
        List<GetAllUsersDtoOut> userList = new ArrayList<>();
        // Add some users to the list
        when(userService.getAllUsers(0)).thenReturn(userList);
        ResponseEntity<?> response = userController.getAllUsers(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    public void testGetAllUsers_EmptyList() {
        List<GetAllUsersDtoOut> emptyList = new ArrayList<>();
        when(userService.getAllUsers(0)).thenReturn(emptyList);
        ResponseEntity<?> response = userController.getAllUsers(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emptyList, response.getBody());
    }

    @Test
    void testGetUserByUserName() {
        UserProfileDtoOut userProfileDTOout = new UserProfileDtoOut("John Doe",
                "johndoe", "password123", "Admin", "IT");
        when(userService.getByUserByUserName("johndoe"))
                .thenReturn(userProfileDTOout);
        ResponseEntity<?> responseEntity = userController
                .getUserByUserName("johndoe");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userProfileDTOout, responseEntity.getBody());
    }

    @Test
    void testGetUserByUserNameNotFound() {

        when(userService.getByUserByUserName("nonexistentuser")).thenReturn(null);

        ResponseEntity<?> responseEntity = userController.getUserByUserName("nonexistentuser");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }
}
