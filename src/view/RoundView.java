package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundViewModel;
import interface_adapter.load_game.LoadGameViewModel;
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
import java.util.ArrayList;

public class RoundView extends JPanel implements ActionListener, PropertyChangeListener {

    public final static String VIEW_NAME = "round";

    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final SubmitAnswerViewModel submitAnswerViewModel;
    private final SubmitAnswerController submitAnswerController;
    private final ToggleAudioViewModel toggleAudioViewModel;
    private final ToggleAudioController toggleAudioController;
    private final FinishRoundController finishRoundController;
    private final FinishRoundViewModel finishRoundViewModel;
    private final CreateGameViewModel createGameViewModel;
    private final LoadGameViewModel loadGameViewModel;
    private final JButton playSong;
    private final JButton submit;
    private final JButton easyButton1;
    private final JButton easyButton2;
    private final JButton mediumButton1;
    private final JButton mediumButton2;
    private final JButton mediumButton3;
    private final JButton mediumButton4;
    private final JLabel roundInfo;
    private final JLabel livesInfo;
    private final JLabel genreInfo;
    private final JTextField answerInputField;
    private final JLabel loadingLabel;
    private final JPanel easyAnswerSection;
    private final JPanel mediumAnswerSection;
    private final JPanel hardAnswerSection;
    private final int borderWidth = 2;

    public RoundView(ViewManagerModel viewManagerModel,
                     RoundViewModel roundViewModel,
                     SubmitAnswerViewModel submitAnswerViewModel,
                     SubmitAnswerController submitAnswerController,
                     FinishRoundController finishRoundController,
                     ToggleAudioViewModel toggleAudioViewModel,
                     ToggleAudioController toggleAudioController,
                     FinishRoundViewModel finishRoundViewModel,
                     CreateGameViewModel createGameViewModel,
                     LoadGameViewModel loadGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.submitAnswerViewModel = submitAnswerViewModel;
        this.submitAnswerController = submitAnswerController;
        this.finishRoundController = finishRoundController;
        this.toggleAudioViewModel = toggleAudioViewModel;
        this.toggleAudioController = toggleAudioController;
        this.finishRoundViewModel = finishRoundViewModel;
        this.createGameViewModel = createGameViewModel;
        this.loadGameViewModel = loadGameViewModel;

        this.roundViewModel.addPropertyChangeListener(this);
        this.submitAnswerViewModel.addPropertyChangeListener(this);
        this.toggleAudioViewModel.addPropertyChangeListener(this);
        this.finishRoundViewModel.addPropertyChangeListener(this);
        this.createGameViewModel.addPropertyChangeListener(this);
        this.loadGameViewModel.addPropertyChangeListener(this);


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


        //
        // Easy Answer Section
        //

        easyAnswerSection = new JPanel();
        easyAnswerSection.setLayout(new GridLayout(2,1));
        easyAnswerSection.setMinimumSize(new Dimension(300,10));
        easyAnswerSection.setMaximumSize(new Dimension(getMaximumSize().width, 10));
        easyAnswerSection.setVisible(false);

        // Easy Multiple Choice

        easyButton1 = new JButton("");
        easyButton1.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(easyButton1.getText(), roundState.getGameId());
        });
        easyAnswerSection.add(easyButton1);

        easyButton2 = new JButton("");
        easyButton2.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(easyButton2.getText(), roundState.getGameId());
        });
        easyAnswerSection.add(easyButton2);

        //
        // Medium Answer Section
        //

        mediumAnswerSection = new JPanel();
        mediumAnswerSection.setLayout(new GridLayout(4,1));
        mediumAnswerSection.setMinimumSize(new Dimension(300,20));
        mediumAnswerSection.setMaximumSize(new Dimension(getMaximumSize().width, 20));
        mediumAnswerSection.setVisible(false);

        // Medium Multiple Choice

        mediumButton1 = new JButton("");
        mediumButton1.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(mediumButton1.getText(), roundState.getGameId());
        });
        mediumAnswerSection.add(mediumButton1);

        mediumButton2 = new JButton("");
        mediumButton2.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(mediumButton2.getText(), roundState.getGameId());
        });
        mediumAnswerSection.add(mediumButton2);

        mediumButton3 = new JButton("");
        mediumButton3.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(mediumButton3.getText(), roundState.getGameId());
        });
        mediumAnswerSection.add(mediumButton3);

        mediumButton4 = new JButton("");
        mediumButton4.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(mediumButton4.getText(), roundState.getGameId());
        });
        mediumAnswerSection.add(mediumButton4);

        //
        // Hard Answer Section
        //

        hardAnswerSection = new JPanel();
        hardAnswerSection.setLayout(new GridLayout(2,1));
        hardAnswerSection.setMinimumSize(new Dimension(300,10));
        hardAnswerSection.setMaximumSize(new Dimension(getMaximumSize().width, 10));
        hardAnswerSection.setVisible(false);

        // Hard Text Answer

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
        hardAnswerSection.add(answerInputField);

        submit = new JButton("Submit");
        submit.addActionListener(event -> {
            RoundState roundState = roundViewModel.getState();
            submitAnswerController.execute(roundState.getUserAnswer(), roundState.getGameId());
        });

        hardAnswerSection.add(submit);

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
            RoundState roundState = roundViewModel.getState();
            roundState.setUserAnswer("");
            answerInputField.setText("");

            viewManagerModel.setActiveView(MenuView.VIEW_NAME);
            viewManagerModel.firePropertyChanged();
        });
        JPanel menuButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        menuButtonPanel.add(menuButton);

        // Set view layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(menuButtonPanel);
        this.add(prompt);
        this.add(playSong);
        this.add(hardAnswerSection);
        this.add(easyAnswerSection);
        this.add(mediumAnswerSection);
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
        String propertyName = evt.getPropertyName();

        if (propertyName.equals(ToggleAudioViewModel.STATE_PROPERTY)) {
            ToggleAudioState toggleAudioState = (ToggleAudioState) evt.getNewValue();
            playSong.setIcon(setProperties(new ImageIcon(toggleAudioState.getImgPath())));
        }
        else if (propertyName.equals(SubmitAnswerViewModel.STATE_PROPERTY)) {
            easyAnswerSection.setVisible(false);
            mediumAnswerSection.setVisible(false);
            hardAnswerSection.setVisible(false);

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
        else if (propertyName.equals(CreateGameViewModel.STATE_PROPERTY)
                || propertyName.equals(LoadGameViewModel.STATE_PROPERTY)
                || propertyName.equals(FinishRoundViewModel.STATE_PROPERTY)) {
            RoundState roundState = roundViewModel.getState();

            updateAnswerSection();
            answerSection.setVisible(true);
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
