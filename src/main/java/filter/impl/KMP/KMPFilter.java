package filter.impl.KMP;

import enums.FilterType;
import filter.impl.BaseFilter;

import java.util.List;

/**
 * @author guojingyu
 */
public class KMPFilter extends BaseFilter {

    public KMPFilter(FilterType type, String separator) {
        super(type, separator);
    }

    public Boolean match(String target) {
        return null;
    }

    public List<Boolean> match(String[] target) {
        return null;
    }

    public String filter(String target) {
        return null;
    }

    public String[] filter(String[] target) {
        return new String[0];
    }

}
