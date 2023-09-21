package beachcombing.backend.domain.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile multipartFile) {
        // 파일 이름
        // 파일명 중복을 방지하기 위한 UUID 추가
        String fileName = UUID.randomUUID() + "." + multipartFile.getOriginalFilename();

        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        // 업로드
        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // 이미지 업로드 확인용 퍼블릭 이미지 액세스 허용 옵션 추가
        } catch (IOException e) {
            log.error("S3 파일 업로드에 실패했습니다. {}", e.getMessage());
            throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
        }

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    public String processImage(String image) {

        if (image.isEmpty()) {
            return null;
        }

        // 구글 프로필 적용 시
//        if (image.startsWith("https://")) { // 구글 프로필 이미지 처리
//            return image;
//        }

        return "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + image;
    }

}
