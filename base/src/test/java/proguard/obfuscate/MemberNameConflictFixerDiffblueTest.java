package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.util.WarningPrinter;

class MemberNameConflictFixerDiffblueTest {
  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(false, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(false);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField4() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    memberNameConflictFixer.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(false, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(false);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link MemberNameConflictFixer#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    HashMap<Object, Object> descriptorMap = new HashMap<>();
    WarningPrinter warningPrinter = new WarningPrinter(new PrintWriter(new StringWriter()));
    NumericNameFactory nameFactory = new NumericNameFactory();
    MemberNameConflictFixer memberNameConflictFixer = new MemberNameConflictFixer(true, descriptorMap, warningPrinter,
        new MemberObfuscator(true, nameFactory, new HashMap<>()));
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo("Processing Info");

    // Act
    memberNameConflictFixer.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(programClass, atLeast(1)).getString(eq(0));
  }
}
