package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.RecordAttribute;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.visitor.RecordComponentInfoVisitor;

class AttributeShrinkerDiffblueTest {
  /**
   * Method under test:
   * {@link AttributeShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute() {
    // Arrange
    AttributeShrinker attributeShrinker = new AttributeShrinker();
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute = mock(RecordAttribute.class);
    doNothing().when(recordAttribute).componentsAccept(Mockito.<Clazz>any(), Mockito.<RecordComponentInfoVisitor>any());

    // Act
    attributeShrinker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    verify(recordAttribute).componentsAccept(isA(Clazz.class), isA(RecordComponentInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link AttributeShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  void testVisitRecordComponentInfo() {
    // Arrange
    AttributeShrinker attributeShrinker = new AttributeShrinker();
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = new RecordComponentInfo(1, 1, 1,
        new Attribute[]{new BootstrapMethodsAttribute()});

    // Act
    attributeShrinker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    Attribute[] attributeArray = recordComponentInfo.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, recordComponentInfo.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }
}
