package proguard.fixer.kotlin;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinFileFacadeKindMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinMultiFilePartKindMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.KotlinTypeMetadata;
import proguard.classfile.kotlin.KotlinTypeParameterMetadata;
import proguard.classfile.kotlin.flags.KotlinFunctionFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinPropertyVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeAliasVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.classfile.visitor.MemberVisitor;

class KotlinAnnotationFlagFixerDiffblueTest {
  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinDeclarationContainerMetadata#delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)"})
  void testVisitKotlinDeclarationContainerMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).delegatedPropertiesAccept(isA(Clazz.class),
        isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinDeclarationContainerMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinDeclarationContainerMetadata#delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)"})
  void testVisitKotlinFileFacadeMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinFileFacadeKindMetadata kotlinFileFacadeKindMetadata = mock(KotlinFileFacadeKindMetadata.class);
    doNothing().when(kotlinFileFacadeKindMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinFileFacadeMetadata(clazz, kotlinFileFacadeKindMetadata);

    // Assert
    verify(kotlinFileFacadeKindMetadata).delegatedPropertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinFileFacadeKindMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinSyntheticClassKindMetadata#functionsAccept(Clazz, KotlinFunctionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"})
  void testVisitKotlinSyntheticClassMetadata_thenCallsFunctionsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata = mock(KotlinSyntheticClassKindMetadata.class);
    doNothing().when(kotlinSyntheticClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinSyntheticClassMetadata(clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    verify(kotlinSyntheticClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinDeclarationContainerMetadata#delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)"})
  void testVisitKotlinMultiFilePartMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinMultiFilePartKindMetadata kotlinMultiFilePartKindMetadata = mock(KotlinMultiFilePartKindMetadata.class);
    doNothing().when(kotlinMultiFilePartKindMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinMultiFilePartKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinMultiFilePartKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinMultiFilePartKindMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitKotlinMultiFilePartMetadata(clazz, kotlinMultiFilePartKindMetadata);

    // Assert
    verify(kotlinMultiFilePartKindMetadata).delegatedPropertiesAccept(isA(Clazz.class),
        isA(KotlinPropertyVisitor.class));
    verify(kotlinMultiFilePartKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinMultiFilePartKindMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinMultiFilePartKindMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}.
   * <ul>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  @DisplayName("Test visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata); then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)"})
  void testVisitFunctionReceiverType_thenCallsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
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
    KotlinFunctionFlags flags = new KotlinFunctionFlags(visibility, modality);

    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    KotlinFunctionMetadata kotlinFunctionMetadata = new KotlinFunctionMetadata(flags, "Name");

    kotlinFunctionMetadata.referencedMethod = libraryMethod;
    kotlinFunctionMetadata.referencedMethodClass = new ProgramClass();

    // Act
    kotlinAnnotationFlagFixer.visitFunctionReceiverType(clazz, kotlinMetadata, kotlinFunctionMetadata,
        KotlinTypeMetadata.starProjection());

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinFunctionMetadata#referencedMethodAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  @DisplayName("Test visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinAnnotationFlagFixer.visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)"})
  void testVisitFunctionReceiverType_thenCallsReferencedMethodAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinFunctionMetadata kotlinFunctionMetadata = mock(KotlinFunctionMetadata.class);
    doNothing().when(kotlinFunctionMetadata).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitFunctionReceiverType(clazz, kotlinMetadata, kotlinFunctionMetadata,
        KotlinTypeMetadata.starProjection());

    // Assert
    verify(kotlinFunctionMetadata).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link KotlinAnnotationFlagFixer#visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinTypeParameterMetadata#upperBoundsAccept(Clazz, KotlinTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinAnnotationFlagFixer#visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata); then calls upperBoundsAccept(Clazz, KotlinTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KotlinAnnotationFlagFixer.visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata)"})
  void testVisitAnyTypeParameter_thenCallsUpperBoundsAccept() {
    // Arrange
    KotlinAnnotationFlagFixer kotlinAnnotationFlagFixer = new KotlinAnnotationFlagFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinTypeParameterMetadata kotlinTypeParameterMetadata = mock(KotlinTypeParameterMetadata.class);
    doNothing().when(kotlinTypeParameterMetadata)
        .upperBoundsAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinAnnotationFlagFixer.visitAnyTypeParameter(clazz, kotlinTypeParameterMetadata);

    // Assert
    verify(kotlinTypeParameterMetadata).upperBoundsAccept(isA(Clazz.class), isA(KotlinTypeVisitor.class));
  }
}
