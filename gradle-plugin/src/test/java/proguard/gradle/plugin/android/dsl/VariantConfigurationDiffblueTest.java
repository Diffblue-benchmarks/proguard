package proguard.gradle.plugin.android.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class VariantConfigurationDiffblueTest {
  /**
   * Test {@link VariantConfiguration#configuration(String)}.
   * <p>
   * Method under test: {@link VariantConfiguration#configuration(String)}
   */
  @Test
  @DisplayName("Test configuration(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VariantConfiguration.configuration(String)"})
  void testConfiguration() {
    // Arrange
    VariantConfiguration variantConfiguration = new VariantConfiguration("Name");

    // Act
    variantConfiguration.configuration("Config");

    // Assert
    List<ProGuardConfiguration> configurations = variantConfiguration.getConfigurations();
    assertEquals(1, configurations.size());
    ProGuardConfiguration getResult = configurations.get(0);
    assertTrue(getResult instanceof UserProGuardConfiguration);
    assertEquals("Config", getResult.getFilename());
    assertEquals("Config", getResult.getPath());
    assertEquals("Config", getResult.toString());
  }

  /**
   * Test {@link VariantConfiguration#configurations(String[])}.
   * <p>
   * Method under test: {@link VariantConfiguration#configurations(String[])}
   */
  @Test
  @DisplayName("Test configurations(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VariantConfiguration.configurations(String[])"})
  void testConfigurations() {
    // Arrange
    VariantConfiguration variantConfiguration = new VariantConfiguration("Name");

    // Act
    variantConfiguration.configurations("Configs");

    // Assert
    List<ProGuardConfiguration> configurations = variantConfiguration.getConfigurations();
    assertEquals(1, configurations.size());
    ProGuardConfiguration getResult = configurations.get(0);
    assertTrue(getResult instanceof UserProGuardConfiguration);
    assertEquals("Configs", getResult.getFilename());
    assertEquals("Configs", getResult.getPath());
    assertEquals("Configs", getResult.toString());
  }

  /**
   * Test {@link VariantConfiguration#consumerRuleFilter(String[])}.
   * <p>
   * Method under test: {@link VariantConfiguration#consumerRuleFilter(String[])}
   */
  @Test
  @DisplayName("Test consumerRuleFilter(String[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VariantConfiguration.consumerRuleFilter(String[])"})
  void testConsumerRuleFilter() {
    // Arrange
    VariantConfiguration variantConfiguration = new VariantConfiguration("Name");

    // Act
    variantConfiguration.consumerRuleFilter("Filters");

    // Assert
    List<String> consumerRuleFilter = variantConfiguration.getConsumerRuleFilter();
    assertEquals(1, consumerRuleFilter.size());
    assertEquals("Filters", consumerRuleFilter.get(0));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link VariantConfiguration#setConfigurations(List)}
   *   <li>{@link VariantConfiguration#setConsumerRuleFilter(List)}
   *   <li>{@link VariantConfiguration#getConfigurations()}
   *   <li>{@link VariantConfiguration#getConsumerRuleFilter()}
   *   <li>{@link VariantConfiguration#getName()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List VariantConfiguration.getConfigurations()",
      "List VariantConfiguration.getConsumerRuleFilter()", "String VariantConfiguration.getName()",
      "void VariantConfiguration.setConfigurations(List)", "void VariantConfiguration.setConsumerRuleFilter(List)"})
  void testGettersAndSetters() {
    // Arrange
    VariantConfiguration variantConfiguration = new VariantConfiguration("Name");
    ArrayList<ProGuardConfiguration> configurations = new ArrayList<>();

    // Act
    variantConfiguration.setConfigurations(configurations);
    ArrayList<String> consumerRuleFilter = new ArrayList<>();
    variantConfiguration.setConsumerRuleFilter(consumerRuleFilter);
    List<ProGuardConfiguration> actualConfigurations = variantConfiguration.getConfigurations();
    List<String> actualConsumerRuleFilter = variantConfiguration.getConsumerRuleFilter();

    // Assert
    assertEquals("Name", variantConfiguration.getName());
    assertTrue(actualConfigurations.isEmpty());
    assertTrue(actualConsumerRuleFilter.isEmpty());
    assertSame(configurations, actualConfigurations);
    assertSame(consumerRuleFilter, actualConsumerRuleFilter);
  }

  /**
   * Test {@link VariantConfiguration#VariantConfiguration(String)}.
   * <p>
   * Method under test: {@link VariantConfiguration#VariantConfiguration(String)}
   */
  @Test
  @DisplayName("Test new VariantConfiguration(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void VariantConfiguration.<init>(String)"})
  void testNewVariantConfiguration() {
    // Arrange and Act
    VariantConfiguration actualVariantConfiguration = new VariantConfiguration("Name");

    // Assert
    assertEquals("Name", actualVariantConfiguration.getName());
    assertTrue(actualVariantConfiguration.getConfigurations().isEmpty());
    assertTrue(actualVariantConfiguration.getConsumerRuleFilter().isEmpty());
  }
}
