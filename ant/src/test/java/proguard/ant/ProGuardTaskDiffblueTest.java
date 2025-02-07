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
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import proguard.ClassPath;
import proguard.Configuration;

public class ProGuardTaskDiffblueTest {
  /**
   * Test {@link ProGuardTask#setConfiguration(File)}.
   * <ul>
   *   <li>Given {@code Object}.</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setConfiguration(java.io.File)"})
  public void testSetConfiguration_givenJavaLangObject_thenThrowBuildException() throws BuildException {
    // Arrange
    Project project = new Project();
    Class<Object> typeClass = Object.class;
    project.addDataTypeDefinition("42", typeClass);
    project.addBuildListener(new AntClassLoader());

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class,
        () -> proGuardTask.setConfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ProGuardTask#setConfiguration(File)}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor) Project is {@link Project} (default constructor).</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setConfiguration(java.io.File)"})
  public void testSetConfiguration_givenProGuardTaskProjectIsProject_thenThrowBuildException() throws BuildException {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.setProject(new Project());

    // Act and Assert
    assertThrows(BuildException.class,
        () -> proGuardTask.setConfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ProGuardTask#setConfiguration(File)}.
   * <ul>
   *   <li>Given {@link Project} (default constructor) addBuildListener {@link AntClassLoader#AntClassLoader()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setConfiguration(java.io.File)"})
  public void testSetConfiguration_givenProjectAddBuildListenerAntClassLoader() throws BuildException {
    // Arrange
    Project project = new Project();
    project.addBuildListener(new AntClassLoader());

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class,
        () -> proGuardTask.setConfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ProGuardTask#setConfiguration(File)}.
   * <ul>
   *   <li>Given {@link Project} (default constructor) addTarget {@code 42} and {@link Target#Target()}.</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setConfiguration(java.io.File)"})
  public void testSetConfiguration_givenProjectAddTarget42AndTarget_thenThrowBuildException() throws BuildException {
    // Arrange
    Project project = new Project();
    project.addTarget("42", new Target());
    project.addBuildListener(new AntClassLoader());

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.setProject(project);

    // Act and Assert
    assertThrows(BuildException.class,
        () -> proGuardTask.setConfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link ProGuardTask#setOutjar(String)}.
   * <p>
   * Method under test: {@link ProGuardTask#setOutjar(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setOutjar(java.lang.String)"})
  public void testSetOutjar() {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).setOutjar("Parameters"));
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 5}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3211264}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when5_thenProGuardTaskConfigurationTargetClassVersionIs3211264() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("5");

    // Assert
    assertEquals(3211264, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 6}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3276800}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when6_thenProGuardTaskConfigurationTargetClassVersionIs3276800() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("6");

    // Assert
    assertEquals(3276800, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 7}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3342336}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when7_thenProGuardTaskConfigurationTargetClassVersionIs3342336() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("7");

    // Assert
    assertEquals(3342336, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.0}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 2949123}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when10_thenProGuardTaskConfigurationTargetClassVersionIs2949123() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.0");

    // Assert
    assertEquals(2949123, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.1}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 2949123}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when11_thenProGuardTaskConfigurationTargetClassVersionIs2949123() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.1");

    // Assert
    assertEquals(2949123, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.2}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3014656}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when12_thenProGuardTaskConfigurationTargetClassVersionIs3014656() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.2");

    // Assert
    assertEquals(3014656, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.3}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3080192}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when13_thenProGuardTaskConfigurationTargetClassVersionIs3080192() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.3");

    // Assert
    assertEquals(3080192, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.4}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3145728}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when14_thenProGuardTaskConfigurationTargetClassVersionIs3145728() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.4");

    // Assert
    assertEquals(3145728, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.5}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3211264}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when15_thenProGuardTaskConfigurationTargetClassVersionIs3211264() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.5");

    // Assert
    assertEquals(3211264, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code 1.6}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#targetClassVersion} is {@code 3276800}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_when16_thenProGuardTaskConfigurationTargetClassVersionIs3276800() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.6");

    // Assert
    assertEquals(3276800, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Test {@link ProGuardTask#setTarget(String)}.
   * <ul>
   *   <li>When {@code Target}.</li>
   *   <li>Then throw {@link BuildException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setTarget(java.lang.String)"})
  public void testSetTarget_whenTarget_thenThrowBuildException() {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).setTarget("Target"));
  }

  /**
   * Test {@link ProGuardTask#setForceprocessing(boolean)}.
   * <ul>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#lastModified} is {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setForceprocessing(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setForceprocessing(boolean)"})
  public void testSetForceprocessing_thenProGuardTaskConfigurationLastModifiedIsMax_value() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setForceprocessing(true);

    // Assert
    assertEquals(Long.MAX_VALUE, proGuardTask.configuration.lastModified);
  }

  /**
   * Test {@link ProGuardTask#setForceprocessing(boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#lastModified} is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setForceprocessing(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setForceprocessing(boolean)"})
  public void testSetForceprocessing_whenFalse_thenProGuardTaskConfigurationLastModifiedIsZero() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setForceprocessing(false);

    // Assert that nothing has changed
    assertEquals(0L, proGuardTask.configuration.lastModified);
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintseeds(printSeeds);

    // Assert
    assertSame(printSeeds, proGuardTask.configuration.printSeeds);
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#FALSE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsFalseToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintseeds(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code no} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsNoToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code off} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsOffToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code on} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsOnToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "on").toFile());

    // Assert
    File file = proGuardTask.configuration.printSeeds;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#TRUE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsTrueToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintseeds(Paths.get(property, Boolean.TRUE.toString()).toFile());

    // Assert
    File file = proGuardTask.configuration.printSeeds;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintseeds(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code yes} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintseeds(java.io.File)"})
  public void testSetPrintseeds_whenPropertyIsJavaIoTmpdirIsYesToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "yes").toFile());

    // Assert
    File file = proGuardTask.configuration.printSeeds;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintusage(printUsage);

    // Assert
    assertSame(printUsage, proGuardTask.configuration.printUsage);
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#FALSE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsFalseToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintusage(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code no} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsNoToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code off} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsOffToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code on} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsOnToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "on").toFile());

    // Assert
    File file = proGuardTask.configuration.printUsage;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#TRUE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsTrueToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintusage(Paths.get(property, Boolean.TRUE.toString()).toFile());

    // Assert
    File file = proGuardTask.configuration.printUsage;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintusage(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code yes} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintusage(java.io.File)"})
  public void testSetPrintusage_whenPropertyIsJavaIoTmpdirIsYesToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "yes").toFile());

    // Assert
    File file = proGuardTask.configuration.printUsage;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintmapping(printMapping);

    // Assert
    assertSame(printMapping, proGuardTask.configuration.printMapping);
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#FALSE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsFalseToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintmapping(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code no} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsNoToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code off} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsOffToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code on} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsOnToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "on").toFile());

    // Assert
    File file = proGuardTask.configuration.printMapping;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#TRUE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsTrueToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintmapping(Paths.get(property, Boolean.TRUE.toString()).toFile());

    // Assert
    File file = proGuardTask.configuration.printMapping;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintmapping(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code yes} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintmapping(java.io.File)"})
  public void testSetPrintmapping_whenPropertyIsJavaIoTmpdirIsYesToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "yes").toFile());

    // Assert
    File file = proGuardTask.configuration.printMapping;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setApplymapping(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setApplymapping(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setApplymapping(java.io.File)"})
  public void testSetApplymapping() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setApplymapping(applyMapping);

    // Assert
    assertSame(applyMapping, proGuardTask.configuration.applyMapping);
  }

  /**
   * Test {@link ProGuardTask#setObfuscationdictionary(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setObfuscationdictionary(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setObfuscationdictionary(java.io.File)"})
  public void testSetObfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setObfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, proGuardTask.configuration.obfuscationDictionary.toString());
  }

  /**
   * Test {@link ProGuardTask#setClassobfuscationdictionary(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setClassobfuscationdictionary(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setClassobfuscationdictionary(java.io.File)"})
  public void testSetClassobfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setClassobfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, proGuardTask.configuration.classObfuscationDictionary.toString());
  }

  /**
   * Test {@link ProGuardTask#setPackageobfuscationdictionary(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setPackageobfuscationdictionary(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPackageobfuscationdictionary(java.io.File)"})
  public void testSetPackageobfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPackageobfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString().concat(File.separator));
    assertEquals(expectedToStringResult, proGuardTask.configuration.packageObfuscationDictionary.toString());
  }

  /**
   * Test {@link ProGuardTask#setFlattenpackagehierarchy(String)}.
   * <p>
   * Method under test: {@link ProGuardTask#setFlattenpackagehierarchy(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setFlattenpackagehierarchy(java.lang.String)"})
  public void testSetFlattenpackagehierarchy() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setFlattenpackagehierarchy("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.flattenPackageHierarchy);
  }

  /**
   * Test {@link ProGuardTask#setRepackageclasses(String)}.
   * <p>
   * Method under test: {@link ProGuardTask#setRepackageclasses(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setRepackageclasses(java.lang.String)"})
  public void testSetRepackageclasses() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setRepackageclasses("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.repackageClasses);
  }

  /**
   * Test {@link ProGuardTask#setDefaultpackage(String)}.
   * <p>
   * Method under test: {@link ProGuardTask#setDefaultpackage(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDefaultpackage(java.lang.String)"})
  public void testSetDefaultpackage() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDefaultpackage("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.repackageClasses);
  }

  /**
   * Test {@link ProGuardTask#setNote(boolean)}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor).</li>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setNote(boolean)"})
  public void testSetNote_givenProGuardTask_whenFalse_thenProGuardTaskConfigurationNoteEmpty() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setNote(false);

    // Assert
    assertTrue(proGuardTask.configuration.note.isEmpty());
  }

  /**
   * Test {@link ProGuardTask#setNote(boolean)}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor).</li>
   *   <li>When {@code true}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setNote(boolean)"})
  public void testSetNote_givenProGuardTask_whenTrue_thenProGuardTaskConfigurationNoteIsNull() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setNote(true);

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.note);
  }

  /**
   * Test {@link ProGuardTask#setNote(boolean)}.
   * <ul>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setNote(boolean)"})
  public void testSetNote_thenProGuardTaskConfigurationNoteIsNull() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontnote(new FilterElement());

    // Act
    proGuardTask.setNote(true);

    // Assert
    assertNull(proGuardTask.configuration.note);
  }

  /**
   * Test {@link ProGuardTask#setNote(boolean)}.
   * <ul>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#note} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setNote(boolean)"})
  public void testSetNote_thenProGuardTaskConfigurationNoteSizeIsOne() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontnote(filterElement);

    // Act
    proGuardTask.setNote(true);

    // Assert that nothing has changed
    assertEquals(1, proGuardTask.configuration.note.size());
  }

  /**
   * Test {@link ProGuardTask#setWarn(boolean)}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor).</li>
   *   <li>When {@code false}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setWarn(boolean)"})
  public void testSetWarn_givenProGuardTask_whenFalse_thenProGuardTaskConfigurationWarnEmpty() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setWarn(false);

    // Assert
    assertTrue(proGuardTask.configuration.warn.isEmpty());
  }

  /**
   * Test {@link ProGuardTask#setWarn(boolean)}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor).</li>
   *   <li>When {@code true}.</li>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setWarn(boolean)"})
  public void testSetWarn_givenProGuardTask_whenTrue_thenProGuardTaskConfigurationWarnIsNull() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setWarn(true);

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.warn);
  }

  /**
   * Test {@link ProGuardTask#setWarn(boolean)}.
   * <ul>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setWarn(boolean)"})
  public void testSetWarn_thenProGuardTaskConfigurationWarnIsNull() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontwarn(new FilterElement());

    // Act
    proGuardTask.setWarn(true);

    // Assert
    assertNull(proGuardTask.configuration.warn);
  }

  /**
   * Test {@link ProGuardTask#setWarn(boolean)}.
   * <ul>
   *   <li>Then {@link ProGuardTask} (default constructor) {@link ConfigurationTask#configuration} {@link Configuration#warn} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setWarn(boolean)"})
  public void testSetWarn_thenProGuardTaskConfigurationWarnSizeIsOne() {
    // Arrange
    FilterElement filterElement = new FilterElement();
    filterElement.setName("Name");

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontwarn(filterElement);

    // Act
    proGuardTask.setWarn(true);

    // Assert that nothing has changed
    assertEquals(1, proGuardTask.configuration.warn.size());
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintconfiguration(printConfiguration);

    // Assert
    assertSame(printConfiguration, proGuardTask.configuration.printConfiguration);
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#FALSE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsFalseToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code no} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsNoToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code off} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsOffToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code on} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsOnToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "on").toFile());

    // Assert
    File file = proGuardTask.configuration.printConfiguration;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#TRUE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsTrueToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(property, Boolean.TRUE.toString()).toFile());

    // Assert
    File file = proGuardTask.configuration.printConfiguration;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setPrintconfiguration(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code yes} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setPrintconfiguration(java.io.File)"})
  public void testSetPrintconfiguration_whenPropertyIsJavaIoTmpdirIsYesToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "yes").toFile());

    // Assert
    File file = proGuardTask.configuration.printConfiguration;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setDump(dump);

    // Assert
    assertSame(dump, proGuardTask.configuration.dump);
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#FALSE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsFalseToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setDump(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code no} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsNoToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code off} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsOffToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code on} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsOnToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "on").toFile());

    // Assert
    File file = proGuardTask.configuration.dump;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@link Boolean#TRUE} toString toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsTrueToStringToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setDump(Paths.get(property, Boolean.TRUE.toString()).toFile());

    // Assert
    File file = proGuardTask.configuration.dump;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#setDump(File)}.
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code yes} toFile.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.setDump(java.io.File)"})
  public void testSetDump_whenPropertyIsJavaIoTmpdirIsYesToFile() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "yes").toFile());

    // Assert
    File file = proGuardTask.configuration.dump;
    assertEquals("", file.getName());
    assertFalse(file.isAbsolute());
  }

  /**
   * Test {@link ProGuardTask#execute()}.
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#adaptClassStrings} is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.execute()"})
  public void testExecute_givenConfigurationAdaptClassStringsIsArrayList()
      throws MalformedURLException, BuildException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "1.99*";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.appendTo(configuration);

    // Act and Assert
    assertThrows(BuildException.class, () -> proGuardTask.execute());
  }

  /**
   * Test {@link ProGuardTask#execute()}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.execute()"})
  public void testExecute_givenProGuardTask() throws BuildException {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).execute());
  }

  /**
   * Test {@link ProGuardTask#execute()}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor) addConfiguredKeep {@link KeepSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.execute()"})
  public void testExecute_givenProGuardTaskAddConfiguredKeepKeepSpecificationElement()
      throws MalformedURLException, BuildException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "1.99*";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredKeep(new KeepSpecificationElement());
    proGuardTask.appendTo(configuration);

    // Act and Assert
    assertThrows(BuildException.class, () -> proGuardTask.execute());
  }

  /**
   * Test {@link ProGuardTask#execute()}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor) addConfiguredKeep {@link KeepSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.execute()"})
  public void testExecute_givenProGuardTaskAddConfiguredKeepKeepSpecificationElement2()
      throws MalformedURLException, BuildException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "1.99*";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredKeep(new KeepSpecificationElement());
    proGuardTask.addConfiguredKeep(new KeepSpecificationElement());
    proGuardTask.appendTo(configuration);

    // Act and Assert
    assertThrows(BuildException.class, () -> proGuardTask.execute());
  }

  /**
   * Test {@link ProGuardTask#execute()}.
   * <ul>
   *   <li>Given {@link ProGuardTask} (default constructor) addConfiguredKeepnames {@link KeepSpecificationElement} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.execute()"})
  public void testExecute_givenProGuardTaskAddConfiguredKeepnamesKeepSpecificationElement()
      throws MalformedURLException, BuildException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "1.99*";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;

    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredKeepnames(new KeepSpecificationElement());
    proGuardTask.appendTo(configuration);

    // Act and Assert
    assertThrows(BuildException.class, () -> proGuardTask.execute());
  }

  /**
   * Test new {@link ProGuardTask} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ProGuardTask}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.ant.ProGuardTask.<init>()"})
  public void testNewProGuardTask() {
    // Arrange and Act
    ProGuardTask actualProGuardTask = new ProGuardTask();

    // Assert
    assertNull(actualProGuardTask.getDescription());
    assertNull(actualProGuardTask.getTaskName());
    assertNull(actualProGuardTask.getTaskType());
    assertNull(actualProGuardTask.getProject());
    assertNull(actualProGuardTask.getOwningTarget());
  }
}
