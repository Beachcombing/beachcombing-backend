package beachcombing.backend.global.config.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getError());
        this.errorCode = errorCode;
    }

}
