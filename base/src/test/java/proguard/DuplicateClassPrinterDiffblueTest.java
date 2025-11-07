package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.util.WarningPrinter;

class DuplicateClassPrinterDiffblueTest {
  /**
   * Test {@link DuplicateClassPrinter#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DuplicateClassPrinter#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void DuplicateClassPrinter.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    DuplicateClassPrinter duplicateClassPrinter = new DuplicateClassPrinter(
        new WarningPrinter(new PrintWriter(new StringWriter())));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> duplicateClassPrinter.visitAnyClass(new LibraryClass()));
  }
}
