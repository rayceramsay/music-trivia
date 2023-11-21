package use_case.statistics;

import java.util.HashMap;

public class StatisticsOutputData {

    private final HashMap<String, Object> stringObjectHashMap;
    public StatisticsOutputData(HashMap<String, Object> stringObjectHashMap) {
        this.stringObjectHashMap = stringObjectHashMap;
    }
    public String getStats(){
        StringBuilder mapAsString = new StringBuilder();
        for (String key : stringObjectHashMap.keySet()) {
            mapAsString.append(key).append(":").append(" ").append(stringObjectHashMap.get(key)).append("\n");
        }
        return String.format("Here are your statistics, Player! \n"  + mapAsString);
    }
    }
