package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
   * Method under test:
   * {@link TailRecursionSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
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
