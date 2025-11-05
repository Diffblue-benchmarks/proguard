package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ExceptionInfo;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class EvaluationShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    EvaluationShrinker evaluationShrinker = new EvaluationShrinker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    assertEquals(3, codeAttribute.u4codeLength);
  }

  /**
   * Method under test:
   * {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(true);
    DuplicateInitializerInvocationFixer extraDeletedInstructionVisitor = new DuplicateInitializerInvocationFixer();
    EvaluationShrinker evaluationShrinker = new EvaluationShrinker(instructionUsageMarker, true,
        extraDeletedInstructionVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    assertEquals(3, codeAttribute.u4codeLength);
  }

  /**
   * Method under test:
   * {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute3() {
    // Arrange
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(true);
    DuplicateInitializerInvocationFixer extraDeletedInstructionVisitor = new DuplicateInitializerInvocationFixer();
    EvaluationShrinker evaluationShrinker = new EvaluationShrinker(instructionUsageMarker, false,
        extraDeletedInstructionVisitor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    assertEquals(0, codeAttribute.u4codeLength);
  }

  /**
   * Method under test:
   * {@link EvaluationShrinker#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}
   */
  @Test
  void testVisitExceptionInfo() {
    // Arrange
    EvaluationShrinker evaluationShrinker = new EvaluationShrinker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ExceptionInfo exceptionInfo = new ExceptionInfo(1, 3, 1, 1);

    // Act
    evaluationShrinker.visitExceptionInfo(clazz, method, codeAttribute, exceptionInfo);

    // Assert
    assertEquals(1, exceptionInfo.u2endPC);
  }
}
