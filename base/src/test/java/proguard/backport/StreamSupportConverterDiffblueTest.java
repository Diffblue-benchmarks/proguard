package proguard.backport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.backport.AbstractAPIConverter.TypeReplacement;
import proguard.classfile.ClassPool;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.kotlin.KotlinConstants;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;
import proguard.util.FixedStringMatcher;

class StreamSupportConverterDiffblueTest {
  /**
   * Test {@link StreamSupportConverter#StreamSupportConverter(ClassPool, ClassPool, WarningPrinter,
   * ClassVisitor, InstructionVisitor)}.
   *
   * <p>Method under test: {@link StreamSupportConverter#StreamSupportConverter(ClassPool,
   * ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)}
   */
  @Test
  @DisplayName(
      "Test new StreamSupportConverter(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamSupportConverter.<init>(ClassPool, ClassPool, WarningPrinter, ClassVisitor, InstructionVisitor)"
  })
  void testNewStreamSupportConverter() {
    // Arrange
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);

    // Act
    StreamSupportConverter actualStreamSupportConverter =
        new StreamSupportConverter(
            KotlinConstants.dummyClassPool,
            KotlinConstants.dummyClassPool,
            warningPrinter,
            modifiedClassVisitor,
            new CodeAttributeEditor());

    // Assert
    TypeReplacement missingResult = actualStreamSupportConverter.missing("Class Name");
    assertTrue(missingResult.classNameMatcher instanceof FixedStringMatcher);
    assertEquals("Class Name", missingResult.matchingClassName);
    assertNull(missingResult.replacementClassName);
  }
}
