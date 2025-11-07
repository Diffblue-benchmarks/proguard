package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link AttributeShrinker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then calls {@link RecordAttribute#componentsAccept(Clazz, RecordComponentInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AttributeShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then calls componentsAccept(Clazz, RecordComponentInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AttributeShrinker.visitRecordAttribute(Clazz, RecordAttribute)"})
  void testVisitRecordAttribute_thenCallsComponentsAccept() {
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
   * Test {@link AttributeShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   * <ul>
   *   <li>Then first element is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AttributeShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  @DisplayName("Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then first element is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void AttributeShrinker.visitRecordComponentInfo(Clazz, RecordComponentInfo)"})
  void testVisitRecordComponentInfo_thenFirstElementIsNull() {
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
