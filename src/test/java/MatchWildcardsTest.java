import builder.FilterBuilder;
import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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

//        assertEquals(true, filter.matchWithWildcards("*"));
//        assertEquals(true, filter.matchWithWildcards("/*"));
//        assertEquals(true, filter.matchWithWildcards("/clf/*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRoleInfo"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/find*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/find*Info"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRole*Info"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRole*Info"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p2/*/findUserRole*Info*"));
//        assertEquals(true, filter.matchWithWildcards("/dgkh/p*2/u*serMana*ger/findUserRoleInfo*"));
//
//        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRole*sss"));
        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/userManager/findUserRoleInfo*sss"));
//        assertEquals(false, filter.matchWithWildcards("/dgkh/p2/*/findUserRole"));
    }


}
