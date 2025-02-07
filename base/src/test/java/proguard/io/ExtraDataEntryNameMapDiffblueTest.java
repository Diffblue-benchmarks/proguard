package proguard.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ExtraDataEntryNameMapDiffblueTest {
  /**
   * Test {@link ExtraDataEntryNameMap#clearExtraClasses()}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#clearExtraClasses()}
   */
  @Test
  @DisplayName("Test clearExtraClasses()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.clearExtraClasses()"})
  void testClearExtraClasses() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraClass("Extra Data Entry Name");
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Act
    extraDataEntryNameMap.clearExtraClasses();

    // Assert
    Set<String> defaultExtraDataEntryNames = extraDataEntryNameMap.getDefaultExtraDataEntryNames();
    assertEquals(1, defaultExtraDataEntryNames.size());
    assertTrue(defaultExtraDataEntryNames.contains("Extra Data Entry Name"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#clearExtraClasses()}.
   * <ul>
   *   <li>Then {@link ExtraDataEntryNameMap} (default constructor) DefaultExtraDataEntryNames is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#clearExtraClasses()}
   */
  @Test
  @DisplayName("Test clearExtraClasses(); then ExtraDataEntryNameMap (default constructor) DefaultExtraDataEntryNames is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.clearExtraClasses()"})
  void testClearExtraClasses_thenExtraDataEntryNameMapDefaultExtraDataEntryNamesIsNull() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.clearExtraClasses();

    // Assert that nothing has changed
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntry(String)} with {@code extraDataEntryName}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntry(String) with 'extraDataEntryName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraDataEntry(java.lang.String)"})
  void testAddExtraDataEntryWithExtraDataEntryName() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Assert that nothing has changed
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains(null));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntry(String)} with {@code extraDataEntryName}.
   * <ul>
   *   <li>Given {@link ExtraDataEntryNameMap} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntry(String) with 'extraDataEntryName'; given ExtraDataEntryNameMap (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraDataEntry(java.lang.String)"})
  void testAddExtraDataEntryWithExtraDataEntryName_givenExtraDataEntryNameMap() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains(null));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)} with {@code keyDataEntryName}, {@code extraDataEntryName}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntry(String, String) with 'keyDataEntryName', 'extraDataEntryName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraDataEntry(java.lang.String, java.lang.String)"})
  void testAddExtraDataEntryWithKeyDataEntryNameExtraDataEntryName() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Key Data Entry Name", "Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains("Key Data Entry Name"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)} with {@code keyDataEntryName}, {@code extraDataEntryName}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntry(String, String) with 'keyDataEntryName', 'extraDataEntryName'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraDataEntry(java.lang.String, java.lang.String)"})
  void testAddExtraDataEntryWithKeyDataEntryNameExtraDataEntryName2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry("Key Data Entry Name", "Extra Data Entry Name");

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Key Data Entry Name", "Extra Data Entry Name");

    // Assert that nothing has changed
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains("Key Data Entry Name"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntryToClass(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraDataEntryToClass(java.lang.String, java.lang.String)"})
  void testAddExtraDataEntryToClass() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraDataEntryToClass("Key Class Name", "Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}
   */
  @Test
  @DisplayName("Test addExtraDataEntryToClass(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraDataEntryToClass(java.lang.String, java.lang.String)"})
  void testAddExtraDataEntryToClass2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntryToClass("Key Class Name", ".class");

    // Act
    extraDataEntryNameMap.addExtraDataEntryToClass("Key Class Name", "Extra Data Entry Name");

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains(".class"));
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClass(String)}.
   * <ul>
   *   <li>Then {@link ExtraDataEntryNameMap} (default constructor) AllExtraDataEntryNames size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClass(String)}
   */
  @Test
  @DisplayName("Test addExtraClass(String); then ExtraDataEntryNameMap (default constructor) AllExtraDataEntryNames size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClass(java.lang.String)"})
  void testAddExtraClass_thenExtraDataEntryNameMapAllExtraDataEntryNamesSizeIsOne() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraClass("Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name.class"));
    assertTrue(keyDataEntryNames.contains(null));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClass(String)}.
   * <ul>
   *   <li>Then {@link ExtraDataEntryNameMap} (default constructor) AllExtraDataEntryNames size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClass(String)}
   */
  @Test
  @DisplayName("Test addExtraClass(String); then ExtraDataEntryNameMap (default constructor) AllExtraDataEntryNames size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClass(java.lang.String)"})
  void testAddExtraClass_thenExtraDataEntryNameMapAllExtraDataEntryNamesSizeIsTwo() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry(".class");

    // Act
    extraDataEntryNameMap.addExtraClass("Extra Data Entry Name");

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains(".class"));
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name.class"));
    assertTrue(keyDataEntryNames.contains(null));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)} with {@code Clazz}, {@code Class}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, Class) with 'Clazz', 'Class'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, java.lang.Class)"})
  void testAddExtraClassToClassWithClazzClass() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();
    Class<Object> extraClass = Object.class;

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass, extraClass);

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("java/lang/Object.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)} with {@code Clazz}, {@code Class}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, Class) with 'Clazz', 'Class'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, java.lang.Class)"})
  void testAddExtraClassToClassWithClazzClass2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());
    LibraryClass keyClass2 = new LibraryClass();
    Class<Object> extraClass = Object.class;

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass2, extraClass);

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("java/lang/Object.class"));
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)} with {@code Clazz}, {@code Clazz}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, Clazz) with 'Clazz', 'Clazz'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, proguard.classfile.Clazz)"})
  void testAddExtraClassToClassWithClazzClazz() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getKeyDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)} with {@code Clazz}, {@code Clazz}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, Clazz) with 'Clazz', 'Clazz'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, proguard.classfile.Clazz)"})
  void testAddExtraClassToClassWithClazzClazz2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());
    LibraryClass keyClass2 = new LibraryClass();

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass2, new LibraryClass());

    // Assert that nothing has changed
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertEquals(allExtraDataEntryNames, extraDataEntryNameMap.getKeyDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)} with {@code Clazz}, {@code String}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, String) with 'Clazz', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, java.lang.String)"})
  void testAddExtraClassToClassWithClazzString() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraClassToClass(new LibraryClass(), "Extra Class Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Class Name.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)} with {@code Clazz}, {@code String}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(Clazz, String) with 'Clazz', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(proguard.classfile.Clazz, java.lang.String)"})
  void testAddExtraClassToClassWithClazzString2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());

    // Act
    extraDataEntryNameMap.addExtraClassToClass(new LibraryClass(), "Extra Class Name");

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Class Name.class"));
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)} with {@code String}, {@code Class}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(String, Class) with 'String', 'Class'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(java.lang.String, java.lang.Class)"})
  void testAddExtraClassToClassWithStringClass() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    Class<Object> extraClass = Object.class;

    // Act
    extraDataEntryNameMap.addExtraClassToClass("Key Class Name", extraClass);

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("java/lang/Object.class"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)} with {@code String}, {@code Class}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(String, Class) with 'String', 'Class'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(java.lang.String, java.lang.Class)"})
  void testAddExtraClassToClassWithStringClass2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntryToClass("Key Class Name", ".class");
    Class<Object> extraClass = Object.class;

    // Act
    extraDataEntryNameMap.addExtraClassToClass("Key Class Name", extraClass);

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains(".class"));
    assertTrue(allExtraDataEntryNames.contains("java/lang/Object.class"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)} with {@code String}, {@code String}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(String, String) with 'String', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(java.lang.String, java.lang.String)"})
  void testAddExtraClassToClassWithStringString() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraClassToClass("Key Class Name", "Extra Class Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Class Name.class"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)} with {@code String}, {@code String}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)}
   */
  @Test
  @DisplayName("Test addExtraClassToClass(String, String) with 'String', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.addExtraClassToClass(java.lang.String, java.lang.String)"})
  void testAddExtraClassToClassWithStringString2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntryToClass("Key Class Name", ".class");

    // Act
    extraDataEntryNameMap.addExtraClassToClass("Key Class Name", "Extra Class Name");

    // Assert
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(2, allExtraDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains(".class"));
    assertTrue(allExtraDataEntryNames.contains("Extra Class Name.class"));
    assertTrue(keyDataEntryNames.contains("Key Class Name.class"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#getKeyDataEntryNames()}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#getKeyDataEntryNames()}
   */
  @Test
  @DisplayName("Test getKeyDataEntryNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set proguard.io.ExtraDataEntryNameMap.getKeyDataEntryNames()"})
  void testGetKeyDataEntryNames() {
    // Arrange, Act and Assert
    assertTrue((new ExtraDataEntryNameMap()).getKeyDataEntryNames().isEmpty());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#getAllExtraDataEntryNames()}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#getAllExtraDataEntryNames()}
   */
  @Test
  @DisplayName("Test getAllExtraDataEntryNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set proguard.io.ExtraDataEntryNameMap.getAllExtraDataEntryNames()"})
  void testGetAllExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertTrue((new ExtraDataEntryNameMap()).getAllExtraDataEntryNames().isEmpty());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#getDefaultExtraDataEntryNames()}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#getDefaultExtraDataEntryNames()}
   */
  @Test
  @DisplayName("Test getDefaultExtraDataEntryNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set proguard.io.ExtraDataEntryNameMap.getDefaultExtraDataEntryNames()"})
  void testGetDefaultExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertNull((new ExtraDataEntryNameMap()).getDefaultExtraDataEntryNames());
  }

  /**
   * Test {@link ExtraDataEntryNameMap#getExtraDataEntryNames(String)}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#getExtraDataEntryNames(String)}
   */
  @Test
  @DisplayName("Test getExtraDataEntryNames(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Set proguard.io.ExtraDataEntryNameMap.getExtraDataEntryNames(java.lang.String)"})
  void testGetExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertNull((new ExtraDataEntryNameMap()).getExtraDataEntryNames("Key Data Entry Name"));
  }

  /**
   * Test {@link ExtraDataEntryNameMap#getClassDataEntryName(String)} with {@code String}.
   * <p>
   * Method under test: {@link ExtraDataEntryNameMap#getClassDataEntryName(String)}
   */
  @Test
  @DisplayName("Test getClassDataEntryName(String) with 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.io.ExtraDataEntryNameMap.getClassDataEntryName(java.lang.String)"})
  void testGetClassDataEntryNameWithString() {
    // Arrange, Act and Assert
    assertEquals("Class Name.class", (new ExtraDataEntryNameMap()).getClassDataEntryName("Class Name"));
  }

  /**
   * Test new {@link ExtraDataEntryNameMap} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ExtraDataEntryNameMap}
   */
  @Test
  @DisplayName("Test new ExtraDataEntryNameMap (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.io.ExtraDataEntryNameMap.<init>()"})
  void testNewExtraDataEntryNameMap() {
    // Arrange and Act
    ExtraDataEntryNameMap actualExtraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Assert
    assertNull(actualExtraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertTrue(actualExtraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(actualExtraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }
}
