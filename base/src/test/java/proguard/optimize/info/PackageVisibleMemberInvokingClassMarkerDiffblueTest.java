package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;

class PackageVisibleMemberInvokingClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
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
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
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
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
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
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
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
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
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
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    PackageVisibleMemberInvokingClassMarker packageVisibleMemberInvokingClassMarker = new PackageVisibleMemberInvokingClassMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getAccessFlags()).thenReturn(1);

    // Act
    packageVisibleMemberInvokingClassMarker.visitAnyClass(clazz);

    // Assert
    verify(clazz).getAccessFlags();
  }

  /**
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}
   */
  @Test
  void testInvokesPackageVisibleMembers() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(clazz));
  }

  /**
   * Method under test:
   * {@link PackageVisibleMemberInvokingClassMarker#invokesPackageVisibleMembers(Clazz)}
   */
  @Test
  void testInvokesPackageVisibleMembers2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(PackageVisibleMemberInvokingClassMarker.invokesPackageVisibleMembers(clazz));
  }
}
