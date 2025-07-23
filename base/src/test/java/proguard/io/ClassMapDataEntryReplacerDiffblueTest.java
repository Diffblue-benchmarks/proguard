package proguard.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.configuration.InitialStateInfo;
import proguard.util.AndMatcher;
import proguard.util.EmptyStringMatcher;
import proguard.util.StringFunction;
import proguard.util.TransformedStringMatcher;

class ClassMapDataEntryReplacerDiffblueTest {
  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    UniqueDataEntryWriter dataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new NameFilteredDataEntryWriter(
                        "Regular Expression",
                        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer =
        new ClassMapDataEntryReplacer(
            programClassPool, new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code
   *       false}.
   *   <li>Then calls {@link DataEntryFilter#accepts(DataEntry)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then calls accepts(DataEntry)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead_givenDataEntryFilterAcceptsReturnFalse_thenCallsAccepts() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter dataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer =
        new ClassMapDataEntryReplacer(
            programClassPool, new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link StringFunction} {@link StringFunction#transform(String)} return empty
   *       string.
   *   <li>Then calls {@link StringFunction#transform(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given StringFunction transform(String) return empty string; then calls transform(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead_givenStringFunctionTransformReturnEmptyString_thenCallsTransform()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("");
    TransformedStringMatcher matcher1 =
        new TransformedStringMatcher(stringFunction2, new EmptyStringMatcher());

    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("Transform");
    TransformedStringMatcher stringMatcher =
        new TransformedStringMatcher(
            stringFunction,
            new AndMatcher(
                matcher1, new TransformedStringMatcher(stringFunction3, new EmptyStringMatcher())));

    UniqueDataEntryWriter dataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new NameFilteredDataEntryWriter(
                        stringMatcher, new UniqueDataEntryWriter(mock(DataEntryWriter.class))))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer =
        new ClassMapDataEntryReplacer(
            programClassPool, new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(stringFunction).transform(eq("Name"));
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link StringFunction} {@link StringFunction#transform(String)} return {@code
   *       Transform}.
   *   <li>Then calls {@link StringFunction#transform(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test read(DataEntry); given StringFunction transform(String) return 'Transform'; then calls transform(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead_givenStringFunctionTransformReturnTransform_thenCallsTransform()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("Transform");
    TransformedStringMatcher matcher1 =
        new TransformedStringMatcher(stringFunction2, new EmptyStringMatcher());

    StringFunction stringFunction3 = mock(StringFunction.class);
    TransformedStringMatcher stringMatcher =
        new TransformedStringMatcher(
            stringFunction,
            new AndMatcher(
                matcher1, new TransformedStringMatcher(stringFunction3, new EmptyStringMatcher())));

    UniqueDataEntryWriter dataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new NameFilteredDataEntryWriter(
                        stringMatcher, new UniqueDataEntryWriter(mock(DataEntryWriter.class))))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer =
        new ClassMapDataEntryReplacer(
            programClassPool, new InitialStateInfo(new ClassPool()), dataEntryWriter);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(stringFunction).transform(eq("Name"));
    verify(stringFunction2).transform(eq("Transform"));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#read(DataEntry)}.
   *
   * <ul>
   *   <li>Then calls {@link DataEntryWriter#createOutputStream(DataEntry)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#read(DataEntry)}
   */
  @Test
  @DisplayName("Test read(DataEntry); then calls createOutputStream(DataEntry)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassMapDataEntryReplacer.read(DataEntry)"})
  void testRead_thenCallsCreateOutputStream() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    StringFunction stringFunction = mock(StringFunction.class);
    when(stringFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    StringFunction stringFunction2 = mock(StringFunction.class);
    when(stringFunction2.transform(Mockito.<String>any())).thenReturn("");
    TransformedStringMatcher matcher1 =
        new TransformedStringMatcher(stringFunction2, new EmptyStringMatcher());

    StringFunction stringFunction3 = mock(StringFunction.class);
    when(stringFunction3.transform(Mockito.<String>any())).thenReturn("");
    TransformedStringMatcher stringMatcher =
        new TransformedStringMatcher(
            stringFunction,
            new AndMatcher(
                matcher1, new TransformedStringMatcher(stringFunction3, new EmptyStringMatcher())));

    DataEntryWriter dataEntryWriter = mock(DataEntryWriter.class);
    when(dataEntryWriter.createOutputStream(Mockito.<DataEntry>any()))
        .thenReturn(new ByteArrayOutputStream(1));
    UniqueDataEntryWriter dataEntryWriter2 =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new NameFilteredDataEntryWriter(
                        stringMatcher, new UniqueDataEntryWriter(dataEntryWriter)))));
    ClassPool programClassPool = new ClassPool();
    ClassMapDataEntryReplacer classMapDataEntryReplacer =
        new ClassMapDataEntryReplacer(
            programClassPool, new InitialStateInfo(new ClassPool()), dataEntryWriter2);

    // Act
    classMapDataEntryReplacer.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryWriter).createOutputStream(isA(DataEntry.class));
    verify(stringFunction).transform(eq("Name"));
    verify(stringFunction2).transform(eq("Transform"));
    verify(stringFunction3).transform(eq("Transform"));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   *
   * <ul>
   *   <li>When {@code 4194304}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when '4194304'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_when4194304_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMapDataEntryReplacer.isKept(4194304));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when minus one; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_whenMinusOne_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(ClassMapDataEntryReplacer.isKept(-1));
  }

  /**
   * Test {@link ClassMapDataEntryReplacer#isKept(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ClassMapDataEntryReplacer#isKept(int)}
   */
  @Test
  @DisplayName("Test isKept(int); when one; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ClassMapDataEntryReplacer.isKept(int)"})
  void testIsKept_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ClassMapDataEntryReplacer.isKept(1));
  }
}
