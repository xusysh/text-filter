package filter.impl;

import enums.FilterType;
import filter.api.Filter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guojingyu
 */
public class BaseFilter implements Filter {

    protected FilterType type;

    protected List<String> rules;

    protected String seperator;

    public BaseFilter(FilterType type) {
        this.type = type;
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
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.addRules(rules);
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

    public Boolean match(String[] target) {
        return null;
    }

    public String filter(String target) {
        return null;
    }

    public String[] filter(String[] target) {
        return new String[0];
    }
}
