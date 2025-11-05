package proguard.optimize;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.MemberNameCleaner;
import proguard.optimize.info.FieldOptimizationInfo;
import proguard.optimize.info.MethodOptimizationInfo;
import proguard.optimize.info.ProgramFieldOptimizationInfo;

class KeptMemberFilterDiffblueTest {
  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doNothing().when(programField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(memberVisitor).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
    verify(programField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    LibraryClass clazz = new LibraryClass();
    when(programField.getProcessingInfo())
        .thenReturn(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act
    keptMemberFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(methodOptimizationInfo).isKept();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField2() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    FieldOptimizationInfo fieldOptimizationInfo = new FieldOptimizationInfo();
    libraryField.setProcessingInfo(fieldOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof FieldOptimizationInfo);
    assertSame(fieldOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField3() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);

    libraryField.setProcessingInfo(programFieldOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertSame(programFieldOptimizationInfo, processingInfo);
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField4() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new MemberNameCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(new FieldOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod3() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(memberVisitor);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(true);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(memberVisitor).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(methodOptimizationInfo).isKept();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link KeptMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod4() {
    // Arrange
    KeptMemberFilter keptMemberFilter = new KeptMemberFilter(mock(MemberVisitor.class));
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.isKept()).thenReturn(false);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    keptMemberFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).isKept();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
