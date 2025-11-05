package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ExtendedLineNumberInfo;
import proguard.classfile.attribute.LineNumberInfo;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.ExceptionInfoVisitor;
import proguard.classfile.attribute.visitor.LineNumberInfoVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.visitor.MemberVisitor;

class MethodInlinerDiffblueTest {
  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    when(clazz.getName()).thenReturn("Name");
    ProgramMethod method = new ProgramMethod();

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz, atLeast(1)).getName();
    verify(clazz, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getString(anyInt())).thenReturn("<init>");
    when(clazz.getName()).thenReturn("Name");
    ProgramMethod method = new ProgramMethod();

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz, atLeast(1)).getName();
    verify(clazz, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute3() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getName()).thenReturn("Name");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, null);

    // Assert
    verify(clazz).getName();
    verify(method).getDescriptor(isA(Clazz.class));
    verify(method).getName(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute4() throws UnsupportedEncodingException {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getName()).thenReturn("Name");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, new CodeAttribute(1, 3, 3, 3, "AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(clazz, atLeast(1)).getName();
    verify(method, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(method, atLeast(1)).getName(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute5() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getProcessingFlags()).thenReturn(1);
    doNothing().when(method).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .exceptionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<ExceptionInfoVisitor>any());
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    shortMethodInliner.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(method).getName(isA(Clazz.class));
    verify(method).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(codeAttribute).accept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).exceptionsAccept(isA(Clazz.class), isA(Method.class), isA(ExceptionInfoVisitor.class));
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
    verify(method).getProcessingFlags();
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitCodeAttribute0(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute0() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addExtraFeatureName(Mockito.<String>any());
    clazz.addExtraFeatureName("Feature Name");
    ProgramMethod method = mock(ProgramMethod.class);
    when(method.getProcessingFlags()).thenReturn(1);
    doNothing().when(method).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    when(method.getName(Mockito.<Clazz>any())).thenReturn("Name");
    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(codeAttribute)
        .exceptionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<ExceptionInfoVisitor>any());
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    shortMethodInliner.visitCodeAttribute0(clazz, method, codeAttribute);

    // Assert
    verify(method).getName(isA(Clazz.class));
    verify(method).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(codeAttribute).accept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).attributesAccept(isA(Clazz.class), isA(Method.class), isA(AttributeVisitor.class));
    verify(codeAttribute).exceptionsAccept(isA(Clazz.class), isA(Method.class), isA(ExceptionInfoVisitor.class));
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
    verify(clazz).addExtraFeatureName(eq("Feature Name"));
    verify(method).getProcessingFlags();
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = mock(LineNumberTableAttribute.class);
    doNothing().when(lineNumberTableAttribute)
        .lineNumbersAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LineNumberInfoVisitor>any());

    // Act
    shortMethodInliner.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    verify(lineNumberTableAttribute).lineNumbersAccept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(LineNumberInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    shortMethodInliner.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = libraryMethod;

    // Act
    shortMethodInliner.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(libraryMethod).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link MethodInliner#visitLineNumberInfo(Clazz, Method, CodeAttribute, LineNumberInfo)}
   */
  @Test
  void testVisitLineNumberInfo() {
    // Arrange
    ShortMethodInliner shortMethodInliner = new ShortMethodInliner(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ExtendedLineNumberInfo lineNumberInfo = mock(ExtendedLineNumberInfo.class);
    when(lineNumberInfo.getSource()).thenThrow(new IllegalArgumentException("foo"));

    // Act
    shortMethodInliner.visitLineNumberInfo(clazz, method, codeAttribute, lineNumberInfo);

    // Assert
    verify(lineNumberInfo).getSource();
  }
}
