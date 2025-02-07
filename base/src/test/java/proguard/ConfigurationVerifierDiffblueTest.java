package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConfigurationVerifierDiffblueTest {
  /**
   * Test {@link ConfigurationVerifier#check()}.
   * <ul>
   *   <li>Given {@link ConfigurationVerifier#ConfigurationVerifier(Configuration)} with {@link Configuration}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConfigurationVerifier#check()}
   */
  @Test
  @DisplayName("Test check(); given ConfigurationVerifier(Configuration) with Configuration; then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.ConfigurationVerifier.check()"})
  void testCheck_givenConfigurationVerifierWithConfiguration_thenThrowIOException() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> (new ConfigurationVerifier(mock(Configuration.class))).check());
  }
}
