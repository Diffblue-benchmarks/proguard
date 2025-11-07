package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.util.Processable;

class AttributeUsageMarkerDiffblueTest {
  /**
   * Test {@link AttributeUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AttributeUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean AttributeUsageMarker.isUsed(Processable)"})
  void testIsUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AttributeUsageMarker.isUsed(new LibraryClass()));
  }
}
