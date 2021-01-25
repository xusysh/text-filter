import builder.FilterBuilder;
import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class MatchTest {

    @Test
    public void matchWordTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.WORD)
                .setSeparator("/")
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        assertEquals(true, filter.match("/clf/p2/queryneedadd"));
        assertEquals(true, filter.match("/dgkh/p2/userManager/findUserRoleInfo"));
        assertEquals(true, filter.match("/nanjing/ppas/stm/query-balance"));
        assertEquals(true, filter.match("/opcs/p2/payslice/queryContinExtList"));

        assertEquals(false, filter.match("/opcs/p2/payslice/queryContinExtList/emm"));
        assertEquals(false, filter.match("/p2/payslice/queryContinExtList"));
        assertEquals(false, filter.match("/opcs/p2/payslice/"));
        assertEquals(false, filter.match("/opcs/p2/payslice"));
        assertEquals(false, filter.match("/opcs/p2/payslice/queryContinExtList2"));
        assertEquals(false, filter.match("/opc1/p2/payslice/queryContinExtList"));
        assertEquals(false, filter.match("/opasd"));
        assertEquals(false, filter.match("/a/b/c"));
        assertEquals(false, filter.match(""));

    }

    @Test
    public void matchCharTest() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules2.txt");

        assertEquals(true, filter.match("depths"));
        assertEquals(true, filter.match("demersal"));
        assertEquals(true, filter.match("delf"));
        assertEquals(true, filter.match("decupled"));
        assertEquals(true, filter.match("汗流浃肤"));
        assertEquals(true, filter.match("汗牛马"));
        assertEquals(true, filter.match("汗出沾背"));
        assertEquals(true, filter.match("虚汗"));

        assertEquals(false, filter.match("dept2hs"));
        assertEquals(false, filter.match("elf"));
        assertEquals(false, filter.match("decuple"));
        assertEquals(false, filter.match("汗"));
        assertEquals(false, filter.match("流浃"));
        assertEquals(false, filter.match("牛马"));

    }

    @Test
    public void matchCharTest1() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.CHAR)
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");

        assertEquals(true, filter.match("/clf/p2/queryneedadd"));
        assertEquals(true, filter.match("/dgkh/p2/userManager/findUserRoleInfo"));
        assertEquals(true, filter.match("/nanjing/ppas/stm/query-balance"));
        assertEquals(true, filter.match("/opcs/p2/payslice/queryContinExtList"));

        assertEquals(false, filter.match("/opcs/p2/payslice/queryContinExtList/emm"));
        assertEquals(false, filter.match("/p2/payslice/queryContinExtList"));
        assertEquals(false, filter.match("/opcs/p2/payslice/"));
        assertEquals(false, filter.match("/opcs/p2/payslice"));
        assertEquals(false, filter.match("/opcs/p2/payslice/queryContinExtList2"));
        assertEquals(false, filter.match("/opc1/p2/payslice/queryContinExtList"));
        assertEquals(false, filter.match("/opasd"));
        assertEquals(false, filter.match("/a/b/c"));
        assertEquals(false, filter.match(""));

    }

}
