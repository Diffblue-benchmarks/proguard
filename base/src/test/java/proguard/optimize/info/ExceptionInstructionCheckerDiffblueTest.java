package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;

class ExceptionInstructionCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testMayThrowExceptions() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method, new CodeAttribute(1)));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testMayThrowExceptions2() throws UnsupportedEncodingException {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, "A\tA\tA\tA\t".getBytes("UTF-8"))));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)}
   */
  @Test
  void testMayThrowExceptions3() throws UnsupportedEncodingException {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, "AXAXAXAX".getBytes("UTF-8")), 2));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)}
   */
  @Test
  void testMayThrowExceptions4() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 'X', 4, 'X', 'A', 'X', 'A', 'X'}), 2));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testMayThrowExceptions5() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testMayThrowExceptions6() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method, codeAttribute, 2,
        new BranchInstruction((byte) 'A', 1)));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#firstExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testFirstExceptionThrowingInstructionOffset() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(3, exceptionInstructionChecker.firstExceptionThrowingInstructionOffset(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }

  /**
   * Method under test:
   * {@link ExceptionInstructionChecker#lastExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  void testLastExceptionThrowingInstructionOffset() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(1, exceptionInstructionChecker.lastExceptionThrowingInstructionOffset(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }
}
