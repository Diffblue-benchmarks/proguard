package proguard.classfile.pass;

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
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class PrimitiveArrayConstantIntroducerDiffblueTest {
  /**
   * Test {@link PrimitiveArrayConstantIntroducer#execute(AppView)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PrimitiveArrayConstantIntroducer#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.classfile.pass.PrimitiveArrayConstantIntroducer.execute(proguard.AppView)"})
  void testExecute_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    PrimitiveArrayConstantIntroducer primitiveArrayConstantIntroducer = new PrimitiveArrayConstantIntroducer();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);

    // Act
    primitiveArrayConstantIntroducer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link PrimitiveArrayConstantIntroducer#execute(AppView)}.
   * <ul>
   *   <li>When {@link ClassPool} {@link ClassPool#classesAccept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link ClassPool#classesAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PrimitiveArrayConstantIntroducer#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); when ClassPool classesAccept(ClassVisitor) does nothing; then calls classesAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.classfile.pass.PrimitiveArrayConstantIntroducer.execute(proguard.AppView)"})
  void testExecute_whenClassPoolClassesAcceptDoesNothing_thenCallsClassesAccept() {
    // Arrange
    PrimitiveArrayConstantIntroducer primitiveArrayConstantIntroducer = new PrimitiveArrayConstantIntroducer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    primitiveArrayConstantIntroducer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test new {@link PrimitiveArrayConstantIntroducer} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PrimitiveArrayConstantIntroducer}
   */
  @Test
  @DisplayName("Test new PrimitiveArrayConstantIntroducer (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.classfile.pass.PrimitiveArrayConstantIntroducer.<init>()"})
  void testNewPrimitiveArrayConstantIntroducer() {
    // Arrange, Act and Assert
    assertEquals("proguard.classfile.pass.PrimitiveArrayConstantIntroducer",
        (new PrimitiveArrayConstantIntroducer()).getName());
  }
}
