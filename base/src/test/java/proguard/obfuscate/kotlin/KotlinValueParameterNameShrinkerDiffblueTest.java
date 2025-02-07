package proguard.obfuscate.kotlin;

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
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.visitor.KotlinConstructorVisitor;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinPropertyVisitor;

class KotlinValueParameterNameShrinkerDiffblueTest {
  /**
   * Test {@link KotlinValueParameterNameShrinker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinDeclarationContainerMetadata#functionsAccept(Clazz, KotlinFunctionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterNameShrinker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinValueParameterNameShrinker.visitKotlinDeclarationContainerMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata)"})
  void testVisitKotlinDeclarationContainerMetadata_thenCallsFunctionsAccept() {
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
   * Test {@link KotlinValueParameterNameShrinker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinClassKindMetadata#constructorsAccept(Clazz, KotlinConstructorVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterNameShrinker#visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinClassMetadata(Clazz, KotlinClassKindMetadata); then calls constructorsAccept(Clazz, KotlinConstructorVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinValueParameterNameShrinker.visitKotlinClassMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinClassKindMetadata)"})
  void testVisitKotlinClassMetadata_thenCallsConstructorsAccept() {
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
   * Test {@link KotlinValueParameterNameShrinker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinSyntheticClassKindMetadata#functionsAccept(Clazz, KotlinFunctionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinValueParameterNameShrinker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.kotlin.KotlinValueParameterNameShrinker.visitKotlinSyntheticClassMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata)"})
  void testVisitKotlinSyntheticClassMetadata_thenCallsFunctionsAccept() {
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
}
