package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link LocalVariableTypeUsageMarker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute); then array length is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitLocalVariableTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTableAttribute)"})
  void testVisitLocalVariableTableAttribute_thenArrayLengthIsOne() {
    // Arrange
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(1, 1,
        new LocalVariableInfo[]{new LocalVariableInfo(1, 3, 1, 1, 1)});

    // Act
    localVariableTypeUsageMarker.visitLocalVariableTableAttribute(clazz, method, codeAttribute,
        localVariableTableAttribute);

    // Assert
    assertEquals(1, localVariableTableAttribute.localVariableTable.length);
  }

  /**
   * Test {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}.
   * <ul>
   *   <li>Then array length is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute); then array length is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitLocalVariableTypeTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTypeTableAttribute)"})
  void testVisitLocalVariableTypeTableAttribute_thenArrayLengthIsOne() {
    // Arrange
    LocalVariableTypeUsageMarker localVariableTypeUsageMarker = new LocalVariableTypeUsageMarker(
        new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute = new LocalVariableTypeTableAttribute(1, 1,
        new LocalVariableTypeInfo[]{new LocalVariableTypeInfo(1, 3, 1, 1, 1)});

    // Act
    localVariableTypeUsageMarker.visitLocalVariableTypeTableAttribute(clazz, method, codeAttribute,
        localVariableTypeTableAttribute);

    // Assert
    assertEquals(1, localVariableTypeTableAttribute.localVariableTypeTable.length);
  }

  /**
   * Test {@link LocalVariableTypeUsageMarker#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}.
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableInfo(Clazz, Method, CodeAttribute, LocalVariableInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitLocalVariableInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableInfo)"})
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
   * Test {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}.
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)}
   */
  @Test
  @DisplayName("Test visitLocalVariableTypeInfo(Clazz, Method, CodeAttribute, LocalVariableTypeInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitLocalVariableTypeInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTypeInfo)"})
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
   * Test {@link LocalVariableTypeUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
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
   * Test {@link LocalVariableTypeUsageMarker#visitAnyConstant(Clazz, Constant)}.
   * <p>
   * Method under test: {@link LocalVariableTypeUsageMarker#visitAnyConstant(Clazz, Constant)}
   */
  @Test
  @DisplayName("Test visitAnyConstant(Clazz, Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.LocalVariableTypeUsageMarker.visitAnyConstant(proguard.classfile.Clazz, proguard.classfile.constant.Constant)"})
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
