package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.Test;

class FilterElementDiffblueTest {
  /**
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  void testAppendTo() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    ArrayList<Object> filter = new ArrayList<>();

    // Act
    filterElement.appendTo(filter, true);

    // Assert
    assertTrue(filter.isEmpty());
  }

  /**
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  void testAppendTo2() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");
    ArrayList<Object> filter = new ArrayList<>();

    // Act
    filterElement.appendTo(filter, true);

    // Assert
    assertEquals(1, filter.size());
    assertEquals("Name", filter.get(0));
  }

  /**
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  void testAppendTo3() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    ArrayList<Object> filter = new ArrayList<>();
    filter.add("42");

    // Act
    filterElement.appendTo(filter, true);

    // Assert
    assertEquals(2, filter.size());
    assertEquals("42", filter.get(0));
    assertEquals("Name", filter.get(1));
  }

  /**
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  void testAppendTo4() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");
    ArrayList<Object> filter = new ArrayList<>();

    // Act
    filterElement.appendTo(filter, false);

    // Assert
    assertEquals(1, filter.size());
    assertEquals("Name", filter.get(0));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link FilterElement}
   */
  @Test
  void testNewFilterElement() {
    // Arrange and Act
    FilterElement actualFilterElement = new FilterElement();

    // Assert
    Location location = actualFilterElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualFilterElement.getDescription());
    assertNull(actualFilterElement.getProject());
    assertNull(actualFilterElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertFalse(actualFilterElement.isReference());
  }
}
