package exception;

import enums.error.ErrorCodeEnum;
import enums.error.FilterError;

public class ProjectRuntimeException extends RuntimeException {

    public ProjectRuntimeException(ErrorCodeEnum errorCodeEnum) {
        super(FilterError.TEXT_FILTER_001.name());
    }

}
