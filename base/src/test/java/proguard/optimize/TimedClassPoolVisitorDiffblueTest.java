package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;

class TimedClassPoolVisitorDiffblueTest {
  /**
   * Method under test:
   * {@link TimedClassPoolVisitor#TimedClassPoolVisitor(String, ClassVisitor)}
   */
  @Test
  void testNewTimedClassPoolVisitor() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());

    // Act
    TimedClassPoolVisitor actualTimedClassPoolVisitor = new TimedClassPoolVisitor("Not all who wander are lost",
        classVisitor);
    actualTimedClassPoolVisitor
        .visitClassPool(new ClassPool(new LibraryClass(1, "This Class Name", "Super Class Name")));

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Method under test: {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool() {
    // Arrange
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);
    doNothing().when(classPoolVisitor).visitClassPool(Mockito.<ClassPool>any());
    TimedClassPoolVisitor timedClassPoolVisitor = new TimedClassPoolVisitor("Not all who wander are lost",
        classPoolVisitor);

    // Act
    timedClassPoolVisitor.visitClassPool(new ClassPool());

    // Assert
    verify(classPoolVisitor).visitClassPool(isA(ClassPool.class));
  }

  /**
   * Method under test: {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}
   */
  @Test
  void testVisitClassPool2() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    TimedClassPoolVisitor timedClassPoolVisitor = new TimedClassPoolVisitor("Not all who wander are lost",
        classVisitor);

    ClassPool classPool = new ClassPool();
    classPool.addClass("Name", new LibraryClass());

    // Act
    timedClassPoolVisitor.visitClassPool(classPool);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
  }
}
