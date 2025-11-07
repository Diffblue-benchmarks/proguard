package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.visitor.ClassVisitor;

class WrapperClassUseSimplifierDiffblueTest {
  /**
   * Test {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); given '<init>'; when LibraryClass getName(int) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitMethodrefConstant(Clazz, MethodrefConstant)"})
  void testVisitMethodrefConstant_givenInit_whenLibraryClassGetNameReturnInit() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("<init>");

    // Act
    wrapperClassUseSimplifier.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'Name'; when LibraryClass getName(int) return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitMethodrefConstant(Clazz, MethodrefConstant)"})
  void testVisitMethodrefConstant_givenName_whenLibraryClassGetNameReturnName() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");

    // Act
    wrapperClassUseSimplifier.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    wrapperClassUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsReferencedClassAccept() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    wrapperClassUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }
}
