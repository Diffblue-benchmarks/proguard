package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.classfile.util.WarningPrinter;
import proguard.classfile.visitor.ClassVisitor;

class GsonDeserializationInvocationFinderDiffblueTest {
  /**
   * Method under test:
   * {@link GsonDeserializationInvocationFinder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    ClassVisitor domainClassVisitor = mock(ClassVisitor.class);
    GsonDeserializationInvocationFinder gsonDeserializationInvocationFinder = new GsonDeserializationInvocationFinder(
        programClassPool, libraryClassPool, domainClassVisitor,
        new WarningPrinter(new PrintWriter(new StringWriter())));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    BranchInstruction instruction = mock(BranchInstruction.class);
    doNothing().when(instruction)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(), anyInt(),
            Mockito.<InstructionVisitor>any());

    // Act
    gsonDeserializationInvocationFinder.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(instruction, atLeast(1)).accept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class), eq(2),
        Mockito.<InstructionVisitor>any());
  }
}
