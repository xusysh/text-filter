import builder.FilterBuilder;
import enums.FilterImpl;
import enums.FilterType;
import filter.api.Filter;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class DFATest {

    Filter filter;

    public DFATest() throws IOException {
        this.loadRules();
    }

    @Test
    public void loadRules() throws IOException {
        FilterBuilder builder = FilterBuilder.getBuilder();
        this.filter = builder
                .setImpl(FilterImpl.DFA)
                .setType(FilterType.WORD)
                .setSeparator("/")
                .getFilter();
        filter.loadRules("src/test/cases/rules1.txt");
    }

    @Test
    public void matchTest() {
        assertEquals(true,filter.match("/clf/p2/queryneedadd"));
        assertEquals(true,filter.match("/dgkh/p2/userManager/findUserRoleInfo"));
        assertEquals(true,filter.match("/nanjing/ppas/stm/query-balance"));
        assertEquals(true,filter.match("/opcs/p2/payslice/queryContinExtList"));
        assertEquals(false,filter.match("/opcs/p2/payslice/queryContinExtList/emm"));
        assertEquals(false,filter.match("/opcs/p2/payslice/"));
        assertEquals(false,filter.match("/opcs/p2/payslice"));
        assertEquals(false,filter.match("/opcs/p2/payslice/queryContinExtList2"));
        assertEquals(false,filter.match("/opc1/p2/payslice/queryContinExtList"));
        assertEquals(false,filter.match("/opasd"));
        assertEquals(false,filter.match("/a/b/c"));
        assertEquals(false,filter.match(""));
    }

}
