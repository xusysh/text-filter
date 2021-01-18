package filter.impl.DFA;

import filter.impl.BaseFilter;

import java.util.HashMap;
import java.util.Objects;

public class DFAFilter extends BaseFilter {

    /**
     * tire图中的边
     */
    HashMap<String, HashMap> edges;

    @Override
    public void loadRules(String[] rules) {
        super.loadRules(rules);
        this.loadEdges();
    }

    private void loadEdges() {
        if (Objects.isNull(this.edges) || this.edges.size() == 0) {
            return;
        }
        for (String rule : rules) {
            this.loadRuleEdges(rule);
        }
    }

    private void loadRuleEdges(String rule) {
        
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
