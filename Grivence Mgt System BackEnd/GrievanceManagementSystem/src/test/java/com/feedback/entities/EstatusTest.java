package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class EstatusTest {

  @Test
  void testEnumValues() {
	    assertEquals(3, Estatus.values().length);
	    assertEquals(Estatus.Open, Estatus.valueOf("Open"));
	    assertEquals(Estatus.Being_Addressed, Estatus.valueOf("Being_Addressed"));
	    assertEquals(Estatus.Resolved, Estatus.valueOf("Resolved"));
	  }
  
  @Test
  void getStatusComparatorTest() {
      assertNotNull(Estatus.getStatusComparator());
  }

  @Test
  void getSortOrderTest() throws Exception {
    Estatus status = Estatus.Open;

    Method method = Estatus.class.getDeclaredMethod("getSortOrder");
    method.setAccessible(true);

    int sortOrder = (int) method.invoke(status);

    assertEquals(1, sortOrder);
  }
}
