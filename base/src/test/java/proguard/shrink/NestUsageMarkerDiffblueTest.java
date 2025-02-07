package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.attribute.NestHostAttribute;
import proguard.classfile.attribute.NestMembersAttribute;
import proguard.classfile.attribute.PermittedSubclassesAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.util.Processable;

class NestUsageMarkerDiffblueTest {
  /**
   * Test {@link NestUsageMarker#visitNestHostAttribute(Clazz, NestHostAttribute)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestUsageMarker#visitNestHostAttribute(Clazz, NestHostAttribute)}
   */
  @Test
  @DisplayName("Test visitNestHostAttribute(Clazz, NestHostAttribute); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitNestHostAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestHostAttribute)"})
  void testVisitNestHostAttribute_thenCallsConstantPoolEntryAccept() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitNestHostAttribute(clazz, new NestHostAttribute(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link NestUsageMarker#visitNestMembersAttribute(Clazz, NestMembersAttribute)}.
   * <ul>
   *   <li>Then calls {@link NestMembersAttribute#memberClassConstantsAccept(Clazz, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestUsageMarker#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   */
  @Test
  @DisplayName("Test visitNestMembersAttribute(Clazz, NestMembersAttribute); then calls memberClassConstantsAccept(Clazz, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitNestMembersAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestMembersAttribute)"})
  void testVisitNestMembersAttribute_thenCallsMemberClassConstantsAccept() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    NestMembersAttribute nestMembersAttribute = mock(NestMembersAttribute.class);
    doNothing().when(nestMembersAttribute)
        .memberClassConstantsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitNestMembersAttribute(clazz, nestMembersAttribute);

    // Assert
    verify(nestMembersAttribute).memberClassConstantsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link NestUsageMarker#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}.
   * <ul>
   *   <li>Then calls {@link PermittedSubclassesAttribute#permittedSubclassConstantsAccept(Clazz, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestUsageMarker#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   */
  @Test
  @DisplayName("Test visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute); then calls permittedSubclassConstantsAccept(Clazz, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitPermittedSubclassesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.PermittedSubclassesAttribute)"})
  void testVisitPermittedSubclassesAttribute_thenCallsPermittedSubclassConstantsAccept() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    PermittedSubclassesAttribute permittedSubclassesAttribute = mock(PermittedSubclassesAttribute.class);
    doNothing().when(permittedSubclassesAttribute)
        .permittedSubclassConstantsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    nestUsageMarker.visitPermittedSubclassesAttribute(clazz, permittedSubclassesAttribute);

    // Assert
    verify(permittedSubclassesAttribute).permittedSubclassConstantsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    nestUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Test {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link NestUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new ProgramClass());

    // Act
    nestUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.getProcessingInfo());
  }

  /**
   * Test {@link NestUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   * <p>
   * Method under test: {@link NestUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName("Test visitUtf8Constant(Clazz, Utf8Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.NestUsageMarker.visitUtf8Constant(proguard.classfile.Clazz, proguard.classfile.constant.Utf8Constant)"})
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ShortestClassUsageMarker(usageMarker, "Just cause"));
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    nestUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Test {@link NestUsageMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.NestUsageMarker.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    NestUsageMarker nestUsageMarker = new NestUsageMarker(new ClassUsageMarker());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> nestUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link NestUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker} {@link ClassUsageMarker#isUsed(Processable)} return {@code true}.</li>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NestUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); given ClassUsageMarker isUsed(Processable) return 'true'; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.NestUsageMarker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_givenClassUsageMarkerIsUsedReturnTrue_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    NestUsageMarker nestUsageMarker = new NestUsageMarker(classUsageMarker);

    // Act
    nestUsageMarker.visitProgramClass(new ProgramClass());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }
}
