package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileList.FileName;
import org.apache.tools.ant.types.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.ClassPath;

class ClassPathElementDiffblueTest {
  /**
   * Test {@link ClassPathElement#ClassPathElement(Project)}.
   * <p>
   * Method under test: {@link ClassPathElement#ClassPathElement(Project)}
   */
  @Test
  @DisplayName("Test new ClassPathElement(Project)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.<init>(Project)"})
  void testNewClassPathElement() {
    // Arrange
    Project project = new Project();

    // Act
    ClassPathElement actualClassPathElement = new ClassPathElement(project);

    // Assert
    assertNull(actualClassPathElement.getDescription());
    assertNull(actualClassPathElement.getRefid());
    assertEquals(0, actualClassPathElement.size());
    assertFalse(actualClassPathElement.isReference());
    assertTrue(actualClassPathElement.isEmpty());
    assertSame(project, actualClassPathElement.getProject());
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo() throws BuildException {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addFilelist(new FileList());

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo2() throws BuildException {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.add(new Path(new Project()));

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo3() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addJavaRuntime();

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean); given 'java.lang.Object'; then throw BuildException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenJavaLangObject_thenThrowBuildException() {
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
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <ul>
   *   <li>Given {@link Project} (default constructor) addBuildListener {@link AntClassLoader#AntClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean); given Project (default constructor) addBuildListener AntClassLoader()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenProjectAddBuildListenerAntClassLoader() {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <ul>
   *   <li>Then {@link ClassPath} (default constructor) size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean); then ClassPath (default constructor) size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_thenClassPathSizeIsOne() throws BuildException {
    // Arrange
    FileName name = new FileName();
    name.setName("The <outjar> element must specify exactly one file or directory [");

    FileList fl = new FileList();
    fl.addConfiguredFile(name);

    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.addFilelist(fl);
    ClassPath classPath = new ClassPath();

    // Act
    classPathElement.appendClassPathEntriesTo(classPath, true);

    // Assert
    assertEquals(1, classPath.size());
    assertFalse(classPath.isEmpty());
    assertTrue(classPath.hasOutput());
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <ul>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean); then throw BuildException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_thenThrowBuildException() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ClassPath} (default constructor) size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean); when 'false'; then ClassPath (default constructor) size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_whenFalse_thenClassPathSizeIsZero() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    ClassPath classPath = new ClassPath();

    // Act
    classPathElement.appendClassPathEntriesTo(classPath, false);

    // Assert that nothing has changed
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ClassPathElement#setFile(File)}.
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project} (default constructor) size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#setFile(File)}
   */
  @Test
  @DisplayName("Test setFile(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.setFile(File)"})
  void testSetFile_thenClassPathElementWithProjectIsProjectSizeIsOne() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Test {@link ClassPathElement#setDir(File)}.
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project} (default constructor) size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#setDir(File)}
   */
  @Test
  @DisplayName("Test setDir(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.setDir(File)"})
  void testSetDir_thenClassPathElementWithProjectIsProjectSizeIsOne() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }

  /**
   * Test {@link ClassPathElement#setName(File)}.
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project} (default constructor) size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#setName(File)}
   */
  @Test
  @DisplayName("Test setName(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassPathElement.setName(File)"})
  void testSetName_thenClassPathElementWithProjectIsProjectSizeIsOne() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setName(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }
}
