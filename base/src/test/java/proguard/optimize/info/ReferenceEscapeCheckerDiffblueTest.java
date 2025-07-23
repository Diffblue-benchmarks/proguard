package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.evaluation.PartialEvaluator;

class ReferenceEscapeCheckerDiffblueTest {
  /**
   * Test {@link ReferenceEscapeChecker#ReferenceEscapeChecker()}.
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#ReferenceEscapeChecker()}
   */
  @Test
  @DisplayName("Test new ReferenceEscapeChecker()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReferenceEscapeChecker.<init>()"})
  void testNewReferenceEscapeChecker() {
    // Arrange and Act
    ReferenceEscapeChecker actualReferenceEscapeChecker = new ReferenceEscapeChecker();

    // Assert
    assertFalse(actualReferenceEscapeChecker.isInstanceEscaping(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceExternal(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceModified(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceReturned(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#ReferenceEscapeChecker(PartialEvaluator, boolean)}.
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#ReferenceEscapeChecker(PartialEvaluator,
   * boolean)}
   */
  @Test
  @DisplayName("Test new ReferenceEscapeChecker(PartialEvaluator, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReferenceEscapeChecker.<init>(PartialEvaluator, boolean)"})
  void testNewReferenceEscapeChecker2() {
    // Arrange and Act
    ReferenceEscapeChecker actualReferenceEscapeChecker =
        new ReferenceEscapeChecker(new PartialEvaluator(), true);

    // Assert
    assertFalse(actualReferenceEscapeChecker.isInstanceEscaping(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceExternal(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceModified(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceReturned(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#isInstanceEscaping(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#isInstanceEscaping(int)}
   */
  @Test
  @DisplayName("Test isInstanceEscaping(int); when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReferenceEscapeChecker.isInstanceEscaping(int)"})
  void testIsInstanceEscaping_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReferenceEscapeChecker().isInstanceEscaping(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#isInstanceReturned(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#isInstanceReturned(int)}
   */
  @Test
  @DisplayName("Test isInstanceReturned(int); when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReferenceEscapeChecker.isInstanceReturned(int)"})
  void testIsInstanceReturned_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReferenceEscapeChecker().isInstanceReturned(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#isInstanceModified(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#isInstanceModified(int)}
   */
  @Test
  @DisplayName("Test isInstanceModified(int); when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReferenceEscapeChecker.isInstanceModified(int)"})
  void testIsInstanceModified_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReferenceEscapeChecker().isInstanceModified(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#isInstanceExternal(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#isInstanceExternal(int)}
   */
  @Test
  @DisplayName("Test isInstanceExternal(int); when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ReferenceEscapeChecker.isInstanceExternal(int)"})
  void testIsInstanceExternal_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ReferenceEscapeChecker().isInstanceExternal(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReferenceEscapeChecker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(
        clazz,
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getType(eq(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReferenceEscapeChecker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenEmptyString() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(
        clazz,
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getType(eq(1));
  }

  /**
   * Test {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code Type}.
   *   <li>Then calls {@link LibraryClass#getType(int)}.
   * </ul>
   *
   * <p>Method under test: {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'Type'; then calls getType(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ReferenceEscapeChecker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenType_thenCallsGetType() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getType(eq(0));
  }
}
