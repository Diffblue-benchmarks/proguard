package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.ClassPath;

class ClassPathElementDiffblueTest {
  /**
   * Test {@link ClassPathElement#ClassPathElement(Project)}.
   *
   * <p>Method under test: {@link ClassPathElement#ClassPathElement(Project)}
   */
  @Test
  @DisplayName("Test new ClassPathElement(Project)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo() throws BuildException {
    // Arrange
    Project project = mock(Project.class);
    doNothing().when(project).log(Mockito.<String>any(), anyInt());
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.add(Path.systemBootClasspath);
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(project, atLeast(1)).getBaseDir();
    verify(project, atLeast(1)).log(Mockito.<String>any(), eq(3));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo2() throws BuildException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(mock(Project.class));
    classPathElement.setProject(new Project());
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName("Test appendClassPathEntriesTo(ClassPath, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo3() throws BuildException {
    // Arrange
    Project project = mock(Project.class);
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addExisting(new Path(new Project()));
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(project).getBaseDir();
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ClassPathElement#ClassPathElement(Project)} with {@link Project}.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); given ClassPathElement(Project) with Project")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenClassPathElementWithProject() {
    // Arrange
    Project project = mock(Project.class);
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
    ClassPathElement classPathElement = new ClassPathElement(project);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(project).getBaseDir();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ClassPathElement#ClassPathElement(Project)} with {@link Project} addDirset
   *       {@link DirSet#DirSet()}.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); given ClassPathElement(Project) with Project addDirset DirSet()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenClassPathElementWithProjectAddDirsetDirSet()
      throws BuildException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(mock(Project.class));
    classPathElement.setProject(new Project());
    classPathElement.addDirset(new DirSet());
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ClassPathElement#ClassPathElement(Project)} with {@link Project} addFilelist
   *       {@link FileList#FileList()}.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); given ClassPathElement(Project) with Project addFilelist FileList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenClassPathElementWithProjectAddFilelistFileList()
      throws BuildException {
    // Arrange
    Project project = mock(Project.class);
    doNothing().when(project).log(Mockito.<String>any(), anyInt());
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addFilelist(new FileList());
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(project, atLeast(1)).getBaseDir();
    verify(project, atLeast(1)).log(Mockito.<String>any(), eq(3));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project}
   *       (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); given ClassPathElement(Project) with project is Project (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenClassPathElementWithProjectIsProject() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Project} {@link Project#log(String, int)} does nothing.
   *   <li>When {@code true}.
   *   <li>Then calls {@link Project#log(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); given Project log(String, int) does nothing; when 'true'; then calls log(String, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_givenProjectLogDoesNothing_whenTrue_thenCallsLog()
      throws BuildException {
    // Arrange
    Project project = mock(Project.class);
    doNothing().when(project).log(Mockito.<String>any(), anyInt());
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);

    // Act and Assert
    assertThrows(
        BuildException.class,
        () -> classPathElement.appendClassPathEntriesTo(new ClassPath(), true));
    verify(project, atLeast(1)).getBaseDir();
    verify(project, atLeast(1)).log(Mockito.<String>any(), eq(3));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>Then {@link ClassPath} (default constructor) size is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); then ClassPath (default constructor) size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_thenClassPathSizeIsZero() {
    // Arrange
    ClassPathElement classPathElement = new ClassPathElement(new Project());
    ClassPath classPath = new ClassPath();

    // Act
    classPathElement.appendClassPathEntriesTo(classPath, false);

    // Assert that nothing has changed
    assertEquals(0, classPath.size());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Test {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then {@link ClassPath} (default constructor) size is six.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#appendClassPathEntriesTo(ClassPath, boolean)}
   */
  @Test
  @DisplayName(
      "Test appendClassPathEntriesTo(ClassPath, boolean); when 'false'; then ClassPath (default constructor) size is six")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassPathElement.appendClassPathEntriesTo(ClassPath, boolean)"})
  void testAppendClassPathEntriesTo_whenFalse_thenClassPathSizeIsSix() throws BuildException {
    // Arrange
    Project project = mock(Project.class);
    doNothing().when(project).log(Mockito.<String>any(), anyInt());
    when(project.getBaseDir())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    FileSet fs = mock(FileSet.class);
    when(fs.isFilesystemOnly()).thenReturn(true);

    ArrayList<Resource> resourceList = new ArrayList<>();
    Stream<Resource> streamResult = resourceList.stream();
    Mockito.<Stream<? extends Resource>>when(fs.stream()).thenReturn(streamResult);
    when(fs.getProject()).thenReturn(new Project());

    ClassPathElement classPathElement = new ClassPathElement(project);
    classPathElement.addExisting(Path.systemBootClasspath);
    classPathElement.addFileset(fs);
    ClassPath classPath = new ClassPath();

    // Act
    classPathElement.appendClassPathEntriesTo(classPath, false);

    // Assert
    verify(project, atLeast(1)).getBaseDir();
    verify(project, atLeast(1)).log(Mockito.<String>any(), eq(3));
    verify(fs, atLeast(1)).getProject();
    verify(fs).isFilesystemOnly();
    verify(fs).stream();
    assertEquals(6, classPath.size());
    assertFalse(classPath.isEmpty());
  }

  /**
   * Test {@link ClassPathElement#setFile(File)}.
   *
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project}
   *       (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#setFile(File)}
   */
  @Test
  @DisplayName(
      "Test setFile(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project}
   *       (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#setDir(File)}
   */
  @Test
  @DisplayName(
      "Test setDir(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Then {@link ClassPathElement#ClassPathElement(Project)} with project is {@link Project}
   *       (default constructor) size is one.
   * </ul>
   *
   * <p>Method under test: {@link ClassPathElement#setName(File)}
   */
  @Test
  @DisplayName(
      "Test setName(File); then ClassPathElement(Project) with project is Project (default constructor) size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
