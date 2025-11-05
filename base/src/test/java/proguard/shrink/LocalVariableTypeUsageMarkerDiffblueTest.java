package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeInfo;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.util.Processable;

class LocalVariableTypeUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTableAttribute() {
    // Arrange
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(1, 1,
        new LocalVariableInfo[]{localVariableInfo});

    // Act
    localVariableTypeUsageMarker.visitLocalVariableTableAttribute(clazz, method, codeAttribute,
        localVariableTableAttribute);

    // Assert
    LocalVariableInfo[] localVariableInfoArray = localVariableTableAttribute.localVariableTable;
    assertEquals(1, localVariableInfoArray.length);
    assertSame(localVariableInfo, localVariableInfoArray[0]);
  }

  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeInfo localVariableTypeInfo = new LocalVariableTypeInfo(1, 3, 1, 1, 1);

    LocalVariableTypeTableAttribute localVariableTypeTableAttribute = new LocalVariableTypeTableAttribute(1, 1,
        new LocalVariableTypeInfo[]{localVariableTypeInfo});

    // Act
    localVariableTypeUsageMarker.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        localVariableTypeTableAttribute);

    // Assert
    LocalVariableTypeInfo[] localVariableTypeInfoArray = localVariableTypeTableAttribute.localVariableTypeTable;
    assertEquals(1, localVariableTypeInfoArray.length);
    assertSame(localVariableTypeInfo, localVariableTypeInfoArray[0]);
  }

  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  void testVisitLocalVariableInfo() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableInfo localVariableInfo = new LocalVariableInfo(1, 3, 1, 1, 1);

    // Act
    localVariableTypeUsageMarker.visitLocalVariableInfo(clazz, method, codeAttribute, localVariableInfo);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, localVariableInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  void testVisitLocalVariableTypeInfo() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeInfo localVariableTypeInfo = new LocalVariableTypeInfo(1, 3, 1, 1, 1);

    // Act
    localVariableTypeUsageMarker.visitLocalVariableTypeInfo(clazz, method, codeAttribute, localVariableTypeInfo);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, localVariableTypeInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(classUsageMarker);

    // Act
    localVariableTypeUsageMarker.visitProgramClass(new ProgramClass());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link LocalVariableTypeUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  void testVisitAnyConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant constant = new ClassConstant();

    // Act
    localVariableTypeUsageMarker.visitAnyConstant(clazz, constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constant.getProcessingInfo());
  }
}
