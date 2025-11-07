package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTypeInfo;

class VariableOptimizerDiffblueTest {
  /**
   * Test {@link VariableOptimizer#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <p>
   * Method under test: {@link VariableOptimizer#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VariableOptimizer.visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)"})
  void testVisitLocalVariableInfo() {
    // Arrange
    VariableOptimizer variableOptimizer = new VariableOptimizer(true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    // Act
    variableOptimizer.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    assertEquals(0, localVariableInfo.u2length);
  }

  /**
   * Test {@link VariableOptimizer#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}.
   * <p>
   * Method under test: {@link VariableOptimizer#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void VariableOptimizer.visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)"})
  void testVisitLocalVariableTypeInfo() {
    // Arrange
    VariableOptimizer variableOptimizer = new VariableOptimizer(true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeInfo localVariableTypeInfo = new LocalVariableTypeInfo(1, 3, 1, 1, 1);

    // Act
    variableOptimizer.visitLocalVariableTypeInfo(clazz, method, codeAttribute, localVariableTypeInfo);

    // Assert
    assertEquals(0, localVariableTypeInfo.u2length);
  }
}
