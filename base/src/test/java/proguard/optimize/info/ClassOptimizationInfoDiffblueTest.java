package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ClassOptimizationInfoDiffblueTest {
  /**
   * Method under test: {@link ClassOptimizationInfo#containsConstructors()}
   */
  @Test
  void testContainsConstructors() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).containsConstructors());
    assertFalse((new ProgramClassOptimizationInfo()).containsConstructors());
  }

  /**
   * Method under test: {@link ClassOptimizationInfo#isInstanceofed()}
   */
  @Test
  void testIsInstanceofed() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).isInstanceofed());
    assertFalse((new ProgramClassOptimizationInfo()).isInstanceofed());
  }

  /**
   * Method under test: {@link ClassOptimizationInfo#isDotClassed()}
   */
  @Test
  void testIsDotClassed() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).isDotClassed());
    assertFalse((new ProgramClassOptimizationInfo()).isDotClassed());
  }

  /**
   * Method under test: {@link ClassOptimizationInfo#hasSideEffects()}
   */
  @Test
  void testHasSideEffects() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).hasSideEffects());
    assertFalse((new ProgramClassOptimizationInfo()).hasSideEffects());
  }

  /**
   * Method under test:
   * {@link ClassOptimizationInfo#containsPackageVisibleMembers()}
   */
  @Test
  void testContainsPackageVisibleMembers() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).containsPackageVisibleMembers());
    assertFalse((new ProgramClassOptimizationInfo()).containsPackageVisibleMembers());
  }

  /**
   * Method under test:
   * {@link ClassOptimizationInfo#invokesPackageVisibleMembers()}
   */
  @Test
  void testInvokesPackageVisibleMembers() {
    // Arrange, Act and Assert
    assertTrue((new ClassOptimizationInfo()).invokesPackageVisibleMembers());
    assertFalse((new ProgramClassOptimizationInfo()).invokesPackageVisibleMembers());
  }

  /**
   * Method under test: {@link ClassOptimizationInfo#mayBeMerged()}
   */
  @Test
  void testMayBeMerged() {
    // Arrange, Act and Assert
    assertFalse((new ClassOptimizationInfo()).mayBeMerged());
    assertTrue((new ProgramClassOptimizationInfo()).mayBeMerged());
  }

  /**
   * Method under test:
   * {@link ClassOptimizationInfo#setClassOptimizationInfo(Clazz)}
   */
  @Test
  void testSetClassOptimizationInfo() {
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
   * Method under test:
   * {@link ClassOptimizationInfo#getClassOptimizationInfo(Clazz)}
   */
  @Test
  void testGetClassOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(ClassOptimizationInfo.getClassOptimizationInfo(new LibraryClass()));
  }

  /**
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
  void testGettersAndSetters() {
    // Arrange and Act
    ClassOptimizationInfo actualClassOptimizationInfo = new ClassOptimizationInfo();
    actualClassOptimizationInfo.setNoSideEffects();
    actualClassOptimizationInfo.getTargetClass();
    actualClassOptimizationInfo.getWrappedClass();
    boolean actualHasNoSideEffectsResult = actualClassOptimizationInfo.hasNoSideEffects();
    boolean actualIsCaughtResult = actualClassOptimizationInfo.isCaught();
    boolean actualIsEscapingResult = actualClassOptimizationInfo.isEscaping();
    boolean actualIsInstantiatedResult = actualClassOptimizationInfo.isInstantiated();
    boolean actualIsKeptResult = actualClassOptimizationInfo.isKept();

    // Assert that nothing has changed
    assertFalse(actualClassOptimizationInfo.isSimpleEnum());
    assertTrue(actualHasNoSideEffectsResult);
    assertTrue(actualIsCaughtResult);
    assertTrue(actualIsEscapingResult);
    assertTrue(actualIsInstantiatedResult);
    assertTrue(actualIsKeptResult);
  }
}
