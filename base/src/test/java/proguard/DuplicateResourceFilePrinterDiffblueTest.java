package proguard;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.util.WarningLogger;
import proguard.classfile.util.WarningPrinter;
import proguard.resources.kotlinmodule.KotlinModule;

class DuplicateResourceFilePrinterDiffblueTest {
  /**
   * Method under test:
   * {@link DuplicateResourceFilePrinter#visitKotlinModule(KotlinModule)}
   */
  @Test
  void testVisitKotlinModule() {
    // Arrange
    WarningLogger notePrinter = mock(WarningLogger.class);
    doNothing().when(notePrinter).print(Mockito.<String>any(), Mockito.<String>any());
    DuplicateResourceFilePrinter duplicateResourceFilePrinter = new DuplicateResourceFilePrinter(notePrinter);
    KotlinModule kotlinModule = mock(KotlinModule.class);
    when(kotlinModule.getFileName()).thenReturn("foo.txt");

    // Act
    duplicateResourceFilePrinter.visitKotlinModule(kotlinModule);

    // Assert
    verify(notePrinter).print(eq("foo.txt"), eq("Note: duplicate definition of Kotlin module file [foo.txt]"));
    verify(kotlinModule, atLeast(1)).getFileName();
  }

  /**
   * Method under test:
   * {@link DuplicateResourceFilePrinter#visitKotlinModule(KotlinModule)}
   */
  @Test
  void testVisitKotlinModule2() {
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
