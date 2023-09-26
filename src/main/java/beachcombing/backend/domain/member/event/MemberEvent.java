package beachcombing.backend.domain.member.event;

import beachcombing.backend.domain.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MemberEvent {
    private final Member member;
    private final NotificationCode notificationCode;

    public static MemberEvent createMemberEvent(Member member, NotificationCode notificationCode){
        return MemberEvent.builder()
                .member(member)
                .notificationCode(notificationCode)
                .build();
    }
}
