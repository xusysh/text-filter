import builder.FilterBuilder;
import enums.FilterImpl;
import filter.api.Filter;
import org.junit.Test;

public class DFATest {

    @Test
    public void test() {
        FilterBuilder builder = FilterBuilder.getBuilder();
        Filter filter = builder.setImpl(FilterImpl.DFA).getFilter();
    }

}
