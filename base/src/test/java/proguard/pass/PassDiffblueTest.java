package proguard.pass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.KotlinMetadataAdapter;

class PassDiffblueTest {
  /**
   * Method under test: {@link Pass#getName()}
   */
  @Test
  void testGetName() {
    // Arrange, Act and Assert
    assertEquals("proguard.KotlinMetadataAdapter", (new KotlinMetadataAdapter()).getName());
  }

  /**
   * Method under test: {@link Pass#getName()}
   */
  @Test
  void testGetName2() {
    // Arrange
    Pass pass = mock(Pass.class);
    when(pass.getName()).thenReturn("Name");

    // Act
    pass.getName();

    // Assert
    verify(pass).getName();
  }
}
