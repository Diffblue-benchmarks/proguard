package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
import proguard.util.Processable;

class UsagePrinterDiffblueTest {
  /**
   * Test {@link UsagePrinter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return
   *       {@code true}.
   *   <li>Then calls {@link ShortestUsageMarker#isUsed(Processable)}.
   * </ul>
   *
   * <p>Method under test: {@link UsagePrinter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); given ShortestUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void UsagePrinter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenShortestUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter =
        new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = new ProgramClass();

    // Act
    usagePrinter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link ShortestUsageMarker} {@link ShortestUsageMarker#isUsed(Processable)} return
   *       {@code true}.
   *   <li>Then calls {@link ShortestUsageMarker#isUsed(Processable)}.
   * </ul>
   *
   * <p>Method under test: {@link UsagePrinter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given ShortestUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void UsagePrinter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenShortestUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    UsagePrinter usagePrinter =
        new UsagePrinter(usageMarker, true, new PrintWriter(new StringWriter()));
    ProgramClass programClass = new ProgramClass();

    // Act
    usagePrinter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(usageMarker).isUsed(isA(Processable.class));
  }
}
