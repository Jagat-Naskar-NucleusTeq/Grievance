package com.feedback.entities;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class EStatusTest {

  @Test
  void testEnumValues() {
	    assertEquals(3, EStatus.values().length);
	    assertEquals(EStatus.Open, EStatus.valueOf("Open"));
	    assertEquals(EStatus.Being_Addressed, EStatus.valueOf("Being_Addressed"));
	    assertEquals(EStatus.Resolved, EStatus.valueOf("Resolved"));
	  }
  
  @Test
  void getStatusComparatorTest() {
      assertNotNull(EStatus.getStatusComparator());
  }

  @Test
  void getSortOrderTest() throws Exception {
    EStatus status = EStatus.Open;

    Method method = EStatus.class.getDeclaredMethod("getSortOrder");
    method.setAccessible(true);

    int sortOrder = (int) method.invoke(status);

    assertEquals(1, sortOrder);
  }
}
