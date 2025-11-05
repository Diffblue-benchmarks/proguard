package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class PackageVisibleMemberContainingClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link PackageVisibleMemberContainingClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    PackageVisibleMemberContainingClassMarker packageVisibleMemberContainingClassMarker = new PackageVisibleMemberContainingClassMarker();

    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act
    packageVisibleMemberContainingClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).containsPackageVisibleMembers());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}
   */
  @Test
  void testContainsPackageVisibleMembers() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(clazz));
  }

  /**
   * Method under test:
   * {@link PackageVisibleMemberContainingClassMarker#containsPackageVisibleMembers(Clazz)}
   */
  @Test
  void testContainsPackageVisibleMembers2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(PackageVisibleMemberContainingClassMarker.containsPackageVisibleMembers(clazz));
  }
}
