package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.ConstantValueAttribute;
import proguard.classfile.attribute.DeprecatedAttribute;
import proguard.classfile.attribute.EnclosingMethodAttribute;
import proguard.classfile.attribute.ExceptionInfo;
import proguard.classfile.attribute.ExceptionsAttribute;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.LineNumberTableAttribute;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.MethodParametersAttribute;
import proguard.classfile.attribute.NestHostAttribute;
import proguard.classfile.attribute.NestMembersAttribute;
import proguard.classfile.attribute.ParameterInfo;
import proguard.classfile.attribute.PermittedSubclassesAttribute;
import proguard.classfile.attribute.RecordAttribute;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.SourceDebugExtensionAttribute;
import proguard.classfile.attribute.SourceDirAttribute;
import proguard.classfile.attribute.SourceFileAttribute;
import proguard.classfile.attribute.SyntheticAttribute;
import proguard.classfile.attribute.UnknownAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ClassElementValue;
import proguard.classfile.attribute.annotation.ConstantElementValue;
import proguard.classfile.attribute.annotation.EnumConstantElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.ElementValueVisitor;
import proguard.classfile.attribute.module.ExportsInfo;
import proguard.classfile.attribute.module.ModuleAttribute;
import proguard.classfile.attribute.module.ModuleMainClassAttribute;
import proguard.classfile.attribute.module.ModulePackagesAttribute;
import proguard.classfile.attribute.module.OpensInfo;
import proguard.classfile.attribute.module.ProvidesInfo;
import proguard.classfile.attribute.module.RequiresInfo;
import proguard.classfile.attribute.preverification.FullFrame;
import proguard.classfile.attribute.preverification.ObjectType;
import proguard.classfile.attribute.preverification.SameOneFrame;
import proguard.classfile.attribute.preverification.StackMapAttribute;
import proguard.classfile.attribute.preverification.StackMapFrame;
import proguard.classfile.attribute.preverification.StackMapTableAttribute;
import proguard.classfile.attribute.preverification.VerificationType;
import proguard.classfile.attribute.preverification.VerificationTypeFactory;
import proguard.classfile.attribute.preverification.visitor.VerificationTypeVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.InnerClassesInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.DoubleConstant;
import proguard.classfile.constant.DynamicConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.FloatConstant;
import proguard.classfile.constant.IntegerConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.LongConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.constant.MethodTypeConstant;
import proguard.classfile.constant.ModuleConstant;
import proguard.classfile.constant.NameAndTypeConstant;
import proguard.classfile.constant.PackageConstant;
import proguard.classfile.constant.PrimitiveArrayConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.Utf8Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.kotlin.KotlinAnnotatable;
import proguard.classfile.kotlin.KotlinAnnotation;
import proguard.classfile.kotlin.KotlinClassKindMetadata;
import proguard.classfile.kotlin.KotlinConstructorMetadata;
import proguard.classfile.kotlin.KotlinDeclarationContainerMetadata;
import proguard.classfile.kotlin.KotlinEffectExpressionMetadata;
import proguard.classfile.kotlin.KotlinEffectInvocationKind;
import proguard.classfile.kotlin.KotlinEffectMetadata;
import proguard.classfile.kotlin.KotlinEffectType;
import proguard.classfile.kotlin.KotlinFileFacadeKindMetadata;
import proguard.classfile.kotlin.KotlinFunctionMetadata;
import proguard.classfile.kotlin.KotlinMetadata;
import proguard.classfile.kotlin.KotlinPropertyMetadata;
import proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata;
import proguard.classfile.kotlin.KotlinTypeAliasMetadata;
import proguard.classfile.kotlin.KotlinValueParameterMetadata;
import proguard.classfile.kotlin.KotlinVersionRequirementMetadata;
import proguard.classfile.kotlin.flags.KotlinConstructorFlags;
import proguard.classfile.kotlin.flags.KotlinFunctionFlags;
import proguard.classfile.kotlin.flags.KotlinModalityFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyAccessorFlags;
import proguard.classfile.kotlin.flags.KotlinPropertyFlags;
import proguard.classfile.kotlin.flags.KotlinVisibilityFlags;
import proguard.classfile.kotlin.visitor.KotlinFunctionVisitor;
import proguard.classfile.kotlin.visitor.KotlinMetadataVisitor;
import proguard.classfile.kotlin.visitor.KotlinPropertyVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeAliasVisitor;
import proguard.classfile.kotlin.visitor.KotlinTypeVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.ClassRenamer;
import proguard.shrink.ClassUsageMarker.KotlinUsageMarker;
import proguard.shrink.ClassUsageMarker.MarkingMode;
import proguard.testutils.cpa.NamedField;
import proguard.testutils.cpa.NamedMember;
import proguard.util.Processable;

class ClassUsageMarkerDiffblueTest {
  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitAnyAnnotation(Clazz, KotlinAnnotatable, KotlinAnnotation)}.
   * <ul>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitAnyAnnotation(Clazz, KotlinAnnotatable, KotlinAnnotation)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitAnyAnnotation(Clazz, KotlinAnnotatable, KotlinAnnotation); then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitAnyAnnotation(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinAnnotatable, proguard.classfile.kotlin.KotlinAnnotation)"})
  void testKotlinUsageMarkerVisitAnyAnnotation_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinAnnotatable annotatable = mock(KotlinAnnotatable.class);

    // Act
    kotlinUsageMarker.visitAnyAnnotation(clazz, annotatable, new KotlinAnnotation("Class Name"));

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitAnyEffectExpression(Clazz, KotlinEffectMetadata, KotlinEffectExpressionMetadata)}.
   * <ul>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitAnyEffectExpression(Clazz, KotlinEffectMetadata, KotlinEffectExpressionMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitAnyEffectExpression(Clazz, KotlinEffectMetadata, KotlinEffectExpressionMetadata); then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitAnyEffectExpression(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinEffectMetadata, proguard.classfile.kotlin.KotlinEffectExpressionMetadata)"})
  void testKotlinUsageMarkerVisitAnyEffectExpression_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinEffectMetadata kotlinEffectMetadata = new KotlinEffectMetadata(KotlinEffectType.RETURNS_CONSTANT,
        KotlinEffectInvocationKind.AT_MOST_ONCE);

    // Act
    kotlinUsageMarker.visitAnyEffectExpression(clazz, kotlinEffectMetadata, new KotlinEffectExpressionMetadata());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitAnyVersionRequirement(Clazz, KotlinVersionRequirementMetadata)}.
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitAnyVersionRequirement(Clazz, KotlinVersionRequirementMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitAnyVersionRequirement(Clazz, KotlinVersionRequirementMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitAnyVersionRequirement(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinVersionRequirementMetadata)"})
  void testKotlinUsageMarkerVisitAnyVersionRequirement() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    KotlinUsageMarker kotlinUsageMarker = (new ShortestClassUsageMarker(usageMarker,
        "Just cause")).new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinVersionRequirementMetadata kotlinVersionRequirementMetadata = new KotlinVersionRequirementMetadata();

    // Act
    kotlinUsageMarker.visitAnyVersionRequirement(clazz, kotlinVersionRequirementMetadata);

    // Assert
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, kotlinVersionRequirementMetadata.getProcessingInfo());
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinValueParameterMetadata#typeAccept(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata); then calls typeAccept(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitConstructorValParameter(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinClassKindMetadata, proguard.classfile.kotlin.KotlinConstructorMetadata, proguard.classfile.kotlin.KotlinValueParameterMetadata)"})
  void testKotlinUsageMarkerVisitConstructorValParameter_thenCallsTypeAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinConstructorMetadata kotlinConstructorMetadata = new KotlinConstructorMetadata(
        new KotlinConstructorFlags(visibility));
    KotlinValueParameterMetadata kotlinValueParameterMetadata = mock(KotlinValueParameterMetadata.class);
    doNothing().when(kotlinValueParameterMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinClassKindMetadata>any(),
            Mockito.<KotlinConstructorMetadata>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinUsageMarker.visitConstructorValParameter(clazz, kotlinClassKindMetadata, kotlinConstructorMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata).typeAccept(isA(Clazz.class), isA(KotlinClassKindMetadata.class),
        isA(KotlinConstructorMetadata.class), isA(KotlinTypeVisitor.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}.
   * <ul>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata); then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitConstructor(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinClassKindMetadata, proguard.classfile.kotlin.KotlinConstructorMetadata)"})
  void testKotlinUsageMarkerVisitConstructor_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinClassKindMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs",
        "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;

    // Act
    kotlinUsageMarker.visitConstructor(clazz, kotlinClassKindMetadata,
        new KotlinConstructorMetadata(new KotlinConstructorFlags(visibility)));

    // Assert
    verify(classUsageMarker, atLeast(1)).isUsed(Mockito.<Processable>any());
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitFunctionValParameter(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinValueParameterMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinValueParameterMetadata#typeAccept(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitFunctionValParameter(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitFunctionValParameter(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinValueParameterMetadata); then calls typeAccept(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitFunctionValParameter(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinMetadata, proguard.classfile.kotlin.KotlinFunctionMetadata, proguard.classfile.kotlin.KotlinValueParameterMetadata)"})
  void testKotlinUsageMarkerVisitFunctionValParameter_thenCallsTypeAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinFunctionMetadata kotlinFunctionMetadata = new KotlinFunctionMetadata(
        new KotlinFunctionFlags(visibility, modality), "Name");

    KotlinValueParameterMetadata kotlinValueParameterMetadata = mock(KotlinValueParameterMetadata.class);
    doNothing().when(kotlinValueParameterMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinMetadata>any(), Mockito.<KotlinFunctionMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinUsageMarker.visitFunctionValParameter(clazz, kotlinMetadata, kotlinFunctionMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata).typeAccept(isA(Clazz.class), isA(KotlinMetadata.class),
        isA(KotlinFunctionMetadata.class), isA(KotlinTypeVisitor.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitFunction(Clazz, KotlinDeclarationContainerMetadata, KotlinFunctionMetadata)}.
   * <ul>
   *   <li>When {@link KotlinClassKindMetadata}.</li>
   *   <li>Then calls {@link ClassUsageMarker#isUsed(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitFunction(Clazz, KotlinDeclarationContainerMetadata, KotlinFunctionMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitFunction(Clazz, KotlinDeclarationContainerMetadata, KotlinFunctionMetadata); when KotlinClassKindMetadata; then calls isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitFunction(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinFunctionMetadata)"})
  void testKotlinUsageMarkerVisitFunction_whenKotlinClassKindMetadata_thenCallsIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = mock(KotlinClassKindMetadata.class);
    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;

    // Act
    kotlinUsageMarker.visitFunction(clazz, kotlinDeclarationContainerMetadata,
        new KotlinFunctionMetadata(new KotlinFunctionFlags(visibility, modality), "Name"));

    // Assert
    verify(classUsageMarker, atLeast(1)).isUsed(Mockito.<Processable>any());
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}.
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitKotlinDeclarationContainerMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata)"})
  void testKotlinUsageMarkerVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinDeclarationContainerMetadata kotlinDeclarationContainerMetadata = mock(
        KotlinDeclarationContainerMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinUsageMarker.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).delegatedPropertiesAccept(isA(Clazz.class),
        isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinDeclarationContainerMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
    verify(classUsageMarker, atLeast(1)).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}.
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitKotlinFileFacadeMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinFileFacadeKindMetadata)"})
  void testKotlinUsageMarkerVisitKotlinFileFacadeMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinFileFacadeKindMetadata kotlinFileFacadeKindMetadata = mock(KotlinFileFacadeKindMetadata.class);
    doNothing().when(kotlinFileFacadeKindMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinFileFacadeKindMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    kotlinUsageMarker.visitKotlinFileFacadeMetadata(clazz, kotlinFileFacadeKindMetadata);

    // Assert
    verify(kotlinFileFacadeKindMetadata).delegatedPropertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinFileFacadeKindMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinFileFacadeKindMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
    verify(classUsageMarker, atLeast(1)).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinSyntheticClassKindMetadata#functionsAccept(Clazz, KotlinFunctionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata); then calls functionsAccept(Clazz, KotlinFunctionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitKotlinSyntheticClassMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinSyntheticClassKindMetadata)"})
  void testKotlinUsageMarkerVisitKotlinSyntheticClassMetadata_thenCallsFunctionsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUsed(Mockito.<Processable>any());
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinSyntheticClassKindMetadata kotlinSyntheticClassKindMetadata = mock(KotlinSyntheticClassKindMetadata.class);
    doNothing().when(kotlinSyntheticClassKindMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());

    // Act
    kotlinUsageMarker.visitKotlinSyntheticClassMetadata(clazz, kotlinSyntheticClassKindMetadata);

    // Assert
    verify(kotlinSyntheticClassKindMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(classUsageMarker).markAsUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinValueParameterMetadata#typeAccept(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata); then calls typeAccept(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitPropertyValParameter(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinPropertyMetadata, proguard.classfile.kotlin.KotlinValueParameterMetadata)"})
  void testKotlinUsageMarkerVisitPropertyValParameter_thenCallsTypeAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinVisibilityFlags visibility = new KotlinVisibilityFlags();
    visibility.isInternal = true;
    visibility.isLocal = true;
    visibility.isPrivate = true;
    visibility.isPrivateToThis = true;
    visibility.isProtected = true;
    visibility.isPublic = true;
    KotlinModalityFlags modality = new KotlinModalityFlags();
    modality.isAbstract = true;
    modality.isFinal = true;
    modality.isOpen = true;
    modality.isSealed = true;
    KotlinPropertyFlags flags = new KotlinPropertyFlags(visibility, modality);

    KotlinVisibilityFlags visibility2 = new KotlinVisibilityFlags();
    visibility2.isInternal = true;
    visibility2.isLocal = true;
    visibility2.isPrivate = true;
    visibility2.isPrivateToThis = true;
    visibility2.isProtected = true;
    visibility2.isPublic = true;
    KotlinModalityFlags modality2 = new KotlinModalityFlags();
    modality2.isAbstract = true;
    modality2.isFinal = true;
    modality2.isOpen = true;
    modality2.isSealed = true;
    KotlinPropertyAccessorFlags getterFlags = new KotlinPropertyAccessorFlags(visibility2, modality2);

    KotlinVisibilityFlags visibility3 = new KotlinVisibilityFlags();
    visibility3.isInternal = true;
    visibility3.isLocal = true;
    visibility3.isPrivate = true;
    visibility3.isPrivateToThis = true;
    visibility3.isProtected = true;
    visibility3.isPublic = true;
    KotlinModalityFlags modality3 = new KotlinModalityFlags();
    modality3.isAbstract = true;
    modality3.isFinal = true;
    modality3.isOpen = true;
    modality3.isSealed = true;
    KotlinPropertyMetadata kotlinPropertyMetadata = new KotlinPropertyMetadata(flags, "Name", getterFlags,
        new KotlinPropertyAccessorFlags(visibility3, modality3));

    KotlinValueParameterMetadata kotlinValueParameterMetadata = mock(KotlinValueParameterMetadata.class);
    doNothing().when(kotlinValueParameterMetadata)
        .typeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinPropertyMetadata>any(), Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinUsageMarker.visitPropertyValParameter(clazz, kotlinDeclarationContainerMetadata, kotlinPropertyMetadata,
        kotlinValueParameterMetadata);

    // Assert
    verify(kotlinValueParameterMetadata).typeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinPropertyMetadata.class), isA(KotlinTypeVisitor.class));
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Test KotlinUsageMarker {@link KotlinUsageMarker#visitTypeAlias(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeAliasMetadata)}.
   * <ul>
   *   <li>When {@link Clazz}.</li>
   *   <li>Then calls {@link KotlinTypeAliasMetadata#expandedTypeAccept(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link KotlinUsageMarker#visitTypeAlias(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeAliasMetadata)}
   */
  @Test
  @DisplayName("Test KotlinUsageMarker visitTypeAlias(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeAliasMetadata); when Clazz; then calls expandedTypeAccept(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker$KotlinUsageMarker.visitTypeAlias(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata, proguard.classfile.kotlin.KotlinTypeAliasMetadata)"})
  void testKotlinUsageMarkerVisitTypeAlias_whenClazz_thenCallsExpandedTypeAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    Clazz clazz = mock(Clazz.class);
    KotlinClassKindMetadata kotlinDeclarationContainerMetadata = new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1,
        "Xs", "Pn");

    KotlinTypeAliasMetadata kotlinTypeAliasMetadata = mock(KotlinTypeAliasMetadata.class);
    doNothing().when(kotlinTypeAliasMetadata)
        .expandedTypeAccept(Mockito.<Clazz>any(), Mockito.<KotlinDeclarationContainerMetadata>any(),
            Mockito.<KotlinTypeVisitor>any());

    // Act
    kotlinUsageMarker.visitTypeAlias(clazz, kotlinDeclarationContainerMetadata, kotlinTypeAliasMetadata);

    // Assert
    verify(kotlinTypeAliasMetadata).expandedTypeAccept(isA(Clazz.class), isA(KotlinDeclarationContainerMetadata.class),
        isA(KotlinTypeVisitor.class));
    verify(classUsageMarker, atLeast(1)).isUsed(Mockito.<Processable>any());
  }

  /**
   * Test {@link ClassUsageMarker#ClassUsageMarker()}.
   * <p>
   * Method under test: {@link ClassUsageMarker#ClassUsageMarker()}
   */
  @Test
  @DisplayName("Test new ClassUsageMarker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.<init>()"})
  void testNewClassUsageMarker() {
    // Arrange, Act and Assert
    assertNull((new ClassUsageMarker()).getExtraConstantVisitor());
  }

  /**
   * Test {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker)}
   */
  @Test
  @DisplayName("Test new ClassUsageMarker(SimpleUsageMarker)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.<init>(proguard.shrink.SimpleUsageMarker)"})
  void testNewClassUsageMarker2() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();

    // Act
    ClassUsageMarker actualClassUsageMarker = new ClassUsageMarker(usageMarker);

    // Assert
    assertNull(actualClassUsageMarker.getExtraConstantVisitor());
    assertSame(usageMarker, actualClassUsageMarker.getUsageMarker());
  }

  /**
   * Test {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker, MarkingMode)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker, MarkingMode)}
   */
  @Test
  @DisplayName("Test new ClassUsageMarker(SimpleUsageMarker, MarkingMode)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.<init>(proguard.shrink.SimpleUsageMarker, proguard.shrink.ClassUsageMarker$MarkingMode)"})
  void testNewClassUsageMarker3() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();

    // Act
    ClassUsageMarker actualClassUsageMarker = new ClassUsageMarker(usageMarker, MarkingMode.SHRINKING);

    // Assert
    assertNull(actualClassUsageMarker.getExtraConstantVisitor());
    assertSame(usageMarker, actualClassUsageMarker.getUsageMarker());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClassUsageMarker#setExtraConstantVisitor(ConstantVisitor)}
   *   <li>{@link ClassUsageMarker#setExtraMethodVisitor(MemberVisitor)}
   *   <li>{@link ClassUsageMarker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   *   <li>{@link ClassUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   *   <li>{@link ClassUsageMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   *   <li>{@link ClassUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   *   <li>{@link ClassUsageMarker#visitAnyStackMapFrame(Clazz, Method, CodeAttribute, int, StackMapFrame)}
   *   <li>{@link ClassUsageMarker#visitAnyVerificationType(Clazz, Method, CodeAttribute, int, VerificationType)}
   *   <li>{@link ClassUsageMarker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   *   <li>{@link ClassUsageMarker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   *   <li>{@link ClassUsageMarker#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   *   <li>{@link ClassUsageMarker#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   *   <li>{@link ClassUsageMarker#visitLibraryField(LibraryClass, LibraryField)}
   *   <li>{@link ClassUsageMarker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   *   <li>{@link ClassUsageMarker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   *   <li>{@link ClassUsageMarker#visitNestHostAttribute(Clazz, NestHostAttribute)}
   *   <li>{@link ClassUsageMarker#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   *   <li>{@link ClassUsageMarker#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   *   <li>{@link ClassUsageMarker#getExtraConstantVisitor()}
   *   <li>{@link ClassUsageMarker#getUsageMarker()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.constant.visitor.ConstantVisitor proguard.shrink.ClassUsageMarker.getExtraConstantVisitor()",
      "proguard.shrink.SimpleUsageMarker proguard.shrink.ClassUsageMarker.getUsageMarker()",
      "void proguard.shrink.ClassUsageMarker.setExtraConstantVisitor(proguard.classfile.constant.visitor.ConstantVisitor)",
      "void proguard.shrink.ClassUsageMarker.setExtraMethodVisitor(proguard.classfile.visitor.MemberVisitor)",
      "void proguard.shrink.ClassUsageMarker.visitAnnotationElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.AnnotationElementValue)",
      "void proguard.shrink.ClassUsageMarker.visitAnyAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.AnnotationsAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitAnyInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)",
      "void proguard.shrink.ClassUsageMarker.visitAnyParameterAnnotationsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitAnyStackMapFrame(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.attribute.preverification.StackMapFrame)",
      "void proguard.shrink.ClassUsageMarker.visitAnyVerificationType(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.attribute.preverification.VerificationType)",
      "void proguard.shrink.ClassUsageMarker.visitArrayElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ArrayElementValue)",
      "void proguard.shrink.ClassUsageMarker.visitBootstrapMethodsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodsAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitClassElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ClassElementValue)",
      "void proguard.shrink.ClassUsageMarker.visitConstantElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.ConstantElementValue)",
      "void proguard.shrink.ClassUsageMarker.visitLibraryField(proguard.classfile.LibraryClass, proguard.classfile.LibraryField)",
      "void proguard.shrink.ClassUsageMarker.visitLocalVariableTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTableAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitLocalVariableTypeTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LocalVariableTypeTableAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitNestHostAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestHostAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitNestMembersAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.NestMembersAttribute)",
      "void proguard.shrink.ClassUsageMarker.visitPermittedSubclassesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.PermittedSubclassesAttribute)"})
  void testGettersAndSetters() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassRenamer extraConstantVisitor = new ClassRenamer();

    // Act
    classUsageMarker.setExtraConstantVisitor(extraConstantVisitor);
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    classUsageMarker.visitAnnotationElementValue(clazz, annotation, new AnnotationElementValue());
    LibraryClass clazz2 = new LibraryClass();
    classUsageMarker.visitAnyAnnotationsAttribute(clazz2, new RuntimeInvisibleAnnotationsAttribute());
    LibraryClass clazz3 = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    classUsageMarker.visitAnyInstruction(clazz3, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1));
    LibraryClass clazz4 = new LibraryClass();
    LibraryMethod method2 = new LibraryMethod(1, "Name", "Descriptor");

    classUsageMarker.visitAnyParameterAnnotationsAttribute(clazz4, method2,
        new RuntimeInvisibleParameterAnnotationsAttribute());
    LibraryClass clazz5 = new LibraryClass();
    LibraryMethod method3 = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute2 = new CodeAttribute(1);
    classUsageMarker.visitAnyStackMapFrame(clazz5, method3, codeAttribute2, 2, new FullFrame());
    LibraryClass clazz6 = new LibraryClass();
    LibraryMethod method4 = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute3 = new CodeAttribute(1);
    classUsageMarker.visitAnyVerificationType(clazz6, method4, codeAttribute3, 2,
        VerificationTypeFactory.createDoubleType());
    LibraryClass clazz7 = new LibraryClass();
    Annotation annotation2 = new Annotation();
    classUsageMarker.visitArrayElementValue(clazz7, annotation2, new ArrayElementValue());
    LibraryClass clazz8 = new LibraryClass();
    classUsageMarker.visitBootstrapMethodsAttribute(clazz8, new BootstrapMethodsAttribute());
    LibraryClass clazz9 = new LibraryClass();
    Annotation annotation3 = new Annotation();
    classUsageMarker.visitClassElementValue(clazz9, annotation3, new ClassElementValue(1, 1));
    LibraryClass clazz10 = new LibraryClass();
    Annotation annotation4 = new Annotation();
    classUsageMarker.visitConstantElementValue(clazz10, annotation4, new ConstantElementValue('A'));
    LibraryClass programClass = new LibraryClass();
    classUsageMarker.visitLibraryField(programClass, new LibraryField(1, "Name", "Descriptor"));
    LibraryClass clazz11 = new LibraryClass();
    LibraryMethod method5 = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute4 = new CodeAttribute(1);
    classUsageMarker.visitLocalVariableTableAttribute(clazz11, method5, codeAttribute4,
        new LocalVariableTableAttribute());
    LibraryClass clazz12 = new LibraryClass();
    LibraryMethod method6 = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute5 = new CodeAttribute(1);
    classUsageMarker.visitLocalVariableTypeTableAttribute(clazz12, method6, codeAttribute5,
        new LocalVariableTypeTableAttribute());
    LibraryClass clazz13 = new LibraryClass();
    classUsageMarker.visitNestHostAttribute(clazz13, new NestHostAttribute(1, 1));
    LibraryClass clazz14 = new LibraryClass();
    classUsageMarker.visitNestMembersAttribute(clazz14, new NestMembersAttribute());
    LibraryClass clazz15 = new LibraryClass();
    classUsageMarker.visitPermittedSubclassesAttribute(clazz15, new PermittedSubclassesAttribute());
    ConstantVisitor actualExtraConstantVisitor = classUsageMarker.getExtraConstantVisitor();
    classUsageMarker.getUsageMarker();

    // Assert
    assertTrue(actualExtraConstantVisitor instanceof ClassRenamer);
    assertSame(extraConstantVisitor, actualExtraConstantVisitor);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> classUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenFirstElementClassConstant() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 0, 0);

    // Act
    classUsageMarker.visitProgramClass(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[0] instanceof ClassConstant);
    assertEquals(1, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{doubleConstant, new ClassConstant()}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{dynamicConstant, new ClassConstant()}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{fieldrefConstant, new ClassConstant()}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{floatConstant, new ClassConstant()}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{integerConstant, new ClassConstant()}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  @DisplayName("Test markProgramClassBody(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markProgramClassBody(proguard.classfile.ProgramClass)"})
  void testMarkProgramClassBody6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{interfaceMethodrefConstant, new ClassConstant()},
        1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    classUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new SimpleUsageMarker(), MarkingMode.MAIN_DEX_TRACING);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link LibraryClass#methodsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); given 'Processing Info'; then calls methodsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_givenProcessingInfo_thenCallsMethodsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).methodsAccept(isA(MemberVisitor.class));
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#methodsAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then calls methodsAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.visitLibraryClass(proguard.classfile.LibraryClass)"})
  void testVisitLibraryClass_thenCallsMethodsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryClass(libraryClass);

    // Assert
    verify(libraryClass).methodsAccept(isA(MemberVisitor.class));
    verify(libraryClass, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isNull());
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyKotlinMetadata(Clazz, KotlinMetadata)}.
   * <ul>
   *   <li>When {@link KotlinMetadata} {@link KotlinMetadata#accept(Clazz, KotlinMetadataVisitor)} does nothing.</li>
   *   <li>Then calls {@link KotlinMetadata#accept(Clazz, KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyKotlinMetadata(Clazz, KotlinMetadata)}
   */
  @Test
  @DisplayName("Test visitAnyKotlinMetadata(Clazz, KotlinMetadata); when KotlinMetadata accept(Clazz, KotlinMetadataVisitor) does nothing; then calls accept(Clazz, KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyKotlinMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinMetadata)"})
  void testVisitAnyKotlinMetadata_whenKotlinMetadataAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    KotlinMetadata kotlinMetadata = mock(KotlinMetadata.class);
    doNothing().when(kotlinMetadata).accept(Mockito.<Clazz>any(), Mockito.<KotlinMetadataVisitor>any());

    // Act
    shortestClassUsageMarker.visitAnyKotlinMetadata(clazz, kotlinMetadata);

    // Assert
    verify(kotlinMetadata).accept(isA(Clazz.class), isA(KotlinMetadataVisitor.class));
    verify(usageMarker).isUsed(isA(Processable.class));
    verify(usageMarker).setCurrentUsageMark(isA(ShortestUsageMark.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}.
   * <ul>
   *   <li>Then calls {@link KotlinDeclarationContainerMetadata#delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  @DisplayName("Test visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata); then calls delegatedPropertiesAccept(Clazz, KotlinPropertyVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitKotlinDeclarationContainerMetadata(proguard.classfile.Clazz, proguard.classfile.kotlin.KotlinDeclarationContainerMetadata)"})
  void testVisitKotlinDeclarationContainerMetadata_thenCallsDelegatedPropertiesAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    KotlinDeclarationContainerMetadata kotlinDeclarationContainerMetadata = mock(
        KotlinDeclarationContainerMetadata.class);
    doNothing().when(kotlinDeclarationContainerMetadata)
        .accept(Mockito.<Clazz>any(), Mockito.<KotlinMetadataVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .delegatedPropertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .functionsAccept(Mockito.<Clazz>any(), Mockito.<KotlinFunctionVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .propertiesAccept(Mockito.<Clazz>any(), Mockito.<KotlinPropertyVisitor>any());
    doNothing().when(kotlinDeclarationContainerMetadata)
        .typeAliasesAccept(Mockito.<Clazz>any(), Mockito.<KotlinTypeAliasVisitor>any());

    // Act
    shortestClassUsageMarker.visitKotlinDeclarationContainerMetadata(clazz, kotlinDeclarationContainerMetadata);

    // Assert
    verify(kotlinDeclarationContainerMetadata).delegatedPropertiesAccept(isA(Clazz.class),
        isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).functionsAccept(isA(Clazz.class), isA(KotlinFunctionVisitor.class));
    verify(kotlinDeclarationContainerMetadata).propertiesAccept(isA(Clazz.class), isA(KotlinPropertyVisitor.class));
    verify(kotlinDeclarationContainerMetadata).typeAliasesAccept(isA(Clazz.class), isA(KotlinTypeAliasVisitor.class));
    verify(kotlinDeclarationContainerMetadata).accept(isA(Clazz.class), isA(KotlinMetadataVisitor.class));
    verify(usageMarker, atLeast(1)).isUsed(Mockito.<Processable>any());
    verify(usageMarker).setCurrentUsageMark(isA(ShortestUsageMark.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then {@link ProgramField#ProgramField()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then ProgramField() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_thenProgramFieldProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = new ProgramField();

    // Act
    shortestClassUsageMarker.visitProgramField(programClass, programField);

    // Assert
    Object processingInfo = programField.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertFalse(((ShortestUsageMark) processingInfo).isCertain());
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    when(programClass.getString(anyInt())).thenReturn("String");
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, new ProgramMethod(5, 1, 1, new Clazz[]{new LibraryClass()}));

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).getString(eq(1));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doThrow(new UnsupportedOperationException("<init>")).when(programMethod)
        .attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classUsageMarker.visitProgramMethod(programClass, programMethod));
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));

    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doThrow(new UnsupportedOperationException("<init>")).when(programMethod)
        .accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classUsageMarker.visitProgramMethod(programClass, programMethod));
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod6() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    doThrow(new UnsupportedOperationException("is invoked by    ")).when(usageMarker)
        .markAsUsed(Mockito.<Processable>any());
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    when(usageMarker.shouldBeMarkedAsUsed(Mockito.<Processable>any())).thenReturn(true);
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());

    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    shortestClassUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> shortestClassUsageMarker.visitProgramMethod(mock(ProgramClass.class), mock(ProgramMethod.class)));
    verify(usageMarker).isUsed(isA(Processable.class));
    verify(usageMarker).markAsUsed(isA(Processable.class));
    verify(usageMarker).setCurrentUsageMark(isA(ShortestUsageMark.class));
    verify(usageMarker).shouldBeMarkedAsUsed(isA(Processable.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given '<init>'; when ProgramMethod getName(Clazz) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenInit_whenProgramMethodGetNameReturnInit() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("<init>");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>Then calls {@link ProgramMember#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Name'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenName_thenCallsGetDescriptor() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>Then calls {@link ProgramMember#getDescriptor(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Name'; then calls getDescriptor(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenName_thenCallsGetDescriptor2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenString_thenCallsGetString() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    when(programClass.getString(anyInt())).thenReturn("String");
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given ten.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getAccessFlags()} return ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given ten; when ProgramMethod getAccessFlags() return ten")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenTen_whenProgramMethodGetAccessFlagsReturnTen() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getAccessFlags()).thenReturn(10);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getAccessFlags();
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ShortestUsageMarker#getShortestUsageMark(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls getShortestUsageMark(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsGetShortestUsageMark() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    when(usageMarker.getShortestUsageMark(Mockito.<Processable>any())).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(usageMarker).markAsUsed(Mockito.<Processable>any());
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    when(usageMarker.shouldBeMarkedAsUsed(Mockito.<Processable>any())).thenReturn(true);
    doNothing().when(usageMarker).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());

    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    shortestClassUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    shortestClassUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(usageMarker, atLeast(1)).getShortestUsageMark(isA(Processable.class));
    verify(usageMarker).isUsed(isA(Processable.class));
    verify(usageMarker).markAsUsed(isA(Processable.class));
    verify(usageMarker, atLeast(1)).setCurrentUsageMark(Mockito.<ShortestUsageMark>any());
    verify(usageMarker).shouldBeMarkedAsUsed(isA(Processable.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link NamedMember#NamedMember(String, String)} with {@code Member Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when NamedMember(String, String) with 'Member Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_whenNamedMemberWithMemberNameAndDescriptor() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, new NamedMember("Member Name", "Descriptor"));

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    usageMarker.setCurrentUsageMark(new ShortestUsageMark("Just cause"));
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(programClass.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));
    doNothing().when(programClass).accept(Mockito.<ClassVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getAccessFlags();
    verify(programMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
    verify(programClass).getProcessingInfo();
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, new LibraryMethod(10, "Name", "Descriptor"));

    // Assert
    verify(libraryClass, atLeast(1)).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(libraryClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, new LibraryMethod(1024, "Name", "Descriptor"));

    // Assert
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryClass, atLeast(1)).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, new LibraryMethod(1, "<init>", "Descriptor"));

    // Assert
    verify(libraryClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<LibraryClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getAccessFlags()).thenReturn(1);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(libraryMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod).getAccessFlags();
    verify(libraryMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(libraryMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(libraryMethod).accept(isA(LibraryClass.class), isA(MemberVisitor.class));
    verify(libraryMethod).getProcessingInfo();
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<LibraryClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getAccessFlags()).thenReturn(1);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(libraryMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod).getAccessFlags();
    verify(libraryMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(libraryMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(libraryMethod).accept(isA(LibraryClass.class), isA(MemberVisitor.class));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryMethod).setProcessingInfo(isNull());
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@code Descriptor}.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Descriptor'; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenDescriptor_thenCallsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getAccessFlags()).thenReturn(1);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(libraryMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod).getAccessFlags();
    verify(libraryMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(libraryMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(libraryMethod).getProcessingInfo();
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link LibraryMethod} {@link LibraryMember#getName(Clazz)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given '<init>'; when LibraryMethod getName(Clazz) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenInit_whenLibraryMethodGetNameReturnInit() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getAccessFlags()).thenReturn(1);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryMethod.getName(Mockito.<Clazz>any())).thenReturn("<init>");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(libraryMethod).getAccessFlags();
    verify(libraryMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(libraryMethod).getProcessingInfo();
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link ShortestUsageMark#ShortestUsageMark(String)} with reason is {@code Just cause}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given ShortestUsageMark(String) with reason is 'Just cause'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenShortestUsageMarkWithReasonIsJustCause() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    LibraryClass libraryClass = mock(LibraryClass.class);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod).getProcessingInfo();
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#kotlinMetadataAccept(KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls kotlinMetadataAccept(KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsKotlinMetadataAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    doNothing().when(libraryClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.visitLibraryMethod(libraryClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryClass, atLeast(1)).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doThrow(new UnsupportedOperationException("<init>")).when(libraryMethod)
        .accept(Mockito.<LibraryClass>any(), Mockito.<MemberVisitor>any());
    when(libraryMethod.getAccessFlags()).thenReturn(1);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryMethod.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(libraryMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classUsageMarker.visitLibraryMethod(libraryClass, libraryMethod));
    verify(libraryClass, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(libraryMethod).getAccessFlags();
    verify(libraryMethod, atLeast(1)).getDescriptor(isA(Clazz.class));
    verify(libraryMethod, atLeast(1)).getName(isA(Clazz.class));
    verify(libraryMethod).accept(isA(LibraryClass.class), isA(MemberVisitor.class));
    verify(libraryMethod).getProcessingInfo();
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.markProgramFieldBody(programClass,
        new ProgramField(1, 1, 1, new LibraryClass(5, "This Class Name", "Super Class Name")));

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    LibraryClass referencedClass = mock(LibraryClass.class);
    doNothing().when(referencedClass).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markProgramFieldBody(programClass, new ProgramField(1, 1, 1, referencedClass));

    // Assert
    verify(referencedClass).accept(isA(ClassVisitor.class));
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link NamedField#NamedField(String, String)} with {@code Field Name} and {@code Field Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); when NamedField(String, String) with 'Field Name' and 'Field Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_whenNamedFieldWithFieldNameAndFieldDescriptor() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.markProgramFieldBody(programClass, new NamedField("Field Name", "Field Descriptor"));

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then calls {@link ProgramClass#kotlinMetadataAccept(KotlinMetadataVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test markProgramFieldBody(ProgramClass, ProgramField); when ProgramField(); then calls kotlinMetadataAccept(KotlinMetadataVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramFieldBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testMarkProgramFieldBody_whenProgramField_thenCallsKotlinMetadataAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.markProgramFieldBody(programClass, new ProgramField());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given '<init>'; when ProgramMethod getName(Clazz) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenInit_whenProgramMethodGetNameReturnInit() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("<init>");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Name}.</li>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#getName(Clazz)} return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given 'Name'; when ProgramMethod getName(Clazz) return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenName_whenProgramMethodGetNameReturnName() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_givenString_thenCallsGetString() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    when(programClass.getString(anyInt())).thenReturn("String");
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).getString(eq(0));
    verify(programClass, atLeast(1)).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doThrow(new UnsupportedOperationException("<init>")).when(programMethod)
        .accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> classUsageMarker.markProgramMethodBody(programClass, programMethod));
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link NamedMember#NamedMember(String, String)} with memberName is {@code <init>} and descriptor is {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); when NamedMember(String, String) with memberName is '<init>' and descriptor is '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_whenNamedMemberWithMemberNameIsInitAndDescriptorIsInit() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, new NamedMember("<init>", "<init>"));

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code <init>}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); when ProgramClass getString(int) return '<init>'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_whenProgramClassGetStringReturnInit_thenCallsGetString() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).kotlinMetadataAccept(Mockito.<KotlinMetadataVisitor>any());
    when(programClass.getString(anyInt())).thenReturn("<init>");
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programClass).getString(eq(0));
    verify(programClass).kotlinMetadataAccept(isA(KotlinMetadataVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMethod#accept(ProgramClass, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMethod#accept(ProgramClass, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test markProgramMethodBody(ProgramClass, ProgramMethod); when ProgramMethod accept(ProgramClass, MemberVisitor) does nothing; then calls accept(ProgramClass, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markProgramMethodBody(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testMarkProgramMethodBody_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    classUsageMarker.setExtraMethodVisitor(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<ProgramClass>any(), Mockito.<MemberVisitor>any());
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());
    doNothing().when(programMethod).referencedClassesAccept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markProgramMethodBody(programClass, programMethod);

    // Assert
    verify(programClass, atLeast(1)).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod).accept(isA(ProgramClass.class), isA(MemberVisitor.class));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
    verify(programMethod).referencedClassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
  void testMarkMethodHierarchy() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(1024, "Name", "Descriptor"));

    // Assert
    verify(clazz, atLeast(1)).accept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link LibraryClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
  void testMarkMethodHierarchy_givenString_thenCallsGetString() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markMethodHierarchy(clazz, new ProgramMethod());

    // Assert
    verify(clazz, atLeast(1)).accept(Mockito.<ClassVisitor>any());
    verify(clazz, atLeast(1)).getString(eq(0));
  }

  /**
   * Test {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}.
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#accept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryClass#accept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  @DisplayName("Test markMethodHierarchy(Clazz, Method); when LibraryClass accept(ClassVisitor) does nothing; then calls accept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.markMethodHierarchy(proguard.classfile.Clazz, proguard.classfile.Method)"})
  void testMarkMethodHierarchy_whenLibraryClassAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    // Act
    classUsageMarker.markMethodHierarchy(clazz, new LibraryMethod(1, "Name", "Descriptor"));

    // Assert
    verify(clazz, atLeast(1)).accept(Mockito.<ClassVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#visitIntegerConstant(Clazz, IntegerConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitIntegerConstant(Clazz, IntegerConstant)}
   */
  @Test
  @DisplayName("Test visitIntegerConstant(Clazz, IntegerConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitIntegerConstant(proguard.classfile.Clazz, proguard.classfile.constant.IntegerConstant)"})
  void testVisitIntegerConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    IntegerConstant integerConstant = new IntegerConstant(42);

    // Act
    shortestClassUsageMarker.visitIntegerConstant(clazz, integerConstant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, integerConstant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}
   */
  @Test
  @DisplayName("Test visitLongConstant(Clazz, LongConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLongConstant(proguard.classfile.Clazz, proguard.classfile.constant.LongConstant)"})
  void testVisitLongConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LongConstant longConstant = new LongConstant(42L);

    // Act
    shortestClassUsageMarker.visitLongConstant(clazz, longConstant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, longConstant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}.
   * <ul>
   *   <li>Then {@link LongConstant#LongConstant(long)} with value is forty-two ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}
   */
  @Test
  @DisplayName("Test visitLongConstant(Clazz, LongConstant); then LongConstant(long) with value is forty-two ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLongConstant(proguard.classfile.Clazz, proguard.classfile.constant.LongConstant)"})
  void testVisitLongConstant_thenLongConstantWithValueIsFortyTwoProcessingInfoIsNull() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LongConstant longConstant = new LongConstant(42L);

    // Act
    classUsageMarker.visitLongConstant(clazz, longConstant);

    // Assert that nothing has changed
    assertNull(longConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}
   */
  @Test
  @DisplayName("Test visitFloatConstant(Clazz, FloatConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitFloatConstant(proguard.classfile.Clazz, proguard.classfile.constant.FloatConstant)"})
  void testVisitFloatConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    FloatConstant floatConstant = new FloatConstant(10.0f);

    // Act
    shortestClassUsageMarker.visitFloatConstant(clazz, floatConstant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, floatConstant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}.
   * <ul>
   *   <li>Then {@link FloatConstant#FloatConstant(float)} with value is ten ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}
   */
  @Test
  @DisplayName("Test visitFloatConstant(Clazz, FloatConstant); then FloatConstant(float) with value is ten ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitFloatConstant(proguard.classfile.Clazz, proguard.classfile.constant.FloatConstant)"})
  void testVisitFloatConstant_thenFloatConstantWithValueIsTenProcessingInfoIsNull() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    FloatConstant floatConstant = new FloatConstant(10.0f);

    // Act
    classUsageMarker.visitFloatConstant(clazz, floatConstant);

    // Assert that nothing has changed
    assertNull(floatConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}
   */
  @Test
  @DisplayName("Test visitDoubleConstant(Clazz, DoubleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDoubleConstant(proguard.classfile.Clazz, proguard.classfile.constant.DoubleConstant)"})
  void testVisitDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);

    // Act
    shortestClassUsageMarker.visitDoubleConstant(clazz, doubleConstant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, doubleConstant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}.
   * <ul>
   *   <li>Then {@link DoubleConstant#DoubleConstant(double)} with value is ten ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}
   */
  @Test
  @DisplayName("Test visitDoubleConstant(Clazz, DoubleConstant); then DoubleConstant(double) with value is ten ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDoubleConstant(proguard.classfile.Clazz, proguard.classfile.constant.DoubleConstant)"})
  void testVisitDoubleConstant_thenDoubleConstantWithValueIsTenProcessingInfoIsNull() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);

    // Act
    classUsageMarker.visitDoubleConstant(clazz, doubleConstant);

    // Assert that nothing has changed
    assertNull(doubleConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitPrimitiveArrayConstant(Clazz, PrimitiveArrayConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPrimitiveArrayConstant(Clazz, PrimitiveArrayConstant)}
   */
  @Test
  @DisplayName("Test visitPrimitiveArrayConstant(Clazz, PrimitiveArrayConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPrimitiveArrayConstant(proguard.classfile.Clazz, proguard.classfile.constant.PrimitiveArrayConstant)"})
  void testVisitPrimitiveArrayConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    PrimitiveArrayConstant primitiveArrayConstant = new PrimitiveArrayConstant();

    // Act
    shortestClassUsageMarker.visitPrimitiveArrayConstant(clazz, primitiveArrayConstant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, primitiveArrayConstant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stringConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant()}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stringConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stringConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stringConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then {@link StringConstant#StringConstant()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then StringConstant() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStringConstant(proguard.classfile.Clazz, proguard.classfile.constant.StringConstant)"})
  void testVisitStringConstant_thenStringConstantProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Object processingInfo = stringConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName("Test visitUtf8Constant(Clazz, Utf8Constant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitUtf8Constant(proguard.classfile.Clazz, proguard.classfile.constant.Utf8Constant)"})
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    shortestClassUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, utf8Constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}.
   * <ul>
   *   <li>Then {@link Utf8Constant#Utf8Constant(String)} with {@code String} ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  @DisplayName("Test visitUtf8Constant(Clazz, Utf8Constant); then Utf8Constant(String) with 'String' ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitUtf8Constant(proguard.classfile.Clazz, proguard.classfile.constant.Utf8Constant)"})
  void testVisitUtf8Constant_thenUtf8ConstantWithStringProcessingInfoIsNull() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    classUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert that nothing has changed
    assertNull(utf8Constant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  @DisplayName("Test visitDynamicConstant(Clazz, DynamicConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.DynamicConstant)"})
  void testVisitDynamicConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, dynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  @DisplayName("Test visitDynamicConstant(Clazz, DynamicConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.DynamicConstant)"})
  void testVisitDynamicConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant()}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, dynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  @DisplayName("Test visitDynamicConstant(Clazz, DynamicConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.DynamicConstant)"})
  void testVisitDynamicConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, dynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, invokeDynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant()}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, invokeDynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, invokeDynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  @DisplayName("Test visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInvokeDynamicConstant(proguard.classfile.Clazz, proguard.classfile.constant.InvokeDynamicConstant)"})
  void testVisitInvokeDynamicConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, invokeDynamicConstant.getProcessingInfo());
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodHandleConstant methodHandleConstant = new MethodHandleConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    Object processingInfo = methodHandleConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    MethodHandleConstant methodHandleConstant = new MethodHandleConstant(1, 1);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert that nothing has changed
    assertNull(methodHandleConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  @DisplayName("Test visitMethodHandleConstant(Clazz, MethodHandleConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodHandleConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodHandleConstant)"})
  void testVisitMethodHandleConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    shortestClassUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constantArray[1];
    assertTrue(constant2 instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, processingInfo);
    assertSame(shortestUsageMark, constant2.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant(1, 1, null, new LibraryField(5, "Name", "Descriptor"));

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Object processingInfo = refConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryClass referencedClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    FieldrefConstant refConstant = new FieldrefConstant(1, 1, referencedClass,
        new LibraryField(5, "Name", "Descriptor"));

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Clazz clazz2 = refConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = refConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, clazz2.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then {@link FieldrefConstant#FieldrefConstant()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then FieldrefConstant() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenFieldrefConstantProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Object processingInfo = refConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, refConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant()}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, refConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnyRefConstant(proguard.classfile.Clazz, proguard.classfile.constant.RefConstant)"})
  void testVisitAnyRefConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, refConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new LibraryClass(1, "This Class Name", "Super Class Name"));

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = classConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, clazz2.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenClassConstantProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Object processingInfo = classConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant(10.0d)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link FloatConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element FloatConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenFirstElementFloatConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FloatConstant(10.0f)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FloatConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then first element {@link IntegerConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then first element IntegerConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenFirstElementIntegerConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new IntegerConstant(42)}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof IntegerConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, classConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Object processingInfo = methodTypeConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant(1, new Clazz[]{null});

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Object processingInfo = methodTypeConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new ClassConstant()}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodTypeConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DoubleConstant()}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodTypeConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new DynamicConstant()}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodTypeConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{new FieldrefConstant()}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodTypeConstant.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  @DisplayName("Test visitMethodTypeConstant(Clazz, MethodTypeConstant); then first element LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.MethodTypeConstant)"})
  void testVisitMethodTypeConstant_thenFirstElementLibraryClass() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant(1,
        new Clazz[]{new LibraryClass(1, "This Class Name", "Super Class Name")});

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Clazz[] clazzArray = methodTypeConstant.referencedClasses;
    Clazz clazz2 = clazzArray[0];
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = methodTypeConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertEquals(1, clazzArray.length);
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, clazz2.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitNameAndTypeConstant(clazz, nameAndTypeConstant);

    // Assert
    Object processingInfo = nameAndTypeConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(1, 1);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, nameAndTypeConstant);

    // Assert that nothing has changed
    assertNull(nameAndTypeConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  @DisplayName("Test visitNameAndTypeConstant(Clazz, NameAndTypeConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitNameAndTypeConstant(proguard.classfile.Clazz, proguard.classfile.constant.NameAndTypeConstant)"})
  void testVisitNameAndTypeConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    shortestClassUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constantArray[1];
    assertTrue(constant2 instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, processingInfo);
    assertSame(shortestUsageMark, constant2.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleConstant moduleConstant = new ModuleConstant(1);

    // Act
    shortestClassUsageMarker.visitModuleConstant(clazz, moduleConstant);

    // Assert
    Object processingInfo = moduleConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ModuleConstant moduleConstant = new ModuleConstant(1);

    // Act
    classUsageMarker.visitModuleConstant(clazz, moduleConstant);

    // Assert that nothing has changed
    assertNull(moduleConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{interfaceMethodrefConstant, new ClassConstant()}, 5, 5,
        5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  @DisplayName("Test visitModuleConstant(Clazz, ModuleConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleConstant(proguard.classfile.Clazz, proguard.classfile.constant.ModuleConstant)"})
  void testVisitModuleConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    shortestClassUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constantArray[1];
    assertTrue(constant2 instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, processingInfo);
    assertSame(shortestUsageMark, constant2.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    PackageConstant packageConstant = new PackageConstant(1);

    // Act
    shortestClassUsageMarker.visitPackageConstant(clazz, packageConstant);

    // Assert
    Object processingInfo = packageConstant.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    PackageConstant packageConstant = new PackageConstant(1);

    // Act
    classUsageMarker.visitPackageConstant(clazz, packageConstant);

    // Assert that nothing has changed
    assertNull(packageConstant.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{interfaceMethodrefConstant, new ClassConstant()}, 5, 5,
        5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertTrue(constantArray[1] instanceof ClassConstant);
    assertEquals(2, constantArray.length);
  }

  /**
   * Test {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  @DisplayName("Test visitPackageConstant(Clazz, PackageConstant); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitPackageConstant(proguard.classfile.Clazz, proguard.classfile.constant.PackageConstant)"})
  void testVisitPackageConstant_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, new ClassConstant()}, 5, 5, 5);

    // Act
    shortestClassUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    Constant constant2 = constantArray[1];
    assertTrue(constant2 instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, processingInfo);
    assertSame(shortestUsageMark, constant2.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitUnknownAttribute(Clazz, UnknownAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitUnknownAttribute(Clazz, UnknownAttribute)}
   */
  @Test
  @DisplayName("Test visitUnknownAttribute(Clazz, UnknownAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitUnknownAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.UnknownAttribute)"})
  void testVisitUnknownAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    UnknownAttribute unknownAttribute = new UnknownAttribute(1, 3);

    // Act
    shortestClassUsageMarker.visitUnknownAttribute(clazz, unknownAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, unknownAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Object processingInfo = sourceDebugExtensionAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDebugExtensionAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDebugExtensionAttribute)"})
  void testVisitSourceDebugExtensionAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRecordAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.RecordAttribute)"})
  void testVisitRecordAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, recordAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRecordAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.RecordAttribute)"})
  void testVisitRecordAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, recordAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRecordAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.RecordAttribute)"})
  void testVisitRecordAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, recordAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRecordAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.RecordAttribute)"})
  void testVisitRecordAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, recordAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}.
   * <ul>
   *   <li>Then {@link RecordAttribute#RecordAttribute()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then RecordAttribute() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRecordAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.RecordAttribute)"})
  void testVisitRecordAttribute_thenRecordAttributeProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Object processingInfo = recordAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceFileAttribute(Clazz, SourceFileAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceFileAttribute(Clazz, SourceFileAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceFileAttribute(Clazz, SourceFileAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceFileAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceFileAttribute)"})
  void testVisitSourceFileAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceFileAttribute sourceFileAttribute = new SourceFileAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSourceFileAttribute(clazz, sourceFileAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, sourceFileAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSourceDirAttribute(Clazz, SourceDirAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSourceDirAttribute(Clazz, SourceDirAttribute)}
   */
  @Test
  @DisplayName("Test visitSourceDirAttribute(Clazz, SourceDirAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSourceDirAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SourceDirAttribute)"})
  void testVisitSourceDirAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceDirAttribute sourceDirAttribute = new SourceDirAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSourceDirAttribute(clazz, sourceDirAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, sourceDirAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   * <ul>
   *   <li>Then calls {@link InnerClassesAttribute#innerClassEntriesAccept(Clazz, InnerClassesInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  @DisplayName("Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then calls innerClassEntriesAccept(Clazz, InnerClassesInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenCallsInnerClassEntriesAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute = mock(InnerClassesAttribute.class);
    doNothing().when(innerClassesAttribute)
        .innerClassEntriesAccept(Mockito.<Clazz>any(), Mockito.<InnerClassesInfoVisitor>any());

    // Act
    classUsageMarker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    verify(innerClassesAttribute).innerClassEntriesAccept(isA(Clazz.class), isA(InnerClassesInfoVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  @DisplayName("Test visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitEnclosingMethodAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.EnclosingMethodAttribute)"})
  void testVisitEnclosingMethodAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    // Act
    shortestClassUsageMarker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, enclosingMethodAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, moduleAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, moduleAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, moduleAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, moduleAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}.
   * <ul>
   *   <li>Then {@link ModuleAttribute#ModuleAttribute()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleAttribute(Clazz, ModuleAttribute); then ModuleAttribute() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleAttribute)"})
  void testVisitModuleAttribute_thenModuleAttributeProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Object processingInfo = moduleAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}
   */
  @Test
  @DisplayName("Test visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModuleMainClassAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModuleMainClassAttribute)"})
  void testVisitModuleMainClassAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleMainClassAttribute moduleMainClassAttribute = new ModuleMainClassAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitModuleMainClassAttribute(clazz, moduleMainClassAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, moduleMainClassAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Object processingInfo = modulePackagesAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, modulePackagesAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, modulePackagesAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, modulePackagesAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  @DisplayName("Test visitModulePackagesAttribute(Clazz, ModulePackagesAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitModulePackagesAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.module.ModulePackagesAttribute)"})
  void testVisitModulePackagesAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, modulePackagesAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)} with {@code clazz}, {@code deprecatedAttribute}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)}
   */
  @Test
  @DisplayName("Test visitDeprecatedAttribute(Clazz, DeprecatedAttribute) with 'clazz', 'deprecatedAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitDeprecatedAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.DeprecatedAttribute)"})
  void testVisitDeprecatedAttributeWithClazzDeprecatedAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    DeprecatedAttribute deprecatedAttribute = new DeprecatedAttribute(1);

    // Act
    shortestClassUsageMarker.visitDeprecatedAttribute(clazz, deprecatedAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, deprecatedAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSyntheticAttribute(Clazz, SyntheticAttribute)} with {@code clazz}, {@code syntheticAttribute}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSyntheticAttribute(Clazz, SyntheticAttribute)}
   */
  @Test
  @DisplayName("Test visitSyntheticAttribute(Clazz, SyntheticAttribute) with 'clazz', 'syntheticAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSyntheticAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SyntheticAttribute)"})
  void testVisitSyntheticAttributeWithClazzSyntheticAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SyntheticAttribute syntheticAttribute = new SyntheticAttribute(1);

    // Act
    shortestClassUsageMarker.visitSyntheticAttribute(clazz, syntheticAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, syntheticAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code clazz}, {@code signatureAttribute}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  @DisplayName("Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSignatureAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, signatureAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
  @DisplayName("Test visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitConstantValueAttribute(proguard.classfile.Clazz, proguard.classfile.Field, proguard.classfile.attribute.ConstantValueAttribute)"})
  void testVisitConstantValueAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryField field = new LibraryField(1, "Name", "Descriptor");

    ConstantValueAttribute constantValueAttribute = new ConstantValueAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitConstantValueAttribute(clazz, field, constantValueAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constantValueAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Object processingInfo = methodParametersAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodParametersAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodParametersAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodParametersAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  @DisplayName("Test visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitMethodParametersAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.MethodParametersAttribute)"})
  void testVisitMethodParametersAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, methodParametersAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Object processingInfo = exceptionsAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, exceptionsAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, exceptionsAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, exceptionsAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  @DisplayName("Test visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionsAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.ExceptionsAttribute)"})
  void testVisitExceptionsAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, exceptionsAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitCodeAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute)"})
  void testVisitCodeAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    shortestClassUsageMarker.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, codeAttribute.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}.
   * <ul>
   *   <li>Then {@link StackMapAttribute#StackMapAttribute()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute); then StackMapAttribute() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapAttribute)"})
  void testVisitStackMapAttribute_thenStackMapAttributeProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Object processingInfo = stackMapAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Object processingInfo = stackMapTableAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  @DisplayName("Test visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitStackMapTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.preverification.StackMapTableAttribute)"})
  void testVisitStackMapTableAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, stackMapTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Object processingInfo = lineNumberTableAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertTrue(((ShortestUsageMark) processingInfo).isCertain());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, lineNumberTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, lineNumberTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, lineNumberTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  @DisplayName("Test visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitLineNumberTableAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.LineNumberTableAttribute)"})
  void testVisitLineNumberTableAttribute_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    Object processingInfo = constant.getProcessingInfo();
    assertSame(processingInfo, lineNumberTableAttribute.getProcessingInfo());
    assertSame(processingInfo, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
    assertSame(usageMarker.currentUsageMark, processingInfo);
  }

  /**
   * Test {@link ClassUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}.
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#defaultValueAccept(Clazz, ElementValueVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName("Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls defaultValueAccept(Clazz, ElementValueVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitAnnotationDefaultAttribute(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.annotation.AnnotationDefaultAttribute)"})
  void testVisitAnnotationDefaultAttribute_thenCallsDefaultValueAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing().when(annotationDefaultAttribute)
        .defaultValueAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    classUsageMarker.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    verify(annotationDefaultAttribute).defaultValueAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}
   */
  @Test
  @DisplayName("Test visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExceptionInfo(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, proguard.classfile.attribute.ExceptionInfo)"})
  void testVisitExceptionInfo() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ExceptionInfo exceptionInfo = new ExceptionInfo(1, 3, 1, 1);

    // Act
    shortestClassUsageMarker.visitExceptionInfo(clazz, method, codeAttribute, exceptionInfo);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, exceptionInfo.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, innerClassesInfo.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(0, 1, 1, 1);

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert that nothing has changed
    assertNull(innerClassesInfo.getProcessingInfo());
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 0, 1, 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 0, 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Given {@code Class Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); given 'Class Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo_givenClassName() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert that nothing has changed
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitInnerClassesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.InnerClassesInfo)"})
  void testVisitInnerClassesInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link ClassUsageMarker#visitSameOneFrame(Clazz, Method, CodeAttribute, int, SameOneFrame)}.
   * <ul>
   *   <li>Then calls {@link SameOneFrame#stackItemAccept(Clazz, Method, CodeAttribute, int, VerificationTypeVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitSameOneFrame(Clazz, Method, CodeAttribute, int, SameOneFrame)}
   */
  @Test
  @DisplayName("Test visitSameOneFrame(Clazz, Method, CodeAttribute, int, SameOneFrame); then calls stackItemAccept(Clazz, Method, CodeAttribute, int, VerificationTypeVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitSameOneFrame(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.attribute.preverification.SameOneFrame)"})
  void testVisitSameOneFrame_thenCallsStackItemAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    SameOneFrame sameOneFrame = mock(SameOneFrame.class);
    doNothing().when(sameOneFrame)
        .stackItemAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(), anyInt(),
            Mockito.<VerificationTypeVisitor>any());

    // Act
    classUsageMarker.visitSameOneFrame(clazz, method, codeAttribute, 2, sameOneFrame);

    // Assert
    verify(sameOneFrame).stackItemAccept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class), eq(2),
        isA(VerificationTypeVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitObjectType(Clazz, Method, CodeAttribute, int, ObjectType)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitObjectType(Clazz, Method, CodeAttribute, int, ObjectType)}
   */
  @Test
  @DisplayName("Test visitObjectType(Clazz, Method, CodeAttribute, int, ObjectType); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitObjectType(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.attribute.preverification.ObjectType)"})
  void testVisitObjectType_thenCallsConstantPoolEntryAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitObjectType(clazz, method, codeAttribute, 2, VerificationTypeFactory.createObjectType(1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}
   */
  @Test
  @DisplayName("Test visitParameterInfo(Clazz, Method, int, ParameterInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitParameterInfo(proguard.classfile.Clazz, proguard.classfile.Method, int, proguard.classfile.attribute.ParameterInfo)"})
  void testVisitParameterInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitParameterInfo(clazz, method, 1, new ParameterInfo(1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}.
   * <ul>
   *   <li>When {@link LibraryClass}.</li>
   *   <li>Then calls {@link ParameterInfo#nameConstantAccept(Clazz, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}
   */
  @Test
  @DisplayName("Test visitParameterInfo(Clazz, Method, int, ParameterInfo); when LibraryClass; then calls nameConstantAccept(Clazz, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitParameterInfo(proguard.classfile.Clazz, proguard.classfile.Method, int, proguard.classfile.attribute.ParameterInfo)"})
  void testVisitParameterInfo_whenLibraryClass_thenCallsNameConstantAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ParameterInfo parameterInfo = mock(ParameterInfo.class);
    doNothing().when(parameterInfo).nameConstantAccept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.visitParameterInfo(clazz, method, 1, parameterInfo);

    // Assert
    verify(parameterInfo).nameConstantAccept(isA(Clazz.class), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitRequiresInfo(Clazz, RequiresInfo)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitRequiresInfo(Clazz, RequiresInfo)}
   */
  @Test
  @DisplayName("Test visitRequiresInfo(Clazz, RequiresInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitRequiresInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.RequiresInfo)"})
  void testVisitRequiresInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    classUsageMarker.visitRequiresInfo(clazz, new RequiresInfo(1, 1, 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  @DisplayName("Test visitExportsInfo(Clazz, ExportsInfo); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExportsInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ExportsInfo)"})
  void testVisitExportsInfo_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  @DisplayName("Test visitExportsInfo(Clazz, ExportsInfo); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExportsInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ExportsInfo)"})
  void testVisitExportsInfo_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  @DisplayName("Test visitExportsInfo(Clazz, ExportsInfo); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExportsInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ExportsInfo)"})
  void testVisitExportsInfo_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  @DisplayName("Test visitExportsInfo(Clazz, ExportsInfo); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitExportsInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ExportsInfo)"})
  void testVisitExportsInfo_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  @DisplayName("Test visitOpensInfo(Clazz, OpensInfo); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitOpensInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.OpensInfo)"})
  void testVisitOpensInfo_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  @DisplayName("Test visitOpensInfo(Clazz, OpensInfo); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitOpensInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.OpensInfo)"})
  void testVisitOpensInfo_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  @DisplayName("Test visitOpensInfo(Clazz, OpensInfo); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitOpensInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.OpensInfo)"})
  void testVisitOpensInfo_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  @DisplayName("Test visitOpensInfo(Clazz, OpensInfo); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitOpensInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.OpensInfo)"})
  void testVisitOpensInfo_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}.
   * <ul>
   *   <li>Then first element {@link ClassConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  @DisplayName("Test visitProvidesInfo(Clazz, ProvidesInfo); then first element ClassConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProvidesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ProvidesInfo)"})
  void testVisitProvidesInfo_thenFirstElementClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}.
   * <ul>
   *   <li>Then first element {@link DoubleConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  @DisplayName("Test visitProvidesInfo(Clazz, ProvidesInfo); then first element DoubleConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProvidesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ProvidesInfo)"})
  void testVisitProvidesInfo_thenFirstElementDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DoubleConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}.
   * <ul>
   *   <li>Then first element {@link DynamicConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  @DisplayName("Test visitProvidesInfo(Clazz, ProvidesInfo); then first element DynamicConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProvidesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ProvidesInfo)"})
  void testVisitProvidesInfo_thenFirstElementDynamicConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new DynamicConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}.
   * <ul>
   *   <li>Then first element {@link FieldrefConstant}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  @DisplayName("Test visitProvidesInfo(Clazz, ProvidesInfo); then first element FieldrefConstant")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitProvidesInfo(proguard.classfile.Clazz, proguard.classfile.attribute.module.ProvidesInfo)"})
  void testVisitProvidesInfo_thenFirstElementFieldrefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{new FieldrefConstant()}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, constant.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}.
   * <ul>
   *   <li>Then calls {@link EnumConstantElementValue#referencedFieldAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
  @DisplayName("Test visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue); then calls referencedFieldAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitEnumConstantElementValue(proguard.classfile.Clazz, proguard.classfile.attribute.annotation.Annotation, proguard.classfile.attribute.annotation.EnumConstantElementValue)"})
  void testVisitEnumConstantElementValue_thenCallsReferencedFieldAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    EnumConstantElementValue enumConstantElementValue = mock(EnumConstantElementValue.class);
    doNothing().when(enumConstantElementValue).referencedFieldAccept(Mockito.<MemberVisitor>any());

    // Act
    classUsageMarker.visitEnumConstantElementValue(clazz, annotation, enumConstantElementValue);

    // Assert
    verify(enumConstantElementValue).referencedFieldAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link ClassUsageMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.shrink.ClassUsageMarker.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsConstantPoolEntryAccept() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitConstantInstruction(clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1));

    // Assert
    verify(clazz, atLeast(1)).constantPoolEntryAccept(eq(1), Mockito.<ConstantVisitor>any());
  }

  /**
   * Test {@link ClassUsageMarker#markAsUsed(Processable)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#markAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test markAsUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markAsUsed(proguard.util.Processable)"})
  void testMarkAsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass processable = new LibraryClass();

    // Act
    shortestClassUsageMarker.markAsUsed(processable);

    // Assert
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, processable.getProcessingInfo());
    assertSame(shortestUsageMark, shortestClassUsageMarker.getUsageMarker().currentUsageMark);
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(Processable) with 'processable'; given ClassUsageMarker(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsUsedWithProcessable_givenClassUsageMarker_thenReturnTrue() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'; given ClassUsageMarker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsUsedWithProgramClassProgramMember_givenClassUsageMarker() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)} with {@code programClass}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsUsed(ProgramClass) with 'programClass'; given ClassUsageMarker(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsUsed(proguard.classfile.ProgramClass)"})
  void testShouldBeMarkedAsUsedWithProgramClass_givenClassUsageMarker_thenReturnTrue() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Test {@link ClassUsageMarker#isUsed(Processable)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isUsed(proguard.util.Processable)"})
  void testIsUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker)} with usageMarker is {@link ShortestUsageMarker} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); given ClassUsageMarker(SimpleUsageMarker) with usageMarker is ShortestUsageMarker (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isUsed(proguard.util.Processable)"})
  void testIsUsed_givenClassUsageMarkerWithUsageMarkerIsShortestUsageMarker() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertFalse(classUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); given ClassUsageMarker(); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isUsed(proguard.util.Processable)"})
  void testIsUsed_givenClassUsageMarker_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertFalse(classUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#markAsPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ShortestUsageMark}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test markAsPossiblyUsed(Processable); then LibraryClass() ProcessingInfo ShortestUsageMark")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markAsPossiblyUsed(proguard.util.Processable)"})
  void testMarkAsPossiblyUsed_thenLibraryClassProcessingInfoShortestUsageMark() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    LibraryClass processable = new LibraryClass();

    // Act
    shortestClassUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    Object processingInfo = processable.getProcessingInfo();
    assertTrue(processingInfo instanceof ShortestUsageMark);
    assertEquals("Just cause", ((ShortestUsageMark) processingInfo).getReason());
    assertFalse(((ShortestUsageMark) processingInfo).isCertain());
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)} with {@code processable}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(Processable) with 'processable'; given ClassUsageMarker()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.util.Processable)"})
  void testShouldBeMarkedAsPossiblyUsedWithProcessable_givenClassUsageMarker() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)} with {@code programClass}, {@code programMember}.
   * <p>
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember) with 'programClass', 'programMember'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.shrink.ClassUsageMarker.shouldBeMarkedAsPossiblyUsed(proguard.classfile.ProgramClass, proguard.classfile.ProgramMember)"})
  void testShouldBeMarkedAsPossiblyUsedWithProgramClassProgramMember3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Test {@link ClassUsageMarker#isPossiblyUsed(Processable)}.
   * <p>
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
  void testIsPossiblyUsed() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker)} with usageMarker is {@link ShortestUsageMarker} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); given ClassUsageMarker(SimpleUsageMarker) with usageMarker is ShortestUsageMarker (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
  void testIsPossiblyUsed_givenClassUsageMarkerWithUsageMarkerIsShortestUsageMarker() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertFalse(classUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>Given {@link ClassUsageMarker#ClassUsageMarker()}.</li>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); given ClassUsageMarker(); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.shrink.ClassUsageMarker.isPossiblyUsed(proguard.util.Processable)"})
  void testIsPossiblyUsed_givenClassUsageMarker_whenLibraryClass_thenReturnFalse() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertFalse(classUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link ClassUsageMarker#markAsUnused(Processable)}.
   * <ul>
   *   <li>Then calls {@link SimpleUsageMarker#markAsUnused(Processable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassUsageMarker#markAsUnused(Processable)}
   */
  @Test
  @DisplayName("Test markAsUnused(Processable); then calls markAsUnused(Processable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.shrink.ClassUsageMarker.markAsUnused(proguard.util.Processable)"})
  void testMarkAsUnused_thenCallsMarkAsUnused() {
    // Arrange
    ShortestUsageMarker usageMarker = mock(ShortestUsageMarker.class);
    doNothing().when(usageMarker).markAsUnused(Mockito.<Processable>any());
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);

    // Act
    classUsageMarker.markAsUnused(new LibraryClass());

    // Assert
    verify(usageMarker).markAsUnused(isA(Processable.class));
  }
}
