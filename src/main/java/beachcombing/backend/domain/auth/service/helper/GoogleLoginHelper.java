package beachcombing.backend.domain.auth.service.helper;

import beachcombing.backend.domain.auth.controller.dto.AuthGoogleLoginDto;
import beachcombing.backend.domain.member.domain.Member;
import beachcombing.backend.global.config.exception.CustomException;
import beachcombing.backend.global.config.exception.ErrorCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleLoginHelper {
    public Member getUserData(String idToken){
        try{
            RestTemplate restTemplate = new RestTemplate();

            AuthGoogleLoginDto authGoogleLoginDto = restTemplate.getForEntity(
                    "https://oauth2.googleapis.com/tokeninfo?id_token={idToken}",
                    AuthGoogleLoginDto.class,
                    idToken
            ).getBody();


            return Member.createMemberByGoogleLogin(authGoogleLoginDto);

        }catch (HttpClientErrorException e){
            throw new CustomException(ErrorCode.UNAUTHORIZED_TOKEN);
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
