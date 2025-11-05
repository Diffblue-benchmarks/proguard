package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.util.WarningPrinter;

class MappingKeeperDiffblueTest {
  /**
   * Method under test: {@link MappingKeeper#processClassMapping(String, String)}
   */
  @Test
  void testProcessClassMapping() {
    // Arrange
    ClassPool classPool = new ClassPool();

    // Act and Assert
    assertFalse((new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter()))))
        .processClassMapping("Class Name", "New Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert that nothing has changed
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping2() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass(1, "Class Name", "Class Name"));

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping3() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new ProgramClass());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping4() {
    // Arrange
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping5() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping6() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("New Field Name");
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping7() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn(new LibraryField(1, "New Class Name", "New Class Name"));
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField, atLeast(1)).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping8() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, null);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping9() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    PrintWriter printWriter = new PrintWriter(new StringWriter());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(printWriter, new ArrayList<>()));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processFieldMapping(String, String, String, String, String)}
   */
  @Test
  void testProcessFieldMapping10() {
    // Arrange
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryField).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryField);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    WarningPrinter warningPrinter = mock(WarningPrinter.class);
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any());
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, warningPrinter);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processFieldMapping("New Class Name", "Field Type", "Field Name", "New Class Name", "New Field Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findField(eq("Field Name"), eq("LField Type;"));
    verify(warningPrinter).print(eq("New Class Name"), eq(
        "Warning: New Class Name: field 'Field Type Field Name' is not being kept as 'Processing Info', but remapped to 'New Field Name'"));
    verify(warningPrinter).print(eq("Class Name"), eq("Processing Info"),
        eq("Warning: Class Name is not being kept as 'Processing Info', but remapped to 'New Class Name'"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryField, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert that nothing has changed
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping2() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new LibraryClass(1, "Class Name", "Class Name"));

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping3() {
    // Arrange
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(new ProgramClass());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping4() {
    // Arrange
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryMethod(1, "Name", "Descriptor"));
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping5() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping6() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("New Method Name");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping7() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new LibraryField(1, "New Class Name", "New Class Name"));
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(new PrintWriter(new StringWriter())));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod, atLeast(1)).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping8() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, null);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping9() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    PrintWriter printWriter = new PrintWriter(new StringWriter());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, new WarningPrinter(printWriter, new ArrayList<>()));
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping10() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    WarningPrinter warningPrinter = mock(WarningPrinter.class);
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any());
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, warningPrinter);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "Method Arguments",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(LMethod Arguments;)LMethod Return Type;"));
    verify(warningPrinter).print(eq("New Class Name"), eq(
        "Warning: New Class Name: method 'Method Return Type Method Name(Method Arguments)' is not being kept as 'Processing Info', but remapped to 'New Method Name'"));
    verify(warningPrinter).print(eq("Class Name"), eq("Processing Info"),
        eq("Warning: Class Name is not being kept as 'Processing Info', but remapped to 'New Class Name'"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping11() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    WarningPrinter warningPrinter = mock(WarningPrinter.class);
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any());
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, warningPrinter);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", " ",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("()LMethod Return Type;"));
    verify(warningPrinter).print(eq("New Class Name"), eq(
        "Warning: New Class Name: method 'Method Return Type Method Name( )' is not being kept as 'Processing Info', but remapped to 'New Method Name'"));
    verify(warningPrinter).print(eq("Class Name"), eq("Processing Info"),
        eq("Warning: Class Name is not being kept as 'Processing Info', but remapped to 'New Class Name'"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping12() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    WarningPrinter warningPrinter = mock(WarningPrinter.class);
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any());
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, warningPrinter);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name",
        "' is not being kept as '", "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(L is not being kept as ;)LMethod Return Type;"));
    verify(warningPrinter).print(eq("New Class Name"), eq(
        "Warning: New Class Name: method 'Method Return Type Method Name(' is not being kept as ')' is not being kept as 'Processing Info', but remapped to 'New Method Name'"));
    verify(warningPrinter).print(eq("Class Name"), eq("Processing Info"),
        eq("Warning: Class Name is not being kept as 'Processing Info', but remapped to 'New Class Name'"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link MappingKeeper#processMethodMapping(String, int, int, String, String, String, String, int, int, String)}
   */
  @Test
  void testProcessMethodMapping13() {
    // Arrange
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");
    doNothing().when(libraryMethod).setProcessingInfo(Mockito.<Object>any());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn("Processing Info");
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(libraryMethod);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    ClassPool classPool = mock(ClassPool.class);
    when(classPool.getClass(Mockito.<String>any())).thenReturn(libraryClass);
    WarningPrinter warningPrinter = mock(WarningPrinter.class);
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any());
    doNothing().when(warningPrinter).print(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

    MappingKeeper mappingKeeper = new MappingKeeper(classPool, warningPrinter);
    mappingKeeper.processClassMapping("Class Name", "New Class Name");

    // Act
    mappingKeeper.processMethodMapping("New Class Name", 2, 2, "Method Return Type", "Method Name", "'",
        "New Class Name", 2, 2, "New Method Name");

    // Assert
    verify(classPool).getClass(eq("Class Name"));
    verify(libraryClass).findMethod(eq("Method Name"), eq("(L;)LMethod Return Type;"));
    verify(warningPrinter).print(eq("New Class Name"), eq(
        "Warning: New Class Name: method 'Method Return Type Method Name(')' is not being kept as 'Processing Info', but remapped to 'New Method Name'"));
    verify(warningPrinter).print(eq("Class Name"), eq("Processing Info"),
        eq("Warning: Class Name is not being kept as 'Processing Info', but remapped to 'New Class Name'"));
    verify(libraryClass).getProcessingInfo();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
    verify(libraryMethod).setProcessingInfo(isA(Object.class));
  }
}
