package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.info.MethodOptimizationInfo;

class SingleInvocationMethodInlinerDiffblueTest {
  /**
   * Test {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean)}.
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean)}
   */
  @Test
  @DisplayName("Test new SingleInvocationMethodInliner(boolean, boolean, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SingleInvocationMethodInliner.<init>(boolean, boolean, boolean)"})
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
   * Test {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}.
   * <ul>
   *   <li>Then return {@link MethodInliner#maxResultingCodeLength} is {@code 2000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  @DisplayName("Test new SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor); then return maxResultingCodeLength is '2000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SingleInvocationMethodInliner.<init>(boolean, boolean, boolean, InstructionVisitor)"})
  void testNewSingleInvocationMethodInliner_thenReturnMaxResultingCodeLengthIs2000() {
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
   * Test {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}.
   * <ul>
   *   <li>Then return {@link MethodInliner#maxResultingCodeLength} is {@code 7000}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor)}
   */
  @Test
  @DisplayName("Test new SingleInvocationMethodInliner(boolean, boolean, boolean, InstructionVisitor); then return maxResultingCodeLength is '7000'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SingleInvocationMethodInliner.<init>(boolean, boolean, boolean, InstructionVisitor)"})
  void testNewSingleInvocationMethodInliner_thenReturnMaxResultingCodeLengthIs7000() {
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
   * Test {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test shouldInline(Clazz, Method, CodeAttribute); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SingleInvocationMethodInliner.shouldInline(Clazz, Method, CodeAttribute)"})
  void testShouldInline_givenMethodOptimizationInfo() {
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
   * Test {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#getInvocationCount()} return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test shouldInline(Clazz, Method, CodeAttribute); given MethodOptimizationInfo getInvocationCount() return three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SingleInvocationMethodInliner.shouldInline(Clazz, Method, CodeAttribute)"})
  void testShouldInline_givenMethodOptimizationInfoGetInvocationCountReturnThree() {
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
   * Test {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SingleInvocationMethodInliner#shouldInline(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test shouldInline(Clazz, Method, CodeAttribute); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SingleInvocationMethodInliner.shouldInline(Clazz, Method, CodeAttribute)"})
  void testShouldInline_thenReturnTrue() {
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
}
