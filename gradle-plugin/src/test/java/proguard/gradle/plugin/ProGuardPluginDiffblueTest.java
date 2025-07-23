package proguard.gradle.plugin;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.internal.project.LifecycleAwareProject;
import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.internal.extensibility.DefaultConvention;
import org.gradle.internal.instantiation.InstanceGenerator;
import org.gradle.invocation.GradleLifecycleActionExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProGuardPluginDiffblueTest {
  /**
   * Test {@link ProGuardPlugin#apply(Project)} with {@code project}.
   *
   * <ul>
   *   <li>Then throw {@link GradleException}.
   * </ul>
   *
   * <p>Method under test: {@link ProGuardPlugin#apply(Project)}
   */
  @Test
  @DisplayName("Test apply(Project) with 'project'; then throw GradleException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProGuardPlugin.apply(Project)"})
  void testApplyWithProject_thenThrowGradleException() {
    // Arrange
    ProGuardPlugin proGuardPlugin = new ProGuardPlugin();
    ProjectInternal delegate = mock(ProjectInternal.class);
    when(delegate.getExtensions()).thenReturn(new DefaultConvention(mock(InstanceGenerator.class)));
    GradleLifecycleActionExecutor gradleLifecycleActionExecutor =
        mock(GradleLifecycleActionExecutor.class);
    doNothing().when(gradleLifecycleActionExecutor).executeBeforeProjectFor(Mockito.<Project>any());

    // Act and Assert
    assertThrows(
        GradleException.class,
        () ->
            proGuardPlugin.apply(
                new LifecycleAwareProject(
                    delegate, mock(ProjectInternal.class), gradleLifecycleActionExecutor)));
    verify(delegate, atLeast(1)).getExtensions();
    verify(gradleLifecycleActionExecutor, atLeast(1)).executeBeforeProjectFor(isA(Project.class));
  }
}
