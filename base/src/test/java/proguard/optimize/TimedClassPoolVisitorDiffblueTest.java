package proguard.optimize;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;

class TimedClassPoolVisitorDiffblueTest {
  /**
   * Test {@link TimedClassPoolVisitor#TimedClassPoolVisitor(String, ClassVisitor)}.
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimedClassPoolVisitor#TimedClassPoolVisitor(String, ClassVisitor)}
   */
  @Test
  @DisplayName("Test new TimedClassPoolVisitor(String, ClassVisitor); then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.TimedClassPoolVisitor.<init>(java.lang.String, proguard.classfile.visitor.ClassVisitor)"})
  void testNewTimedClassPoolVisitor_thenCallsVisitLibraryClass() {
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
   * Test {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}.
   * <ul>
   *   <li>Given {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}
   */
  @Test
  @DisplayName("Test visitClassPool(ClassPool); given LibraryClass(); then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.TimedClassPoolVisitor.visitClassPool(proguard.classfile.ClassPool)"})
  void testVisitClassPool_givenLibraryClass_thenCallsVisitLibraryClass() {
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

  /**
   * Test {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}.
   * <ul>
   *   <li>Then calls {@link ClassPoolVisitor#visitClassPool(ClassPool)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimedClassPoolVisitor#visitClassPool(ClassPool)}
   */
  @Test
  @DisplayName("Test visitClassPool(ClassPool); then calls visitClassPool(ClassPool)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.TimedClassPoolVisitor.visitClassPool(proguard.classfile.ClassPool)"})
  void testVisitClassPool_thenCallsVisitClassPool() {
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
}
