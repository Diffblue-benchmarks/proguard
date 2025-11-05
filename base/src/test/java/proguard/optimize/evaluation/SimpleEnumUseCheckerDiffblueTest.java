package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.BootstrapMethodInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class SimpleEnumUseCheckerDiffblueTest {
  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(1);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumUseChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).getAccessFlags();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getAccessFlags()).thenReturn(8192);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumUseChecker.visitProgramClass(programClass);

    // Assert
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).getAccessFlags();
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute = mock(BootstrapMethodsAttribute.class);
    doNothing().when(bootstrapMethodsAttribute)
        .bootstrapMethodEntriesAccept(Mockito.<Clazz>any(), Mockito.<BootstrapMethodInfoVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    verify(bootstrapMethodsAttribute).bootstrapMethodEntriesAccept(isA(Clazz.class),
        isA(BootstrapMethodInfoVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    doNothing().when(partialEvaluator)
        .visitCodeAttribute(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any());
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker(partialEvaluator);
    LibraryClass clazz = mock(LibraryClass.class);
    ProgramMethod method = new ProgramMethod();

    // Act
    simpleEnumUseChecker.visitCodeAttribute(clazz, method, new CodeAttribute());

    // Assert
    verify(partialEvaluator).visitCodeAttribute(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo(1, 3, new int[]{1, 0, 1, 0}));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(anyInt(), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo3() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    BootstrapMethodInfo bootstrapMethodInfo = mock(BootstrapMethodInfo.class);
    doNothing().when(bootstrapMethodInfo).methodArgumentsAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    doNothing().when(bootstrapMethodInfo).methodHandleAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitBootstrapMethodInfo(clazz, bootstrapMethodInfo);

    // Assert
    verify(bootstrapMethodInfo).methodArgumentsAccept(isA(Clazz.class), isA(ConstantVisitor.class));
    verify(bootstrapMethodInfo).methodHandleAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;
    stringConstant.referencedMember = null;

    // Act
    simpleEnumUseChecker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setSimpleEnum(anyBoolean());

    ProgramClass programClass = new ProgramClass();
    programClass.setProcessingInfo(programClassOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = programClass;
    stringConstant.referencedMember = null;

    // Act
    simpleEnumUseChecker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(programClassOptimizationInfo).setSimpleEnum(eq(false));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    MethodHandleConstant methodHandleConstant = mock(MethodHandleConstant.class);
    doNothing().when(methodHandleConstant).referenceAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    verify(methodHandleConstant).referenceAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = mock(MethodTypeConstant.class);
    doNothing().when(methodTypeConstant).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    verify(methodTypeConstant).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();
    methodTypeConstant.referencedClasses = new Clazz[]{libraryClass};

    // Act
    simpleEnumUseChecker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    doNothing().when(refConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    FieldrefConstant refConstant = new FieldrefConstant();
    refConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseChecker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    simpleEnumUseChecker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseChecker.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker(partialEvaluator);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod();
    method.referencedClasses = null;
    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) -62;

    // Act
    simpleEnumUseChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(2));
    verify(tracedStack, atLeast(1)).getTop(eq(0));
    verify(arrayReferenceValue, atLeast(1)).getReferencedClass();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction2() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker(partialEvaluator);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod();
    method.referencedClasses = null;
    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) 'S';

    // Act
    simpleEnumUseChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(2));
    verify(tracedStack, atLeast(1)).getTop(anyInt());
    verify(arrayReferenceValue, atLeast(1)).getReferencedClass();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    clazz.addSubClass(new LibraryClass());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ConstantInstruction constantInstruction = new ConstantInstruction((byte) 'A', 1);

    constantInstruction.opcode = (byte) -77;

    // Act
    simpleEnumUseChecker.visitConstantInstruction(clazz, method, codeAttribute, 2, constantInstruction);

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    clazz.addSubClass(new LibraryClass());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ConstantInstruction constantInstruction = new ConstantInstruction((byte) 'A', 1);

    constantInstruction.opcode = (byte) -73;

    // Act
    simpleEnumUseChecker.visitConstantInstruction(clazz, method, codeAttribute, 2, constantInstruction);

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), Mockito.<ConstantVisitor>any());
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1,
        new ClassOptimizationInfo());

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert that nothing has changed
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) any());
    constant.addProcessingFlags(2, 1, 2, 1);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{constant}, 1, 1, 1, "Feature Name", 1,
        new ProgramClassOptimizationInfo());

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert that nothing has changed
    verify(constant).addProcessingFlags((int[]) any());
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getString(anyInt())).thenReturn("valueOf");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getName();
    verify(programClass, atLeast(1)).getString(eq(0));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod5() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getDescriptor(isA(Clazz.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod6() {
    // Arrange
    SimpleEnumUseChecker simpleEnumUseChecker = new SimpleEnumUseChecker();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("valueOf");

    // Act
    simpleEnumUseChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getName();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(classOptimizationInfo).isSimpleEnum();
    verify(programClass, atLeast(1)).getProcessingInfo();
  }
}
