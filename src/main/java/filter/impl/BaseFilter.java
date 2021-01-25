package filter.impl;

import enums.FilterType;
import filter.api.Filter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author guojingyu
 */
public class BaseFilter implements Filter {

    protected FilterType type;

    protected List<String> rules;

    protected String separator;

    public BaseFilter(FilterType type, String separator) {
        this.type = type;
        this.separator = separator;
        rules = new ArrayList<>();
    }

    public void loadRules(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                this.rules.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        } finally {
            if (!Objects.isNull(fileReader)) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    throw new IOException();
                }
            }
            if (!Objects.isNull(bufferedReader)) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new IOException();
                }
            }
        }
    }

    public void addRules(List<String> rules) {
        this.rules.addAll(rules);
    }

    public void addRules(String... rules) {
        this.rules.addAll(Arrays.asList(rules));
    }

    public void clearRules() {
        rules.clear();
    }


    public Boolean match(String target) {
        return null;
    }

    public Boolean matchWithWildcards(String target) {
        return null;
    }

    public List<Boolean> match(String[] target) {
        return null;
    }

    public List<Boolean> matchWithWildcards(String[] target) {
        return null;
    }

    public String filter(String target) {
        return null;
    }

    public String filterWithWildcards(String target) {
        return null;
    }

    public String[] filter(String[] target) {
        return new String[0];
    }

    public String[] filterWithWildcards(String[] target) {
        return new String[0];
    }

}
