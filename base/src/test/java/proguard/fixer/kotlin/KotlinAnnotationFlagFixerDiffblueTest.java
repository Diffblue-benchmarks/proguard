package proguard.fixer.kotlin;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testVisitKotlinDeclarationContainerMetadata() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}
   */
  @Test
  void testVisitKotlinFileFacadeMetadata() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitKotlinMultiFilePartMetadata(Clazz, KotlinMultiFilePartKindMetadata)}
   */
  @Test
  void testVisitKotlinMultiFilePartMetadata() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  void testVisitFunctionReceiverType() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitFunctionReceiverType(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeMetadata)}
   */
  @Test
  void testVisitFunctionReceiverType2() {
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
   * Method under test:
   * {@link KotlinAnnotationFlagFixer#visitAnyTypeParameter(Clazz, KotlinTypeParameterMetadata)}
   */
  @Test
  void testVisitAnyTypeParameter() {
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
