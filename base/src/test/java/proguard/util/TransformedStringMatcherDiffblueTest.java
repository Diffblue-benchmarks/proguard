package proguard.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TransformedStringMatcherDiffblueTest {
  /**
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches2() {
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

  /**
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches3() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches4() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches5() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String)}
   */
  @Test
  void testMatches6() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches7() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches8() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches9() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches10() {
    // Arrange
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");

    // Act
    boolean actualMatchesResult = (new TransformedStringMatcher(stringFunction, new TransformedStringMatcher(
        stringFunction2, new TransformedStringMatcher(stringFunction3, new EmptyStringMatcher())))).matches("String", 1,
            3);

    // Assert
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
    verify(stringFunction).transform(eq("tr"));
    assertFalse(actualMatchesResult);
  }

  /**
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches11() {
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
   * Method under test: {@link TransformedStringMatcher#matches(String, int, int)}
   */
  @Test
  void testMatches12() {
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
}
