package proguard.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransformedStringMatcherDiffblueTest {
  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2, new EmptyStringMatcher()))).matches("String");

    // Assert
    verify(stringFunction).transform(eq("String"));
    verify(stringFunction2).transform(eq("Transform"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString2() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction4 = mock(StringFunction.class);
    when(stringFunction4.transform(Mockito.<String>any())).thenReturn("Transform");
    TransformedStringMatcher matcher1 = new TransformedStringMatcher(stringFunction4, new EmptyStringMatcher());

    StringFunction stringFunction5 = mock(StringFunction.class);

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2,
            new TransformedStringMatcher(stringFunction3,
                new AndMatcher(matcher1, new TransformedStringMatcher(stringFunction5, new EmptyStringMatcher()))))))
        .matches("String");

    // Assert
    verify(stringFunction).transform(eq("String"));
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction4).transform(eq("Transform"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2, new EmptyStringMatcher()))).matches("String", 1, 3);

    // Assert
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset2() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new TransformedStringMatcher(
        stringFunction2, new TransformedStringMatcher(stringFunction3, new EmptyStringMatcher()))))
        .matches("String", 1, 3);

    // Assert
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset3() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction4 = mock(StringFunction.class);
    when(stringFunction4.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction5 = mock(StringFunction.class);
    when(stringFunction5.transform(Mockito.<String>any())).thenReturn("Transform");
    TransformedStringMatcher matcher1 = new TransformedStringMatcher(stringFunction5, new EmptyStringMatcher());

    StringFunction stringFunction6 = mock(StringFunction.class);

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2, new TransformedStringMatcher(stringFunction3,
            new TransformedStringMatcher(stringFunction4,
                new AndMatcher(matcher1, new TransformedStringMatcher(stringFunction6, new EmptyStringMatcher())))))))
        .matches("String", 1, 3);

    // Assert
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction4).transform(eq("Transform"));
    verify(stringFunction5).transform(eq("Transform"));
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset4() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction4 = mock(StringFunction.class);
    when(stringFunction4.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction5 = mock(StringFunction.class);
    when(stringFunction5.transform(Mockito.<String>any())).thenReturn("");
    TransformedStringMatcher matcher1 = new TransformedStringMatcher(stringFunction5, new EmptyStringMatcher());

    StringFunction stringFunction6 = mock(StringFunction.class);
    when(stringFunction6.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2, new TransformedStringMatcher(stringFunction3,
            new TransformedStringMatcher(stringFunction4,
                new AndMatcher(matcher1, new TransformedStringMatcher(stringFunction6, new EmptyStringMatcher())))))))
        .matches("String", 1, 3);

    // Assert
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction4).transform(eq("Transform"));
    verify(stringFunction5).transform(eq("Transform"));
    verify(stringFunction6).transform(eq("Transform"));
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset_thenReturnFalse() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()))
        .matches("String", 1, 3);

    // Assert
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String, int, int)} with {@code string}, {@code beginOffset}, {@code endOffset}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  @DisplayName("Test matches(String, int, int) with 'string', 'beginOffset', 'endOffset'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String, int, int)"})
  void testMatchesWithStringBeginOffsetEndOffset_thenReturnTrue() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()))
        .matches("String", 1, 3);

    // Assert
    verify(stringFunction).transform(eq("tr"));
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <ul>
   *   <li>Given {@link StringFunction} {@link StringFunction#transform(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'; given StringFunction transform(String) return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString_givenStringFunctionTransformReturnEmptyString() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction4 = mock(StringFunction.class);
    when(stringFunction4.transform(Mockito.<String>any())).thenReturn("");
    TransformedStringMatcher matcher1 = new TransformedStringMatcher(stringFunction4, new EmptyStringMatcher());

    StringFunction stringFunction5 = mock(StringFunction.class);
    when(stringFunction5.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2,
            new TransformedStringMatcher(stringFunction3,
                new AndMatcher(matcher1, new TransformedStringMatcher(stringFunction5, new EmptyStringMatcher()))))))
        .matches("String");

    // Assert
    verify(stringFunction).transform(eq("String"));
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction4).transform(eq("Transform"));
    verify(stringFunction5).transform(eq("Transform"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <ul>
   *   <li>Given {@link StringFunction} {@link StringFunction#transform(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'; given StringFunction transform(String) return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString_givenStringFunctionTransformReturnEmptyString2() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction4 = mock(StringFunction.class);
    when(stringFunction4.transform(Mockito.<String>any())).thenReturn("");
    StringFunction stringFunction5 = mock(StringFunction.class);
    when(stringFunction5.transform(Mockito.<String>any())).thenReturn("Transform");
    TransformedStringMatcher matcher1 = new TransformedStringMatcher(stringFunction4,
        new TransformedStringMatcher(stringFunction5, new EmptyStringMatcher()));

    StringFunction stringFunction6 = mock(StringFunction.class);
    when(stringFunction6.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction,
        new TransformedStringMatcher(stringFunction2,
            new TransformedStringMatcher(stringFunction3,
                new AndMatcher(matcher1, new TransformedStringMatcher(stringFunction6, new EmptyStringMatcher()))))))
        .matches("String");

    // Assert
    verify(stringFunction5).transform(eq(""));
    verify(stringFunction).transform(eq("String"));
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction4).transform(eq("Transform"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString_thenReturnFalse() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()))
        .matches("String");

    // Assert
    verify(stringFunction).transform(eq("String"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link TransformedStringMatcher#matches(String)} with {@code string}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  @DisplayName("Test matches(String) with 'string'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.util.TransformedStringMatcher.matches(java.lang.String)"})
  void testMatchesWithString_thenReturnTrue() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()))
        .matches("String");

    // Assert
    verify(stringFunction).transform(eq("String"));
    assertTrue(actualMatchesResult);
  }
}
