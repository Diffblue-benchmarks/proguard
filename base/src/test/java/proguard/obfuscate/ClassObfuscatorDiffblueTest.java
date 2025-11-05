package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ClassObfuscatorDiffblueTest {
  /**
   * Method under test: {@link ClassObfuscator#setNewClassName(Clazz, String)}
   */
  @Test
  void testSetNewClassName() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ClassObfuscator.setNewClassName(clazz, "Name");

    // Assert
    assertEquals("Name", clazz.getProcessingInfo());
  }

  /**
   * Method under test: {@link ClassObfuscator#hasOriginalClassName(Clazz)}
   */
  @Test
  void testHasOriginalClassName() {
    // Arrange, Act and Assert
    assertFalse(ClassObfuscator.hasOriginalClassName(new LibraryClass(1, "This Class Name", "Super Class Name")));
  }

  /**
   * Method under test: {@link ClassObfuscator#newClassName(Clazz)}
   */
  @Test
  void testNewClassName() {
    // Arrange, Act and Assert
    assertNull(ClassObfuscator.newClassName(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassObfuscator#newClassName(Clazz)}
   */
  @Test
  void testNewClassName2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("Clazz");

    // Act and Assert
    assertEquals("Clazz", ClassObfuscator.newClassName(clazz));
  }
}
