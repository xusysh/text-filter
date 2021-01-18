package filter.impl;

import filter.api.Filter;

public class BaseFilter implements Filter {

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
