package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;

class FinalFieldAssignmentMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link FinalFieldAssignmentMarker#assignsFinalField(Method)}
   */
  @Test
  void testAssignsFinalField() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(FinalFieldAssignmentMarker.assignsFinalField(method));
  }
}
