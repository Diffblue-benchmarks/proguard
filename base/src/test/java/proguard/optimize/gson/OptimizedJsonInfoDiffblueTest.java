package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

class OptimizedJsonInfoDiffblueTest {
  /**
   * Method under test: {@link OptimizedJsonInfo#assignIndices()}
   */
  @Test
  void testAssignIndices() {
    // Arrange
    OptimizedJsonInfo optimizedJsonInfo = new OptimizedJsonInfo();

    // Act
    optimizedJsonInfo.assignIndices();

    // Assert that nothing has changed
    assertTrue(optimizedJsonInfo.classIndices.isEmpty());
  }

  /**
   * Method under test: {@link OptimizedJsonInfo#assignIndices()}
   */
  @Test
  void testAssignIndices2() {
    // Arrange
    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.put("foo", 1);
    OptimizedJsonInfo optimizedJsonInfo = new OptimizedJsonInfo();
    optimizedJsonInfo.classIndices = stringIntegerMap;

    // Act
    optimizedJsonInfo.assignIndices();

    // Assert
    Map<String, Integer> stringIntegerMap2 = optimizedJsonInfo.classIndices;
    assertEquals(1, stringIntegerMap2.size());
    assertEquals(0, stringIntegerMap2.get("foo").intValue());
  }

  /**
   * Method under test: {@link OptimizedJsonInfo#assignIndices()}
   */
  @Test
  void testAssignIndices3() {
    // Arrange
    HashMap<String, Integer> stringIntegerMap = new HashMap<>();
    stringIntegerMap.computeIfPresent("foo", mock(BiFunction.class));
    stringIntegerMap.put("foo", 1);
    OptimizedJsonInfo optimizedJsonInfo = new OptimizedJsonInfo();
    optimizedJsonInfo.classIndices = stringIntegerMap;

    // Act
    optimizedJsonInfo.assignIndices();

    // Assert
    Map<String, Integer> stringIntegerMap2 = optimizedJsonInfo.classIndices;
    assertEquals(1, stringIntegerMap2.size());
    assertEquals(0, stringIntegerMap2.get("foo").intValue());
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link OptimizedJsonInfo.ClassJsonInfo}
   */
  @Test
  void testClassJsonInfoNewClassJsonInfo() {
    // Arrange and Act
    OptimizedJsonInfo.ClassJsonInfo actualClassJsonInfo = new OptimizedJsonInfo.ClassJsonInfo();

    // Assert
    assertTrue(actualClassJsonInfo.javaToJsonFieldNames.isEmpty());
    assertTrue(actualClassJsonInfo.exposedJavaFieldNames.isEmpty());
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link OptimizedJsonInfo}
   */
  @Test
  void testNewOptimizedJsonInfo() {
    // Arrange and Act
    OptimizedJsonInfo actualOptimizedJsonInfo = new OptimizedJsonInfo();

    // Assert
    assertTrue(actualOptimizedJsonInfo.classIndices.isEmpty());
    assertTrue(actualOptimizedJsonInfo.classJsonInfos.isEmpty());
    assertTrue(actualOptimizedJsonInfo.jsonFieldIndices.isEmpty());
  }
}
