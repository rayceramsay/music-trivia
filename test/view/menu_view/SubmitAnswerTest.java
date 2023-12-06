package view.menu_view;

import app.Main;
import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SubmitAnswerTest {
    GameDataAccessInterface dao;
    Game game;

    /**
     * ensures there are at least 2 users in the CSV file for testing purposes
     */
    public void createRound() {
        RoundFactory roundFactory = new MockRoundFactory();
        Round round = roundFactory.generateOptionRoundFromGenre("hip hop", 1);
        game = new CommonGame("hip hop", "easy", 3, 5);
        game.setCurrentRound(round);
        dao = new InMemoryGameDataAccessObject();
        dao.save(game);
    }


    public HashMap<String, JButton> getButtons() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        HashMap<String, JButton> buttons = new HashMap();
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        MenuView sv = (MenuView) jp2.getComponent(0);

        buttons.put("NEW GAME", (JButton) sv.getComponent(0));
        buttons.put("LOAD GAME", (JButton) sv.getComponent(1));
        buttons.put("CAREER STATS", (JButton) sv.getComponent(2));

        return buttons; // this should be the clear button
    }

    /**
     *
     * Test that the Clear button is present and where it is expected to be
     */
    @org.junit.Test
    public void verifyButtonTest() {
        Main.main(null);
        HashMap<String, JButton> buttons = getButtons();
        for (String name : buttons.keySet()) {
            assertEquals(buttons.get(name).getText(), name);
        }

    }
}
