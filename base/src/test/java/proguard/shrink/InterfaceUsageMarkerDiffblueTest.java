package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.util.Processable;

class InterfaceUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link InterfaceUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> interfaceUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doThrow(new UnsupportedOperationException("foo")).when(classUsageMarker).markAsUsed(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> interfaceUsageMarker.visitProgramClass(new ProgramClass()));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUsed(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).superClassConstantAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(programClass).superClassConstantAccept(isA(ConstantVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(false);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUnused(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act
    interfaceUsageMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUnused(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doThrow(new UnsupportedOperationException("foo")).when(classUsageMarker).markAsUnused(Mockito.<Processable>any());
    when(classUsageMarker.isPossiblyUsed(Mockito.<Processable>any())).thenReturn(true);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(classUsageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).interfaceConstantsAccept(Mockito.<ConstantVisitor>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> interfaceUsageMarker.visitProgramClass(programClass));
    verify(programClass).interfaceConstantsAccept(isA(ConstantVisitor.class));
    verify(classUsageMarker).isPossiblyUsed(isA(Processable.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
    verify(classUsageMarker).markAsUnused(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new LibraryClass());

    // Act
    interfaceUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    interfaceUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link InterfaceUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant2() {
    // Arrange
    InterfaceUsageMarker interfaceUsageMarker = new InterfaceUsageMarker(
        new ClassUsageMarker(new ShortestUsageMarker()));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    interfaceUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    assertNull(utf8Constant.getProcessingInfo());
  }
}
