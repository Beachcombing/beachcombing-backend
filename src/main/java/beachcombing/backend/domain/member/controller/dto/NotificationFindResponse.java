package beachcombing.backend.domain.member.controller.dto;

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
}
