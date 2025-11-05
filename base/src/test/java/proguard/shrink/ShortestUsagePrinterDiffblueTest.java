package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.util.Processable;

class ShortestUsagePrinterDiffblueTest {
  /**
   * Method under test: {@link ShortestUsagePrinter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, true,
        new PrintWriter(new StringWriter()));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> shortestUsagePrinter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, true,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    when(programClass.getName()).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark("Just cause"));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, true,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    shortestUsagePrinter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getName();
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn("Processing Info");
    when(programField.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getName();
    verify(programField).getDescriptor(isA(Clazz.class));
    verify(programField).getName(isA(Clazz.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark("Just cause"));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramField programField = mock(ProgramField.class);
    when(programField.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getName();
    verify(programField).getDescriptor(isA(Clazz.class));
    verify(programField).getName(isA(Clazz.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenThrow(new UnsupportedOperationException("."));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramField programField = mock(ProgramField.class);
    when(programField.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> shortestUsagePrinter.visitProgramField(programClass, programField));
    verify(programClass).getName();
    verify(programField).getDescriptor(isA(Clazz.class));
    verify(programField).getName(isA(Clazz.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass(1, ".", ".")));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramField programField = mock(ProgramField.class);
    when(programField.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getName();
    verify(programField).getDescriptor(isA(Clazz.class));
    verify(programField).getName(isA(Clazz.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField6() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), "Just cause", 1, clazz));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramField programField = mock(ProgramField.class);
    when(programField.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramField(programClass, programField);

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
    verify(programClass).getName();
    verify(programField).getDescriptor(isA(Clazz.class));
    verify(programField).getName(isA(Clazz.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    shortestUsagePrinter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getName();
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getName();
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark("Just cause"));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getName();
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenThrow(new UnsupportedOperationException("."));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> shortestUsagePrinter.visitProgramMethod(programClass, programMethod));
    verify(programClass).getName();
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass(1, ".", ".")));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getName();
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod6() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.getShortestUsageMark(Mockito.<Processable>any()))
        .thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), "Just cause", 1, clazz));
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, false,
        new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestUsagePrinter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
    verify(programClass).getName();
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(shortestUsageMarker).getShortestUsageMark(isA(Processable.class));
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }
}
