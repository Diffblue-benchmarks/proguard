package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.ConstantValueAttribute;
import proguard.evaluation.value.CompositeDoubleValue;
import proguard.evaluation.value.ParticularDoubleValue;
import proguard.evaluation.value.ParticularFloatValue;
import proguard.evaluation.value.ReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.UnknownDoubleValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.testutils.cpa.NamedField;

class ProgramFieldOptimizationInfoDiffblueTest {
  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(8, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <ul>
   *   <li>Then return Read.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then return Read")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenReturnRead() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(Double.SIZE, "Name", "Descriptor"), true);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(actualProgramFieldOptimizationInfo.isRead());
    assertTrue(actualProgramFieldOptimizationInfo.isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo)}.
   * <ul>
   *   <li>Then Value return {@link ParticularDoubleValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(ProgramFieldOptimizationInfo); then Value return ParticularDoubleValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(ProgramFieldOptimizationInfo)"})
  void testNewProgramFieldOptimizationInfo_thenValueReturnParticularDoubleValue() {
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

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <ul>
   *   <li>Then Value return {@link ParticularFloatValue}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); then Value return ParticularFloatValue")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_thenValueReturnParticularFloatValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    Value value = (new ProgramFieldOptimizationInfo(clazz, new NamedField("Field Name", "Field Descriptor"), true))
        .getValue();
    assertTrue(value instanceof ParticularFloatValue);
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   *   <li>Then return not Read.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#ProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test new ProgramFieldOptimizationInfo(Clazz, Field, boolean); when 'false'; then return not Read")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.<init>(Clazz, Field, boolean)"})
  void testNewProgramFieldOptimizationInfo_whenFalse_thenReturnNotRead() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act
    ProgramFieldOptimizationInfo actualProgramFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), false);

    // Assert
    Value value = actualProgramFieldOptimizationInfo.getValue();
    assertTrue(value instanceof ParticularDoubleValue);
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(actualProgramFieldOptimizationInfo.isRead());
    assertFalse(actualProgramFieldOptimizationInfo.isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test getters and setters.
   * <p>
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
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProgramFieldOptimizationInfo.canBeMadePrivate()",
      "ReferenceValue ProgramFieldOptimizationInfo.getReferencedClass()",
      "boolean ProgramFieldOptimizationInfo.isKept()", "boolean ProgramFieldOptimizationInfo.isRead()",
      "boolean ProgramFieldOptimizationInfo.isWritten()", "void ProgramFieldOptimizationInfo.setCanNotBeMadePrivate()",
      "void ProgramFieldOptimizationInfo.setRead()", "void ProgramFieldOptimizationInfo.setWritten()",
      "void ProgramFieldOptimizationInfo.visitAnyAttribute(Clazz, Attribute)"})
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
    ReferenceValue actualReferencedClass = programFieldOptimizationInfo.getReferencedClass();
    boolean actualIsKeptResult = programFieldOptimizationInfo.isKept();
    boolean actualIsReadResult = programFieldOptimizationInfo.isRead();

    // Assert
    assertNull(actualReferencedClass);
    assertFalse(actualCanBeMadePrivateResult);
    assertFalse(actualIsKeptResult);
    assertTrue(actualIsReadResult);
    assertTrue(programFieldOptimizationInfo.isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#generalizeReferencedClass(ReferenceValue)}
   */
  @Test
  @DisplayName("Test generalizeReferencedClass(ReferenceValue)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeReferencedClass(ReferenceValue)"})
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
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new LibraryField(1, "Name", "Descriptor"));

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), false);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new LibraryField(1, "Name", "Descriptor"));

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
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
   * Test {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}.
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is eight and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#resetValue(Clazz, Field)}
   */
  @Test
  @DisplayName("Test resetValue(Clazz, Field); when LibraryField(int, String, String) with u2accessFlags is eight and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.resetValue(Clazz, Field)"})
  void testResetValue_whenLibraryFieldWithU2accessFlagsIsEightAndNameAndDescriptor() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ProgramFieldOptimizationInfo programFieldOptimizationInfo = new ProgramFieldOptimizationInfo(clazz,
        new LibraryField(1, "Name", "Descriptor"), true);
    LibraryClass clazz2 = new LibraryClass();

    // Act
    programFieldOptimizationInfo.resetValue(clazz2, new LibraryField(8, "Name", "Descriptor"));

    // Assert that nothing has changed
    assertTrue(programFieldOptimizationInfo.getValue() instanceof ParticularDoubleValue);
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  @DisplayName("Test generalizeValue(Value)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeValue(Value)"})
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
   * Test {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#generalizeValue(Value)}
   */
  @Test
  @DisplayName("Test generalizeValue(Value)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.generalizeValue(Value)"})
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
   * Test {@link ProgramFieldOptimizationInfo#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
  @DisplayName("Test visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void ProgramFieldOptimizationInfo.visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)"})
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
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"})
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
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"})
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
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"})
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
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"})
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
    assertEquals(0.0f, ((ParticularFloatValue) value).value());
    assertFalse(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}.
   * <ul>
   *   <li>When {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#setProgramFieldOptimizationInfo(Clazz, Field, boolean)}
   */
  @Test
  @DisplayName("Test setProgramFieldOptimizationInfo(Clazz, Field, boolean); when 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ProgramFieldOptimizationInfo.setProgramFieldOptimizationInfo(Clazz, Field, boolean)"})
  void testSetProgramFieldOptimizationInfo_whenFalse() {
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
    assertEquals(0.0d, ((ParticularDoubleValue) value).value());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isRead());
    assertFalse(((ProgramFieldOptimizationInfo) processingInfo).isWritten());
    assertTrue(value.isCategory2());
    assertTrue(value.isParticular());
    assertTrue(value.isSpecific());
  }

  /**
   * Test {@link ProgramFieldOptimizationInfo#getProgramFieldOptimizationInfo(Field)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramFieldOptimizationInfo#getProgramFieldOptimizationInfo(Field)}
   */
  @Test
  @DisplayName("Test getProgramFieldOptimizationInfo(Field); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "ProgramFieldOptimizationInfo ProgramFieldOptimizationInfo.getProgramFieldOptimizationInfo(Field)"})
  void testGetProgramFieldOptimizationInfo_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(ProgramFieldOptimizationInfo.getProgramFieldOptimizationInfo(new LibraryField(1, "Name", "Descriptor")));
  }
}
