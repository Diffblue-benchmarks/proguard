package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link FieldSignatureCollector}
   *   <li>{@link FieldSignatureCollector#visitAnyAttribute(Clazz, Attribute)}
   *   <li>{@link FieldSignatureCollector#getFieldSignature()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    FieldSignatureCollector actualFieldSignatureCollector = new FieldSignatureCollector();
    LibraryClass clazz = new LibraryClass();
    actualFieldSignatureCollector.visitAnyAttribute(clazz, new BootstrapMethodsAttribute());

    // Assert that nothing has changed
    assertNull(actualFieldSignatureCollector.getFieldSignature());
  }

  /**
   * Method under test:
   * {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
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
   * Method under test:
   * {@link FieldSignatureCollector#visitSignatureAttribute(Clazz, Field, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute2() {
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
}
