package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesState;
import interface_adapter.get_loadable_games.GetLoadableGamesStateItem;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.round.RoundViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class LoadableGamesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME = "loadable games";

    private final ViewManagerModel viewManagerModel;
    private final LoadGameController loadGameController;

    private final JButton backButton;
    private final JPanel loadableGames;

    public LoadableGamesView(ViewManagerModel viewManagerModel,
                             GetLoadableGamesViewModel getLoadableGamesViewModel,
                             LoadGameController loadGameController) {
        this.viewManagerModel = viewManagerModel;
        getLoadableGamesViewModel.addPropertyChangeListener(this);
        this.loadGameController = loadGameController;

        loadableGames = new JPanel();
        JScrollPane loadableGamesContainer = new JScrollPane(loadableGames);
        this.add(loadableGamesContainer);

        backButton = new JButton("BACK");
        backButton.addActionListener(this);
        this.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            viewManagerModel.setActiveView(MenuView.VIEW_NAME);
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals(GetLoadableGamesViewModel.STATE_PROPERTY)) {
            GetLoadableGamesState getLoadableGamesState = (GetLoadableGamesState) e.getNewValue();

            if (getLoadableGamesState.getErrorMessage().isEmpty()) {
                displayLoadableGames(getLoadableGamesState.getGamesData());
            } else {
                displayErrorMessage(getLoadableGamesState.getErrorMessage());
            }
        }
    }

    private void displayLoadableGames(List<GetLoadableGamesStateItem> gamesData) {
        loadableGames.removeAll();
        loadableGames.setLayout(new GridLayout(0, 1));

        for (GetLoadableGamesStateItem gameData: gamesData) {
            String gameDescription = String.format("<html>%s<br>Difficulty: %s | Genre: %s | Round: %s/%s | Lives: %s/%s</html> | Score %s</html>",
                    gameData.getCreatedAt(),
                    gameData.getDifficulty(),
                    gameData.getGenre(),
                    gameData.getCurrentRoundNumber(),
                    gameData.getMaxRounds(),
                    gameData.getCurrentLives(),
                    gameData.getInitialLives(),
                    gameData.getScore());
            JButton loadableGameButton = new JButton(gameDescription);
            loadableGameButton.addActionListener(e -> {
                String gameID = gameData.getGameID();
                loadGameController.execute(gameID);
            });

            JPanel loadableGameButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            loadableGameButtonPanel.add(loadableGameButton);
            loadableGames.add(loadableGameButtonPanel);
        }

        loadableGames.repaint();
        loadableGames.revalidate();
    }

    private void displayErrorMessage(String errorMessage) {
        loadableGames.removeAll();
        loadableGames.setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel(errorMessage);
        loadableGames.add(messageLabel);

        loadableGames.repaint();
        loadableGames.revalidate();
    }
}
