import builder.FilterBuilder;
import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatchWildcardsTest2 {

    @Test
    public void matchWordTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.WORD)
                .setSeparator("/")
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");


    }

    @Test
    public void bracketTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        try {
            assertEquals(true, filter.matchWithWildcards("/scms/*?/cardSts/queryCardFinStatus"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Expression parsing error: brace does not match"));
        }

    }

    @Test
    public void braceTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");


        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-project"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-bank"));

    }

    @Test
    public void escapeTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules2.txt");

    }

}
