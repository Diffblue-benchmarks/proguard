package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;

class ClassNameAdapterFunctionDiffblueTest {
  /**
   * Test {@link ClassNameAdapterFunction#ClassNameAdapterFunction(ClassPool)}.
   * <ul>
   *   <li>Then return transform {@code foo.txt} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassNameAdapterFunction#ClassNameAdapterFunction(ClassPool)}
   */
  @Test
  @DisplayName("Test new ClassNameAdapterFunction(ClassPool); then return transform 'foo.txt' is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.obfuscate.ClassNameAdapterFunction.<init>(proguard.classfile.ClassPool)"})
  void testNewClassNameAdapterFunction_thenReturnTransformFooTxtIsNull() {
    // Arrange, Act and Assert
    assertNull((new ClassNameAdapterFunction(new ClassPool())).transform("foo.txt"));
  }

  /**
   * Test {@link ClassNameAdapterFunction#transform(String)}.
   * <ul>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassNameAdapterFunction#transform(String)}
   */
  @Test
  @DisplayName("Test transform(String); when 'foo.txt'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.obfuscate.ClassNameAdapterFunction.transform(java.lang.String)"})
  void testTransform_whenFooTxt_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new ClassNameAdapterFunction(new ClassPool())).transform("foo.txt"));
  }
}
