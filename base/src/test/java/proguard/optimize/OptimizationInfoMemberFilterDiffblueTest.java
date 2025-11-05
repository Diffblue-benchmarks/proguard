package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.MemberNameCleaner;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;
import proguard.optimize.info.ProgramMethodOptimizationInfo;

class OptimizationInfoMemberFilterDiffblueTest {
  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing().when(otherMemberVisitor).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(otherMemberVisitor).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new KotlinAnnotationCounter(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new MemberNameCleaner(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    optimizationInfoMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isNull());
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing().when(otherMemberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(otherMemberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    MemberVisitor otherMemberVisitor = mock(MemberVisitor.class);
    doNothing().when(otherMemberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new KotlinAnnotationCounter(), otherMemberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(otherMemberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new KotlinAnnotationCounter(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link OptimizationInfoMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    OptimizationInfoMemberFilter optimizationInfoMemberFilter = new OptimizationInfoMemberFilter(
        new MemberNameCleaner(), mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());
    when(programMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    optimizationInfoMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isNull());
  }
}
