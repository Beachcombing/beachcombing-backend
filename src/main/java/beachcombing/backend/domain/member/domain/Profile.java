package beachcombing.backend.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {  // 회원 기본 정보
    private String email;
    @Column(nullable = false, unique = true, length = 20)
    private String nickname;
    private String image; // s3에 저장된 이미지 파일 이름
    private String role; // USER 혹은 ADMIN

    @Builder
    public Profile(String email, String nickname, String image, String role) {
        this.email = email;
        this.nickname = nickname;
        this.image = image;
        this.role = role;
    }
}