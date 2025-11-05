package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ConstantElementValue;
import proguard.classfile.attribute.annotation.ElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.classfile.attribute.annotation.visitor.ElementValueVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.LocalVariableInfoVisitor;
import proguard.classfile.attribute.visitor.LocalVariableTypeInfoVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class TargetClassChangerDiffblueTest {
  /**
   * Method under test: {@link TargetClassChanger#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> targetClassChanger.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass);

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass);

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);

    LibraryClass libraryClass2 = new LibraryClass();
    libraryClass2.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass2);

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = new ProgramField();

    // Act
    targetClassChanger.visitProgramField(programClass, programField);

    // Assert
    assertNull(programField.referencedClass);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
    ProgramField programField = new ProgramField();
    programField.u2attributesCount = 0;
    programField.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitProgramField(programClass, programField);

    // Assert
    Clazz clazz = programField.referencedClass;
    assertTrue(clazz instanceof LibraryClass);
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo2);
    ProgramField programField = new ProgramField();
    programField.u2attributesCount = 0;
    programField.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitProgramField(programClass, programField);

    // Assert
    Clazz clazz = programField.referencedClass;
    assertTrue(clazz instanceof LibraryClass);
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField4() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();

    LibraryClass libraryClass = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = new ClassOptimizationInfo();
    libraryClass.setProcessingInfo(classOptimizationInfo);
    ProgramField programField = new ProgramField();
    programField.u2attributesCount = 0;
    programField.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitProgramField(programClass, programField);

    // Assert
    Clazz clazz = programField.referencedClass;
    assertTrue(clazz instanceof LibraryClass);
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ClassOptimizationInfo);
    assertSame(classOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doThrow(new UnsupportedOperationException("foo")).when(programMethod)
        .attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> targetClassChanger.visitProgramMethod(programClass, programMethod));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(programClassOptimizationInfo10);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;
    stringConstant.referencedMember = null;

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);

    LibraryClass targetClass10 = new LibraryClass();
    targetClass10.setProcessingInfo(programClassOptimizationInfo10);

    ProgramClassOptimizationInfo programClassOptimizationInfo11 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo11.setTargetClass(targetClass10);

    LibraryClass targetClass11 = new LibraryClass();
    targetClass11.setProcessingInfo(programClassOptimizationInfo11);

    ProgramClassOptimizationInfo programClassOptimizationInfo12 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo12.setTargetClass(targetClass11);

    LibraryClass targetClass12 = new LibraryClass();
    targetClass12.setProcessingInfo(programClassOptimizationInfo12);

    ProgramClassOptimizationInfo programClassOptimizationInfo13 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo13.setTargetClass(targetClass12);

    LibraryClass targetClass13 = new LibraryClass();
    targetClass13.setProcessingInfo(programClassOptimizationInfo13);

    ProgramClassOptimizationInfo programClassOptimizationInfo14 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo14.setTargetClass(targetClass13);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(programClassOptimizationInfo14);
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();
    refConstant.referencedClass = libraryClass;
    refConstant.referencedMethod = null;

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);

    LibraryClass targetClass10 = new LibraryClass();
    targetClass10.setProcessingInfo(programClassOptimizationInfo10);

    ProgramClassOptimizationInfo programClassOptimizationInfo11 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo11.setTargetClass(targetClass10);

    LibraryClass targetClass11 = new LibraryClass();
    targetClass11.setProcessingInfo(programClassOptimizationInfo11);

    ProgramClassOptimizationInfo programClassOptimizationInfo12 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo12.setTargetClass(targetClass11);

    LibraryClass targetClass12 = new LibraryClass();
    targetClass12.setProcessingInfo(programClassOptimizationInfo12);

    ProgramClassOptimizationInfo programClassOptimizationInfo13 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo13.setTargetClass(targetClass12);

    LibraryClass targetClass13 = new LibraryClass();
    targetClass13.setProcessingInfo(programClassOptimizationInfo13);

    ProgramClassOptimizationInfo programClassOptimizationInfo14 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo14.setTargetClass(targetClass13);

    LibraryClass targetClass14 = new LibraryClass();
    targetClass14.setProcessingInfo(programClassOptimizationInfo14);

    ProgramClassOptimizationInfo programClassOptimizationInfo15 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo15.setTargetClass(targetClass14);

    LibraryClass targetClass15 = new LibraryClass();
    targetClass15.setProcessingInfo(programClassOptimizationInfo15);

    ProgramClassOptimizationInfo programClassOptimizationInfo16 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo16.setTargetClass(targetClass15);

    LibraryClass targetClass16 = new LibraryClass();
    targetClass16.setProcessingInfo(programClassOptimizationInfo16);

    ProgramClassOptimizationInfo programClassOptimizationInfo17 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo17.setTargetClass(targetClass16);

    LibraryClass targetClass17 = new LibraryClass();
    targetClass17.setProcessingInfo(programClassOptimizationInfo17);

    ProgramClassOptimizationInfo programClassOptimizationInfo18 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo18.setTargetClass(targetClass17);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(programClassOptimizationInfo18);
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedClass = libraryClass;
    refConstant.referencedField = null;

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    targetClassChanger.visitClassConstant(clazz, classConstant);

    // Assert
    assertNull(classConstant.referencedClass);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo3);
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    targetClassChanger.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTableAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute = mock(LocalVariableTableAttribute.class);
    doNothing().when(localVariableTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableInfoVisitor>any());

    // Act
    targetClassChanger.visitLocalVariableTableAttribute(clazz, method, codeAttribute, localVariableTableAttribute);

    // Assert
    verify(localVariableTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute = mock(LocalVariableTypeTableAttribute.class);
    doNothing().when(localVariableTypeTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableTypeInfoVisitor>any());

    // Act
    targetClassChanger.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        localVariableTypeTableAttribute);

    // Assert
    verify(localVariableTypeTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableTypeInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(classOptimizationInfo);
    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz2).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute4() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo())
        .thenThrow(new UnsupportedOperationException("Unexpected error while adapting signatures for merged classes:"));
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(classOptimizationInfo);
    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    when(signatureAttribute.getSignature(Mockito.<Clazz>any())).thenReturn("Signature");
    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute));
    verify(clazz).getName();
    verify(signatureAttribute).getSignature(isA(Clazz.class));
    verify(classOptimizationInfo, atLeast(1)).getTargetClass();
    verify(clazz2, atLeast(1)).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute = mock(RuntimeInvisibleAnnotationsAttribute.class);
    doNothing().when(annotationsAttribute).annotationsAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    targetClassChanger.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    verify(annotationsAttribute).annotationsAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   */
  @Test
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute = mock(
        RuntimeInvisibleParameterAnnotationsAttribute.class);
    doNothing().when(parameterAnnotationsAttribute)
        .annotationsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AnnotationVisitor>any());

    // Act
    targetClassChanger.visitAnyParameterAnnotationsAttribute(clazz, method, parameterAnnotationsAttribute);

    // Assert
    verify(parameterAnnotationsAttribute).annotationsAccept(isA(Clazz.class), isA(Method.class),
        isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing().when(annotationDefaultAttribute)
        .defaultValueAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    verify(annotationDefaultAttribute).defaultValueAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  void testVisitRecordComponentInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = mock(RecordComponentInfo.class);
    doNothing().when(recordComponentInfo).attributesAccept(Mockito.<Clazz>any(), Mockito.<AttributeVisitor>any());

    // Act
    targetClassChanger.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    verify(recordComponentInfo).attributesAccept(isA(Clazz.class), isA(AttributeVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    // Act
    targetClassChanger.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    assertNull(localVariableInfo.referencedClass);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo4);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    localVariableInfo.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    Clazz clazz2 = localVariableInfo.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    localVariableInfo.referencedClass = libraryClass;

    // Act
    targetClassChanger.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    Clazz clazz2 = localVariableInfo.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  void testVisitAnnotation() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = mock(Annotation.class);
    doNothing().when(annotation).elementValuesAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnnotation(clazz, annotation);

    // Assert
    verify(annotation).elementValuesAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnyElementValue(Clazz, Annotation, ElementValue)}
   */
  @Test
  void testVisitAnyElementValue() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);

    LibraryClass targetClass10 = new LibraryClass();
    targetClass10.setProcessingInfo(programClassOptimizationInfo10);

    ProgramClassOptimizationInfo programClassOptimizationInfo11 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo11.setTargetClass(targetClass10);

    LibraryClass targetClass11 = new LibraryClass();
    targetClass11.setProcessingInfo(programClassOptimizationInfo11);

    ProgramClassOptimizationInfo programClassOptimizationInfo12 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo12.setTargetClass(targetClass11);

    LibraryClass targetClass12 = new LibraryClass();
    targetClass12.setProcessingInfo(programClassOptimizationInfo12);

    ProgramClassOptimizationInfo programClassOptimizationInfo13 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo13.setTargetClass(targetClass12);

    LibraryClass targetClass13 = new LibraryClass();
    targetClass13.setProcessingInfo(programClassOptimizationInfo13);

    ProgramClassOptimizationInfo programClassOptimizationInfo14 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo14.setTargetClass(targetClass13);

    LibraryClass targetClass14 = new LibraryClass();
    targetClass14.setProcessingInfo(programClassOptimizationInfo14);

    ProgramClassOptimizationInfo programClassOptimizationInfo15 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo15.setTargetClass(targetClass14);

    LibraryClass targetClass15 = new LibraryClass();
    targetClass15.setProcessingInfo(programClassOptimizationInfo15);

    ProgramClassOptimizationInfo programClassOptimizationInfo16 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo16.setTargetClass(targetClass15);

    LibraryClass targetClass16 = new LibraryClass();
    targetClass16.setProcessingInfo(programClassOptimizationInfo16);

    ProgramClassOptimizationInfo programClassOptimizationInfo17 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo17.setTargetClass(targetClass16);

    LibraryClass targetClass17 = new LibraryClass();
    targetClass17.setProcessingInfo(programClassOptimizationInfo17);

    ProgramClassOptimizationInfo programClassOptimizationInfo18 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo18.setTargetClass(targetClass17);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(programClassOptimizationInfo18);
    AnnotationElementValue elementValue = new AnnotationElementValue();
    elementValue.referencedClass = libraryClass;
    elementValue.referencedMethod = null;

    // Act
    targetClassChanger.visitAnyElementValue(clazz, annotation, elementValue);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   */
  @Test
  void testVisitConstantElementValue() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);

    LibraryClass targetClass10 = new LibraryClass();
    targetClass10.setProcessingInfo(programClassOptimizationInfo10);

    ProgramClassOptimizationInfo programClassOptimizationInfo11 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo11.setTargetClass(targetClass10);

    LibraryClass targetClass11 = new LibraryClass();
    targetClass11.setProcessingInfo(programClassOptimizationInfo11);

    ProgramClassOptimizationInfo programClassOptimizationInfo12 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo12.setTargetClass(targetClass11);

    LibraryClass targetClass12 = new LibraryClass();
    targetClass12.setProcessingInfo(programClassOptimizationInfo12);

    ProgramClassOptimizationInfo programClassOptimizationInfo13 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo13.setTargetClass(targetClass12);

    LibraryClass targetClass13 = new LibraryClass();
    targetClass13.setProcessingInfo(programClassOptimizationInfo13);

    ProgramClassOptimizationInfo programClassOptimizationInfo14 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo14.setTargetClass(targetClass13);

    LibraryClass targetClass14 = new LibraryClass();
    targetClass14.setProcessingInfo(programClassOptimizationInfo14);

    ProgramClassOptimizationInfo programClassOptimizationInfo15 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo15.setTargetClass(targetClass14);

    LibraryClass targetClass15 = new LibraryClass();
    targetClass15.setProcessingInfo(programClassOptimizationInfo15);

    ProgramClassOptimizationInfo programClassOptimizationInfo16 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo16.setTargetClass(targetClass15);

    LibraryClass targetClass16 = new LibraryClass();
    targetClass16.setProcessingInfo(programClassOptimizationInfo16);

    ProgramClassOptimizationInfo programClassOptimizationInfo17 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo17.setTargetClass(targetClass16);

    LibraryClass targetClass17 = new LibraryClass();
    targetClass17.setProcessingInfo(programClassOptimizationInfo17);

    ProgramClassOptimizationInfo programClassOptimizationInfo18 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo18.setTargetClass(targetClass17);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(programClassOptimizationInfo18);
    ConstantElementValue constantElementValue = new ConstantElementValue('A');
    constantElementValue.referencedClass = libraryClass;
    constantElementValue.referencedMethod = null;

    // Act
    targetClassChanger.visitConstantElementValue(clazz, annotation, constantElementValue);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  void testVisitAnnotationElementValue() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = mock(AnnotationElementValue.class);
    doNothing().when(annotationElementValue).annotationAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    targetClassChanger.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    verify(annotationElementValue).annotationAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  void testVisitArrayElementValue() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue = mock(ArrayElementValue.class);
    doNothing().when(arrayElementValue)
        .elementValuesAccept(Mockito.<Clazz>any(), Mockito.<Annotation>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    verify(arrayElementValue).elementValuesAccept(isA(Clazz.class), isA(Annotation.class),
        isA(ElementValueVisitor.class));
  }

  /**
   * Method under test:
   * {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  void testVisitArrayElementValue2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo.setTargetClass(null);

    LibraryClass targetClass = new LibraryClass();
    targetClass.setProcessingInfo(programClassOptimizationInfo);

    ProgramClassOptimizationInfo programClassOptimizationInfo2 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo2.setTargetClass(targetClass);

    LibraryClass targetClass2 = new LibraryClass();
    targetClass2.setProcessingInfo(programClassOptimizationInfo2);

    ProgramClassOptimizationInfo programClassOptimizationInfo3 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo3.setTargetClass(targetClass2);

    LibraryClass targetClass3 = new LibraryClass();
    targetClass3.setProcessingInfo(programClassOptimizationInfo3);

    ProgramClassOptimizationInfo programClassOptimizationInfo4 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo4.setTargetClass(targetClass3);

    LibraryClass targetClass4 = new LibraryClass();
    targetClass4.setProcessingInfo(programClassOptimizationInfo4);

    ProgramClassOptimizationInfo programClassOptimizationInfo5 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo5.setTargetClass(targetClass4);

    LibraryClass targetClass5 = new LibraryClass();
    targetClass5.setProcessingInfo(programClassOptimizationInfo5);

    ProgramClassOptimizationInfo programClassOptimizationInfo6 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo6.setTargetClass(targetClass5);

    LibraryClass targetClass6 = new LibraryClass();
    targetClass6.setProcessingInfo(programClassOptimizationInfo6);

    ProgramClassOptimizationInfo programClassOptimizationInfo7 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo7.setTargetClass(targetClass6);

    LibraryClass targetClass7 = new LibraryClass();
    targetClass7.setProcessingInfo(programClassOptimizationInfo7);

    ProgramClassOptimizationInfo programClassOptimizationInfo8 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo8.setTargetClass(targetClass7);

    LibraryClass targetClass8 = new LibraryClass();
    targetClass8.setProcessingInfo(programClassOptimizationInfo8);

    ProgramClassOptimizationInfo programClassOptimizationInfo9 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo9.setTargetClass(targetClass8);

    LibraryClass targetClass9 = new LibraryClass();
    targetClass9.setProcessingInfo(programClassOptimizationInfo9);

    ProgramClassOptimizationInfo programClassOptimizationInfo10 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo10.setTargetClass(targetClass9);

    LibraryClass targetClass10 = new LibraryClass();
    targetClass10.setProcessingInfo(programClassOptimizationInfo10);

    ProgramClassOptimizationInfo programClassOptimizationInfo11 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo11.setTargetClass(targetClass10);

    LibraryClass targetClass11 = new LibraryClass();
    targetClass11.setProcessingInfo(programClassOptimizationInfo11);

    ProgramClassOptimizationInfo programClassOptimizationInfo12 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo12.setTargetClass(targetClass11);

    LibraryClass targetClass12 = new LibraryClass();
    targetClass12.setProcessingInfo(programClassOptimizationInfo12);

    ProgramClassOptimizationInfo programClassOptimizationInfo13 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo13.setTargetClass(targetClass12);

    LibraryClass targetClass13 = new LibraryClass();
    targetClass13.setProcessingInfo(programClassOptimizationInfo13);

    ProgramClassOptimizationInfo programClassOptimizationInfo14 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo14.setTargetClass(targetClass13);

    LibraryClass targetClass14 = new LibraryClass();
    targetClass14.setProcessingInfo(programClassOptimizationInfo14);

    ProgramClassOptimizationInfo programClassOptimizationInfo15 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo15.setTargetClass(targetClass14);

    LibraryClass targetClass15 = new LibraryClass();
    targetClass15.setProcessingInfo(programClassOptimizationInfo15);

    ProgramClassOptimizationInfo programClassOptimizationInfo16 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo16.setTargetClass(targetClass15);

    LibraryClass targetClass16 = new LibraryClass();
    targetClass16.setProcessingInfo(programClassOptimizationInfo16);

    ProgramClassOptimizationInfo programClassOptimizationInfo17 = new ProgramClassOptimizationInfo();
    programClassOptimizationInfo17.setTargetClass(targetClass16);

    LibraryClass targetClass17 = new LibraryClass();
    targetClass17.setProcessingInfo(programClassOptimizationInfo17);
    ProgramClassOptimizationInfo programClassOptimizationInfo18 = mock(ProgramClassOptimizationInfo.class);
    when(programClassOptimizationInfo18.getTargetClass()).thenReturn(null);
    doNothing().when(programClassOptimizationInfo18).setTargetClass(Mockito.<Clazz>any());
    programClassOptimizationInfo18.setTargetClass(targetClass17);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(programClassOptimizationInfo18);
    ArrayElementValue arrayElementValue = new ArrayElementValue();
    arrayElementValue.referencedClass = libraryClass;
    arrayElementValue.referencedMethod = null;

    // Act
    targetClassChanger.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    verify(programClassOptimizationInfo18).getTargetClass();
    verify(programClassOptimizationInfo18).setTargetClass(isA(Clazz.class));
  }
}
