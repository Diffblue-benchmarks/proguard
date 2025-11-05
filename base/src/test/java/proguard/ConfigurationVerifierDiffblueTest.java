package proguard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ConfigurationVerifierDiffblueTest {
  /**
   * Method under test: {@link ConfigurationVerifier#check()}
   */
  @Test
  void testCheck() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class, () -> (new ConfigurationVerifier(mock(Configuration.class))).check());
  }
}
