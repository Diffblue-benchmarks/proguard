package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.Value;

class FieldOptimizationInfoDiffblueTest {
  /**
   * Test {@link FieldOptimizationInfo#canBeMadePrivate()}.
   *
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FieldOptimizationInfo#canBeMadePrivate()}
   */
  @Test
  @DisplayName(
      "Test canBeMadePrivate(); given FieldOptimizationInfo (default constructor); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldOptimizationInfo.canBeMadePrivate()"})
  void testCanBeMadePrivate_givenFieldOptimizationInfo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new FieldOptimizationInfo().canBeMadePrivate());
  }

  /**
   * Test {@link FieldOptimizationInfo#canBeMadePrivate()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FieldOptimizationInfo#canBeMadePrivate()}
   */
  @Test
  @DisplayName("Test canBeMadePrivate(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FieldOptimizationInfo.canBeMadePrivate()"})
  void testCanBeMadePrivate_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(2, "Name", "Descriptor");

    ProgramFieldOptimizationInfo programFieldOptimizationInfo =
        new ProgramFieldOptimizationInfo(clazz, field, true);

    // Act and Assert
    assertTrue(programFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Test {@link FieldOptimizationInfo#setFieldOptimizationInfo(Clazz, Field)}.
   *
   * <p>Method under test: {@link FieldOptimizationInfo#setFieldOptimizationInfo(Clazz, Field)}
   */
  @Test
  @DisplayName("Test setFieldOptimizationInfo(Clazz, Field)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FieldOptimizationInfo.setFieldOptimizationInfo(Clazz, Field)"})
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
   * Test {@link FieldOptimizationInfo#getFieldOptimizationInfo(Field)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FieldOptimizationInfo#getFieldOptimizationInfo(Field)}
   */
  @Test
  @DisplayName("Test getFieldOptimizationInfo(Field); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FieldOptimizationInfo FieldOptimizationInfo.getFieldOptimizationInfo(Field)"})
  void testGetFieldOptimizationInfo_thenReturnNull() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act and Assert
    assertNull(FieldOptimizationInfo.getFieldOptimizationInfo(field));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
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
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FieldOptimizationInfo.<init>()",
    "ReferenceValue FieldOptimizationInfo.getReferencedClass()",
    "Value FieldOptimizationInfo.getValue()",
    "boolean FieldOptimizationInfo.isKept()",
    "boolean FieldOptimizationInfo.isRead()",
    "boolean FieldOptimizationInfo.isWritten()",
    "void FieldOptimizationInfo.setValue(Value)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    FieldOptimizationInfo actualFieldOptimizationInfo = new FieldOptimizationInfo();
    TopValue value = new TopValue();
    actualFieldOptimizationInfo.setValue(value);
    ReferenceValue actualReferencedClass = actualFieldOptimizationInfo.getReferencedClass();
    Value actualValue = actualFieldOptimizationInfo.getValue();
    boolean actualIsKeptResult = actualFieldOptimizationInfo.isKept();
    boolean actualIsReadResult = actualFieldOptimizationInfo.isRead();

    // Assert
    assertNull(actualReferencedClass);
    assertTrue(actualIsKeptResult);
    assertTrue(actualIsReadResult);
    assertTrue(actualFieldOptimizationInfo.isWritten());
    assertSame(value, actualValue);
  }
}
