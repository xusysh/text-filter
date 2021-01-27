package filter.impl.DFA;

import enums.FilterType;
import enums.WildcardType;
import filter.impl.BaseFilter;
import filter.impl.DFA.model.DFANode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
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

//    HashMap<WildcardType, Function<Object>> wildcardToHandler;

    public DFAFilter(FilterType type, String separator) {
        super(type, separator);
        this.treeRoot = new DFANode().setChildren(new HashMap<>());
//        wildcardToHandler.put(WildcardType.ASTER_RISK,this::loadTree);
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
        return curNode.isLeaf();
    }

    @Override
    public Boolean matchWithWildcards(String target) {
        String[] words = this.getWords(target);
        DFANode curNode = this.treeRoot;
        return this.matchWildcardsWords(words, curNode);
    }

    // todo: recursion -> iteration/tail recursion
    public Boolean matchWildcardsWords(String[] words, DFANode curNode) {
        boolean inExpr = false;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (this.type.equals(FilterType.CHAR)) {
                char wildCardChar = word.charAt(0);
                WildcardType wildCardType = WildcardType.getEnumFromValue(wildCardChar);
                if (i > 0 && words[i - 1].equals("\\"))
                    wildCardType = WildcardType.NONE;
                Collection<DFANode> childNodeList;
                String[] nextWords;
                String nextWord;
                switch (wildCardType) {
                    case ASTER_RISK:
                        while (i + 1 < words.length) {
                            nextWord = words[i + 1];
                            if (nextWord.charAt(0) == WildcardType.QUESTION_MARK.getValue())
                                throw new IllegalArgumentException("'?' should not be the next of '*'");
                            if (nextWord.charAt(0) != WildcardType.ASTER_RISK.getValue()
                                    && nextWord.charAt(0) != WildcardType.QUESTION_MARK.getValue())
                                break;
                            i++;
                        }
                        if (words.length == i + 1) return true;
                        if (curNode.isLeaf()) return false;
                        if (curNode.getChildren().containsKey(words[i + 1])) {
                            nextWords = ArrayUtils.subarray(words, i + 1, words.length);
                            if (this.matchWildcardsWords(nextWords, curNode)) return true;
                        }
                        final String[] nextWordsFinal = ArrayUtils.subarray(words, i, words.length);
                        childNodeList = curNode.getChildren().values();
                        return childNodeList.stream().anyMatch(node -> this.matchWildcardsWords(nextWordsFinal, node));
                    case QUESTION_MARK:
                        childNodeList = curNode.getChildren().values();
                        if (words.length == i + 1) return childNodeList.stream().anyMatch(DFANode::isLeaf);
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
                    case EXCAPE:
                        break;
                    case NONE:
                        if (!inExpr) {
                            curNode = curNode.getChildren().get(word);
                        }
                        break;
                    default:
                        throw new EnumConstantNotPresentException(WildcardType.class, String.valueOf(wildCardChar));
                }
            }
            if (Objects.isNull(curNode)) return false;
        }
        return curNode.isLeaf();
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