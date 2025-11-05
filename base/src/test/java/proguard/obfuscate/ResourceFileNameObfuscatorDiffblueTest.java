package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
  void testVisitAnyResourceFile2() {
    // Arrange
    StringFunction nameObfuscationFunction = mock(StringFunction.class);
    when(nameObfuscationFunction.transform(Mockito.<String>any())).thenReturn(null);
    ResourceFileNameObfuscator resourceFileNameObfuscator = new ResourceFileNameObfuscator(nameObfuscationFunction,
        true);
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);

    // Act
    resourceFileNameObfuscator.visitAnyResourceFile(resourceFile);

    // Assert
    verify(nameObfuscationFunction).transform(eq("foo.txt"));
    assertEquals("foo.txt", resourceFile.getFileName());
    assertNull(resourceFile.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#visitAnyResourceFile(ResourceFile)}
   */
  @Test
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
   * Method under test:
   * {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}
   */
  @Test
  void testIsObfuscated() {
    // Arrange, Act and Assert
    assertFalse(ResourceFileNameObfuscator.isObfuscated(new ResourceFile("foo.txt", 3L)));
  }

  /**
   * Method under test:
   * {@link ResourceFileNameObfuscator#isObfuscated(ResourceFile)}
   */
  @Test
  void testIsObfuscated2() {
    // Arrange
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);
    resourceFile.setProcessingInfo("Processing Info");

    // Act and Assert
    assertTrue(ResourceFileNameObfuscator.isObfuscated(resourceFile));
  }

  /**
   * Method under test:
   * {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}
   */
  @Test
  void testGetOriginalResourceFileName() {
    // Arrange, Act and Assert
    assertEquals("foo.txt", ResourceFileNameObfuscator.getOriginalResourceFileName(new ResourceFile("foo.txt", 3L)));
  }

  /**
   * Method under test:
   * {@link ResourceFileNameObfuscator#getOriginalResourceFileName(ResourceFile)}
   */
  @Test
  void testGetOriginalResourceFileName2() {
    // Arrange
    ResourceFile resourceFile = new ResourceFile("foo.txt", 3L);
    resourceFile.setProcessingInfo("Resource File");

    // Act and Assert
    assertEquals("Resource File", ResourceFileNameObfuscator.getOriginalResourceFileName(resourceFile));
  }
}
