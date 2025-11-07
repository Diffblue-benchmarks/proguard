package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.List;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.ClassPath;
import proguard.ClassSpecification;
import proguard.Configuration;
import proguard.KeepClassSpecification;

class ConfigurationTaskDiffblueTest {
  /**
   * Test {@link ConfigurationTask#appendTo(Configuration)}.
   * <p>
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  @DisplayName("Test appendTo(Configuration)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.appendTo(Configuration)"})
  void testAppendTo() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert that nothing has changed
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keepDirectories);
    assertNull(configuration.keep);
  }

  /**
   * Test {@link ConfigurationTask#appendTo(Configuration)}.
   * <ul>
   *   <li>Given {@link ConfigurationTask} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  @DisplayName("Test appendTo(Configuration); given ConfigurationTask (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.appendTo(Configuration)"})
  void testAppendTo_givenConfigurationTask() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert that nothing has changed
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keepDirectories);
    assertNull(configuration.keep);
  }

  /**
   * Test {@link ConfigurationTask#appendTo(Configuration)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#keepDirectories} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  @DisplayName("Test appendTo(Configuration); then ConfigurationTask (default constructor) configuration keepDirectories Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.appendTo(Configuration)"})
  void testAppendTo_thenConfigurationTaskConfigurationKeepDirectoriesEmpty() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keep);
    assertTrue(configuration.keepDirectories.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#appendTo(Configuration)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#keep} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  @DisplayName("Test appendTo(Configuration); then ConfigurationTask (default constructor) configuration keep size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.appendTo(Configuration)"})
  void testAppendTo_thenConfigurationTaskConfigurationKeepSizeIsOne() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keepDirectories);
    assertEquals(1, configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Assert that nothing has changed
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Project} (default constructor) addDataTypeDefinition {@code 42} and {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); given '42'; when Project (default constructor) addDataTypeDefinition '42' and Object")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_given42_whenProjectAddDataTypeDefinition42AndObject() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    Class<Object> typeClass = Object.class;
    project.addDataTypeDefinition("42", typeClass);
    project.addBuildListener(new AntClassLoader());

    // Act
    configurationTask.addConfiguredInjar(new ClassPathElement(project));

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@link AntClassLoader#AntClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); given AntClassLoader()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_givenAntClassLoader() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    // Act
    configurationTask.addConfiguredInjar(new ClassPathElement(project));

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@code Filter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); given 'Filter'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_givenFilter() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.setFilter("Filter");
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredInjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#programJars} size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); then ConfigurationTask (default constructor) configuration programJars size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_thenConfigurationTaskConfigurationProgramJarsSizeIsThree() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredInjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#programJars} size is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); then ConfigurationTask (default constructor) configuration programJars size is three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_thenConfigurationTaskConfigurationProgramJarsSizeIsThree2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addJavaRuntime();
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredInjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#programJars} size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredInjar(ClassPathElement); then ConfigurationTask (default constructor) configuration programJars size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredInjar(ClassPathElement)"})
  void testAddConfiguredInjar_thenConfigurationTaskConfigurationProgramJarsSizeIsZero() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOutjar(ClassPathElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#programJars} size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOutjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOutjar(ClassPathElement); then ConfigurationTask (default constructor) configuration programJars size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOutjar(ClassPathElement)"})
  void testAddConfiguredOutjar_thenConfigurationTaskConfigurationProgramJarsSizeIsZero() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    ClassPathElement classPathElement = mock(ClassPathElement.class);
    doNothing().when(classPathElement).appendClassPathEntriesTo(Mockito.<ClassPath>any(), anyBoolean());

    // Act
    configurationTask.addConfiguredOutjar(classPathElement);

    // Assert
    verify(classPathElement).appendClassPathEntriesTo(isA(ClassPath.class), eq(true));
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(new Project()));

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(new Project()));

    // Assert that nothing has changed
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredLibraryjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar4() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addJavaRuntime();
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredLibraryjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Project} (default constructor) addDataTypeDefinition {@code 42} and {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement); given '42'; when Project (default constructor) addDataTypeDefinition '42' and Object")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar_given42_whenProjectAddDataTypeDefinition42AndObject() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    Class<Object> typeClass = Object.class;
    project.addDataTypeDefinition("42", typeClass);
    project.addBuildListener(new AntClassLoader());

    // Act
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(project));

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@link AntClassLoader#AntClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement); given AntClassLoader()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar_givenAntClassLoader() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    // Act
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(project));

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}.
   * <ul>
   *   <li>Given {@code Filter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @DisplayName("Test addConfiguredLibraryjar(ClassPathElement); given 'Filter'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredLibraryjar(ClassPathElement)"})
  void testAddConfiguredLibraryjar_givenFilter() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.setFilter("Filter");
    classPathElement.addJavaRuntime();

    // Act
    configurationTask.addConfiguredLibraryjar(classPathElement);

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(3, classPath.size());
    assertFalse(classPath.hasOutput());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectory(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectory(FilterElement)"})
  void testAddConfiguredKeepdirectory() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectory(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectory(FilterElement)"})
  void testAddConfiguredKeepdirectory2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectory(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectory(FilterElement)"})
  void testAddConfiguredKeepdirectory3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepdirectory(filterElement);

    // Assert
    List list = configurationTask.configuration.keepDirectories;
    assertEquals(1, list.size());
    assertEquals("Name", list.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectories(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectories(FilterElement)"})
  void testAddConfiguredKeepdirectories() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepdirectories(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectories(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectories(FilterElement)"})
  void testAddConfiguredKeepdirectories2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepdirectories(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepdirectories(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepdirectories(FilterElement)"})
  void testAddConfiguredKeepdirectories3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepdirectories(filterElement);

    // Assert
    List list = configurationTask.configuration.keepDirectories;
    assertEquals(1, list.size());
    assertEquals("Name", list.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeep(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeep(KeepSpecificationElement)"})
  void testAddConfiguredKeep() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeep(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeep(KeepSpecificationElement)"})
  void testAddConfiguredKeep2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeep(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#keep} first {@link ClassSpecification#className} is {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeep(KeepSpecificationElement); then ConfigurationTask (default constructor) configuration keep first className is 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeep(KeepSpecificationElement)"})
  void testAddConfiguredKeep_thenConfigurationTaskConfigurationKeepFirstClassNameIsName() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeep(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#keep} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeep(KeepSpecificationElement); then ConfigurationTask (default constructor) configuration keep size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeep(KeepSpecificationElement)"})
  void testAddConfiguredKeep_thenConfigurationTaskConfigurationKeepSizeIsTwo() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    assertEquals(keepClassSpecificationList.get(0), keepClassSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembers() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepclassmembers(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembers2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclassmembers(new KeepSpecificationElement());

    // Assert
    assertEquals(2, configurationTask.configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembers3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeepclassmembers(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembers4() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepclassmembers(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembers() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepclasseswithmembers(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembers2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclasseswithmembers(new KeepSpecificationElement());

    // Assert
    assertEquals(2, configurationTask.configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembers3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeepclasseswithmembers(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembers(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembers(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembers4() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepclasseswithmembers(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepnames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepnames(KeepSpecificationElement)"})
  void testAddConfiguredKeepnames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepnames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepnames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepnames(KeepSpecificationElement)"})
  void testAddConfiguredKeepnames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeepnames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepnames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepnames(KeepSpecificationElement)"})
  void testAddConfiguredKeepnames3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepnames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#keep} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepnames(KeepSpecificationElement); then ConfigurationTask (default constructor) configuration keep size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepnames(KeepSpecificationElement)"})
  void testAddConfiguredKeepnames_thenConfigurationTaskConfigurationKeepSizeIsTwo() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepnames(new KeepSpecificationElement());

    // Assert
    assertEquals(2, configurationTask.configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembernames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepclassmembernames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembernames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclassmembernames(new KeepSpecificationElement());

    // Assert
    assertEquals(2, configurationTask.configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembernames3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeepclassmembernames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclassmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclassmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclassmembernames4() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepclassmembernames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembernames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepclasseswithmembernames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembernames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclasseswithmembernames(new KeepSpecificationElement());

    // Assert
    assertEquals(2, configurationTask.configuration.keep.size());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembernames3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredKeepclasseswithmembernames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)"})
  void testAddConfiguredKeepclasseswithmembernames4() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    KeepSpecificationElement keepSpecificationElement = new KeepSpecificationElement();
    keepSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepclasseswithmembernames(keepSpecificationElement);

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(1, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult.markDescriptorClasses);
    assertTrue(getResult.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult.markConditionally);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredWhyareyoukeeping(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredWhyareyoukeeping(ClassSpecificationElement)"})
  void testAddConfiguredWhyareyoukeeping() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredWhyareyoukeeping(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredWhyareyoukeeping(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.whyAreYouKeeping;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredWhyareyoukeeping(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredWhyareyoukeeping(ClassSpecificationElement)"})
  void testAddConfiguredWhyareyoukeeping2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredWhyareyoukeeping(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.whyAreYouKeeping;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredWhyareyoukeeping(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredWhyareyoukeeping(ClassSpecificationElement)"})
  void testAddConfiguredWhyareyoukeeping3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredWhyareyoukeeping(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.whyAreYouKeeping;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredWhyareyoukeeping(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredWhyareyoukeeping(ClassSpecificationElement)"})
  void testAddConfiguredWhyareyoukeeping_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredWhyareyoukeeping(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.whyAreYouKeeping;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenosideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenosideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenosideeffects() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAssumenosideeffects(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredAssumenosideeffects(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoSideEffects;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenosideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenosideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenosideeffects2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredAssumenosideeffects(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenosideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenosideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenosideeffects3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredAssumenosideeffects(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenosideeffects(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenosideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenosideeffects_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAssumenosideeffects(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalsideeffects() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAssumenoexternalsideeffects(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredAssumenoexternalsideeffects(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalSideEffects;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalsideeffects2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredAssumenoexternalsideeffects(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalsideeffects3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredAssumenoexternalsideeffects(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalsideeffects_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAssumenoexternalsideeffects(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalSideEffects;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoescapingparameters(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoescapingparameters(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoescapingparameters() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAssumenoescapingparameters(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredAssumenoescapingparameters(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoEscapingParameters;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoescapingparameters(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoescapingparameters(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoescapingparameters2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredAssumenoescapingparameters(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoEscapingParameters;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoescapingparameters(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoescapingparameters(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoescapingparameters3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredAssumenoescapingparameters(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoEscapingParameters;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoescapingparameters(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoescapingparameters(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoescapingparameters_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAssumenoescapingparameters(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoEscapingParameters;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalreturnvalues() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAssumenoexternalreturnvalues(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredAssumenoexternalreturnvalues(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalReturnValues;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalreturnvalues2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredAssumenoexternalreturnvalues(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalReturnValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalreturnvalues3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredAssumenoexternalreturnvalues(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalReturnValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumenoexternalreturnvalues_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAssumenoexternalreturnvalues(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeNoExternalReturnValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumevalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumevalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumevalues() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAssumevalues(new ClassSpecificationElement());

    // Act
    configurationTask.addConfiguredAssumevalues(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeValues;
    assertEquals(2, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(getResult, classSpecificationList.get(1));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumevalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumevalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumevalues2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setAnnotation("Annotation");

    // Act
    configurationTask.addConfiguredAssumevalues(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("LAnnotation;", getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumevalues(ClassSpecificationElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumevalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumevalues3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    ClassSpecificationElement classSpecificationElement = new ClassSpecificationElement();
    classSpecificationElement.setName("Name");

    // Act
    configurationTask.addConfiguredAssumevalues(classSpecificationElement);

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertEquals("Name", getResult.className);
    assertNull(getResult.annotationType);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}.
   * <ul>
   *   <li>When {@link ClassSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAssumevalues(ClassSpecificationElement); when ClassSpecificationElement (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAssumevalues(ClassSpecificationElement)"})
  void testAddConfiguredAssumevalues_whenClassSpecificationElement() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAssumevalues(new ClassSpecificationElement());

    // Assert
    List<ClassSpecification> classSpecificationList = configurationTask.configuration.assumeValues;
    assertEquals(1, classSpecificationList.size());
    ClassSpecification getResult = classSpecificationList.get(0);
    assertNull(getResult.annotationType);
    assertNull(getResult.className);
    assertNull(getResult.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimizations(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimizations(FilterElement)"})
  void testAddConfiguredOptimizations() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimizations(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimizations(FilterElement)"})
  void testAddConfiguredOptimizations2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Act
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimizations(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimizations(FilterElement)"})
  void testAddConfiguredOptimizations3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredOptimizations(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.optimizations;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimization(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimization(FilterElement)"})
  void testAddConfiguredOptimization() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredOptimization(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimization(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimization(FilterElement)"})
  void testAddConfiguredOptimization2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Act
    configurationTask.addConfiguredOptimization(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredOptimization(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredOptimization(FilterElement)"})
  void testAddConfiguredOptimization3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredOptimization(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.optimizations;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagename(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagename(FilterElement)"})
  void testAddConfiguredKeeppackagename() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagename(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagename(FilterElement)"})
  void testAddConfiguredKeeppackagename2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Act
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagename(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagename(FilterElement)"})
  void testAddConfiguredKeeppackagename3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeeppackagename(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.keepPackageNames;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagenames(FilterElement)"})
  void testAddConfiguredKeeppackagenames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeeppackagenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagenames(FilterElement)"})
  void testAddConfiguredKeeppackagenames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Act
    configurationTask.addConfiguredKeeppackagenames(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeeppackagenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeeppackagenames(FilterElement)"})
  void testAddConfiguredKeeppackagenames3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeeppackagenames(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.keepPackageNames;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattributes(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattributes(FilterElement)"})
  void testAddConfiguredKeepattributes() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattributes(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattributes(FilterElement)"})
  void testAddConfiguredKeepattributes2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattributes(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattributes(FilterElement)"})
  void testAddConfiguredKeepattributes3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepattributes(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.keepAttributes;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattribute(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattribute(FilterElement)"})
  void testAddConfiguredKeepattribute() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepattribute(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattribute(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattribute(FilterElement)"})
  void testAddConfiguredKeepattribute2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepattribute(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredKeepattribute(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredKeepattribute(FilterElement)"})
  void testAddConfiguredKeepattribute3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredKeepattribute(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.keepAttributes;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptclassstrings(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptclassstrings(FilterElement)"})
  void testAddConfiguredAdaptclassstrings() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptClassStrings.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptclassstrings(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptclassstrings(FilterElement)"})
  void testAddConfiguredAdaptclassstrings2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.adaptClassStrings.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptclassstrings(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptclassstrings(FilterElement)"})
  void testAddConfiguredAdaptclassstrings3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredAdaptclassstrings(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.adaptClassStrings;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilenames(FilterElement)"})
  void testAddConfiguredAdaptresourcefilenames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilenames(FilterElement)"})
  void testAddConfiguredAdaptresourcefilenames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.adaptResourceFileNames.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilenames(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilenames(FilterElement)"})
  void testAddConfiguredAdaptresourcefilenames3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredAdaptresourcefilenames(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.adaptResourceFileNames;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilecontents(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilecontents(FilterElement)"})
  void testAddConfiguredAdaptresourcefilecontents() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileContents.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilecontents(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilecontents(FilterElement)"})
  void testAddConfiguredAdaptresourcefilecontents2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.adaptResourceFileContents.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredAdaptresourcefilecontents(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredAdaptresourcefilecontents(FilterElement)"})
  void testAddConfiguredAdaptresourcefilecontents3() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredAdaptresourcefilecontents(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.adaptResourceFileContents;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontnote(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontnote(FilterElement)"})
  void testAddConfiguredDontnote() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Act
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.note.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontnote(FilterElement); then ConfigurationTask (default constructor) configuration note Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontnote(FilterElement)"})
  void testAddConfiguredDontnote_thenConfigurationTaskConfigurationNoteEmpty() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.note.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontnote(FilterElement); then ConfigurationTask (default constructor) configuration note size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontnote(FilterElement)"})
  void testAddConfiguredDontnote_thenConfigurationTaskConfigurationNoteSizeIsOne() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredDontnote(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.note;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontwarn(FilterElement)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontwarn(FilterElement)"})
  void testAddConfiguredDontwarn() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Act
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Assert that nothing has changed
    assertTrue(configurationTask.configuration.warn.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontwarn(FilterElement); then ConfigurationTask (default constructor) configuration warn Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontwarn(FilterElement)"})
  void testAddConfiguredDontwarn_thenConfigurationTaskConfigurationWarnEmpty() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.warn.isEmpty());
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}.
   * <ul>
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  @DisplayName("Test addConfiguredDontwarn(FilterElement); then ConfigurationTask (default constructor) configuration warn size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredDontwarn(FilterElement)"})
  void testAddConfiguredDontwarn_thenConfigurationTaskConfigurationWarnSizeIsOne() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    // Act
    configurationTask.addConfiguredDontwarn(filterElement);

    // Assert
    List<String> stringList = configurationTask.configuration.warn;
    assertEquals(1, stringList.size());
    assertEquals("Name", stringList.get(0));
  }

  /**
   * Test {@link ConfigurationTask#addConfiguredConfiguration(ConfigurationElement)}.
   * <ul>
   *   <li>Then calls {@link ConfigurationElement#appendTo(Configuration)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredConfiguration(ConfigurationElement)}
   */
  @Test
  @DisplayName("Test addConfiguredConfiguration(ConfigurationElement); then calls appendTo(Configuration)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addConfiguredConfiguration(ConfigurationElement)"})
  void testAddConfiguredConfiguration_thenCallsAppendTo() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    ConfigurationElement configurationElement = mock(ConfigurationElement.class);
    doNothing().when(configurationElement).appendTo(Mockito.<Configuration>any());

    // Act
    configurationTask.addConfiguredConfiguration(configurationElement);

    // Assert
    verify(configurationElement).appendTo(isA(Configuration.class));
  }

  /**
   * Test {@link ConfigurationTask#addText(String)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addText(String)}
   */
  @Test
  @DisplayName("Test addText(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addText(String)"})
  void testAddText() throws BuildException {
    // Arrange
    Project project = new Project();
    project.setBaseDir(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile());

    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> configurationTask.addText("Text"));
  }

  /**
   * Test {@link ConfigurationTask#addText(String)}.
   * <ul>
   *   <li>Given {@link ConfigurationTask} (default constructor) Project is {@link Project} (default constructor).</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addText(String)}
   */
  @Test
  @DisplayName("Test addText(String); given ConfigurationTask (default constructor) Project is Project (default constructor); then throw BuildException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addText(String)"})
  void testAddText_givenConfigurationTaskProjectIsProject_thenThrowBuildException() throws BuildException {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.setProject(new Project());

    // Act and Assert
    assertThrows(BuildException.class, () -> configurationTask.addText("Text"));
  }

  /**
   * Test {@link ConfigurationTask#addText(String)}.
   * <ul>
   *   <li>Given {@link Project} (default constructor) addBuildListener {@link AntClassLoader#AntClassLoader()}.</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addText(String)}
   */
  @Test
  @DisplayName("Test addText(String); given Project (default constructor) addBuildListener AntClassLoader(); then throw BuildException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.addText(String)"})
  void testAddText_givenProjectAddBuildListenerAntClassLoader_thenThrowBuildException() throws BuildException {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> configurationTask.addText("Text"));
  }

  /**
   * Test new {@link ConfigurationTask} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ConfigurationTask}
   */
  @Test
  @DisplayName("Test new ConfigurationTask (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConfigurationTask.<init>()"})
  void testNewConfigurationTask() {
    // Arrange and Act
    ConfigurationTask actualConfigurationTask = new ConfigurationTask();

    // Assert
    assertNull(actualConfigurationTask.getDescription());
    assertNull(actualConfigurationTask.getTaskName());
    assertNull(actualConfigurationTask.getTaskType());
    assertNull(actualConfigurationTask.getProject());
    assertNull(actualConfigurationTask.getOwningTarget());
  }
}
