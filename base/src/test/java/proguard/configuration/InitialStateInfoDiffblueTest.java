package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;

class InitialStateInfoDiffblueTest {
  /**
   * Method under test: {@link InitialStateInfo#size()}
   */
  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new InitialStateInfo(new ClassPool())).size());
  }

  /**
   * Method under test: {@link InitialStateInfo#classNames()}
   */
  @Test
  void testClassNames() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).classNames().isEmpty());
  }

  /**
   * Method under test: {@link InitialStateInfo#getSuperClassName(String)}
   */
  @Test
  void testGetSuperClassName() {
    // Arrange, Act and Assert
    assertNull((new InitialStateInfo(new ClassPool())).getSuperClassName("Class Name"));
  }

  /**
   * Method under test: {@link InitialStateInfo#getMethodHashMap(String)}
   */
  @Test
  void testGetMethodHashMap() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).getMethodHashMap("Class Name").isEmpty());
  }

  /**
   * Method under test: {@link InitialStateInfo#getFieldHashMap(String)}
   */
  @Test
  void testGetFieldHashMap() {
    // Arrange, Act and Assert
    assertTrue((new InitialStateInfo(new ClassPool())).getFieldHashMap("Class Name").isEmpty());
  }

  /**
   * Method under test: {@link InitialStateInfo#InitialStateInfo(ClassPool)}
   */
  @Test
  void testNewInitialStateInfo() {
    // Arrange and Act
    InitialStateInfo actualInitialStateInfo = new InitialStateInfo(new ClassPool());

    // Assert
    assertEquals(0, actualInitialStateInfo.size());
    assertTrue(actualInitialStateInfo.classNames().isEmpty());
  }
}
