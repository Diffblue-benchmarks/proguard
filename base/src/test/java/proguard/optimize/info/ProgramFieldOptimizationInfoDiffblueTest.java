package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.ConstantValueAttribute;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.value.CompositeDoubleValue;
import proguard.evaluation.value.ParticularDoubleValue;
import proguard.evaluation.value.ParticularFloatValue;
import proguard.evaluation.value.ParticularReferenceValue;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.UnknownDoubleValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.evaluation.value.object.AnalyzedObject;
import proguard.testutils.cpa.NamedField;

class ProgramFieldOptimizationInfoDiffblueTest {
  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  void testGeneralizeReferencedClass() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    UnknownReferenceValue referencedClass = new UnknownReferenceValue();

    // Act
    programFieldOptimizationInfo.generalizeReferencedClass(referencedClass);

    // Assert
    assertSame(referencedClass, programFieldOptimizationInfo.getReferencedClass());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  void testGeneralizeReferencedClass2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    AnalyzedObject value = mock(AnalyzedObject.class);
    when(value.getType()).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();
    ParticularReferenceValue referencedClass2 = new ParticularReferenceValue(referencedClass,
        new ParticularReferenceValueFactory(), "Reference ID", value);

    // Act
    programFieldOptimizationInfo.generalizeReferencedClass(referencedClass2);

    // Assert
    verify(value, atLeast(1)).getType();
    assertSame(referencedClass2, programFieldOptimizationInfo.getReferencedClass());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  void testResetValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new LibraryField(1, "Name", "Descriptor"));

    // Assert
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  void testResetValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new LibraryField(8, "Name", "Descriptor"));

    // Assert
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  void testResetValue3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new NamedField("Field Name", "Field Descriptor"));

    // Assert
    Value value = programFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  void testGeneralizeValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    UnknownDoubleValue doubleValue1 = new UnknownDoubleValue();

    // Act
    programFieldOptimizationInfo
        .generalizeValue(new CompositeDoubleValue(doubleValue1, (byte) 'A', new UnknownDoubleValue()));

    // Assert
    Value value = programFieldOptimizationInfo.getValue();
    assertTrue(value instanceof UnknownDoubleValue);
    assertEquals(doubleValue1, value);
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  void testGeneralizeValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    programFieldOptimizationInfo.visitConstantValueAttribute(clazz2, field, new ConstantValueAttribute(1, 1));
    TopValue value = new TopValue();

    // Act
    programFieldOptimizationInfo.generalizeValue(value);

    // Assert
    assertSame(value, programFieldOptimizationInfo.getValue());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
  void testVisitConstantValueAttribute() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    programFieldOptimizationInfo.visitConstantValueAttribute(clazz2, field, new ConstantValueAttribute(1, 1));

    // Assert
    assertNull(programFieldOptimizationInfo.getValue());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testSetProgramFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testSetProgramFieldOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(Double.SIZE, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testSetProgramFieldOptimizationInfo3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(8, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testSetProgramFieldOptimizationInfo4() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    NamedField field = new NamedField("Field Name", "Field Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, true);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testSetProgramFieldOptimizationInfo5() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(clazz, field, false);

    // Assert
    Object processingInfo = field.getProcessingInfo();
    Value value = ((ProgramFieldOptimizationInfo) processingInfo).getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertTrue(processingInfo instanceof ProgramFieldOptimizationInfo);
    assertNull(((ProgramFieldOptimizationInfo) processingInfo).getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#getProgramFieldOptimizationInfo(Field)}
   */
  @Test
  void testGetProgramFieldOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(ProgramFieldOptimizationInfo.getProgramFieldOptimizationInfo(new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ProgramFieldOptimizationInfo#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link ProgramFieldOptimizationInfo#setCanNotBeMadePrivate()}
   *   <li>{@link ProgramFieldOptimizationInfo#setRead()}
   *   <li>{@link ProgramFieldOptimizationInfo#setWritten()}
   *   <li>{@link ProgramFieldOptimizationInfo#canBeMadePrivate()}
   *   <li>{@link ProgramFieldOptimizationInfo#getReferencedClass()}
   *   <li>{@link ProgramFieldOptimizationInfo#isKept()}
   *   <li>{@link ProgramFieldOptimizationInfo#isRead()}
   *   <li>{@link ProgramFieldOptimizationInfo#isWritten()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.visitAnyAttribute(clazz2, new BootstrapMethodsAttribute());
    programFieldOptimizationInfo.setCanNotBeMadePrivate();
    programFieldOptimizationInfo.setRead();
    programFieldOptimizationInfo.setWritten();
    boolean actualCanBeMadePrivateResult = programFieldOptimizationInfo.canBeMadePrivate();
    programFieldOptimizationInfo.getReferencedClass();
    boolean actualIsKeptResult = programFieldOptimizationInfo.isKept();
    boolean actualIsReadResult = programFieldOptimizationInfo.isRead();

    // Assert that nothing has changed
    assertFalse(actualCanBeMadePrivateResult);
    assertFalse(actualIsKeptResult);
    assertTrue(actualIsReadResult);
    assertTrue(programFieldOptimizationInfo.isWritten());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(Double.SIZE, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
    assertTrue(actualProgramFieldOptimizationInfo.isRead());
    assertTrue(actualProgramFieldOptimizationInfo.isWritten());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo3() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(8, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo4() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new NamedField("Field Name", "Field Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo5() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), false);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }

  /**
   * Method under test:
   * {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo)}
   */
  @Test
  void testNewProgramFieldOptimizationInfo6() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(
        new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertNull(actualProgramFieldOptimizationInfo.getReferencedClass());
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isKept());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.canBeMadePrivate());
  }
}
