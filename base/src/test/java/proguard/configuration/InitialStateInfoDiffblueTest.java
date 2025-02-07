package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;

class InitialStateInfoDiffblueTest {
  /**
   * Test {@link InitialStateInfo#InitialStateInfo(ClassPool)}.
   * <ul>
   *   <li>When {@link ClassPool#ClassPool()}.</li>
   *   <li>Then return size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link InitialStateInfo#InitialStateInfo(ClassPool)}
   */
  @Test
  @DisplayName("Test new InitialStateInfo(ClassPool); when ClassPool(); then return size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.configuration.InitialStateInfo.<init>(proguard.classfile.ClassPool)"})
  void testNewInitialStateInfo_whenClassPool_thenReturnSizeIsZero() {
    // Arrange and Act
    InitialStateInfo actualInitialStateInfo = new InitialStateInfo(new ClassPool());

    // Assert
    assertEquals(0, actualInitialStateInfo.size());
    assertTrue(actualInitialStateInfo.classNames().isEmpty());
  }

  /**
   * Test {@link InitialStateInfo#size()}.
   * <p>
   * Method under test: {@link InitialStateInfo#size()}
   */
  @Test
  @DisplayName("Test size()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int proguard.configuration.InitialStateInfo.size()"})
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new InitialStateInfo(new ClassPool())).size());
  }

  /**
   * Test {@link InitialStateInfo#classNames()}.
   * <p>
   * Method under test: {@link InitialStateInfo#classNames()}
   */
  @Test
  @DisplayName("Test classNames()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.List proguard.configuration.InitialStateInfo.classNames()"})
  void testClassNames() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).classNames().isEmpty());
  }

  /**
   * Test {@link InitialStateInfo#getSuperClassName(String)}.
   * <p>
   * Method under test: {@link InitialStateInfo#getSuperClassName(String)}
   */
  @Test
  @DisplayName("Test getSuperClassName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.configuration.InitialStateInfo.getSuperClassName(java.lang.String)"})
  void testGetSuperClassName() {
    // Arrange, Act and Assert
    assertNull((new InitialStateInfo(new ClassPool())).getSuperClassName("Class Name"));
  }

  /**
   * Test {@link InitialStateInfo#getMethodHashMap(String)}.
   * <p>
   * Method under test: {@link InitialStateInfo#getMethodHashMap(String)}
   */
  @Test
  @DisplayName("Test getMethodHashMap(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Map proguard.configuration.InitialStateInfo.getMethodHashMap(java.lang.String)"})
  void testGetMethodHashMap() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).getMethodHashMap("Class Name").isEmpty());
  }

  /**
   * Test {@link InitialStateInfo#getFieldHashMap(String)}.
   * <p>
   * Method under test: {@link InitialStateInfo#getFieldHashMap(String)}
   */
  @Test
  @DisplayName("Test getFieldHashMap(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.Map proguard.configuration.InitialStateInfo.getFieldHashMap(java.lang.String)"})
  void testGetFieldHashMap() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).getFieldHashMap("Class Name").isEmpty());
  }
}
