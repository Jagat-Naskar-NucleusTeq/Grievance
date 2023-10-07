package com.feedback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//import static org.junit.Assert.assertNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.feedback.entities.Comment;
//import com.feedback.entities.Department;
//import com.feedback.entities.ERole;
//import com.feedback.entities.Estatus;
//import com.feedback.entities.Ticket;
//import com.feedback.entities.User;
//import com.feedback.payloads.user_dto.AddUserDto;
//import com.feedback.payloads.user_dto.LoginDto;
//import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
//import com.feedback.payloads.user_dto.UserProfileDtoOut;
//import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
//import com.feedback.service.UserService;
//
//public class UserControllerTest {
//    @Mock
//    private UserService userService;
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddUser_Success() {
//        AddUserDto validUser = new AddUserDto("Admin", "admin@nucleusteq.com",
//                "Admin@123", ERole.admin, "Sales");
//        User user = new User();
//        user.setUserId(1);
//        user.setName("Jagat Naskar");
//        user.setUserName("jme@nucleusteq.com");
//        user.setPassword("password123");
//        user.setUserType(ERole.admin);
//        user.setfinalPassword(true);
//        Department department = new Department();
//        department.setDeptId(1);
//        department.setDeptName("IT");
//        user.setDepartment(department);
//        Ticket ticket1 = new Ticket();
//        ticket1.setTicketId(1L);
//        ticket1.setTicketTitle("Issue 1");
//        ticket1.setTicketStatus(Estatus.Open);
//        ticket1.setCreatedBy(user);
//        Ticket ticket2 = new Ticket();
//        ticket2.setTicketId(2L);
//        ticket2.setTicketTitle("Issue 2");
//        ticket2.setTicketStatus(Estatus.Being_Addressed);
//        ticket2.setCreatedBy(user);
//        List<Ticket> ticketList = Arrays.asList(ticket1, ticket2);
//        user.setTicketList(ticketList);
//        Comment comment1 = new Comment();
//        comment1.setCommentId(1);
//        comment1.setCommentMessage("Comment 1");
//        comment1.setUser1(user);
//        Comment comment2 = new Comment();
//        comment2.setCommentId(2);
//        comment2.setCommentMessage("Comment 2");
//        comment2.setUser1(user);
//        List<Comment> commentList = Arrays.asList(comment1, comment2);
//        user.setCommentList(commentList);
//        when(userService.checkAlreadyExist(any())).thenReturn(false);
//        when(userService.saveUser(any())).thenReturn(user);
//        ResponseEntity<?> response = userController.addUser(validUser);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("User saved!!!", response.getBody());
//    }
//
//    @Test
//    public void testAddUser_Failure_AlreadyExist() {
//        AddUserDto existingUser = new AddUserDto("Admin",
//                "admin@nucleusteq.com", "Admin@123", ERole.admin, "Sales");
//        when(userService.checkAlreadyExist(any())).thenReturn(true);
//        ResponseEntity<?> response = userController.addUser(existingUser);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("UserName(email) already exist!!!", response.getBody());
//    }
//
//    @Test
//    public void testAddUser_Failure_InvalidData() {
//        AddUserDto invalidUser = new AddUserDto("Virus", "virus@nucleusteq.com",
//                "Virus@123", ERole.admin, "Sales");
//        ResponseEntity<?> response = userController.addUser(invalidUser);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("", response.getBody());
//    }
//
//    @Test
//    public void testChangePassword_Success() throws Exception {
//        PasswordChangeDtoIn validPasswordChange = new PasswordChangeDtoIn(
//                "admin@nucleusteq.com", "oldPassword", "newPassword",
//                "newPassword");
//        when(userService.passwordChangedSuccess(any()))
//                .thenReturn("Password changed successfully!");
//        ResponseEntity<String> response = userController
//                .changePassword(validPasswordChange);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Password changed successfully!", response.getBody());
//    }
//
//    @Test
//    public void testChangePassword_Failure() throws Exception {
//        PasswordChangeDtoIn invalidPasswordChange = new PasswordChangeDtoIn(
//                "admin@nucleusteq.com", "oldPassword", "newPassword",
//                "confirmPassword");
//        when(userService.passwordChangedSuccess(any()))
//                .thenAnswer(invocation -> {
//                    throw new Exception("Password change failed");
//                });
//        ResponseEntity<String> response = userController
//                .changePassword(invalidPasswordChange);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
//                response.getStatusCode());
//        assertEquals("An error occurred while changing the password.",
//                response.getBody());
//    }
//
//    @Test
//    public void testGetByUserPassword_Success() {
//        LoginDto validLogin = new LoginDto("YWRtaW5AbnVjbGV1c3RlcS5jb20=",
//                "QWRtaW5AMTIz");// admin@nu..., Admin@123
//        when(userService.getByUserAndPassword(any(), any())).thenReturn("Role");
//        ResponseEntity<?> response = userController.login(validLogin);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Role", response.getBody());
//    }
//
//    @Test
//    public void testAddUser_Failure_NullUser() {
//        AddUserDto nullUser = null;
//        ResponseEntity<?> response = userController.addUser(nullUser);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Sent  User is null.", response.getBody());
//    }
//
//    @Test
//    public void testChangePassword_Failure_NullRequest() throws Exception {
//        PasswordChangeDtoIn nullRequest = null;
//        ResponseEntity<String> response = userController
//                .changePassword(nullRequest);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Input not found.", response.getBody());
//    }
//
//    @Test
//    public void testChangePassword_Failure_PasswordMismatch() throws Exception {
//        PasswordChangeDtoIn mismatchPasswordChange = new PasswordChangeDtoIn(
//                "am1lQG51Y2xldXN0ZXEuY29t", "UGFzc3dvcmRAMTIz",
//                "UGFzc3dvcmRANTIz", "UGFzc3dvcmRAMTIz");
//        when(userService.passwordChangedSuccess(mismatchPasswordChange))
//                .thenReturn("Passwords do not match.");
//        ResponseEntity<String> response = userController
//                .changePassword(mismatchPasswordChange);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Passwords do not match.", response.getBody());
//    }
//
//    @Test
//    public void testGetByUserPassword_Failure_InvalidCredentials() {
//        LoginDto invalidLogin = new LoginDto("am1lQG51Y2xldXN0ZXEuY29t",
//                "UGFzc3dvcmRAMTIz");
//        when(userService.getByUserAndPassword(any(), any())).thenReturn(null);
//        ResponseEntity<?> response = userController.login(invalidLogin);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    public void testDeleteUserById_Success() {
//        Integer userId = 1;
//        when(userService.deleteUser(any(Integer.class)))
//                .thenReturn("User deleted successfully!");
//        ResponseEntity<?> response = userController.deleteUserById(userId);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("User deleted successfully!", response.getBody());
//    }
//
//    @Test
//    public void testDeleteUserById_Failure() {
//        Integer userId = 2;
//        when(userService.deleteUser(any(Integer.class))).thenReturn(null);
//        ResponseEntity<?> response = userController.deleteUserById(userId);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsers_Success() {
//        List<GetAllUsersDtoOut> userList = new ArrayList<>();
//        // Add some users to the list
//        when(userService.getAllUsers(0)).thenReturn(userList);
//        ResponseEntity<?> response = userController.getAllUsers(0);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userList, response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsers_EmptyList() {
//        List<GetAllUsersDtoOut> emptyList = new ArrayList<>();
//        when(userService.getAllUsers(0)).thenReturn(emptyList);
//        ResponseEntity<?> response = userController.getAllUsers(0);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(emptyList, response.getBody());
//    }
//
//    @Test
//    void testGetUserByUserName() {
//        UserProfileDtoOut userProfileDTOout = new UserProfileDtoOut("Jagat Naskar",
//                "jme@nucleusteq.com", "password123", "Admin", "IT");
//        when(userService.getByUserByUserName("jme@nucleusteq.com"))
//                .thenReturn(userProfileDTOout);
//        ResponseEntity<?> responseEntity = userController
//                .getUserByUserName("jme@nucleusteq.com");
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(userProfileDTOout, responseEntity.getBody());
//    }
//
//    @Test
//    void testGetUserByUserNameNotFound() {
//
//        when(userService.getByUserByUserName("nonexistentuser")).thenReturn(null);
//        
//        ResponseEntity<?> responseEntity = userController.getUserByUserName("nonexistentuser");
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("User not found", responseEntity.getBody());
//    }
//}







import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.entities.ERole;
import com.feedback.entities.User;
import com.feedback.payloads.user_dto.AddUserDto;
import com.feedback.payloads.user_dto.GetAllUsersDtoOut;
import com.feedback.payloads.user_dto.LoginDto;
import com.feedback.payloads.user_dto.PasswordChangeDtoIn;
import com.feedback.payloads.user_dto.UserProfileDtoOut;
import com.feedback.service.UserService;

import static org.mockito.Mockito.*;


public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testAddUser_Success() throws Exception {

        AddUserDto validUser = new AddUserDto("Admin", "admin@nucleusteq.com",
                "Admin@123", ERole.admin, "Sales");

        User user = new User();
        when(userService.checkAlreadyExist(any())).thenReturn(false);
        when(userService.saveUser(any())).thenReturn(user);

        mockMvc.perform(post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Admin\",\"userName\":\"admin@nucleusteq.com\",\"password\":\"Admin@123\",\"userType\":\"admin\",\"departmentName\":\"Sales\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User saved!!!"));
    }

    @Test
    public void testAddUser_Failure_AlreadyExist() throws Exception {
        AddUserDto existingUser = new AddUserDto("Admin",
                "admin@nucleusteq.com", "Admin@123", ERole.admin, "Sales");
        when(userService.checkAlreadyExist(any())).thenReturn(true);

        mockMvc.perform(post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Admin\",\"userName\":\"admin@nucleusteq.com\",\"password\":\"Admin@123\",\"userType\":\"admin\",\"departmentName\":\"Sales\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("UserName(email) already exist!!!"));
    }

    @Test
    public void testAddUser_Failure_InvalidData() throws Exception {
        AddUserDto invalidUser = new AddUserDto("Virus", "virus@nucleusteq.com",
                "Virus@123", ERole.admin, "Sales");

        mockMvc.perform(post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Virus\",\"userName\":\"virus@nucleusteq.com\",\"password\":\"Virus@123\",\"userType\":\"admin\",\"departmentName\":\"Sales\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testChangePassword_Success() throws Exception {
        PasswordChangeDtoIn validPasswordChange = new PasswordChangeDtoIn(
                "admin@nucleusteq.com", "oldPassword", "newPassword",
                "newPassword");
        when(userService.passwordChangedSuccess(any()))
                .thenReturn("Password changed successfully!");

        mockMvc.perform(post("/api/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@nucleusteq.com\",\"oldPassword\":\"oldPassword\",\"newPassword\":\"newPassword\",\"confirmPassword\":\"newPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Password changed successfully!"));
    }
    

    @Test
    public void testChangePassword_Failure() throws Exception {
        PasswordChangeDtoIn invalidPasswordChange = new PasswordChangeDtoIn(
                "admin@nucleusteq.com", "oldPassword", "newPassword",
                "confirmPassword");

        when(userService.passwordChangedSuccess(any()))
                .thenAnswer(invocation -> {
                    throw new Exception("Password change failed");
                });

        mockMvc.perform(post("/api/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(invalidPasswordChange)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred while changing the password."));
    }

    @Test
    public void testGetByUserPassword_Success() throws Exception {
        LoginDto validLogin = new LoginDto("YWRtaW5AbnVjbGV1c3RlcS5jb20=",
                "QWRtaW5AMTIz");

        when(userService.getByUserAndPassword(any(), any())).thenReturn("Role");

        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"YWRtaW5AbnVjbGV1c3RlcS5jb20=\",\"password\":\"QWRtaW5AMTIz\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Role"));
    }

    @Test
    public void testGetByUserPassword_Failure_InvalidCredentials() throws Exception {
        LoginDto invalidLogin = new LoginDto("YWRtaW5AbnVjbGV1c3RlcS5jb20=",
                "QWRtaW5AMTIz");

        when(userService.getByUserAndPassword(any(), any())).thenReturn(null);

        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"YWRtaW5AbnVjbGV1c3RlcS5jb20=\",\"password\":\"QWRtaW5AMTIz\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testAddUser_Failure_NullUser() throws Exception {
        mockMvc.perform(post("/api/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("null"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    public void testChangePassword_Failure_NullRequest() throws Exception {
        mockMvc.perform(post("/api/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content("null"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    public void testChangePassword_Failure_PasswordMismatch() throws Exception {
        PasswordChangeDtoIn mismatchPasswordChange = new PasswordChangeDtoIn(
                "am1lQG51Y2xldXN0ZXEuY29t", "UGFzc3dvcmRAMTIz",
                "UGFzc3dvcmRANTIz", "UGFzc3dvcmRAMTIz");

        when(userService.passwordChangedSuccess(mismatchPasswordChange))
                .thenReturn("Passwords do not match.");

        mockMvc.perform(post("/api/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mismatchPasswordChange)))
                .andExpect(status().isOk())
                .andExpect(content().string("Passwords do not match."));
    }

    @Test
    public void testDeleteUserById_Success() throws Exception {
        Integer userId = 1;

        when(userService.deleteUser(any(Integer.class)))
                .thenReturn("User deleted successfully!");

        mockMvc.perform(delete("/api/users/deleteUser/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully!"));
    }

    @Test
    public void testDeleteUserById_Failure() throws Exception {
        Integer userId = 2;

        when(userService.deleteUser(any(Integer.class))).thenReturn(null);

        mockMvc.perform(delete("/api/users/deleteUser/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetAllUsers_Success() throws Exception {
       int currentPage = 0; 
        List<GetAllUsersDtoOut> userList = new ArrayList<>();
        when(userService.getAllUsers(0)).thenReturn(userList);

        mockMvc.perform(get("/api/users/allUsers/{currentPage}", currentPage))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userList)));
    }

    @Test
    public void testGetAllUsers_EmptyList() throws Exception {
        int currentPage = 1;
        List<GetAllUsersDtoOut> emptyList = new ArrayList<>();
        when(userService.getAllUsers(0)).thenReturn(emptyList);

        mockMvc.perform(get("/api/users/allUsers/{currentPage}", currentPage))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(emptyList)));
    }

    @Test
    public void testGetUserByUserName() throws Exception {
        UserProfileDtoOut userProfileDTOout = new UserProfileDtoOut("John Doe",
                "johndoe", "password123", "Admin", "IT");
        when(userService.getByUserByUserName("johndoe"))
                .thenReturn(userProfileDTOout);

        mockMvc.perform(get("/api/users/getByUsrName/{userName}", "johndoe"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(userProfileDTOout)));
    }

    @Test
    public void testGetUserByUserNameNotFound() throws Exception {
        when(userService.getByUserByUserName("nonexistentuser")).thenReturn(null);

        mockMvc.perform(get("/api/users/getByUsrName/{userName}", "nonexistentuser"))
                .andExpect(status().isOk())
                .andExpect(content().string("User not found"));
    }

    //converting objects to JSON strings
    private String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}