package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.io.DataEntry;
import proguard.io.DataEntryReader;

class InputReaderDiffblueTest {
  /**
   * Test {@link InputReader#InputReader(Configuration)}.
   * <p>
   * Method under test: {@link InputReader#InputReader(Configuration)}
   */
  @Test
  @DisplayName("Test new InputReader(Configuration)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.InputReader.<init>(proguard.Configuration)"})
  void testNewInputReader() throws MalformedURLException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Configuration.STD_OUT;
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Configuration.STD_OUT;
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Configuration.STD_OUT;
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "New Source File Attribute";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Configuration.STD_OUT;
    configuration.printMapping = Configuration.STD_OUT;
    configuration.printSeeds = Configuration.STD_OUT;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;

    // Act and Assert
    assertEquals("proguard.InputReader", (new InputReader(configuration)).getName());
  }

  /**
   * Test {@link InputReader#execute(AppView)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>When {@link AppView#AppView()}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputReader#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); given ArrayList() add empty string; when AppView(); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.InputReader.execute(proguard.AppView)"})
  void testExecute_givenArrayListAddEmptyString_whenAppView_thenThrowIOException() throws IOException {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("");
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = stringList;
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Configuration.STD_OUT;
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Configuration.STD_OUT;
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Configuration.STD_OUT;
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 2L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "Reading input...";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 2;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Configuration.STD_OUT;
    configuration.printMapping = Configuration.STD_OUT;
    configuration.printSeeds = Configuration.STD_OUT;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 2;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 2;
    InputReader inputReader = new InputReader(configuration);

    // Act and Assert
    assertThrows(IOException.class, () -> inputReader.execute(new AppView()));
  }

  /**
   * Test {@link InputReader#execute(AppView)}.
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#adaptClassStrings} is {@link ArrayList#ArrayList()}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputReader#execute(AppView)}
   */
  @Test
  @DisplayName("Test execute(AppView); given Configuration (default constructor) adaptClassStrings is ArrayList(); then throw IOException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.InputReader.execute(proguard.AppView)"})
  void testExecute_givenConfigurationAdaptClassStringsIsArrayList_thenThrowIOException() throws IOException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Configuration.STD_OUT;
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Configuration.STD_OUT;
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Configuration.STD_OUT;
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 2L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "Reading input...";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 2;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Configuration.STD_OUT;
    configuration.printMapping = Configuration.STD_OUT;
    configuration.printSeeds = Configuration.STD_OUT;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 2;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 2;
    InputReader inputReader = new InputReader(configuration);

    // Act and Assert
    assertThrows(IOException.class, () -> inputReader.execute(new AppView()));
  }

  /**
   * Test {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)} with {@code messagePrefix}, {@code classPath}, {@code fromIndex}, {@code toIndex}, {@code reader}.
   * <p>
   * Method under test: {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)}
   */
  @Test
  @DisplayName("Test readInput(String, ClassPath, int, int, DataEntryReader) with 'messagePrefix', 'classPath', 'fromIndex', 'toIndex', 'reader'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.InputReader.readInput(java.lang.String, proguard.ClassPath, int, int, proguard.io.DataEntryReader)"})
  void testReadInputWithMessagePrefixClassPathFromIndexToIndexReader() throws IOException {
    // Arrange
    InputReader inputReader = new InputReader(mock(Configuration.class));

    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, false));

    // Act and Assert
    assertThrows(IOException.class,
        () -> inputReader.readInput("Message Prefix", classPath, 0, 1, mock(DataEntryReader.class)));
  }

  /**
   * Test {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)} with {@code messagePrefix}, {@code classPath}, {@code fromIndex}, {@code toIndex}, {@code reader}.
   * <p>
   * Method under test: {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)}
   */
  @Test
  @DisplayName("Test readInput(String, ClassPath, int, int, DataEntryReader) with 'messagePrefix', 'classPath', 'fromIndex', 'toIndex', 'reader'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.InputReader.readInput(java.lang.String, proguard.ClassPath, int, int, proguard.io.DataEntryReader)"})
  void testReadInputWithMessagePrefixClassPathFromIndexToIndexReader2() throws IOException {
    // Arrange
    Configuration configuration = new Configuration();
    configuration.adaptClassStrings = new ArrayList<>();
    configuration.adaptResourceFileContents = new ArrayList<>();
    configuration.adaptResourceFileNames = new ArrayList<>();
    configuration.addConfigurationDebugging = true;
    configuration.allowAccessModification = true;
    configuration.android = true;
    configuration.applyMapping = Configuration.STD_OUT;
    configuration.assumeNoEscapingParameters = new ArrayList<>();
    configuration.assumeNoExternalReturnValues = new ArrayList<>();
    configuration.assumeNoExternalSideEffects = new ArrayList<>();
    configuration.assumeNoSideEffects = new ArrayList<>();
    configuration.assumeValues = new ArrayList<>();
    configuration.backport = true;
    configuration.classObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.dontCompress = new ArrayList<>();
    configuration.dontProcessKotlinMetadata = true;
    configuration.dump = Configuration.STD_OUT;
    configuration.enableKotlinAsserter = true;
    configuration.extraJar = Configuration.STD_OUT;
    configuration.flattenPackageHierarchy = "java.text";
    configuration.ignoreWarnings = true;
    configuration.keep = new ArrayList<>();
    configuration.keepAttributes = new ArrayList<>();
    configuration.keepDirectories = new ArrayList();
    configuration.keepKotlinMetadata = true;
    configuration.keepPackageNames = new ArrayList<>();
    configuration.keepParameterNames = true;
    configuration.keyAliases = new ArrayList<>();
    configuration.keyPasswords = new ArrayList<>();
    configuration.keyStorePasswords = new ArrayList<>();
    configuration.keyStores = new ArrayList<>();
    configuration.lastModified = 1L;
    configuration.libraryJars = new ClassPath();
    configuration.mergeInterfacesAggressively = true;
    configuration.microEdition = true;
    configuration.newSourceFileAttribute = "New Source File Attribute";
    configuration.note = new ArrayList<>();
    configuration.obfuscate = true;
    configuration.obfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
        .toUri()
        .toURL();
    configuration.preverify = true;
    configuration.printConfiguration = Configuration.STD_OUT;
    configuration.printMapping = Configuration.STD_OUT;
    configuration.printSeeds = Configuration.STD_OUT;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.programJars = new ClassPath();
    configuration.repackageClasses = "java.text";
    configuration.shrink = true;
    configuration.skipNonPublicLibraryClassMembers = true;
    configuration.skipNonPublicLibraryClasses = true;
    configuration.targetClassVersion = 1;
    configuration.useMixedCaseClassNames = true;
    configuration.useUniqueClassMemberNames = true;
    configuration.verbose = true;
    configuration.warn = new ArrayList<>();
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.zipAlign = 1;
    InputReader inputReader = new InputReader(configuration);

    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Configuration.STD_OUT, false));

    // Act and Assert
    assertThrows(IOException.class,
        () -> inputReader.readInput("Message Prefix", classPath, 0, 1, mock(DataEntryReader.class)));
  }

  /**
   * Test {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)} with {@code messagePrefix}, {@code classPath}, {@code fromIndex}, {@code toIndex}, {@code reader}.
   * <p>
   * Method under test: {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)}
   */
  @Test
  @DisplayName("Test readInput(String, ClassPath, int, int, DataEntryReader) with 'messagePrefix', 'classPath', 'fromIndex', 'toIndex', 'reader'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.InputReader.readInput(java.lang.String, proguard.ClassPath, int, int, proguard.io.DataEntryReader)"})
  void testReadInputWithMessagePrefixClassPathFromIndexToIndexReader3() throws IOException {
    // Arrange
    InputReader inputReader = new InputReader(mock(Configuration.class));

    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), false));
    DataEntryReader reader = mock(DataEntryReader.class);
    doThrow(new IOException("{}{} [{}]{}")).when(reader).read(Mockito.<DataEntry>any());

    // Act and Assert
    assertThrows(IOException.class, () -> inputReader.readInput("Message Prefix", classPath, 0, 1, reader));
    verify(reader).read(isA(DataEntry.class));
  }

  /**
   * Test {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)} with {@code messagePrefix}, {@code classPath}, {@code fromIndex}, {@code toIndex}, {@code reader}.
   * <ul>
   *   <li>Then calls {@link DataEntryReader#read(DataEntry)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputReader#readInput(String, ClassPath, int, int, DataEntryReader)}
   */
  @Test
  @DisplayName("Test readInput(String, ClassPath, int, int, DataEntryReader) with 'messagePrefix', 'classPath', 'fromIndex', 'toIndex', 'reader'; then calls read(DataEntry)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.InputReader.readInput(java.lang.String, proguard.ClassPath, int, int, proguard.io.DataEntryReader)"})
  void testReadInputWithMessagePrefixClassPathFromIndexToIndexReader_thenCallsRead() throws IOException {
    // Arrange
    InputReader inputReader = new InputReader(mock(Configuration.class));

    ClassPath classPath = new ClassPath();
    classPath.add(new ClassPathEntry(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), false));
    DataEntryReader reader = mock(DataEntryReader.class);
    doNothing().when(reader).read(Mockito.<DataEntry>any());

    // Act
    inputReader.readInput("Message Prefix", classPath, 0, 1, reader);

    // Assert
    verify(reader, atLeast(1)).read(Mockito.<DataEntry>any());
  }
}
