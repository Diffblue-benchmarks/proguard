package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link RecordComponentUsageMarker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   * <ul>
   *   <li>Then calls {@link RecordComponentInfo#referencedFieldAccept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComponentUsageMarker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}
   */
  @Test
  @DisplayName("Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then calls referencedFieldAccept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComponentUsageMarker.visitRecordComponentInfo(Clazz, RecordComponentInfo)"})
  void testVisitRecordComponentInfo_thenCallsReferencedFieldAccept() {
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
   * Test {@link RecordComponentUsageMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RecordComponentUsageMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given ClassUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComponentUsageMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenClassUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
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
   * Test {@link RecordComponentUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   * <p>
   * Method under test: {@link RecordComponentUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName("Test visitUtf8Constant(Clazz, Utf8Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RecordComponentUsageMarker.visitUtf8Constant(Clazz, Utf8Constant)"})
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
