package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.testutils.cpa.NamedClass;

class RenamedFlagSetterDiffblueTest {
  /**
   * Method under test: {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();
    NamedClass programClass = new NamedClass("Member Name");

    // Act
    renamedFlagSetter.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Method under test: {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();

    NamedClass programClass = new NamedClass("Member Name");
    programClass.setProcessingInfo("Processing Info");

    // Act
    renamedFlagSetter.visitProgramClass(programClass);

    // Assert
    assertEquals(65536, programClass.getAccessFlags());
  }

  /**
   * Method under test:
   * {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    renamedFlagSetter.visitProgramMember(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember2() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMember programMember = mock(ProgramMember.class);
    when(programMember.getProcessingInfo()).thenReturn("Processing Info");
    when(programMember.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    renamedFlagSetter.visitProgramMember(programClass, programMember);

    // Assert
    verify(programMember).getName(isA(Clazz.class));
    verify(programMember, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  void testVisitProgramMember3() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMember programMember = mock(ProgramMember.class);
    when(programMember.getProcessingInfo()).thenReturn("Name");
    when(programMember.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    renamedFlagSetter.visitProgramMember(programClass, programMember);

    // Assert
    verify(programMember).getName(isA(Clazz.class));
    verify(programMember, atLeast(1)).getProcessingInfo();
  }
}
