package filter.impl.DFA;

import enums.FilterType;
import filter.impl.BaseFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author guojingyu
 */
public class DFAFilter extends BaseFilter {

    /**
     * edges of a tire tree(graph)
     */
    HashMap<String, HashMap> edges;

    public DFAFilter(FilterType type) {
        super(type);
    }

    @Override
    public void loadRules(String filePath) throws IOException {
        super.loadRules(filePath);
        this.loadEdges();
    }

    @Override
    public void addRules(List<String> rules) {
        super.addRules(rules);
        this.loadEdges();
    }

    @Override
    public void addRules(String... rules) {
        super.addRules(rules);
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
        if (this.type.equals(FilterType.WORD)) {
            String[] words = rule.split(seperator);
            // todo
        } else {
            for (char emm : rule.toCharArray()) {
                // todo
            }
        }
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
