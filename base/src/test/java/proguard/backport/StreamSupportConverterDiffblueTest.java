package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.BottomClassFilter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.util.FixedStringMatcher;

class StreamSupportConverterDiffblueTest {
  /**
   * Method under test:
   * {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  void testNewStreamSupportConverter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    AbstractAPIConverter.TypeReplacement missingResult = (new StreamSupportConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }

  /**
   * Method under test:
   * {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  void testNewStreamSupportConverter2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java8/util/DoubleSummaryStatistics", new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    BottomClassFilter modifiedClassVisitor = new BottomClassFilter(mock(ClassVisitor.class));
    ClassPool programClassPool2 = new ClassPool();
    ClassPool libraryClassPool2 = new ClassPool();
    WarningPrinter warningPrinter2 = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor2 = mock(ClassVisitor.class);
    StreamSupportConverter extraInstructionVisitor = new StreamSupportConverter(programClassPool2, libraryClassPool2,
        warningPrinter2, modifiedClassVisitor2, new DuplicateInitializerInvocationFixer());

    // Act and Assert
    AbstractAPIConverter.TypeReplacement missingResult = (new StreamSupportConverter(programClassPool, libraryClassPool,
        warningPrinter, modifiedClassVisitor, extraInstructionVisitor)).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    AbstractAPIConverter.TypeReplacement missingResult2 = extraInstructionVisitor.missing("Class Name");
    assertTrue(missingResult2.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertEquals("Class Name", missingResult2.matchingClassName);
    assertNull(missingResult.replacementClassName);
    assertNull(missingResult2.replacementClassName);
  }
}
