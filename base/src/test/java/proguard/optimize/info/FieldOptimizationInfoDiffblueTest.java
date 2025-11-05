package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.Value;

class FieldOptimizationInfoDiffblueTest {
  /**
   * Method under test: {@link FieldOptimizationInfo#canBeMadePrivate()}
   */
  @Test
  void testCanBeMadePrivate() {
    // Arrange, Act and Assert
    assertFalse((new FieldOptimizationInfo()).canBeMadePrivate());
  }

  /**
   * Method under test: {@link FieldOptimizationInfo#canBeMadePrivate()}
   */
  @Test
  void testCanBeMadePrivate2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertTrue(
        (new ProgramFieldOptimizationInfo(clazz, new LibraryField(2, "Name", "Descriptor"), true)).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link FieldOptimizationInfo#setFieldOptimizationInfo(Clazz, Field)}
   */
  @Test
  void testSetFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    FieldOptimizationInfo.setFieldOptimizationInfo(clazz, field);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    assertTrue(processingInfo instanceof FieldOptimizationInfo);
    assertNull(((FieldOptimizationInfo) processingInfo).getReferencedClass());
    assertNull(((FieldOptimizationInfo) processingInfo).getValue());
    assertTrue(((FieldOptimizationInfo) processingInfo).isKept());
    assertTrue(((FieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((FieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Method under test:
   * {@link FieldOptimizationInfo#getFieldOptimizationInfo(Field)}
   */
  @Test
  void testGetFieldOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(FieldOptimizationInfo.getFieldOptimizationInfo(new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link FieldOptimizationInfo}
   *   <li>{@link FieldOptimizationInfo#setValue(Value)}
   *   <li>{@link FieldOptimizationInfo#getReferencedClass()}
   *   <li>{@link FieldOptimizationInfo#getValue()}
   *   <li>{@link FieldOptimizationInfo#isKept()}
   *   <li>{@link FieldOptimizationInfo#isRead()}
   *   <li>{@link FieldOptimizationInfo#isWritten()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    FieldOptimizationInfo actualFieldOptimizationInfo = new FieldOptimizationInfo();
    TopValue value = new TopValue();
    actualFieldOptimizationInfo.setValue(value);
    actualFieldOptimizationInfo.getReferencedClass();
    Value actualValue = actualFieldOptimizationInfo.getValue();
    boolean actualIsKeptResult = actualFieldOptimizationInfo.isKept();
    boolean actualIsReadResult = actualFieldOptimizationInfo.isRead();

    // Assert that nothing has changed
    assertTrue(actualIsKeptResult);
    assertTrue(actualIsReadResult);
    assertTrue(actualFieldOptimizationInfo.isWritten());
    assertSame(value, actualValue);
  }
}
