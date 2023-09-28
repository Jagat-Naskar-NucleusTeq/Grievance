package com.feedback.repository;


import com.feedback.entities.Department;



import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Department entities.
 */
public interface DepartmentRepository extends
    JpaRepository<Department, Integer> {

  /**
  * Find a department by its name.
  *
  * @param deptName The name of the department to retrieve.
  *
  * @return The department with the specified name.
  */
  Department findByDeptName(String deptName);
}
