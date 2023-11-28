package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesController;
import interface_adapter.game_settings.GameSettingsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements ActionListener {

    public final static String VIEW_NAME = "menu";

    private final ViewManagerModel viewManagerModel;
    private final GetLoadableGamesController getLoadableGamesController;
    private final GameSettingsViewModel gameSettingsViewModel;

    private final JButton newGame;
    private final JButton loadGame;
    private final JButton careerStats;

    public MenuView(ViewManagerModel viewManagerModel, GameSettingsViewModel gameSettingsViewModel, GetLoadableGamesController getLoadableGamesController) {
        this.viewManagerModel = viewManagerModel;
        this.getLoadableGamesController = getLoadableGamesController;
        this.gameSettingsViewModel = gameSettingsViewModel;

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
    }
}
