package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ProgramClassOptimizationInfoDiffblueTest {
  /**
   * Test {@link ProgramClassOptimizationInfo#hasSideEffects()}.
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfo#hasSideEffects()}
   */
  @Test
  @DisplayName("Test hasSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ProgramClassOptimizationInfo.hasSideEffects()"})
  void testHasSideEffects() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).hasSideEffects());
  }

  /**
   * Test {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}.
   * <ul>
   *   <li>Then not {@link ProgramClassOptimizationInfo} (default constructor) containsConstructors.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}
   */
  @Test
  @DisplayName("Test merge(ClassOptimizationInfo); then not ProgramClassOptimizationInfo (default constructor) containsConstructors")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfo.merge(proguard.optimize.info.ClassOptimizationInfo)"})
  void testMerge_thenNotProgramClassOptimizationInfoContainsConstructors() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    ProgramClassOptimizationInfo other = new ProgramClassOptimizationInfo();

    // Act
    programClassOptimizationInfo.merge(other);

    // Assert that nothing has changed
    assertFalse(other.containsConstructors());
    assertFalse(other.containsPackageVisibleMembers());
    assertFalse(other.hasSideEffects());
    assertFalse(other.invokesPackageVisibleMembers());
    assertFalse(other.isCaught());
    assertFalse(other.isDotClassed());
    assertFalse(other.isEscaping());
    assertFalse(other.isInstanceofed());
    assertFalse(other.isInstantiated());
  }

  /**
   * Test {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}.
   * <ul>
   *   <li>Then {@link ProgramClassOptimizationInfo} (default constructor) containsConstructors.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}
   */
  @Test
  @DisplayName("Test merge(ClassOptimizationInfo); then ProgramClassOptimizationInfo (default constructor) containsConstructors")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfo.merge(proguard.optimize.info.ClassOptimizationInfo)"})
  void testMerge_thenProgramClassOptimizationInfoContainsConstructors() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();

    // Act
    programClassOptimizationInfo.merge(new ClassOptimizationInfo());

    // Assert
    assertTrue(programClassOptimizationInfo.containsConstructors());
    assertTrue(programClassOptimizationInfo.containsPackageVisibleMembers());
    assertTrue(programClassOptimizationInfo.hasSideEffects());
    assertTrue(programClassOptimizationInfo.invokesPackageVisibleMembers());
    assertTrue(programClassOptimizationInfo.isCaught());
    assertTrue(programClassOptimizationInfo.isDotClassed());
    assertTrue(programClassOptimizationInfo.isEscaping());
    assertTrue(programClassOptimizationInfo.isInstanceofed());
    assertTrue(programClassOptimizationInfo.isInstantiated());
  }

  /**
   * Test {@link ProgramClassOptimizationInfo#setProgramClassOptimizationInfo(Clazz)}.
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfo#setProgramClassOptimizationInfo(Clazz)}
   */
  @Test
  @DisplayName("Test setProgramClassOptimizationInfo(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setProgramClassOptimizationInfo(proguard.classfile.Clazz)"})
  void testSetProgramClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramClassOptimizationInfo.setProgramClassOptimizationInfo(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getWrappedClass());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsConstructors());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).invokesPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isCaught());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isEscaping());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstanceofed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isSimpleEnum());
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).mayBeMerged());
  }

  /**
   * Test {@link ProgramClassOptimizationInfo#getProgramClassOptimizationInfo(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfo#getProgramClassOptimizationInfo(Clazz)}
   */
  @Test
  @DisplayName("Test getProgramClassOptimizationInfo(Clazz); when LibraryClass(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.optimize.info.ProgramClassOptimizationInfo proguard.optimize.info.ProgramClassOptimizationInfo.getProgramClassOptimizationInfo(proguard.classfile.Clazz)"})
  void testGetProgramClassOptimizationInfo_whenLibraryClass_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ProgramClassOptimizationInfo.getProgramClassOptimizationInfo(new LibraryClass()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ProgramClassOptimizationInfo}
   *   <li>{@link ProgramClassOptimizationInfo#setSimpleEnum(boolean)}
   *   <li>{@link ProgramClassOptimizationInfo#setTargetClass(Clazz)}
   *   <li>{@link ProgramClassOptimizationInfo#setWrappedClass(Clazz)}
   *   <li>{@link ProgramClassOptimizationInfo#setCaught()}
   *   <li>{@link ProgramClassOptimizationInfo#setContainsConstructors()}
   *   <li>{@link ProgramClassOptimizationInfo#setContainsPackageVisibleMembers()}
   *   <li>{@link ProgramClassOptimizationInfo#setDotClassed()}
   *   <li>{@link ProgramClassOptimizationInfo#setEscaping()}
   *   <li>{@link ProgramClassOptimizationInfo#setInstanceofed()}
   *   <li>{@link ProgramClassOptimizationInfo#setInstantiated()}
   *   <li>{@link ProgramClassOptimizationInfo#setInvokesPackageVisibleMembers()}
   *   <li>{@link ProgramClassOptimizationInfo#setMayNotBeMerged()}
   *   <li>{@link ProgramClassOptimizationInfo#setSideEffects()}
   *   <li>{@link ProgramClassOptimizationInfo#containsConstructors()}
   *   <li>{@link ProgramClassOptimizationInfo#containsPackageVisibleMembers()}
   *   <li>{@link ProgramClassOptimizationInfo#getTargetClass()}
   *   <li>{@link ProgramClassOptimizationInfo#getWrappedClass()}
   *   <li>{@link ProgramClassOptimizationInfo#invokesPackageVisibleMembers()}
   *   <li>{@link ProgramClassOptimizationInfo#isCaught()}
   *   <li>{@link ProgramClassOptimizationInfo#isDotClassed()}
   *   <li>{@link ProgramClassOptimizationInfo#isEscaping()}
   *   <li>{@link ProgramClassOptimizationInfo#isInstanceofed()}
   *   <li>{@link ProgramClassOptimizationInfo#isInstantiated()}
   *   <li>{@link ProgramClassOptimizationInfo#isKept()}
   *   <li>{@link ProgramClassOptimizationInfo#isSimpleEnum()}
   *   <li>{@link ProgramClassOptimizationInfo#mayBeMerged()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.ProgramClassOptimizationInfo.<init>()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.containsConstructors()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.containsPackageVisibleMembers()",
      "proguard.classfile.Clazz proguard.optimize.info.ProgramClassOptimizationInfo.getTargetClass()",
      "proguard.classfile.Clazz proguard.optimize.info.ProgramClassOptimizationInfo.getWrappedClass()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.invokesPackageVisibleMembers()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isCaught()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isDotClassed()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isEscaping()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isInstanceofed()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isInstantiated()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isKept()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.isSimpleEnum()",
      "boolean proguard.optimize.info.ProgramClassOptimizationInfo.mayBeMerged()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setCaught()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setContainsConstructors()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setContainsPackageVisibleMembers()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setDotClassed()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setEscaping()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setInstanceofed()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setInstantiated()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setInvokesPackageVisibleMembers()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setMayNotBeMerged()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setSideEffects()",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setSimpleEnum(boolean)",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setTargetClass(proguard.classfile.Clazz)",
      "void proguard.optimize.info.ProgramClassOptimizationInfo.setWrappedClass(proguard.classfile.Clazz)"})
  void testGettersAndSetters() {
    // Arrange and Act
    ProgramClassOptimizationInfo actualProgramClassOptimizationInfo = new ProgramClassOptimizationInfo();
    actualProgramClassOptimizationInfo.setSimpleEnum(true);
    LibraryClass targetClass = new LibraryClass();
    actualProgramClassOptimizationInfo.setTargetClass(targetClass);
    LibraryClass wrappedClass = new LibraryClass();
    actualProgramClassOptimizationInfo.setWrappedClass(wrappedClass);
    actualProgramClassOptimizationInfo.setCaught();
    actualProgramClassOptimizationInfo.setContainsConstructors();
    actualProgramClassOptimizationInfo.setContainsPackageVisibleMembers();
    actualProgramClassOptimizationInfo.setDotClassed();
    actualProgramClassOptimizationInfo.setEscaping();
    actualProgramClassOptimizationInfo.setInstanceofed();
    actualProgramClassOptimizationInfo.setInstantiated();
    actualProgramClassOptimizationInfo.setInvokesPackageVisibleMembers();
    actualProgramClassOptimizationInfo.setMayNotBeMerged();
    actualProgramClassOptimizationInfo.setSideEffects();
    boolean actualContainsConstructorsResult = actualProgramClassOptimizationInfo.containsConstructors();
    boolean actualContainsPackageVisibleMembersResult = actualProgramClassOptimizationInfo
        .containsPackageVisibleMembers();
    Clazz actualTargetClass = actualProgramClassOptimizationInfo.getTargetClass();
    Clazz actualWrappedClass = actualProgramClassOptimizationInfo.getWrappedClass();
    boolean actualInvokesPackageVisibleMembersResult = actualProgramClassOptimizationInfo
        .invokesPackageVisibleMembers();
    boolean actualIsCaughtResult = actualProgramClassOptimizationInfo.isCaught();
    boolean actualIsDotClassedResult = actualProgramClassOptimizationInfo.isDotClassed();
    boolean actualIsEscapingResult = actualProgramClassOptimizationInfo.isEscaping();
    boolean actualIsInstanceofedResult = actualProgramClassOptimizationInfo.isInstanceofed();
    boolean actualIsInstantiatedResult = actualProgramClassOptimizationInfo.isInstantiated();
    boolean actualIsKeptResult = actualProgramClassOptimizationInfo.isKept();
    boolean actualIsSimpleEnumResult = actualProgramClassOptimizationInfo.isSimpleEnum();
    boolean actualMayBeMergedResult = actualProgramClassOptimizationInfo.mayBeMerged();

    // Assert
    assertFalse(actualProgramClassOptimizationInfo.hasNoSideEffects());
    assertFalse(actualIsKeptResult);
    assertFalse(actualMayBeMergedResult);
    assertTrue(actualContainsConstructorsResult);
    assertTrue(actualContainsPackageVisibleMembersResult);
    assertTrue(actualInvokesPackageVisibleMembersResult);
    assertTrue(actualIsCaughtResult);
    assertTrue(actualIsDotClassedResult);
    assertTrue(actualIsEscapingResult);
    assertTrue(actualIsInstanceofedResult);
    assertTrue(actualIsInstantiatedResult);
    assertTrue(actualIsSimpleEnumResult);
    assertSame(targetClass, actualTargetClass);
    assertSame(wrappedClass, actualWrappedClass);
  }
}
