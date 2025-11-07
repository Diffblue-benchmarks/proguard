package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConfigurationElementDiffblueTest {
  /**
   * Test new {@link ConfigurationElement} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConfigurationElement}
   */
  @Test
  @DisplayName("Test new ConfigurationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationElement.<init>()"})
  void testNewConfigurationElement() {
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
