package config;

import enums.WildcardType;
import filter.api.WildcardHandler;

import java.util.HashMap;
import java.util.function.Function;

/**
 * @author guojingyu
 */
public class FilterConfig {

    HashMap<WildcardType, Function> wildcardToHandler = new HashMap();

    HashMap<WildcardType, WildcardHandler> wildcardToHandler2 = new HashMap();

}
