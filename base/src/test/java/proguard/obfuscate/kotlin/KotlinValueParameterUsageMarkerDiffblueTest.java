package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinConstructorMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.visitor.KotlinConstructorVisitor;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinMetadataVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.util.Processable;

class KotlinValueParameterUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    KotlinValueParameterUsageMarker kotlinValueParameterUsageMarker = new KotlinValueParameterUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .accept(Mockito.<Clazz>any(), Mockito.<KotlinMetadataVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinValueParameterUsageMarker.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).accept(isA(Clazz.class), isA(KotlinMetadataVisitor.class));
    verify(kotlinDeclarationContainerMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  void testVisitKotlinClassMetadata() {
    // Arrange
    KotlinValueParameterUsageMarker kotlinValueParameterUsageMarker = new KotlinValueParameterUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = mock(KotlinClassKindMetadata.class);
    doNothing().when(kotlinClassKindMetadata).accept(Mockito.<Clazz>any(), Mockito.<KotlinMetadataVisitor>any());
    doNothing().when(kotlinClassKindMetadata)
        .constructorsAccept(Mockito.<Clazz>any(), Mockito.<KotlinConstructorVisitor>any());
    doNothing().when(kotlinClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinValueParameterUsageMarker.visitKotlinClassMetadata(clazz, kotlinClassKindMetadata);

    // Assert
    verify(kotlinClassKindMetadata).accept(isA(Clazz.class), isA(KotlinMetadataVisitor.class));
    verify(kotlinClassKindMetadata).constructorsAccept(isA(Clazz.class), isA(KotlinConstructorVisitor.class));
    verify(kotlinClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testVisitKotlinSyntheticClassMetadata() {
    // Arrange
    KotlinValueParameterUsageMarker kotlinValueParameterUsageMarker = new KotlinValueParameterUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata = mock(KotlinSyntheticClassKindMetadata.class);
    doNothing().when(kotlinSyntheticClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinValueParameterUsageMarker.visitKotlinSyntheticClassMetadata(clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    verify(kotlinSyntheticClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  void testVisitConstructor() {
    // Arrange
    KotlinValueParameterUsageMarker kotlinValueParameterUsageMarker = new KotlinValueParameterUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    KotlinConstructorMetadata kotlinConstructorMetadata = mock(KotlinConstructorMetadata.class);
    doNothing().when(kotlinConstructorMetadata)
        .referencedMethodAccept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    kotlinValueParameterUsageMarker.visitConstructor(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata);

    // Assert
    verify(kotlinConstructorMetadata).referencedMethodAccept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)}
   */
  @Test
  void testVisitAnyFunction() {
    // Arrange
    KotlinValueParameterUsageMarker kotlinValueParameterUsageMarker = new KotlinValueParameterUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinFunctionMetadata kotlinFunctionMetadata = mock(KotlinFunctionMetadata.class);
    doNothing().when(kotlinFunctionMetadata).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    kotlinValueParameterUsageMarker.visitAnyFunction(clazz, kotlinMetadata, kotlinFunctionMetadata);

    // Assert
    verify(kotlinFunctionMetadata).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinValueParameterUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange, Act and Assert
    assertFalse(KotlinValueParameterUsageMarker.isUsed(new LibraryClass()));
  }
}
