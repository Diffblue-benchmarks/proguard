package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;

class NonPrivateMemberMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass)
        .methodAccept(Mockito.<String>any(), Mockito.<String>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    nonPrivateMemberMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
    verify(programClass, atLeast(1)).methodAccept(Mockito.<String>any(), eq("()V"), isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = new StringConstant();

    // Act
    nonPrivateMemberMarker.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    assertNull(stringConstant.referencedMember);
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = new LibraryField(1, "Name", "Descriptor");

    // Act
    nonPrivateMemberMarker.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    assertTrue(stringConstant.referencedMember instanceof LibraryField);
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = new ProgramField();

    // Act
    nonPrivateMemberMarker.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    assertTrue(stringConstant.referencedMember instanceof ProgramField);
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant4() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = new LibraryClass();

    ProgramField programField = new ProgramField();
    LibraryClass clazz2 = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz2,
        new LibraryField(1, "Name", "Descriptor"), true);

    programField.setProcessingInfo(programFieldOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    nonPrivateMemberMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof ProgramField);
    Object processingInfo = member.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
    assertSame(programFieldOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant3() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    when(refConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    nonPrivateMemberMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(clazz).getName();
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).getClassName(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link NonPrivateMemberMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    NonPrivateMemberMarker nonPrivateMemberMarker = new NonPrivateMemberMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setCanNotBeMadePrivate();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    nonPrivateMemberMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethodOptimizationInfo).setCanNotBeMadePrivate();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Field)}
   */
  @Test
  void testCanBeMadePrivate() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertFalse(NonPrivateMemberMarker.canBeMadePrivate(field));
  }

  /**
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Field)}
   */
  @Test
  void testCanBeMadePrivate2() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertTrue(NonPrivateMemberMarker.canBeMadePrivate(field));
  }

  /**
   * Method under test: {@link NonPrivateMemberMarker#canBeMadePrivate(Method)}
   */
  @Test
  void testCanBeMadePrivate3() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NonPrivateMemberMarker.canBeMadePrivate(method));
  }
}
