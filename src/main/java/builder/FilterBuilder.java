package builder;

import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import filter.impl.ACA.ACAFilter;
import filter.impl.DFA.DFAFilter;
import filter.impl.KMP.KMPFilter;

/**
 * @author guojingyu
 */
public class FilterBuilder {

    FilterImpl impl = FilterImpl.DFA;

    FilterType type = FilterType.CHAR;

    String separator = "";

    public static FilterBuilder getBuilder() {
        return new FilterBuilder();
    }

    public FilterBuilder setImpl(FilterImpl impl) {
        this.impl = impl;
        return this;
    }

    public FilterBuilder setType(FilterType type) {
        this.type = type;
        return this;
    }

    public FilterBuilder setSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public Filter getFilter() {
        switch (impl) {
            // case KMP:
            //     return new KMPFilter();
            case DFA:
                return new DFAFilter(this.type, this.separator);
            // case ACA:
            //     return new ACAFilter();
            default:
                throw new EnumConstantNotPresentException(FilterImpl.class, impl.name());
        }
    }

}
