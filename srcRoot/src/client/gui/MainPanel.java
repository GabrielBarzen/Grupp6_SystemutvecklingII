package client.gui;

import server.Activity;
import server.Buffer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private AppPanel appPanel;
    private String userName;
    private Color backGroundColor;
    private boolean isMinimized = false;

    public MainPanel(MainFrame mainFrame, String userName) {
        this.mainFrame = mainFrame;
        mainFrame.addWindowListener(new MinimizedListener());
        this.userName = userName;
        backGroundColor = new Color(134, 144, 154, 145); //64, 87, 139
        setupPanel();
        appPanel = new AppPanel(this);
        showAppPanel();
    }

    public void setupPanel() {
        setSize(new Dimension(819, 438));
        setBackground(backGroundColor);
        setBorder(BorderFactory.createTitledBorder("Välkommen, " + userName));
    }

    public void showAppPanel() {
        add(appPanel);
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void logOut() {
        mainFrame.logOut();
    }

    public void sendActivityFromGUI(Activity activity) {
        mainFrame.sendActivityFromGUI(activity);
    }

    public void sendChosenInterval(int interval) {
        mainFrame.sendChosenInterval(interval);
    }

    /**
     * @author Satya Singh
     * This function returns the boolean variable isMinimized.
     * @return is the boolean variable.
     */
    public boolean checkIfMinimized() {
        return isMinimized;
    }

    public void getNewActivity() {
        mainFrame.getNewActivity();

    }

    /**
     * @author Satya Singh
     * This class listens for when the window is minimized or maximized and sets the
     * variable isMinimized accordingly.
     */
    private class MinimizedListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {
            isMinimized = true;
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            isMinimized = false;
        }

        @Override
        public void windowActivated(WindowEvent e) {
            isMinimized = false;
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            isMinimized = true;
        }
    }

    public void saveActivity(String name, String instructions, String description, BufferedImage image) {
        mainFrame.saveActivity(name, instructions, description, image);
    }
}
