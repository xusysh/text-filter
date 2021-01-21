package filter.impl.DFA;

import enums.FilterType;
import filter.impl.BaseFilter;
import filter.impl.DFA.model.DFANode;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author guojingyu
 */
public class DFAFilter extends BaseFilter {

    /**
     * root node of tire tree(graph)
     */
    DFANode treeRoot;

    public DFAFilter(FilterType type, String separator) {
        super(type, separator);
        this.treeRoot = new DFANode().setChildren(new HashMap<>());
    }

    @Override
    public void loadRules(String filePath) throws IOException {
        super.loadRules(filePath);
        this.loadTree();
    }

    @Override
    public void addRules(List<String> rules) {
        super.addRules(rules);
        this.loadTree();
    }

    @Override
    public void addRules(String... rules) {
        super.addRules(rules);
        this.loadTree();
    }

    private void loadTree() {
        if (Objects.isNull(this.rules) || this.rules.size() == 0) {
            return;
        }
        DFANode curNode;
        for (String rule : rules) {
            DFANode preNode = this.treeRoot;
            Collection<String> words = this.getWords(rule);
            for (String word : words) {
                curNode = preNode.getChildren().get(word);
                if (Objects.isNull(curNode)) {
                    curNode = new DFANode().setVal(word).setLeaf(true).setChildren(new HashMap<>());
                    preNode.setLeaf(false).getChildren().put(word, curNode);
                }
                preNode = curNode;
            }
        }
    }

    public Boolean match(String target) {
        Collection<String> words = this.getWords(target);
        DFANode curNode = this.treeRoot;
        for (String word : words) {
            curNode = curNode.getChildren().get(word);
            if(Objects.isNull(curNode)) return false;
        }
        if(!curNode.isLeaf()) return false;
        return true;
    }

    private Collection<String> getWords(String target) {
        return FilterType.WORD.equals(this.type) ?
                Arrays.asList(StringUtils.split(target, separator)) :
                Arrays.asList(target.split(""));
    }

    public List<Boolean> match(String[] targetArr) {
        for(String target:targetArr) {

        }
        return null;
    }

    public String filter(String target) {
        return null;
    }

    public String[] filter(String[] target) {
        return new String[0];
    }

}
