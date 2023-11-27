package use_case.statistics;

import java.util.HashMap;

public class StatisticsOutputData {

    private final HashMap<String, Object> stringObjectHashMap;
    public StatisticsOutputData(HashMap<String, Object> stringObjectHashMap) {
        this.stringObjectHashMap = stringObjectHashMap;
    }

    public HashMap<String, Object> getStats(){return stringObjectHashMap;}
}
