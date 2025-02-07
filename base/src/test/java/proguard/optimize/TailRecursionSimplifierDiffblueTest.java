package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import proguard.classfile.instruction.visitor.InstructionVisitor;

class TailRecursionSimplifierDiffblueTest {
  /**
   * Test {@link TailRecursionSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link CodeAttribute#instructionsAccept(Clazz, Method, InstructionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TailRecursionSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls instructionsAccept(Clazz, Method, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.TailRecursionSimplifier.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsInstructionsAccept() {
    // Arrange
    TailRecursionSimplifier tailRecursionSimplifier = new TailRecursionSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(26, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    tailRecursionSimplifier.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
  }
}
