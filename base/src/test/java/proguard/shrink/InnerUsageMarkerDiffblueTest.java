package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.Utf8Constant;
import proguard.util.Processable;

class InnerUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link InnerUsageMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
    // Arrange
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(1, 1,
        new InnerClassesInfo[]{innerClassesInfo});

    // Act
    innerUsageMarker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    InnerClassesInfo[] innerClassesInfoArray = innerClassesAttribute.classes;
    assertEquals(1, innerClassesInfoArray.length);
    assertSame(innerClassesInfo, innerClassesInfoArray[0]);
  }

  /**
   * Method under test:
   * {@link InnerUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act
    innerUsageMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, innerClassesInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link InnerUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    innerUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link InnerUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ClassUsageMarker());
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    ClassConstant classConstant2 = new ClassConstant();

    // Act
    innerUsageMarker.visitClassConstant(clazz, classConstant2);

    // Assert
    assertEquals(classConstant, classConstant2);
  }

  /**
   * Method under test:
   * {@link InnerUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    innerUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Method under test: {@link InnerUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> innerUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link InnerUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    InnerUsageMarker innerUsageMarker = new InnerUsageMarker(classUsageMarker);

    // Act
    innerUsageMarker.visitProgramClass(new ProgramClass());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }
}
