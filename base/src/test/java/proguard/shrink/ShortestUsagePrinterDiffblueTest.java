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
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
import proguard.util.SimpleProcessable;

class ShortestUsagePrinterDiffblueTest {
  /**
   * Test {@link ShortestUsagePrinter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ShortestUsagePrinter shortestUsagePrinter = new ShortestUsagePrinter(shortestUsageMarker, true,
        new PrintWriter(new StringWriter()));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> shortestUsagePrinter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsGetProcessingInfo() {
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
   * Test {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsGetShortestUsageMark() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField2() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link Clazz} {@link Clazz#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link Clazz#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given Clazz accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenClazzAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenCallsGetProcessingInfo() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenCallsGetString() {
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
   * Test {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenThrowUnsupportedOperationException() {
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
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
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
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod2() {
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
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link Clazz} {@link Clazz#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link Clazz#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given Clazz accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenClazzAcceptDoesNothing_thenCallsAccept() {
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

  /**
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsGetProcessingInfo() {
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
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsGetString() {
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
   * Test {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ShortestUsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ShortestUsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenThrowUnsupportedOperationException() {
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
}
