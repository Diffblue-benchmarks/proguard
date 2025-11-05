package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.MemberVisitor;

class MethodInvocationMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link MethodInvocationMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    methodInvocationMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInvocationMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    methodInvocationMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    methodInvocationMarker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInvocationMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).incrementInvocationCount();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    methodInvocationMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethodOptimizationInfo).incrementInvocationCount();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link MethodInvocationMarker#getInvocationCount(Method)}
   */
  @Test
  void testGetInvocationCount() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(Integer.MAX_VALUE, MethodInvocationMarker.getInvocationCount(method));
  }
}
