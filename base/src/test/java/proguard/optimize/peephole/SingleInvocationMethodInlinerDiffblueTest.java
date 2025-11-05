package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.visitor.InstructionConstantVisitor;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.ClassRenamer;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.info.MethodOptimizationInfo;

class SingleInvocationMethodInlinerDiffblueTest {
  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean)}
   */
  @Test
  void testNewSingleInvocationMethodInliner() {
    // Arrange and Act
    SingleInvocationMethodInliner actualSingleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true,
        true);

    // Assert
    assertNull(actualSingleInvocationMethodInliner.extraInlinedInvocationVisitor);
    assertEquals(2000, actualSingleInvocationMethodInliner.maxResultingCodeLength);
    assertTrue(actualSingleInvocationMethodInliner.allowAccessModification);
    assertTrue(actualSingleInvocationMethodInliner.android);
    assertTrue(actualSingleInvocationMethodInliner.microEdition);
    assertTrue(actualSingleInvocationMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testShouldInline() {
    // Arrange
    SingleInvocationMethodInliner singleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    boolean actualShouldInlineResult = singleInvocationMethodInliner.shouldInline(clazz, method, new CodeAttribute(1));

    // Assert
    verify(method, atLeast(1)).getProcessingInfo();
    assertFalse(actualShouldInlineResult);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testShouldInline2() {
    // Arrange
    SingleInvocationMethodInliner singleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.getInvocationCount()).thenReturn(3);
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    boolean actualShouldInlineResult = singleInvocationMethodInliner.shouldInline(clazz, method, new CodeAttribute(1));

    // Assert
    verify(methodOptimizationInfo).getInvocationCount();
    verify(method, atLeast(1)).getProcessingInfo();
    assertFalse(actualShouldInlineResult);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testShouldInline3() {
    // Arrange
    SingleInvocationMethodInliner singleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.getInvocationCount()).thenReturn(1);
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    boolean actualShouldInlineResult = singleInvocationMethodInliner.shouldInline(clazz, method, new CodeAttribute(1));

    // Assert
    verify(methodOptimizationInfo).getInvocationCount();
    verify(method, atLeast(1)).getProcessingInfo();
    assertTrue(actualShouldInlineResult);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewSingleInvocationMethodInliner2() {
    // Arrange and Act
    SingleInvocationMethodInliner actualSingleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true,
        true, new DuplicateInitializerInvocationFixer());

    // Assert
    assertTrue(
        actualSingleInvocationMethodInliner.extraInlinedInvocationVisitor instanceof DuplicateInitializerInvocationFixer);
    assertEquals(2000, actualSingleInvocationMethodInliner.maxResultingCodeLength);
    assertTrue(actualSingleInvocationMethodInliner.allowAccessModification);
    assertTrue(actualSingleInvocationMethodInliner.android);
    assertTrue(actualSingleInvocationMethodInliner.microEdition);
    assertTrue(actualSingleInvocationMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewSingleInvocationMethodInliner3() {
    // Arrange and Act
    SingleInvocationMethodInliner actualSingleInvocationMethodInliner = new SingleInvocationMethodInliner(false, true,
        true, new DuplicateInitializerInvocationFixer());

    // Assert
    assertTrue(
        actualSingleInvocationMethodInliner.extraInlinedInvocationVisitor instanceof DuplicateInitializerInvocationFixer);
    assertEquals(7000, actualSingleInvocationMethodInliner.maxResultingCodeLength);
    assertFalse(actualSingleInvocationMethodInliner.microEdition);
    assertTrue(actualSingleInvocationMethodInliner.allowAccessModification);
    assertTrue(actualSingleInvocationMethodInliner.android);
    assertTrue(actualSingleInvocationMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewSingleInvocationMethodInliner4() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);

    // Act
    SingleInvocationMethodInliner actualSingleInvocationMethodInliner = new SingleInvocationMethodInliner(true, true,
        true, new InstructionConstantVisitor(new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter())));

    // Assert
    assertTrue(actualSingleInvocationMethodInliner.extraInlinedInvocationVisitor instanceof InstructionConstantVisitor);
    assertEquals(2000, actualSingleInvocationMethodInliner.maxResultingCodeLength);
    assertTrue(actualSingleInvocationMethodInliner.allowAccessModification);
    assertTrue(actualSingleInvocationMethodInliner.android);
    assertTrue(actualSingleInvocationMethodInliner.microEdition);
    assertTrue(actualSingleInvocationMethodInliner.usesOptimizationInfo);
  }
}
