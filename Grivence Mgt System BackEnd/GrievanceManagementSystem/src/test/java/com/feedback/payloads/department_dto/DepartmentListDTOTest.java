package com.feedback.payloads.department_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentListDTOTest {
  @Test
  void testEquals_SameObject() {
      DepartmentListDTO dto = new DepartmentListDTO(1, "Sales");
      assertTrue(dto.equals(dto));
  }

  @Test
  void testEquals_NullObject() {
      DepartmentListDTO dto = new DepartmentListDTO(1, "Sales");
      assertFalse(dto.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      DepartmentListDTO dto = new DepartmentListDTO(1, "Sales");
      assertFalse(dto.equals("Not a DepartmentListDTO object"));
  }

  @Test
  void testEquals_EqualObjects() {
      DepartmentListDTO dto1 = new DepartmentListDTO(1, "Sales");
      DepartmentListDTO dto2 = new DepartmentListDTO(1, "Sales");
      assertTrue(dto1.equals(dto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      DepartmentListDTO dto1 = new DepartmentListDTO(1, "Sales");
      DepartmentListDTO dto2 = new DepartmentListDTO(2, "HR");
      assertFalse(dto1.equals(dto2));
  }

  @Test
  void testHashCode() {
      DepartmentListDTO dto1 = new DepartmentListDTO(1, "Sales");
      DepartmentListDTO dto2 = new DepartmentListDTO(1, "Sales");
      assertEquals(dto1.hashCode(), dto2.hashCode());
  }
  @Test
  void testToString() {
      DepartmentListDTO dto = new DepartmentListDTO(1, "IT");
      String expectedString = "DepartmentListDTO [deptId=1, deptName=IT]";
      assertEquals(expectedString, dto.toString());
  }

}
