/**
 * Implementation of the DepartmentService interface for managing departments.
 */
package com.feedback.serviceImplementation;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDTO;
import com.feedback.payloads.department_dto.DepartmentListDto;
import com.feedback.repository.DepartmentRepository;
import com.feedback.service.DepartmentService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * DepartmentServiceImpl class.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

  /**
   * DepartmentRepository object.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * Check if a department with the given name already exists.
   *
   * @param dept1 The department information to check.
   *
   * @return True if department already exists.
   */
  public boolean checkAlreadyExist(final AddDepartemntDTO dept1) {
    Department d1 = new Department();
    d1.setDeptName(dept1.getDeptName());
    System.out.println("Already " + departmentRepository
        .findByDeptName(d1.getDeptName()));
    if (departmentRepository
        .findByDeptName(d1.getDeptName()) != null) {
      return true;
    }
    return false;
  }

  /**
   * Add a new Department to the database.
   *
   * @param dept The department information to add.
   *
   * @return The added department.
   */
  public Department addDept(final AddDepartemntDTO dept) {
    Department d1 = new Department();
    d1.setDeptName(dept.getDeptName());
    return departmentRepository.save(d1);
  }

  /**
   * Get a list of all departments.
   *
   * @return A list of department information.
   */
  @Override
  public List<DepartmentListDto> getAllDepartments() {
    System.out.println("getting All Department using Effect");
    List<Department> departments = departmentRepository.findAll();
    return departments.stream()
        .map(department -> {
          DepartmentListDto deptDto = new DepartmentListDto();
          deptDto.setDeptId(department.getDeptId());
          deptDto.setDeptName(department.getDeptName());
          return deptDto;
        })
          .collect(Collectors.toList());
  }

  /**
   * Delete a department by its name.
   *
   * @param deptName to delete.
   *
   * @return A message indicating the result of the deletion.
   */
  @Override
  public String deleteDept(final String deptName) {
    Department d1 = departmentRepository.findByDeptName(deptName);
    if (d1 != null) {
      departmentRepository.deleteById(d1.getDeptId());
      return "Deleted Successfully";
    } else {
      throw new DepartmentNotFoundException(deptName);
    }
  }

  /**
   * getAllDepartments.
   *
   *@param currentPage
   *
   *@return list of department.
   */
  @Override
  public List<DepartmentListDto> getAllDepartments(final Integer currentPage) {
    final int noOfElement = 5;
    Pageable pageable = PageRequest.of(currentPage, noOfElement);
    Page<Department> departmentPage = departmentRepository.findAll(pageable);
    List<Department> departments = departmentPage.getContent();
    return departments.stream()
        .map(department -> {
          DepartmentListDto deptDto = new DepartmentListDto();
          deptDto.setDeptId(department.getDeptId());
          deptDto.setDeptName(department.getDeptName());
          return deptDto;
        })
          .collect(Collectors.toList());
  }
}
