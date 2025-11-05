package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinConstructorMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinPropertyMetadata;
import proguard.classfile.kotlin.KotlinValueParameterMetadata;
import proguard.classfile.kotlin.flags.KotlinConstructorFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyAccessorFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinTypeParameterVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.classfile.kotlin.visitor.KotlinValueParameterVisitor;
import proguard.classfile.kotlin.visitor.KotlinVersionRequirementVisitor;
import proguard.util.Processable;

class KotlinShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinShrinker#visitAnyProperty(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata)}
   */
  @Test
  void testVisitAnyProperty() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinPropertyMetadata kotlinPropertyMetadata = mock(KotlinPropertyMetadata.class);
    doNothing().when(kotlinPropertyMetadata)
        .contextReceiverTypesAccept(Mockito.<Clazz>any(), Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());
    doNothing().when(kotlinPropertyMetadata)
        .receiverTypeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());
    doNothing().when(kotlinPropertyMetadata)
        .setterParametersAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());
    doNothing().when(kotlinPropertyMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());
    doNothing().when(kotlinPropertyMetadata)
        .typeParametersAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinTypeParameterVisitor>any());
    doNothing().when(kotlinPropertyMetadata)
        .versionRequirementAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());

    // Act
    kotlinShrinker.visitAnyProperty(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata);

    // Assert
    verify(kotlinPropertyMetadata).contextReceiverTypesAccept(isA(Clazz.class), isA(KotlinMetadata.class),
        isA(KotlinTypeVisitor.class));
    verify(kotlinPropertyMetadata).receiverTypeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinTypeVisitor.class));
    verify(kotlinPropertyMetadata).setterParametersAccept(isA(Clazz.class),
        isA(KotlinDeclarationContainerMetadata.class), isA(KotlinValueParameterVisitor.class));
    verify(kotlinPropertyMetadata).typeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinTypeVisitor.class));
    verify(kotlinPropertyMetadata).typeParametersAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinTypeParameterVisitor.class));
    verify(kotlinPropertyMetadata, atLeast(1)).versionRequirementAccept(isA(Clazz.class),
        isA(KotlinDeclarationContainerMetadata.class), isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinShrinker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  void testVisitConstructor() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    KotlinConstructorMetadata kotlinConstructorMetadata = mock(KotlinConstructorMetadata.class);
    doNothing().when(kotlinConstructorMetadata)
        .valueParametersAccept(Mockito.<Clazz>any(), Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinValueParameterVisitor>any());
    doNothing().when(kotlinConstructorMetadata)
        .versionRequirementAccept(Mockito.<Clazz>any(), Mockito.<KotlinMetadata>any(),
            Mockito.<KotlinVersionRequirementVisitor>any());

    // Act
    kotlinShrinker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinConstructorMetadata).valueParametersAccept(isA(Clazz.class), isA(KotlinClassKindMetadata.class),
        isA(KotlinValueParameterVisitor.class));
    verify(kotlinConstructorMetadata).versionRequirementAccept(isA(Clazz.class), isA(KotlinMetadata.class),
        isA(KotlinVersionRequirementVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinShrinker#visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  void testVisitConstructorValParameter() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(
        new KotlinConstructorFlags(visibility));
    KotlinValueParameterMetadata kotlinValueParameterMetadata = mock(KotlinValueParameterMetadata.class);
    doNothing().when(kotlinValueParameterMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinShrinker.visitConstructorValParameter(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata).typeAccept(isA(Clazz.class), isA(KotlinClassKindMetadata.class),
        isA(KotlinConstructorMetadata.class), isA(KotlinTypeVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinShrinker#visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  void testVisitPropertyValParameter() {
    // Arrange
    KotlinShrinker kotlinShrinker = new KotlinShrinker(new SimpleUsageMarker());
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

    KotlinValueParameterMetadata kotlinValueParameterMetadata = mock(KotlinValueParameterMetadata.class);
    doNothing().when(kotlinValueParameterMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinPropertyMetadata>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinShrinker.visitPropertyValParameter(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata).typeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinPropertyMetadata.class), isA(KotlinTypeVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  void testShrinkArray() {
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
   * Method under test:
   * {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  void testShrinkArray2() {
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
   * Method under test:
   * {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  void testShrinkArray3() {
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
   * Method under test:
   * {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  void testShrinkArray4() {
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

  /**
   * Method under test:
   * {@link KotlinShrinker#shrinkArray(SimpleUsageMarker, List, List)}
   */
  @Test
  void testShrinkArray5() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    ArrayList<Object> elements = new ArrayList<>();

    ArrayList<Processable> referencedJavaElements = new ArrayList<>();
    referencedJavaElements.add(mock(Processable.class));

    // Act
    KotlinShrinker.shrinkArray(usageMarker, elements, referencedJavaElements);

    // Assert that nothing has changed
    assertEquals(1, referencedJavaElements.size());
    assertTrue(elements.isEmpty());
  }
}
