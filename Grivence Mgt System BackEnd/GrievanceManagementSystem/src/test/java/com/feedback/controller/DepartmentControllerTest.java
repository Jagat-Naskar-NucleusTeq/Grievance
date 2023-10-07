package com.feedback.controller;



//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.feedback.entities.Department;
//import com.feedback.payloads.department_dto.AddDepartemntDto;
//import com.feedback.payloads.department_dto.DepartmentListDto;
//import com.feedback.service.DepartmentService;
//
//class DepartmentControllerTest {
//    @Test
//    void testAddDept() {
//
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        AddDepartemntDto deptDTO = new AddDepartemntDto();
//        deptDTO.setDeptName("HR");
//        Department department = new Department(1, "HR");
//
//        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
//        when(departmentService.addDept(deptDTO)).thenReturn(department);
//
//        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Department HR saved successfully!!!", responseEntity.getBody());
// }
//
//    @Test
//    void testGetAllDepartments() {
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        List<DepartmentListDto> departmentList = new ArrayList<>();
//        departmentList.add(new DepartmentListDto(1, "TestDept"));
//
//        when(departmentService.getAllDepartments()).thenReturn(departmentList);
//
//        ResponseEntity<List<DepartmentListDto>> responseEntity = departmentController.getAllDepartments();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(departmentList, responseEntity.getBody());
//    }
//
//    @Test
//    void testGetAllDepartmentss() {
//
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        List<DepartmentListDto> departmentList = new ArrayList<>();
//        departmentList.add(new DepartmentListDto(1, "TestDept"));
//
//        when(departmentService.getAllDepartments(anyInt())).thenReturn(departmentList);
//
//        ResponseEntity<List<DepartmentListDto>> responseEntity = departmentController.getAllDepartments(1);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(departmentList, responseEntity.getBody());
//    }
//
//    @Test
//    void testDeleteDeptByName() {
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        when(departmentService.deleteDept("TestDept")).thenReturn("Deleted Successfully");
//
//        ResponseEntity<?> responseEntity = departmentController.deleteDeptByName("TestDept");
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Deleted Successfully", responseEntity.getBody());
//    }
//
//    @Test
//    void testAddDeptDepartmentAlreadyExists() {
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        AddDepartemntDto deptDTO = new AddDepartemntDto();
//        deptDTO.setDeptName("TestDept");
//
//        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(true);
//
//        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Department already exists", responseEntity.getBody());
//    }
//
//    @Test
//    void testAddDeptDatabaseError() {
//        DepartmentService departmentService = mock(DepartmentService.class);
//        DepartmentController departmentController = new DepartmentController(departmentService);
//
//        AddDepartemntDto deptDTO = new AddDepartemntDto();
//        deptDTO.setDeptName("TestDept");
//
//        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
//        when(departmentService.addDept(deptDTO)).thenReturn(null);
//
//        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Problem saving in the database: Database save problem", responseEntity.getBody());
//    }
//}



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDto;
import com.feedback.payloads.department_dto.DepartmentListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.service.DepartmentService;

class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @Autowired
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentController = new DepartmentController(departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void testAddDept() throws Exception {
        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("HR");
        Department department = new Department(1, "HR");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
        when(departmentService.addDept(deptDTO)).thenReturn(department);

        mockMvc.perform(post("/api/dept/addDept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(deptDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Department HR saved successfully!!!"));
    }

    @Test
    void testGetAllDepartments() throws Exception {
        List<DepartmentListDto> departmentList = new ArrayList<>();
        departmentList.add(new DepartmentListDto(1, "TestDept"));

        when(departmentService.getAllDepartments()).thenReturn(departmentList);

        mockMvc.perform(get("/api/dept/allDepartment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deptId").value(1))
                .andExpect(jsonPath("$[0].deptName").value("TestDept"));
    }

    @Test
    void testGetAllDepartmentsWithParam() throws Exception {
        int currentPage = 1; 
        List<DepartmentListDto> departmentList = new ArrayList<>();
        departmentList.add(new DepartmentListDto(1, "TestDept"));

        when(departmentService.getAllDepartments(1)).thenReturn(departmentList);

        mockMvc.perform(get("/api/dept/allDepartment/{currentPage}", currentPage)
                .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deptId").value(1))
                .andExpect(jsonPath("$[0].deptName").value("TestDept"));
    }

    @Test
    public void testDeleteDeptByName() throws Exception {
        String deptName = "HR";
        String expectedResult = "Deleted Successfully";

        when(departmentService.deleteDept(deptName)).thenReturn(expectedResult);

        mockMvc.perform(post("/api/dept/deleteDept/{deptName}", deptName))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    void testAddDeptDepartmentAlreadyExists() throws Exception {
        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("TestDept");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(true);

        mockMvc.perform(post("/api/dept/addDept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(deptDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Department already exists"));
    }

    @Test
    void testAddDeptDatabaseError() throws Exception {
        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("TestDept");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
        when(departmentService.addDept(deptDTO)).thenReturn(null);

        mockMvc.perform(post("/api/dept/addDept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(deptDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Problem saving in the database: Database save problem"));
    }

    //  objects to JSON strings
    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}