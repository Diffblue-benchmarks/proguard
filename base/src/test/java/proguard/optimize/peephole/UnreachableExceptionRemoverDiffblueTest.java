package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ExceptionInfo;
import proguard.classfile.attribute.visitor.ExceptionInfoVisitor;

class UnreachableExceptionRemoverDiffblueTest {
  /**
   * Test {@link UnreachableExceptionRemover#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}.
   * <p>
   * Method under test: {@link UnreachableExceptionRemover#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}
   */
  @Test
  @DisplayName("Test visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.UnreachableExceptionRemover.visitExceptionInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.ExceptionInfo)"})
  void testVisitExceptionInfo() {
    // Arrange
    UnreachableExceptionRemover unreachableExceptionRemover = new UnreachableExceptionRemover();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    ExceptionInfo exceptionInfo = new ExceptionInfo(1, 3, 1, 1);

    // Act
    unreachableExceptionRemover.visitExceptionInfo(clazz, method, codeAttribute, exceptionInfo);

    // Assert
    assertEquals(1, exceptionInfo.u2endPC);
  }

  /**
   * Test {@link UnreachableExceptionRemover#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}.
   * <ul>
   *   <li>Then calls {@link ExceptionInfoVisitor#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnreachableExceptionRemover#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}
   */
  @Test
  @DisplayName("Test visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo); then calls visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.UnreachableExceptionRemover.visitExceptionInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.ExceptionInfo)"})
  void testVisitExceptionInfo_thenCallsVisitExceptionInfo() {
    // Arrange
    ExceptionInfoVisitor extraExceptionInfoVisitor = mock(ExceptionInfoVisitor.class);
    doNothing().when(extraExceptionInfoVisitor)
        .visitExceptionInfo(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(),
            Mockito.<ExceptionInfo>any());
    UnreachableExceptionRemover unreachableExceptionRemover = new UnreachableExceptionRemover(
        extraExceptionInfoVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 3, 'A', 3, 'A', 3, 'A', 3});

    ExceptionInfo exceptionInfo = new ExceptionInfo(1, 3, 1, 1);

    // Act
    unreachableExceptionRemover.visitExceptionInfo(clazz, method, codeAttribute, exceptionInfo);

    // Assert
    verify(extraExceptionInfoVisitor).visitExceptionInfo(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class),
        isA(ExceptionInfo.class));
    assertEquals(1, exceptionInfo.u2endPC);
  }
}
