package proguard.shrink;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.resources.file.ResourceFilePool;

class UsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)}
   */
  @Test
  void testMark() {
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
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)}
   */
  @Test
  void testMark2() {
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
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)}
   */
  @Test
  void testMark3() {
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
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker)}
   */
  @Test
  void testMark4() {
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
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker)}
   */
  @Test
  void testMark5() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, simpleUsageMarker, new ClassUsageMarker());

    // Assert
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }

  /**
   * Method under test:
   * {@link UsageMarker#mark(ClassPool, ClassPool, ResourceFilePool, SimpleUsageMarker, ClassUsageMarker)}
   */
  @Test
  void testMark6() {
    // Arrange
    UsageMarker usageMarker = new UsageMarker(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act
    usageMarker.mark(programClassPool, libraryClassPool, resourceFilePool, simpleUsageMarker, new ClassUsageMarker());

    // Assert
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
    verify(programClassPool, atLeast(1)).classesAccept(Mockito.<ClassVisitor>any());
  }
}
