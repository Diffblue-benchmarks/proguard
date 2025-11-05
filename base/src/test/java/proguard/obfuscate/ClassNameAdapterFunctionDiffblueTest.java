package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;

class ClassNameAdapterFunctionDiffblueTest {
  /**
   * Method under test:
   * {@link ClassNameAdapterFunction#ClassNameAdapterFunction(ClassPool)}
   */
  @Test
  void testNewClassNameAdapterFunction() {
    // Arrange, Act and Assert
    assertNull((new ClassNameAdapterFunction(new ClassPool())).transform("foo.txt"));
  }

  /**
   * Method under test: {@link ClassNameAdapterFunction#transform(String)}
   */
  @Test
  void testTransform() {
    // Arrange, Act and Assert
    assertNull((new ClassNameAdapterFunction(new ClassPool())).transform("foo.txt"));
  }
}
