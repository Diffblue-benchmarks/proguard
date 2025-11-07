package proguard.pass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;

class PassRunnerDiffblueTest {
  /**
   * Test {@link PassRunner#run(Pass, AppView)}.
   * <ul>
   *   <li>Given {@link Exception#Exception(String)} with {@code Pass {} completed in {}}.</li>
   *   <li>Then throw {@link Exception}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PassRunner#run(Pass, AppView)}
   */
  @Test
  @DisplayName("Test run(Pass, AppView); given Exception(String) with 'Pass {} completed in {}'; then throw Exception")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PassRunner.run(Pass, AppView)"})
  void testRun_givenExceptionWithPassCompletedIn_thenThrowException() throws Exception {
    // Arrange
    PassRunner passRunner = new PassRunner();
    Pass pass = mock(Pass.class);
    doThrow(new Exception("Pass {} completed in {}")).when(pass).execute(Mockito.<AppView>any());

    // Act and Assert
    assertThrows(Exception.class, () -> passRunner.run(pass, new AppView()));
    verify(pass).execute(isA(AppView.class));
  }

  /**
   * Test {@link PassRunner#run(Pass, AppView)}.
   * <ul>
   *   <li>When {@link Pass} {@link Pass#execute(AppView)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link PassRunner#run(Pass, AppView)}
   */
  @Test
  @DisplayName("Test run(Pass, AppView); when Pass execute(AppView) does nothing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void PassRunner.run(Pass, AppView)"})
  void testRun_whenPassExecuteDoesNothing() throws Exception {
    // Arrange
    PassRunner passRunner = new PassRunner();
    Pass pass = mock(Pass.class);
    doNothing().when(pass).execute(Mockito.<AppView>any());

    // Act
    passRunner.run(pass, new AppView());

    // Assert
    verify(pass).execute(isA(AppView.class));
  }
}
