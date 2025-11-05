package proguard.strip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.AppView;
import proguard.ClassPath;
import proguard.Configuration;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.visitor.ClassVisitor;

class KotlinAnnotationStripperDiffblueTest {
  /**
   * Method under test: {@link KotlinAnnotationStripper#execute(AppView)}
   */
  @Test
  void testExecute() {
    // Arrange
    KotlinAnnotationStripper kotlinAnnotationStripper = new KotlinAnnotationStripper(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    kotlinAnnotationStripper.execute(new AppView(programClassPool, new ClassPool()));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinAnnotationStripper#execute(AppView)}
   */
  @Test
  void testExecute2() {
    // Arrange
    KotlinAnnotationStripper kotlinAnnotationStripper = new KotlinAnnotationStripper(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    ClassPool libraryClassPool = mock(ClassPool.class);
    doNothing().when(libraryClassPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    kotlinAnnotationStripper.execute(new AppView(programClassPool, libraryClassPool));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
    verify(libraryClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinAnnotationStripper#execute(AppView)}
   */
  @Test
  void testExecute3() {
    // Arrange
    KotlinAnnotationStripper kotlinAnnotationStripper = new KotlinAnnotationStripper(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("Removing @kotlin.Metadata annotation where not kept...", new LibraryClass());

    // Act
    kotlinAnnotationStripper.execute(new AppView(programClassPool, libraryClassPool));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test: {@link KotlinAnnotationStripper#execute(AppView)}
   */
  @Test
  void testExecute4() {
    // Arrange
    KotlinAnnotationStripper kotlinAnnotationStripper = new KotlinAnnotationStripper(mock(Configuration.class));
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).classesAccept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ClassPool libraryClassPool = new ClassPool();
    libraryClassPool.addClass("Removing @kotlin.Metadata annotation where not kept...", clazz);

    // Act
    kotlinAnnotationStripper.execute(new AppView(programClassPool, libraryClassPool));

    // Assert
    verify(programClassPool).classesAccept(isA(ClassVisitor.class));
    verify(clazz).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link KotlinAnnotationStripper#KotlinAnnotationStripper(Configuration)}
   */
  @Test
  void testNewKotlinAnnotationStripper() throws MalformedURLException {
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
    assertEquals("proguard.strip.KotlinAnnotationStripper", (new KotlinAnnotationStripper(configuration)).getName());
  }
}
