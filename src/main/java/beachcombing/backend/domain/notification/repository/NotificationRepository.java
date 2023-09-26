package beachcombing.backend.domain.notification.repository;

import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByMember(Member member);
}
