package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.VariableInstruction;

class VariableUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link VariableUsageMarker#isVariableUsed(int)}
   */
  @Test
  void testIsVariableUsed() {
    // Arrange, Act and Assert
    assertFalse((new VariableUsageMarker()).isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    variableUsageMarker.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    assertFalse(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() throws UnsupportedEncodingException {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    variableUsageMarker.visitCodeAttribute(clazz, method, new CodeAttribute(1, 3, 3, 3, "A@A@A@A@".getBytes("UTF-8")));

    // Assert
    assertTrue(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute3() {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    variableUsageMarker.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{3, '@', 'A', '@', 'A', '@', 'A', '@'}));

    // Assert
    assertTrue(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}
   */
  @Test
  void testVisitVariableInstruction() {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    variableUsageMarker.visitVariableInstruction(clazz, method, codeAttribute, 2, new VariableInstruction((byte) 'A'));

    // Assert
    assertFalse(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}
   */
  @Test
  void testVisitVariableInstruction2() {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    variableUsageMarker.visitVariableInstruction(clazz, method, codeAttribute, 2, new VariableInstruction((byte) 1));

    // Assert
    assertFalse(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test:
   * {@link VariableUsageMarker#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}
   */
  @Test
  void testVisitVariableInstruction3() {
    // Arrange
    VariableUsageMarker variableUsageMarker = new VariableUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    variableUsageMarker.visitVariableInstruction(clazz, method, codeAttribute, 2,
        new VariableInstruction(Byte.MAX_VALUE));

    // Assert
    assertTrue(variableUsageMarker.isVariableUsed(1));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link VariableUsageMarker}
   */
  @Test
  void testNewVariableUsageMarker() {
    // Arrange, Act and Assert
    assertFalse((new VariableUsageMarker()).isVariableUsed(1));
  }
}
