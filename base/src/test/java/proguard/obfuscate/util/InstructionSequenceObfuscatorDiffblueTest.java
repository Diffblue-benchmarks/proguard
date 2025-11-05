package proguard.obfuscate.util;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.visitor.MemberVisitor;
import proguard.obfuscate.kotlin.KotlinIntrinsicsReplacementSequences;
import proguard.testutils.cpa.NamedMember;

class InstructionSequenceObfuscatorDiffblueTest {
  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#InstructionSequenceObfuscator(ReplacementSequences)}
   */
  @Test
  void testNewInstructionSequenceObfuscator() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any()))
        .thenReturn(new LibraryClass(1, "This Class Name", "Super Class Name"));

    // Act
    new InstructionSequenceObfuscator(new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any()))
        .thenReturn(new LibraryClass(1, "This Class Name", "Super Class Name"));
    InstructionSequenceObfuscator instructionSequenceObfuscator = new InstructionSequenceObfuscator(
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()));

    // Act
    instructionSequenceObfuscator.visitProgramClass(new ProgramClass());

    // Assert that nothing has changed
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any()))
        .thenReturn(new LibraryClass(1, "This Class Name", "Super Class Name"));
    InstructionSequenceObfuscator instructionSequenceObfuscator = new InstructionSequenceObfuscator(
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()));
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    instructionSequenceObfuscator.visitProgramClass(programClass);

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any()))
        .thenReturn(new LibraryClass(1, "This Class Name", "Super Class Name"));
    InstructionSequenceObfuscator instructionSequenceObfuscator = new InstructionSequenceObfuscator(
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()));
    ProgramClass programClass = new ProgramClass();

    // Act
    instructionSequenceObfuscator.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    when(programClassPool.getClass(Mockito.<String>any()))
        .thenReturn(new LibraryClass(1, "This Class Name", "Super Class Name"));
    InstructionSequenceObfuscator instructionSequenceObfuscator = new InstructionSequenceObfuscator(
        new KotlinIntrinsicsReplacementSequences(programClassPool, new ClassPool()));
    ProgramClass programClass = new ProgramClass();

    // Act
    instructionSequenceObfuscator.visitProgramMethod(programClass, new NamedMember("Member Name", "Descriptor"));

    // Assert
    verify(programClassPool, atLeast(1)).getClass(Mockito.<String>any());
  }

  /**
   * Method under test:
   * {@link InstructionSequenceObfuscator#InstructionSequenceObfuscator(ReplacementSequences)}
   */
  @Test
  void testNewInstructionSequenceObfuscator2() {
    // Arrange
    ReplacementSequences replacementSequences = mock(ReplacementSequences.class);
    when(replacementSequences.getConstants()).thenReturn(new Constant[]{new ClassConstant()});
    when(replacementSequences.getSequences()).thenReturn(new Instruction[][][]{});

    // Act
    new InstructionSequenceObfuscator(replacementSequences);

    // Assert
    verify(replacementSequences).getConstants();
    verify(replacementSequences).getSequences();
  }
}
