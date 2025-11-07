package proguard.gui.splash;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Graphics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TimeSwitchSpriteDiffblueTest {
  /**
   * Test {@link TimeSwitchSprite#TimeSwitchSprite(long, Sprite)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then calls {@link Sprite#paint(Graphics, long)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeSwitchSprite#TimeSwitchSprite(long, Sprite)}
   */
  @Test
  @DisplayName("Test new TimeSwitchSprite(long, Sprite); when one; then calls paint(Graphics, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TimeSwitchSprite.<init>(long, Sprite)"})
  void testNewTimeSwitchSprite_whenOne_thenCallsPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Test {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}.
   * <ul>
   *   <li>When ten.</li>
   *   <li>Then calls {@link Sprite#paint(Graphics, long)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}
   */
  @Test
  @DisplayName("Test new TimeSwitchSprite(long, long, Sprite); when ten; then calls paint(Graphics, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TimeSwitchSprite.<init>(long, long, Sprite)"})
  void testNewTimeSwitchSprite_whenTen_thenCallsPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 10L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Test {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then calls {@link Sprite#paint(Graphics, long)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)}
   */
  @Test
  @DisplayName("Test new TimeSwitchSprite(long, long, Sprite); when zero; then calls paint(Graphics, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TimeSwitchSprite.<init>(long, long, Sprite)"})
  void testNewTimeSwitchSprite_whenZero_thenCallsPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 0L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Test {@link TimeSwitchSprite#paint(Graphics, long)}.
   * <ul>
   *   <li>Given {@link TimeSwitchSprite#TimeSwitchSprite(long, long, Sprite)} with onTime is one and offTime is ten and {@link Sprite}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeSwitchSprite#paint(Graphics, long)}
   */
  @Test
  @DisplayName("Test paint(Graphics, long); given TimeSwitchSprite(long, long, Sprite) with onTime is one and offTime is ten and Sprite")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TimeSwitchSprite.paint(Graphics, long)"})
  void testPaint_givenTimeSwitchSpriteWithOnTimeIsOneAndOffTimeIsTenAndSprite() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, 10L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }

  /**
   * Test {@link TimeSwitchSprite#paint(Graphics, long)}.
   * <ul>
   *   <li>Given {@link TimeSwitchSprite#TimeSwitchSprite(long, Sprite)} with onTime is one and {@link Sprite}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link Sprite#paint(Graphics, long)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TimeSwitchSprite#paint(Graphics, long)}
   */
  @Test
  @DisplayName("Test paint(Graphics, long); given TimeSwitchSprite(long, Sprite) with onTime is one and Sprite; when 'null'; then calls paint(Graphics, long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TimeSwitchSprite.paint(Graphics, long)"})
  void testPaint_givenTimeSwitchSpriteWithOnTimeIsOneAndSprite_whenNull_thenCallsPaint() {
    // Arrange
    Sprite sprite = mock(Sprite.class);
    doNothing().when(sprite).paint(Mockito.<Graphics>any(), anyLong());

    // Act
    (new TimeSwitchSprite(1L, sprite)).paint(null, 10L);

    // Assert
    verify(sprite).paint(isNull(), eq(9L));
  }
}
