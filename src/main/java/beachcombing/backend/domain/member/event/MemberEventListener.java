package beachcombing.backend.domain.member.event;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.notification.domain.Notification;
import beachcombing.backend.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Async
@Component
@Transactional
@RequiredArgsConstructor
public class MemberEventListener {
    private final NotificationRepository notificationRepository;

    @EventListener
    public void handleMemberEvent(MemberEvent memberEvent){
        Member member = memberEvent.getMember();
        NotificationCode notificationCode = memberEvent.getNotificationCode();

        Notification notification = Notification.createNotification(member, notificationCode);
        notificationRepository.save(notification);
    }

}
