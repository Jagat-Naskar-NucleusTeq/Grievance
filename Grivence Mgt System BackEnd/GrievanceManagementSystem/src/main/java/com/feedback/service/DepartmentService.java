package com.feedback.service;

import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDto;
import com.feedback.payloads.department_dto.DepartmentListDto;
import java.util.List;

/**
 * Service interface for managing departments.
 */
public interface DepartmentService {

  /**
   * Check if a department with the given name already exists.
   *
   * @param dept1 The department information to check.
   *
   * @return True if a department with the name already exists.
   */
  boolean checkAlreadyExist(AddDepartemntDto dept1);

  /**
   * Add a new department.
   *
   * @param dept The department information to add.
   *
   * @return The added department.
   */
  Department addDept(AddDepartemntDto dept);

  /**
   * Get a list of all departments.
   *
   * @return A list of department information.
   */
  List<DepartmentListDto> getAllDepartments();

  /**
   * Get a list of all departments.
   *
   *@param currentPage
   *
   * @return A list of department information.
   */
  List<DepartmentListDto> getAllDepartments(Integer currentPage);

  /**
   * Delete a department by its name.
   *
   * @param deptName The name of the department to delete.
   *
   * @return A message indicating the result of the deletion.
   */
  String deleteDept(String deptName);
}
