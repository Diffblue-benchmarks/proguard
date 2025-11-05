package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.io.ClassPathDataEntry;
import proguard.io.DataEntry;
import proguard.io.DataEntryReader;
import proguard.io.FilteredDataEntryReader;

class DataEntryReaderFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link DataEntryReaderFactory#createDataEntryReader(ClassPathEntry, DataEntryReader)}
   */
  @Test
  void testCreateDataEntryReader() throws IOException {
    // Arrange
    DataEntryReaderFactory dataEntryReaderFactory = new DataEntryReaderFactory(true);
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    DataEntryReader reader = mock(DataEntryReader.class);
    doNothing().when(reader).read(Mockito.<DataEntry>any());

    // Act
    DataEntryReader actualCreateDataEntryReaderResult = dataEntryReaderFactory.createDataEntryReader(classPathEntry,
        reader);
    actualCreateDataEntryReaderResult.read(new ClassPathDataEntry("Name"));

    // Assert
    verify(reader).read(isA(DataEntry.class));
    assertTrue(actualCreateDataEntryReaderResult instanceof FilteredDataEntryReader);
  }

  /**
   * Method under test:
   * {@link DataEntryReaderFactory#createDataEntryReader(ClassPathEntry, DataEntryReader)}
   */
  @Test
  void testCreateDataEntryReader2() throws IOException {
    // Arrange
    DataEntryReaderFactory dataEntryReaderFactory = new DataEntryReaderFactory(true);
    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);

    DataEntryReader reader = mock(DataEntryReader.class);
    doNothing().when(reader).read(Mockito.<DataEntry>any());

    // Act
    DataEntryReader actualCreateDataEntryReaderResult = dataEntryReaderFactory.createDataEntryReader(classPathEntry,
        reader);
    Class<Object> clazz = Object.class;
    actualCreateDataEntryReaderResult.read(new ClassPathDataEntry(clazz));

    // Assert
    verify(reader).read(isA(DataEntry.class));
    assertTrue(actualCreateDataEntryReaderResult instanceof FilteredDataEntryReader);
  }

  /**
   * Method under test:
   * {@link DataEntryReaderFactory#getFilterExcludingVersionedClasses(ClassPathEntry)}
   */
  @Test
  void testGetFilterExcludingVersionedClasses() {
    // Arrange and Act
    List<String> actualFilterExcludingVersionedClasses = DataEntryReaderFactory
        .getFilterExcludingVersionedClasses(new ClassPathEntry(Configuration.STD_OUT, true));

    // Assert
    assertEquals(1, actualFilterExcludingVersionedClasses.size());
    assertEquals("!META-INF/versions/**", actualFilterExcludingVersionedClasses.get(0));
  }

  /**
   * Method under test:
   * {@link DataEntryReaderFactory#getFilterExcludingVersionedClasses(ClassPathEntry)}
   */
  @Test
  void testGetFilterExcludingVersionedClasses2() {
    // Arrange
    ArrayList<String> filter = new ArrayList<>();
    filter.add("META-INF/versions");

    ClassPathEntry classPathEntry = new ClassPathEntry(Configuration.STD_OUT, true);
    classPathEntry.setFilter(filter);

    // Act
    List<String> actualFilterExcludingVersionedClasses = DataEntryReaderFactory
        .getFilterExcludingVersionedClasses(classPathEntry);

    // Assert
    assertEquals(1, actualFilterExcludingVersionedClasses.size());
    assertEquals("META-INF/versions", actualFilterExcludingVersionedClasses.get(0));
  }

  /**
   * Method under test:
   * {@link DataEntryReaderFactory#getFilterExcludingVersionedClasses(ClassPathEntry)}
   */
  @Test
  void testGetFilterExcludingVersionedClasses3() {
    // Arrange
    ClassPathEntry classPathEntry = mock(ClassPathEntry.class);
    when(classPathEntry.getFilter()).thenReturn(new ArrayList<>());

    // Act
    List<String> actualFilterExcludingVersionedClasses = DataEntryReaderFactory
        .getFilterExcludingVersionedClasses(classPathEntry);

    // Assert
    verify(classPathEntry).getFilter();
    assertEquals(1, actualFilterExcludingVersionedClasses.size());
    assertEquals("!META-INF/versions/**", actualFilterExcludingVersionedClasses.get(0));
  }

  /**
   * Method under test:
   * {@link DataEntryReaderFactory#getFilterExcludingVersionedClasses(ClassPathEntry)}
   */
  @Test
  void testGetFilterExcludingVersionedClasses4() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("!META-INF/versions/**");
    ClassPathEntry classPathEntry = mock(ClassPathEntry.class);
    when(classPathEntry.getFilter()).thenReturn(stringList);

    // Act
    List<String> actualFilterExcludingVersionedClasses = DataEntryReaderFactory
        .getFilterExcludingVersionedClasses(classPathEntry);

    // Assert
    verify(classPathEntry).getFilter();
    assertEquals(2, actualFilterExcludingVersionedClasses.size());
    assertEquals("!META-INF/versions/**", actualFilterExcludingVersionedClasses.get(1));
    assertEquals("42", actualFilterExcludingVersionedClasses.get(0));
  }
}
