package com.feedback.payloads.department_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddDepartemntDTOTest {

  private AddDepartemntDTO departmentDTO;

  @BeforeEach
  public void setUp() {
    departmentDTO = new AddDepartemntDTO();
  }

  @Test
  public void testDefaultConstructor() {
    assertNull(departmentDTO.getDeptName());
  }

  @Test
  public void testParameterizedConstructor() {
    AddDepartemntDTO departmentDTO = new AddDepartemntDTO("HR");

    assertEquals("HR", departmentDTO.getDeptName());
  }

  @Test
  public void testSettersAndGetters() {
    departmentDTO.setDeptName("Finance");

    assertEquals("Finance", departmentDTO.getDeptName());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddDepartemntDTO departmentDTO1 = new AddDepartemntDTO("Marketing");
    AddDepartemntDTO departmentDTO2 = new AddDepartemntDTO("Marketing");
    AddDepartemntDTO departmentDTO3 = new AddDepartemntDTO("Finance");

    assertEquals(departmentDTO1, departmentDTO2);
    assertNotEquals(departmentDTO1, departmentDTO3);

    assertEquals(departmentDTO1.hashCode(), departmentDTO2.hashCode());
    assertNotEquals(departmentDTO1.hashCode(), departmentDTO3.hashCode());
  }

  @Test
  public void testToString() {
    AddDepartemntDTO departmentDTO = new AddDepartemntDTO("Marketing");
    String expectedToString = "AddDepartemntDTO [deptName=Marketing]";
    assertEquals(expectedToString, departmentDTO.toString());
  }
  

  
  
  @Test
  void testEquals_SameObject() {
      AddDepartemntDTO dto = new AddDepartemntDTO("Marketing");
      assertTrue(dto.equals(dto));
  }

  @Test
  void testEquals_NullObject() {
      AddDepartemntDTO dto = new AddDepartemntDTO("Marketing");
      assertFalse(dto.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      AddDepartemntDTO dto = new AddDepartemntDTO("Marketing");
      assertFalse(dto.equals("Not an AddDepartemntDTO object"));
  }

  @Test
  void testEquals_EqualObjects() {
      AddDepartemntDTO dto1 = new AddDepartemntDTO("Marketing");
      AddDepartemntDTO dto2 = new AddDepartemntDTO("Marketing");
      assertTrue(dto1.equals(dto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      AddDepartemntDTO dto1 = new AddDepartemntDTO("Marketing");
      AddDepartemntDTO dto2 = new AddDepartemntDTO("HR");
      assertFalse(dto1.equals(dto2));
  }

}
