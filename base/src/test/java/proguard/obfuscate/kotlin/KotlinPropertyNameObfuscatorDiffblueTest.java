package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinPropertyMetadata;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyAccessorFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.obfuscate.NumericNameFactory;

class KotlinPropertyNameObfuscatorDiffblueTest {
  /**
   * Test {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}.
   * <p>
   * Method under test: {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinPropertyNameObfuscator.visitAnyProperty(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinPropertyMetadata)"})
  void testVisitAnyProperty() {
    // Arrange
    KotlinPropertyNameObfuscator kotlinPropertyNameObfuscator = new KotlinPropertyNameObfuscator(
        new NumericNameFactory());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinPropertyFlags flags = new KotlinPropertyFlags(visibility, modality);

    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinModalityFlags modality2 = new KotlinModalityFlags();
    modality2.isAbstract = true;
    modality2.isFinal = true;
    modality2.isOpen = true;
    modality2.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags = new KotlinPropertyAccessorFlags(visibility2, modality2);

    KotlinVisibilityFlags visibility3 = new KotlinVisibilityFlags();
    visibility3.isInternal = true;
    visibility3.isLocal = true;
    visibility3.isPrivate = true;
    visibility3.isPrivateToThis = true;
    visibility3.isProtected = true;
    visibility3.isPublic = true;
    KotlinModalityFlags modality3 = new KotlinModalityFlags();
    modality3.isAbstract = true;
    modality3.isFinal = true;
    modality3.isOpen = true;
    modality3.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata = new KotlinPropertyMetadata(flags, "Name", getterFlags,
        new KotlinPropertyAccessorFlags(visibility3, modality3));

    // Act
    kotlinPropertyNameObfuscator.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata);

    // Assert
    assertEquals("1", kotlinPropertyMetadata.getProcessingInfo());
  }

  /**
   * Test {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}.
   * <p>
   * Method under test: {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinPropertyNameObfuscator.visitAnyProperty(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinPropertyMetadata)"})
  void testVisitAnyProperty2() {
    // Arrange
    KotlinPropertyNameObfuscator kotlinPropertyNameObfuscator = new KotlinPropertyNameObfuscator(
        new NumericNameFactory());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinPropertyFlags flags = new KotlinPropertyFlags(visibility, modality);

    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinModalityFlags modality2 = new KotlinModalityFlags();
    modality2.isAbstract = true;
    modality2.isFinal = true;
    modality2.isOpen = true;
    modality2.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags = new KotlinPropertyAccessorFlags(visibility2, modality2);

    KotlinVisibilityFlags visibility3 = new KotlinVisibilityFlags();
    visibility3.isInternal = true;
    visibility3.isLocal = true;
    visibility3.isPrivate = true;
    visibility3.isPrivateToThis = true;
    visibility3.isProtected = true;
    visibility3.isPublic = true;
    KotlinModalityFlags modality3 = new KotlinModalityFlags();
    modality3.isAbstract = true;
    modality3.isFinal = true;
    modality3.isOpen = true;
    modality3.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata = new KotlinPropertyMetadata(flags, "Name", getterFlags,
        new KotlinPropertyAccessorFlags(visibility3, modality3));

    kotlinPropertyMetadata.referencedBackingField = null;
    kotlinPropertyMetadata.referencedGetterMethod = null;
    kotlinPropertyMetadata.referencedSetterMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    kotlinPropertyNameObfuscator.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata);

    // Assert
    assertEquals("1", kotlinPropertyMetadata.getProcessingInfo());
  }

  /**
   * Test {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}.
   * <p>
   * Method under test: {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinPropertyNameObfuscator.visitAnyProperty(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinPropertyMetadata)"})
  void testVisitAnyProperty3() {
    // Arrange
    KotlinPropertyNameObfuscator kotlinPropertyNameObfuscator = new KotlinPropertyNameObfuscator(
        new NumericNameFactory());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinPropertyFlags flags = new KotlinPropertyFlags(visibility, modality);

    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinModalityFlags modality2 = new KotlinModalityFlags();
    modality2.isAbstract = true;
    modality2.isFinal = true;
    modality2.isOpen = true;
    modality2.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags = new KotlinPropertyAccessorFlags(visibility2, modality2);

    KotlinVisibilityFlags visibility3 = new KotlinVisibilityFlags();
    visibility3.isInternal = true;
    visibility3.isLocal = true;
    visibility3.isPrivate = true;
    visibility3.isPrivateToThis = true;
    visibility3.isProtected = true;
    visibility3.isPublic = true;
    KotlinModalityFlags modality3 = new KotlinModalityFlags();
    modality3.isAbstract = true;
    modality3.isFinal = true;
    modality3.isOpen = true;
    modality3.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata = new KotlinPropertyMetadata(flags, "Name", getterFlags,
        new KotlinPropertyAccessorFlags(visibility3, modality3));

    kotlinPropertyMetadata.referencedBackingField = null;
    kotlinPropertyMetadata.referencedGetterMethod = new LibraryMethod(1, "Name", "Descriptor");
    kotlinPropertyMetadata.referencedSetterMethod = null;

    // Act
    kotlinPropertyNameObfuscator.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata);

    // Assert
    assertEquals("1", kotlinPropertyMetadata.getProcessingInfo());
  }

  /**
   * Test {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}.
   * <ul>
   *   <li>Given {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinPropertyNameObfuscator#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata); given LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinPropertyNameObfuscator.visitAnyProperty(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinPropertyMetadata)"})
  void testVisitAnyProperty_givenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange
    KotlinPropertyNameObfuscator kotlinPropertyNameObfuscator = new KotlinPropertyNameObfuscator(
        new NumericNameFactory());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinPropertyFlags flags = new KotlinPropertyFlags(visibility, modality);

    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinModalityFlags modality2 = new KotlinModalityFlags();
    modality2.isAbstract = true;
    modality2.isFinal = true;
    modality2.isOpen = true;
    modality2.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags = new KotlinPropertyAccessorFlags(visibility2, modality2);

    KotlinVisibilityFlags visibility3 = new KotlinVisibilityFlags();
    visibility3.isInternal = true;
    visibility3.isLocal = true;
    visibility3.isPrivate = true;
    visibility3.isPrivateToThis = true;
    visibility3.isProtected = true;
    visibility3.isPublic = true;
    KotlinModalityFlags modality3 = new KotlinModalityFlags();
    modality3.isAbstract = true;
    modality3.isFinal = true;
    modality3.isOpen = true;
    modality3.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata = new KotlinPropertyMetadata(flags, "Name", getterFlags,
        new KotlinPropertyAccessorFlags(visibility3, modality3));

    kotlinPropertyMetadata.referencedBackingField = new LibraryField(1, "Name", "Descriptor");
    kotlinPropertyMetadata.referencedGetterMethod = null;
    kotlinPropertyMetadata.referencedSetterMethod = null;

    // Act
    kotlinPropertyNameObfuscator.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata);

    // Assert
    assertEquals("1", kotlinPropertyMetadata.getProcessingInfo());
  }
}
