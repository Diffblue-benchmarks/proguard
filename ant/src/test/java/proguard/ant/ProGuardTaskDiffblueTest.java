package proguard.ant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.junit.jupiter.api.Test;
import proguard.ClassPath;
import proguard.Configuration;

class ProGuardTaskDiffblueTest {
  /**
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  void testSetConfiguration() throws BuildException {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.setProject(new Project());

    // Act and Assert
    assertThrows(BuildException.class,
        () -> proGuardTask.setConfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  void testSetConfiguration2() throws BuildException {
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
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  void testSetConfiguration3() throws BuildException {
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
   * Method under test: {@link ProGuardTask#setConfiguration(File)}
   */
  @Test
  void testSetConfiguration4() throws BuildException {
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
   * Method under test: {@link ProGuardTask#setOutjar(String)}
   */
  @Test
  void testSetOutjar() {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).setOutjar("Parameters"));
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget() {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).setTarget("Target"));
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.0");

    // Assert
    assertEquals(2949123, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.1");

    // Assert
    assertEquals(2949123, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.2");

    // Assert
    assertEquals(3014656, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget5() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.3");

    // Assert
    assertEquals(3080192, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget6() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.4");

    // Assert
    assertEquals(3145728, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget7() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("5");

    // Assert
    assertEquals(3211264, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget8() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.5");

    // Assert
    assertEquals(3211264, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget9() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("6");

    // Assert
    assertEquals(3276800, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget10() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("1.6");

    // Assert
    assertEquals(3276800, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setTarget(String)}
   */
  @Test
  void testSetTarget11() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setTarget("7");

    // Assert
    assertEquals(3342336, proGuardTask.configuration.targetClassVersion);
  }

  /**
   * Method under test: {@link ProGuardTask#setForceprocessing(boolean)}
   */
  @Test
  void testSetForceprocessing() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setForceprocessing(true);

    // Assert
    assertEquals(Long.MAX_VALUE, proGuardTask.configuration.lastModified);
  }

  /**
   * Method under test: {@link ProGuardTask#setForceprocessing(boolean)}
   */
  @Test
  void testSetForceprocessing2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setForceprocessing(false);

    // Assert
    assertEquals(0L, proGuardTask.configuration.lastModified);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printSeeds = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintseeds(printSeeds);

    // Assert
    assertSame(printSeeds, proGuardTask.configuration.printSeeds);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintseeds(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintseeds(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printSeeds);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds5() {
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
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds6() {
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
   * Method under test: {@link ProGuardTask#setPrintseeds(File)}
   */
  @Test
  void testSetPrintseeds7() {
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
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printUsage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintusage(printUsage);

    // Assert
    assertSame(printUsage, proGuardTask.configuration.printUsage);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintusage(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintusage(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printUsage);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage5() {
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
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage6() {
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
   * Method under test: {@link ProGuardTask#setPrintusage(File)}
   */
  @Test
  void testSetPrintusage7() {
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
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintmapping(printMapping);

    // Assert
    assertSame(printMapping, proGuardTask.configuration.printMapping);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintmapping(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintmapping(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printMapping);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping5() {
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
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping6() {
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
   * Method under test: {@link ProGuardTask#setPrintmapping(File)}
   */
  @Test
  void testSetPrintmapping7() {
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
   * Method under test: {@link ProGuardTask#setApplymapping(File)}
   */
  @Test
  void testSetApplymapping() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File applyMapping = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setApplymapping(applyMapping);

    // Assert
    assertSame(applyMapping, proGuardTask.configuration.applyMapping);
  }

  /**
   * Method under test: {@link ProGuardTask#setObfuscationdictionary(File)}
   */
  @Test
  void testSetObfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setObfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString());
    assertEquals(expectedToStringResult, proGuardTask.configuration.obfuscationDictionary.toString());
  }

  /**
   * Method under test: {@link ProGuardTask#setClassobfuscationdictionary(File)}
   */
  @Test
  void testSetClassobfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setClassobfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString());
    assertEquals(expectedToStringResult, proGuardTask.configuration.classObfuscationDictionary.toString());
  }

  /**
   * Method under test: {@link ProGuardTask#setPackageobfuscationdictionary(File)}
   */
  @Test
  void testSetPackageobfuscationdictionary() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPackageobfuscationdictionary(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    String expectedToStringResult = String.join("", "file:",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString());
    assertEquals(expectedToStringResult, proGuardTask.configuration.packageObfuscationDictionary.toString());
  }

  /**
   * Method under test: {@link ProGuardTask#setFlattenpackagehierarchy(String)}
   */
  @Test
  void testSetFlattenpackagehierarchy() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setFlattenpackagehierarchy("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.flattenPackageHierarchy);
  }

  /**
   * Method under test: {@link ProGuardTask#setRepackageclasses(String)}
   */
  @Test
  void testSetRepackageclasses() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setRepackageclasses("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.repackageClasses);
  }

  /**
   * Method under test: {@link ProGuardTask#setDefaultpackage(String)}
   */
  @Test
  void testSetDefaultpackage() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDefaultpackage("java.text");

    // Assert
    assertEquals("java/text", proGuardTask.configuration.repackageClasses);
  }

  /**
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  void testSetNote() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setNote(true);

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.note);
  }

  /**
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  void testSetNote2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontnote(new FilterElement());

    // Act
    proGuardTask.setNote(true);

    // Assert
    assertNull(proGuardTask.configuration.note);
  }

  /**
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  void testSetNote3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setNote(false);

    // Assert
    assertTrue(proGuardTask.configuration.note.isEmpty());
  }

  /**
   * Method under test: {@link ProGuardTask#setNote(boolean)}
   */
  @Test
  void testSetNote4() {
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
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  void testSetWarn() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setWarn(true);

    // Assert that nothing has changed
    assertNull(proGuardTask.configuration.warn);
  }

  /**
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  void testSetWarn2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    proGuardTask.addConfiguredDontwarn(new FilterElement());

    // Act
    proGuardTask.setWarn(true);

    // Assert
    assertNull(proGuardTask.configuration.warn);
  }

  /**
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  void testSetWarn3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setWarn(false);

    // Assert
    assertTrue(proGuardTask.configuration.warn.isEmpty());
  }

  /**
   * Method under test: {@link ProGuardTask#setWarn(boolean)}
   */
  @Test
  void testSetWarn4() {
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
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File printConfiguration = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setPrintconfiguration(printConfiguration);

    // Assert
    assertSame(printConfiguration, proGuardTask.configuration.printConfiguration);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setPrintconfiguration(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert
    assertNull(proGuardTask.configuration.printConfiguration);
  }

  /**
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration5() {
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
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration6() {
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
   * Method under test: {@link ProGuardTask#setPrintconfiguration(File)}
   */
  @Test
  void testSetPrintconfiguration7() {
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
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    File dump = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    proGuardTask.setDump(dump);

    // Assert
    assertSame(dump, proGuardTask.configuration.dump);
  }

  /**
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump2() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();
    String property = System.getProperty("java.io.tmpdir");

    // Act
    proGuardTask.setDump(Paths.get(property, Boolean.FALSE.toString()).toFile());

    // Assert
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump3() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "no").toFile());

    // Assert
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump4() {
    // Arrange
    ProGuardTask proGuardTask = new ProGuardTask();

    // Act
    proGuardTask.setDump(Paths.get(System.getProperty("java.io.tmpdir"), "off").toFile());

    // Assert
    assertNull(proGuardTask.configuration.dump);
  }

  /**
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump5() {
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
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump6() {
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
   * Method under test: {@link ProGuardTask#setDump(File)}
   */
  @Test
  void testSetDump7() {
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
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  void testExecute() throws BuildException {
    // Arrange, Act and Assert
    assertThrows(BuildException.class, () -> (new ProGuardTask()).execute());
  }

  /**
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  void testExecute2() throws MalformedURLException, BuildException {
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
    configuration.newSourceFileAttribute = "1.544*";
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
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  void testExecute3() throws MalformedURLException, BuildException {
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
    configuration.newSourceFileAttribute = "1.544*";
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
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  void testExecute4() throws MalformedURLException, BuildException {
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
    configuration.newSourceFileAttribute = "1.544*";
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
   * Method under test: {@link ProGuardTask#execute()}
   */
  @Test
  void testExecute5() throws MalformedURLException, BuildException {
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
    configuration.newSourceFileAttribute = "1.544*";
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
   * Method under test: default or parameterless constructor of
   * {@link ProGuardTask}
   */
  @Test
  void testNewProGuardTask() {
    // Arrange and Act
    ProGuardTask actualProGuardTask = new ProGuardTask();

    // Assert
    RuntimeConfigurable runtimeConfigurableWrapper = actualProGuardTask.getRuntimeConfigurableWrapper();
    assertEquals("", runtimeConfigurableWrapper.getText().toString());
    Configuration configuration = actualProGuardTask.configuration;
    assertNull(configuration.applyMapping);
    assertNull(configuration.dump);
    assertNull(configuration.extraJar);
    assertNull(configuration.printConfiguration);
    assertNull(configuration.printMapping);
    assertNull(configuration.printSeeds);
    assertNull(configuration.printUsage);
    Location location = actualProGuardTask.getLocation();
    assertNull(location.getFileName());
    assertNull(actualProGuardTask.getDescription());
    assertNull(runtimeConfigurableWrapper.getElementTag());
    assertNull(runtimeConfigurableWrapper.getId());
    assertNull(runtimeConfigurableWrapper.getPolyType());
    assertNull(actualProGuardTask.getTaskName());
    assertNull(actualProGuardTask.getTaskType());
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
    assertNull(actualProGuardTask.getProject());
    assertNull(actualProGuardTask.getOwningTarget());
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
    assertSame(actualProGuardTask, runtimeConfigurableWrapper.getProxy());
  }
}
