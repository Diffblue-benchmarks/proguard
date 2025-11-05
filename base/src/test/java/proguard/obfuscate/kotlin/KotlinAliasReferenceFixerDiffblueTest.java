package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinTypeAliasMetadata;
import proguard.classfile.kotlin.KotlinTypeMetadata;
import proguard.classfile.kotlin.flags.KotlinTypeAliasFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;

class KotlinAliasReferenceFixerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinAliasReferenceFixer#visitAnyType(Clazz, KotlinTypeMetadata)}
   */
  @Test
  void testVisitAnyType() {
    // Arrange
    KotlinAliasReferenceFixer kotlinAliasReferenceFixer = new KotlinAliasReferenceFixer();
    LibraryClass clazz = new LibraryClass();
    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinTypeAliasFlags flags = new KotlinTypeAliasFlags(visibility);
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    kotlinClassKindMetadata.k = 1;
    KotlinTypeAliasMetadata kotlinTypeAliasMetadata = new KotlinTypeAliasMetadata(flags, "Name");

    kotlinTypeAliasMetadata.referencedDeclarationContainer = kotlinClassKindMetadata;
    KotlinTypeMetadata kotlinTypeMetadata = KotlinTypeMetadata.starProjection();
    kotlinTypeMetadata.aliasName = "Kotlin Type Metadata";
    kotlinTypeMetadata.referencedTypeAlias = kotlinTypeAliasMetadata;

    // Act
    kotlinAliasReferenceFixer.visitAnyType(clazz, kotlinTypeMetadata);

    // Assert
    assertEquals("null.Name", kotlinTypeMetadata.aliasName);
  }
}
