package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.FlowLayout;
import java.awt.image.DirectColorModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import javax.swing.JComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SplashPanelDiffblueTest {
  /**
   * Test {@link SplashPanel#SplashPanel(Sprite, double)}.
   *
   * <p>Method under test: {@link SplashPanel#SplashPanel(Sprite, double)}
   */
  @Test
  @DisplayName("Test new SplashPanel(Sprite, double)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.<init>(Sprite, double)"})
  void testNewSplashPanel() {
    // Arrange and Act
    SplashPanel actualSplashPanel = new SplashPanel(mock(Sprite.class), 10.0d);

    // Assert
    assertTrue(actualSplashPanel.getLayout() instanceof FlowLayout);
    assertTrue(actualSplashPanel.getColorModel() instanceof DirectColorModel);
    assertEquals("PanelUI", actualSplashPanel.getUIClassID());
    assertNull(actualSplashPanel.getNextFocusableComponent());
    assertNull(actualSplashPanel.getFocusCycleRootAncestor());
    assertNull(actualSplashPanel.getParent());
    assertNull(actualSplashPanel.getTopLevelAncestor());
    assertNull(actualSplashPanel.getFocusTraversalPolicy());
    assertNull(actualSplashPanel.getGraphics());
    assertNull(actualSplashPanel.getGraphicsConfiguration());
    assertNull(actualSplashPanel.getDropTarget());
    assertNull(actualSplashPanel.getInputContext());
    assertNull(actualSplashPanel.getInputMethodRequests());
    assertNull(actualSplashPanel.getPeer());
    assertNull(actualSplashPanel.getName());
    assertNull(actualSplashPanel.getToolTipText());
    assertNull(actualSplashPanel.getInputVerifier());
    assertNull(actualSplashPanel.getComponentPopupMenu());
    assertNull(actualSplashPanel.getRootPane());
    assertNull(actualSplashPanel.getTransferHandler());
    assertNull(actualSplashPanel.getBorder());
    assertEquals(0, actualSplashPanel.getComponentCount());
    assertEquals(0, actualSplashPanel.getDebugGraphicsOptions());
    assertEquals(0, actualSplashPanel.getHeight());
    assertEquals(0, actualSplashPanel.getWidth());
    assertEquals(0, actualSplashPanel.getX());
    assertEquals(0, actualSplashPanel.getY());
    assertEquals(0, actualSplashPanel.getComponentListeners().length);
    assertEquals(0, actualSplashPanel.getFocusListeners().length);
    assertEquals(0, actualSplashPanel.getHierarchyBoundsListeners().length);
    assertEquals(0, actualSplashPanel.getHierarchyListeners().length);
    assertEquals(0, actualSplashPanel.getInputMethodListeners().length);
    assertEquals(0, actualSplashPanel.getKeyListeners().length);
    assertEquals(0, actualSplashPanel.getMouseMotionListeners().length);
    assertEquals(0, actualSplashPanel.getMouseWheelListeners().length);
    assertEquals(0, actualSplashPanel.getPropertyChangeListeners().length);
    assertEquals(0, actualSplashPanel.getComponents().length);
    assertEquals(0, actualSplashPanel.getContainerListeners().length);
    assertEquals(0, actualSplashPanel.getAncestorListeners().length);
    assertEquals(0, actualSplashPanel.getRegisteredKeyStrokes().length);
    assertEquals(0, actualSplashPanel.getVetoableChangeListeners().length);
    assertEquals(0.5f, actualSplashPanel.getAlignmentX());
    assertEquals(0.5f, actualSplashPanel.getAlignmentY());
    assertEquals(1, actualSplashPanel.getMouseListeners().length);
    assertEquals(BaselineResizeBehavior.OTHER, actualSplashPanel.getBaselineResizeBehavior());
    assertFalse(actualSplashPanel.getIgnoreRepaint());
    assertFalse(actualSplashPanel.hasFocus());
    assertFalse(actualSplashPanel.isCursorSet());
    assertFalse(actualSplashPanel.isDisplayable());
    assertFalse(actualSplashPanel.isFocusOwner());
    assertFalse(actualSplashPanel.isLightweight());
    assertFalse(actualSplashPanel.isMaximumSizeSet());
    assertFalse(actualSplashPanel.isMinimumSizeSet());
    assertFalse(actualSplashPanel.isPreferredSizeSet());
    assertFalse(actualSplashPanel.isShowing());
    assertFalse(actualSplashPanel.isValid());
    assertFalse(actualSplashPanel.isFocusCycleRoot());
    assertFalse(actualSplashPanel.isFocusTraversalPolicyProvider());
    assertFalse(actualSplashPanel.isFocusTraversalPolicySet());
    assertFalse(actualSplashPanel.getAutoscrolls());
    assertFalse(actualSplashPanel.getInheritsPopupMenu());
    assertFalse(actualSplashPanel.isManagingFocus());
    assertFalse(actualSplashPanel.isPaintingForPrint());
    assertFalse(actualSplashPanel.isPaintingTile());
    assertFalse(actualSplashPanel.isValidateRoot());
    assertTrue(actualSplashPanel.getFocusTraversalKeysEnabled());
    assertTrue(actualSplashPanel.isBackgroundSet());
    assertTrue(actualSplashPanel.isEnabled());
    assertTrue(actualSplashPanel.isFocusable());
    assertTrue(actualSplashPanel.isFontSet());
    assertTrue(actualSplashPanel.isForegroundSet());
    assertTrue(actualSplashPanel.isVisible());
    assertTrue(actualSplashPanel.getVerifyInputWhenFocusTarget());
    assertTrue(actualSplashPanel.isDoubleBuffered());
    assertTrue(actualSplashPanel.isOpaque());
    assertTrue(actualSplashPanel.isOptimizedDrawingEnabled());
    assertTrue(actualSplashPanel.isRequestFocusEnabled());
  }

  /**
   * Test {@link SplashPanel#SplashPanel(Sprite, double, long)}.
   *
   * <p>Method under test: {@link SplashPanel#SplashPanel(Sprite, double, long)}
   */
  @Test
  @DisplayName("Test new SplashPanel(Sprite, double, long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.<init>(Sprite, double, long)"})
  void testNewSplashPanel2() {
    // Arrange and Act
    SplashPanel actualSplashPanel = new SplashPanel(mock(Sprite.class), 10.0d, 1L);

    // Assert
    assertTrue(actualSplashPanel.getLayout() instanceof FlowLayout);
    assertTrue(actualSplashPanel.getColorModel() instanceof DirectColorModel);
    assertEquals("PanelUI", actualSplashPanel.getUIClassID());
    assertNull(actualSplashPanel.getNextFocusableComponent());
    assertNull(actualSplashPanel.getFocusCycleRootAncestor());
    assertNull(actualSplashPanel.getParent());
    assertNull(actualSplashPanel.getTopLevelAncestor());
    assertNull(actualSplashPanel.getFocusTraversalPolicy());
    assertNull(actualSplashPanel.getGraphics());
    assertNull(actualSplashPanel.getGraphicsConfiguration());
    assertNull(actualSplashPanel.getDropTarget());
    assertNull(actualSplashPanel.getInputContext());
    assertNull(actualSplashPanel.getInputMethodRequests());
    assertNull(actualSplashPanel.getPeer());
    assertNull(actualSplashPanel.getName());
    assertNull(actualSplashPanel.getToolTipText());
    assertNull(actualSplashPanel.getInputVerifier());
    assertNull(actualSplashPanel.getComponentPopupMenu());
    assertNull(actualSplashPanel.getRootPane());
    assertNull(actualSplashPanel.getTransferHandler());
    assertNull(actualSplashPanel.getBorder());
    assertEquals(0, actualSplashPanel.getComponentCount());
    assertEquals(0, actualSplashPanel.getDebugGraphicsOptions());
    assertEquals(0, actualSplashPanel.getHeight());
    assertEquals(0, actualSplashPanel.getWidth());
    assertEquals(0, actualSplashPanel.getX());
    assertEquals(0, actualSplashPanel.getY());
    assertEquals(0, actualSplashPanel.getComponentListeners().length);
    assertEquals(0, actualSplashPanel.getFocusListeners().length);
    assertEquals(0, actualSplashPanel.getHierarchyBoundsListeners().length);
    assertEquals(0, actualSplashPanel.getHierarchyListeners().length);
    assertEquals(0, actualSplashPanel.getInputMethodListeners().length);
    assertEquals(0, actualSplashPanel.getKeyListeners().length);
    assertEquals(0, actualSplashPanel.getMouseMotionListeners().length);
    assertEquals(0, actualSplashPanel.getMouseWheelListeners().length);
    assertEquals(0, actualSplashPanel.getPropertyChangeListeners().length);
    assertEquals(0, actualSplashPanel.getComponents().length);
    assertEquals(0, actualSplashPanel.getContainerListeners().length);
    assertEquals(0, actualSplashPanel.getAncestorListeners().length);
    assertEquals(0, actualSplashPanel.getRegisteredKeyStrokes().length);
    assertEquals(0, actualSplashPanel.getVetoableChangeListeners().length);
    assertEquals(0.5f, actualSplashPanel.getAlignmentX());
    assertEquals(0.5f, actualSplashPanel.getAlignmentY());
    assertEquals(1, actualSplashPanel.getMouseListeners().length);
    assertEquals(BaselineResizeBehavior.OTHER, actualSplashPanel.getBaselineResizeBehavior());
    assertFalse(actualSplashPanel.getIgnoreRepaint());
    assertFalse(actualSplashPanel.hasFocus());
    assertFalse(actualSplashPanel.isCursorSet());
    assertFalse(actualSplashPanel.isDisplayable());
    assertFalse(actualSplashPanel.isFocusOwner());
    assertFalse(actualSplashPanel.isLightweight());
    assertFalse(actualSplashPanel.isMaximumSizeSet());
    assertFalse(actualSplashPanel.isMinimumSizeSet());
    assertFalse(actualSplashPanel.isPreferredSizeSet());
    assertFalse(actualSplashPanel.isShowing());
    assertFalse(actualSplashPanel.isValid());
    assertFalse(actualSplashPanel.isFocusCycleRoot());
    assertFalse(actualSplashPanel.isFocusTraversalPolicyProvider());
    assertFalse(actualSplashPanel.isFocusTraversalPolicySet());
    assertFalse(actualSplashPanel.getAutoscrolls());
    assertFalse(actualSplashPanel.getInheritsPopupMenu());
    assertFalse(actualSplashPanel.isManagingFocus());
    assertFalse(actualSplashPanel.isPaintingForPrint());
    assertFalse(actualSplashPanel.isPaintingTile());
    assertFalse(actualSplashPanel.isValidateRoot());
    assertTrue(actualSplashPanel.getFocusTraversalKeysEnabled());
    assertTrue(actualSplashPanel.isBackgroundSet());
    assertTrue(actualSplashPanel.isEnabled());
    assertTrue(actualSplashPanel.isFocusable());
    assertTrue(actualSplashPanel.isFontSet());
    assertTrue(actualSplashPanel.isForegroundSet());
    assertTrue(actualSplashPanel.isVisible());
    assertTrue(actualSplashPanel.getVerifyInputWhenFocusTarget());
    assertTrue(actualSplashPanel.isDoubleBuffered());
    assertTrue(actualSplashPanel.isOpaque());
    assertTrue(actualSplashPanel.isOptimizedDrawingEnabled());
    assertTrue(actualSplashPanel.isRequestFocusEnabled());
  }

  /**
   * Test {@link SplashPanel#start()}.
   *
   * <p>Method under test: {@link SplashPanel#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.start()"})
  void testStart() {
    // Arrange
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(Mockito.<PropertyChangeEvent>any());

    PropertyChangeListener propertyChangeListener2 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener2).propertyChange(Mockito.<PropertyChangeEvent>any());

    SplashPanel splashPanel = new SplashPanel(mock(Sprite.class), 10.0d);
    splashPanel.setVerifyInputWhenFocusTarget(true);
    splashPanel.addPropertyChangeListener(propertyChangeListener2);
    splashPanel.addPropertyChangeListener(propertyChangeListener);
    splashPanel.addVetoableChangeListener(mock(VetoableChangeListener.class));
    splashPanel.putClientProperty(42, 10);

    // Act
    splashPanel.start();

    // Assert
    verify(propertyChangeListener2).propertyChange(isA(PropertyChangeEvent.class));
    verify(propertyChangeListener).propertyChange(isA(PropertyChangeEvent.class));
  }

  /**
   * Test {@link SplashPanel#start()}.
   *
   * <p>Method under test: {@link SplashPanel#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.start()"})
  void testStart2() {
    // Arrange
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(Mockito.<PropertyChangeEvent>any());

    PropertyChangeListener propertyChangeListener2 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener2).propertyChange(Mockito.<PropertyChangeEvent>any());

    SplashPanel splashPanel = new SplashPanel(mock(Sprite.class), 2.0d);
    splashPanel.setVerifyInputWhenFocusTarget(true);
    splashPanel.addPropertyChangeListener(propertyChangeListener2);
    splashPanel.addPropertyChangeListener(propertyChangeListener);
    splashPanel.addVetoableChangeListener(mock(VetoableChangeListener.class));
    splashPanel.putClientProperty(42, 10);

    // Act
    splashPanel.start();

    // Assert
    verify(propertyChangeListener2).propertyChange(isA(PropertyChangeEvent.class));
    verify(propertyChangeListener).propertyChange(isA(PropertyChangeEvent.class));
  }

  /**
   * Test {@link SplashPanel#start()}.
   *
   * <p>Method under test: {@link SplashPanel#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.start()"})
  void testStart3() {
    // Arrange
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(Mockito.<PropertyChangeEvent>any());

    PropertyChangeListener propertyChangeListener2 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener2).propertyChange(Mockito.<PropertyChangeEvent>any());

    SplashPanel splashPanel = new SplashPanel(mock(Sprite.class), 10.0d);
    splashPanel.setVerifyInputWhenFocusTarget(true);
    splashPanel.addPropertyChangeListener(propertyChangeListener2);
    splashPanel.addPropertyChangeListener(propertyChangeListener);
    splashPanel.addVetoableChangeListener(mock(VetoableChangeListener.class));
    splashPanel.putClientProperty(42, "42");

    // Act
    splashPanel.start();

    // Assert
    verify(propertyChangeListener2).propertyChange(isA(PropertyChangeEvent.class));
    verify(propertyChangeListener).propertyChange(isA(PropertyChangeEvent.class));
  }

  /**
   * Test {@link SplashPanel#start()}.
   *
   * <p>Method under test: {@link SplashPanel#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.start()"})
  void testStart4() {
    // Arrange
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(Mockito.<PropertyChangeEvent>any());

    PropertyChangeListener propertyChangeListener2 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener2).propertyChange(Mockito.<PropertyChangeEvent>any());

    SplashPanel splashPanel = new SplashPanel(mock(Sprite.class), 10.0d);
    splashPanel.setVerifyInputWhenFocusTarget(true);
    splashPanel.addPropertyChangeListener(propertyChangeListener2);
    splashPanel.addPropertyChangeListener(propertyChangeListener);
    splashPanel.addVetoableChangeListener(mock(VetoableChangeListener.class));
    splashPanel.putClientProperty(42, JComponent.UNDEFINED_CONDITION);

    // Act
    splashPanel.start();

    // Assert
    verify(propertyChangeListener2).propertyChange(isA(PropertyChangeEvent.class));
    verify(propertyChangeListener).propertyChange(isA(PropertyChangeEvent.class));
  }

  /**
   * Test {@link SplashPanel#start()}.
   *
   * <p>Method under test: {@link SplashPanel#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SplashPanel.start()"})
  void testStart5() {
    // Arrange
    PropertyChangeListener propertyChangeListener = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener).propertyChange(Mockito.<PropertyChangeEvent>any());

    PropertyChangeListener propertyChangeListener2 = mock(PropertyChangeListener.class);
    doNothing().when(propertyChangeListener2).propertyChange(Mockito.<PropertyChangeEvent>any());

    SplashPanel splashPanel = new SplashPanel(mock(Sprite.class), 10.0d, 1L);
    splashPanel.setVerifyInputWhenFocusTarget(true);
    splashPanel.addPropertyChangeListener(propertyChangeListener2);
    splashPanel.addPropertyChangeListener(propertyChangeListener);
    splashPanel.addVetoableChangeListener(mock(VetoableChangeListener.class));
    splashPanel.putClientProperty(42, "42");

    // Act
    splashPanel.start();

    // Assert
    verify(propertyChangeListener2).propertyChange(isA(PropertyChangeEvent.class));
    verify(propertyChangeListener).propertyChange(isA(PropertyChangeEvent.class));
  }
}
