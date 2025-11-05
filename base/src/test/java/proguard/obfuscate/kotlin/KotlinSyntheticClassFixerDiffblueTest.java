package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;

class KotlinSyntheticClassFixerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinSyntheticClassFixer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo("$DefaultImpls");
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    kotlinClassKindMetadata.referencedDefaultImplsClass = new LibraryClass();
    kotlinClassKindMetadata.referencedClass = libraryClass;

    // Act
    kotlinSyntheticClassFixer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();
    LibraryClass clazz = new LibraryClass();

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(clazz, new KotlinSyntheticClassKindMetadata(
        new int[]{1, -1, 1, -1}, 1, "Xs", "Pn", KotlinSyntheticClassKindMetadata.Flavor.REGULAR));

    // Assert that nothing has changed
    assertNull(clazz.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata2() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("Processing Info");

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(clazz, new KotlinSyntheticClassKindMetadata(
        new int[]{1, -1, 1, -1}, 1, "Xs", "Pn", KotlinSyntheticClassKindMetadata.Flavor.WHEN_MAPPINGS));

    // Assert
    assertEquals("Processing Info$WhenMappings", clazz.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link KotlinSyntheticClassFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata3() {
    // Arrange
    KotlinSyntheticClassFixer kotlinSyntheticClassFixer = new KotlinSyntheticClassFixer();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo("$WhenMappings");

    // Act
    kotlinSyntheticClassFixer.visitKotlinSyntheticClassMetadata(clazz, new KotlinSyntheticClassKindMetadata(
        new int[]{1, -1, 1, -1}, 1, "Xs", "Pn", KotlinSyntheticClassKindMetadata.Flavor.WHEN_MAPPINGS));

    // Assert that nothing has changed
    assertEquals("$WhenMappings", clazz.getProcessingInfo());
  }
}
