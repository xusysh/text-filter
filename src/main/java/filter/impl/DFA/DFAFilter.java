package filter.impl.DFA;

import enums.FilterType;
import enums.WildcardType;
import filter.impl.BaseFilter;
import filter.impl.DFA.model.DFANode;
import org.apache.commons.lang3.ArrayUtils;
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
            String[] words = this.getWords(rule);
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

    @Override
    public Boolean match(String target) {
        String[] words = this.getWords(target);
        DFANode curNode = this.treeRoot;
        for (String word : words) {
            curNode = curNode.getChildren().get(word);
            if (Objects.isNull(curNode)) return false;
        }
        if (!curNode.isLeaf()) return false;
        return true;
    }

    @Override
    public Boolean matchWithWildcards(String target) {
        String[] words = this.getWords(target);
        DFANode curNode = this.treeRoot;
        return this.matchWildcardsWords(words, curNode);
    }

    // todo: tail recursion
    public Boolean matchWildcardsWords(String[] words, DFANode curNode) {
        boolean inExpr = false;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (this.type.equals(FilterType.CHAR)) {
                char wildCardChar = word.charAt(0);
                WildcardType wildCardType = WildcardType.getEnumFromValue(wildCardChar);
                Collection<DFANode> childNodeList;
                String[] nextWords;
                switch (wildCardType) {
                    case ASTER_RISK:
                        // word ends
                        if (words.length != i + 1) {
                            String nextWord = words[i + 1];
                            if (nextWord.equals(curNode.getVal())) {
                                nextWords = ArrayUtils.subarray(words, i + 1, words.length);
                                return this.matchWildcardsWords(nextWords, curNode);
                            }
                        } else if(curNode.isLeaf()) return true;
                        nextWords = ArrayUtils.subarray(words, i, words.length);
                        childNodeList = curNode.getChildren().values();
                        return childNodeList.stream().anyMatch(node -> this.matchWildcardsWords(nextWords, node));
                    case QUESTION_MARK:
                        if (words.length == i + 1) return curNode.isLeaf();
                        childNodeList = curNode.getChildren().values();
                        nextWords = ArrayUtils.subarray(words, i + 1, words.length);
                        return childNodeList.stream().anyMatch(node -> this.matchWildcardsWords(nextWords, node));
                    case OPEN_BRACE:
                        break;
                    case CLOSE_BRACE:
                        break;
                    case OPEN_BRACKET:
                        inExpr = true;
                        break;
                    case CLOSE_BRACKET:
                        inExpr = false;
                        break;
                    case HYPHEN:
                        break;
                    case CARET:
                    case EXCLAMATION_MARK:
                        break;
                    case NONE:

                    default:
                        throw new EnumConstantNotPresentException(WildcardType.class, String.valueOf(wildCardChar));
                }
            }
            curNode = curNode.getChildren().get(word);
            if (Objects.isNull(curNode)) return false;
            i++;
        }
        if (!curNode.isLeaf()) return false;
        return true;
    }

    private String[] getWords(String target) {
        return FilterType.WORD.equals(this.type) ?
                StringUtils.split(target, separator) :
                target.split("");
    }

    @Override
    public List<Boolean> match(String[] targetArr) {
        for (String target : targetArr) {

        }
        return null;
    }

    @Override
    public String filter(String target) {
        return null;
    }

    @Override
    public String[] filter(String[] target) {
        return new String[0];
    }

}