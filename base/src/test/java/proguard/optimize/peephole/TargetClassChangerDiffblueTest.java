package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ConstantElementValue;
import proguard.classfile.attribute.annotation.ElementValue;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.classfile.attribute.annotation.visitor.ElementValueVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;
import proguard.util.Processable;
import proguard.util.SimpleProcessable;

class TargetClassChangerDiffblueTest {
  /**
   * Test {@link TargetClassChanger#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.peephole.TargetClassChanger.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> targetClassChanger.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass() {
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
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@link Clazz} {@link Processable#getProcessingInfo()} return {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given Clazz getProcessingInfo() return ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_givenClazzGetProcessingInfoReturnClassOptimizationInfo() {
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
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#getTargetClass()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then calls getTargetClass()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_thenCallsGetTargetClass() {
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
   * Test {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField() {
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
  }

  /**
   * Test {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then {@link ProgramField#ProgramField()} {@link ProgramField#referencedClass} {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then ProgramField() referencedClass LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_thenProgramFieldReferencedClassLibraryClass() {
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

    // Assert that nothing has changed
    Clazz clazz = programField.referencedClass;
    assertTrue(clazz instanceof LibraryClass);
    assertTrue(clazz.getProcessingInfo() instanceof ProgramClassOptimizationInfo);
  }

  /**
   * Test {@link TargetClassChanger#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenThrowUnsupportedOperationException() {
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
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenCallsGetProcessingInfo() {
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
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsGetProcessingInfo() {
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
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitFieldrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant)"})
  void testVisitFieldrefConstant_thenCallsGetProcessingInfo() {
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
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant() {
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
  }

  /**
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} {@link ClassConstant#referencedClass} {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() referencedClass LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenClassConstantReferencedClassLibraryClass() {
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

    // Assert that nothing has changed
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertTrue(clazz2.getProcessingInfo() instanceof ProgramClassOptimizationInfo);
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenThrow(new UnsupportedOperationException("foo"));
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(classOptimizationInfo);
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[]{clazz2};

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute));
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz2).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   * <ul>
   *   <li>Then calls {@link AnnotationsAttribute#annotationsAccept(Clazz, AnnotationVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then calls annotationsAccept(Clazz, AnnotationVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnyAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.AnnotationsAttribute)"})
  void testVisitAnyAnnotationsAttribute_thenCallsAnnotationsAccept() {
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
   * Test {@link TargetClassChanger#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#defaultValueAccept(Clazz, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls defaultValueAccept(Clazz, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnnotationDefaultAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute_thenCallsDefaultValueAccept() {
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
   * Test {@link TargetClassChanger#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   * <ul>
   *   <li>Then calls {@link RecordComponentInfo#attributesAccept(Clazz, AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  @DisplayName("Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then calls attributesAccept(Clazz, AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitRecordComponentInfo(proguard.classfile.Clazz, proguard.classfile.attribute.RecordComponentInfo)"})
  void testVisitRecordComponentInfo_thenCallsAttributesAccept() {
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
   * Test {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitLocalVariableInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableInfo)"})
  void testVisitLocalVariableInfo() {
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
  }

  /**
   * Test {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <p>
   * Method under test: {@link TargetClassChanger#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitLocalVariableInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableInfo)"})
  void testVisitLocalVariableInfo2() {
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

    // Assert that nothing has changed
    Clazz clazz2 = localVariableInfo.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertTrue(clazz2.getProcessingInfo() instanceof ProgramClassOptimizationInfo);
  }

  /**
   * Test {@link TargetClassChanger#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code annotation}.
   * <ul>
   *   <li>Then calls {@link Annotation#elementValuesAccept(Clazz, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then calls elementValuesAccept(Clazz, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnnotation(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenCallsElementValuesAccept() {
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
   * Test {@link TargetClassChanger#visitAnyElementValue(Clazz, Annotation, ElementValue)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnyElementValue(Clazz, Annotation, ElementValue)}
   */
  @Test
  @DisplayName("Test visitAnyElementValue(Clazz, Annotation, ElementValue); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnyElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ElementValue)"})
  void testVisitAnyElementValue_thenCallsGetProcessingInfo() {
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
   * Test {@link TargetClassChanger#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitConstantElementValue(Clazz, Annotation, ConstantElementValue); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitConstantElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ConstantElementValue)"})
  void testVisitConstantElementValue_thenCallsGetProcessingInfo() {
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
   * Test {@link TargetClassChanger#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}.
   * <ul>
   *   <li>Then calls {@link AnnotationElementValue#annotationAccept(Clazz, AnnotationVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then calls annotationAccept(Clazz, AnnotationVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitAnnotationElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.AnnotationElementValue)"})
  void testVisitAnnotationElementValue_thenCallsAnnotationAccept() {
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
   * Test {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   * <ul>
   *   <li>Then calls {@link ArrayElementValue#elementValuesAccept(Clazz, Annotation, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then calls elementValuesAccept(Clazz, Annotation, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitArrayElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ArrayElementValue)"})
  void testVisitArrayElementValue_thenCallsElementValuesAccept() {
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
   * Test {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   * <ul>
   *   <li>Then calls {@link ProgramClassOptimizationInfo#getTargetClass()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then calls getTargetClass()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.TargetClassChanger.visitArrayElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ArrayElementValue)"})
  void testVisitArrayElementValue_thenCallsGetTargetClass() {
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
