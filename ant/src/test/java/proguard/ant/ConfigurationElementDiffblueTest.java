package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.Configuration;

class ConfigurationElementDiffblueTest {
  /**
   * Test {@link ConfigurationElement#appendTo(Configuration)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElement} (default constructor) appendSelector {@link
   *       FileSelector}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationElement#appendTo(Configuration)}
   */
  @Test
  @DisplayName(
      "Test appendTo(Configuration); given ConfigurationElement (default constructor) appendSelector FileSelector")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationElement.appendTo(Configuration)"})
  void testAppendTo_givenConfigurationElementAppendSelectorFileSelector() {
    // Arrange
    ConfigurationElement configurationElement = new ConfigurationElement();
    configurationElement.appendSelector(mock(FileSelector.class));

    // Act and Assert
    assertThrows(
        BuildException.class, () -> configurationElement.appendTo(mock(Configuration.class)));
  }

  /**
   * Test {@link ConfigurationElement#appendTo(Configuration)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElement} (default constructor) Project is {@link Project}
   *       (default constructor).
   *   <li>Then throw {@link BuildException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationElement#appendTo(Configuration)}
   */
  @Test
  @DisplayName(
      "Test appendTo(Configuration); given ConfigurationElement (default constructor) Project is Project (default constructor); then throw BuildException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationElement.appendTo(Configuration)"})
  void testAppendTo_givenConfigurationElementProjectIsProject_thenThrowBuildException() {
    // Arrange
    ConfigurationElement configurationElement = new ConfigurationElement();
    configurationElement.setProject(new Project());

    // Act and Assert
    assertThrows(
        BuildException.class, () -> configurationElement.appendTo(mock(Configuration.class)));
  }

  /**
   * Test {@link ConfigurationElement#appendTo(Configuration)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElement} (default constructor).
   *   <li>Then throw {@link BuildException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationElement#appendTo(Configuration)}
   */
  @Test
  @DisplayName(
      "Test appendTo(Configuration); given ConfigurationElement (default constructor); then throw BuildException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationElement.appendTo(Configuration)"})
  void testAppendTo_givenConfigurationElement_thenThrowBuildException() {
    // Arrange, Act and Assert
    assertThrows(
        BuildException.class, () -> new ConfigurationElement().appendTo(mock(Configuration.class)));
  }

  /**
   * Test {@link ConfigurationElement#appendTo(Configuration)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then throw {@link BuildException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationElement#appendTo(Configuration)}
   */
  @Test
  @DisplayName("Test appendTo(Configuration); given 'java.lang.Object'; then throw BuildException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationElement.appendTo(Configuration)"})
  void testAppendTo_givenJavaLangObject_thenThrowBuildException() {
    // Arrange
    Project project = new Project();
    Class<Object> typeClass = Object.class;
    project.addDataTypeDefinition("42", typeClass);
    project.addBuildListener(new AntClassLoader());

    ConfigurationElement configurationElement = new ConfigurationElement();
    configurationElement.setProject(project);

    // Act and Assert
    assertThrows(
        BuildException.class, () -> configurationElement.appendTo(mock(Configuration.class)));
  }

  /**
   * Test {@link ConfigurationElement#appendTo(Configuration)}.
   *
   * <ul>
   *   <li>Given {@link Project} (default constructor) addBuildListener {@link
   *       AntClassLoader#AntClassLoader()}.
   *   <li>Then throw {@link BuildException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigurationElement#appendTo(Configuration)}
   */
  @Test
  @DisplayName(
      "Test appendTo(Configuration); given Project (default constructor) addBuildListener AntClassLoader(); then throw BuildException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigurationElement.appendTo(Configuration)"})
  void testAppendTo_givenProjectAddBuildListenerAntClassLoader_thenThrowBuildException() {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    ConfigurationElement configurationElement = new ConfigurationElement();
    configurationElement.setProject(project);

    // Act and Assert
    assertThrows(
        BuildException.class, () -> configurationElement.appendTo(mock(Configuration.class)));
  }

  /**
   * Test new {@link ConfigurationElement} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ConfigurationElement}
   */
  @Test
  @DisplayName("Test new ConfigurationElement (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
