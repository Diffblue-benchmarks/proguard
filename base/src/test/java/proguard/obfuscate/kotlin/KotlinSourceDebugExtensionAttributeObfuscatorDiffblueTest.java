package proguard.obfuscate.kotlin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.SourceDebugExtensionAttribute;

class KotlinSourceDebugExtensionAttributeObfuscatorDiffblueTest {
  /**
   * Method under test:
   * {@link KotlinSourceDebugExtensionAttributeObfuscator#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute() throws UnsupportedEncodingException {
    // Arrange
    KotlinSourceDebugExtensionAttributeObfuscator kotlinSourceDebugExtensionAttributeObfuscator = new KotlinSourceDebugExtensionAttributeObfuscator();
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    kotlinSourceDebugExtensionAttributeObfuscator.visitSourceDebugExtensionAttribute(clazz,
        sourceDebugExtensionAttribute);

    // Assert
    assertEquals(45, sourceDebugExtensionAttribute.u4attributeLength);
    assertArrayEquals("SMAP\n\nKotlin\n*S Kotlin\n*F\n+ 1 \n\n*L\n1#1,1:1\n*E".getBytes("UTF-8"),
        sourceDebugExtensionAttribute.info);
  }
}
