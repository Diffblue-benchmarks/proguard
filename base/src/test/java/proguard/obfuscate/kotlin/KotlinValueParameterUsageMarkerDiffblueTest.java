package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
   * Test {@link KotlinValueParameterUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinClassKindMetadata#accept(Clazz, KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata); then calls accept(Clazz, KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinValueParameterUsageMarker.visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)"})
  void testVisitKotlinDeclarationContainerMetadata_thenCallsAccept() {
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
   * Test {@link KotlinValueParameterUsageMarker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinClassKindMetadata#accept(Clazz, KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata); then calls accept(Clazz, KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void KotlinValueParameterUsageMarker.visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)"})
  void testVisitKotlinClassMetadata_thenCallsAccept() {
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
   * Test {@link KotlinValueParameterUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinSyntheticClassKindMetadata#functionsAccept(Clazz, KotlinFunctionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinValueParameterUsageMarker.visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)"})
  void testVisitKotlinSyntheticClassMetadata_thenCallsFunctionsAccept() {
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
   * Test {@link KotlinValueParameterUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinConstructorMetadata#referencedMethodAccept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName("Test visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); then calls referencedMethodAccept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinValueParameterUsageMarker.visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)"})
  void testVisitConstructor_thenCallsReferencedMethodAccept() {
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
   * Test {@link KotlinValueParameterUsageMarker#visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinFunctionMetadata#referencedMethodAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void KotlinValueParameterUsageMarker.visitAnyFunction(Clazz, KotlinMetadata, KotlinFunctionMetadata)"})
  void testVisitAnyFunction_thenCallsReferencedMethodAccept() {
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
   * Test {@link KotlinValueParameterUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean KotlinValueParameterUsageMarker.isUsed(Processable)"})
  void testIsUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(KotlinValueParameterUsageMarker.isUsed(new LibraryClass()));
  }
}
