package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.io.ExtraDataEntryNameMap;
import proguard.resources.file.ResourceFilePool;

class AppViewDiffblueTest {
  /**
   * Test {@link AppView#AppView(ClassPool, ClassPool, ResourceFilePool, ExtraDataEntryNameMap)}.
   * <p>
   * Method under test: {@link AppView#AppView(ClassPool, ClassPool, ResourceFilePool, ExtraDataEntryNameMap)}
   */
  @Test
  @DisplayName("Test new AppView(ClassPool, ClassPool, ResourceFilePool, ExtraDataEntryNameMap)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.AppView.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool, proguard.resources.file.ResourceFilePool, proguard.io.ExtraDataEntryNameMap)"})
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
   * Test {@link AppView#AppView()}.
   * <p>
   * Method under test: {@link AppView#AppView()}
   */
  @Test
  @DisplayName("Test new AppView()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.AppView.<init>()"})
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
   * Test {@link AppView#AppView(ClassPool, ClassPool)}.
   * <p>
   * Method under test: {@link AppView#AppView(ClassPool, ClassPool)}
   */
  @Test
  @DisplayName("Test new AppView(ClassPool, ClassPool)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.AppView.<init>(proguard.classfile.ClassPool, proguard.classfile.ClassPool)"})
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
}
