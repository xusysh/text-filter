package filter.impl;

import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;

public class BaseFilter implements Filter {

    FilterImpl impl = FilterImpl.DFA;

    FilterType type = FilterType.MATCH;

    protected String[] rules;

    public void loadRules(String filepath) {

    }

    public void loadRules(String[] rules) {

    }

    public void addRules(String[] rules) {

    }

    public void addRule(String... rule) {

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
