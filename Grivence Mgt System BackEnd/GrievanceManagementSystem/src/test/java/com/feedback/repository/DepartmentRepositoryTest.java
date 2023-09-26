package com.feedback.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.feedback.entities.Department;

@RunWith(SpringRunner.class)
@DataJpaTest
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository departmentRepository;
  
  @Autowired
  private TestEntityManager entityManager;
  /**
   * checking if the department exist by name.
   * returning Department object.
   */
  @Test
  void testFindByDeptName_success() {
    Department department = new Department();
    department.setDeptName("HR1");
    entityManager.persistAndFlush(department);
    Department foundDept = departmentRepository.findByDeptName("HR1");
    assertEquals(department.hashCode(), foundDept.hashCode());
//    assertNotEquals
  }

  /**
   * checking if the department exist by name.
   * returning Department object.
 */
  @Test
  void testFindByDeptName_failure() {
      Department department4 = new Department();
      department4.setDeptName("Rocket Science");
      entityManager.persistAndFlush(department4);
      Department foundDept = departmentRepository.findByDeptName("Parapsychology");
      assertNull(foundDept);
  }
 
}

