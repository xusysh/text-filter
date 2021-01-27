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
    public void braceTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-project"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-bank"));
        assertEquals(true,filter.matchWithWildcards("/tbms/common/query-org-project"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-{project,bank}"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-{project,bank,123}"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-{project,123}"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-{project,123,banl}"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-{project1,123,bank}"));
        assertEquals(true,filter.matchWithWildcards("/{clf,tbms}/common/query-org-{project1,123,bank}"));
        assertEquals(true,filter.matchWithWildcards("/{clf,tbms}/common/query-org-{bank}"));
        assertEquals(true,filter.matchWithWildcards("/tbms/{common,p2}/{query-org-bank,query-org-project}"));

        assertEquals(false,filter.matchWithWildcards("/{clf1,tbms}/common/query-org-{project1,123,bank}"));
        assertEquals(false,filter.matchWithWildcards("/{clf,tbms}/common/query-org-{project1,123,bank1}"));
        assertEquals(false,filter.matchWithWildcards("/tbms/{p2}/{query-org-bank,query-org-project}"));

//        try {
//            assertEquals(true, filter.matchWithWildcards("/scms/*?/cardSts/queryCardFinStatus"));
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Expression parsing error: brace does not match"));
//        }

    }

    @Test
    public void bracketTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");


        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-project"));
        assertEquals(true,filter.matchWithWildcards("/clf/common/query-org-bank"));
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
