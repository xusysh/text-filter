package filter.impl.DFA.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class DFANode {

    String val;

    boolean isLeaf = true;

    HashMap<String, DFANode> children;

}
