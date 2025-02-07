package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ClassOptimizationInfoDiffblueTest {
  /**
   * Test {@link ClassOptimizationInfo#containsConstructors()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#containsConstructors()}
   */
  @Test
  @DisplayName("Test containsConstructors(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.containsConstructors()"})
  void testContainsConstructors_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).containsConstructors());
  }

  /**
   * Test {@link ClassOptimizationInfo#containsConstructors()}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#containsConstructors()}
   */
  @Test
  @DisplayName("Test containsConstructors(); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.containsConstructors()"})
  void testContainsConstructors_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).containsConstructors());
  }

  /**
   * Test {@link ClassOptimizationInfo#isInstanceofed()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#isInstanceofed()}
   */
  @Test
  @DisplayName("Test isInstanceofed(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.isInstanceofed()"})
  void testIsInstanceofed_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).isInstanceofed());
  }

  /**
   * Test {@link ClassOptimizationInfo#isInstanceofed()}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#isInstanceofed()}
   */
  @Test
  @DisplayName("Test isInstanceofed(); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.isInstanceofed()"})
  void testIsInstanceofed_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).isInstanceofed());
  }

  /**
   * Test {@link ClassOptimizationInfo#isDotClassed()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#isDotClassed()}
   */
  @Test
  @DisplayName("Test isDotClassed(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.isDotClassed()"})
  void testIsDotClassed_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).isDotClassed());
  }

  /**
   * Test {@link ClassOptimizationInfo#isDotClassed()}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#isDotClassed()}
   */
  @Test
  @DisplayName("Test isDotClassed(); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.isDotClassed()"})
  void testIsDotClassed_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).isDotClassed());
  }

  /**
   * Test {@link ClassOptimizationInfo#hasSideEffects()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#hasSideEffects()}
   */
  @Test
  @DisplayName("Test hasSideEffects(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.hasSideEffects()"})
  void testHasSideEffects_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).hasSideEffects());
  }

  /**
   * Test {@link ClassOptimizationInfo#hasSideEffects()}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#hasSideEffects()}
   */
  @Test
  @DisplayName("Test hasSideEffects(); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.hasSideEffects()"})
  void testHasSideEffects_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).hasSideEffects());
  }

  /**
   * Test {@link ClassOptimizationInfo#containsPackageVisibleMembers()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#containsPackageVisibleMembers()}
   */
  @Test
  @DisplayName("Test containsPackageVisibleMembers(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.containsPackageVisibleMembers()"})
  void testContainsPackageVisibleMembers_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).containsPackageVisibleMembers());
  }

  /**
   * Test {@link ClassOptimizationInfo#containsPackageVisibleMembers()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#containsPackageVisibleMembers()}
   */
  @Test
  @DisplayName("Test containsPackageVisibleMembers(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.containsPackageVisibleMembers()"})
  void testContainsPackageVisibleMembers_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).containsPackageVisibleMembers());
  }

  /**
   * Test {@link ClassOptimizationInfo#invokesPackageVisibleMembers()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#invokesPackageVisibleMembers()}
   */
  @Test
  @DisplayName("Test invokesPackageVisibleMembers(); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.invokesPackageVisibleMembers()"})
  void testInvokesPackageVisibleMembers_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).invokesPackageVisibleMembers());
  }

  /**
   * Test {@link ClassOptimizationInfo#invokesPackageVisibleMembers()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#invokesPackageVisibleMembers()}
   */
  @Test
  @DisplayName("Test invokesPackageVisibleMembers(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.invokesPackageVisibleMembers()"})
  void testInvokesPackageVisibleMembers_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).invokesPackageVisibleMembers());
  }

  /**
   * Test {@link ClassOptimizationInfo#mayBeMerged()}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#mayBeMerged()}
   */
  @Test
  @DisplayName("Test mayBeMerged(); given ClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.mayBeMerged()"})
  void testMayBeMerged_givenClassOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ClassOptimizationInfo()).mayBeMerged());
  }

  /**
   * Test {@link ClassOptimizationInfo#mayBeMerged()}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#mayBeMerged()}
   */
  @Test
  @DisplayName("Test mayBeMerged(); given ProgramClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.ClassOptimizationInfo.mayBeMerged()"})
  void testMayBeMerged_givenProgramClassOptimizationInfo_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new ProgramClassOptimizationInfo()).mayBeMerged());
  }

  /**
   * Test {@link ClassOptimizationInfo#setClassOptimizationInfo(Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#setClassOptimizationInfo(Clazz)}
   */
  @Test
  @DisplayName("Test setClassOptimizationInfo(Clazz); then LibraryClass() ProcessingInfo ClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ClassOptimizationInfo.setClassOptimizationInfo(proguard.classfile.Clazz)"})
  void testSetClassOptimizationInfo_thenLibraryClassProcessingInfoClassOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ClassOptimizationInfo.setClassOptimizationInfo(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ClassOptimizationInfo);
    assertNull(((ClassOptimizationInfo) processingInfo).getTargetClass());
    assertNull(((ClassOptimizationInfo) processingInfo).getWrappedClass());
    assertFalse(((ClassOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((ClassOptimizationInfo) processingInfo).isSimpleEnum());
    assertTrue(((ClassOptimizationInfo) processingInfo).hasSideEffects());
    assertTrue(((ClassOptimizationInfo) processingInfo).isCaught());
    assertTrue(((ClassOptimizationInfo) processingInfo).isDotClassed());
    assertTrue(((ClassOptimizationInfo) processingInfo).isEscaping());
    assertTrue(((ClassOptimizationInfo) processingInfo).isInstanceofed());
    assertTrue(((ClassOptimizationInfo) processingInfo).isInstantiated());
    assertTrue(((ClassOptimizationInfo) processingInfo).isKept());
  }

  /**
   * Test {@link ClassOptimizationInfo#getClassOptimizationInfo(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassOptimizationInfo#getClassOptimizationInfo(Clazz)}
   */
  @Test
  @DisplayName("Test getClassOptimizationInfo(Clazz); when LibraryClass(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.optimize.info.ClassOptimizationInfo proguard.optimize.info.ClassOptimizationInfo.getClassOptimizationInfo(proguard.classfile.Clazz)"})
  void testGetClassOptimizationInfo_whenLibraryClass_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ClassOptimizationInfo.getClassOptimizationInfo(new LibraryClass()));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link ClassOptimizationInfo}
   *   <li>{@link ClassOptimizationInfo#setNoSideEffects()}
   *   <li>{@link ClassOptimizationInfo#getTargetClass()}
   *   <li>{@link ClassOptimizationInfo#getWrappedClass()}
   *   <li>{@link ClassOptimizationInfo#hasNoSideEffects()}
   *   <li>{@link ClassOptimizationInfo#isCaught()}
   *   <li>{@link ClassOptimizationInfo#isEscaping()}
   *   <li>{@link ClassOptimizationInfo#isInstantiated()}
   *   <li>{@link ClassOptimizationInfo#isKept()}
   *   <li>{@link ClassOptimizationInfo#isSimpleEnum()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.ClassOptimizationInfo.<init>()",
      "proguard.classfile.Clazz proguard.optimize.info.ClassOptimizationInfo.getTargetClass()",
      "proguard.classfile.Clazz proguard.optimize.info.ClassOptimizationInfo.getWrappedClass()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.hasNoSideEffects()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.isCaught()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.isEscaping()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.isInstantiated()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.isKept()",
      "boolean proguard.optimize.info.ClassOptimizationInfo.isSimpleEnum()",
      "void proguard.optimize.info.ClassOptimizationInfo.setNoSideEffects()"})
  void testGettersAndSetters() {
    // Arrange and Act
    ClassOptimizationInfo actualClassOptimizationInfo = new ClassOptimizationInfo();
    actualClassOptimizationInfo.setNoSideEffects();
    Clazz actualTargetClass = actualClassOptimizationInfo.getTargetClass();
    Clazz actualWrappedClass = actualClassOptimizationInfo.getWrappedClass();
    boolean actualHasNoSideEffectsResult = actualClassOptimizationInfo.hasNoSideEffects();
    boolean actualIsCaughtResult = actualClassOptimizationInfo.isCaught();
    boolean actualIsEscapingResult = actualClassOptimizationInfo.isEscaping();
    boolean actualIsInstantiatedResult = actualClassOptimizationInfo.isInstantiated();
    boolean actualIsKeptResult = actualClassOptimizationInfo.isKept();

    // Assert
    assertNull(actualTargetClass);
    assertNull(actualWrappedClass);
    assertFalse(actualClassOptimizationInfo.isSimpleEnum());
    assertTrue(actualHasNoSideEffectsResult);
    assertTrue(actualIsCaughtResult);
    assertTrue(actualIsEscapingResult);
    assertTrue(actualIsInstantiatedResult);
    assertTrue(actualIsKeptResult);
  }
}
