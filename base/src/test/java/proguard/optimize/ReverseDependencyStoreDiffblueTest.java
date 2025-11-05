package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
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
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramMethodOptimizationInfo;
import proguard.util.MultiValueMap;

class ReverseDependencyStoreDiffblueTest {
  /**
   * Method under test:
   * {@link ReverseDependencyStore.InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testInfluencedMethodTravellerVisitProgramMethod() {
    // Arrange
    MultiValueMap<Method, ClassMemberPair> calledBy = new MultiValueMap<>();
    ReverseDependencyStore reverseDependencyStore = new ReverseDependencyStore(calledBy, new MultiValueMap<>());

    ReverseDependencyStore.InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
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
   * Method under test:
   * {@link ReverseDependencyStore.InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testInfluencedMethodTravellerVisitProgramMethod2() {
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

    ReverseDependencyStore.InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
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
   * Method under test:
   * {@link ReverseDependencyStore.InfluencedMethodTraveller#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testInfluencedMethodTravellerVisitProgramMethod3() {
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

    ReverseDependencyStore.InfluencedMethodTraveller influencedMethodTraveller = reverseDependencyStore.new InfluencedMethodTraveller(
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
