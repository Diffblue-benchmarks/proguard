package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.AppView;
import proguard.ClassPath;
import proguard.Configuration;

class ShrinkerDiffblueTest {
  /**
   * Test {@link Shrinker#Shrinker(Configuration, boolean)}.
   *
   * <p>Method under test: {@link Shrinker#Shrinker(Configuration, boolean)}
   */
  @Test
  @DisplayName("Test new Shrinker(Configuration, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.<init>(Configuration, boolean)"})
  void testNewShrinker() throws MalformedURLException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    assertEquals("proguard.shrink.Shrinker", new Shrinker(configuration, true).getName());
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#keep} is {@code
   *       null}.
   *   <li>When {@link AppView#AppView()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Configuration (default constructor) keep is 'null'; when AppView(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenConfigurationKeepIsNull_whenAppView_thenThrowIOException()
      throws IOException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.whyAreYouKeeping = null;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.keep = null;
    configuration.keepKotlinMetadata = false;
    configuration.warn = null;
    configuration.ignoreWarnings = false;
    Shrinker shrinker = new Shrinker(configuration, false);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#keep} is {@code
   *       null}.
   *   <li>When {@link AppView#AppView()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Configuration (default constructor) keep is 'null'; when AppView(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenConfigurationKeepIsNull_whenAppView_thenThrowIOException2()
      throws IOException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.printUsage = null;
    configuration.keep = null;
    configuration.keepKotlinMetadata = false;
    configuration.warn = null;
    configuration.ignoreWarnings = false;
    Shrinker shrinker = new Shrinker(configuration, false);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#printUsage} is
   *       {@code null}.
   *   <li>When {@link AppView#AppView()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Configuration (default constructor) printUsage is 'null'; when AppView(); then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenConfigurationPrintUsageIsNull_whenAppView_thenThrowIOException()
      throws IOException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.whyAreYouKeeping = new ArrayList<>();
    configuration.printUsage = null;
    configuration.keep = new ArrayList<>();
    configuration.keepKotlinMetadata = false;
    configuration.warn = null;
    configuration.ignoreWarnings = false;
    Shrinker shrinker = new Shrinker(configuration, false);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#whyAreYouKeeping}
   *       is {@code null}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Configuration (default constructor) whyAreYouKeeping is 'null'; then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenConfigurationWhyAreYouKeepingIsNull_thenThrowIOException()
      throws IOException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.whyAreYouKeeping = null;
    configuration.printUsage = null;
    configuration.keep = new ArrayList<>();
    configuration.keepKotlinMetadata = false;
    configuration.warn = null;
    configuration.ignoreWarnings = false;
    Shrinker shrinker = new Shrinker(configuration, false);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Configuration} (default constructor) {@link Configuration#whyAreYouKeeping}
   *       is {@code null}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Configuration (default constructor) whyAreYouKeeping is 'null'; then throw IOException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenConfigurationWhyAreYouKeepingIsNull_thenThrowIOException2()
      throws IOException {
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
    configuration.classObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.obfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
    configuration.optimizationPasses = 1;
    configuration.optimizations = new ArrayList<>();
    configuration.optimize = true;
    configuration.optimizeConservatively = true;
    configuration.overloadAggressively = true;
    configuration.packageObfuscationDictionary =
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();
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
    configuration.whyAreYouKeeping = null;
    configuration.printUsage = Configuration.STD_OUT;
    configuration.keep = new ArrayList<>();
    configuration.keepKotlinMetadata = false;
    configuration.warn = null;
    configuration.ignoreWarnings = false;
    Shrinker shrinker = new Shrinker(configuration, false);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }

  /**
   * Test {@link Shrinker#execute(AppView)}.
   *
   * <ul>
   *   <li>Given {@link Shrinker#Shrinker(Configuration, boolean)} with {@link Configuration} and
   *       afterOptimizer is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Shrinker#execute(AppView)}
   */
  @Test
  @DisplayName(
      "Test execute(AppView); given Shrinker(Configuration, boolean) with Configuration and afterOptimizer is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Shrinker.execute(AppView)"})
  void testExecute_givenShrinkerWithConfigurationAndAfterOptimizerIsTrue() throws IOException {
    // Arrange
    Shrinker shrinker = new Shrinker(mock(Configuration.class), true);

    // Act and Assert
    assertThrows(IOException.class, () -> shrinker.execute(new AppView()));
  }
}
