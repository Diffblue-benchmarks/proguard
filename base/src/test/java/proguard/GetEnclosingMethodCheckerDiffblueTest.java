package proguard;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.util.WarningPrinter;

class GetEnclosingMethodCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link GetEnclosingMethodChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  void testVisitMethodrefConstant() {
    // Arrange
    GetEnclosingMethodChecker getEnclosingMethodChecker = new GetEnclosingMethodChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");

    // Act
    getEnclosingMethodChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
  }

  /**
   * Method under test:
   * {@link GetEnclosingMethodChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  void testVisitMethodrefConstant2() {
    // Arrange
    GetEnclosingMethodChecker getEnclosingMethodChecker = new GetEnclosingMethodChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getEnclosingMethodChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test:
   * {@link GetEnclosingMethodChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  void testVisitMethodrefConstant3() {
    // Arrange
    GetEnclosingMethodChecker getEnclosingMethodChecker = new GetEnclosingMethodChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("getEnclosingConstructor");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getEnclosingMethodChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(0));
  }
}
