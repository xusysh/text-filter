package builder;

import enums.FilterImpl;
import filter.api.Filter;
import filter.impl.ACA.ACAFilter;
import filter.impl.DFA.DFAFilter;
import filter.impl.KMP.KMPFilter;

public class FilterBuilder {

    FilterImpl impl = FilterImpl.DFA;

    public static FilterBuilder getBuilder() {
        return new FilterBuilder();
    }

    public FilterBuilder setImpl(FilterImpl impl) {
        this.impl = impl;
        return this;
    }

    public Filter getFilter() {
        switch (impl) {
            // case KMP:
            //     return new KMPFilter();
            case DFA:
                return new DFAFilter();
            // case ACA:
            //     return new ACAFilter();
            default:
                throw new EnumConstantNotPresentException(FilterImpl.class, impl.name());
        }
    }

}
