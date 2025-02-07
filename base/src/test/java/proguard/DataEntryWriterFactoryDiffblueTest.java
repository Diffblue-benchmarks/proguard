package proguard;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.io.DataEntryWriter;
import proguard.io.DirectoryWriter;
import proguard.io.NameFilteredDataEntryWriter;
import proguard.io.UniqueDataEntryWriter;
import proguard.resources.file.ResourceFilePool;
import proguard.util.EmptyStringMatcher;
import proguard.util.StringFunction;
import proguard.util.StringMatcher;
import proguard.util.TransformedStringMatcher;

class DataEntryWriterFactoryDiffblueTest {
  /**
   * Test {@link DataEntryWriterFactory#DataEntryWriterFactory(ClassPool, ResourceFilePool, int, StringMatcher, int, boolean, boolean, PrivateKeyEntry[])}.
   * <p>
   * Method under test: {@link DataEntryWriterFactory#DataEntryWriterFactory(ClassPool, ResourceFilePool, int, StringMatcher, int, boolean, boolean, KeyStore.PrivateKeyEntry[])}
   */
  @Test
  @DisplayName("Test new DataEntryWriterFactory(ClassPool, ResourceFilePool, int, StringMatcher, int, boolean, boolean, PrivateKeyEntry[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.DataEntryWriterFactory.<init>(proguard.classfile.ClassPool, proguard.resources.file.ResourceFilePool, int, proguard.util.StringMatcher, int, boolean, boolean, java.security.KeyStore$PrivateKeyEntry[])"})
  void testNewDataEntryWriterFactory() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);

    // Act and Assert
    assertNull((new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true,
        new PrivateKeyEntry[]{null})).createDataEntryWriter(null, 1, 1, null));
  }

  /**
   * Test {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}.
   * <ul>
   *   <li>Given {@link ClassPathEntry#ClassPathEntry(File, boolean)} with file is {@link Configuration#STD_OUT} and isOutput is {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  @DisplayName("Test createDataEntryWriter(ClassPath, int, int, DataEntryWriter); given ClassPathEntry(File, boolean) with file is STD_OUT and isOutput is 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.io.DataEntryWriter proguard.DataEntryWriterFactory.createDataEntryWriter(proguard.ClassPath, int, int, proguard.io.DataEntryWriter)"})
  void testCreateDataEntryWriter_givenClassPathEntryWithFileIsStd_outAndIsOutputIsFalse() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true, null);

    ClassPath classPath = new ClassPath();
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, false));
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(dataEntryWriterFactory.createDataEntryWriter(classPath, 0, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))) instanceof NameFilteredDataEntryWriter);
  }

  /**
   * Test {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}.
   * <ul>
   *   <li>Then return {@link NameFilteredDataEntryWriter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  @DisplayName("Test createDataEntryWriter(ClassPath, int, int, DataEntryWriter); then return NameFilteredDataEntryWriter")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.io.DataEntryWriter proguard.DataEntryWriterFactory.createDataEntryWriter(proguard.ClassPath, int, int, proguard.io.DataEntryWriter)"})
  void testCreateDataEntryWriter_thenReturnNameFilteredDataEntryWriter() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true, null);

    ClassPath classPath = new ClassPath();
    classPath.add(0, new ClassPathEntry(Configuration.STD_OUT, true));

    // Act and Assert
    assertTrue(dataEntryWriterFactory.createDataEntryWriter(classPath, 0, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))) instanceof NameFilteredDataEntryWriter);
  }

  /**
   * Test {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}.
   * <ul>
   *   <li>When {@link ClassPath} (default constructor).</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DataEntryWriterFactory#createDataEntryWriter(ClassPath, int, int, DataEntryWriter)}
   */
  @Test
  @DisplayName("Test createDataEntryWriter(ClassPath, int, int, DataEntryWriter); when ClassPath (default constructor); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.io.DataEntryWriter proguard.DataEntryWriterFactory.createDataEntryWriter(proguard.ClassPath, int, int, proguard.io.DataEntryWriter)"})
  void testCreateDataEntryWriter_whenClassPath_thenReturnNull() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ResourceFilePool resourceFilePool = new ResourceFilePool();
    StringFunction stringFunction = mock(StringFunction.class);
    DataEntryWriterFactory dataEntryWriterFactory = new DataEntryWriterFactory(programClassPool, resourceFilePool, 1,
        new TransformedStringMatcher(stringFunction, new EmptyStringMatcher()), 1, true, true,
        new PrivateKeyEntry[]{null});
    ClassPath classPath = new ClassPath();

    // Act and Assert
    assertNull(dataEntryWriterFactory.createDataEntryWriter(classPath, 1, 1,
        new UniqueDataEntryWriter(new DirectoryWriter(Configuration.STD_OUT))));
  }
}
