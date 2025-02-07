package proguard.ant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Location;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FilterElementDiffblueTest {
  /**
   * Test {@link FilterElement#appendTo(List, boolean)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.FilterElement.appendTo(java.util.List, boolean)"})
  public void testAppendTo_given42_whenArrayListAdd42_thenArrayListSizeIsTwo() {
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
   * Test {@link FilterElement#appendTo(List, boolean)}.
   * <ul>
   *   <li>Given {@link FilterElement} (default constructor) Name is {@code Name}.</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.FilterElement.appendTo(java.util.List, boolean)"})
  public void testAppendTo_givenFilterElementNameIsName_whenArrayList_thenArrayListSizeIsOne() {
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
   * Test {@link FilterElement#appendTo(List, boolean)}.
   * <ul>
   *   <li>Given {@link FilterElement} (default constructor) Name is {@code Name}.</li>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.FilterElement.appendTo(java.util.List, boolean)"})
  public void testAppendTo_givenFilterElementNameIsName_whenFalse_thenArrayListSizeIsOne() {
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
   * Test {@link FilterElement#appendTo(List, boolean)}.
   * <ul>
   *   <li>Given {@link FilterElement} (default constructor).</li>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterElement#appendTo(List, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.FilterElement.appendTo(java.util.List, boolean)"})
  public void testAppendTo_givenFilterElement_whenArrayList_thenArrayListEmpty() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    ArrayList<Object> filter = new ArrayList<>();

    // Act
    filterElement.appendTo(filter, true);

    // Assert that nothing has changed
    assertTrue(filter.isEmpty());
  }

  /**
   * Test new {@link FilterElement} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link FilterElement}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.FilterElement.<init>()"})
  public void testNewFilterElement() {
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
