package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.kotlin.KotlinClassKindMetadata;

class KotlinCompanionEqualizerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinCompanionEqualizer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata() {
    // Arrange
    KotlinCompanionEqualizer kotlinCompanionEqualizer = new KotlinCompanionEqualizer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    // Act
    kotlinCompanionEqualizer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert that nothing has changed
    assertNull(kotlinClassKindMetadata.referencedCompanionField);
  }

  /**
   * Method under test:
   * {@link KotlinCompanionEqualizer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata2() {
    // Arrange
    KotlinCompanionEqualizer kotlinCompanionEqualizer = new KotlinCompanionEqualizer();
    LibraryClass clazz = mock(LibraryClass.class);
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    // Act
    kotlinCompanionEqualizer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert that nothing has changed
    assertNull(kotlinClassKindMetadata.referencedCompanionField);
  }

  /**
   * Method under test:
   * {@link KotlinCompanionEqualizer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata3() {
    // Arrange
    KotlinCompanionEqualizer kotlinCompanionEqualizer = new KotlinCompanionEqualizer();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryClass libraryClass2 = new LibraryClass();
    libraryClass2.setProcessingInfo("Kotlin Class Kind Metadata");
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    kotlinClassKindMetadata.companionObjectName = "foo";
    kotlinClassKindMetadata.referencedCompanionClass = libraryClass;
    kotlinClassKindMetadata.referencedCompanionField = libraryField;
    kotlinClassKindMetadata.referencedClass = libraryClass2;

    // Act
    kotlinCompanionEqualizer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert that nothing has changed
    assertTrue(kotlinClassKindMetadata.referencedCompanionField instanceof LibraryField);
  }

  /**
   * Method under test:
   * {@link KotlinCompanionEqualizer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata4() {
    // Arrange
    KotlinCompanionEqualizer kotlinCompanionEqualizer = new KotlinCompanionEqualizer();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryClass libraryClass2 = new LibraryClass();
    libraryClass2.setProcessingInfo("Kotlin Class Kind Metadata");
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    kotlinClassKindMetadata.companionObjectName = "foo";
    kotlinClassKindMetadata.referencedCompanionClass = libraryClass;
    kotlinClassKindMetadata.referencedCompanionField = libraryField;
    kotlinClassKindMetadata.referencedClass = libraryClass2;

    // Act
    kotlinCompanionEqualizer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isNull());
    Field field = kotlinClassKindMetadata.referencedCompanionField;
    assertTrue(field instanceof LibraryField);
    assertNull(field.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link KotlinCompanionEqualizer#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata5() {
    // Arrange
    KotlinCompanionEqualizer kotlinCompanionEqualizer = new KotlinCompanionEqualizer();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("$");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryClass libraryClass2 = new LibraryClass();
    libraryClass2.setProcessingInfo("Kotlin Class Kind Metadata");
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    kotlinClassKindMetadata.companionObjectName = "foo";
    kotlinClassKindMetadata.referencedCompanionClass = libraryClass;
    kotlinClassKindMetadata.referencedCompanionField = libraryField;
    kotlinClassKindMetadata.referencedClass = libraryClass2;

    // Act
    kotlinCompanionEqualizer.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isNull());
    Field field = kotlinClassKindMetadata.referencedCompanionField;
    assertTrue(field instanceof LibraryField);
    assertEquals("", field.getProcessingInfo());
  }
}
