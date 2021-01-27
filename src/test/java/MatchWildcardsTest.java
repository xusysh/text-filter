import builder.FilterBuilder;
import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatchWildcardsTest {

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
    public void asterTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        assertEquals(true, filter.matchWithWildcards("*"));
        assertEquals(true, filter.matchWithWildcards("/*"));
        assertEquals(true, filter.matchWithWildcards("/clf/*"));
        assertEquals(true, filter.matchWithWildcards("/*/p2/*"));
        assertEquals(true, filter.matchWithWildcards("/*/p2/userManager/findUserRoleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRoleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/find*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/find*Info"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRole*Info"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRole*Info"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRole*Info*"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p*2/u*serMana*ger/findUserRoleInfo*"));
        assertEquals(true, filter.matchWithWildcards("/scms/p2/cardSts/queryCardFinStatus"));
        assertEquals(true, filter.matchWithWildcards("/scms/p2/cardSts/query*"));
        assertEquals(true, filter.matchWithWildcards("/scms/p2/*/queryCardFinStatus"));
        assertEquals(true, filter.matchWithWildcards("/scms/*/*/queryCardFinStatus"));
        // Backtracking
        assertEquals(true, filter.matchWithWildcards("/scms/*/queryCardFinStatus"));
        assertEquals(true, filter.matchWithWildcards("/*/queryCardFinStatus"));
        assertEquals(true, filter.matchWithWildcards("/*/query*"));
        assertEquals(true, filter.matchWithWildcards("/*ery*"));
        // overlay
        assertEquals(true, filter.matchWithWildcards("/scms/**/queryCardFinStatus"));
        assertEquals(true, filter.matchWithWildcards("/scms/?*/card*/queryCard???Status"));
        assertEquals(true, filter.matchWithWildcards("/scms/*/card???/queryCardFin??atus"));

        assertEquals(false, filter.matchWithWildcards("/*eryy"));
        assertEquals(false, filter.matchWithWildcards("/*/p2/"));
        assertEquals(false, filter.matchWithWildcards("/*/p3/*"));
        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRole*sss"));
        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*sss"));
        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/*/findUserRole"));

        try {
            assertEquals(true, filter.matchWithWildcards("/scms/*?/cardSts/queryCardFinStatus"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("'?' should not be the next of '*'"));
        }

    }


    @Test
    public void questionMarkTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUser?oleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInf?"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUser?oleInf?"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/user?anager/findUser?oleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dg?h/??/user?anager/findUser?oleInfo"));
        assertEquals(true, filter.matchWithWildcards("/????/p2/user?anager/findUser?oleInfo"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p?/user?an?ger/findUser?oleInf?"));
        assertEquals(true, filter.matchWithWildcards("/dgkh????user?an?ger/findUser?oleInf?"));
        assertEquals(true, filter.matchWithWildcards("?????????user?an?ger/findUser?oleInf?"));
        assertEquals(true, filter.matchWithWildcards("/dgkh/p???????an?ger/findUser?oleInf?"));
        assertEquals(true, filter.matchWithWildcards("?????????????????????????????????????"));
        assertEquals(true, filter.matchWithWildcards("/ssgf/p2/trans/bhSemt/queryProgress"));
        assertEquals(true, filter.matchWithWildcards("/????/p2/trans/bhSemt/?????????????"));
        assertEquals(true, filter.matchWithWildcards("/??????????????????????????????????"));

        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo?"));
        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRole?Info"));
    }

    @Test
    public void asterAndQuestionMarkTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules2.txt");

        assertEquals(true, filter.matchWithWildcards("class"));
        assertEquals(true, filter.matchWithWildcards("cl???"));
        assertEquals(true, filter.matchWithWildcards("cl*"));
        assertEquals(true, filter.matchWithWildcards("muscles"));
        assertEquals(true, filter.matchWithWildcards("???cl*"));
        assertEquals(true, filter.matchWithWildcards("???cl?*"));
        assertEquals(true, filter.matchWithWildcards("??*cl??*"));
        assertEquals(true, filter.matchWithWildcards("?*cl*"));
        assertEquals(true, filter.matchWithWildcards("???*cl??*"));
        assertEquals(true, filter.matchWithWildcards("汗流浃肤"));
        assertEquals(true, filter.matchWithWildcards("汗出沾背"));
        assertEquals(true, filter.matchWithWildcards("虚汗"));
        assertEquals(true, filter.matchWithWildcards("?汗"));
        assertEquals(true, filter.matchWithWildcards("*汗??"));
        assertEquals(true, filter.matchWithWildcards("汗???*"));
        assertEquals(true, filter.matchWithWildcards("汗??*"));
        assertEquals(true, filter.matchWithWildcards("汗?*"));
        assertEquals(true, filter.matchWithWildcards("汗*"));

        assertEquals(false, filter.matchWithWildcards("cl"));
        assertEquals(false, filter.matchWithWildcards("汗?"));
        assertEquals(false, filter.matchWithWildcards("*汗?"));

    }

    @Test
    public void escapeTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules2.txt");

        assertEquals(true, filter.matchWithWildcards("asdf\\*we"));
        assertEquals(true, filter.matchWithWildcards("asdf\\?qwe"));
        assertEquals(true, filter.matchWithWildcards("asdf*we"));
        assertEquals(true, filter.matchWithWildcards("asdf*qwe"));
        assertEquals(true, filter.matchWithWildcards("asdf?qwe"));
        assertEquals(true, filter.matchWithWildcards("asdf?we"));
        assertEquals(true, filter.matchWithWildcards("asdf???"));
        assertEquals(true, filter.matchWithWildcards("asdf????"));
        assertEquals(true, filter.matchWithWildcards("asdf???*"));
        assertEquals(true, filter.matchWithWildcards("asdf*"));

        assertEquals(false, filter.matchWithWildcards("asdf\\*"));
        assertEquals(false, filter.matchWithWildcards("asdf*\\?qwe"));
        assertEquals(false, filter.matchWithWildcards("asdf\\*qwe"));
    }

}
