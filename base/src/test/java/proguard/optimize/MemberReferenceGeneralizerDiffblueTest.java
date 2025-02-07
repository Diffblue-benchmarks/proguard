package proguard.optimize;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.visitor.ClassVisitor;
import proguard.util.SimpleProcessable;

class MemberReferenceGeneralizerDiffblueTest {
  /**
   * Test {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link CodeAttributeEditor#isModified(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls isModified(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsIsModified() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    memberReferenceGeneralizer.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link CodeAttributeEditor#isModified(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls isModified(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsIsModified2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    memberReferenceGeneralizer.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -74, 1));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link FieldrefConstant#FieldrefConstant()}.</li>
   *   <li>Then calls {@link LibraryClass#getName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given 'Name'; when FieldrefConstant(); then calls getName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_givenName_whenFieldrefConstant_thenCallsGetName() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
    verify(clazz).getType(eq(0));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link LibraryClass}.</li>
   *   <li>Then calls {@link RefConstant#getName(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given 'Name'; when LibraryClass; then calls getName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_givenName_whenLibraryClass_thenCallsGetName() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    when(refConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(refConstant.getType(Mockito.<Clazz>any())).thenReturn("Type");

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).getName(isA(Clazz.class));
    verify(refConstant).getType(isA(Clazz.class));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given one; then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_givenOne_thenCallsConstantPoolEntryAccept() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getProcessingFlags()).thenReturn(1);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(clazz).getProcessingFlags();
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link LibraryClass} {@link SimpleProcessable#getProcessingFlags()} return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given zero; when LibraryClass getProcessingFlags() return zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_givenZero_whenLibraryClassGetProcessingFlagsReturnZero() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(0);
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    memberReferenceGeneralizer.visitAnyRefConstant(clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(clazz).getProcessingFlags();
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    memberReferenceGeneralizer.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then calls {@link ClassConstant#referencedClassAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls referencedClassAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.MemberReferenceGeneralizer.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenCallsReferencedClassAccept() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    memberReferenceGeneralizer.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}.
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.MemberReferenceGeneralizer.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(-74);
    when(clazz.getSuperClass()).thenReturn(new LibraryClass(-74, "This Class Name", "Super Class Name"));
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getSuperClass();
    verify(clazz).getProcessingFlags();
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Given {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); given LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.MemberReferenceGeneralizer.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_givenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(-74);
    when(clazz.getSuperClass()).thenReturn(libraryClass);
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getSuperClass();
    verify(clazz).getProcessingFlags();
  }

  /**
   * Test {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>When {@link LibraryClass} {@link SimpleProcessable#getProcessingFlags()} return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberReferenceGeneralizer#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); given one; when LibraryClass getProcessingFlags() return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.MemberReferenceGeneralizer.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_givenOne_whenLibraryClassGetProcessingFlagsReturnOne() {
    // Arrange
    MemberReferenceGeneralizer memberReferenceGeneralizer = new MemberReferenceGeneralizer(true, true,
        new CodeAttributeEditor());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingFlags()).thenReturn(1);
    when(clazz.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));

    // Act
    memberReferenceGeneralizer.visitAnyClass(clazz);

    // Assert
    verify(clazz).findField(isNull(), isNull());
    verify(clazz).getProcessingFlags();
  }
}
