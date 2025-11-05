package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.tools.ant.Location;
import org.junit.jupiter.api.Test;

class ConfigurationElementDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link ConfigurationElement}
   */
  @Test
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
