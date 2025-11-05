package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class ClassPathDiffblueTest {
  /**
   * Method under test: {@link ClassPath#hasOutput()}
   */
  @Test
  void testHasOutput() {
    // Arrange, Act and Assert
    assertFalse((new ClassPath()).hasOutput());
  }

  /**
   * Method under test: {@link ClassPath#hasOutput()}
   */
  @Test
  void testHasOutput2() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(classPath.hasOutput());
  }

  /**
   * Method under test: {@link ClassPath#hasOutput()}
   */
  @Test
  void testHasOutput3() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, false));

    // Act and Assert
    assertFalse(classPath.hasOutput());
  }

  /**
   * Method under test: {@link ClassPath#clear()}
   */
  @Test
  void testClear() {
    // Arrange
    ClassPath classPath = new ClassPath();

    // Act
    classPath.clear();

    // Assert
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Method under test: {@link ClassPath#clear()}
   */
  @Test
  void testClear2() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));
    classPath.add(1, mock(ClassPathEntry.class));

    // Act
    classPath.clear();

    // Assert
    assertEquals(0, classPath.size());
    assertFalse(classPath.hasOutput());
    assertTrue(classPath.isEmpty());
  }

  /**
   * Method under test: {@link ClassPath#add(int, ClassPathEntry)}
   */
  @Test
  void testAdd() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));

    // Act
    classPath.add(1, new ClassPathEntry(Configuration.STD_OUT, true));

    // Assert
    assertEquals(2, classPath.size());
  }

  /**
   * Method under test: {@link ClassPath#add(ClassPathEntry)}
   */
  @Test
  void testAdd2() {
    // Arrange
    ClassPath classPath = new ClassPath();

    // Act
    boolean actualAddResult = classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));

    // Assert
    assertEquals(1, classPath.size());
    assertFalse(classPath.isEmpty());
    assertTrue(actualAddResult);
    assertTrue(classPath.hasOutput());
  }

  /**
   * Method under test: {@link ClassPath#addAll(ClassPath)}
   */
  @Test
  void testAddAll() {
    // Arrange
    ClassPath classPath = new ClassPath();

    // Act and Assert
    assertFalse(classPath.addAll(new ClassPath()));
  }

  /**
   * Method under test: {@link ClassPath#addAll(ClassPath)}
   */
  @Test
  void testAddAll2() {
    // Arrange
    ClassPath classPath = new ClassPath();

    ClassPath classPath2 = new ClassPath();
    classPath2.add(new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(classPath.addAll(classPath2));
  }

  /**
   * Method under test: {@link ClassPath#get(int)}
   */
  @Test
  void testGet() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    classPath.add(1, classPathEntry);

    // Act and Assert
    assertSame(classPathEntry, classPath.get(1));
  }

  /**
   * Method under test: {@link ClassPath#remove(int)}
   */
  @Test
  void testRemove() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    classPath.add(1, classPathEntry);

    // Act
    ClassPathEntry actualRemoveResult = classPath.remove(1);

    // Assert
    assertEquals(1, classPath.size());
    assertSame(classPathEntry, actualRemoveResult);
  }

  /**
   * Method under test: {@link ClassPath#isEmpty()}
   */
  @Test
  void testIsEmpty() {
    // Arrange, Act and Assert
    assertTrue((new ClassPath()).isEmpty());
  }

  /**
   * Method under test: {@link ClassPath#isEmpty()}
   */
  @Test
  void testIsEmpty2() {
    // Arrange
    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertFalse(classPath.isEmpty());
  }

  /**
   * Method under test: {@link ClassPath#size()}
   */
  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new ClassPath()).size());
  }

  /**
   * Method under test: default or parameterless constructor of {@link ClassPath}
   */
  @Test
  void testNewClassPath() {
    // Arrange and Act
    ClassPath actualClassPath = new ClassPath();

    // Assert
    assertEquals(0, actualClassPath.size());
    assertFalse(actualClassPath.hasOutput());
    assertTrue(actualClassPath.isEmpty());
  }
}
