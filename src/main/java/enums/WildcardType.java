package enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum WildcardType {

    ASTER_RISK('*', 0),
    QUESTION_MARK('?', 0),
    OPEN_BRACE('{', 2),
    CLOSE_BRACE('}', 2),
    OPEN_BRACKET('[', 2),
    CLOSE_BRACKET(']', 2),
    HYPHEN('-', 1),
    CARET('^', 3),
    EXCLAMATION_MARK('!', 3),
    ESCAPE('\\', 4),
    NONE;

    Character value;

    Integer privilege;

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
            case '\\':
                return ESCAPE;
            default:
                return NONE;
        }
    }

}
