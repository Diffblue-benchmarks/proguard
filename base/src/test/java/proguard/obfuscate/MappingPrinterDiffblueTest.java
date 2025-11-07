package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.MemberVisitor;

class MappingPrinterDiffblueTest {
  /**
   * Test {@link MappingPrinter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MappingPrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given 'Processing Info'; then calls attributesAccept(AttributeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MappingPrinter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenProcessingInfo_thenCallsAttributesAccept() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    when(programClass.getName()).thenReturn("Name");

    // Act
    mappingPrinter.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Given {@code Source}.</li>
   *   <li>Then calls {@link Member#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); given 'Source'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_givenSource_thenCallsGetDescriptor() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn("Processing Info");
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");
    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    when(lineNumberTableAttribute.getHighestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getLowestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getSource(anyInt())).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(method).getDescriptor(isA(Clazz.class));
    verify(method).getName(isA(Clazz.class));
    verify(lineNumberTableAttribute).getHighestLineNumber();
    verify(lineNumberTableAttribute).getLowestLineNumber();
    verify(lineNumberTableAttribute).getSource(eq(0));
    verify(method, atLeast(1)).getProcessingInfo();
  }
}
