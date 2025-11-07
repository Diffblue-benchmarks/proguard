package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassMemberPair;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.optimize.ReverseDependencyStore.InfluencedMethodTraveller;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramMethodOptimizationInfo;
import proguard.util.MultiValueMap;

class ReverseDependencyStoreDiffblueTest {
  /**
   * Test InfluencedMethodTraveller {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test InfluencedMethodTraveller visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InfluencedMethodTraveller.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testInfluencedMethodTravellerVisitProgramMethod_givenMethodOptimizationInfo() {
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

  /**
   * Test InfluencedMethodTraveller {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MultiValueMap#get(Object)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test InfluencedMethodTraveller visitProgramMethod(ProgramClass, ProgramMethod); then calls get(Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InfluencedMethodTraveller.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testInfluencedMethodTravellerVisitProgramMethod_thenCallsGet() {
    // Arrange
    MultiValueMap<Method, ClassMemberPair> calledBy = mock(MultiValueMap.class);
    doNothing().when(calledBy).put(Mockito.<Method>any(), Mockito.<ClassMemberPair>any());
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    LibraryClass clazz = new LibraryClass();
    calledBy.put(libraryMethod, new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")));
    MultiValueMap<ProgramMethodOptimizationInfo, Method> methodsByProgramMethodOptimizationInfo = mock(
        MultiValueMap.class);
    when(methodsByProgramMethodOptimizationInfo.get(Mockito.<ProgramMethodOptimizationInfo>any()))
        .thenReturn(new HashSet<>());
    doNothing().when(methodsByProgramMethodOptimizationInfo)
        .putAll(Mockito.<Set<ProgramMethodOptimizationInfo>>any(), Mockito.<Method>any());
    HashSet<ProgramMethodOptimizationInfo> key = new HashSet<>();
    methodsByProgramMethodOptimizationInfo.putAll(key, new LibraryMethod(1, "Name", "Descriptor"));
    ReverseDependencyStore reverseDependencyStore = new ReverseDependencyStore(calledBy,
        methodsByProgramMethodOptimizationInfo);

    InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    influencedMethodTraveller.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodsByProgramMethodOptimizationInfo).get(isA(ProgramMethodOptimizationInfo.class));
    verify(calledBy).put(isA(Method.class), isA(ClassMemberPair.class));
    verify(methodsByProgramMethodOptimizationInfo).putAll(isA(Set.class), isA(Method.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test InfluencedMethodTraveller {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MultiValueMap#keySet()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test InfluencedMethodTraveller visitProgramMethod(ProgramClass, ProgramMethod); then calls keySet()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InfluencedMethodTraveller.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testInfluencedMethodTravellerVisitProgramMethod_thenCallsKeySet() {
    // Arrange
    MultiValueMap<Method, ClassMemberPair> calledBy = mock(MultiValueMap.class);
    when(calledBy.keySet()).thenReturn(new HashSet<>());
    doNothing().when(calledBy).put(Mockito.<Method>any(), Mockito.<ClassMemberPair>any());
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    LibraryClass clazz = new LibraryClass();
    calledBy.put(libraryMethod, new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")));

    HashSet<Method> methodSet = new HashSet<>();
    methodSet.add(new LibraryMethod(1, "Name", "Descriptor"));
    MultiValueMap<ProgramMethodOptimizationInfo, Method> methodsByProgramMethodOptimizationInfo = mock(
        MultiValueMap.class);
    when(methodsByProgramMethodOptimizationInfo.get(Mockito.<ProgramMethodOptimizationInfo>any()))
        .thenReturn(methodSet);
    doNothing().when(methodsByProgramMethodOptimizationInfo)
        .putAll(Mockito.<Set<ProgramMethodOptimizationInfo>>any(), Mockito.<Method>any());
    HashSet<ProgramMethodOptimizationInfo> key = new HashSet<>();
    methodsByProgramMethodOptimizationInfo.putAll(key, new LibraryMethod(1, "Name", "Descriptor"));
    ReverseDependencyStore reverseDependencyStore = new ReverseDependencyStore(calledBy,
        methodsByProgramMethodOptimizationInfo);

    InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    influencedMethodTraveller.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodsByProgramMethodOptimizationInfo).get(isA(ProgramMethodOptimizationInfo.class));
    verify(calledBy).keySet();
    verify(calledBy).put(isA(Method.class), isA(ClassMemberPair.class));
    verify(methodsByProgramMethodOptimizationInfo).putAll(isA(Set.class), isA(Method.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
