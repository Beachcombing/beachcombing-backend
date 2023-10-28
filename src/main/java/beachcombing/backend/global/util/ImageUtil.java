package beachcombing.backend.global.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class ImageUtil {

    @Value("${cloud.aws.s3.bucket}")
    private String tempBucket;
    private static String bucket;

    @PostConstruct
    public void init() {
        bucket = tempBucket;
    }

    public static String processImage(String image) {

        if (image == null || image.isEmpty()) {
            return null;
        }

        if (image.startsWith("https://")) { // 구글 프로필 이미지 처리
            return image;
        }

        return "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + image;
    }

}
