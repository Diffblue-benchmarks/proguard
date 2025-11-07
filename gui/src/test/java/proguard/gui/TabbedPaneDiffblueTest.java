package proguard.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.GridBagLayout;
import java.awt.image.DirectColorModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TabbedPaneDiffblueTest {
  /**
   * Test new {@link TabbedPane} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link TabbedPane}
   */
  @Test
  @DisplayName("Test new TabbedPane (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TabbedPane.<init>()"})
  void testNewTabbedPane() {
    // Arrange and Act
    TabbedPane actualTabbedPane = new TabbedPane();

    // Assert
    assertTrue(actualTabbedPane.getLayout() instanceof GridBagLayout);
    assertTrue(actualTabbedPane.getColorModel() instanceof DirectColorModel);
    assertEquals("PanelUI", actualTabbedPane.getUIClassID());
    assertNull(actualTabbedPane.getNextFocusableComponent());
    assertNull(actualTabbedPane.getFocusCycleRootAncestor());
    assertNull(actualTabbedPane.getParent());
    assertNull(actualTabbedPane.getTopLevelAncestor());
    assertNull(actualTabbedPane.getFocusTraversalPolicy());
    assertNull(actualTabbedPane.getGraphics());
    assertNull(actualTabbedPane.getGraphicsConfiguration());
    assertNull(actualTabbedPane.getDropTarget());
    assertNull(actualTabbedPane.getInputContext());
    assertNull(actualTabbedPane.getInputMethodRequests());
    assertNull(actualTabbedPane.getPeer());
    assertNull(actualTabbedPane.getName());
    assertNull(actualTabbedPane.getToolTipText());
    assertNull(actualTabbedPane.getInputVerifier());
    assertNull(actualTabbedPane.getComponentPopupMenu());
    assertNull(actualTabbedPane.getRootPane());
    assertNull(actualTabbedPane.getTransferHandler());
    assertNull(actualTabbedPane.getBorder());
    assertEquals(0, actualTabbedPane.getDebugGraphicsOptions());
    assertEquals(0, actualTabbedPane.getHeight());
    assertEquals(0, actualTabbedPane.getWidth());
    assertEquals(0, actualTabbedPane.getX());
    assertEquals(0, actualTabbedPane.getY());
    assertEquals(0, actualTabbedPane.getComponentListeners().length);
    assertEquals(0, actualTabbedPane.getFocusListeners().length);
    assertEquals(0, actualTabbedPane.getHierarchyBoundsListeners().length);
    assertEquals(0, actualTabbedPane.getHierarchyListeners().length);
    assertEquals(0, actualTabbedPane.getInputMethodListeners().length);
    assertEquals(0, actualTabbedPane.getKeyListeners().length);
    assertEquals(0, actualTabbedPane.getMouseListeners().length);
    assertEquals(0, actualTabbedPane.getMouseMotionListeners().length);
    assertEquals(0, actualTabbedPane.getMouseWheelListeners().length);
    assertEquals(0, actualTabbedPane.getPropertyChangeListeners().length);
    assertEquals(0, actualTabbedPane.getContainerListeners().length);
    assertEquals(0, actualTabbedPane.getAncestorListeners().length);
    assertEquals(0, actualTabbedPane.getRegisteredKeyStrokes().length);
    assertEquals(0, actualTabbedPane.getVetoableChangeListeners().length);
    assertEquals(0.5f, actualTabbedPane.getAlignmentX());
    assertEquals(0.5f, actualTabbedPane.getAlignmentY());
    assertEquals(1, actualTabbedPane.getComponentCount());
    assertEquals(1, actualTabbedPane.getComponents().length);
    assertEquals(BaselineResizeBehavior.OTHER, actualTabbedPane.getBaselineResizeBehavior());
    assertFalse(actualTabbedPane.getIgnoreRepaint());
    assertFalse(actualTabbedPane.hasFocus());
    assertFalse(actualTabbedPane.isCursorSet());
    assertFalse(actualTabbedPane.isDisplayable());
    assertFalse(actualTabbedPane.isFocusOwner());
    assertFalse(actualTabbedPane.isLightweight());
    assertFalse(actualTabbedPane.isMaximumSizeSet());
    assertFalse(actualTabbedPane.isMinimumSizeSet());
    assertFalse(actualTabbedPane.isPreferredSizeSet());
    assertFalse(actualTabbedPane.isShowing());
    assertFalse(actualTabbedPane.isValid());
    assertFalse(actualTabbedPane.isFocusCycleRoot());
    assertFalse(actualTabbedPane.isFocusTraversalPolicyProvider());
    assertFalse(actualTabbedPane.isFocusTraversalPolicySet());
    assertFalse(actualTabbedPane.getAutoscrolls());
    assertFalse(actualTabbedPane.getInheritsPopupMenu());
    assertFalse(actualTabbedPane.isManagingFocus());
    assertFalse(actualTabbedPane.isPaintingForPrint());
    assertFalse(actualTabbedPane.isPaintingTile());
    assertFalse(actualTabbedPane.isValidateRoot());
    assertTrue(actualTabbedPane.getFocusTraversalKeysEnabled());
    assertTrue(actualTabbedPane.isBackgroundSet());
    assertTrue(actualTabbedPane.isEnabled());
    assertTrue(actualTabbedPane.isFocusable());
    assertTrue(actualTabbedPane.isFontSet());
    assertTrue(actualTabbedPane.isForegroundSet());
    assertTrue(actualTabbedPane.isVisible());
    assertTrue(actualTabbedPane.getVerifyInputWhenFocusTarget());
    assertTrue(actualTabbedPane.isDoubleBuffered());
    assertTrue(actualTabbedPane.isOpaque());
    assertTrue(actualTabbedPane.isOptimizedDrawingEnabled());
    assertTrue(actualTabbedPane.isRequestFocusEnabled());
  }
}
