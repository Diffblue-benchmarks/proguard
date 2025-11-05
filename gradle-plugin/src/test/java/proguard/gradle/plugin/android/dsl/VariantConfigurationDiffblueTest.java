package proguard.gradle.plugin.android.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class VariantConfigurationDiffblueTest {
  /**
   * Method under test: {@link VariantConfiguration#configuration(String)}
   */
  @Test
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
   * Method under test: {@link VariantConfiguration#configurations(String[])}
   */
  @Test
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
   * Method under test: {@link VariantConfiguration#consumerRuleFilter(String[])}
   */
  @Test
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

    // Assert that nothing has changed
    assertEquals("Name", variantConfiguration.getName());
    assertTrue(actualConfigurations.isEmpty());
    assertTrue(actualConsumerRuleFilter.isEmpty());
    assertSame(configurations, actualConfigurations);
    assertSame(consumerRuleFilter, actualConsumerRuleFilter);
  }

  /**
   * Method under test: {@link VariantConfiguration#VariantConfiguration(String)}
   */
  @Test
  void testNewVariantConfiguration() {
    // Arrange and Act
    VariantConfiguration actualVariantConfiguration = new VariantConfiguration("Name");

    // Assert
    assertEquals("Name", actualVariantConfiguration.getName());
    assertTrue(actualVariantConfiguration.getConfigurations().isEmpty());
    assertTrue(actualVariantConfiguration.getConsumerRuleFilter().isEmpty());
  }
}
