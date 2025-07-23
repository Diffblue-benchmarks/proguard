package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.resources.file.ResourceFilePool;
import proguard.util.Processable;

class UsageMarkerDiffblueTest {
  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, new SimpleUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker2() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, new SimpleUsageMarker());

    // Assert
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker3() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("<init>", new LibraryClass(2, "<init>", "<init>"));
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, new SimpleUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker4() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("<init>", clazz);
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, new SimpleUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker5() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("library/gson", new LibraryClass(2, "<init>", "<init>"));
    libraryClassPool.addClass("<init>", clazz);
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    usageMarker.mark(
        programClassPool, libraryClassPool, resourceFilePool, new ShortestUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker6() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("library/gson", new LibraryClass(2, "<init>", "<init>"));
    libraryClassPool.addClass("<init>", clazz);
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = mock(SimpleUsageMarker.class);
    when(simpleUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, simpleUsageMarker);

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
    verify(clazz).accept(isA(ClassVisitor.class));
    verify(simpleUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)} with
   * {@code programClassPool}, {@code libraryClassPool}, {@code resourceFilePool}, {@code
   * simpleUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)"
  })
  void testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarker7() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("library/gson", new ProgramClass());
    libraryClassPool.addClass("<init>", clazz);
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = mock(SimpleUsageMarker.class);
    when(simpleUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, simpleUsageMarker);

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
    verify(clazz).accept(isA(ClassVisitor.class));
    verify(simpleUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker,
   * ClassUsageMarker)} with {@code programClassPool}, {@code libraryClassPool}, {@code
   * resourceFilePool}, {@code simpleUsageMarker}, {@code classUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker, ClassUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker', 'classUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker)"
  })
  void
      testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarkerClassUsageMarker() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act
    usageMarker.mark(
        programClassPool,
        libraryClassPool,
        resourceFilePool,
        simpleUsageMarker,
        new ClassUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker,
   * ClassUsageMarker)} with {@code programClassPool}, {@code libraryClassPool}, {@code
   * resourceFilePool}, {@code simpleUsageMarker}, {@code classUsageMarker}.
   *
   * <p>Method under test: {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool,
   * SimpleUsageMarker, ClassUsageMarker)}
   */
  @Test
  @DisplayName(
      "Test mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker) with 'programClassPool', 'libraryClassPool', 'resourceFilePool', 'simpleUsageMarker', 'classUsageMarker'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UsageMarker.mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker)"
  })
  void
      testMarkWithProgramClassPoolLibraryClassPoolResourceFilePoolSimpleUsageMarkerClassUsageMarker2() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act
    usageMarker.mark(
        programClassPool,
        libraryClassPool,
        resourceFilePool,
        simpleUsageMarker,
        new ClassUsageMarker());

    // Assert
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }
}
