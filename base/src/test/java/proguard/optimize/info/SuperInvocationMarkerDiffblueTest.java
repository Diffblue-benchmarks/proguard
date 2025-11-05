package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;

class SuperInvocationMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link SuperInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    SuperInvocationMarker superInvocationMarker = new SuperInvocationMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");

    // Act
    superInvocationMarker.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test:
   * {@link SuperInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    SuperInvocationMarker superInvocationMarker = new SuperInvocationMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("<init>");

    // Act
    superInvocationMarker.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test: {@link SuperInvocationMarker#invokesSuperMethods(Method)}
   */
  @Test
  void testInvokesSuperMethods() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(SuperInvocationMarker.invokesSuperMethods(method));
  }
}
