package proguard.optimize.info;

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
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.DynamicConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class AccessMethodMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link AccessMethodMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedClassAccept(Mockito.<ClassVisitor>any());
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedClassAccept(isA(ClassVisitor.class));
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = null;
    stringConstant.referencedClass = libraryClass;

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = libraryField;
    stringConstant.referencedClass = mock(LibraryClass.class);

    // Act
    accessMethodMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryField).accept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant4() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = new LibraryField();
    stringConstant.referencedClass = mock(LibraryClass.class);

    // Act
    accessMethodMarker.visitStringConstant(clazz2, stringConstant);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    accessMethodMarker.visitDynamicConstant(clazz, new DynamicConstant());

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    DynamicConstant dynamicConstant = mock(DynamicConstant.class);
    doNothing().when(dynamicConstant).bootstrapMethodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    verify(dynamicConstant).bootstrapMethodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    accessMethodMarker.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    doNothing().when(invokeDynamicConstant)
        .bootstrapMethodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    verify(invokeDynamicConstant).bootstrapMethodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant3() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryClass referencedClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant4() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPrivateCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = mock(LibraryClass.class);
    doNothing().when(clazz2).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz2,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(2, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(referencedClass).accept(isA(ClassVisitor.class));
    verify(clazz2).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPrivateCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant5() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = mock(LibraryClass.class);
    doNothing().when(clazz2).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz2,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(0, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(referencedClass).accept(isA(ClassVisitor.class));
    verify(clazz2).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant6() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesProtectedCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = mock(LibraryClass.class);
    doNothing().when(clazz2).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz2,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(4, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(referencedClass).accept(isA(ClassVisitor.class));
    verify(clazz2).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesProtectedCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant7() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = mock(LibraryClass.class);
    doNothing().when(clazz2).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedClass = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyRefConstant(clazz2, refConstant);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz2).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    accessMethodMarker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    accessMethodMarker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link AccessMethodMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    accessMethodMarker.visitClassConstant(clazz2, classConstant);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link AccessMethodMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getAccessFlags()).thenReturn(1);

    // Act
    accessMethodMarker.visitAnyClass(clazz);

    // Assert
    verify(clazz).getAccessFlags();
  }

  /**
   * Method under test: {@link AccessMethodMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass2() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = mock(LibraryClass.class);
    when(clazz2.getAccessFlags()).thenReturn(Integer.MIN_VALUE);

    // Act
    accessMethodMarker.visitAnyClass(clazz2);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz2).getAccessFlags();
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPrivateCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(2, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPrivateCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember2() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesPackageCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(0, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesPackageCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link AccessMethodMarker#visitAnyMember(Clazz, Member)}
   */
  @Test
  void testVisitAnyMember3() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setAccessesProtectedCode();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    AccessMethodMarker accessMethodMarker = new AccessMethodMarker();
    CodeAttribute codeAttribute = new CodeAttribute(1);
    accessMethodMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1));
    LibraryClass clazz2 = new LibraryClass();

    // Act
    accessMethodMarker.visitAnyMember(clazz2, new LibraryField(4, "Name", "Descriptor"));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programMethodOptimizationInfo).setAccessesProtectedCode();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link AccessMethodMarker#accessesPrivateCode(Method)}
   */
  @Test
  void testAccessesPrivateCode() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesPrivateCode(method));
  }

  /**
   * Method under test: {@link AccessMethodMarker#accessesPackageCode(Method)}
   */
  @Test
  void testAccessesPackageCode() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesPackageCode(method));
  }

  /**
   * Method under test: {@link AccessMethodMarker#accessesProtectedCode(Method)}
   */
  @Test
  void testAccessesProtectedCode() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(AccessMethodMarker.accessesProtectedCode(method));
  }
}
