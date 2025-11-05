package proguard.obfuscate.kotlin;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinTypeAliasMetadata;
import proguard.classfile.kotlin.visitor.KotlinTypeAliasVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.obfuscate.DictionaryNameFactory;
import proguard.obfuscate.NumericNameFactory;

class KotlinAliasNameObfuscatorDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinAliasNameObfuscator#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    DictionaryNameFactory nameFactory = mock(DictionaryNameFactory.class);
    doNothing().when(nameFactory).reset();
    KotlinAliasNameObfuscator kotlinAliasNameObfuscator = new KotlinAliasNameObfuscator(nameFactory);
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinAliasNameObfuscator.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
    verify(nameFactory).reset();
  }

  /**
   * Method under test:
   * {@link KotlinAliasNameObfuscator#visitTypeAlias(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeAliasMetadata)}
   */
  @Test
  void testVisitTypeAlias() {
    // Arrange
    KotlinAliasNameObfuscator kotlinAliasNameObfuscator = new KotlinAliasNameObfuscator(new NumericNameFactory());
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinTypeAliasMetadata kotlinTypeAliasMetadata = mock(KotlinTypeAliasMetadata.class);
    doNothing().when(kotlinTypeAliasMetadata)
        .expandedTypeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinAliasNameObfuscator.visitTypeAlias(clazz, kotlinDeclarationContainerMetadata, kotlinTypeAliasMetadata);

    // Assert
    verify(kotlinTypeAliasMetadata).expandedTypeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinTypeVisitor.class));
  }
}
