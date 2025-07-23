package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata.Flavor;

class KotlinSyntheticClassFixerDiffblueTest {
  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinClassMetadata(Clazz,
   * KotlinClassKindMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinClassMetadata(Clazz,
   * KotlinClassKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)"
  })
  void testVisitKotlinClassMetadata_thenCallsGetProcessingInfo() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo("$DefaultImpls");
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.referencedDefaultImplsClass = new LibraryClass();
    kotlinClassKindMetadata.referencedClass = libraryClass;

    // Act
    kotlinSyntheticClassFixer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(
        clazz,
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.LAMBDA));

    // Assert that nothing has changed
    assertNull(clazz.getProcessingInfo());
  }

  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata2() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("Processing Info");

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(
        clazz,
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.WHEN_MAPPINGS));

    // Assert
    assertEquals("Processing Info$WhenMappings", clazz.getProcessingInfo());
  }

  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata3() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("$WhenMappings");

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(
        clazz,
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.WHEN_MAPPINGS));

    // Assert that nothing has changed
    assertEquals("$WhenMappings", clazz.getProcessingInfo());
  }

  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then LibraryClass() ProcessingInfo is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata_thenLibraryClassProcessingInfoIsNull() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(
        clazz,
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.REGULAR));

    // Assert that nothing has changed
    assertNull(clazz.getProcessingInfo());
  }

  /**
   * Test {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <ul>
   *   <li>Then {@link ProgramClass#ProgramClass()} ProcessingInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then ProgramClass() ProcessingInfo is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata_thenProgramClassProcessingInfoIsNull() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    ProgramClass clazz = new ProgramClass();

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(
        clazz,
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.LAMBDA));

    // Assert that nothing has changed
    assertNull(clazz.getProcessingInfo());
  }
}
