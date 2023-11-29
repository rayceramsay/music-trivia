package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverState;
import interface_adapter.game_over.GameOverViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME = "game over";

    private final GameOverViewModel gameOverViewModel;
    private final ViewManagerModel viewManagerModel;

    JLabel scoreInfo;
    final JButton mainMenu;
    final JButton playAgain;
    public GameOverView(GameOverViewModel gameOverViewModel,
                        ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameOverViewModel = gameOverViewModel;
        this.gameOverViewModel.addPropertyChangeListener(this);

        // Create title label and horizontally align in view
        JLabel title = new JLabel(gameOverViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create score label and horizontally align in view
        scoreInfo = new JLabel();
        scoreInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        mainMenu = new JButton(gameOverViewModel.MAIN_MENU_LABEL);
        mainMenu.addActionListener(this);

        playAgain = new JButton(gameOverViewModel.PLAY_AGAIN_LABEL);
        playAgain.addActionListener(this);

        buttons.add(playAgain);
        buttons.add(mainMenu);

        // Set view layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scoreInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mainMenu)) {
            viewManagerModel.setActiveView(MenuView.VIEW_NAME);
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameOverState state = (GameOverState) evt.getNewValue();
        scoreInfo.setText("Your final score was " + state.getScore());
    }
}