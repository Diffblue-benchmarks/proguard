package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;

class WrapperClassMarkerDiffblueTest {
  /**
   * Test {@link WrapperClassMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls fieldsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.WrapperClassMarker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenCallsFieldsAccept() {
    // Arrange
    WrapperClassMarker wrapperClassMarker = new WrapperClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());

    // Act
    wrapperClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link WrapperClassMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.WrapperClassMarker.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenEmptyString() {
    // Arrange
    WrapperClassMarker wrapperClassMarker = new WrapperClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("");

    // Act
    wrapperClassMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link WrapperClassMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.WrapperClassMarker.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenString_thenCallsGetString() {
    // Arrange
    WrapperClassMarker wrapperClassMarker = new WrapperClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    wrapperClassMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link WrapperClassMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.WrapperClassMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenString_thenCallsGetString() {
    // Arrange
    WrapperClassMarker wrapperClassMarker = new WrapperClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    wrapperClassMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link WrapperClassMarker#getWrappedClass(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#getWrappedClass(Clazz)}
   */
  @Test
  @DisplayName("Test getWrappedClass(Clazz); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.Clazz proguard.optimize.info.WrapperClassMarker.getWrappedClass(proguard.classfile.Clazz)"})
  void testGetWrappedClass_givenClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertNull(WrapperClassMarker.getWrappedClass(clazz));
  }

  /**
   * Test {@link WrapperClassMarker#getWrappedClass(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link WrapperClassMarker#getWrappedClass(Clazz)}
   */
  @Test
  @DisplayName("Test getWrappedClass(Clazz); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.Clazz proguard.optimize.info.WrapperClassMarker.getWrappedClass(proguard.classfile.Clazz)"})
  void testGetWrappedClass_givenProgramClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertNull(WrapperClassMarker.getWrappedClass(clazz));
  }
}
