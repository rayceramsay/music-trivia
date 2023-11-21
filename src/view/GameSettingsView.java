package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_settings.GameSettingsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameSettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME =  "game settings";

    private final GameSettingsViewModel gameSettingsViewModel;

    private final ViewManagerModel viewManagerModel;


    public final JButton playGame;
    public final JButton back;

    public final JComboBox<String> genreSelector;
    public final JComboBox<String> difficultySelector;

    public final SpinnerNumberModel livesSpinnerNumberModel;
    public final SpinnerNumberModel roundsSpinnerNumberModel;
    public final JSpinner livesSpinner;
    public final JSpinner roundsSpinner;

    public final JLabel difficultyLabel;
    public final JLabel genreLabel;
    public final JLabel livesLabel;
    public final JLabel roundsLabel;


    public GameSettingsView (GameSettingsViewModel gameSettingsViewModel,
                             ViewManagerModel viewManagerModel) {

        this.setLayout(new GridBagLayout());

        this.gameSettingsViewModel = gameSettingsViewModel;
        this.viewManagerModel = viewManagerModel;

        String[] genreOptions = {"Hip-Hop", "Rock", "Pop"};
        String[] difficultyOptions = {"Easy", "Medium", "Hard"};

        GridBagConstraints gridBagContraints = new GridBagConstraints();
        gridBagContraints.insets = new Insets(10, 10, 10, 10);


        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        // DIFFICULTY

        difficultyLabel = new JLabel(gameSettingsViewModel.DIFFICULTY_SELECTOR_LABEL);
        gridBagContraints.gridx = 0;
        gridBagContraints.gridy = 0;
        gridBagContraints.anchor = GridBagConstraints.LINE_START;
        this.add(difficultyLabel, gridBagContraints);


        difficultySelector = new JComboBox<>(difficultyOptions);
        gridBagContraints.gridx = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_END;
        this.add(difficultySelector, gridBagContraints);

        // GENRE

        genreLabel = new JLabel(gameSettingsViewModel.GENRE_SELECTOR_LABEL);
        gridBagContraints.gridx = 0;
        gridBagContraints.gridy = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_START;
        this.add(genreLabel, gridBagContraints);

        genreSelector = new JComboBox<>(genreOptions);
        gridBagContraints.gridx = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_END;
        this.add(genreSelector, gridBagContraints);

        // LIVES SPINNER

        livesSpinnerNumberModel = new SpinnerNumberModel(3, 1, 3, 1);

        livesLabel = new JLabel(gameSettingsViewModel.LIVES_SPINNER_LABEL);
        gridBagContraints.gridx = 0;
        gridBagContraints.gridy = 2;
        gridBagContraints.anchor = GridBagConstraints.LINE_START;
        this.add(livesLabel, gridBagContraints);

        livesSpinner = new JSpinner(livesSpinnerNumberModel);
        gridBagContraints.gridx = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_END;
        this.add(livesSpinner, gridBagContraints);

        // ROUNDS SPINNER

        roundsSpinnerNumberModel = new SpinnerNumberModel(10, 5, 15, 1);

        roundsLabel = new JLabel(gameSettingsViewModel.ROUNDS_SPINNER_LABEL);
        gridBagContraints.gridx = 0;
        gridBagContraints.gridy = 3;
        gridBagContraints.anchor = GridBagConstraints.LINE_START;
        this.add(roundsLabel, gridBagContraints);

        roundsSpinner = new JSpinner(roundsSpinnerNumberModel);
        gridBagContraints.gridx = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_END;
        this.add(roundsSpinner, gridBagContraints);

        // BUTTONS

        back = new JButton(gameSettingsViewModel.BACK_LABEL);
        gridBagContraints.gridx = 0;
        gridBagContraints.gridy = 4;
        gridBagContraints.anchor = GridBagConstraints.LINE_START;
        this.add(back, gridBagContraints);

        playGame = new JButton(gameSettingsViewModel.PLAY_GAME_LABEL);
        gridBagContraints.gridx = 1;
        gridBagContraints.anchor = GridBagConstraints.LINE_END;
        this.add(playGame, gridBagContraints);

        playGame.addActionListener(this);
        back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            viewManagerModel.setActiveView(MenuView.VIEW_NAME);
            viewManagerModel.firePropertyChanged();
        }
        if (e.getSource().equals(playGame)) {
            viewManagerModel.setActiveView(RoundView.VIEW_NAME);
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}