package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class ClassFinalizerDiffblueTest {
  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));
    ProgramClass programClass = new ProgramClass(1552, 3, new Constant[]{new ClassConstant()}, 1552, 1552, 1552);

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(1552, programClass.getAccessFlags());
  }

  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassFinalizer#ClassFinalizer()}.</li>
   *   <li>Then {@link ProgramClass#ProgramClass()} AccessFlags is {@link Short#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassFinalizer(); then ProgramClass() AccessFlags is SIZE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassFinalizer_thenProgramClassAccessFlagsIsSize() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer();
    ProgramClass programClass = new ProgramClass();

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert
    assertEquals(Short.SIZE, programClass.getAccessFlags());
  }

  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenClassOptimizationInfo() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.setProcessingInfo(new ClassOptimizationInfo());
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then {@link ProgramClass#ProgramClass()} AccessFlags is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given LibraryClass(); then ProgramClass() AccessFlags is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenLibraryClass_thenProgramClassAccessFlagsIsZero() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenProgramClassOptimizationInfo() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.setProcessingInfo(new ProgramClassOptimizationInfo());
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Test {@link ClassFinalizer#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitProgramClass(ProgramClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ClassFinalizer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsVisitProgramClass() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    doNothing().when(extraClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    ClassFinalizer classFinalizer = new ClassFinalizer(extraClassVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert
    verify(extraClassVisitor).visitProgramClass(isA(ProgramClass.class));
    assertEquals(Short.SIZE, programClass.getAccessFlags());
  }
}
