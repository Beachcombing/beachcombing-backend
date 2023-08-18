package beachcombing.backend.domain.notification.domain;

import beachcombing.backend.domain.common.domain.BaseEntity;
import beachcombing.backend.domain.member.domain.Member;
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

    public static Notification createNotification(Member member, String title, String message, String details) {
        return Notification.builder()
                .member(member)
                .title(title)
                .message(message)
                .details(details)
                .build();
    }
}
