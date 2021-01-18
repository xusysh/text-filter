package filter.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author guojingyu
 */
public interface Filter {

    void loadRules(String filepath) throws IOException;

    void addRules(List<String> rules);

    void addRules(String... rule);

    void clearRules();

    Boolean match(String target);

    Boolean match(String[] target);

    String filter(String target);

    String[] filter(String[] target);

}
