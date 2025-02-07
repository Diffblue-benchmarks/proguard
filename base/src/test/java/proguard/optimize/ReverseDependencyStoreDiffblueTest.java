package proguard.optimize;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassMemberPair;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.optimize.ReverseDependencyStore.InfluencedMethodTraveller;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.util.MultiValueMap;
import proguard.util.SimpleProcessable;

class ReverseDependencyStoreDiffblueTest {
  /**
   * Test InfluencedMethodTraveller {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test InfluencedMethodTraveller visitProgramMethod(ProgramClass, ProgramMethod); then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.ReverseDependencyStore$InfluencedMethodTraveller.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testInfluencedMethodTravellerVisitProgramMethod_thenCallsGetProcessingInfo() {
    // Arrange
    MultiValueMap<Method, ClassMemberPair> calledBy = new MultiValueMap<>();
    ReverseDependencyStore reverseDependencyStore = new ReverseDependencyStore(calledBy, new MultiValueMap<>());

    InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    influencedMethodTraveller.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
