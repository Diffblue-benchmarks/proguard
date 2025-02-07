package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.SignatureAttribute;

class FieldSignatureCollectorDiffblueTest {
  /**
   * Test {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.FieldSignatureCollector.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute() {
    // Arrange
    FieldSignatureCollector fieldSignatureCollector = new FieldSignatureCollector();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    // Act
    fieldSignatureCollector.visitSignatureAttribute(clazz, (Field) field, new SignatureAttribute(1, 1));

    // Assert
    verify(clazz).getString(eq(1));
    assertEquals("String", fieldSignatureCollector.getFieldSignature());
  }

  /**
   * Test {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)} with {@code clazz}, {@code field}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, Field, SignatureAttribute) with 'clazz', 'field', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.gson.FieldSignatureCollector.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzFieldSignatureAttribute2() {
    // Arrange
    FieldSignatureCollector fieldSignatureCollector = new FieldSignatureCollector();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    SignatureAttribute signatureAttribute = mock(SignatureAttribute.class);
    when(signatureAttribute.getSignature(Mockito.<Clazz>any())).thenReturn("Signature");

    // Act
    fieldSignatureCollector.visitSignatureAttribute(clazz, (Field) field, signatureAttribute);

    // Assert
    verify(signatureAttribute).getSignature(isA(Clazz.class));
    assertEquals("Signature", fieldSignatureCollector.getFieldSignature());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link FieldSignatureCollector}
   *   <li>{@link FieldSignatureCollector#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link FieldSignatureCollector#getFieldSignature()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.gson.FieldSignatureCollector.<init>()",
      "java.lang.String proguard.optimize.gson.FieldSignatureCollector.getFieldSignature()",
      "void proguard.optimize.gson.FieldSignatureCollector.visitAnyAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.Attribute)"})
  void testGettersAndSetters() {
    // Arrange and Act
    FieldSignatureCollector actualFieldSignatureCollector = new FieldSignatureCollector();
    LibraryClass clazz = new LibraryClass();
    actualFieldSignatureCollector.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert
    assertNull(actualFieldSignatureCollector.getFieldSignature());
  }
}
