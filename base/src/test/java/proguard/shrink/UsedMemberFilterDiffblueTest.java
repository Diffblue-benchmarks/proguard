package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
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
import proguard.util.Processable;

class UsedMemberFilterDiffblueTest {
  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(new SimpleUsageMarker());
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter.visitProgramField(programClass, new ProgramField());

    // Assert that nothing has changed
    verify(classUsageMarker).getUsageMarker();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(classUsageMarker, usedMemberFilter);
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(usedMemberFilter).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(new SimpleUsageMarker());
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert that nothing has changed
    verify(classUsageMarker).getUsageMarker();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(classUsageMarker, usedMemberFilter);
    ProgramClass programClass = new ProgramClass();

    // Act
    usedMemberFilter2.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(usedMemberFilter).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#UsedMemberFilter(ClassUsageMarker, MemberVisitor)}
   */
  @Test
  void testNewUsedMemberFilter() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(new SimpleUsageMarker());

    // Act
    new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());

    // Assert
    verify(classUsageMarker).getUsageMarker();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(new SimpleUsageMarker());
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter.visitLibraryField(libraryClass, new LibraryField(1, "Name", "Descriptor"));

    // Assert that nothing has changed
    verify(classUsageMarker).getUsageMarker();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter.visitLibraryField(libraryClass, new LibraryField(1, "Name", "Descriptor"));

    // Assert
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitLibraryField(Mockito.<LibraryClass>any(), Mockito.<LibraryField>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(classUsageMarker, usedMemberFilter);
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryField(libraryClass, new LibraryField(1, "Name", "Descriptor"));

    // Assert
    verify(usedMemberFilter).visitLibraryField(isA(LibraryClass.class), isA(LibraryField.class));
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField4() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    usedMemberFilter2.visitLibraryField(libraryClass, libraryField);

    // Assert
    verify(libraryField).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryField).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(new SimpleUsageMarker());
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert that nothing has changed
    verify(classUsageMarker).getUsageMarker();
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    UsedMemberFilter usedMemberFilter = new UsedMemberFilter(classUsageMarker, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = mock(ShortestUsageMarker.class);
    when(shortestUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.getUsageMarker()).thenReturn(shortestUsageMarker);
    MemberVisitor usedMemberFilter = mock(MemberVisitor.class);
    doNothing().when(usedMemberFilter).visitLibraryMethod(Mockito.<LibraryClass>any(), Mockito.<LibraryMethod>any());
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(classUsageMarker, usedMemberFilter);
    LibraryClass libraryClass = new LibraryClass();

    // Act
    usedMemberFilter2.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(usedMemberFilter).visitLibraryMethod(isA(LibraryClass.class), isA(LibraryMethod.class));
    verify(classUsageMarker).getUsageMarker();
    verify(shortestUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link UsedMemberFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod4() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();
    KotlinAnnotationCounter usedMemberFilter = new KotlinAnnotationCounter();
    UsedMemberFilter usedMemberFilter2 = new UsedMemberFilter(usageMarker, usedMemberFilter,
        new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    usedMemberFilter2.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod).getProcessingInfo();
  }
}
