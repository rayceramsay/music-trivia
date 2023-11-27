package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.menu.MenuViewModel;

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
    private final GameSettingsViewModel gameSettingsViewModel;

    public final JButton newGame;
    public final JButton loadGame;
    public final JButton careerStats;

    public MenuView(MenuViewModel menuViewModel, ViewManagerModel viewManagerModel, GameSettingsViewModel gameSettingsViewModel) {

        this.menuViewModel = menuViewModel;
        this.viewManagerModel = viewManagerModel;
        this.gameSettingsViewModel = gameSettingsViewModel;

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
        careerStats.addActionListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGame)) {
            viewManagerModel.setActiveView("game settings");
            gameSettingsViewModel.firePropertyChanged();
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
