package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.ClassVisitor;

class MemberReferenceGeneralizerDiffblueTest {
  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    memberReferenceGeneralizer.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    memberReferenceGeneralizer.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -74, 1));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getProcessingFlags()).thenReturn(1);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(clazz).getProcessingFlags();
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant3() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(0);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(clazz).getProcessingFlags();
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant4() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    when(refConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(refConstant.getType(Mockito.<Clazz>any())).thenReturn("Type");

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).getName(isA(Clazz.class));
    verify(refConstant).getType(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    memberReferenceGeneralizer.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    memberReferenceGeneralizer.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(1);
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getProcessingFlags();
  }

  /**
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass2() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(-74);
    when(clazz.getSuperClass()).thenReturn(new LibraryClass(-74, "This Class Name", "Super Class Name"));
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getSuperClass();
    verify(clazz).getProcessingFlags();
  }

  /**
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass3() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(-74);
    when(clazz.getSuperClass()).thenReturn(libraryClass);
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getSuperClass();
    verify(clazz).getProcessingFlags();
  }
}
