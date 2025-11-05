package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;

class DuplicateInitializerInvocationFixerDiffblueTest {
  /**
   * Method under test:
   * {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    DuplicateInitializerInvocationFixer duplicateInitializerInvocationFixer = new DuplicateInitializerInvocationFixer();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");

    // Act
    duplicateInitializerInvocationFixer.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test:
   * {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    DuplicateInitializerInvocationFixer duplicateInitializerInvocationFixer = new DuplicateInitializerInvocationFixer();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");
    when(clazz.getName(anyInt())).thenReturn("<init>");

    // Act
    duplicateInitializerInvocationFixer.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }

  /**
   * Method under test:
   * {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant3() {
    // Arrange
    DuplicateInitializerInvocationFixer duplicateInitializerInvocationFixer = new DuplicateInitializerInvocationFixer();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");
    when(clazz.getName(anyInt())).thenReturn("<init>");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    duplicateInitializerInvocationFixer.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "<init>", "<init>")));

    // Assert
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
  }
}
