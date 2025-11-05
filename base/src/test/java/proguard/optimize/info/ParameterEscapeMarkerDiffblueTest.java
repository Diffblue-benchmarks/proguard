package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;

class ParameterEscapeMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(new MethodOptimizationInfo());

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert that nothing has changed
    verify(programClass).addSubClass(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(true);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(1L);

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(1L);

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    parameterEscapeMarker.visitFieldrefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    Method referencedMethod = mock(Method.class);
    doNothing().when(referencedMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(referencedMethod.getAccessFlags()).thenReturn(1);
    when(referencedMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(referencedMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert
    verify(referencedMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(referencedMethod).getAccessFlags();
    verify(referencedMethod).getName(isA(Clazz.class));
    verify(methodOptimizationInfo).modifiesAnything();
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#isParameterEscaping(Method, int)}
   */
  @Test
  void testIsParameterEscaping() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterEscaping(method, 1));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#getEscapingParameters(Method)}
   */
  @Test
  void testGetEscapingParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getEscapingParameters(method));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#isParameterReturned(Method, int)}
   */
  @Test
  void testIsParameterReturned() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterReturned(method, 1));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#getReturnedParameters(Method)}
   */
  @Test
  void testGetReturnedParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getReturnedParameters(method));
  }

  /**
   * Method under test: {@link ParameterEscapeMarker#returnsNewInstances(Method)}
   */
  @Test
  void testReturnsNewInstances() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsNewInstances(method));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#returnsExternalValues(Method)}
   */
  @Test
  void testReturnsExternalValues() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsExternalValues(method));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#isParameterModified(Method, int)}
   */
  @Test
  void testIsParameterModified() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterModified(method, 1));
  }

  /**
   * Method under test:
   * {@link ParameterEscapeMarker#getModifiedParameters(Method)}
   */
  @Test
  void testGetModifiedParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getModifiedParameters(method));
  }

  /**
   * Method under test: {@link ParameterEscapeMarker#modifiesAnything(Method)}
   */
  @Test
  void testModifiesAnything() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.modifiesAnything(method));
  }
}
