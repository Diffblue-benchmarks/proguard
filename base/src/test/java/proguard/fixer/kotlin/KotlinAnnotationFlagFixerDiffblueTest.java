package proguard.fixer.kotlin;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinConstructorMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinFileFacadeKindMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinMultiFilePartKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.KotlinTypeMetadata;
import proguard.classfile.kotlin.KotlinTypeParameterMetadata;
import proguard.classfile.kotlin.KotlinValueParameterMetadata;
import proguard.classfile.kotlin.KotlinVersionRequirementMetadata;
import proguard.classfile.kotlin.flags.KotlinClassFlags;
import proguard.classfile.kotlin.flags.KotlinConstructorFlags;
import proguard.classfile.kotlin.flags.KotlinFunctionFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinPropertyVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeAliasVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.classfile.kotlin.visitor.KotlinValueParameterVisitor;
import proguard.classfile.kotlin.visitor.KotlinVersionRequirementVisitor;
import proguard.classfile.visitor.MemberVisitor;

class KotlinAnnotationFlagFixerDiffblueTest {
  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinDeclarationContainerMetadata(Clazz,
   * KotlinDeclarationContainerMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinClassKindMetadata#delegatedPropertiesAccept(Clazz,
   *       KotlinPropertyVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link
   * KotlinAnnotationFlagFixer#visitKotlinDeclarationContainerMetadata(Clazz,
   * KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)"
  })
  void testVisitKotlinDeclarationContainerMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata =
        mock(KotlinClassKindMetadata.class);
    doNothing()
        .when(kotlinDeclarationContainerMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing()
        .when(kotlinDeclarationContainerMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinDeclarationContainerMetadata(
        clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata)
        .delegatedPropertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata)
        .functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinDeclarationContainerMetadata)
        .propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinFileFacadeMetadata(Clazz,
   * KotlinFileFacadeKindMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinFileFacadeKindMetadata#delegatedPropertiesAccept(Clazz,
   *       KotlinPropertyVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinFileFacadeMetadata(Clazz,
   * KotlinFileFacadeKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)"
  })
  void testVisitKotlinFileFacadeMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinFileFacadeKindMetadata kotlinFileFacadeKindMetadata =
        mock(KotlinFileFacadeKindMetadata.class);
    doNothing()
        .when(kotlinFileFacadeKindMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinFileFacadeKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing()
        .when(kotlinFileFacadeKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinFileFacadeKindMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinFileFacadeMetadata(clazz, kotlinFileFacadeKindMetadata);

    // Assert
    verify(kotlinFileFacadeKindMetadata)
        .delegatedPropertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata)
        .functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinFileFacadeKindMetadata)
        .propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata)
        .typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinSyntheticClassKindMetadata#functionsAccept(Clazz,
   *       KotlinFunctionVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata_thenCallsFunctionsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata =
        mock(KotlinSyntheticClassKindMetadata.class);
    doNothing()
        .when(kotlinSyntheticClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinSyntheticClassMetadata(
        clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    verify(kotlinSyntheticClassKindMetadata)
        .functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinMultiFilePartMetadata(Clazz,
   * KotlinMultiFilePartKindMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinMultiFilePartKindMetadata#delegatedPropertiesAccept(Clazz,
   *       KotlinPropertyVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinMultiFilePartMetadata(Clazz,
   * KotlinMultiFilePartKindMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)"
  })
  void testVisitKotlinMultiFilePartMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinMultiFilePartKindMetadata kotlinMultiFilePartKindMetadata =
        mock(KotlinMultiFilePartKindMetadata.class);
    doNothing()
        .when(kotlinMultiFilePartKindMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinMultiFilePartKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing()
        .when(kotlinMultiFilePartKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing()
        .when(kotlinMultiFilePartKindMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinMultiFilePartMetadata(
        clazz, kotlinMultiFilePartKindMetadata);

    // Assert
    verify(kotlinMultiFilePartKindMetadata)
        .delegatedPropertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinMultiFilePartKindMetadata)
        .functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinMultiFilePartKindMetadata)
        .propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinMultiFilePartKindMetadata)
        .typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName("Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = false;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = null;
    kotlinConstructorMetadata.referencedMethod = null;

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName("Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor2() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = true;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = null;
    kotlinConstructorMetadata.referencedMethod = null;

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName("Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor3() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = false;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinVersionRequirementMetadata kotlinVersionRequirementMetadata =
        mock(KotlinVersionRequirementMetadata.class);
    doNothing()
        .when(kotlinVersionRequirementMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = kotlinVersionRequirementMetadata;
    kotlinConstructorMetadata.referencedMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
    verify(kotlinVersionRequirementMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Given {@link KotlinVersionRequirementMetadata} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); given KotlinVersionRequirementMetadata (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_givenKotlinVersionRequirementMetadata() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = false;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = new KotlinVersionRequirementMetadata();
    kotlinConstructorMetadata.referencedMethod = null;

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Given {@link LibraryMethod} {@link LibraryMethod#accept(Clazz, MemberVisitor)} does
   *       nothing.
   *   <li>Then calls {@link LibraryMethod#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); given LibraryMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_givenLibraryMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = false;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinVersionRequirementMetadata kotlinVersionRequirementMetadata =
        mock(KotlinVersionRequirementMetadata.class);
    doNothing()
        .when(kotlinVersionRequirementMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = kotlinVersionRequirementMetadata;
    kotlinConstructorMetadata.referencedMethod = libraryMethod;

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
    verify(kotlinVersionRequirementMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinVersionRequirementMetadata#accept(Clazz, KotlinMetadata,
   *       KotlinConstructorMetadata, KotlinVersionRequirementVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitConstructor(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); then calls accept(Clazz, KotlinMetadata, KotlinConstructorMetadata, KotlinVersionRequirementVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_thenCallsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
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
    KotlinClassFlags kotlinClassFlags = new KotlinClassFlags(visibility, modality);

    kotlinClassFlags.isAnnotationClass = false;
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinClassKindMetadata.flags = kotlinClassFlags;
    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility2);
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());

    ArrayList<KotlinValueParameterMetadata> kotlinValueParameterMetadataList = new ArrayList<>();
    kotlinValueParameterMetadataList.add(kotlinValueParameterMetadata);
    KotlinVersionRequirementMetadata kotlinVersionRequirementMetadata =
        mock(KotlinVersionRequirementMetadata.class);
    doNothing()
        .when(kotlinVersionRequirementMetadata)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(flags);
    kotlinConstructorMetadata.valueParameters = kotlinValueParameterMetadataList;
    kotlinConstructorMetadata.versionRequirement = kotlinVersionRequirementMetadata;
    kotlinConstructorMetadata.referencedMethod = null;

    // Act
    kotlinAnnotationFlagFixer.visitConstructor(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
    verify(kotlinVersionRequirementMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata,
   * KotlinFunctionMetadata, KotlinTypeMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryMethod#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz,
   * KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata); then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)"
  })
  void testVisitFunctionReceiverType_thenCallsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

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
    KotlinFunctionFlags flags = new KotlinFunctionFlags(visibility, modality);

    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    KotlinFunctionMetadata kotlinFunctionMetadata = new KotlinFunctionMetadata(flags, "Name");

    kotlinFunctionMetadata.referencedMethod = libraryMethod;
    kotlinFunctionMetadata.referencedMethodClass = new ProgramClass();

    // Act
    kotlinAnnotationFlagFixer.visitFunctionReceiverType(
        clazz, kotlinMetadata, kotlinFunctionMetadata, KotlinTypeMetadata.starProjection());

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata,
   * KotlinFunctionMetadata, KotlinTypeMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinFunctionMetadata#referencedMethodAccept(MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz,
   * KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)"
  })
  void testVisitFunctionReceiverType_thenCallsReferencedMethodAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinFunctionMetadata kotlinFunctionMetadata = mock(KotlinFunctionMetadata.class);
    doNothing().when(kotlinFunctionMetadata).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitFunctionReceiverType(
        clazz, kotlinMetadata, kotlinFunctionMetadata, KotlinTypeMetadata.starProjection());

    // Assert
    verify(kotlinFunctionMetadata).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitAnyTypeParameter(Clazz,
   * KotlinTypeParameterMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinTypeParameterMetadata#upperBoundsAccept(Clazz,
   *       KotlinTypeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinAnnotationFlagFixer#visitAnyTypeParameter(Clazz,
   * KotlinTypeParameterMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata); then calls upperBoundsAccept(Clazz, KotlinTypeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinAnnotationFlagFixer.visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata)"
  })
  void testVisitAnyTypeParameter_thenCallsUpperBoundsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinTypeParameterMetadata kotlinTypeParameterMetadata =
        mock(KotlinTypeParameterMetadata.class);
    doNothing()
        .when(kotlinTypeParameterMetadata)
        .upperBoundsAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitAnyTypeParameter(clazz, kotlinTypeParameterMetadata);

    // Assert
    verify(kotlinTypeParameterMetadata)
        .upperBoundsAccept(isA(Clazz.class), isA(KotlinTypeVisitor.class));
  }
}
