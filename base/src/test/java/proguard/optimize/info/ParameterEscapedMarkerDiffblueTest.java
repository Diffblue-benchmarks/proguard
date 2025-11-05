package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.visitor.ClassVisitor;

class ParameterEscapedMarkerDiffblueTest {
  /**
   * Method under test: {@link ParameterEscapedMarker#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    ClassPool classPool = mock(ClassPool.class);
    doNothing().when(classPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    parameterEscapedMarker.visitClassPool(classPool);

    // Assert
    verify(classPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    parameterEscapedMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert that nothing has changed
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    parameterEscapedMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert that nothing has changed
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ParameterEscapedMarker#hasParameterEscaped(Method, int)}
   */
  @Test
  void testHasParameterEscaped() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapedMarker.hasParameterEscaped(method, 1));
  }

  /**
   * Method under test:
   * {@link ParameterEscapedMarker#getEscapedParameters(Method)}
   */
  @Test
  void testGetEscapedParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapedMarker.getEscapedParameters(method));
  }
}
