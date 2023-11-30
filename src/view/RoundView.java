package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.exit_round.ExitRoundController;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerState;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioController;
import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RoundView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME = "round";

    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final SubmitAnswerViewModel submitAnswerViewModel;
    private final SubmitAnswerController submitAnswerController;
    private final ToggleAudioViewModel toggleAudioViewModel;
    private final ToggleAudioController toggleAudioController;
    private final FinishRoundController finishRoundController;
    private final ExitRoundController exitRoundController;

    private final JButton playSong;
    private final JButton submit;
    private final JLabel roundInfo;
    private final JLabel livesInfo;
    private final JLabel genreInfo;
    private final JTextField answerInputField;
    private final JLabel loadingLabel;
    private final int borderWidth = 2;

    public RoundView(ViewManagerModel viewManagerModel,
                     RoundViewModel roundViewModel,
                     SubmitAnswerViewModel submitAnswerViewModel,
                     ToggleAudioViewModel toggleAudioViewModel,
                     SubmitAnswerController submitAnswerController,
                     ToggleAudioController toggleAudioController,
                     FinishRoundController finishRoundController,
                     ExitRoundController exitRoundController) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.submitAnswerViewModel = submitAnswerViewModel;
        this.toggleAudioViewModel = toggleAudioViewModel;
        this.submitAnswerController = submitAnswerController;
        this.toggleAudioController = toggleAudioController;
        this.finishRoundController = finishRoundController;
        this.exitRoundController = exitRoundController;

        this.roundViewModel.addPropertyChangeListener(this);
        this.submitAnswerViewModel.addPropertyChangeListener(this);
        this.toggleAudioViewModel.addPropertyChangeListener(this);

        // Prompt
        JLabel prompt = new JLabel(roundViewModel.TITLE_LABEL);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        playSong = new JButton();
        ImageIcon playIcon = new ImageIcon("src/assets/play-img2.png");
        playSong.setIcon(setProperties(playIcon));
        playSong.setMaximumSize(new Dimension(50, 350));
        playSong.setAlignmentX(Component.CENTER_ALIGNMENT);
        playSong.setAlignmentY(Component.CENTER_ALIGNMENT);
        playSong.addActionListener(event -> {
            toggleAudioController.execute(roundViewModel.getState().getGameId());
        });
        playSong.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answer Section
        JPanel answerSection = new JPanel();
        answerSection.setLayout(new GridLayout(2,1));
        answerSection.setMinimumSize(new Dimension(300,10));
        answerSection.setMaximumSize(new Dimension(getMaximumSize().width, 10));

        answerInputField = new JFormattedTextField();
        answerInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                RoundState roundState = roundViewModel.getState();
                String userAnswer = answerInputField.getText() + e.getKeyChar();
                roundState.setUserAnswer(userAnswer);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        answerSection.add(answerInputField);

        submit = new JButton("Submit");
        submit.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(roundState.getUserAnswer(), roundState.getGameId());
        });
        answerSection.add(submit);

        // Loading Label
        loadingLabel = new JLabel("loading round ... ");
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingLabel.setVisible(false);

        // Round Info Section
        JPanel infoSection = new JPanel();
        infoSection.setMinimumSize(new Dimension(300,10));
        infoSection.setMaximumSize(new Dimension(getMaximumSize().width, 10));
        infoSection.setBackground(Color.darkGray);
        infoSection.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));
        infoSection.setLayout(new GridLayout(1, 3, borderWidth, borderWidth));

        roundInfo = new JLabel("Round: ");
        JPanel roundCell = new JPanel();
        roundCell.setBackground(this.getBackground());
        roundCell.add(roundInfo);
        infoSection.add(roundCell);

        livesInfo = new JLabel("Lives left:");
        JPanel livesCell = new JPanel();
        livesCell.setBackground(this.getBackground());
        livesCell.add(livesInfo);
        infoSection.add(livesCell);

        genreInfo = new JLabel("Genre: ");
        JPanel genreCell = new JPanel();
        genreCell.setBackground(this.getBackground());
        genreCell.add(genreInfo);
        infoSection.add(genreCell);

        // Menu button
        JButton menuButton = new JButton("Go to main menu");
        menuButton.addActionListener(e -> {
            answerInputField.setText("");

            RoundState roundState = roundViewModel.getState();
            exitRoundController.execute(roundState.getGameId());
        });
        JPanel menuButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        menuButtonPanel.add(menuButton);

        // Set view layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(menuButtonPanel);
        this.add(prompt);
        this.add(playSong);
        this.add(answerSection);
        this.add(loadingLabel);
        this.add(new JPanel());
        this.add(infoSection);

        loadingLabel.setVisible(true); // showing loading label
        submit.setEnabled(false); // disable submit button while loading next round
        playSong.setEnabled(false); // disable play button while loading next round
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ToggleAudioViewModel.STATE_PROPERTY)) {
            ToggleAudioState toggleAudioState = (ToggleAudioState) evt.getNewValue();
            playSong.setIcon(setProperties(new ImageIcon(toggleAudioState.getImgPath())));
        }
        if (evt.getPropertyName().equals(SubmitAnswerViewModel.STATE_PROPERTY)) {
            SubmitAnswerState submitAnswerState = (SubmitAnswerState) evt.getNewValue();

            // Create dialog displaying the correctness of the user's answer
            JOptionPane optionPane = new JOptionPane(submitAnswerState.getCorrectnessMessage(),
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{"Next"});
            JDialog dialog = optionPane.createDialog(this, submitAnswerState.getCorrectnessTitle());

            loadingLabel.setVisible(true); // showing loading label
            submit.setEnabled(false); // disable submit button while loading next round
            playSong.setEnabled(false); // disable play button while loading next round

            optionPane.addPropertyChangeListener(e -> {
                if (JOptionPane.VALUE_PROPERTY.equals(e.getPropertyName())) {
                    // Call finish round controller
                    RoundState roundState = roundViewModel.getState();
                    finishRoundController.execute(roundState.getGameId());
                    answerInputField.setText("");
                }
            });
            dialog.setVisible(true);
        }
        this.updateViewComponents();
    }

    private void updateViewComponents(){
        roundInfo.setText("Round: " + roundViewModel.getState().getCurrentRoundNumber() + "/" + roundViewModel.getState().getMaxRounds());
        livesInfo.setText("Lives left:" + roundViewModel.getState().getCurrentLives());
        genreInfo.setText("Genre: "  + roundViewModel.getState().getGenre());

        loadingLabel.setVisible(false);
        playSong.setEnabled(true);
        submit.setEnabled(true);
    }

    private ImageIcon setProperties(ImageIcon buttonImage) {
        return new ImageIcon(buttonImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }
}
