package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningLogger;
import proguard.classfile.util.WarningPrinter;

class DuplicateClassPrinterDiffblueTest {
  /**
   * Method under test: {@link DuplicateClassPrinter#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    DuplicateClassPrinter duplicateClassPrinter = new DuplicateClassPrinter(
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> duplicateClassPrinter.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link DuplicateClassPrinter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
    // Arrange
    WarningLogger notePrinter = mock(WarningLogger.class);
    doNothing().when(notePrinter).print(Mockito.<String>any(), Mockito.<String>any());
    DuplicateClassPrinter duplicateClassPrinter = new DuplicateClassPrinter(notePrinter);

    // Act
    duplicateClassPrinter.visitLibraryClass(new LibraryClass(1, "Note: duplicate definition of library class [",
        "Note: duplicate definition of library class ["));

    // Assert
    verify(notePrinter).print(eq("Note: duplicate definition of library class ["),
        eq("Note: duplicate definition of library class [Note: duplicate definition of library class []"));
  }

  /**
   * Method under test:
   * {@link DuplicateClassPrinter#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
    // Arrange
    WarningLogger notePrinter = mock(WarningLogger.class);
    doThrow(new UnsupportedOperationException("Note: duplicate definition of library class [")).when(notePrinter)
        .print(Mockito.<String>any(), Mockito.<String>any());
    DuplicateClassPrinter duplicateClassPrinter = new DuplicateClassPrinter(notePrinter);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> duplicateClassPrinter.visitLibraryClass(new LibraryClass(1,
        "Note: duplicate definition of library class [", "Note: duplicate definition of library class [")));
    verify(notePrinter).print(eq("Note: duplicate definition of library class ["),
        eq("Note: duplicate definition of library class [Note: duplicate definition of library class []"));
  }
}
