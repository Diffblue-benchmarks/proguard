package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.visitor.InnerClassesInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.testutils.cpa.NamedClass;

class NameMarkerDiffblueTest {
  /**
   * Method under test: {@link NameMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> nameMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link NameMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    NamedClass programClass = new NamedClass("Member Name");

    // Act
    nameMarker.visitProgramClass(programClass);

    // Assert
    assertEquals("Member Name", programClass.getProcessingInfo());
  }

  /**
   * Method under test: {@link NameMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getName()).thenReturn("Name");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).getName();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn("Processing Info");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getName(isA(Clazz.class));
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getName(isA(Clazz.class));
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryField2 = new LibraryField(1, "Name", "Descriptor");
    libraryField2.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField2);

    // Assert
    Object processingInfo = libraryField2.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    LibraryField libraryField = new LibraryField();
    libraryMethod.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    Object processingInfo = libraryMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test:
   * {@link NameMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute = mock(InnerClassesAttribute.class);
    doNothing().when(innerClassesAttribute)
        .innerClassEntriesAccept(Mockito.<Clazz>any(), Mockito.<InnerClassesInfoVisitor>any());

    // Act
    nameMarker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    verify(innerClassesAttribute).innerClassEntriesAccept(isA(Clazz.class), isA(InnerClassesInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doThrow(new UnsupportedOperationException("Name")).when(clazz)
        .constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1)));
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Method under test:
   * {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.referencedClass);
  }

  /**
   * Method under test:
   * {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertNull(clazz2.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();

    NamedClass namedClass = new NamedClass("Member Name");
    namedClass.addSubClass(new LibraryClass());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = namedClass;

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof NamedClass);
    assertEquals("Member Name", clazz2.getProcessingInfo());
  }

  /**
   * Method under test: {@link NameMarker#keepClassName(Clazz)}
   */
  @Test
  void testKeepClassName() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    doNothing().when(clazz).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.keepClassName(clazz);

    // Assert
    verify(clazz).getName();
    verify(clazz).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test: {@link NameMarker#keepClassName(Clazz)}
   */
  @Test
  void testKeepClassName2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).addProcessingFlags((int[]) any());
    classConstant.addProcessingFlags(2, 1, 2, 1);
    ClassConstant classConstant2 = mock(ClassConstant.class);
    when(classConstant2.getName(Mockito.<Clazz>any())).thenReturn("Name");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant, classConstant2}, 1, 1, 1);

    // Act
    nameMarker.keepClassName(clazz);

    // Assert
    verify(classConstant2).getName(isA(Clazz.class));
    verify(classConstant).addProcessingFlags((int[]) any());
    assertEquals("Name", clazz.getProcessingInfo());
  }
}
