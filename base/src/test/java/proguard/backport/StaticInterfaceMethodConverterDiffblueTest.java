package proguard.backport;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.io.ExtraDataEntryNameMap;

class StaticInterfaceMethodConverterDiffblueTest {
  /**
   * Method under test:
   * {@link StaticInterfaceMethodConverter#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    ExtraDataEntryNameMap extraDataEntryNameMap = new ExtraDataEntryNameMap();
    ClassVisitor modifiedClassVisitor = mock(ClassVisitor.class);
    StaticInterfaceMethodConverter staticInterfaceMethodConverter = new StaticInterfaceMethodConverter(programClassPool,
        libraryClassPool, extraDataEntryNameMap, modifiedClassVisitor, new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());

    // Act
    staticInterfaceMethodConverter.visitProgramClass(programClass);

    // Assert
    verify(programClass).accept(isA(ClassVisitor.class));
  }
}
