package enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum WildcardType {

    ASTER_RISK('*'),
    QUESTION_MARK('?'),
    OPEN_BRACE('{'),
    CLOSE_BRACE('}'),
    OPEN_BRACKET('['),
    CLOSE_BRACKET(']'),
    HYPHEN('-'),
    CARET('^'),
    EXCLAMATION_MARK('!'),
    NONE
    ;

    Character value;

    public static WildcardType getEnumFromValue(char value) {
        switch (value) {
            case '*':
                return ASTER_RISK;
            case '?':
                return QUESTION_MARK;
            case '{':
                return OPEN_BRACE;
            case '}':
                return CLOSE_BRACE;
            case '[':
                return OPEN_BRACKET;
            case ']':
                return CLOSE_BRACKET;
            case '-':
                return HYPHEN;
            case '^':
                return CARET;
            case '!':
                return EXCLAMATION_MARK;
            default:
                return NONE;
        }
    }

}
