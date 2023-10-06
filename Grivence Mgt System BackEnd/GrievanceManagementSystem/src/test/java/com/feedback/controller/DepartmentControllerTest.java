package com.feedback.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDto;
import com.feedback.payloads.department_dto.DepartmentListDto;
import com.feedback.service.DepartmentService;

class DepartmentControllerTest {
    @Test
    void testAddDept() {

        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("HR");
        Department department = new Department(1, "HR");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
        when(departmentService.addDept(deptDTO)).thenReturn(department);

        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Department HR saved successfully!!!", responseEntity.getBody());
 }

    @Test
    void testGetAllDepartments() {
        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        List<DepartmentListDto> departmentList = new ArrayList<>();
        departmentList.add(new DepartmentListDto(1, "TestDept"));

        when(departmentService.getAllDepartments()).thenReturn(departmentList);

        ResponseEntity<List<DepartmentListDto>> responseEntity = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(departmentList, responseEntity.getBody());
    }

    @Test
    void testGetAllDepartmentss() {

        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        List<DepartmentListDto> departmentList = new ArrayList<>();
        departmentList.add(new DepartmentListDto(1, "TestDept"));

        when(departmentService.getAllDepartments(anyInt())).thenReturn(departmentList);

        ResponseEntity<List<DepartmentListDto>> responseEntity = departmentController.getAllDepartments(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(departmentList, responseEntity.getBody());
    }

    @Test
    void testDeleteDeptByName() {
        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        when(departmentService.deleteDept("TestDept")).thenReturn("Deleted Successfully");

        ResponseEntity<?> responseEntity = departmentController.deleteDeptByName("TestDept");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted Successfully", responseEntity.getBody());
    }

    @Test
    void testAddDeptDepartmentAlreadyExists() {
        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("TestDept");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(true);

        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Department already exists", responseEntity.getBody());
    }

    @Test
    void testAddDeptDatabaseError() {
        DepartmentService departmentService = mock(DepartmentService.class);
        DepartmentController departmentController = new DepartmentController(departmentService);

        AddDepartemntDto deptDTO = new AddDepartemntDto();
        deptDTO.setDeptName("TestDept");

        when(departmentService.checkAlreadyExist(deptDTO)).thenReturn(false);
        when(departmentService.addDept(deptDTO)).thenReturn(null);

        ResponseEntity<?> responseEntity = departmentController.addDept(deptDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Problem saving in the database: Database save problem", responseEntity.getBody());
    }
}