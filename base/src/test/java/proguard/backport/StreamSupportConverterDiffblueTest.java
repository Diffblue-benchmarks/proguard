package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.backport.AbstractAPIConverter.TypeReplacement;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.util.FixedStringMatcher;

class StreamSupportConverterDiffblueTest {
  /**
   * Test {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}.
   * <p>
   * Method under test: {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  @DisplayName("Test new StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StreamSupportConverter.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool, proguard.classfile.util.WarningPrinter, proguard.classfile.visitor.ClassVisitor, proguard.classfile.instruction.visitor.InstructionVisitor)"})
  void testNewStreamSupportConverter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java/util/stream/**", new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    TypeReplacement missingResult = (new StreamSupportConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }

  /**
   * Test {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}.
   * <p>
   * Method under test: {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  @DisplayName("Test new StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StreamSupportConverter.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool, proguard.classfile.util.WarningPrinter, proguard.classfile.visitor.ClassVisitor, proguard.classfile.instruction.visitor.InstructionVisitor)"})
  void testNewStreamSupportConverter2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java8/util/DoubleSummaryStatistics", new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act and Assert
    TypeReplacement missingResult = (new StreamSupportConverter(programClassPool, libraryClassPool, warningPrinter,
        modifiedClassVisitor, new DuplicateInitializerInvocationFixer())).missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }
}
