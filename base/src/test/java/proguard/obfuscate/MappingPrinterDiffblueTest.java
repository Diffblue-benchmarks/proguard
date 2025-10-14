package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
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
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LineNumberInfo;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.MemberVisitor;

class MappingPrinterDiffblueTest {
  /**
   * Test {@link MappingPrinter#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   *   <li>Then calls {@link ProgramClass#attributesAccept(AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link MappingPrinter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given 'Processing Info'; then calls attributesAccept(AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   * Test {@link MappingPrinter#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link CodeAttribute#attributesAccept(Clazz, Method, AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link MappingPrinter#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls attributesAccept(Clazz, Method, AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MappingPrinter.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsAttributesAccept() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing()
        .when(codeAttribute)
        .attributesAccept(
            Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());

    // Act
    mappingPrinter.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute)
        .attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberInfo lineNumberInfo = mock(LineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenReturn("Source");
    LineNumberInfo[] lineNumberTable = new LineNumberInfo[] {lineNumberInfo};

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz, method, codeAttribute, new LineNumberTableAttribute(1, 1, lineNumberTable));

    // Assert
    verify(lineNumberInfo, atLeast(1)).getSource();
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute2() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberInfo lineNumberInfo = mock(LineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz,
        method,
        codeAttribute,
        new LineNumberTableAttribute(
            1, 2, new LineNumberInfo[] {lineNumberInfo, new LineNumberInfo(1, 2)}));

    // Assert
    verify(lineNumberInfo, atLeast(1)).getSource();
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute3() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberInfo lineNumberInfo = mock(LineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz,
        method,
        codeAttribute,
        new LineNumberTableAttribute(
            1, 2, new LineNumberInfo[] {lineNumberInfo, new LineNumberInfo(1, 0)}));

    // Assert
    verify(lineNumberInfo, atLeast(1)).getSource();
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute4() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberInfo lineNumberInfo = mock(LineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenReturn("Source");

    LineNumberInfo lineNumberInfo2 = mock(LineNumberInfo.class);
    when(lineNumberInfo2.getSource()).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz,
        method,
        codeAttribute,
        new LineNumberTableAttribute(1, 2, new LineNumberInfo[] {lineNumberInfo, lineNumberInfo2}));

    // Assert
    verify(lineNumberInfo, atLeast(1)).getSource();
    verify(lineNumberInfo2, atLeast(1)).getSource();
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link LineNumberTableAttribute#getHighestLineNumber()}.
   * </ul>
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then calls getHighestLineNumber()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute_thenCallsGetHighestLineNumber() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    when(lineNumberTableAttribute.getHighestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getLowestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getSource(anyInt())).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(lineNumberTableAttribute).getHighestLineNumber();
    verify(lineNumberTableAttribute).getLowestLineNumber();
    verify(lineNumberTableAttribute).getSource(0);
  }

  /**
   * Test {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute,
   * LineNumberTableAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link LineNumberTableAttribute#getHighestLineNumber()}.
   * </ul>
   *
   * <p>Method under test: {@link MappingPrinter#visitLineNumberTableAttribute(Clazz, Method,
   * CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then calls getHighestLineNumber()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MappingPrinter.visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)"
  })
  void testVisitLineNumberTableAttribute_thenCallsGetHighestLineNumber2() {
    // Arrange
    MappingPrinter mappingPrinter = new MappingPrinter(new PrintWriter(new StringWriter()));
    LibraryClass clazz = new LibraryClass();

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo("Processing Info");
    CodeAttribute codeAttribute = new CodeAttribute(1);

    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    when(lineNumberTableAttribute.getHighestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getLowestLineNumber()).thenReturn(2);
    when(lineNumberTableAttribute.getSource(anyInt())).thenReturn("Source");

    // Act
    mappingPrinter.visitLineNumberTableAttribute(
        clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(lineNumberTableAttribute).getHighestLineNumber();
    verify(lineNumberTableAttribute).getLowestLineNumber();
    verify(lineNumberTableAttribute).getSource(0);
  }
}
