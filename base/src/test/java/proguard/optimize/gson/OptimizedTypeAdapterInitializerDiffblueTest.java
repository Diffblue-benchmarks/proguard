package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.testutils.cpa.NamedClass;

class OptimizedTypeAdapterInitializerDiffblueTest {
  /**
   * Method under test:
   * {@link OptimizedTypeAdapterInitializer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    NamedClass objectProgramClass = new NamedClass("Member Name");
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    OptimizedTypeAdapterInitializer optimizedTypeAdapterInitializer = new OptimizedTypeAdapterInitializer(
        "Type Adapter Class Name", objectProgramClass, codeAttributeEditor, serializationInfo, deserializationInfo,
        new ClassPool(), classVisitor);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    optimizedTypeAdapterInitializer.visitProgramClass(programClass);

    // Assert
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterInitializer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ProgramClass objectProgramClass = mock(ProgramClass.class);
    when(objectProgramClass.getAccessFlags()).thenReturn(1);
    when(objectProgramClass.getName()).thenReturn("Name");
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    OptimizedTypeAdapterInitializer optimizedTypeAdapterInitializer = new OptimizedTypeAdapterInitializer(
        "Type Adapter Class Name", objectProgramClass, codeAttributeEditor, serializationInfo, deserializationInfo,
        new ClassPool(), classVisitor);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    optimizedTypeAdapterInitializer.visitProgramClass(programClass);

    // Assert
    verify(objectProgramClass).getAccessFlags();
    verify(objectProgramClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link OptimizedTypeAdapterInitializer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ProgramClass objectProgramClass = mock(ProgramClass.class);
    when(objectProgramClass.getAccessFlags()).thenReturn(16384);
    when(objectProgramClass.getName()).thenReturn("Name");
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    CodeAttributeEditor codeAttributeEditor = new CodeAttributeEditor();
    OptimizedJsonInfo serializationInfo = new OptimizedJsonInfo();
    OptimizedJsonInfo deserializationInfo = new OptimizedJsonInfo();
    OptimizedTypeAdapterInitializer optimizedTypeAdapterInitializer = new OptimizedTypeAdapterInitializer(
        "Type Adapter Class Name", objectProgramClass, codeAttributeEditor, serializationInfo, deserializationInfo,
        new ClassPool(), classVisitor);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    optimizedTypeAdapterInitializer.visitProgramClass(programClass);

    // Assert
    verify(objectProgramClass).getAccessFlags();
    verify(objectProgramClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }
}
