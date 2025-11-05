package proguard.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Set;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ExtraDataEntryNameMapDiffblueTest {
  /**
   * Method under test: {@link ExtraDataEntryNameMap#clear()}
   */
  @Test
  void testClear() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.clear();

    // Assert
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#clear()}
   */
  @Test
  void testClear2() {
    // Arrange
    LibraryClass keyClass = mock(LibraryClass.class);
    when(keyClass.getName()).thenReturn("Name");

    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());

    // Act
    extraDataEntryNameMap.clear();

    // Assert
    verify(keyClass).getName();
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#clearExtraClasses()}
   */
  @Test
  void testClearExtraClasses() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.clearExtraClasses();

    // Assert that nothing has changed
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#clearExtraClasses()}
   */
  @Test
  void testClearExtraClasses2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Act
    extraDataEntryNameMap.clearExtraClasses();

    // Assert that nothing has changed
    Set<String> defaultExtraDataEntryNames = extraDataEntryNameMap.getDefaultExtraDataEntryNames();
    assertEquals(1, defaultExtraDataEntryNames.size());
    assertTrue(defaultExtraDataEntryNames.contains("Extra Data Entry Name"));
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#clearExtraClasses()}
   */
  @Test
  void testClearExtraClasses3() {
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
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String)}
   */
  @Test
  void testAddExtraDataEntry() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> defaultExtraDataEntryNames = extraDataEntryNameMap.getDefaultExtraDataEntryNames();
    assertEquals(1, defaultExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(defaultExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains(null));
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#addExtraDataEntry(String)}
   */
  @Test
  void testAddExtraDataEntry2() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Act
    extraDataEntryNameMap.addExtraDataEntry("Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> defaultExtraDataEntryNames = extraDataEntryNameMap.getDefaultExtraDataEntryNames();
    assertEquals(1, defaultExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(defaultExtraDataEntryNames.contains("Extra Data Entry Name"));
    assertTrue(keyDataEntryNames.contains(null));
  }

  /**
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)}
   */
  @Test
  void testAddExtraDataEntry3() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraDataEntry(String, String)}
   */
  @Test
  void testAddExtraDataEntry4() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    extraDataEntryNameMap.addExtraDataEntry("Key Data Entry Name", "Extra Data Entry Name");

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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}
   */
  @Test
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraDataEntryToClass(String, String)}
   */
  @Test
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
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClass(String)}
   */
  @Test
  void testAddExtraClass() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Act
    extraDataEntryNameMap.addExtraClass("Extra Data Entry Name");

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> defaultExtraDataEntryNames = extraDataEntryNameMap.getDefaultExtraDataEntryNames();
    assertEquals(1, defaultExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("Extra Data Entry Name.class"));
    assertTrue(defaultExtraDataEntryNames.contains("Extra Data Entry Name.class"));
    assertTrue(keyDataEntryNames.contains(null));
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#addExtraClass(String)}
   */
  @Test
  void testAddExtraClass2() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)}
   */
  @Test
  void testAddExtraClassToClass() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(String, Class)}
   */
  @Test
  void testAddExtraClassToClass2() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)}
   */
  @Test
  void testAddExtraClassToClass3() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(String, String)}
   */
  @Test
  void testAddExtraClassToClass4() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)}
   */
  @Test
  void testAddExtraClassToClass5() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Class)}
   */
  @Test
  void testAddExtraClassToClass6() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)}
   */
  @Test
  void testAddExtraClassToClass7() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, String)}
   */
  @Test
  void testAddExtraClassToClass8() {
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
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)}
   */
  @Test
  void testAddExtraClassToClass9() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Method under test:
   * {@link ExtraDataEntryNameMap#addExtraClassToClass(Clazz, Clazz)}
   */
  @Test
  void testAddExtraClassToClass10() {
    // Arrange
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    LibraryClass keyClass = new LibraryClass();
    extraDataEntryNameMap.addExtraClassToClass(keyClass, new LibraryClass());
    LibraryClass keyClass2 = new LibraryClass();

    // Act
    extraDataEntryNameMap.addExtraClassToClass(keyClass2, new LibraryClass());

    // Assert
    Set<String> allExtraDataEntryNames = extraDataEntryNameMap.getAllExtraDataEntryNames();
    assertEquals(1, allExtraDataEntryNames.size());
    Set<String> keyDataEntryNames = extraDataEntryNameMap.getKeyDataEntryNames();
    assertEquals(1, keyDataEntryNames.size());
    assertTrue(allExtraDataEntryNames.contains("null.class"));
    assertTrue(keyDataEntryNames.contains("null.class"));
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#getKeyDataEntryNames()}
   */
  @Test
  void testGetKeyDataEntryNames() {
    // Arrange, Act and Assert
    assertTrue((new ExtraDataEntryNameMap()).getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link ExtraDataEntryNameMap#getAllExtraDataEntryNames()}
   */
  @Test
  void testGetAllExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertTrue((new ExtraDataEntryNameMap()).getAllExtraDataEntryNames().isEmpty());
  }

  /**
   * Method under test:
   * {@link ExtraDataEntryNameMap#getDefaultExtraDataEntryNames()}
   */
  @Test
  void testGetDefaultExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertNull((new ExtraDataEntryNameMap()).getDefaultExtraDataEntryNames());
  }

  /**
   * Method under test:
   * {@link ExtraDataEntryNameMap#getExtraDataEntryNames(String)}
   */
  @Test
  void testGetExtraDataEntryNames() {
    // Arrange, Act and Assert
    assertNull((new ExtraDataEntryNameMap()).getExtraDataEntryNames("Key Data Entry Name"));
  }

  /**
   * Method under test:
   * {@link ExtraDataEntryNameMap#getClassDataEntryName(String)}
   */
  @Test
  void testGetClassDataEntryName() {
    // Arrange, Act and Assert
    assertEquals("Class Name.class", (new ExtraDataEntryNameMap()).getClassDataEntryName("Class Name"));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ExtraDataEntryNameMap}
   */
  @Test
  void testNewExtraDataEntryNameMap() {
    // Arrange and Act
    ExtraDataEntryNameMap actualExtraDataEntryNameMap = new ExtraDataEntryNameMap();

    // Assert
    assertNull(actualExtraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertTrue(actualExtraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(actualExtraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }
}
