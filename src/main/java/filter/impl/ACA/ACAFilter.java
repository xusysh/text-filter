package filter.impl.ACA;

import enums.FilterType;
import filter.impl.BaseFilter;

/**
 * @author guojingyu
 */
public class ACAFilter extends BaseFilter {

    public ACAFilter(FilterType type) {
        super(type);
    }

    public Boolean match(String target) {
        return null;
    }

    public Boolean match(String[] target) {
        return null;
    }

    public String filter(String target) {
        return null;
    }

    public String[] filter(String[] target) {
        return new String[0];
    }

}
