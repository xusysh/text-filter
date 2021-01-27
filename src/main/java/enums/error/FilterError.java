package enums.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FilterError implements ErrorCodeEnum {

    TEXT_FILTER_001(""),
    ;

    String msg;

    @Override
    public String getCode() {
        return this.name();
    }
}
