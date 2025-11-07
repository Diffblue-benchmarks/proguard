package proguard;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.util.WarningPrinter;
import proguard.resources.file.ResourceFile;
import proguard.resources.kotlinmodule.KotlinModule;

class DuplicateResourceFilePrinterDiffblueTest {
  /**
   * Test {@link DuplicateResourceFilePrinter#visitKotlinModule(KotlinModule)}.
   * <ul>
   *   <li>Given {@code foo.txt}.</li>
   *   <li>Then calls {@link ResourceFile#getFileName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DuplicateResourceFilePrinter#visitKotlinModule(KotlinModule)}
   */
  @Test
  @DisplayName("Test visitKotlinModule(KotlinModule); given 'foo.txt'; then calls getFileName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateResourceFilePrinter.visitKotlinModule(KotlinModule)"})
  void testVisitKotlinModule_givenFooTxt_thenCallsGetFileName() {
    // Arrange
    DuplicateResourceFilePrinter duplicateResourceFilePrinter = new DuplicateResourceFilePrinter(
        new WarningPrinter(new PrintWriter(new StringWriter())));
    KotlinModule kotlinModule = mock(KotlinModule.class);
    when(kotlinModule.getFileName()).thenReturn("foo.txt");

    // Act
    duplicateResourceFilePrinter.visitKotlinModule(kotlinModule);

    // Assert
    verify(kotlinModule, atLeast(1)).getFileName();
  }
}
