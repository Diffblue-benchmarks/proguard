package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;

class ParameterUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link ParameterUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    ParameterUsageMarker parameterUsageMarker = new ParameterUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.mayHaveImplementations(Mockito.<Method>any())).thenReturn(true);
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).updateUsedParameters(anyLong());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    parameterUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryClass).mayHaveImplementations(isA(Method.class));
    verify(programMethodOptimizationInfo).updateUsedParameters(eq(-1L));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ParameterUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    ParameterUsageMarker parameterUsageMarker = new ParameterUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.mayHaveImplementations(Mockito.<Method>any())).thenReturn(false);

    // Act
    parameterUsageMarker.visitLibraryMethod(libraryClass, mock(LibraryMethod.class));

    // Assert
    verify(libraryClass).mayHaveImplementations(isA(Method.class));
  }

  /**
   * Method under test: {@link ParameterUsageMarker#getParameterSize(Method)}
   */
  @Test
  void testGetParameterSize() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(0, ParameterUsageMarker.getParameterSize(method));
  }

  /**
   * Method under test:
   * {@link ParameterUsageMarker#markParameterUsed(Method, int)}
   */
  @Test
  void testMarkParameterUsed() {
    // Arrange
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setParameterUsed(anyInt());
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    ParameterUsageMarker.markParameterUsed(method, 1);

    // Assert
    verify(programMethodOptimizationInfo).setParameterUsed(eq(1));
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test: {@link ParameterUsageMarker#hasUnusedParameters(Method)}
   */
  @Test
  void testHasUnusedParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(ParameterUsageMarker.hasUnusedParameters(method));
  }

  /**
   * Method under test: {@link ParameterUsageMarker#isParameterUsed(Method, int)}
   */
  @Test
  void testIsParameterUsed() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterUsageMarker.isParameterUsed(method, 1));
  }

  /**
   * Method under test: {@link ParameterUsageMarker#getUsedParameters(Method)}
   */
  @Test
  void testGetUsedParameters() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterUsageMarker.getUsedParameters(method));
  }
}
