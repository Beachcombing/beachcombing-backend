package beachcombing.backend.domain.member.domain;

import beachcombing.backend.domain.common.domain.Role;
import beachcombing.backend.domain.member.controller.dto.MemberUpdateRequest;
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

    private Role role; // USER 혹은 ADMIN

    @Builder
    public Profile(String email, String nickname, String image, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.image = image;
        this.role = role;
    }

    public static Profile createProfile(String email, String nickname, String image, Role role){
        return Profile.builder()
                .email(email)
                .nickname(nickname)
                .image(image)
                .role(role)
                .build();
    }

    //image빠짐
    public void updateNicknameAndImage (MemberUpdateRequest request, Boolean isChanged){
        this.nickname = request.getNickname();

        //TODO getIsChanged가 true 이면, image 변경
    }
}