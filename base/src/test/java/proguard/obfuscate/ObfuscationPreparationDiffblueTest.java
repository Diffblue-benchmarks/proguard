package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.AppView;
import proguard.Configuration;

class ObfuscationPreparationDiffblueTest {
  /**
   * Test {@link ObfuscationPreparation#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link ObfuscationPreparation#ObfuscationPreparation(Configuration)} with {@link
   *       Configuration}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ObfuscationPreparation#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given ObfuscationPreparation(Configuration) with Configuration; then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObfuscationPreparation.execute(AppView)"})
  void testExecute_givenObfuscationPreparationWithConfiguration_thenThrowIOException()
      throws IOException {
    // Arrange
    ObfuscationPreparation obfuscationPreparation =
        new ObfuscationPreparation(mock(Configuration.class));

    // Act and Assert
    assertThrows(IOException.class, () -> obfuscationPreparation.execute(new AppView()));
  }
}
