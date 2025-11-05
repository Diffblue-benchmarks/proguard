package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.util.Processable;

class UsagePrinterDiffblueTest {
  /**
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter = new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    usagePrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    UsagePrinter usagePrinter = new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    usagePrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter = new UsagePrinter(usageMarker, false, new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    usagePrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).getName();
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);

    // Act
    (new UsagePrinter(usageMarker, false, new PrintWriter(new StringWriter())))
        .visitProgramClass(mock(ProgramClass.class));

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter = new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = new ProgramClass();

    // Act
    usagePrinter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter = new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = new ProgramClass();

    // Act
    usagePrinter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }
}
