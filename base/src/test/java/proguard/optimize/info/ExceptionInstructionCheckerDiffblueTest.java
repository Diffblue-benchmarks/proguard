package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)} with {@code clazz}, {@code method}, {@code codeAttribute}.
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute) with 'clazz', 'method', 'codeAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttribute() throws UnsupportedEncodingException {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, "A\tA\tA\tA\t".getBytes("UTF-8"))));
  }

  /**
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code offset}.
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute, int) with 'clazz', 'method', 'codeAttribute', 'offset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttributeOffset() throws UnsupportedEncodingException {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, "AXAXAXAX".getBytes("UTF-8")), 2));
  }

  /**
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, Instruction)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code offset}, {@code instruction}.
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute, int, Instruction) with 'clazz', 'method', 'codeAttribute', 'offset', 'instruction'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttributeOffsetInstruction() {
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
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code offset}.
   * <ul>
   *   <li>When {@code X}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute, int) with 'clazz', 'method', 'codeAttribute', 'offset'; when 'X'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttributeOffset_whenX_thenReturnFalse() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 'X', 4, 'X', 'A', 'X', 'A', 'X'}), 2));
  }

  /**
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, int)} with {@code clazz}, {@code method}, {@code codeAttribute}, {@code startOffset}, {@code endOffset}.
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute, int, int) with 'clazz', 'method', 'codeAttribute', 'startOffset', 'endOffset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, int)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttributeStartOffsetEndOffset() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }

  /**
   * Test {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)} with {@code clazz}, {@code method}, {@code codeAttribute}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#mayThrowExceptions(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test mayThrowExceptions(Clazz, Method, CodeAttribute) with 'clazz', 'method', 'codeAttribute'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ExceptionInstructionChecker.mayThrowExceptions(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testMayThrowExceptionsWithClazzMethodCodeAttribute_thenReturnFalse() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertFalse(exceptionInstructionChecker.mayThrowExceptions(clazz, method, new CodeAttribute(1)));
  }

  /**
   * Test {@link ExceptionInstructionChecker#firstExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}.
   * <ul>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#firstExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  @DisplayName("Test firstExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int); then return three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "int proguard.optimize.info.ExceptionInstructionChecker.firstExceptionThrowingInstructionOffset(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, int)"})
  void testFirstExceptionThrowingInstructionOffset_thenReturnThree() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(3, exceptionInstructionChecker.firstExceptionThrowingInstructionOffset(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }

  /**
   * Test {@link ExceptionInstructionChecker#lastExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ExceptionInstructionChecker#lastExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int)}
   */
  @Test
  @DisplayName("Test lastExceptionThrowingInstructionOffset(Clazz, Method, CodeAttribute, int, int); then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "int proguard.optimize.info.ExceptionInstructionChecker.lastExceptionThrowingInstructionOffset(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, int)"})
  void testLastExceptionThrowingInstructionOffset_thenReturnOne() {
    // Arrange
    ExceptionInstructionChecker exceptionInstructionChecker = new ExceptionInstructionChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertEquals(1, exceptionInstructionChecker.lastExceptionThrowingInstructionOffset(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3}), 1, 3));
  }
}
