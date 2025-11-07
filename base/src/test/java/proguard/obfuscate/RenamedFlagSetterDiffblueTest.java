package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.testutils.cpa.NamedClass;
import proguard.util.SimpleProcessable;

class RenamedFlagSetterDiffblueTest {
  /**
   * Test {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();

    NamedClass programClass = new NamedClass("Processing Info");
    programClass.setProcessingInfo("Processing Info");

    // Act
    renamedFlagSetter.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Test {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then {@link NamedClass#NamedClass(String)} with {@code Member Name} AccessFlags is {@code 65536}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then NamedClass(String) with 'Member Name' AccessFlags is '65536'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenNamedClassWithMemberNameAccessFlagsIs65536() {
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
   * Test {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then {@link NamedClass#NamedClass(String)} with {@code Member Name} AccessFlags is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then NamedClass(String) with 'Member Name' AccessFlags is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenNamedClassWithMemberNameAccessFlagsIsZero() {
    // Arrange
    RenamedFlagSetter renamedFlagSetter = new RenamedFlagSetter();
    NamedClass programClass = new NamedClass("Member Name");

    // Act
    renamedFlagSetter.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Test {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test visitProgramMember(ProgramClass, ProgramMember); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_givenProcessingInfo() {
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
   * Test {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test visitProgramMember(ProgramClass, ProgramMember); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_givenString_thenCallsGetString() {
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
   * Test {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}.
   * <ul>
   *   <li>When {@link ProgramMember} {@link SimpleProcessable#getProcessingInfo()} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RenamedFlagSetter#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test visitProgramMember(ProgramClass, ProgramMember); when ProgramMember getProcessingInfo() return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RenamedFlagSetter.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_whenProgramMemberGetProcessingInfoReturnName() {
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
