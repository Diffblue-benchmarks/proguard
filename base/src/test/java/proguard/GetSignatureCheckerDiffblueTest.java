package proguard;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

class GetSignatureCheckerDiffblueTest {
  /**
   * Test {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   * <ul>
   *   <li>Given {@code Class Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'Class Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.GetSignatureChecker.visitMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodrefConstant)"})
  void testVisitMethodrefConstant_givenClassName() {
    // Arrange
    GetSignatureChecker getSignatureChecker = new GetSignatureChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");

    // Act
    getSignatureChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
  }

  /**
   * Test {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   * <ul>
   *   <li>Given {@code getType}.</li>
   *   <li>Then calls {@link LibraryClass#getName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'getType'; then calls getName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.GetSignatureChecker.visitMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodrefConstant)"})
  void testVisitMethodrefConstant_givenGetType_thenCallsGetName() {
    // Arrange
    GetSignatureChecker getSignatureChecker = new GetSignatureChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("getType");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getSignatureChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(0));
  }

  /**
   * Test {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code Name}.</li>
   *   <li>Then calls {@link LibraryClass#getName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GetSignatureChecker#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); when LibraryClass getName(int) return 'Name'; then calls getName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.GetSignatureChecker.visitMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodrefConstant)"})
  void testVisitMethodrefConstant_whenLibraryClassGetNameReturnName_thenCallsGetName() {
    // Arrange
    GetSignatureChecker getSignatureChecker = new GetSignatureChecker(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getClassName(anyInt())).thenReturn("java/lang/Class");

    // Act
    getSignatureChecker.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    verify(clazz).getName(eq(0));
  }
}
