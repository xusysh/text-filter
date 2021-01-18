package filter.impl.DFA;

import filter.api.Filter;
import filter.impl.BaseFilter;

import java.util.HashMap;

public class DFAFilter extends BaseFilter {

    /**
     * tire图中的边
     */
    HashMap<String, HashMap> edges;

    @Override
    public void loadRules(String[] rules) {
        super.loadRules(rules);
    }

    private void loadEdges() {

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
