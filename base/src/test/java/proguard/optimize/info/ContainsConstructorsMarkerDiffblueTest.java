package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class ContainsConstructorsMarkerDiffblueTest {
  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ContainsConstructorsMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenProgramClassOptimizationInfo() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ContainsConstructorsMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramClassGetStringReturnString() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramClassOptimizationInfo#setContainsConstructors()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setContainsConstructors()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ContainsConstructorsMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsSetContainsConstructors() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setContainsConstructors();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClassOptimizationInfo).setContainsConstructors();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ContainsConstructorsMarker#containsConstructors(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  @DisplayName("Test containsConstructors(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ContainsConstructorsMarker.containsConstructors(proguard.classfile.Clazz)"})
  void testContainsConstructors_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(ContainsConstructorsMarker.containsConstructors(clazz));
  }

  /**
   * Test {@link ContainsConstructorsMarker#containsConstructors(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  @DisplayName("Test containsConstructors(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ContainsConstructorsMarker.containsConstructors(proguard.classfile.Clazz)"})
  void testContainsConstructors_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(ContainsConstructorsMarker.containsConstructors(clazz));
  }
}
