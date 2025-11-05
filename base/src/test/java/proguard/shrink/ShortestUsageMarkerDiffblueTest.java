package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.util.Processable;

class ShortestUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link ShortestUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    shortestUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    verify(processable).setProcessingInfo(isNull());
  }

  /**
   * Method under test: {@link ShortestUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    shortestUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    verify(processable).setProcessingInfo(isNull());
  }

  /**
   * Method under test: {@link ShortestUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    shortestUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo()).thenReturn(
        new ShortestUsageMark(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()), false));
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    shortestUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertFalse(shortestUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass();
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertFalse(shortestUsageMarker.isUsed(processable));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = shortestUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualIsUsedResult = shortestUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualIsUsedResult = shortestUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(
        new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1, "Processing Info")));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1,
        1, 1, "Feature Name", 1, new ShortestUsageMark("Just cause"))));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1,
        1, 1, "Feature Name", 1, new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()))));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed6() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed7() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed8() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed9() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed10() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass();
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsUsed(processable));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed11() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed12() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed13() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo())
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed14() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = shortestUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    LibraryClass processable = new LibraryClass();

    // Act
    shortestUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    Object processingInfo = processable.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertFalse(((ShortestUsageMark) processingInfo).isCertain());
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo(new ShortestUsageMark("Just cause"));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ProgramClass programClass = new ProgramClass();

    ProgramField programMember = new ProgramField();
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    programMember.setProcessingInfo(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act and Assert
    assertFalse(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, programMember));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed6() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass();
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertTrue(shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed7() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed8() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed9() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    shortestUsageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    Processable processable = mock(Processable.class);
    ShortestUsageMark previousUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo())
        .thenReturn(new ShortestUsageMark(previousUsageMark, "Just cause", 1, new LibraryClass()));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = shortestUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertFalse(shortestUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    LibraryClass processable = new LibraryClass();
    processable.setProcessingInfo("Processable");

    // Act and Assert
    assertFalse(shortestUsageMarker.isPossiblyUsed(processable));
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed3() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsPossiblyUsedResult = shortestUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed4() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualIsPossiblyUsedResult = shortestUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ShortestUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed5() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualIsPossiblyUsedResult = shortestUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  void testGetShortestUsageMark() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();

    // Act and Assert
    assertNull(shortestUsageMarker.getShortestUsageMark(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ShortestUsageMarker#getShortestUsageMark(Processable)}
   */
  @Test
  void testGetShortestUsageMark2() {
    // Arrange
    ShortestUsageMarker shortestUsageMarker = new ShortestUsageMarker();
    Processable processable = mock(Processable.class);
    ShortestUsageMark shortestUsageMark = new ShortestUsageMark("Just cause");
    when(processable.getProcessingInfo()).thenReturn(shortestUsageMark);

    // Act
    ShortestUsageMark actualShortestUsageMark = shortestUsageMarker.getShortestUsageMark(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertSame(shortestUsageMark, actualShortestUsageMark);
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ShortestUsageMarker}
   */
  @Test
  void testNewShortestUsageMarker() {
    // Arrange, Act and Assert
    assertNull((new ShortestUsageMarker()).currentUsageMark);
  }
}
