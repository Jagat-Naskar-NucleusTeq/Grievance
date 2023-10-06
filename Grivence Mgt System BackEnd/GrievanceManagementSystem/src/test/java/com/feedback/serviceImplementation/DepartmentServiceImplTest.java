package com.feedback.serviceImplementation;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.feedback.custom_exception.DepartmentNotFoundException;
import com.feedback.entities.Department;
import com.feedback.payloads.department_dto.AddDepartemntDto;
import com.feedback.payloads.department_dto.DepartmentListDto;
import com.feedback.repository.DepartmentRepository;;

@TestInstance(Lifecycle.PER_CLASS)
class DepartmentServiceImplTest {

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @Mock
  private DepartmentRepository departmentRepository;

  @BeforeAll
  public void setUp() {
    // Initialize Mockito annotations
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCheckAlreadyExist_DepartmentExists() {
    // Create a valid AddDepartemntDTO object
    AddDepartemntDto validDepartmentDTO = new AddDepartemntDto("HR");

    // Mock the behavior of the repository to return a Department object (department already exists)
    when(departmentRepository.findByDeptName("HR")).thenReturn(new Department());

    // Call the service method
    boolean result = departmentService.checkAlreadyExist(validDepartmentDTO);

    // Assert that the result is true since the department already exists
    assertTrue(result);
  }

  @Test
  public void testCheckAlreadyExist_DepartmentDoesNotExist() {
    // Create a valid AddDepartemntDTO object
    AddDepartemntDto validDepartmentDTO = new AddDepartemntDto("IT");

    // Mock the behavior of the repository to return null (department does not exist)
    when(departmentRepository.findByDeptName("IT")).thenReturn(null);

    // Call the service method
    boolean result = departmentService.checkAlreadyExist(validDepartmentDTO);

    // Assert that the result is false since the department does not exist
    assertFalse(result);
  }

  @Test
  public void testAddDept_Success() {
    // Create a valid AddDepartemntDTO object
    AddDepartemntDto validDepartmentDTO = new AddDepartemntDto("IT");

    // Mock the behavior of the repository to return a Department object after saving
    Department savedDepartment = new Department();
    savedDepartment.setDeptId(1);
    savedDepartment.setDeptName("IT");
    when(departmentRepository.save(any(Department.class))).thenReturn(savedDepartment);

    // Call the service method
    Department result = departmentService.addDept(validDepartmentDTO);

    // Assert that the result is the saved department object
    assertNotNull(result);
    assertEquals("IT", result.getDeptName());
  }

  @Test
  public void testGetAllDepartments() {
    Department department1 = new Department();
    department1.setDeptId(1);
    department1.setDeptName("IT");

    Department department2 = new Department();
    department2.setDeptId(2);
    department2.setDeptName("HR");

    List<Department> sampleDepartments = Arrays.asList(department1, department2);

    when(departmentRepository.findAll()).thenReturn(sampleDepartments);

    List<DepartmentListDto> result = departmentService.getAllDepartments();

    List<DepartmentListDto> expected = sampleDepartments.stream()
      .map(department -> {
        DepartmentListDto deptDTO = new DepartmentListDto();
        deptDTO.setDeptId(department.getDeptId());
        deptDTO.setDeptName(department.getDeptName());
        return deptDTO;
        })
      .collect(Collectors.toList());

    assertEquals(expected, result);
  }

  @Test
  void testDeleteDept() {
      // Arrange
      String deptName = "TestDept";
      Department mockDepartment = new Department();
      mockDepartment.setDeptId(1);
      mockDepartment.setDeptName(deptName);

      when(departmentRepository.findByDeptName(deptName)).thenReturn(mockDepartment);

      // Act
      String result = departmentService.deleteDept(deptName);

      // Assert
      assertEquals("Deleted Successfully", result);
      verify(departmentRepository, times(1)).findByDeptName(deptName);
      verify(departmentRepository, times(1)).deleteById(1);
  }

  @Test
  void testDeleteDeptDepartmentNotFound() {
      // Arrange
      String deptName = "NonExistentDept";

      when(departmentRepository.findByDeptName(deptName)).thenReturn(null);

      // Act and Assert
      assertThrows(DepartmentNotFoundException.class, () -> {
          departmentService.deleteDept(deptName);
      });

      verify(departmentRepository, times(1)).findByDeptName(deptName);
      verify(departmentRepository, never()).deleteById(anyInt());
  }

  @Test
  public void testGetAllDepartmentss() {
      Department department1 = new Department();
      department1.setDeptId(1);
      department1.setDeptName("IT");

      Department department2 = new Department();
      department2.setDeptId(2);
      department2.setDeptName("HR");

      List<Department> sampleDepartments = Arrays.asList(department1, department2);

      Page<Department> samplePage = new PageImpl<>(sampleDepartments);

      when(departmentRepository.findAll(any(Pageable.class))).thenReturn(samplePage);

      List<DepartmentListDto> result = departmentService.getAllDepartments(0);

      List<DepartmentListDto> expected = sampleDepartments.stream()
              .map(department -> {
                  DepartmentListDto deptDTO = new DepartmentListDto();
                  deptDTO.setDeptId(department.getDeptId());
                  deptDTO.setDeptName(department.getDeptName());
                  return deptDTO;
              })
              .collect(Collectors.toList());

      assertEquals(expected, result);
  }
}
