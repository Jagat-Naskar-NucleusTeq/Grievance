package com.feedback.payloads.department_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddDepartemntDtoTest {

  private AddDepartemntDto departmentDTO;

  @BeforeEach
  public void setUp() {
    departmentDTO = new AddDepartemntDto();
  }

  @Test
  public void testDefaultConstructor() {
    assertNull(departmentDTO.getDeptName());
  }

  @Test
  public void testParameterizedConstructor() {
    AddDepartemntDto departmentDTO = new AddDepartemntDto("HR");

    assertEquals("HR", departmentDTO.getDeptName());
  }

  @Test
  public void testSettersAndGetters() {
    departmentDTO.setDeptName("Finance");

    assertEquals("Finance", departmentDTO.getDeptName());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddDepartemntDto departmentDTO1 = new AddDepartemntDto("Marketing");
    AddDepartemntDto departmentDTO2 = new AddDepartemntDto("Marketing");
    AddDepartemntDto departmentDTO3 = new AddDepartemntDto("Finance");

    assertEquals(departmentDTO1, departmentDTO2);
    assertNotEquals(departmentDTO1, departmentDTO3);

    assertEquals(departmentDTO1.hashCode(), departmentDTO2.hashCode());
    assertNotEquals(departmentDTO1.hashCode(), departmentDTO3.hashCode());
  }

  @Test
  public void testToString() {
    AddDepartemntDto departmentDTO = new AddDepartemntDto("Marketing");
    String expectedToString = "AddDepartemntDTO [deptName=Marketing]";
    assertEquals(expectedToString, departmentDTO.toString());
  }
  

  
  
  @Test
  void testEquals_SameObject() {
      AddDepartemntDto dto = new AddDepartemntDto("Marketing");
      assertTrue(dto.equals(dto));
  }

  @Test
  void testEquals_NullObject() {
      AddDepartemntDto dto = new AddDepartemntDto("Marketing");
      assertFalse(dto.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      AddDepartemntDto dto = new AddDepartemntDto("Marketing");
      assertFalse(dto.equals("Not an AddDepartemntDTO object"));
  }

  @Test
  void testEquals_EqualObjects() {
      AddDepartemntDto dto1 = new AddDepartemntDto("Marketing");
      AddDepartemntDto dto2 = new AddDepartemntDto("Marketing");
      assertTrue(dto1.equals(dto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      AddDepartemntDto dto1 = new AddDepartemntDto("Marketing");
      AddDepartemntDto dto2 = new AddDepartemntDto("HR");
      assertFalse(dto1.equals(dto2));
  }

}
