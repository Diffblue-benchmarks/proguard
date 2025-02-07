package proguard.ant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.tools.ant.Location;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConfigurationElementDiffblueTest {
  /**
   * Test new {@link ConfigurationElement} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConfigurationElement}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationElement.<init>()"})
  public void testNewConfigurationElement() {
    // Arrange and Act
    ConfigurationElement actualConfigurationElement = new ConfigurationElement();

    // Assert
    assertNull(actualConfigurationElement.getDir());
    Location location = actualConfigurationElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualConfigurationElement.getDescription());
    assertNull(actualConfigurationElement.getProject());
    assertNull(actualConfigurationElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertEquals(5, actualConfigurationElement.getMaxLevelsOfSymlinks());
    assertFalse(actualConfigurationElement.isReference());
    assertTrue(actualConfigurationElement.getDefaultexcludes());
    assertTrue(actualConfigurationElement.getErrorOnMissingDir());
    assertTrue(actualConfigurationElement.isFilesystemOnly());
  }
}
