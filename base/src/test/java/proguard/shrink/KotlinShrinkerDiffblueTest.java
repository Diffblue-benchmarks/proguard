package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinConstructorMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinFileFacadeKindMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinMultiFilePartKindMetadata;
import proguard.classfile.kotlin.KotlinPropertyMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata.Flavor;
import proguard.classfile.kotlin.KotlinTypeAliasMetadata;
import proguard.classfile.kotlin.KotlinValueParameterMetadata;
import proguard.classfile.kotlin.KotlinVersionRequirementMetadata;
import proguard.classfile.kotlin.flags.KotlinConstructorFlags;
import proguard.classfile.kotlin.flags.KotlinFunctionFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyAccessorFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyFlags;
import proguard.classfile.kotlin.flags.KotlinTypeAliasFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.classfile.kotlin.visitor.KotlinValueParameterVisitor;
import proguard.classfile.kotlin.visitor.KotlinVersionRequirementVisitor;
import proguard.util.Processable;

class KotlinShrinkerDiffblueTest {
  /**
   * Test {@link KotlinShrinker#visitKotlinDeclarationContainerMetadata(Clazz,
   * KotlinDeclarationContainerMetadata)}.
   *
   * <p>Method under test: {@link KotlinShrinker#visitKotlinDeclarationContainerMetadata(Clazz,
   * KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)"
  })
  void testVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
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
    KotlinPropertyAccessorFlags getterFlags =
        new KotlinPropertyAccessorFlags(visibility2, modality2);

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
    KotlinPropertyMetadata kotlinPropertyMetadata =
        new KotlinPropertyMetadata(
            flags, "Name", getterFlags, new KotlinPropertyAccessorFlags(visibility3, modality3));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList = new ArrayList<>();
    kotlinPropertyMetadataList.add(kotlinPropertyMetadata);
    KotlinVisibilityFlags visibility4 = new KotlinVisibilityFlags();
    visibility4.isInternal = true;
    visibility4.isLocal = true;
    visibility4.isPrivate = true;
    visibility4.isPrivateToThis = true;
    visibility4.isProtected = true;
    visibility4.isPublic = true;
    KotlinModalityFlags modality4 = new KotlinModalityFlags();
    modality4.isAbstract = true;
    modality4.isFinal = true;
    modality4.isOpen = true;
    modality4.isSealed = true;
    KotlinFunctionMetadata kotlinFunctionMetadata =
        new KotlinFunctionMetadata(new KotlinFunctionFlags(visibility4, modality4), "Name");

    ArrayList<KotlinFunctionMetadata> kotlinFunctionMetadataList = new ArrayList<>();
    kotlinFunctionMetadataList.add(kotlinFunctionMetadata);
    KotlinVisibilityFlags visibility5 = new KotlinVisibilityFlags();
    visibility5.isInternal = true;
    visibility5.isLocal = true;
    visibility5.isPrivate = true;
    visibility5.isPrivateToThis = true;
    visibility5.isProtected = true;
    visibility5.isPublic = true;
    KotlinTypeAliasMetadata kotlinTypeAliasMetadata =
        new KotlinTypeAliasMetadata(new KotlinTypeAliasFlags(visibility5), "Name");

    ArrayList<KotlinTypeAliasMetadata> kotlinTypeAliasMetadataList = new ArrayList<>();
    kotlinTypeAliasMetadataList.add(kotlinTypeAliasMetadata);
    KotlinVisibilityFlags visibility6 = new KotlinVisibilityFlags();
    visibility6.isInternal = true;
    visibility6.isLocal = true;
    visibility6.isPrivate = true;
    visibility6.isPrivateToThis = true;
    visibility6.isProtected = true;
    visibility6.isPublic = true;
    KotlinModalityFlags modality5 = new KotlinModalityFlags();
    modality5.isAbstract = true;
    modality5.isFinal = true;
    modality5.isOpen = true;
    modality5.isSealed = true;
    KotlinPropertyFlags flags2 = new KotlinPropertyFlags(visibility6, modality5);

    KotlinVisibilityFlags visibility7 = new KotlinVisibilityFlags();
    visibility7.isInternal = true;
    visibility7.isLocal = true;
    visibility7.isPrivate = true;
    visibility7.isPrivateToThis = true;
    visibility7.isProtected = true;
    visibility7.isPublic = true;
    KotlinModalityFlags modality6 = new KotlinModalityFlags();
    modality6.isAbstract = true;
    modality6.isFinal = true;
    modality6.isOpen = true;
    modality6.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags2 =
        new KotlinPropertyAccessorFlags(visibility7, modality6);

    KotlinVisibilityFlags visibility8 = new KotlinVisibilityFlags();
    visibility8.isInternal = true;
    visibility8.isLocal = true;
    visibility8.isPrivate = true;
    visibility8.isPrivateToThis = true;
    visibility8.isProtected = true;
    visibility8.isPublic = true;
    KotlinModalityFlags modality7 = new KotlinModalityFlags();
    modality7.isAbstract = true;
    modality7.isFinal = true;
    modality7.isOpen = true;
    modality7.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata2 =
        new KotlinPropertyMetadata(
            flags2, "Name", getterFlags2, new KotlinPropertyAccessorFlags(visibility8, modality7));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList2 = new ArrayList<>();
    kotlinPropertyMetadataList2.add(kotlinPropertyMetadata2);
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinDeclarationContainerMetadata.properties = kotlinPropertyMetadataList;
    kotlinDeclarationContainerMetadata.functions = kotlinFunctionMetadataList;
    kotlinDeclarationContainerMetadata.typeAliases = kotlinTypeAliasMetadataList;
    kotlinDeclarationContainerMetadata.localDelegatedProperties = kotlinPropertyMetadataList2;

    // Act
    kotlinShrinker.visitKotlinDeclarationContainerMetadata(
        clazz, kotlinDeclarationContainerMetadata);

    // Assert
    assertTrue(kotlinDeclarationContainerMetadata.functions.isEmpty());
    assertTrue(kotlinDeclarationContainerMetadata.localDelegatedProperties.isEmpty());
    assertTrue(kotlinDeclarationContainerMetadata.properties.isEmpty());
    assertTrue(kotlinDeclarationContainerMetadata.typeAliases.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinShrinker#visitKotlinFileFacadeMetadata(Clazz,
   * KotlinFileFacadeKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)"
  })
  void testVisitKotlinFileFacadeMetadata() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
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
    KotlinPropertyAccessorFlags getterFlags =
        new KotlinPropertyAccessorFlags(visibility2, modality2);

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
    KotlinPropertyMetadata kotlinPropertyMetadata =
        new KotlinPropertyMetadata(
            flags, "Name", getterFlags, new KotlinPropertyAccessorFlags(visibility3, modality3));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList = new ArrayList<>();
    kotlinPropertyMetadataList.add(kotlinPropertyMetadata);
    KotlinVisibilityFlags visibility4 = new KotlinVisibilityFlags();
    visibility4.isInternal = true;
    visibility4.isLocal = true;
    visibility4.isPrivate = true;
    visibility4.isPrivateToThis = true;
    visibility4.isProtected = true;
    visibility4.isPublic = true;
    KotlinModalityFlags modality4 = new KotlinModalityFlags();
    modality4.isAbstract = true;
    modality4.isFinal = true;
    modality4.isOpen = true;
    modality4.isSealed = true;
    KotlinFunctionMetadata kotlinFunctionMetadata =
        new KotlinFunctionMetadata(new KotlinFunctionFlags(visibility4, modality4), "Name");

    ArrayList<KotlinFunctionMetadata> kotlinFunctionMetadataList = new ArrayList<>();
    kotlinFunctionMetadataList.add(kotlinFunctionMetadata);
    KotlinVisibilityFlags visibility5 = new KotlinVisibilityFlags();
    visibility5.isInternal = true;
    visibility5.isLocal = true;
    visibility5.isPrivate = true;
    visibility5.isPrivateToThis = true;
    visibility5.isProtected = true;
    visibility5.isPublic = true;
    KotlinTypeAliasMetadata kotlinTypeAliasMetadata =
        new KotlinTypeAliasMetadata(new KotlinTypeAliasFlags(visibility5), "Name");

    ArrayList<KotlinTypeAliasMetadata> kotlinTypeAliasMetadataList = new ArrayList<>();
    kotlinTypeAliasMetadataList.add(kotlinTypeAliasMetadata);
    KotlinVisibilityFlags visibility6 = new KotlinVisibilityFlags();
    visibility6.isInternal = true;
    visibility6.isLocal = true;
    visibility6.isPrivate = true;
    visibility6.isPrivateToThis = true;
    visibility6.isProtected = true;
    visibility6.isPublic = true;
    KotlinModalityFlags modality5 = new KotlinModalityFlags();
    modality5.isAbstract = true;
    modality5.isFinal = true;
    modality5.isOpen = true;
    modality5.isSealed = true;
    KotlinPropertyFlags flags2 = new KotlinPropertyFlags(visibility6, modality5);

    KotlinVisibilityFlags visibility7 = new KotlinVisibilityFlags();
    visibility7.isInternal = true;
    visibility7.isLocal = true;
    visibility7.isPrivate = true;
    visibility7.isPrivateToThis = true;
    visibility7.isProtected = true;
    visibility7.isPublic = true;
    KotlinModalityFlags modality6 = new KotlinModalityFlags();
    modality6.isAbstract = true;
    modality6.isFinal = true;
    modality6.isOpen = true;
    modality6.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags2 =
        new KotlinPropertyAccessorFlags(visibility7, modality6);

    KotlinVisibilityFlags visibility8 = new KotlinVisibilityFlags();
    visibility8.isInternal = true;
    visibility8.isLocal = true;
    visibility8.isPrivate = true;
    visibility8.isPrivateToThis = true;
    visibility8.isProtected = true;
    visibility8.isPublic = true;
    KotlinModalityFlags modality7 = new KotlinModalityFlags();
    modality7.isAbstract = true;
    modality7.isFinal = true;
    modality7.isOpen = true;
    modality7.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata2 =
        new KotlinPropertyMetadata(
            flags2, "Name", getterFlags2, new KotlinPropertyAccessorFlags(visibility8, modality7));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList2 = new ArrayList<>();
    kotlinPropertyMetadataList2.add(kotlinPropertyMetadata2);
    KotlinFileFacadeKindMetadata kotlinFileFacadeKindMetadata =
        new KotlinFileFacadeKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinFileFacadeKindMetadata.properties = kotlinPropertyMetadataList;
    kotlinFileFacadeKindMetadata.functions = kotlinFunctionMetadataList;
    kotlinFileFacadeKindMetadata.typeAliases = kotlinTypeAliasMetadataList;
    kotlinFileFacadeKindMetadata.localDelegatedProperties = kotlinPropertyMetadataList2;

    // Act
    kotlinShrinker.visitKotlinFileFacadeMetadata(clazz, kotlinFileFacadeKindMetadata);

    // Assert
    assertTrue(kotlinFileFacadeKindMetadata.functions.isEmpty());
    assertTrue(kotlinFileFacadeKindMetadata.localDelegatedProperties.isEmpty());
    assertTrue(kotlinFileFacadeKindMetadata.properties.isEmpty());
    assertTrue(kotlinFileFacadeKindMetadata.typeAliases.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinShrinker#visitKotlinSyntheticClassMetadata(Clazz,
   * KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"
  })
  void testVisitKotlinSyntheticClassMetadata() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
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
    KotlinFunctionMetadata kotlinFunctionMetadata =
        new KotlinFunctionMetadata(new KotlinFunctionFlags(visibility, modality), "Name");

    ArrayList<KotlinFunctionMetadata> kotlinFunctionMetadataList = new ArrayList<>();
    kotlinFunctionMetadataList.add(kotlinFunctionMetadata);
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata =
        new KotlinSyntheticClassKindMetadata(
            new int[] {1, -1, 1, -1}, 1, "Xs", "Pn", Flavor.REGULAR);

    kotlinSyntheticClassKindMetadata.functions = kotlinFunctionMetadataList;

    // Act
    kotlinShrinker.visitKotlinSyntheticClassMetadata(clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    assertTrue(kotlinSyntheticClassKindMetadata.functions.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#visitKotlinMultiFilePartMetadata(Clazz,
   * KotlinMultiFilePartKindMetadata)}.
   *
   * <p>Method under test: {@link KotlinShrinker#visitKotlinMultiFilePartMetadata(Clazz,
   * KotlinMultiFilePartKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)"
  })
  void testVisitKotlinMultiFilePartMetadata() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
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
    KotlinPropertyAccessorFlags getterFlags =
        new KotlinPropertyAccessorFlags(visibility2, modality2);

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
    KotlinPropertyMetadata kotlinPropertyMetadata =
        new KotlinPropertyMetadata(
            flags, "Name", getterFlags, new KotlinPropertyAccessorFlags(visibility3, modality3));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList = new ArrayList<>();
    kotlinPropertyMetadataList.add(kotlinPropertyMetadata);
    KotlinVisibilityFlags visibility4 = new KotlinVisibilityFlags();
    visibility4.isInternal = true;
    visibility4.isLocal = true;
    visibility4.isPrivate = true;
    visibility4.isPrivateToThis = true;
    visibility4.isProtected = true;
    visibility4.isPublic = true;
    KotlinModalityFlags modality4 = new KotlinModalityFlags();
    modality4.isAbstract = true;
    modality4.isFinal = true;
    modality4.isOpen = true;
    modality4.isSealed = true;
    KotlinFunctionMetadata kotlinFunctionMetadata =
        new KotlinFunctionMetadata(new KotlinFunctionFlags(visibility4, modality4), "Name");

    ArrayList<KotlinFunctionMetadata> kotlinFunctionMetadataList = new ArrayList<>();
    kotlinFunctionMetadataList.add(kotlinFunctionMetadata);
    KotlinVisibilityFlags visibility5 = new KotlinVisibilityFlags();
    visibility5.isInternal = true;
    visibility5.isLocal = true;
    visibility5.isPrivate = true;
    visibility5.isPrivateToThis = true;
    visibility5.isProtected = true;
    visibility5.isPublic = true;
    KotlinTypeAliasMetadata kotlinTypeAliasMetadata =
        new KotlinTypeAliasMetadata(new KotlinTypeAliasFlags(visibility5), "Name");

    ArrayList<KotlinTypeAliasMetadata> kotlinTypeAliasMetadataList = new ArrayList<>();
    kotlinTypeAliasMetadataList.add(kotlinTypeAliasMetadata);
    KotlinVisibilityFlags visibility6 = new KotlinVisibilityFlags();
    visibility6.isInternal = true;
    visibility6.isLocal = true;
    visibility6.isPrivate = true;
    visibility6.isPrivateToThis = true;
    visibility6.isProtected = true;
    visibility6.isPublic = true;
    KotlinModalityFlags modality5 = new KotlinModalityFlags();
    modality5.isAbstract = true;
    modality5.isFinal = true;
    modality5.isOpen = true;
    modality5.isSealed = true;
    KotlinPropertyFlags flags2 = new KotlinPropertyFlags(visibility6, modality5);

    KotlinVisibilityFlags visibility7 = new KotlinVisibilityFlags();
    visibility7.isInternal = true;
    visibility7.isLocal = true;
    visibility7.isPrivate = true;
    visibility7.isPrivateToThis = true;
    visibility7.isProtected = true;
    visibility7.isPublic = true;
    KotlinModalityFlags modality6 = new KotlinModalityFlags();
    modality6.isAbstract = true;
    modality6.isFinal = true;
    modality6.isOpen = true;
    modality6.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags2 =
        new KotlinPropertyAccessorFlags(visibility7, modality6);

    KotlinVisibilityFlags visibility8 = new KotlinVisibilityFlags();
    visibility8.isInternal = true;
    visibility8.isLocal = true;
    visibility8.isPrivate = true;
    visibility8.isPrivateToThis = true;
    visibility8.isProtected = true;
    visibility8.isPublic = true;
    KotlinModalityFlags modality7 = new KotlinModalityFlags();
    modality7.isAbstract = true;
    modality7.isFinal = true;
    modality7.isOpen = true;
    modality7.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata2 =
        new KotlinPropertyMetadata(
            flags2, "Name", getterFlags2, new KotlinPropertyAccessorFlags(visibility8, modality7));

    ArrayList<KotlinPropertyMetadata> kotlinPropertyMetadataList2 = new ArrayList<>();
    kotlinPropertyMetadataList2.add(kotlinPropertyMetadata2);
    KotlinMultiFilePartKindMetadata kotlinMultiFilePartKindMetadata =
        new KotlinMultiFilePartKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    kotlinMultiFilePartKindMetadata.properties = kotlinPropertyMetadataList;
    kotlinMultiFilePartKindMetadata.functions = kotlinFunctionMetadataList;
    kotlinMultiFilePartKindMetadata.typeAliases = kotlinTypeAliasMetadataList;
    kotlinMultiFilePartKindMetadata.localDelegatedProperties = kotlinPropertyMetadataList2;

    // Act
    kotlinShrinker.visitKotlinMultiFilePartMetadata(clazz, kotlinMultiFilePartKindMetadata);

    // Assert
    assertTrue(kotlinMultiFilePartKindMetadata.functions.isEmpty());
    assertTrue(kotlinMultiFilePartKindMetadata.localDelegatedProperties.isEmpty());
    assertTrue(kotlinMultiFilePartKindMetadata.properties.isEmpty());
    assertTrue(kotlinMultiFilePartKindMetadata.typeAliases.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Given {@link KotlinVersionRequirementMetadata} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); given KotlinVersionRequirementMetadata (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_givenKotlinVersionRequirementMetadata() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility);
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

    // Act
    kotlinShrinker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
  }

  /**
   * Test {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); given 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_givenNull() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility);
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

    // Act
    kotlinShrinker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .accept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinValueParameterVisitor.class));
  }

  /**
   * Test {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinVersionRequirementMetadata#accept(Clazz, KotlinMetadata,
   *       KotlinConstructorMetadata, KotlinVersionRequirementVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); then calls accept(Clazz, KotlinMetadata, KotlinConstructorMetadata, KotlinVersionRequirementVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_thenCallsAccept() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorFlags flags = new KotlinConstructorFlags(visibility);
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

    // Act
    kotlinShrinker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

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
   * Test {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinConstructorMetadata#valueParametersAccept(Clazz,
   *       KotlinClassKindMetadata, KotlinValueParameterVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); then calls valueParametersAccept(Clazz, KotlinClassKindMetadata, KotlinValueParameterVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"
  })
  void testVisitConstructor_thenCallsValueParametersAccept() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinConstructorMetadata kotlinConstructorMetadata = mock(KotlinConstructorMetadata.class);
    doNothing()
        .when(kotlinConstructorMetadata)
        .valueParametersAccept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());
    doNothing()
        .when(kotlinConstructorMetadata)
        .versionRequirementAccept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());

    // Act
    kotlinShrinker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinConstructorMetadata)
        .valueParametersAccept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinValueParameterVisitor.class));
    verify(kotlinConstructorMetadata)
        .versionRequirementAccept(
            isA(Clazz.class),
            isA(KotlinMetadata.class),
            isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Test {@link KotlinShrinker#visitConstructorValParameter(Clazz, KotlinClassKindMetadata,
   * KotlinConstructorMetadata, KotlinValueParameterMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinValueParameterMetadata#typeAccept(Clazz, KotlinClassKindMetadata,
   *       KotlinConstructorMetadata, KotlinTypeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitConstructorValParameter(Clazz,
   * KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata); then calls typeAccept(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinTypeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)"
  })
  void testVisitConstructorValParameter_thenCallsTypeAccept() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata =
        new KotlinClassKindMetadata(new int[] {1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorMetadata kotlinConstructorMetadata =
        new KotlinConstructorMetadata(new KotlinConstructorFlags(visibility));
    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .typeAccept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinShrinker.visitConstructorValParameter(
        clazz, kotlinClassKindMetadata, kotlinConstructorMetadata, kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .typeAccept(
            isA(Clazz.class),
            isA(KotlinClassKindMetadata.class),
            isA(KotlinConstructorMetadata.class),
            isA(KotlinTypeVisitor.class));
  }

  /**
   * Test {@link KotlinShrinker#visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata,
   * KotlinPropertyMetadata, KotlinValueParameterMetadata)}.
   *
   * <ul>
   *   <li>Then calls {@link KotlinValueParameterMetadata#typeAccept(Clazz,
   *       KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinTypeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#visitPropertyValParameter(Clazz,
   * KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  @DisplayName(
      "Test visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata); then calls typeAccept(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinTypeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void KotlinShrinker.visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)"
  })
  void testVisitPropertyValParameter_thenCallsTypeAccept() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata =
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
    KotlinPropertyAccessorFlags getterFlags =
        new KotlinPropertyAccessorFlags(visibility2, modality2);

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
    KotlinPropertyMetadata kotlinPropertyMetadata =
        new KotlinPropertyMetadata(
            flags, "Name", getterFlags, new KotlinPropertyAccessorFlags(visibility3, modality3));

    KotlinValueParameterMetadata kotlinValueParameterMetadata =
        mock(KotlinValueParameterMetadata.class);
    doNothing()
        .when(kotlinValueParameterMetadata)
        .typeAccept(
            Mockito.<Clazz>any(),
            Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinPropertyMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinShrinker.visitPropertyValParameter(
        clazz,
        kotlinDeclarationContainerMetadata,
        kotlinPropertyMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata)
        .typeAccept(
            isA(Clazz.class),
            isA(KotlinDeclarationContainerMetadata.class),
            isA(KotlinPropertyMetadata.class),
            isA(KotlinTypeVisitor.class));
  }

  /**
   * Test {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)} with {@code
   * usageMarker}, {@code elements}, {@code referencedJavaElements}.
   *
   * <p>Method under test: {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  @DisplayName(
      "Test shrinkArray(SimpleUsageMarker, List, List) with 'usageMarker', 'elements', 'referencedJavaElements'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinShrinker.shrinkArray(SimpleUsageMarker, List, List)"})
  void testShrinkArrayWithUsageMarkerElementsReferencedJavaElements() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    ArrayList<Object> elements = new ArrayList<>();

    ArrayList<Processable> referencedJavaElements = new ArrayList<>();
    referencedJavaElements.add(new LibraryClass());

    // Act
    KotlinShrinker.shrinkArray(usageMarker, elements, referencedJavaElements);

    // Assert that nothing has changed
    assertEquals(1, referencedJavaElements.size());
    assertTrue(elements.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)} with {@code
   * usageMarker}, {@code elements}, {@code referencedJavaElements}.
   *
   * <p>Method under test: {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  @DisplayName(
      "Test shrinkArray(SimpleUsageMarker, List, List) with 'usageMarker', 'elements', 'referencedJavaElements'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinShrinker.shrinkArray(SimpleUsageMarker, List, List)"})
  void testShrinkArrayWithUsageMarkerElementsReferencedJavaElements2() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    ArrayList<Object> elements = new ArrayList<>();

    ArrayList<Processable> referencedJavaElements = new ArrayList<>();
    referencedJavaElements.add(new LibraryClass());
    referencedJavaElements.add(new LibraryClass());

    // Act
    KotlinShrinker.shrinkArray(usageMarker, elements, referencedJavaElements);

    // Assert that nothing has changed
    assertEquals(2, referencedJavaElements.size());
    assertTrue(elements.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)} with {@code
   * usageMarker}, {@code elements}, {@code referencedJavaElements}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  @DisplayName(
      "Test shrinkArray(SimpleUsageMarker, List, List) with 'usageMarker', 'elements', 'referencedJavaElements'; then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinShrinker.shrinkArray(SimpleUsageMarker, List, List)"})
  void testShrinkArrayWithUsageMarkerElementsReferencedJavaElements_thenArrayListEmpty() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    ArrayList<Object> elements = new ArrayList<>();
    ArrayList<Processable> referencedJavaElements = new ArrayList<>();

    // Act
    KotlinShrinker.shrinkArray(usageMarker, elements, referencedJavaElements);

    // Assert that nothing has changed
    assertTrue(elements.isEmpty());
    assertTrue(referencedJavaElements.isEmpty());
  }

  /**
   * Test {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)} with {@code
   * usageMarker}, {@code elements}, {@code referencedJavaElements}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  @DisplayName(
      "Test shrinkArray(SimpleUsageMarker, List, List) with 'usageMarker', 'elements', 'referencedJavaElements'; then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KotlinShrinker.shrinkArray(SimpleUsageMarker, List, List)"})
  void testShrinkArrayWithUsageMarkerElementsReferencedJavaElements_thenArrayListEmpty2() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();

    ArrayList<Object> elements = new ArrayList<>();
    elements.add("42");

    ArrayList<Processable> referencedJavaElements = new ArrayList<>();
    referencedJavaElements.add(new LibraryClass());

    // Act
    KotlinShrinker.shrinkArray(usageMarker, elements, referencedJavaElements);

    // Assert
    assertTrue(elements.isEmpty());
    assertTrue(referencedJavaElements.isEmpty());
  }
}
