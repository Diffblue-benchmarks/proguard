package proguard.gui.splash;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.awt.Graphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CompositeSpriteDiffblueTest {
  /**
   * Method under test: {@link CompositeSprite#paint(Graphics, long)}
   */
  @Test
  void testPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new CompositeSprite(new Sprite[]{sprite})).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(10L));
  }
}
