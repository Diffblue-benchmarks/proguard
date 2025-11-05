package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class ProgramClassOptimizationInfoDiffblueTest {
  /**
   * Method under test: {@link ProgramClassOptimizationInfo#hasSideEffects()}
   */
  @Test
  void testHasSideEffects() {
    // Arrange, Act and Assert
    assertFalse((new ProgramClassOptimizationInfo()).hasSideEffects());
  }

  /**
   * Method under test:
   * {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}
   */
  @Test
  void testMerge() {
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
   * Method under test:
   * {@link ProgramClassOptimizationInfo#merge(ClassOptimizationInfo)}
   */
  @Test
  void testMerge2() {
    // Arrange
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    ProgramClassOptimizationInfo other = new ProgramClassOptimizationInfo();

    // Act
    programClassOptimizationInfo.merge(other);

    // Assert
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
   * Method under test:
   * {@link ProgramClassOptimizationInfo#setProgramClassOptimizationInfo(Clazz)}
   */
  @Test
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
   * Method under test:
   * {@link ProgramClassOptimizationInfo#getProgramClassOptimizationInfo(Clazz)}
   */
  @Test
  void testGetProgramClassOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(ProgramClassOptimizationInfo.getProgramClassOptimizationInfo(new LibraryClass()));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of
   * {@link ProgramClassOptimizationInfo}
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

    // Assert that nothing has changed
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
