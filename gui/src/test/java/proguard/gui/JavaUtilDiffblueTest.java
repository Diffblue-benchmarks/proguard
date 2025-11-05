package proguard.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.Test;

class JavaUtilDiffblueTest {
  /**
   * Method under test: {@link JavaUtil#currentJavaVersion()}
   */
  @Test
  void testCurrentJavaVersion() {
    // Arrange, Act and Assert
    assertEquals(8, JavaUtil.currentJavaVersion());
  }

  /**
   * Method under test: {@link JavaUtil#getCurrentJavaHome()}
   */
  @Test
  void testGetCurrentJavaHome() {
    // Arrange and Act
    File actualCurrentJavaHome = JavaUtil.getCurrentJavaHome();

    // Assert
    assertEquals("amazon-corretto-8.462.08.1-linux-x64", actualCurrentJavaHome.getName());
    assertTrue(actualCurrentJavaHome.isAbsolute());
  }

  /**
   * Method under test: {@link JavaUtil#getRtJar()}
   */
  @Test
  void testGetRtJar() {
    // Arrange and Act
    File actualRtJar = JavaUtil.getRtJar();

    // Assert
    assertEquals("rt.jar", actualRtJar.getName());
    assertTrue(actualRtJar.isAbsolute());
  }

  /**
   * Method under test: {@link JavaUtil#getJmodBase()}
   */
  @Test
  void testGetJmodBase() {
    // Arrange and Act
    File actualJmodBase = JavaUtil.getJmodBase();

    // Assert
    assertEquals("java.base.jmod", actualJmodBase.getName());
    assertTrue(actualJmodBase.isAbsolute());
  }
}
