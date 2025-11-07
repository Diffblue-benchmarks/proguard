package proguard.obfuscate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Member;

class MemberNameCollectorDiffblueTest {
  /**
   * Test {@link MemberNameCollector#visitAnyMember(Clazz, Member)}.
   * <p>
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameCollector.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(true, new HashMap<>());
    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");

    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberNameCollector#visitAnyMember(Clazz, Member)}.
   * <p>
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameCollector.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember2() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(false, new HashMap<>());
    Clazz clazz = mock(Clazz.class);
    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberNameCollector#visitAnyMember(Clazz, Member)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link Clazz#extendsOrImplements(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCollector#visitAnyMember(Clazz, Member)}
   */
  @Test
  @DisplayName("Test visitAnyMember(Clazz, Member); given 'true'; then calls extendsOrImplements(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MemberNameCollector.visitAnyMember(Clazz, Member)"})
  void testVisitAnyMember_givenTrue_thenCallsExtendsOrImplements() {
    // Arrange
    MemberNameCollector memberNameCollector = new MemberNameCollector(true, new HashMap<>());
    Clazz clazz = mock(Clazz.class);
    when(clazz.extendsOrImplements(Mockito.<String>any())).thenReturn(true);
    Member member = mock(Member.class);
    when(member.getDescriptor(Mockito.<Clazz>any())).thenReturn("Descriptor");
    when(member.getProcessingInfo()).thenReturn("Processing Info");
    when(member.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    memberNameCollector.visitAnyMember(clazz, member);

    // Assert
    verify(clazz).extendsOrImplements(eq("java/lang/annotation/Annotation"));
    verify(member).getDescriptor(isA(Clazz.class));
    verify(member).getName(isA(Clazz.class));
    verify(member, atLeast(1)).getProcessingInfo();
  }
}
