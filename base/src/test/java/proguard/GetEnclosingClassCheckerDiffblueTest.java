package proguard;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.util.WarningPrinter;

class GetEnclosingClassCheckerDiffblueTest {
  /**
   * Test {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code Class Name}.
   * </ul>
   *
   * <p>Method under test: {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz,
   * MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'Class Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GetEnclosingClassChecker.visitMethodrefConstant(Clazz, MethodrefConstant)"
  })
  void testVisitMethodrefConstant_givenClassName() {
    // Arrange
    GetEnclosingClassChecker getEnclosingClassChecker =
        new GetEnclosingClassChecker(new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");

    // Act
    getEnclosingClassChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
  }

  /**
   * Test {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code getEnclosingClass}.
   *   <li>Then calls {@link LibraryClass#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz,
   * MethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'getEnclosingClass'; then calls getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GetEnclosingClassChecker.visitMethodrefConstant(Clazz, MethodrefConstant)"
  })
  void testVisitMethodrefConstant_givenGetEnclosingClass_thenCallsGetName() {
    // Arrange
    GetEnclosingClassChecker getEnclosingClassChecker =
        new GetEnclosingClassChecker(new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("getEnclosingClass");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getEnclosingClassChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(0));
  }

  /**
   * Test {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code Name}.
   *   <li>Then calls {@link LibraryClass#getName(int)}.
   * </ul>
   *
   * <p>Method under test: {@link GetEnclosingClassChecker#visitMethodrefConstant(Clazz,
   * MethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodrefConstant(Clazz, MethodrefConstant); when LibraryClass getName(int) return 'Name'; then calls getName(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GetEnclosingClassChecker.visitMethodrefConstant(Clazz, MethodrefConstant)"
  })
  void testVisitMethodrefConstant_whenLibraryClassGetNameReturnName_thenCallsGetName() {
    // Arrange
    GetEnclosingClassChecker getEnclosingClassChecker =
        new GetEnclosingClassChecker(new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getEnclosingClassChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName(eq(0));
  }
}
