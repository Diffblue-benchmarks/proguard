package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.File;
import java.nio.file.Paths;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.input.InputHandler;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.Path;
import org.junit.jupiter.api.Test;
import proguard.ClassPath;

class ClassPathElementDiffblueTest {
  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo2() {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo3() {
    // Arrange
    Project project = new Project();
    Class<Object> typeClass = Object.class;
    project.addDataTypeDefinition("]", typeClass);
    project.addBuildListener(new AntClassLoader());
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo4() throws BuildException {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    project.addBuildListener(new AntClassLoader());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addFilelist(new FileList());

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo5() throws BuildException {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    project.addBuildListener(new AntClassLoader());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.add(new Path(new Project()));

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test:
   * {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  void testAppendClassPathEntriesTo6() {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    project.addBuildListener(new AntClassLoader());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addJavaRuntime();

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Method under test: {@link ClassPathElement#setFile(File)}
   */
  @Test
  void testSetFile() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#setFile(File)}
   */
  @Test
  void testSetFile2() {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act
    classPathElement.setFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#setDir(File)}
   */
  @Test
  void testSetDir() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#setDir(File)}
   */
  @Test
  void testSetDir2() {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act
    classPathElement.setDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#setName(File)}
   */
  @Test
  void testSetName() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setName(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#setName(File)}
   */
  @Test
  void testSetName2() {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act
    classPathElement.setName(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Method under test: {@link ClassPathElement#ClassPathElement(Project)}
   */
  @Test
  void testNewClassPathElement() {
    // Arrange
    Project project = new Project();

    // Act
    ClassPathElement actualClassPathElement = new ClassPathElement(project);

    // Assert
    Location location = actualClassPathElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualClassPathElement.getDescription());
    assertNull(actualClassPathElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertEquals(0, actualClassPathElement.size());
    assertFalse(actualClassPathElement.isReference());
    assertTrue(actualClassPathElement.isEmpty());
    assertSame(project, actualClassPathElement.getProject());
  }

  /**
   * Method under test: {@link ClassPathElement#ClassPathElement(Project)}
   */
  @Test
  void testNewClassPathElement2() {
    // Arrange
    Project project = new Project();
    project.setInputHandler(mock(InputHandler.class));

    // Act
    ClassPathElement actualClassPathElement = new ClassPathElement(project);

    // Assert
    Location location = actualClassPathElement.getLocation();
    assertNull(location.getFileName());
    assertNull(actualClassPathElement.getDescription());
    assertNull(actualClassPathElement.getRefid());
    assertEquals(0, location.getColumnNumber());
    assertEquals(0, location.getLineNumber());
    assertEquals(0, actualClassPathElement.size());
    assertFalse(actualClassPathElement.isReference());
    assertTrue(actualClassPathElement.isEmpty());
    assertSame(project, actualClassPathElement.getProject());
  }
}
