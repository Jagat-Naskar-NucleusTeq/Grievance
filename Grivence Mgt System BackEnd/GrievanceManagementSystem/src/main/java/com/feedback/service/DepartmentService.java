package com.feedback.service;

import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDTO;
import com.feedback.payloads.department_dto.DepartmentListDTO;
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
  public boolean checkAlreadyExist(AddDepartemntDTO dept1);

  /**
   * Add a new department.
   *
   * @param dept The department information to add.
   *
   * @return The added department.
   */
  public Department addDept(AddDepartemntDTO dept);

  /**
   * Get a list of all departments.
   *
   * @return A list of department information.
   */
  List<DepartmentListDTO> getAllDepartments();

  /**
   * Delete a department by its name.
   *
   * @param deptName The name of the department to delete.
   *
   * @return A message indicating the result of the deletion.
   */
  String deleteDept(String deptName);

  /**
   * Get a list of all departments.
   *
   *@param currentPAge
   *
   * @return A list of department information.
   */
  List<DepartmentListDTO> getAllDepartments(Integer currentPage);
}
