package use_case.statistics;

import entity.Game;
import entity.LifetimeStatistics;

import java.util.ArrayList;
import java.util.HashMap;

public interface StatisticsDataAccessInterface {
    LifetimeStatistics avgStats();
    void save(Game game);
}
