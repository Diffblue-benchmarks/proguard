package proguard.ant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.Path;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import proguard.ClassPath;

public class ClassPathElementDiffblueTest {
  /**
   * Test {@link ClassPathElement#ClassPathElement(Project)}.
   * <p>
   * Method under test: {@link ClassPathElement#ClassPathElement(Project)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.<init>(org.apache.tools.ant.Project)"})
  public void testNewClassPathElement() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo() throws BuildException {
    // Arrange
    Project project = new Project();
    project.setBaseDir(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    project.addBuildListener(new AntClassLoader());
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo2() throws BuildException {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo3() throws BuildException {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    classPathElement.add(new Path(new Project()));

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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo_givenJavaLangObject_thenThrowBuildException() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo_givenProjectAddBuildListenerAntClassLoader() {
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
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.appendClassPathEntriesTo(proguard.ClassPath, boolean)"})
  public void testAppendClassPathEntriesTo_thenThrowBuildException() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act and Assert
    assertThrows(BuildException.class, () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.setFile(java.io.File)"})
  public void testSetFile_thenClassPathElementWithProjectIsProjectSizeIsOne() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.setDir(java.io.File)"})
  public void testSetDir_thenClassPathElementWithProjectIsProjectSizeIsOne() {
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ClassPathElement.setName(java.io.File)"})
  public void testSetName_thenClassPathElementWithProjectIsProjectSizeIsOne() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act
    classPathElement.setName(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertEquals(1, classPathElement.size());
    assertFalse(classPathElement.isEmpty());
  }
}
