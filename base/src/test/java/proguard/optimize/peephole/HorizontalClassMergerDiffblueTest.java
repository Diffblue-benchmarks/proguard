package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class HorizontalClassMergerDiffblueTest {
  /**
   * Method under test: {@link HorizontalClassMerger#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool() {
    // Arrange
    HorizontalClassMerger horizontalClassMerger = new HorizontalClassMerger(true, true, new HashSet<>());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.classes()).thenReturn(new ArrayList<>());

    // Act
    horizontalClassMerger.visitClassPool(classPool);

    // Assert
    verify(classPool).classes();
  }

  /**
   * Method under test: {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}
   */
  @Test
  void testIsCandidateForMerging() {
    // Arrange
    HorizontalClassMerger horizontalClassMerger = new HorizontalClassMerger(true, true, new HashSet<>());

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertFalse(horizontalClassMerger.isCandidateForMerging(clazz));
  }

  /**
   * Method under test: {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}
   */
  @Test
  void testIsCandidateForMerging2() {
    // Arrange
    HorizontalClassMerger horizontalClassMerger = new HorizontalClassMerger(true, true, new HashSet<>());

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(horizontalClassMerger.isCandidateForMerging(clazz));
  }
}
