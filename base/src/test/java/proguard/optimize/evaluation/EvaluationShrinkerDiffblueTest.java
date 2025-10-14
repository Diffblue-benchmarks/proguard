package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ExceptionInfo;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.exception.EmptyCodeAttributeException;

class EvaluationShrinkerDiffblueTest {
  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing()
        .when(partialEvaluator)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 3, 3, 3, new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(partialEvaluator)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute2() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing()
        .when(partialEvaluator)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute =
        new CodeAttribute(1, 8096, 3, 3, new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(partialEvaluator)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link PartialEvaluator#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * </ul>
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsVisitCodeAttribute() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing()
        .when(partialEvaluator)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(partialEvaluator)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>When {@link CodeAttribute#CodeAttribute()}.
   *   <li>Then calls {@link PartialEvaluator#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * </ul>
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); when CodeAttribute(); then calls visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_whenCodeAttribute_thenCallsVisitCodeAttribute() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing()
        .when(partialEvaluator)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    evaluationShrinker.visitCodeAttribute(clazz, method, new CodeAttribute());

    // Assert
    verify(partialEvaluator)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute0(Clazz, Method, CodeAttribute)}.
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute0(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute0(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute0(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute0() {
    // Arrange
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            mock(PartialEvaluator.class),
            false,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertThrows(
        EmptyCodeAttributeException.class,
        () -> evaluationShrinker.visitCodeAttribute0(clazz, method, new CodeAttribute(1)));
  }

  /**
   * Test {@link EvaluationShrinker#visitCodeAttribute0(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link PartialEvaluator#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * </ul>
   *
   * <p>Method under test: {@link EvaluationShrinker#visitCodeAttribute0(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute0(Clazz, Method, CodeAttribute); then calls visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationShrinker.visitCodeAttribute0(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute0_thenCallsVisitCodeAttribute() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing()
        .when(partialEvaluator)
        .visitCodeAttribute(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertThrows(
        EmptyCodeAttributeException.class,
        () -> evaluationShrinker.visitCodeAttribute0(clazz, method, new CodeAttribute(1)));
    verify(partialEvaluator)
        .visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Test {@link EvaluationShrinker#visitExceptionInfo(Clazz, Method, CodeAttribute,
   * ExceptionInfo)}.
   *
   * <p>Method under test: {@link EvaluationShrinker#visitExceptionInfo(Clazz, Method,
   * CodeAttribute, ExceptionInfo)}
   */
  @Test
  @DisplayName("Test visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EvaluationShrinker.visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)"
  })
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

  /**
   * Test {@link EvaluationShrinker#visitExceptionInfo(Clazz, Method, CodeAttribute,
   * ExceptionInfo)}.
   *
   * <p>Method under test: {@link EvaluationShrinker#visitExceptionInfo(Clazz, Method,
   * CodeAttribute, ExceptionInfo)}
   */
  @Test
  @DisplayName("Test visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EvaluationShrinker.visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)"
  })
  void testVisitExceptionInfo2() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.isTraced(anyInt())).thenReturn(true);
    EvaluationShrinker evaluationShrinker =
        new EvaluationShrinker(
            partialEvaluator,
            true,
            true,
            mock(InstructionVisitor.class),
            mock(InstructionVisitor.class));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);
    ExceptionInfo exceptionInfo = new ExceptionInfo(1, 3, 1, 1);

    // Act
    evaluationShrinker.visitExceptionInfo(clazz, method, codeAttribute, exceptionInfo);

    // Assert that nothing has changed
    verify(partialEvaluator).isTraced(1);
    assertEquals(3, exceptionInfo.u2endPC);
  }
}
