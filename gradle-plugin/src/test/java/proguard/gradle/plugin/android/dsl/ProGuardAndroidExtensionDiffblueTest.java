package proguard.gradle.plugin.android.dsl;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.internal.project.LifecycleAwareProject;
import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.internal.buildtree.BuildModelParameters;
import org.gradle.internal.cc.impl.CoupledProjectsListener;
import org.gradle.internal.cc.impl.CrossProjectModelAccessInstance;
import org.gradle.internal.cc.impl.CrossProjectModelAccessPattern;
import org.gradle.internal.cc.impl.DynamicCallProblemReporting;
import org.gradle.internal.cc.impl.ProblemReportingCrossProjectModelAccess;
import org.gradle.internal.configuration.problems.ProblemFactory;
import org.gradle.internal.configuration.problems.ProblemsListener;
import org.gradle.invocation.GradleLifecycleActionExecutor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProGuardAndroidExtensionDiffblueTest {
  /**
   * Method under test:
   * {@link ProGuardAndroidExtension#ProGuardAndroidExtension(Project)}
   */
  @Test
  void testNewProGuardAndroidExtension() {
    // Arrange
    ProjectInternal delegate = mock(ProjectInternal.class);
    when(delegate.container(Mockito.<Class<VariantConfiguration>>any()))
        .thenReturn(mock(NamedDomainObjectContainer.class));
    ProjectInternal referrer = mock(ProjectInternal.class);

    // Act
    new ProGuardAndroidExtension(new ProblemReportingCrossProjectModelAccess.ProblemReportingProject(delegate, referrer,
        new CrossProjectModelAccessInstance(CrossProjectModelAccessPattern.DIRECT,
            new LifecycleAwareProject(mock(ProjectInternal.class), mock(ProjectInternal.class),
                mock(GradleLifecycleActionExecutor.class))),
        mock(ProblemsListener.class), mock(CoupledProjectsListener.class), mock(ProblemFactory.class),
        mock(BuildModelParameters.class), mock(DynamicCallProblemReporting.class)));

    // Assert
    verify(delegate).container(isA(Class.class));
  }
}
