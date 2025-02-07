package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import proguard.resources.file.ResourceFile;
import proguard.resources.file.ResourceFilePool;
import proguard.resources.file.visitor.MultiResourceFileVisitor;
import proguard.resources.file.visitor.ResourceFileNameFilter;
import proguard.resources.file.visitor.ResourceFilePoolFiller;
import proguard.util.StringFunction;

class ResourceFileNameObfuscatorDiffblueTest {
  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true);
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile2() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn(null);
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true);
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert that nothing has changed
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("foo.txt", resourceFile.getFileName());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile3() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        false);
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile4() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true, new ResourceJavaReferenceFixer());
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile5() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true, new MultiResourceFileVisitor(new ResourceJavaReferenceFixer()));
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile6() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true, new ResourceFileNameFilter("foo.txt", new ResourceJavaReferenceFixer()));
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}.
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  @DisplayName("Test visitAnyResourceFile(ResourceFile)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.ResourceFileNameObfuscator.visitAnyResourceFile(proguard.resources.file.ResourceFile)"})
  void testVisitAnyResourceFile7() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn("Transform");
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true, new ResourceFilePoolFiller(new ResourceFilePool()));
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("Transform", resourceFile.getFileName());
    assertEquals("foo.txt", resourceFile.getProcessingInfo());
  }

  /**
   * Test {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}
   */
  @Test
  @DisplayName("Test isObfuscated(ResourceFile); given 'Processing Info'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.obfuscate.ResourceFileNameObfuscator.isObfuscated(proguard.resources.file.ResourceFile)"})
  void testIsObfuscated_givenProcessingInfo_thenReturnTrue() {
    // Arrange
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);
    resourceFile.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(ResourceFileNameObfuscator.isObfuscated(resourceFile));
  }

  /**
   * Test {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}
   */
  @Test
  @DisplayName("Test isObfuscated(ResourceFile); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.obfuscate.ResourceFileNameObfuscator.isObfuscated(proguard.resources.file.ResourceFile)"})
  void testIsObfuscated_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ResourceFileNameObfuscator.isObfuscated(new ResourceFile("foo.txt", 3L)));
  }

  /**
   * Test {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}.
   * <ul>
   *   <li>Given {@code Resource File}.</li>
   *   <li>Then return {@code Resource File}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}
   */
  @Test
  @DisplayName("Test getOriginalResourceFileName(ResourceFile); given 'Resource File'; then return 'Resource File'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.lang.String proguard.obfuscate.ResourceFileNameObfuscator.getOriginalResourceFileName(proguard.resources.file.ResourceFile)"})
  void testGetOriginalResourceFileName_givenResourceFile_thenReturnResourceFile() {
    // Arrange
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);
    resourceFile.setProcessingInfo("Resource File");

    // Act and Assert
    assertEquals("Resource File", ResourceFileNameObfuscator.getOriginalResourceFileName(resourceFile));
  }

  /**
   * Test {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}.
   * <ul>
   *   <li>Then return {@code foo.txt}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}
   */
  @Test
  @DisplayName("Test getOriginalResourceFileName(ResourceFile); then return 'foo.txt'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "java.lang.String proguard.obfuscate.ResourceFileNameObfuscator.getOriginalResourceFileName(proguard.resources.file.ResourceFile)"})
  void testGetOriginalResourceFileName_thenReturnFooTxt() {
    // Arrange, Act and Assert
    assertEquals("foo.txt", ResourceFileNameObfuscator.getOriginalResourceFileName(new ResourceFile("foo.txt", 3L)));
  }
}
