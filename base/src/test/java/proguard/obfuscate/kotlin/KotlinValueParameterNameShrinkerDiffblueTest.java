package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinPropertyMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.flags.KotlinFunctionFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyAccessorFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinConstructorVisitor;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinPropertyVisitor;

class KotlinValueParameterNameShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());

    // Act
    kotlinValueParameterNameShrinker.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinDeclarationContainerMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinClassKindMetadata)
        .constructorsAccept(Mockito.<Clazz>any(), Mockito.<KotlinConstructorVisitor>any());
    doNothing().when(kotlinClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinClassKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());

    // Act
    kotlinValueParameterNameShrinker.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(kotlinClassKindMetadata).constructorsAccept(isA(Clazz.class), isA(KotlinConstructorVisitor.class));
    verify(kotlinClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinClassKindMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
    LibraryClass clazz = new LibraryClass();
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata = mock(KotlinSyntheticClassKindMetadata.class);
    doNothing().when(kotlinSyntheticClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinValueParameterNameShrinker.visitKotlinSyntheticClassMetadata(clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    verify(kotlinSyntheticClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  void testVisitAnyProperty() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
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

    kotlinPropertyMetadata.setterParameters = new ArrayList<>();

    // Act
    kotlinValueParameterNameShrinker.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata,
        kotlinPropertyMetadata);

    // Assert
    assertTrue(kotlinPropertyMetadata.setterParameters.isEmpty());
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)}
   */
  @Test
  void testVisitAnyFunction() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn");

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
    KotlinFunctionMetadata kotlinFunctionMetadata = new KotlinFunctionMetadata(
        new KotlinFunctionFlags(visibility, modality), "Name");

    // Act
    kotlinValueParameterNameShrinker.visitAnyFunction(clazz, kotlinMetadata, kotlinFunctionMetadata);

    // Assert
    assertNull(kotlinFunctionMetadata.valueParameters);
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterNameShrinker#visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)}
   */
  @Test
  void testVisitAnyFunction2() {
    // Arrange
    KotlinValueParameterNameShrinker kotlinValueParameterNameShrinker = new KotlinValueParameterNameShrinker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn");

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
    KotlinFunctionMetadata kotlinFunctionMetadata = new KotlinFunctionMetadata(
        new KotlinFunctionFlags(visibility, modality), "Name");

    kotlinFunctionMetadata.valueParameters = new ArrayList<>();

    // Act
    kotlinValueParameterNameShrinker.visitAnyFunction(clazz, kotlinMetadata, kotlinFunctionMetadata);

    // Assert
    assertTrue(kotlinFunctionMetadata.valueParameters.isEmpty());
  }
}
