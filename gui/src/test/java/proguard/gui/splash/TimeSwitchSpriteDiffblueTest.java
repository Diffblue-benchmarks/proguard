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

class TimeSwitchSpriteDiffblueTest {
  /**
   * Method under test:
   * {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}
   */
  @Test
  void testNewTimeSwitchSprite() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 10L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Method under test: {@link TimeSwitchSprite#paint(Graphics, long)}
   */
  @Test
  void testPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Method under test: {@link TimeSwitchSprite#paint(Graphics, long)}
   */
  @Test
  void testPaint2() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 10L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Method under test:
   * {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}
   */
  @Test
  void testNewTimeSwitchSprite2() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 0L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Method under test: {@link TimeSwitchSprite#TimeSwitchSprite(long, Sprite)}
   */
  @Test
  void testNewTimeSwitchSprite3() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }
}
