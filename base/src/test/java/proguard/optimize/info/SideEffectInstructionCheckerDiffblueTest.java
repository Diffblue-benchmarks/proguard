package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.LookUpSwitchInstruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.classfile.visitor.MemberVisitor;

class SideEffectInstructionCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new BranchInstruction((byte) 'A', 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new BranchInstruction((byte) -88, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects3() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects4() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2, new LookUpSwitchInstruction()));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects5() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) 'A')));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects6() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new VariableInstruction((byte) 'A')));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects7() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -78, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects8() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -77, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects9() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -76, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects10() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -75, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects11() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -74, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects12() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -73, 1)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects13() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -84)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testHasSideEffects14() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -68)));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant fieldrefConstant = mock(FieldrefConstant.class);
    doNothing().when(fieldrefConstant).referencedFieldAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldrefConstant).referencedFieldAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = libraryField;

    // Act
    sideEffectInstructionChecker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(libraryField).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = libraryMethod;

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(libraryMethod).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant3() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(methodOptimizationInfo);
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = programMethod;

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    sideEffectInstructionChecker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.hasNoSideEffects()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    sideEffectInstructionChecker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
    verify(classOptimizationInfo).hasNoSideEffects();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(false);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getAccessFlags();
    verify(programMethod).getName(isA(Clazz.class));
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectInstructionChecker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasNoSideEffects()).thenReturn(true);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).hasNoSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
