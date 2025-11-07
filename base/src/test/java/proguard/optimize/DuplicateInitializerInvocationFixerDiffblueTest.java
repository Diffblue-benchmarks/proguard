package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;

class DuplicateInitializerInvocationFixerDiffblueTest {
  /**
   * Test {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <p>
   * Method under test: {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateInitializerInvocationFixer.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant() {
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

  /**
   * Test {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'Name'; when LibraryClass getName(int) return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateInitializerInvocationFixer.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenName_whenLibraryClassGetNameReturnName() {
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
   * Test {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@code Type}.</li>
   *   <li>Then calls {@link LibraryClass#getType(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DuplicateInitializerInvocationFixer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'Type'; then calls getType(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateInitializerInvocationFixer.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenType_thenCallsGetType() {
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
}
