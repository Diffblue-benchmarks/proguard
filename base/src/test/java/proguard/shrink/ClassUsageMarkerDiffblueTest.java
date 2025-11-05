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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
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
import proguard.testutils.cpa.NamedField;
import proguard.testutils.cpa.NamedMember;
import proguard.util.Processable;

class ClassUsageMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitAnyAnnotation(Clazz, KotlinAnnotatable, KotlinAnnotation)}
   */
  @Test
  void testKotlinUsageMarkerVisitAnyAnnotation() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinAnnotatable annotatable = mock(KotlinAnnotatable.class);

    // Act
    kotlinUsageMarker.visitAnyAnnotation(clazz, annotatable, new KotlinAnnotation("Class Name"));

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitAnyEffectExpression(Clazz, KotlinEffectMetadata, KotlinEffectExpressionMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitAnyEffectExpression() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
    LibraryClass clazz = new LibraryClass();
    KotlinEffectMetadata kotlinEffectMetadata = new KotlinEffectMetadata(KotlinEffectType.RETURNS_CONSTANT,
        KotlinEffectInvocationKind.AT_MOST_ONCE);

    // Act
    kotlinUsageMarker.visitAnyEffectExpression(clazz, kotlinEffectMetadata, new KotlinEffectExpressionMetadata());

    // Assert
    verify(classUsageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitAnyVersionRequirement(Clazz, KotlinVersionRequirementMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitAnyVersionRequirement() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = (new ShortestClassUsageMarker(usageMarker,
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitConstructor(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitConstructor() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitConstructorValParameter(Clazz, KotlinClassKindMetadata, KotlinConstructorMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitConstructorValParameter() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitFunction(Clazz, KotlinDeclarationContainerMetadata, KotlinFunctionMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitFunction() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitFunctionValParameter(Clazz, KotlinMetadata, KotlinFunctionMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitFunctionValParameter() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitKotlinDeclarationContainerMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitKotlinFileFacadeMetadata(Clazz, KotlinFileFacadeKindMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitKotlinFileFacadeMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitKotlinSyntheticClassMetadata(Clazz, KotlinSyntheticClassKindMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitKotlinSyntheticClassMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    doNothing().when(classUsageMarker).markAsUsed(Mockito.<Processable>any());
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitPropertyValParameter(Clazz, KotlinDeclarationContainerMetadata, KotlinPropertyMetadata, KotlinValueParameterMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitPropertyValParameter() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test:
   * {@link ClassUsageMarker.KotlinUsageMarker#visitTypeAlias(Clazz, KotlinDeclarationContainerMetadata, KotlinTypeAliasMetadata)}
   */
  @Test
  void testKotlinUsageMarkerVisitTypeAlias() {
    // Arrange
    ClassUsageMarker classUsageMarker = mock(ClassUsageMarker.class);
    when(classUsageMarker.isUsed(Mockito.<Processable>any())).thenReturn(false);
    ClassUsageMarker.KotlinUsageMarker kotlinUsageMarker = classUsageMarker.new KotlinUsageMarker();
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
   * Method under test: {@link ClassUsageMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> classUsageMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 0, 0);

    // Act
    classUsageMarker.visitProgramClass(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{classConstant, classConstant2}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    assertEquals(2, constantArray.length);
    assertSame(classConstant2, constantArray[1]);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{doubleConstant, classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{dynamicConstant, classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{fieldrefConstant, classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{floatConstant, classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{integerConstant, classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#markProgramClassBody(ProgramClass)}
   */
  @Test
  void testMarkProgramClassBody7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{interfaceMethodrefConstant, classConstant}, 1, 1,
        1);

    // Act
    classUsageMarker.markProgramClassBody(programClass);

    // Assert
    Constant[] constantArray = programClass.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass() {
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
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass2() {
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
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass3() {
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
   * Method under test: {@link ClassUsageMarker#visitLibraryClass(LibraryClass)}
   */
  @Test
  void testVisitLibraryClass4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new SimpleUsageMarker(),
        ClassUsageMarker.MarkingMode.MAIN_DEX_TRACING);
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
   * Method under test:
   * {@link ClassUsageMarker#visitAnyKotlinMetadata(Clazz, KotlinMetadata)}
   */
  @Test
  void testVisitAnyKotlinMetadata() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    classUsageMarker.visitAnyKotlinMetadata(clazz, new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn"));

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyKotlinMetadata(Clazz, KotlinMetadata)}
   */
  @Test
  void testVisitAnyKotlinMetadata2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    classUsageMarker.visitAnyKotlinMetadata(clazz, new KotlinClassKindMetadata(new int[]{1, -1, 1, -1}, 1, "Xs", "Pn"));

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyKotlinMetadata(Clazz, KotlinMetadata)}
   */
  @Test
  void testVisitAnyKotlinMetadata3() {
    // Arrange
    SimpleUsageMarker usageMarker = mock(SimpleUsageMarker.class);
    when(usageMarker.isUsed(Mockito.<Processable>any())).thenReturn(true);
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(usageMarker);
    LibraryClass clazz = mock(LibraryClass.class);
    KotlinMetadata kotlinMetadata = mock(KotlinMetadata.class);
    doNothing().when(kotlinMetadata).accept(Mockito.<Clazz>any(), Mockito.<KotlinMetadataVisitor>any());

    // Act
    classUsageMarker.visitAnyKotlinMetadata(clazz, kotlinMetadata);

    // Assert
    verify(kotlinMetadata).accept(isA(Clazz.class), isA(KotlinMetadataVisitor.class));
    verify(usageMarker).isUsed(isA(Processable.class));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitKotlinDeclarationContainerMetadata(Clazz, KotlinDeclarationContainerMetadata)}
   */
  @Test
  void testVisitKotlinDeclarationContainerMetadata() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod5() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod6() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod7() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod8() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod9() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod10() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod11() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod12() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod13() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod14() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod3() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod4() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod5() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod6() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod7() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod8() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod9() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod10() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  void testMarkProgramFieldBody() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  void testMarkProgramFieldBody2() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  void testMarkProgramFieldBody3() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramFieldBody(ProgramClass, ProgramField)}
   */
  @Test
  void testMarkProgramFieldBody4() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody2() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody3() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody4() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody5() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody6() {
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
   * Method under test:
   * {@link ClassUsageMarker#markProgramMethodBody(ProgramClass, ProgramMethod)}
   */
  @Test
  void testMarkProgramMethodBody7() {
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
   * Method under test:
   * {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy() {
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
   * Method under test:
   * {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy2() {
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
   * Method under test:
   * {@link ClassUsageMarker#markMethodHierarchy(Clazz, Method)}
   */
  @Test
  void testMarkMethodHierarchy3() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitIntegerConstant(Clazz, IntegerConstant)}
   */
  @Test
  void testVisitIntegerConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    IntegerConstant integerConstant = new IntegerConstant(42);

    // Act
    shortestClassUsageMarker.visitIntegerConstant(clazz, integerConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, integerConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}
   */
  @Test
  void testVisitLongConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LongConstant longConstant = new LongConstant(42L);

    // Act
    shortestClassUsageMarker.visitLongConstant(clazz, longConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, longConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLongConstant(Clazz, LongConstant)}
   */
  @Test
  void testVisitLongConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LongConstant longConstant = new LongConstant(42L);

    // Act
    classUsageMarker.visitLongConstant(clazz, longConstant);

    // Assert
    assertNull(longConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}
   */
  @Test
  void testVisitFloatConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    FloatConstant floatConstant = new FloatConstant(10.0f);

    // Act
    shortestClassUsageMarker.visitFloatConstant(clazz, floatConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, floatConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitFloatConstant(Clazz, FloatConstant)}
   */
  @Test
  void testVisitFloatConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    FloatConstant floatConstant = new FloatConstant(10.0f);

    // Act
    classUsageMarker.visitFloatConstant(clazz, floatConstant);

    // Assert
    assertNull(floatConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}
   */
  @Test
  void testVisitDoubleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);

    // Act
    shortestClassUsageMarker.visitDoubleConstant(clazz, doubleConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, doubleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDoubleConstant(Clazz, DoubleConstant)}
   */
  @Test
  void testVisitDoubleConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);

    // Act
    classUsageMarker.visitDoubleConstant(clazz, doubleConstant);

    // Assert
    assertNull(doubleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPrimitiveArrayConstant(Clazz, PrimitiveArrayConstant)}
   */
  @Test
  void testVisitPrimitiveArrayConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    PrimitiveArrayConstant primitiveArrayConstant = new PrimitiveArrayConstant();

    // Act
    shortestClassUsageMarker.visitPrimitiveArrayConstant(clazz, primitiveArrayConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, primitiveArrayConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stringConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitStringConstant(clazz, new StringConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stringConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitStringConstant(clazz, new StringConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitStringConstant(clazz, new StringConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stringConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stringConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    StringConstant stringConstant = new StringConstant();

    // Act
    shortestClassUsageMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stringConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    shortestClassUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, utf8Constant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitUtf8Constant(Clazz, Utf8Constant)}
   */
  @Test
  void testVisitUtf8Constant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Utf8Constant utf8Constant = new Utf8Constant("String");

    // Act
    classUsageMarker.visitUtf8Constant(clazz, utf8Constant);

    // Assert
    assertNull(utf8Constant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitDynamicConstant(clazz, new DynamicConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, dynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    DynamicConstant dynamicConstant2 = new DynamicConstant();

    // Act
    classUsageMarker.visitDynamicConstant(clazz, dynamicConstant2);

    // Assert
    assertEquals(dynamicConstant, dynamicConstant2);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitDynamicConstant(clazz, new DynamicConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, dynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDynamicConstant(Clazz, DynamicConstant)}
   */
  @Test
  void testVisitDynamicConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    DynamicConstant dynamicConstant = new DynamicConstant();

    // Act
    shortestClassUsageMarker.visitDynamicConstant(clazz, dynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, dynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, invokeDynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitInvokeDynamicConstant(clazz, new InvokeDynamicConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, invokeDynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, invokeDynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();

    // Act
    shortestClassUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, invokeDynamicConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInvokeDynamicConstant(Clazz, InvokeDynamicConstant)}
   */
  @Test
  void testVisitInvokeDynamicConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InvokeDynamicConstant invokeDynamicConstant = new InvokeDynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{invokeDynamicConstant}, 5, 5, 5);

    InvokeDynamicConstant invokeDynamicConstant2 = new InvokeDynamicConstant();

    // Act
    classUsageMarker.visitInvokeDynamicConstant(clazz, invokeDynamicConstant2);

    // Assert
    assertEquals(invokeDynamicConstant, invokeDynamicConstant2);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodHandleConstant methodHandleConstant = new MethodHandleConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodHandleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    MethodHandleConstant methodHandleConstant = new MethodHandleConstant(1, 1);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    assertNull(methodHandleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
    assertSame(classConstant2, constantArray[1]);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    MethodHandleConstant methodHandleConstant = new MethodHandleConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitMethodHandleConstant(clazz, methodHandleConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(classConstant2, constantArray[1]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodHandleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodHandleConstant(Clazz, MethodHandleConstant)}
   */
  @Test
  void testVisitMethodHandleConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodHandleConstant(clazz, new MethodHandleConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    classUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    assertEquals(fieldrefConstant, refConstant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant(1, 1, null, new LibraryField(5, "Name", "Descriptor"));

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant7() {
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, clazz2.getProcessingInfo());
    assertSame(shortestUsageMark, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  void testVisitAnyRefConstant9() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    shortestClassUsageMarker.visitAnyRefConstant(clazz, refConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, refConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    ClassConstant classConstant2 = new ClassConstant();

    // Act
    classUsageMarker.visitClassConstant(clazz, classConstant2);

    // Assert
    assertEquals(classConstant, classConstant2);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DoubleConstant);
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FloatConstant);
    assertEquals(1, constantArray.length);
    assertSame(floatConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitClassConstant(clazz, new ClassConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof IntegerConstant);
    assertEquals(1, constantArray.length);
    assertSame(integerConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant8() {
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark shortestUsageMark = usageMarker.currentUsageMark;
    assertSame(shortestUsageMark, clazz2.getProcessingInfo());
    assertSame(shortestUsageMark, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant9() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant10() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant11() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant12() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(floatConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant13() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    IntegerConstant integerConstant = new IntegerConstant(42);
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant}, 5, 5, 5);

    ClassConstant classConstant = new ClassConstant();

    // Act
    shortestClassUsageMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(integerConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, classConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodTypeConstant(clazz, new MethodTypeConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodTypeConstant(clazz, new MethodTypeConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitMethodTypeConstant(clazz, new MethodTypeConstant());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    MethodTypeConstant methodTypeConstant = new MethodTypeConstant(1, new Clazz[]{null});

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant(1, new Clazz[]{libraryClass});

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Clazz[] clazzArray = methodTypeConstant.referencedClasses;
    assertEquals(1, clazzArray.length);
    assertSame(libraryClass, clazzArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant9() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodTypeConstant(Clazz, MethodTypeConstant)}
   */
  @Test
  void testVisitMethodTypeConstant10() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant}, 5, 5, 5);

    MethodTypeConstant methodTypeConstant = new MethodTypeConstant();

    // Act
    shortestClassUsageMarker.visitMethodTypeConstant(clazz, methodTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitNameAndTypeConstant(clazz, nameAndTypeConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, nameAndTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(1, 1);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, nameAndTypeConstant);

    // Assert
    assertNull(nameAndTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
    assertSame(classConstant2, constantArray[1]);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    NameAndTypeConstant nameAndTypeConstant = new NameAndTypeConstant(1, 1);

    // Act
    shortestClassUsageMarker.visitNameAndTypeConstant(clazz, nameAndTypeConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(classConstant2, constantArray[1]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, nameAndTypeConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitNameAndTypeConstant(Clazz, NameAndTypeConstant)}
   */
  @Test
  void testVisitNameAndTypeConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitNameAndTypeConstant(clazz, new NameAndTypeConstant(1, 1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleConstant moduleConstant = new ModuleConstant(1);

    // Act
    shortestClassUsageMarker.visitModuleConstant(clazz, moduleConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    ModuleConstant moduleConstant = new ModuleConstant(1);

    // Act
    classUsageMarker.visitModuleConstant(clazz, moduleConstant);

    // Assert
    assertNull(moduleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
    assertSame(classConstant2, constantArray[1]);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    ModuleConstant moduleConstant = new ModuleConstant(1);

    // Act
    shortestClassUsageMarker.visitModuleConstant(clazz, moduleConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(classConstant2, constantArray[1]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant9() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleConstant(Clazz, ModuleConstant)}
   */
  @Test
  void testVisitModuleConstant10() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{interfaceMethodrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitModuleConstant(clazz, new ModuleConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    PackageConstant packageConstant = new PackageConstant(1);

    // Act
    shortestClassUsageMarker.visitPackageConstant(clazz, packageConstant);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, packageConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    PackageConstant packageConstant = new PackageConstant(1);

    // Act
    classUsageMarker.visitPackageConstant(clazz, packageConstant);

    // Assert
    assertNull(packageConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
    assertSame(classConstant2, constantArray[1]);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant4() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ClassConstant classConstant2 = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{classConstant, classConstant2}, 5, 5, 5);

    PackageConstant packageConstant = new PackageConstant(1);

    // Act
    shortestClassUsageMarker.visitPackageConstant(clazz, packageConstant);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(classConstant2, constantArray[1]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, packageConstant.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DoubleConstant doubleConstant = new DoubleConstant(10.0d);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{doubleConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{dynamicConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{fieldrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FloatConstant floatConstant = new FloatConstant(10.0f);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{floatConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant9() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    IntegerConstant integerConstant = new IntegerConstant(42);
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{integerConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitPackageConstant(Clazz, PackageConstant)}
   */
  @Test
  void testVisitPackageConstant10() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    InterfaceMethodrefConstant interfaceMethodrefConstant = new InterfaceMethodrefConstant();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(5, 3, new Constant[]{interfaceMethodrefConstant, classConstant}, 5, 5, 5);

    // Act
    classUsageMarker.visitPackageConstant(clazz, new PackageConstant(1));

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[1];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(2, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitUnknownAttribute(Clazz, UnknownAttribute)}
   */
  @Test
  void testVisitUnknownAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    UnknownAttribute unknownAttribute = new UnknownAttribute(1, 3);

    // Act
    shortestClassUsageMarker.visitUnknownAttribute(clazz, unknownAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, unknownAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitSourceDebugExtensionAttribute(clazz, new SourceDebugExtensionAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitSourceDebugExtensionAttribute(clazz, new SourceDebugExtensionAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitSourceDebugExtensionAttribute(clazz, new SourceDebugExtensionAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDebugExtensionAttribute(Clazz, SourceDebugExtensionAttribute)}
   */
  @Test
  void testVisitSourceDebugExtensionAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    SourceDebugExtensionAttribute sourceDebugExtensionAttribute = new SourceDebugExtensionAttribute();

    // Act
    shortestClassUsageMarker.visitSourceDebugExtensionAttribute(clazz, sourceDebugExtensionAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDebugExtensionAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, recordAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitRecordAttribute(clazz, new RecordAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, recordAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitRecordAttribute(clazz, new RecordAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitRecordAttribute(clazz, new RecordAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, recordAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, recordAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  void testVisitRecordAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    shortestClassUsageMarker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, recordAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceFileAttribute(Clazz, SourceFileAttribute)}
   */
  @Test
  void testVisitSourceFileAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceFileAttribute sourceFileAttribute = new SourceFileAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSourceFileAttribute(clazz, sourceFileAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceFileAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSourceDirAttribute(Clazz, SourceDirAttribute)}
   */
  @Test
  void testVisitSourceDirAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SourceDirAttribute sourceDirAttribute = new SourceDirAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSourceDirAttribute(clazz, sourceDirAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, sourceDirAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  void testVisitInnerClassesAttribute() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}
   */
  @Test
  void testVisitEnclosingMethodAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    // Act
    shortestClassUsageMarker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, enclosingMethodAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModuleAttribute(clazz, new ModuleAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModuleAttribute(clazz, new ModuleAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModuleAttribute(clazz, new ModuleAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleAttribute(Clazz, ModuleAttribute)}
   */
  @Test
  void testVisitModuleAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    ModuleAttribute moduleAttribute = new ModuleAttribute();

    // Act
    shortestClassUsageMarker.visitModuleAttribute(clazz, moduleAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModuleMainClassAttribute(Clazz, ModuleMainClassAttribute)}
   */
  @Test
  void testVisitModuleMainClassAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModuleMainClassAttribute moduleMainClassAttribute = new ModuleMainClassAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitModuleMainClassAttribute(clazz, moduleMainClassAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, moduleMainClassAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, modulePackagesAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModulePackagesAttribute(clazz, new ModulePackagesAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, modulePackagesAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModulePackagesAttribute(clazz, new ModulePackagesAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitModulePackagesAttribute(clazz, new ModulePackagesAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, modulePackagesAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, modulePackagesAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitModulePackagesAttribute(Clazz, ModulePackagesAttribute)}
   */
  @Test
  void testVisitModulePackagesAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    ModulePackagesAttribute modulePackagesAttribute = new ModulePackagesAttribute();

    // Act
    shortestClassUsageMarker.visitModulePackagesAttribute(clazz, modulePackagesAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, modulePackagesAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitDeprecatedAttribute(Clazz, DeprecatedAttribute)}
   */
  @Test
  void testVisitDeprecatedAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    DeprecatedAttribute deprecatedAttribute = new DeprecatedAttribute(1);

    // Act
    shortestClassUsageMarker.visitDeprecatedAttribute(clazz, deprecatedAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, deprecatedAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSyntheticAttribute(Clazz, SyntheticAttribute)}
   */
  @Test
  void testVisitSyntheticAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SyntheticAttribute syntheticAttribute = new SyntheticAttribute(1);

    // Act
    shortestClassUsageMarker.visitSyntheticAttribute(clazz, syntheticAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, syntheticAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitSignatureAttribute(Clazz, SignatureAttribute)}
   */
  @Test
  void testVisitSignatureAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    // Act
    shortestClassUsageMarker.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, signatureAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitConstantValueAttribute(Clazz, Field, ConstantValueAttribute)}
   */
  @Test
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, constantValueAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodParametersAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitMethodParametersAttribute(clazz, method, new MethodParametersAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodParametersAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitMethodParametersAttribute(clazz, method, new MethodParametersAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitMethodParametersAttribute(clazz, method, new MethodParametersAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodParametersAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodParametersAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitMethodParametersAttribute(Clazz, Method, MethodParametersAttribute)}
   */
  @Test
  void testVisitMethodParametersAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    MethodParametersAttribute methodParametersAttribute = new MethodParametersAttribute();

    // Act
    shortestClassUsageMarker.visitMethodParametersAttribute(clazz, method, methodParametersAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, methodParametersAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionsAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitExceptionsAttribute(clazz, method, new ExceptionsAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionsAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitExceptionsAttribute(clazz, method, new ExceptionsAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classUsageMarker.visitExceptionsAttribute(clazz, method, new ExceptionsAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionsAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionsAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionsAttribute(Clazz, Method, ExceptionsAttribute)}
   */
  @Test
  void testVisitExceptionsAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    ExceptionsAttribute exceptionsAttribute = new ExceptionsAttribute();

    // Act
    shortestClassUsageMarker.visitExceptionsAttribute(clazz, method, exceptionsAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionsAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, codeAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, new StackMapAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, new StackMapAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, new StackMapAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapAttribute(Clazz, Method, CodeAttribute, StackMapAttribute)}
   */
  @Test
  void testVisitStackMapAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapAttribute stackMapAttribute = new StackMapAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapAttribute(clazz, method, codeAttribute, stackMapAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, new StackMapTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, new StackMapTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, new StackMapTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitStackMapTableAttribute(Clazz, Method, CodeAttribute, StackMapTableAttribute)}
   */
  @Test
  void testVisitStackMapTableAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute();

    // Act
    shortestClassUsageMarker.visitStackMapTableAttribute(clazz, method, codeAttribute, stackMapTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, stackMapTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, lineNumberTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, new LineNumberTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute3() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, lineNumberTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, new LineNumberTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    classUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, new LineNumberTableAttribute());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, lineNumberTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, lineNumberTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitLineNumberTableAttribute(Clazz, Method, CodeAttribute, LineNumberTableAttribute)}
   */
  @Test
  void testVisitLineNumberTableAttribute8() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute();

    // Act
    shortestClassUsageMarker.visitLineNumberTableAttribute(clazz, method, codeAttribute, lineNumberTableAttribute);

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, lineNumberTableAttribute.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)}
   */
  @Test
  void testVisitAnnotationDefaultAttribute() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitExceptionInfo(Clazz, Method, CodeAttribute, ExceptionInfo)}
   */
  @Test
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, exceptionInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act
    classUsageMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
    assertNull(innerClassesInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo2() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo3() {
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
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, innerClassesInfo.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo4() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  void testVisitInnerClassesInfo5() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitSameOneFrame(Clazz, Method, CodeAttribute, int, SameOneFrame)}
   */
  @Test
  void testVisitSameOneFrame() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitObjectType(Clazz, Method, CodeAttribute, int, ObjectType)}
   */
  @Test
  void testVisitObjectType() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}
   */
  @Test
  void testVisitParameterInfo() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitParameterInfo(Clazz, Method, int, ParameterInfo)}
   */
  @Test
  void testVisitParameterInfo2() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitRequiresInfo(Clazz, RequiresInfo)}
   */
  @Test
  void testVisitRequiresInfo() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitExportsInfo(Clazz, ExportsInfo)}
   */
  @Test
  void testVisitExportsInfo7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitExportsInfo(clazz, new ExportsInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test: {@link ClassUsageMarker#visitOpensInfo(Clazz, OpensInfo)}
   */
  @Test
  void testVisitOpensInfo7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitOpensInfo(clazz, new OpensInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof ClassConstant);
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo2() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    ClassConstant classConstant = new ClassConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(classConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof DynamicConstant);
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    classUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    Constant constant = constantArray[0];
    assertTrue(constant instanceof FieldrefConstant);
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constant);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo5() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DoubleConstant doubleConstant = new DoubleConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{doubleConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(doubleConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo6() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    DynamicConstant dynamicConstant = new DynamicConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{dynamicConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(dynamicConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitProvidesInfo(Clazz, ProvidesInfo)}
   */
  @Test
  void testVisitProvidesInfo7() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{fieldrefConstant}, 1, 1, 1);

    // Act
    shortestClassUsageMarker.visitProvidesInfo(clazz, new ProvidesInfo());

    // Assert
    Constant[] constantArray = clazz.constantPool;
    assertEquals(1, constantArray.length);
    assertSame(fieldrefConstant, constantArray[0]);
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#visitEnumConstantElementValue(Clazz, Annotation, EnumConstantElementValue)}
   */
  @Test
  void testVisitEnumConstantElementValue() {
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
   * Method under test:
   * {@link ClassUsageMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
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
   * Method under test: {@link ClassUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed() {
    // Arrange
    ShortestUsageMarker usageMarker = new ShortestUsageMarker();
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(usageMarker, "Just cause");
    LibraryClass processable = new LibraryClass();

    // Act
    shortestClassUsageMarker.markAsUsed(processable);

    // Assert
    assertSame(usageMarker, shortestClassUsageMarker.getUsageMarker());
    ShortestUsageMark expectedProcessingInfo = usageMarker.currentUsageMark;
    assertSame(expectedProcessingInfo, processable.getProcessingInfo());
  }

  /**
   * Method under test: {@link ClassUsageMarker#markAsUsed(Processable)}
   */
  @Test
  void testMarkAsUsed2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.markAsUsed(processable);

    // Assert
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass)}
   */
  @Test
  void testShouldBeMarkedAsUsed4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    classUsageMarker.setExtraConstantVisitor(new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter()));

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new ProgramClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed6() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsUsed7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed9() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed10() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsUsedResult = classUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed11() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsUsedResult = classUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#shouldBeMarkedAsUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsUsed12() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsUsedResult = classUsageMarker.shouldBeMarkedAsUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertFalse(classUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = classUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsUsedResult = classUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isUsed(Processable)}
   */
  @Test
  void testIsUsed5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualIsUsedResult = classUsageMarker.isUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed() {
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
   * Method under test: {@link ClassUsageMarker#markAsPossiblyUsed(Processable)}
   */
  @Test
  void testMarkAsPossiblyUsed2() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.markAsPossiblyUsed(processable);

    // Assert
    verify(processable).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(ProgramClass, ProgramMember)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    ProgramClass programClass = new ProgramClass();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(programClass, new ProgramField()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertTrue(classUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed5() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertTrue(shortestClassUsageMarker.shouldBeMarkedAsPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed6() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = classUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable, atLeast(1)).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed7() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = classUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable, atLeast(1)).getProcessingInfo();
    assertTrue(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed8() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark("Just cause"));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = classUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#shouldBeMarkedAsPossiblyUsed(Processable)}
   */
  @Test
  void testShouldBeMarkedAsPossiblyUsed9() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualShouldBeMarkedAsPossiblyUsedResult = classUsageMarker.shouldBeMarkedAsPossiblyUsed(processable);

    // Assert
    verify(processable, atLeast(1)).getProcessingInfo();
    assertFalse(actualShouldBeMarkedAsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();

    // Act and Assert
    assertFalse(classUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed2() {
    // Arrange
    ShortestClassUsageMarker shortestClassUsageMarker = new ShortestClassUsageMarker(new ShortestUsageMarker(),
        "Just cause");

    // Act and Assert
    assertFalse(shortestClassUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed3() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsPossiblyUsedResult = classUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed4() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    boolean actualIsPossiblyUsedResult = classUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertFalse(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  void testIsPossiblyUsed5() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker(new ShortestUsageMarker());
    Processable processable = mock(Processable.class);
    when(processable.getProcessingInfo()).thenReturn(new ShortestUsageMark(new ShortestUsageMark("Just cause"), false));

    // Act
    boolean actualIsPossiblyUsedResult = classUsageMarker.isPossiblyUsed(processable);

    // Assert
    verify(processable).getProcessingInfo();
    assertTrue(actualIsPossiblyUsedResult);
  }

  /**
   * Method under test: {@link ClassUsageMarker#markAsUnused(Processable)}
   */
  @Test
  void testMarkAsUnused() {
    // Arrange
    ClassUsageMarker classUsageMarker = new ClassUsageMarker();
    Processable processable = mock(Processable.class);
    doNothing().when(processable).setProcessingInfo(Mockito.<Object>any());

    // Act
    classUsageMarker.markAsUnused(processable);

    // Assert
    verify(processable).setProcessingInfo(isNull());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClassUsageMarker#setExtraConstantVisitor(ConstantVisitor)}
   *   <li>{@link ClassUsageMarker#setExtraMethodVisitor(MemberVisitor)}
   *   <li>
   * {@link ClassUsageMarker#visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)}
   *   <li>
   * {@link ClassUsageMarker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   *   <li>
   * {@link ClassUsageMarker#visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitAnyStackMapFrame(Clazz, Method, CodeAttribute, int, StackMapFrame)}
   *   <li>
   * {@link ClassUsageMarker#visitAnyVerificationType(Clazz, Method, CodeAttribute, int, VerificationType)}
   *   <li>
   * {@link ClassUsageMarker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}
   *   <li>
   * {@link ClassUsageMarker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitClassElementValue(Clazz, Annotation, ClassElementValue)}
   *   <li>
   * {@link ClassUsageMarker#visitConstantElementValue(Clazz, Annotation, ConstantElementValue)}
   *   <li>{@link ClassUsageMarker#visitLibraryField(LibraryClass, LibraryField)}
   *   <li>
   * {@link ClassUsageMarker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)}
   *   <li>{@link ClassUsageMarker#visitNestHostAttribute(Clazz, NestHostAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitNestMembersAttribute(Clazz, NestMembersAttribute)}
   *   <li>
   * {@link ClassUsageMarker#visitPermittedSubclassesAttribute(Clazz, PermittedSubclassesAttribute)}
   *   <li>{@link ClassUsageMarker#getExtraConstantVisitor()}
   *   <li>{@link ClassUsageMarker#getUsageMarker()}
   * </ul>
   */
  @Test
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

    // Assert that nothing has changed
    assertTrue(actualExtraConstantVisitor instanceof ClassRenamer);
    assertSame(extraConstantVisitor, actualExtraConstantVisitor);
  }

  /**
   * Method under test: {@link ClassUsageMarker#ClassUsageMarker()}
   */
  @Test
  void testNewClassUsageMarker() {
    // Arrange, Act and Assert
    assertNull((new ClassUsageMarker()).getExtraConstantVisitor());
  }

  /**
   * Method under test:
   * {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker)}
   */
  @Test
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
   * Method under test:
   * {@link ClassUsageMarker#ClassUsageMarker(SimpleUsageMarker, ClassUsageMarker.MarkingMode)}
   */
  @Test
  void testNewClassUsageMarker3() {
    // Arrange
    SimpleUsageMarker usageMarker = new SimpleUsageMarker();

    // Act
    ClassUsageMarker actualClassUsageMarker = new ClassUsageMarker(usageMarker, ClassUsageMarker.MarkingMode.SHRINKING);

    // Assert
    assertNull(actualClassUsageMarker.getExtraConstantVisitor());
    assertSame(usageMarker, actualClassUsageMarker.getUsageMarker());
  }
}
