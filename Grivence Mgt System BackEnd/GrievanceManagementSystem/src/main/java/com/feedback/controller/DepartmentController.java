package com.feedback.controller;

import com.feedback.payloads.department_dto.AddDepartemntDTO;
import com.feedback.payloads.department_dto.DepartmentListDTO;
import com.feedback.service.DepartmentService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DepartmentController.
 *
 * @author jagat
 * @version 001.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

  /**
   * DepartmentService object.
   */
  @Autowired
  private DepartmentService departmentService;

  /**
   * DepartmentController.
   *
   * @param departmentService2
   *
   */
  public DepartmentController(final DepartmentService departmentService2) {
    this.departmentService = departmentService2;
  }

  /**
   * Add department by admin.
   *
   * @param dept1
   *
   * @return ResponseEntity of department list.
   */
  @PostMapping("/addDept")
  public ResponseEntity<?> addDept(
      @Valid @RequestBody final AddDepartemntDTO dept1) {
    try {
      if (departmentService.checkAlreadyExist(dept1)) {
        String message = "Department already exists";
        return ResponseEntity.status(HttpStatus.OK).body(message);
      }
      if (departmentService.addDept(dept1) == null) {
        throw new RuntimeException("Database save problem");
      }
      String message = "Department " + dept1.getDeptName()
          + " saved successfully!!!";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      String errorMessage = "Problem saving in the database: " + e.getMessage();
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(errorMessage);
    }
  }

  /**
   * Get all department.
   *
   * @return list of department.
   */
  @GetMapping("/allDepartment")
  public ResponseEntity<List<DepartmentListDTO>> getAllDepartments() {
    List<DepartmentListDTO> departmentList = departmentService
        .getAllDepartments();
    if (!departmentList.isEmpty()) {
      return ResponseEntity.ok(departmentList);
    } else {
      return ResponseEntity.ok(departmentList);
    }
  }

  /**
   * Get all department.
   *
   *@param currentPage
   *
   * @return list of department.
   */
  @GetMapping("/allDepartment/{currentPage}")
  public ResponseEntity<List<DepartmentListDTO>> GetAllDepartments(
      @PathVariable("currentPage") final Integer currentPage) {
    List<DepartmentListDTO> departmentList = departmentService
        .getAllDepartments(currentPage);
    if (!departmentList.isEmpty()) {
      return ResponseEntity.ok(departmentList);
    } else {
      return ResponseEntity.ok(departmentList);
    }
  }

  /**
   * Delete a department by its name.
   *
   * @param deptName
   *
   * @return deleted Department name.
   */
  @PostMapping("/deleteDept/{deptName}")
  public ResponseEntity<?> deleteDeptByName(@PathVariable final String deptName) {
    System.out.println("_________delete By Dept Name______");
    String deletedDept = departmentService.deleteDept(deptName);
    return ResponseEntity.status(HttpStatus.OK).body(deletedDept);
  }
}
