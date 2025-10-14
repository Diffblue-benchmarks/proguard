package proguard.backport;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.BootstrapMethodInfoVisitor;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;

class LambdaExpressionCollectorDiffblueTest {
  /**
   * Test {@link LambdaExpressionCollector#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#constantPoolEntriesAccept(ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link LambdaExpressionCollector#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); then calls constantPoolEntriesAccept(ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LambdaExpressionCollector.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsConstantPoolEntriesAccept() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector =
        new LambdaExpressionCollector(new HashMap<>());

    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());

    // Act
    lambdaExpressionCollector.visitProgramClass(programClass);

    // Assert
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
  }

  /**
   * Test {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz,
   * InvokeDynamicConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#attributesAccept(AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz,
   * InvokeDynamicConstant)}
   */
  @Test
  @DisplayName(
      "Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then calls attributesAccept(AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LambdaExpressionCollector.visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)"
  })
  void testVisitInvokeDynamicConstant_thenCallsAttributesAccept() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector =
        new LambdaExpressionCollector(new HashMap<>());

    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());

    // Act
    lambdaExpressionCollector.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
  }

  /**
   * Test {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz,
   * InvokeDynamicConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link InvokeDynamicConstant#getBootstrapMethodAttributeIndex()}.
   * </ul>
   *
   * <p>Method under test: {@link LambdaExpressionCollector#visitInvokeDynamicConstant(Clazz,
   * InvokeDynamicConstant)}
   */
  @Test
  @DisplayName(
      "Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then calls getBootstrapMethodAttributeIndex()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LambdaExpressionCollector.visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)"
  })
  void testVisitInvokeDynamicConstant_thenCallsGetBootstrapMethodAttributeIndex() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector =
        new LambdaExpressionCollector(new HashMap<>());

    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());

    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    when(invokeDynamicConstant.getBootstrapMethodAttributeIndex()).thenReturn(1);

    // Act
    lambdaExpressionCollector.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
    verify(invokeDynamicConstant).getBootstrapMethodAttributeIndex();
  }

  /**
   * Test {@link LambdaExpressionCollector#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link BootstrapMethodsAttribute#bootstrapMethodEntryAccept(Clazz, int,
   *       BootstrapMethodInfoVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link LambdaExpressionCollector#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then calls bootstrapMethodEntryAccept(Clazz, int, BootstrapMethodInfoVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LambdaExpressionCollector.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute_thenCallsBootstrapMethodEntryAccept() {
    // Arrange
    LambdaExpressionCollector lambdaExpressionCollector =
        new LambdaExpressionCollector(new HashMap<>());
    LibraryClass clazz = new LibraryClass();

    BootstrapMethodsAttribute bootstrapMethodsAttribute = mock(BootstrapMethodsAttribute.class);
    doNothing()
        .when(bootstrapMethodsAttribute)
        .bootstrapMethodEntryAccept(
            Mockito.<Clazz>any(), anyInt(), Mockito.<BootstrapMethodInfoVisitor>any());
    doNothing().when(bootstrapMethodsAttribute).addProcessingFlags((int[]) Mockito.any());
    bootstrapMethodsAttribute.addProcessingFlags(2, 1, 2, 1);

    // Act
    lambdaExpressionCollector.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    verify(bootstrapMethodsAttribute)
        .bootstrapMethodEntryAccept(isA(Clazz.class), eq(0), isA(BootstrapMethodInfoVisitor.class));
    verify(bootstrapMethodsAttribute).addProcessingFlags((int[]) Mockito.any());
  }
}
