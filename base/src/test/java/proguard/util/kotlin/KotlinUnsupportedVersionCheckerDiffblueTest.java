package proguard.util.kotlin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class KotlinUnsupportedVersionCheckerDiffblueTest {
  /**
   * Method under test: {@link KotlinUnsupportedVersionChecker#execute(AppView)}
   */
  @Test
  void testExecute() throws Exception {
    // Arrange
    KotlinUnsupportedVersionChecker kotlinUnsupportedVersionChecker = new KotlinUnsupportedVersionChecker();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    kotlinUnsupportedVersionChecker.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinUnsupportedVersionChecker#execute(AppView)}
   */
  @Test
  void testExecute2() throws Exception {
    // Arrange
    KotlinUnsupportedVersionChecker kotlinUnsupportedVersionChecker = new KotlinUnsupportedVersionChecker();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    kotlinUnsupportedVersionChecker.execute(new AppView(programClassPool, libraryClassPool));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinUnsupportedVersionChecker#execute(AppView)}
   */
  @Test
  void testExecute3() throws Exception {
    // Arrange
    KotlinUnsupportedVersionChecker kotlinUnsupportedVersionChecker = new KotlinUnsupportedVersionChecker();
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("Name", new LibraryClass());

    // Act
    kotlinUnsupportedVersionChecker.execute(new AppView(programClassPool, libraryClassPool));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link KotlinUnsupportedVersionChecker}
   */
  @Test
  void testNewKotlinUnsupportedVersionChecker() {
    // Arrange, Act and Assert
    assertEquals("proguard.util.kotlin.KotlinUnsupportedVersionChecker",
        (new KotlinUnsupportedVersionChecker()).getName());
  }
}
