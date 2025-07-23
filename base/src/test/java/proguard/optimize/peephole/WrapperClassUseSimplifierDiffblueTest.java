package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class WrapperClassUseSimplifierDiffblueTest {
  /**
   * Test {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    ProgramClass referencedClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {new ClassConstant()},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ClassOptimizationInfo());

    // Act
    wrapperClassUseSimplifier.visitFieldrefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant2() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    ProgramClass referencedClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {new ClassConstant()},
            1,
            1,
            1,
            "Feature Name",
            1,
            new ProgramClassOptimizationInfo());

    // Act
    wrapperClassUseSimplifier.visitFieldrefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant_givenNull() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    ProgramClass referencedClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {new ClassConstant()},
            1,
            1,
            1,
            "Feature Name",
            1,
            classOptimizationInfo);

    // Act
    wrapperClassUseSimplifier.visitFieldrefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(classOptimizationInfo).getTargetClass();
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant_thenCallsGetProcessingInfo() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    ProgramClass referencedClass =
        new ProgramClass(
            1,
            3,
            new Constant[] {new ClassConstant()},
            1,
            1,
            1,
            "Feature Name",
            1,
            classOptimizationInfo);

    // Act
    wrapperClassUseSimplifier.visitFieldrefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code <init>}.
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code <init>}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz,
   * MethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodrefConstant(Clazz, MethodrefConstant); given '<init>'; when LibraryClass getName(int) return '<init>'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitMethodrefConstant(Clazz, MethodrefConstant)"
  })
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
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link LibraryClass} {@link LibraryClass#getName(int)} return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz,
   * MethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitMethodrefConstant(Clazz, MethodrefConstant); given 'Name'; when LibraryClass getName(int) return 'Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperClassUseSimplifier.visitMethodrefConstant(Clazz, MethodrefConstant)"
  })
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
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    wrapperClassUseSimplifier.visitClassConstant(
        clazz,
        new ClassConstant(
            1,
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ClassOptimizationInfo())));

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);

    // Act
    wrapperClassUseSimplifier.visitClassConstant(
        clazz,
        new ClassConstant(
            1,
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ProgramClassOptimizationInfo())));

    // Assert
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); given 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenNull() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);

    // Act
    wrapperClassUseSimplifier.visitClassConstant(
        clazz,
        new ClassConstant(
            1,
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                classOptimizationInfo)));

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsGetProcessingInfo() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 1, 2, 1);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);

    // Act
    wrapperClassUseSimplifier.visitClassConstant(
        clazz,
        new ClassConstant(
            1,
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                classOptimizationInfo)));

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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

  /**
   * Test {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperClassUseSimplifier#visitClassConstant(Clazz,
   * ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperClassUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    wrapperClassUseSimplifier.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
  }
}
