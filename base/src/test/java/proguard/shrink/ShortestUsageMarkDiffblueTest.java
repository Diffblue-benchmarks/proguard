package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.Member;
import proguard.classfile.visitor.ClassVisitor;

class ShortestUsageMarkDiffblueTest {
  /**
   * Method under test: {@link ShortestUsageMark#isShorter(ShortestUsageMark)}
   */
  @Test
  void testIsShorter() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isShorter(new ShortestUsageMark("Just cause")));
  }

  /**
   * Method under test: {@link ShortestUsageMark#isShorter(ShortestUsageMark)}
   */
  @Test
  void testIsShorter2() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertTrue(
        shortestUsageMark.isShorter(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass())));
  }

  /**
   * Method under test: {@link ShortestUsageMark#isCausedBy(Clazz)}
   */
  @Test
  void testIsCausedBy() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedBy(new LibraryClass()));
  }

  /**
   * Method under test: {@link ShortestUsageMark#isCausedBy(Clazz, Member)}
   */
  @Test
  void testIsCausedBy2() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedBy(clazz, new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Method under test: {@link ShortestUsageMark#isCausedByMember(Clazz)}
   */
  @Test
  void testIsCausedByMember() {
    // Arrange
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertFalse(shortestUsageMark.isCausedByMember(new LibraryClass()));
  }

  /**
   * Method under test: {@link ShortestUsageMark#acceptClassVisitor(ClassVisitor)}
   */
  @Test
  void testAcceptClassVisitor() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass());
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());

    // Act
    shortestUsageMark.acceptClassVisitor(classVisitor);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link ShortestUsageMark#toString()}
   */
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("certain=true, depth=0: Just cause(none): (none)", (new ShortestUsageMark("Just cause")).toString());
  }

  /**
   * Method under test: {@link ShortestUsageMark#toString()}
   */
  @Test
  void testToString2() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertEquals("certain=true, depth=1: Just causenull: (none)",
        (new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass())).toString());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ShortestUsageMark#ShortestUsageMark(String)}
   *   <li>{@link ShortestUsageMark#getReason()}
   *   <li>{@link ShortestUsageMark#isCertain()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark("Just cause");
    String actualReason = actualShortestUsageMark.getReason();

    // Assert
    assertEquals("Just cause", actualReason);
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Method under test:
   * {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz)}
   */
  @Test
  void testNewShortestUsageMark() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1,
        new LibraryClass());

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Method under test:
   * {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, String, int, Clazz, Member)}
   */
  @Test
  void testNewShortestUsageMark2() {
    // Arrange
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    LibraryClass clazz = new LibraryClass();

    // Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(previousUsageMark, "Just cause", 1, clazz,
        new LibraryField(1, "Name", "Descriptor"));

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }

  /**
   * Method under test:
   * {@link ShortestUsageMark#ShortestUsageMark(ShortestUsageMark, boolean)}
   */
  @Test
  void testNewShortestUsageMark3() {
    // Arrange and Act
    ShortestUsageMark actualShortestUsageMark = new ShortestUsageMark(new ShortestUsageMark("Just cause"), true);

    // Assert
    assertEquals("Just cause", actualShortestUsageMark.getReason());
    assertTrue(actualShortestUsageMark.isCertain());
  }
}
