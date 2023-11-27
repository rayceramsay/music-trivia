package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
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

    public final String viewName = "menu";

    private final MenuViewModel menuViewModel;

    private final ViewManagerModel viewManagerModel;
    private final StatisticsViewModel statisticsViewModel;

    private final StatisticsController statisticsController;

    public final JButton newGame;
    public final JButton loadGame;
    public final JButton careerStats;

    public MenuView(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel,
                    StatisticsViewModel statisticsViewModel, StatisticsController statisticsController) {

        this.menuViewModel = menuViewModel;
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.statisticsController = statisticsController;

        menuViewModel.addPropertyChangeListener(this);

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        newGame = new JButton(menuViewModel.NEWGAME_BUTTON_LABEL);
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(newGame);
        loadGame = new JButton(menuViewModel.LOADGAME_BUTTON_LABEL);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(loadGame);
        careerStats = new JButton(menuViewModel.CAREERSTATS_BUTTON_LABEL);
        careerStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(careerStats);

        newGame.addActionListener(this);
        loadGame.addActionListener(this);

        this.careerStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(MenuView.this.careerStats)) {
                    statisticsController.execute();
                }
            }
        });
        this.statisticsViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGame)) {
            viewManagerModel.setActiveView("game settings");
            viewManagerModel.firePropertyChanged();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StatisticsState statisticsState = statisticsViewModel.getState();
        String stringToShow = String.format("Here are your lifetime statistics...\n"  +
                "Average Score: " + statisticsState.getAverageScore()  + "\n" +
                "Average Initial Lives: " + statisticsState.getAverageLives() + "\n" +
                "Average # of Rounds Played: " + statisticsState.getAverageRoundsPlayed() + "\n" +
                "Most Common Game Difficulty: " + statisticsState.getCommonGameDifficulty() + "\n" +
                "Most Common Genre Played: " + statisticsState.getCommonGameGenre());
        JOptionPane.showMessageDialog(this, stringToShow);
    }
}
