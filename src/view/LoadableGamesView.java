package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesState;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class LoadableGamesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "loadable games";

    private final ViewManagerModel viewManagerModel;

    private final JButton backButton;
    private final JPanel loadableGames;

    public LoadableGamesView(ViewManagerModel viewManagerModel, GetLoadableGamesViewModel getLoadableGamesViewModel) {
        this.viewManagerModel = viewManagerModel;
        getLoadableGamesViewModel.addPropertyChangeListener(this);

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
            viewManagerModel.setActiveView("menu");
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals(GetLoadableGamesViewModel.STATE_PROPERTY)) {
            System.out.println("get loadable games use case complete");

            GetLoadableGamesState getLoadableGamesState = (GetLoadableGamesState) e.getNewValue();
            if (getLoadableGamesState.getErrorMessage().isEmpty()) {
                displayLoadableGames(getLoadableGamesState.getGamesData());
            } else {
                displayErrorMessage(getLoadableGamesState.getErrorMessage());
            }
        }
    }

    private void displayLoadableGames(List<Map<String, String>> gamesData) {
        loadableGames.removeAll();
        loadableGames.setLayout(new GridLayout(0, 1));

        for (Map<String, String> gameData: gamesData) {
            String gameDescription = String.format("<html>%s<br>Difficulty: %s | Genre: %s | Round: %s/%s | Lives: %s/%s</html>",
                    gameData.get("createdAt"),
                    gameData.get("difficulty"),
                    gameData.get("genre"),
                    gameData.get("currentRound"),
                    gameData.get("maxRounds"),
                    gameData.get("currentLives"),
                    gameData.get("initialLives"));
            JButton loadableGameButton = new JButton(gameDescription);
            loadableGameButton.addActionListener(e -> {
                String gameID = gameData.get("ID");
                System.out.println("Execute LoadGame use case for game with ID = " + gameID);
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