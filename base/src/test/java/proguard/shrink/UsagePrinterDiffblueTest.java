package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.util.Processable;

class UsagePrinterDiffblueTest {
  /**
   * Test {@link UsagePrinter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
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
   * Test {@link UsagePrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code false}.</li>
   *   <li>Then calls {@link ProgramClass#getName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ShortestUsageMarker isUsed(Processable) return 'false'; then calls getName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenShortestUsageMarkerIsUsedReturnFalse_thenCallsGetName() {
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
   * Test {@link UsagePrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls fieldsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsFieldsAccept() {
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
   * Test {@link UsagePrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>When {@link ProgramClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); when ProgramClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_whenProgramClass() {
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
   * Test {@link UsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ShortestUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given ShortestUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenShortestUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
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
   * Test {@link UsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ShortestUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given ShortestUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void UsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenShortestUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
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
