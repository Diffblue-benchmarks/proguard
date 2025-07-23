package proguard.normalize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;

class StringNormalizerDiffblueTest {
  /**
   * Test {@link StringNormalizer#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link StringNormalizer#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringNormalizer.execute(AppView)"})
  void testExecute_givenLibraryClassAcceptDoesNothing_thenCallsAccept() throws Exception {
    // Arrange
    StringNormalizer stringNormalizer = new StringNormalizer();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);

    // Act
    stringNormalizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link StringNormalizer#execute(AppView)}.
   *
   * <ul>
   *   <li>When {@link ClassPool} {@link ClassPool#accept(ClassPoolVisitor)} does nothing.
   *   <li>Then calls {@link ClassPool#accept(ClassPoolVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link StringNormalizer#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); when ClassPool accept(ClassPoolVisitor) does nothing; then calls accept(ClassPoolVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringNormalizer.execute(AppView)"})
  void testExecute_whenClassPoolAcceptDoesNothing_thenCallsAccept() throws Exception {
    // Arrange
    StringNormalizer stringNormalizer = new StringNormalizer();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).accept(Mockito.<ClassPoolVisitor>any());

    // Act
    stringNormalizer.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).accept(isA(ClassPoolVisitor.class));
  }

  /**
   * Test new {@link StringNormalizer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link StringNormalizer}
   */
  @Test
  @DisplayName("Test new StringNormalizer (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringNormalizer.<init>()"})
  void testNewStringNormalizer() {
    // Arrange, Act and Assert
    assertEquals("proguard.normalize.StringNormalizer", new StringNormalizer().getName());
  }
}
