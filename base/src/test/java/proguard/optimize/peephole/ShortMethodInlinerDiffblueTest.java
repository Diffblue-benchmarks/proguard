package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.visitor.InstructionConstantVisitor;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.ClassRenamer;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class ShortMethodInlinerDiffblueTest {
  /**
   * Method under test:
   * {@link ShortMethodInliner#ShortMethodInliner(boolean, boolean, boolean)}
   */
  @Test
  void testNewShortMethodInliner() {
    // Arrange and Act
    ShortMethodInliner actualShortMethodInliner = new ShortMethodInliner(true, true, true);

    // Assert
    assertNull(actualShortMethodInliner.extraInlinedInvocationVisitor);
    assertEquals(2000, actualShortMethodInliner.maxResultingCodeLength);
    assertTrue(actualShortMethodInliner.allowAccessModification);
    assertTrue(actualShortMethodInliner.android);
    assertTrue(actualShortMethodInliner.microEdition);
    assertTrue(actualShortMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testShouldInline() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertTrue(shortMethodInliner.shouldInline(clazz, method, new CodeAttribute(1)));
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testShouldInline2() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, false, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act and Assert
    assertTrue(shortMethodInliner.shouldInline(clazz, method, new CodeAttribute(1)));
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#ShortMethodInliner(boolean, boolean, boolean)}
   */
  @Test
  void testNewShortMethodInliner2() {
    // Arrange and Act
    ShortMethodInliner actualShortMethodInliner = new ShortMethodInliner(false, true, true);

    // Assert
    assertNull(actualShortMethodInliner.extraInlinedInvocationVisitor);
    assertEquals(7000, actualShortMethodInliner.maxResultingCodeLength);
    assertFalse(actualShortMethodInliner.microEdition);
    assertTrue(actualShortMethodInliner.allowAccessModification);
    assertTrue(actualShortMethodInliner.android);
    assertTrue(actualShortMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#ShortMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewShortMethodInliner3() {
    // Arrange and Act
    ShortMethodInliner actualShortMethodInliner = new ShortMethodInliner(true, true, true,
        new DuplicateInitializerInvocationFixer());

    // Assert
    assertTrue(actualShortMethodInliner.extraInlinedInvocationVisitor instanceof DuplicateInitializerInvocationFixer);
    assertEquals(2000, actualShortMethodInliner.maxResultingCodeLength);
    assertTrue(actualShortMethodInliner.allowAccessModification);
    assertTrue(actualShortMethodInliner.android);
    assertTrue(actualShortMethodInliner.microEdition);
    assertTrue(actualShortMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#ShortMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewShortMethodInliner4() {
    // Arrange and Act
    ShortMethodInliner actualShortMethodInliner = new ShortMethodInliner(false, true, true,
        new DuplicateInitializerInvocationFixer());

    // Assert
    assertTrue(actualShortMethodInliner.extraInlinedInvocationVisitor instanceof DuplicateInitializerInvocationFixer);
    assertEquals(7000, actualShortMethodInliner.maxResultingCodeLength);
    assertFalse(actualShortMethodInliner.microEdition);
    assertTrue(actualShortMethodInliner.allowAccessModification);
    assertTrue(actualShortMethodInliner.android);
    assertTrue(actualShortMethodInliner.usesOptimizationInfo);
  }

  /**
   * Method under test:
   * {@link ShortMethodInliner#ShortMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  void testNewShortMethodInliner5() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);

    // Act
    ShortMethodInliner actualShortMethodInliner = new ShortMethodInliner(true, true, true,
        new InstructionConstantVisitor(new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter())));

    // Assert
    assertTrue(actualShortMethodInliner.extraInlinedInvocationVisitor instanceof InstructionConstantVisitor);
    assertEquals(2000, actualShortMethodInliner.maxResultingCodeLength);
    assertTrue(actualShortMethodInliner.allowAccessModification);
    assertTrue(actualShortMethodInliner.android);
    assertTrue(actualShortMethodInliner.microEdition);
    assertTrue(actualShortMethodInliner.usesOptimizationInfo);
  }
}
