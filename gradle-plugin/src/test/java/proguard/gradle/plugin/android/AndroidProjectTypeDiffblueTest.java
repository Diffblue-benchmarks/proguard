package proguard.gradle.plugin.android;

import static org.junit.jupiter.api.Assertions.assertEquals;
import kotlin.enums.EnumEntries;
import org.junit.jupiter.api.Test;

class AndroidProjectTypeDiffblueTest {
  /**
   * Method under test: {@link AndroidProjectType#getEntries()}
   */
  @Test
  void testGetEntries() {
    // Arrange and Act
    EnumEntries<AndroidProjectType> actualEntries = AndroidProjectType.valueOf("ANDROID_APPLICATION").getEntries();

    // Assert
    assertEquals(2, actualEntries.size());
    assertEquals(AndroidProjectType.ANDROID_APPLICATION, actualEntries.get(0));
    assertEquals(AndroidProjectType.ANDROID_LIBRARY, actualEntries.get(1));
  }
}
