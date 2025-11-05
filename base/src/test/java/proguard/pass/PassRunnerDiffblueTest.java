package proguard.pass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;

class PassRunnerDiffblueTest {
  /**
   * Method under test: {@link PassRunner#run(Pass, AppView)}
   */
  @Test
  void testRun() throws Exception {
    // Arrange
    PassRunner passRunner = new PassRunner();
    Pass pass = mock(Pass.class);
    doNothing().when(pass).execute(Mockito.<AppView>any());

    // Act
    passRunner.run(pass, new AppView());

    // Assert
    verify(pass).execute(isA(AppView.class));
  }

  /**
   * Method under test: {@link PassRunner#run(Pass, AppView)}
   */
  @Test
  void testRun2() throws Exception {
    // Arrange
    PassRunner passRunner = new PassRunner();
    Pass pass = mock(Pass.class);
    doThrow(new Exception("Pass {} completed in {}")).when(pass).execute(Mockito.<AppView>any());

    // Act and Assert
    assertThrows(Exception.class, () -> passRunner.run(pass, new AppView()));
    verify(pass).execute(isA(AppView.class));
  }
}
