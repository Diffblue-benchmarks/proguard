package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class HorizontalClassMergerDiffblueTest {
  /**
   * Test {@link HorizontalClassMerger#visitClassPool(ClassPool)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link ClassPool#classes()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HorizontalClassMerger#visitClassPool(ClassPool)}
   */
  @Test
  @DisplayName("Test visitClassPool(ClassPool); given ArrayList(); then calls classes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void HorizontalClassMerger.visitClassPool(ClassPool)"})
  void testVisitClassPool_givenArrayList_thenCallsClasses() {
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
   * Test {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}
   */
  @Test
  @DisplayName("Test isCandidateForMerging(Clazz); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean HorizontalClassMerger.isCandidateForMerging(Clazz)"})
  void testIsCandidateForMerging_givenClassOptimizationInfo() {
    // Arrange
    HorizontalClassMerger horizontalClassMerger = new HorizontalClassMerger(true, true, new HashSet<>());

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertFalse(horizontalClassMerger.isCandidateForMerging(clazz));
  }

  /**
   * Test {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link HorizontalClassMerger#isCandidateForMerging(Clazz)}
   */
  @Test
  @DisplayName("Test isCandidateForMerging(Clazz); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean HorizontalClassMerger.isCandidateForMerging(Clazz)"})
  void testIsCandidateForMerging_givenProgramClassOptimizationInfo() {
    // Arrange
    HorizontalClassMerger horizontalClassMerger = new HorizontalClassMerger(true, true, new HashSet<>());

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(horizontalClassMerger.isCandidateForMerging(clazz));
  }
}
