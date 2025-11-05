package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class ClassRenamerDiffblueTest {
  /**
   * Method under test: {@link ClassRenamer#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> classRenamer.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassRenamer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    classRenamer.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
  }

  /**
   * Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("foo")).when(extraClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo("Processing Info");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> classRenamer.visitLibraryClass(libraryClass));
    verify(extraClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    classRenamer.visitProgramMember(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember2() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMember programMember = mock(ProgramMember.class);
    when(programMember.getProcessingInfo()).thenReturn("Name");
    when(programMember.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    classRenamer.visitProgramMember(programClass, programMember);

    // Assert
    verify(programMember).getName(isA(Clazz.class));
    verify(programMember, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  void testVisitLibraryMember() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    assertEquals("Name", libraryMember.name);
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  void testVisitLibraryMember2() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    assertEquals("Name", libraryMember.name);
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  void testVisitLibraryMember3() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Processing Info", libraryMember.name);
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  void testVisitLibraryMember4() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Processing Info", libraryMember.name);
  }

  /**
   * Method under test:
   * {@link ClassRenamer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classRenamer.visitClassConstant(clazz, new ClassConstant());

    // Assert that nothing has changed
    verify(clazz).getName();
    verify(clazz).getProcessingInfo();
  }
}
