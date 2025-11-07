package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class PackageVisibleMemberContainingClassMarkerDiffblueTest {
  /**
   * Test {@link PackageVisibleMemberContainingClassMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ProgramClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberContainingClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then LibraryClass() ProcessingInfo ProgramClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PackageVisibleMemberContainingClassMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_thenLibraryClassProcessingInfoProgramClassOptimizationInfo() {
    // Arrange
    PackageVisibleMemberContainingClassMarker packageVisibleMemberContainingClassMarker = new PackageVisibleMemberContainingClassMarker();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act
    packageVisibleMemberContainingClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).containsPackageVisibleMembers());
  }

  /**
   * Test {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}
   */
  @Test
  @DisplayName("Test containsPackageVisibleMembers(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(Clazz)"})
  void testContainsPackageVisibleMembers_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(clazz));
  }

  /**
   * Test {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}
   */
  @Test
  @DisplayName("Test containsPackageVisibleMembers(Clazz); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(Clazz)"})
  void testContainsPackageVisibleMembers_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(clazz));
  }
}
