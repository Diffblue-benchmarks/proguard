package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.visitor.MemberVisitor;

class ParameterEscapeMarkerDiffblueTest {
  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapeMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(new MethodOptimizationInfo());

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
  }

  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#modifiesAnything()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo modifiesAnything() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapeMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoModifiesAnythingReturnFalse() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(1L);

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#modifiesAnything()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo modifiesAnything() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapeMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoModifiesAnythingReturnTrue() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(true);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(1L);

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Test {@link ParameterEscapeMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>Then calls {@link Member#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'Name'; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapeMarker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenName_thenCallsAccept() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    Method referencedMethod = mock(Method.class);
    doNothing().when(referencedMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(referencedMethod.getAccessFlags()).thenReturn(1);
    when(referencedMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(referencedMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert
    verify(referencedMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(referencedMethod).getAccessFlags();
    verify(referencedMethod).getName(isA(Clazz.class));
    verify(methodOptimizationInfo).modifiesAnything();
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterEscaping(Method, int)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#isParameterEscaping(Method, int)}
   */
  @Test
  @DisplayName("Test isParameterEscaping(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.isParameterEscaping(proguard.classfile.Method, int)"})
  void testIsParameterEscaping_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterEscaping(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getEscapingParameters(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#getEscapingParameters(Method)}
   */
  @Test
  @DisplayName("Test getEscapingParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "long proguard.optimize.info.ParameterEscapeMarker.getEscapingParameters(proguard.classfile.Method)"})
  void testGetEscapingParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getEscapingParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterReturned(Method, int)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#isParameterReturned(Method, int)}
   */
  @Test
  @DisplayName("Test isParameterReturned(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.isParameterReturned(proguard.classfile.Method, int)"})
  void testIsParameterReturned_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterReturned(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getReturnedParameters(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#getReturnedParameters(Method)}
   */
  @Test
  @DisplayName("Test getReturnedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "long proguard.optimize.info.ParameterEscapeMarker.getReturnedParameters(proguard.classfile.Method)"})
  void testGetReturnedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getReturnedParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#returnsNewInstances(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#returnsNewInstances(Method)}
   */
  @Test
  @DisplayName("Test returnsNewInstances(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.returnsNewInstances(proguard.classfile.Method)"})
  void testReturnsNewInstances_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsNewInstances(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#returnsExternalValues(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#returnsExternalValues(Method)}
   */
  @Test
  @DisplayName("Test returnsExternalValues(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.returnsExternalValues(proguard.classfile.Method)"})
  void testReturnsExternalValues_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsExternalValues(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterModified(Method, int)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#isParameterModified(Method, int)}
   */
  @Test
  @DisplayName("Test isParameterModified(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.isParameterModified(proguard.classfile.Method, int)"})
  void testIsParameterModified_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterModified(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getModifiedParameters(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#getModifiedParameters(Method)}
   */
  @Test
  @DisplayName("Test getModifiedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "long proguard.optimize.info.ParameterEscapeMarker.getModifiedParameters(proguard.classfile.Method)"})
  void testGetModifiedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getModifiedParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#modifiesAnything(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapeMarker#modifiesAnything(Method)}
   */
  @Test
  @DisplayName("Test modifiesAnything(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapeMarker.modifiesAnything(proguard.classfile.Method)"})
  void testModifiesAnything_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.modifiesAnything(method));
  }
}
