package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;

class SimpleEnumClassCheckerDiffblueTest {
  /**
   * Test {@link SimpleEnumClassChecker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumClassChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassOptimizationInfo (default constructor); then calls fieldsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SimpleEnumClassChecker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfo_thenCallsFieldsAccept() {
    // Arrange
    SimpleEnumClassChecker simpleEnumClassChecker = new SimpleEnumClassChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    when(programClass.getName()).thenReturn("Name");
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    when(programClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryMethod(1, "Name", "Descriptor"));

    // Act
    simpleEnumClassChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).findMethod(eq("<init>"), eq("(Ljava/lang/String;I)V"));
    verify(programClass).getName();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).getProcessingInfo();
  }
}
