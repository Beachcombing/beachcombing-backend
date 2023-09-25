package beachcombing.backend.domain.notification.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.domain.member.event.NotificationCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private String title;
    private String message;
    private String details;
    @Builder
    public Notification(Member member, String title, String message, String details) {
        this.member = member;
        this.title = title;
        this.message = message;
        this.details = details;
    }

    public static Notification createNotification(Member member, NotificationCode notificationCode) {
        return Notification.builder()
                .member(member)
                .title(notificationCode.getTitle())
                .message(notificationCode.getMessage())
                .details(notificationCode.getDetails())
                .build();
    }

}
