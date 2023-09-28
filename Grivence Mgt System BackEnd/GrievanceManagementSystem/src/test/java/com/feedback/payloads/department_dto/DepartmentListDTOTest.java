package com.feedback.payloads.department_dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentListDtoTest {
  @Test
  void testEquals_SameObject() {
      DepartmentListDto dto = new DepartmentListDto(1, "Sales");
      assertTrue(dto.equals(dto));
  }

  @Test
  void testEquals_NullObject() {
      DepartmentListDto dto = new DepartmentListDto(1, "Sales");
      assertFalse(dto.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
      DepartmentListDto dto = new DepartmentListDto(1, "Sales");
      assertFalse(dto.equals("Not a DepartmentListDto object"));
  }

  @Test
  void testEquals_EqualObjects() {
      DepartmentListDto dto1 = new DepartmentListDto(1, "Sales");
      DepartmentListDto dto2 = new DepartmentListDto(1, "Sales");
      assertTrue(dto1.equals(dto2));
  }

  @Test
  void testEquals_UnequalObjects() {
      DepartmentListDto dto1 = new DepartmentListDto(1, "Sales");
      DepartmentListDto dto2 = new DepartmentListDto(2, "HR");
      assertFalse(dto1.equals(dto2));
  }

  @Test
  void testHashCode() {
      DepartmentListDto dto1 = new DepartmentListDto(1, "Sales");
      DepartmentListDto dto2 = new DepartmentListDto(1, "Sales");
      assertEquals(dto1.hashCode(), dto2.hashCode());
  }
  @Test
  void testToString() {
      DepartmentListDto dto = new DepartmentListDto(1, "IT");
      String expectedString = "DepartmentListDto [deptId=1, deptName=IT]";
      assertEquals(expectedString, dto.toString());
  }

}
