package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeInfo;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.visitor.LocalVariableInfoVisitor;
import proguard.classfile.attribute.visitor.LocalVariableTypeInfoVisitor;

class VariableOptimizerDiffblueTest {
  /**
   * Method under test:
   * {@link VariableOptimizer#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTableAttribute() {
    // Arrange
    VariableOptimizer variableOptimizer = new VariableOptimizer(true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute = mock(LocalVariableTableAttribute.class);
    doNothing().when(localVariableTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableInfoVisitor>any());

    // Act
    variableOptimizer.visitLocalVariableTableAttribute(clazz, method, codeAttribute, localVariableTableAttribute);

    // Assert
    verify(localVariableTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link VariableOptimizer#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    VariableOptimizer variableOptimizer = new VariableOptimizer(true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute = mock(LocalVariableTypeTableAttribute.class);
    doNothing().when(localVariableTypeTableAttribute)
        .localVariablesAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<LocalVariableTypeInfoVisitor>any());

    // Act
    variableOptimizer.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        localVariableTypeTableAttribute);

    // Assert
    verify(localVariableTypeTableAttribute).localVariablesAccept(isA(Clazz.class), isA(Method.class),
        isA(CodeAttribute.class), isA(LocalVariableTypeInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link VariableOptimizer#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
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
   * Method under test:
   * {@link VariableOptimizer#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
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
