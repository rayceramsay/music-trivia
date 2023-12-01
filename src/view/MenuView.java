package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.get_loadable_games.GetLoadableGamesController;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME = "menu";

    private final ViewManagerModel viewManagerModel;
    private final GetLoadableGamesController getLoadableGamesController;
    private final GameSettingsViewModel gameSettingsViewModel;
    private final StatisticsViewModel statisticsViewModel;
    private final StatisticsController statisticsController;

    private final JButton newGame;
    private final JButton loadGame;
    private final JButton careerStats;

    /**
     * Constructor to initialize objects of MenuView
     *
     * @param viewManagerModel           View manager model
     * @param gameSettingsViewModel      View model for GameSettings
     * @param statisticsViewModel        view model for Statistics
     * @param getLoadableGamesController Controller for GetLoadableGames
     * @param statisticsController       Controller for Statistics
     */
    public MenuView(ViewManagerModel viewManagerModel, GameSettingsViewModel gameSettingsViewModel,
                    StatisticsViewModel statisticsViewModel, GetLoadableGamesController getLoadableGamesController,
                    StatisticsController statisticsController) {
        this.viewManagerModel = viewManagerModel;
        this.getLoadableGamesController = getLoadableGamesController;
        this.gameSettingsViewModel = gameSettingsViewModel;
        this.statisticsViewModel = statisticsViewModel;
        this.statisticsController = statisticsController;
        statisticsViewModel.addPropertyChangeListener(this);

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        newGame = new JButton("NEW GAME");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(newGame);

        loadGame = new JButton("LOAD GAME");
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(loadGame);

        careerStats = new JButton("CAREER STATS");
        careerStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(careerStats);

        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        careerStats.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGame)) {
            viewManagerModel.setActiveView(GameSettingsView.VIEW_NAME);
            gameSettingsViewModel.firePropertyChanged();
            viewManagerModel.firePropertyChanged();
        }
        if (e.getSource().equals(loadGame)) {
            getLoadableGamesController.execute();
        }
        if (e.getSource().equals(careerStats)) {
            statisticsController.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StatisticsState statisticsState = statisticsViewModel.getState();
        JOptionPane.showMessageDialog(this, statisticsState.getStatsMessage());
    }
}
