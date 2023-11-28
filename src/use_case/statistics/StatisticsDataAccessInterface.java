package use_case.statistics;

import entity.Game;

import java.util.ArrayList;
import java.util.HashMap;

public interface StatisticsDataAccessInterface {
    HashMap<String, Object> avgStats();
    void save(Game game);
}
