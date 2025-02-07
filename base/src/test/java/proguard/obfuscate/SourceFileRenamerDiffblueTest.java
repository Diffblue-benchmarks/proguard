package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;

class SourceFileRenamerDiffblueTest {
  /**
   * Test {@link SourceFileRenamer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SourceFileRenamer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls attributesAccept(AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.obfuscate.SourceFileRenamer.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsAttributesAccept() {
    // Arrange
    SourceFileRenamer sourceFileRenamer = new SourceFileRenamer("New Source File Attribute");
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    sourceFileRenamer.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
  }
}
