package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ERoleTest {

  @Test
  void testEnumValues() {
	    assertEquals(2, ERole.values().length);
	    assertEquals(ERole.admin, ERole.valueOf("admin"));
	    assertEquals(ERole.member, ERole.valueOf("member"));
	  }
}

