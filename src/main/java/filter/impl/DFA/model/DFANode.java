package filter.impl.DFA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DFANode {

    String val;

    boolean isLeaf = true;

    HashMap<String, DFANode> children;

    public DFANode setVal(String val) {
        this.val = val;
        return this;
    }

    public DFANode setLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public DFANode setChildren(HashMap children) {
        this.children = children;
        return this;
    }

    public DFANode clone() {
        return new DFANode(this.val, this.isLeaf, (HashMap<String, DFANode>) this.children.clone());
    }

}
