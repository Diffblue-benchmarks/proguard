package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.optimize.info.MethodOptimizationInfo;

class BootstrapMethodArgumentShrinkerDiffblueTest {
  /**
   * Test {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   * <p>
   * Method under test: {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.BootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodInfo)"})
  void testVisitBootstrapMethodInfo() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(clazz,
        new BootstrapMethodInfo(1, 3, new int[]{-1, 1, -1, 1}));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BootstrapMethodArgumentShrinker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.BootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodInfo)"})
  void testVisitBootstrapMethodInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link BootstrapMethodArgumentShrinker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BootstrapMethodArgumentShrinker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.BootstrapMethodArgumentShrinker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant_thenCallsConstantPoolEntryAccept() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    bootstrapMethodArgumentShrinker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.BootstrapMethodArgumentShrinker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    bootstrapMethodArgumentShrinker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#getUsedParameters()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BootstrapMethodArgumentShrinker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls getUsedParameters()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.BootstrapMethodArgumentShrinker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsGetUsedParameters() {
    // Arrange
    BootstrapMethodArgumentShrinker bootstrapMethodArgumentShrinker = new BootstrapMethodArgumentShrinker();
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.getUsedParameters()).thenReturn(1L);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    bootstrapMethodArgumentShrinker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).getUsedParameters();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
