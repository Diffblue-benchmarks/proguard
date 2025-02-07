package proguard.optimize.evaluation;

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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class SimpleEnumUseSimplifierDiffblueTest {
  /**
   * Test {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link Clazz#getName()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls getName()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsGetName() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    simpleEnumUseSimplifier.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz).getName();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsConstantPoolEntryAccept() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -67, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}.
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls isSimpleEnum()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls isSimpleEnum()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  @DisplayName("Test visitParameter(Clazz, Member, int, int, int, int, String, Clazz); given ClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitParameter(proguard.classfile.Clazz, proguard.classfile.Member, int, int, int, int, java.lang.String, proguard.classfile.Clazz)"})
  void testVisitParameter_givenClassOptimizationInfo() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  @DisplayName("Test visitParameter(Clazz, Member, int, int, int, int, String, Clazz); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.evaluation.SimpleEnumUseSimplifier.visitParameter(proguard.classfile.Clazz, proguard.classfile.Member, int, int, int, int, java.lang.String, proguard.classfile.Clazz)"})
  void testVisitParameter_givenProgramClassOptimizationInfo() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(referencedClass).getProcessingInfo();
  }
}
