package proguard.optimize.peephole;

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
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.constant.visitor.ConstantVisitor;

class RetargetedInnerClassAttributeRemoverDiffblueTest {
  /**
   * Test {@link RetargetedInnerClassAttributeRemover#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Then calls {@link InnerClassesInfo#innerClassConstantAccept(Clazz, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RetargetedInnerClassAttributeRemover#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); then calls innerClassConstantAccept(Clazz, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RetargetedInnerClassAttributeRemover.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_thenCallsInnerClassConstantAccept() {
    // Arrange
    RetargetedInnerClassAttributeRemover retargetedInnerClassAttributeRemover = new RetargetedInnerClassAttributeRemover();
    LibraryClass clazz = new LibraryClass();
    InnerClassesInfo innerClassesInfo = mock(InnerClassesInfo.class);
    doNothing().when(innerClassesInfo).innerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing().when(innerClassesInfo).outerClassConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    retargetedInnerClassAttributeRemover.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(innerClassesInfo).innerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(innerClassesInfo).outerClassConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }
}
