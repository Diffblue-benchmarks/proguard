package proguard.gui;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

class SwingUtilDiffblueTest {
  /**
   * Method under test: {@link SwingUtil#invokeAndWait(Runnable)}
   */
  @Test
  void testInvokeAndWait() throws InterruptedException, InvocationTargetException {
    // Arrange
    Runnable runnable = mock(Runnable.class);
    doNothing().when(runnable).run();

    // Act
    SwingUtil.invokeAndWait(runnable);

    // Assert that nothing has changed
    verify(runnable).run();
  }
}
