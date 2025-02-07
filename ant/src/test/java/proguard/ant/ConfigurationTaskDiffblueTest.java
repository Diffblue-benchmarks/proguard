package proguard.ant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.List;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import proguard.ClassPath;
import proguard.ClassSpecification;
import proguard.Configuration;
import proguard.KeepClassSpecification;

public class ConfigurationTaskDiffblueTest {
  /**
   * Test {@link ConfigurationTask#appendTo(Configuration)}.
   * <p>
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.appendTo(proguard.Configuration)"})
  public void testAppendTo() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.appendTo(proguard.Configuration)"})
  public void testAppendTo_givenConfigurationTask() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.appendTo(proguard.Configuration)"})
  public void testAppendTo_thenConfigurationTaskConfigurationKeepDirectoriesEmpty() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.appendTo(proguard.Configuration)"})
  public void testAppendTo_thenConfigurationTaskConfigurationKeepSizeIsOne() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredInjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredInjar() {
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
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredInjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredInjar2() throws BuildException {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.setBaseDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
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
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Project} (default constructor) addDataTypeDefinition {@code 42} and {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredInjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredInjar_given42_whenProjectAddDataTypeDefinition42AndObject() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredInjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredInjar_givenAntClassLoader() {
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
   *   <li>Then {@link ConfigurationTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#programJars} size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredInjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredInjar_thenConfigurationTaskConfigurationProgramJarsSizeIsZero() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOutjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredOutjar_thenConfigurationTaskConfigurationProgramJarsSizeIsZero() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredLibraryjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredLibraryjar() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredLibraryjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredLibraryjar2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredLibraryjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredLibraryjar3() throws BuildException {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.setBaseDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
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
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link Project} (default constructor) addDataTypeDefinition {@code 42} and {@link Object}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredLibraryjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredLibraryjar_given42_whenProjectAddDataTypeDefinition42AndObject() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredLibraryjar(proguard.ant.ClassPathElement)"})
  public void testAddConfiguredLibraryjar_givenAntClassLoader() {
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
   * Test {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}.
   * <p>
   * Method under test: {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectory(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectory() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectory(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectory2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectory(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectory3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectories(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectories() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectories(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectories2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepdirectories(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepdirectories3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeep(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeep() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeep(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeep2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeep(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeep_thenConfigurationTaskConfigurationKeepFirstClassNameIsName() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeep(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeep_thenConfigurationTaskConfigurationKeepSizeIsTwo() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembers() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembers2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembers3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembers4() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembers() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembers2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembers3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembers(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembers4() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepnames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepnames() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepnames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepnames2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepnames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepnames3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepnames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepnames_thenConfigurationTaskConfigurationKeepSizeIsTwo() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembernames() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembernames2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembernames3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclassmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclassmembernames4() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembernames() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembernames2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembernames3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredKeepclasseswithmembernames(proguard.ant.KeepSpecificationElement)"})
  public void testAddConfiguredKeepclasseswithmembernames4() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredWhyareyoukeeping(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredWhyareyoukeeping() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredWhyareyoukeeping(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredWhyareyoukeeping2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredWhyareyoukeeping(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredWhyareyoukeeping3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredWhyareyoukeeping(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredWhyareyoukeeping_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenosideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenosideeffects() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenosideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenosideeffects2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenosideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenosideeffects3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenosideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenosideeffects_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalsideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalsideeffects() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalsideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalsideeffects2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalsideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalsideeffects3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalsideeffects(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalsideeffects_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoescapingparameters(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoescapingparameters() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoescapingparameters(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoescapingparameters2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoescapingparameters(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoescapingparameters3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoescapingparameters(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoescapingparameters_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalreturnvalues() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalreturnvalues2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalreturnvalues3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumenoexternalreturnvalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumenoexternalreturnvalues_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumevalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumevalues() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumevalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumevalues2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumevalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumevalues3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAssumevalues(proguard.ant.ClassSpecificationElement)"})
  public void testAddConfiguredAssumevalues_whenClassSpecificationElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimizations(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimizations() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimizations(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimizations2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimizations(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimizations3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimization(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimization() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimization(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimization2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredOptimization(proguard.ant.FilterElement)"})
  public void testAddConfiguredOptimization3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagename(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagename() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagename(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagename2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagename(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagename3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagenames() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagenames2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeeppackagenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeeppackagenames3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattributes(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattributes() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattributes(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattributes2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattributes(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattributes3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattribute(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattribute() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattribute(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattribute2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredKeepattribute(proguard.ant.FilterElement)"})
  public void testAddConfiguredKeepattribute3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredAdaptclassstrings(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptclassstrings() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredAdaptclassstrings(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptclassstrings2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredAdaptclassstrings(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptclassstrings3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilenames() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilenames2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilenames(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilenames3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilecontents(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilecontents() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilecontents(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilecontents2() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredAdaptresourcefilecontents(proguard.ant.FilterElement)"})
  public void testAddConfiguredAdaptresourcefilecontents3() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontnote(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontnote() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontnote(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontnote_thenConfigurationTaskConfigurationNoteEmpty() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontnote(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontnote_thenConfigurationTaskConfigurationNoteSizeIsOne() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontwarn(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontwarn() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontwarn(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontwarn_thenConfigurationTaskConfigurationWarnEmpty() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addConfiguredDontwarn(proguard.ant.FilterElement)"})
  public void testAddConfiguredDontwarn_thenConfigurationTaskConfigurationWarnSizeIsOne() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "void proguard.ant.ConfigurationTask.addConfiguredConfiguration(proguard.ant.ConfigurationElement)"})
  public void testAddConfiguredConfiguration_thenCallsAppendTo() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addText(java.lang.String)"})
  public void testAddText() throws BuildException {
    // Arrange
    Project project = new Project();
    project.setBaseDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addText(java.lang.String)"})
  public void testAddText_givenConfigurationTaskProjectIsProject_thenThrowBuildException() throws BuildException {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.addText(java.lang.String)"})
  public void testAddText_givenProjectAddBuildListenerAntClassLoader_thenThrowBuildException() throws BuildException {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ConfigurationTask.<init>()"})
  public void testNewConfigurationTask() {
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
