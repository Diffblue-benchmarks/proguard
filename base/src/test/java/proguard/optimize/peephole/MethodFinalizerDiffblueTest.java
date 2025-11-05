package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.ClassRenamer;
import proguard.obfuscate.MemberNameCleaner;
import proguard.testutils.cpa.NamedMember;

class MethodFinalizerDiffblueTest {
  /**
   * Method under test:
   * {@link MethodFinalizer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    MethodFinalizer methodFinalizer = new MethodFinalizer();
    ProgramClass programClass = new ProgramClass();
    NamedMember programMethod = new NamedMember("Member Name", "Descriptor");

    // Act
    methodFinalizer.visitProgramMethod(programClass, programMethod);

    // Assert
    assertEquals(Short.SIZE, programMethod.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link MethodFinalizer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    MethodFinalizer methodFinalizer = new MethodFinalizer(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    NamedMember programMethod = new NamedMember("Member Name", "Descriptor");

    // Act
    methodFinalizer.visitProgramMethod(programClass, programMethod);

    // Assert
    assertEquals(Short.SIZE, programMethod.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link MethodFinalizer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    MethodFinalizer methodFinalizer = new MethodFinalizer(new ClassRenamer());
    ProgramClass programClass = new ProgramClass();
    NamedMember programMethod = new NamedMember("Member Name", "Descriptor");

    // Act
    methodFinalizer.visitProgramMethod(programClass, programMethod);

    // Assert
    assertEquals(Short.SIZE, programMethod.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link MethodFinalizer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    MethodFinalizer methodFinalizer = new MethodFinalizer(new MemberNameCleaner());
    ProgramClass programClass = new ProgramClass();
    NamedMember programMethod = new NamedMember("Member Name", "Descriptor");

    // Act
    methodFinalizer.visitProgramMethod(programClass, programMethod);

    // Assert
    assertEquals(Short.SIZE, programMethod.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link MethodFinalizer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod5() {
    // Arrange
    MethodFinalizer methodFinalizer = new MethodFinalizer();

    ProgramClass programClass = new ProgramClass();
    programClass.addSubClass(new LibraryClass(1050, "<init>", "<init>"));
    NamedMember programMethod = new NamedMember("Member Name", "Descriptor");

    // Act
    methodFinalizer.visitProgramMethod(programClass, programMethod);

    // Assert
    assertEquals(Short.SIZE, programMethod.getAccessFlags());
  }
}
