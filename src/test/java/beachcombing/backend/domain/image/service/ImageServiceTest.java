package beachcombing.backend.domain.image.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    @DisplayName("이미지 업로드 테스트")
    void uploadImage() throws IOException {
        // given
        String expected = "test_img.jpeg";
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/images/" + expected);
        MockMultipartFile multipartFile = new MockMultipartFile("test_img",
                expected, "jpeg",
                fileInputStream);

        // when
        String savedFile = imageService.uploadImage(multipartFile);

        // then
        Assertions.assertThat(savedFile).contains(expected);
    }

    @Test
    @DisplayName("이미지 처리 테스트")
    void processImage() {
        // given
        String expected = "test_img.jpeg";

        // when
        String processedImage = imageService.processImage(expected);

        // then
        Assertions.assertThat(processedImage).isEqualTo("https://" + bucket +".s3.ap-northeast-2.amazonaws.com/" + expected);
        System.out.println("processedImage = " + processedImage);
    }
}