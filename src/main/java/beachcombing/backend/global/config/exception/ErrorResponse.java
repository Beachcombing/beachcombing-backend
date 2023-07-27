package beachcombing.backend.global.config.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@Getter
@Builder
public class ErrorResponse {
    private final String status;
    private final String error;

    public static ResponseEntity<ErrorResponse> toResponseEntity(CustomException e){
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(
                        ErrorResponse.builder()
                                .status(errorCode.getStatus().name())
                                .error(errorCode.getError())
                                .build()
                );

    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus status, FieldError fieldError){
        return ResponseEntity
                .status(status)
                .body(
                        ErrorResponse.builder()
                                .status(status.name())
                                .error(fieldError.getDefaultMessage())
                                .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(
                        ErrorResponse.builder()
                                .status(status.name())
                                .build()
                );
    }


}
