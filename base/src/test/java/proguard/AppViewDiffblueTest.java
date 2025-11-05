package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.visitor.ClassVisitor;
import proguard.io.ClassPath;
import proguard.io.ClassPathEntry;
import proguard.io.DataEntryReader;
import proguard.io.ExtraDataEntryNameMap;
import proguard.io.util.IOUtil;
import proguard.resources.file.ResourceFilePool;

class AppViewDiffblueTest {
  /**
   * Method under test:
   * {@link AppView#AppView(ClassPool, ClassPool, ResourceFilePool, ExtraDataEntryNameMap)}
   */
  @Test
  void testNewAppView() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();

    // Act
    AppView actualAppView = new AppView(programClassPool, libraryClassPool, resourceFilePool,
        new ExtraDataEntryNameMap());

    // Assert
    ExtraDataEntryNameMap extraDataEntryNameMap = actualAppView.extraDataEntryNameMap;
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertEquals(0, actualAppView.libraryClassPool.size());
    assertEquals(0, actualAppView.programClassPool.size());
    assertEquals(0, actualAppView.resourceFilePool.size());
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link AppView#AppView()}
   */
  @Test
  void testNewAppView2() {
    // Arrange and Act
    AppView actualAppView = new AppView();

    // Assert
    ExtraDataEntryNameMap extraDataEntryNameMap = actualAppView.extraDataEntryNameMap;
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertNull(actualAppView.initialStateInfo);
    assertEquals(0, actualAppView.libraryClassPool.size());
    assertEquals(0, actualAppView.programClassPool.size());
    assertEquals(0, actualAppView.resourceFilePool.size());
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link AppView#AppView(ClassPool, ClassPool)}
   */
  @Test
  void testNewAppView3() {
    // Arrange
    ClassPool programClassPool = new ClassPool();

    // Act
    AppView actualAppView = new AppView(programClassPool, new ClassPool());

    // Assert
    ExtraDataEntryNameMap extraDataEntryNameMap = actualAppView.extraDataEntryNameMap;
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertNull(actualAppView.initialStateInfo);
    assertEquals(0, actualAppView.libraryClassPool.size());
    assertEquals(0, actualAppView.programClassPool.size());
    assertEquals(0, actualAppView.resourceFilePool.size());
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }

  /**
   * Method under test: {@link AppView#AppView(ClassPool, ClassPool)}
   */
  @Test
  void testNewAppView4() throws IOException {
    // Arrange
    BiFunction<DataEntryReader, ClassVisitor, DataEntryReader> extraDataEntryReader = mock(BiFunction.class);
    when(extraDataEntryReader.apply(Mockito.<DataEntryReader>any(), Mockito.<ClassVisitor>any()))
        .thenReturn(mock(DataEntryReader.class));
    ClassPool programClassPool = IOUtil.read(new ClassPath(new ClassPathEntry(Configuration.STD_OUT, true)),
        "Class Name Filter", true, true, true, true, true, true, extraDataEntryReader);

    // Act
    AppView actualAppView = new AppView(programClassPool, new ClassPool());

    // Assert
    verify(extraDataEntryReader).apply(isA(DataEntryReader.class), isA(ClassVisitor.class));
    ExtraDataEntryNameMap extraDataEntryNameMap = actualAppView.extraDataEntryNameMap;
    assertNull(extraDataEntryNameMap.getDefaultExtraDataEntryNames());
    assertNull(actualAppView.initialStateInfo);
    assertEquals(0, actualAppView.libraryClassPool.size());
    assertEquals(0, actualAppView.programClassPool.size());
    assertEquals(0, actualAppView.resourceFilePool.size());
    assertTrue(extraDataEntryNameMap.getAllExtraDataEntryNames().isEmpty());
    assertTrue(extraDataEntryNameMap.getKeyDataEntryNames().isEmpty());
  }
}
