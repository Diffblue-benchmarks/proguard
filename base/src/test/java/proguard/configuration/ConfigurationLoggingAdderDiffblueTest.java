package proguard.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import proguard.AppView;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;

class ConfigurationLoggingAdderDiffblueTest {
  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();
    AppView appView = new AppView();

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(3, appView.programClassPool.size());
  }

  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute2() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", new ProgramClass());
    AppView appView = new AppView(programClassPool, new ClassPool());

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(4, appView.programClassPool.size());
  }

  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute3() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", new LibraryClass(4, "java/lang/Class", "java/lang/Class"));
    AppView appView = new AppView(programClassPool, new ClassPool());

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(4, appView.programClassPool.size());
  }

  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute4() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java/lang/Class", new ProgramClass());
    AppView appView = new AppView(programClassPool, new ClassPool());

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(4, appView.programClassPool.size());
  }

  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute5() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("java/lang/Class", new LibraryClass(4, "java/lang/Class", "java/lang/Class"));
    AppView appView = new AppView(programClassPool, new ClassPool());

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(4, appView.programClassPool.size());
  }

  /**
   * Method under test: {@link ConfigurationLoggingAdder#execute(AppView)}
   */
  @Test
  void testExecute6() throws IOException {
    // Arrange
    ConfigurationLoggingAdder configurationLoggingAdder = new ConfigurationLoggingAdder();

    LibraryClass clazz = new LibraryClass(4, "java/lang/Class", "java/lang/Class");
    clazz.addProcessingFlags(-1, 4, 2, 4);

    ClassPool programClassPool = new ClassPool();
    programClassPool.addClass("Name", clazz);
    AppView appView = new AppView(programClassPool, new ClassPool());

    // Act
    configurationLoggingAdder.execute(appView);

    // Assert
    assertEquals(4, appView.programClassPool.size());
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ConfigurationLoggingAdder}
   */
  @Test
  void testNewConfigurationLoggingAdder() {
    // Arrange, Act and Assert
    assertEquals("proguard.configuration.ConfigurationLoggingAdder", (new ConfigurationLoggingAdder()).getName());
  }
}
