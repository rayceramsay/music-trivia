package view;

import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerState;
import interface_adapter.submit_answer.SubmitAnswerViewModel;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RoundView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "round";
    private final RoundViewModel roundViewModel;
    private final SubmitAnswerViewModel submitAnswerViewModel;
    private final SubmitAnswerController submitAnswerController;

    final JButton playSong;
    final JButton submit;
    JLabel roundInfo;
    JLabel livesInfo;
    JLabel genreInfo;
    final int borderWidth = 2;

    public RoundView(RoundViewModel roundViewModel, SubmitAnswerViewModel submitAnswerViewModel,
                     SubmitAnswerController submitAnswerController) {
        this.roundViewModel = roundViewModel;
        this.submitAnswerViewModel = submitAnswerViewModel;
        this.submitAnswerController = submitAnswerController;

        this.roundViewModel.addPropertyChangeListener(this);
        this.submitAnswerViewModel.addPropertyChangeListener(this);

        // Prompt
        JLabel prompt = new JLabel(roundViewModel.TITLE_LABEL);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        playSong = new JButton("Play");
        playSong.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Answer Section
        JPanel answerSection = new JPanel();
        answerSection.setLayout(new GridLayout(2,1));
        answerSection.setMinimumSize(new Dimension(300,10));
        answerSection.setMaximumSize(new Dimension(getMaximumSize().width, 10));

        JTextField answerInputField = new JFormattedTextField();
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

        // Set view layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(prompt);
        this.add(playSong);
        this.add(answerSection);
        this.add(new JPanel());
        this.add(infoSection);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("submitAnswerState")) {
            SubmitAnswerState submitAnswerState = (SubmitAnswerState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, submitAnswerState.getCorrectnessMessage(),
                    submitAnswerState.getCorrectnessTitle(), JOptionPane.PLAIN_MESSAGE);
        }
    }
}
