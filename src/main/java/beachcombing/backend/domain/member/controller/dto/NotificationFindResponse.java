package beachcombing.backend.domain.member.controller.dto;

import beachcombing.backend.domain.notification.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationFindResponse {
    public Long notificationId;
    public Long memberId;
    public String title;
    public String message;
    public String details;

    public static NotificationFindResponse from(Notification notification){
        return NotificationFindResponse.builder()
                .notificationId(notification.getId())
                .memberId(notification.getMember().getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .details(notification.getDetails())
                .build();
    }
}
