package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class PackageVisibleMemberInvokingClassMarkerDiffblueTest {
  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = null;
    stringConstant.referencedClass = libraryClass;

    // Act
    packageVisibleMemberInvokingClassMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Given {@link LibraryField} {@link LibraryMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); given LibraryField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenLibraryFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = libraryField;
    stringConstant.referencedClass = mock(LibraryClass.class);

    // Act
    packageVisibleMemberInvokingClassMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryField).accept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link StringConstant#referencedClassAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsReferencedClassAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedClassAccept(Mockito.<ClassVisitor>any());
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    packageVisibleMemberInvokingClassMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedClassAccept(isA(ClassVisitor.class));
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedClass = libraryClass;

    // Act
    packageVisibleMemberInvokingClassMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedMemberAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then calls referencedMemberAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_thenCallsReferencedMemberAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    packageVisibleMemberInvokingClassMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedMemberAccept(isA(MemberVisitor.class));
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    packageVisibleMemberInvokingClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberInvokingClassMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsReferencedClassAccept() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    packageVisibleMemberInvokingClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}
   */
  @Test
  @DisplayName("Test invokesPackageVisibleMembers(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(Clazz)"})
  void testInvokesPackageVisibleMembers_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(clazz));
  }

  /**
   * Test {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}
   */
  @Test
  @DisplayName("Test invokesPackageVisibleMembers(Clazz); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(Clazz)"})
  void testInvokesPackageVisibleMembers_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(clazz));
  }
}
