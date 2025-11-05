package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.nio.file.Paths;
import java.util.List;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.input.InputHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.ClassPath;
import proguard.ClassSpecification;
import proguard.Configuration;
import proguard.KeepClassSpecification;

class ConfigurationTaskDiffblueTest {
  /**
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  void testAppendTo() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keepDirectories);
    assertNull(configuration.keep);
  }

  /**
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  void testAppendTo2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.appendTo(mock(Configuration.class));

    // Assert
    Configuration configuration = configurationTask.configuration;
    assertNull(configuration.keepDirectories);
    assertNull(configuration.keep);
  }

  /**
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  void testAppendTo3() {
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
   * Method under test: {@link ConfigurationTask#appendTo(Configuration)}
   */
  @Test
  void testAppendTo4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.addConfiguredInjar(new ClassPathElement(new Project()));

    // Assert
    ClassPath classPath = configurationTask.configuration.programJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar5() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar6() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar7() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredInjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredInjar8() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOutjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredOutjar() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(new Project()));

    // Act
    configurationTask.addConfiguredLibraryjar(new ClassPathElement(new Project()));

    // Assert
    ClassPath classPath = configurationTask.configuration.libraryJars;
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar5() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar6() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar7() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredLibraryjar(ClassPathElement)}
   */
  @Test
  void testAddConfiguredLibraryjar8() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepdirectory() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepdirectory2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectory(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepdirectories() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepdirectories(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepdirectories2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepdirectory(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepdirectories(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepDirectories.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepdirectories(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeep2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
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
    assertEquals(getResult, keepClassSpecificationList.get(1));
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeep3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeep(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeep4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepclassmembers2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclassmembers(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    KeepClassSpecification getResult2 = keepClassSpecificationList.get(1);
    assertNull(getResult2.annotationType);
    assertNull(getResult.className);
    assertNull(getResult2.className);
    assertNull(getResult.comments);
    assertNull(getResult2.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult2.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult2.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult2.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult2.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult2.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult2.methodSpecifications);
    assertNull(getResult.condition);
    assertNull(getResult2.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult2.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult2.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult2.allowShrinking);
    assertFalse(getResult2.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult2.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult2.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertFalse(getResult2.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult2.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepclasseswithmembers2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclasseswithmembers(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    KeepClassSpecification getResult2 = keepClassSpecificationList.get(1);
    assertNull(getResult2.annotationType);
    assertNull(getResult.className);
    assertNull(getResult2.className);
    assertNull(getResult.comments);
    assertNull(getResult2.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult2.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult2.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult2.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult2.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult2.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult2.methodSpecifications);
    assertNull(getResult.condition);
    assertNull(getResult2.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult2.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult2.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult2.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult2.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertFalse(getResult2.markDescriptorClasses);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult2.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult2.markClasses);
    assertTrue(getResult2.markConditionally);
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembers(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepnames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepnames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    KeepClassSpecification getResult2 = keepClassSpecificationList.get(1);
    assertNull(getResult2.annotationType);
    assertNull(getResult.className);
    assertNull(getResult2.className);
    assertNull(getResult.comments);
    assertNull(getResult2.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult2.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult2.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult2.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult2.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult2.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult2.methodSpecifications);
    assertNull(getResult.condition);
    assertNull(getResult2.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult2.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult2.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult2.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult2.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertFalse(getResult2.markDescriptorClasses);
    assertTrue(getResult2.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult2.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult2.markClasses);
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepnames3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepnames(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepnames4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepclassmembernames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclassmembernames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    KeepClassSpecification getResult2 = keepClassSpecificationList.get(1);
    assertNull(getResult2.annotationType);
    assertNull(getResult.className);
    assertNull(getResult2.className);
    assertNull(getResult.comments);
    assertNull(getResult2.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult2.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult2.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult2.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult2.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult2.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult2.methodSpecifications);
    assertNull(getResult.condition);
    assertNull(getResult2.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult2.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult2.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult2.markClasses);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult2.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult2.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertFalse(getResult2.markDescriptorClasses);
    assertTrue(getResult2.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult2.markClassMembers);
    assertTrue(getResult.markClasses);
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclassmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
  void testAddConfiguredKeepclasseswithmembernames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeep(new KeepSpecificationElement());

    // Act
    configurationTask.addConfiguredKeepclasseswithmembernames(new KeepSpecificationElement());

    // Assert
    List<KeepClassSpecification> keepClassSpecificationList = configurationTask.configuration.keep;
    assertEquals(2, keepClassSpecificationList.size());
    KeepClassSpecification getResult = keepClassSpecificationList.get(0);
    assertNull(getResult.annotationType);
    KeepClassSpecification getResult2 = keepClassSpecificationList.get(1);
    assertNull(getResult2.annotationType);
    assertNull(getResult.className);
    assertNull(getResult2.className);
    assertNull(getResult.comments);
    assertNull(getResult2.comments);
    assertNull(getResult.extendsAnnotationType);
    assertNull(getResult2.extendsAnnotationType);
    assertNull(getResult.extendsClassName);
    assertNull(getResult2.extendsClassName);
    assertNull(getResult.memberComments);
    assertNull(getResult2.memberComments);
    assertNull(getResult.attributeNames);
    assertNull(getResult2.attributeNames);
    assertNull(getResult.fieldSpecifications);
    assertNull(getResult2.fieldSpecifications);
    assertNull(getResult.methodSpecifications);
    assertNull(getResult2.methodSpecifications);
    assertNull(getResult.condition);
    assertNull(getResult2.condition);
    assertEquals(0, getResult.requiredSetAccessFlags);
    assertEquals(0, getResult2.requiredSetAccessFlags);
    assertEquals(0, getResult.requiredUnsetAccessFlags);
    assertEquals(0, getResult2.requiredUnsetAccessFlags);
    assertFalse(getResult.allowObfuscation);
    assertFalse(getResult2.allowObfuscation);
    assertFalse(getResult.allowOptimization);
    assertFalse(getResult2.allowOptimization);
    assertFalse(getResult.allowShrinking);
    assertFalse(getResult.markCodeAttributes);
    assertFalse(getResult2.markCodeAttributes);
    assertFalse(getResult.markConditionally);
    assertFalse(getResult.markDescriptorClasses);
    assertFalse(getResult2.markDescriptorClasses);
    assertTrue(getResult2.allowShrinking);
    assertTrue(getResult.markClassMembers);
    assertTrue(getResult2.markClassMembers);
    assertTrue(getResult.markClasses);
    assertTrue(getResult2.markClasses);
    assertTrue(getResult2.markConditionally);
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepclasseswithmembernames(KeepSpecificationElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredWhyareyoukeeping() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredWhyareyoukeeping2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredWhyareyoukeeping3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredWhyareyoukeeping(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredWhyareyoukeeping4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenosideeffects() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenosideeffects2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenosideeffects3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenosideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenosideeffects4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalsideeffects() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalsideeffects2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalsideeffects3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalsideeffects(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalsideeffects4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoescapingparameters() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoescapingparameters2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoescapingparameters3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoescapingparameters(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoescapingparameters4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalreturnvalues() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalreturnvalues2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalreturnvalues3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumenoexternalreturnvalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumenoexternalreturnvalues4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumevalues() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumevalues2() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumevalues3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAssumevalues(ClassSpecificationElement)}
   */
  @Test
  void testAddConfiguredAssumevalues4() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
  void testAddConfiguredOptimizations() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
  void testAddConfiguredOptimizations2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Act
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimizations(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
  void testAddConfiguredOptimization() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredOptimization(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
  void testAddConfiguredOptimization2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredOptimizations(new FilterElement());

    // Act
    configurationTask.addConfiguredOptimization(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.optimizations.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredOptimization(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
  void testAddConfiguredKeeppackagename() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
  void testAddConfiguredKeeppackagename2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Act
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagename(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
  void testAddConfiguredKeeppackagenames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeeppackagenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
  void testAddConfiguredKeeppackagenames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeeppackagename(new FilterElement());

    // Act
    configurationTask.addConfiguredKeeppackagenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepPackageNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeeppackagenames(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepattributes() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepattributes2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattributes(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepattribute() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredKeepattribute(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
  void testAddConfiguredKeepattribute2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredKeepattributes(new FilterElement());

    // Act
    configurationTask.addConfiguredKeepattribute(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.keepAttributes.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredKeepattribute(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptclassstrings() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptClassStrings.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptclassstrings2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptclassstrings(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptClassStrings.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptclassstrings(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptresourcefilenames() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptresourcefilenames2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptresourcefilenames(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileNames.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilenames(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptresourcefilecontents() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileContents.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
  void testAddConfiguredAdaptresourcefilecontents2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Act
    configurationTask.addConfiguredAdaptresourcefilecontents(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.adaptResourceFileContents.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredAdaptresourcefilecontents(FilterElement)}
   */
  @Test
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  void testAddConfiguredDontnote() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.note.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  void testAddConfiguredDontnote2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Act
    configurationTask.addConfiguredDontnote(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.note.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontnote(FilterElement)}
   */
  @Test
  void testAddConfiguredDontnote3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  void testAddConfiguredDontwarn() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();

    // Act
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.warn.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  void testAddConfiguredDontwarn2() {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Act
    configurationTask.addConfiguredDontwarn(new FilterElement());

    // Assert
    assertTrue(configurationTask.configuration.warn.isEmpty());
  }

  /**
   * Method under test:
   * {@link ConfigurationTask#addConfiguredDontwarn(FilterElement)}
   */
  @Test
  void testAddConfiguredDontwarn3() {
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
   * Method under test:
   * {@link ConfigurationTask#addConfiguredConfiguration(ConfigurationElement)}
   */
  @Test
  void testAddConfiguredConfiguration() {
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
   * Method under test: {@link ConfigurationTask#addText(String)}
   */
  @Test
  void testAddText() throws BuildException {
    // Arrange
    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.setProject(new Project());

    // Act and Assert
    assertThrows(BuildException.class, () -> configurationTask.addText("Text"));
  }

  /**
   * Method under test: {@link ConfigurationTask#addText(String)}
   */
  @Test
  void testAddText2() throws BuildException {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    ConfigurationTask configurationTask = new ConfigurationTask();
    configurationTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> configurationTask.addText("Text"));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ConfigurationTask}
   */
  @Test
  void testNewConfigurationTask() {
    // Arrange and Act
    ConfigurationTask actualConfigurationTask = new ConfigurationTask();

    // Assert
    RuntimeConfigurable runtimeConfigurableWrapper = actualConfigurationTask.getRuntimeConfigurableWrapper();
    assertEquals("", runtimeConfigurableWrapper.getText().toString());
    Configuration configuration = actualConfigurationTask.configuration;
    assertNull(configuration.applyMapping);
    assertNull(configuration.dump);
    assertNull(configuration.extraJar);
    assertNull(configuration.printConfiguration);
    assertNull(configuration.printMapping);
    assertNull(configuration.printSeeds);
    assertNull(configuration.printUsage);
    Location location = actualConfigurationTask.getLocation();
    assertNull(location.getFileName());
    assertNull(actualConfigurationTask.getDescription());
    assertNull(runtimeConfigurableWrapper.getElementTag());
    assertNull(runtimeConfigurableWrapper.getId());
    assertNull(runtimeConfigurableWrapper.getPolyType());
    assertNull(actualConfigurationTask.getTaskName());
    assertNull(actualConfigurationTask.getTaskType());
    assertNull(configuration.flattenPackageHierarchy);
    assertNull(configuration.newSourceFileAttribute);
    assertNull(configuration.repackageClasses);
    assertNull(configuration.classObfuscationDictionary);
    assertNull(configuration.obfuscationDictionary);
    assertNull(configuration.packageObfuscationDictionary);
    assertNull(configuration.keepDirectories);
    assertNull(configuration.keyStores);
    assertNull(configuration.adaptClassStrings);
    assertNull(configuration.adaptResourceFileContents);
    assertNull(configuration.adaptResourceFileNames);
    assertNull(configuration.dontCompress);
    assertNull(configuration.keepAttributes);
    assertNull(configuration.keepPackageNames);
    assertNull(configuration.keyAliases);
    assertNull(configuration.keyPasswords);
    assertNull(configuration.keyStorePasswords);
    assertNull(configuration.note);
    assertNull(configuration.optimizations);
    assertNull(configuration.warn);
    assertNull(configuration.assumeNoEscapingParameters);
    assertNull(configuration.assumeNoExternalReturnValues);
    assertNull(configuration.assumeNoExternalSideEffects);
    assertNull(configuration.assumeNoSideEffects);
    assertNull(configuration.assumeValues);
    assertNull(configuration.whyAreYouKeeping);
    assertNull(configuration.keep);
    assertNull(actualConfigurationTask.getProject());
    assertNull(actualConfigurationTask.getOwningTarget());
    assertNull(runtimeConfigurableWrapper.getAttributes());
    assertNull(configuration.libraryJars);
    assertNull(configuration.programJars);
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertEquals(0, configuration.targetClassVersion);
    assertEquals(0L, configuration.lastModified);
    assertEquals(1, configuration.optimizationPasses);
    assertEquals(1, configuration.zipAlign);
    assertFalse(configuration.addConfigurationDebugging);
    assertFalse(configuration.allowAccessModification);
    assertFalse(configuration.android);
    assertFalse(configuration.backport);
    assertFalse(configuration.dontProcessKotlinMetadata);
    assertFalse(configuration.ignoreWarnings);
    assertFalse(configuration.keepKotlinMetadata);
    assertFalse(configuration.keepParameterNames);
    assertFalse(configuration.mergeInterfacesAggressively);
    assertFalse(configuration.microEdition);
    assertFalse(configuration.overloadAggressively);
    assertFalse(configuration.skipNonPublicLibraryClasses);
    assertFalse(configuration.useUniqueClassMemberNames);
    assertFalse(configuration.verbose);
    assertTrue(runtimeConfigurableWrapper.getAttributeMap().isEmpty());
    assertTrue(configuration.enableKotlinAsserter);
    assertTrue(configuration.obfuscate);
    assertTrue(configuration.optimize);
    assertTrue(configuration.optimizeConservatively);
    assertTrue(configuration.preverify);
    assertTrue(configuration.shrink);
    assertTrue(configuration.skipNonPublicLibraryClassMembers);
    assertTrue(configuration.useMixedCaseClassNames);
    assertSame(actualConfigurationTask, runtimeConfigurableWrapper.getProxy());
  }
}
