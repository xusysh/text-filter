package filter.api;

public interface Filter {

    void loadRules(String filepath);

    void loadRules(String[] rules);

    void addRules(String[] rules);

    void addRule(String... rule);

    Boolean match(String target);

    Boolean match(String[] target);

    String filter(String target);

    String[] filter(String[] target);

}
