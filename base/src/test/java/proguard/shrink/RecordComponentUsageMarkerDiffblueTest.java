package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.visitor.MemberVisitor;
import proguard.util.Processable;

class RecordComponentUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link RecordComponentUsageMarker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  void testVisitRecordComponentInfo() {
    // Arrange
    RecordComponentUsageMarker recordComponentUsageMarker = new RecordComponentUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = mock(RecordComponentInfo.class);
    doNothing().when(recordComponentInfo).referencedFieldAccept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    recordComponentUsageMarker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    verify(recordComponentInfo).referencedFieldAccept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link RecordComponentUsageMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    RecordComponentUsageMarker recordComponentUsageMarker = new RecordComponentUsageMarker(classUsageMarker);
    ProgramClass programClass = new ProgramClass();

    // Act
    recordComponentUsageMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link RecordComponentUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    RecordComponentUsageMarker recordComponentUsageMarker = new RecordComponentUsageMarker(
        new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    recordComponentUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }
}
