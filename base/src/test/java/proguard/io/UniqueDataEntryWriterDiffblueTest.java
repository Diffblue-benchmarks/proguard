package proguard.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import java.io.OutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.Configuration;
import proguard.io.JarWriter.MyMultiDigestOutputStream;
import proguard.util.StringFunction;

class UniqueDataEntryWriterDiffblueTest {
  /**
   * Test {@link UniqueDataEntryWriter#createDirectory(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test createDirectory(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.createDirectory(DataEntry)"})
  void testCreateDirectory_givenDataEntryFilterAcceptsReturnFalse_thenReturnFalse()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));

    // Act
    boolean actualCreateDirectoryResult =
        uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    assertFalse(actualCreateDirectoryResult);
  }

  /**
   * Test {@link UniqueDataEntryWriter#createDirectory(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test createDirectory(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.createDirectory(DataEntry)"})
  void testCreateDirectory_givenDataEntryFilterAcceptsReturnFalse_thenReturnFalse2()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new FilteredDataEntryWriter(
                        dataEntryFilter2,
                        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    boolean actualCreateDirectoryResult =
        uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertFalse(actualCreateDirectoryResult);
  }

  /**
   * Test {@link UniqueDataEntryWriter#createDirectory(DataEntry)}.
   *
   * <ul>
   *   <li>When {@link ClassPathDataEntry#ClassPathDataEntry(String)} with name is empty string.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createDirectory(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test createDirectory(DataEntry); when ClassPathDataEntry(String) with name is empty string; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.createDirectory(DataEntry)"})
  void testCreateDirectory_whenClassPathDataEntryWithNameIsEmptyString_thenReturnTrue()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new FilteredDataEntryWriter(
                        dataEntryFilter2,
                        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    boolean actualCreateDirectoryResult =
        uniqueDataEntryWriter.createDirectory(new ClassPathDataEntry(""));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertTrue(actualCreateDirectoryResult);
  }

  /**
   * Test {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  @DisplayName("Test sameOutputStream(DataEntry, DataEntry); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.sameOutputStream(DataEntry, DataEntry)"})
  void testSameOutputStream_thenReturnFalse() throws IOException {
    // Arrange
    UniqueDataEntryWriter dataEntryWriter1 =
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new CascadingDataEntryWriter(
                dataEntryWriter1,
                new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("42");

    // Act and Assert
    assertFalse(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Test {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  @DisplayName("Test sameOutputStream(DataEntry, DataEntry); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.sameOutputStream(DataEntry, DataEntry)"})
  void testSameOutputStream_thenReturnTrue() throws IOException {
    // Arrange
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("Name");

    // Act and Assert
    assertTrue(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Test {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  @DisplayName("Test sameOutputStream(DataEntry, DataEntry); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.sameOutputStream(DataEntry, DataEntry)"})
  void testSameOutputStream_thenReturnTrue2() throws IOException {
    // Arrange
    UniqueDataEntryWriter dataEntryWriter1 =
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new CascadingDataEntryWriter(
                dataEntryWriter1,
                new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("Name");

    // Act and Assert
    assertTrue(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Test {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}.
   *
   * <ul>
   *   <li>When {@link ClassPathDataEntry#ClassPathDataEntry(String)} with name is {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#sameOutputStream(DataEntry, DataEntry)}
   */
  @Test
  @DisplayName(
      "Test sameOutputStream(DataEntry, DataEntry); when ClassPathDataEntry(String) with name is '42'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UniqueDataEntryWriter.sameOutputStream(DataEntry, DataEntry)"})
  void testSameOutputStream_whenClassPathDataEntryWithNameIs42_thenReturnFalse()
      throws IOException {
    // Arrange
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT));
    ClassPathDataEntry dataEntry1 = new ClassPathDataEntry("42");

    // Act and Assert
    assertFalse(uniqueDataEntryWriter.sameOutputStream(dataEntry1, new ClassPathDataEntry("Name")));
  }

  /**
   * Test {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code
   *       false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test createOutputStream(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"OutputStream UniqueDataEntryWriter.createOutputStream(DataEntry)"})
  void testCreateOutputStream_givenDataEntryFilterAcceptsReturnFalse_thenReturnNull()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));

    // Act
    OutputStream actualCreateOutputStreamResult =
        uniqueDataEntryWriter.createOutputStream(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    assertNull(actualCreateOutputStreamResult);
  }

  /**
   * Test {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}.
   *
   * <ul>
   *   <li>Given {@link DataEntryFilter} {@link DataEntryFilter#accepts(DataEntry)} return {@code
   *       false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}
   */
  @Test
  @DisplayName(
      "Test createOutputStream(DataEntry); given DataEntryFilter accepts(DataEntry) return 'false'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"OutputStream UniqueDataEntryWriter.createOutputStream(DataEntry)"})
  void testCreateOutputStream_givenDataEntryFilterAcceptsReturnFalse_thenReturnNull2()
      throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(false);
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new FilteredDataEntryWriter(
                        dataEntryFilter2,
                        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))))));

    // Act
    OutputStream actualCreateOutputStreamResult =
        uniqueDataEntryWriter.createOutputStream(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    assertNull(actualCreateOutputStreamResult);
  }

  /**
   * Test {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}.
   *
   * <ul>
   *   <li>Then return {@link MyMultiDigestOutputStream}.
   * </ul>
   *
   * <p>Method under test: {@link UniqueDataEntryWriter#createOutputStream(DataEntry)}
   */
  @Test
  @DisplayName("Test createOutputStream(DataEntry); then return MyMultiDigestOutputStream")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"OutputStream UniqueDataEntryWriter.createOutputStream(DataEntry)"})
  void testCreateOutputStream_thenReturnMyMultiDigestOutputStream() throws IOException {
    // Arrange
    DataEntryFilter dataEntryFilter = mock(DataEntryFilter.class);
    when(dataEntryFilter.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    DataEntryFilter dataEntryFilter2 = mock(DataEntryFilter.class);
    when(dataEntryFilter2.accepts(Mockito.<DataEntry>any())).thenReturn(true);
    StringFunction manifestEntryNameFunction = mock(StringFunction.class);
    when(manifestEntryNameFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    DataEntryWriter dataEntryWriter = mock(DataEntryWriter.class);
    when(dataEntryWriter.createOutputStream(Mockito.<DataEntry>any()))
        .thenReturn(new ByteArrayOutputStream(1));
    UniqueDataEntryWriter zipEntryWriter = new UniqueDataEntryWriter(dataEntryWriter);
    DataEntryWriter dataEntryWriter2 = mock(DataEntryWriter.class);
    when(dataEntryWriter2.createOutputStream(Mockito.<DataEntry>any()))
        .thenReturn(new ByteArrayOutputStream(1));
    UniqueDataEntryWriter uniqueDataEntryWriter =
        new UniqueDataEntryWriter(
            new FilteredDataEntryWriter(
                dataEntryFilter,
                new UniqueDataEntryWriter(
                    new FilteredDataEntryWriter(
                        dataEntryFilter2,
                        new UniqueDataEntryWriter(
                            new JarWriter(
                                new String[] {},
                                "Creator",
                                "foo.txt",
                                manifestEntryNameFunction,
                                zipEntryWriter,
                                new UniqueDataEntryWriter(dataEntryWriter2)))))));

    // Act
    OutputStream actualCreateOutputStreamResult =
        uniqueDataEntryWriter.createOutputStream(new ClassPathDataEntry("Name"));

    // Assert
    verify(dataEntryFilter).accepts(isA(DataEntry.class));
    verify(dataEntryFilter2).accepts(isA(DataEntry.class));
    verify(dataEntryWriter).createOutputStream(isA(DataEntry.class));
    verify(dataEntryWriter2).createOutputStream(isA(DataEntry.class));
    verify(manifestEntryNameFunction).transform(eq("Name"));
    assertTrue(actualCreateOutputStreamResult instanceof MyMultiDigestOutputStream);
  }
}
