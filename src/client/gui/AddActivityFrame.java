package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author: Satya Singh
 * This class extends JFrame and will serve as the container for
 * the JPanel that will make up the 'Add new exercise' window
 */
public class AddActivityFrame extends JFrame {

    /**
     * A constructor that calls the setupFrame function and passes along
     * an instance of the MainFrame class
     * @param mainFrame instance of the MainFrame class
     */
    public AddActivityFrame(MainFrame mainFrame) {
        setupFrame(mainFrame);
    }

    /**
     * This method sets up the frame and creates an instance of the AddActivityPanel class
     * It passes along and instance of the MainFrame class
     * @param mainFrame is the instance of the MainFrame class
     */
    public void setupFrame(MainFrame mainFrame) {
//        setBounds(0, 0, 500, 600);
        setMinimumSize(new Dimension(500,400));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.exit(0);
            }
        });
        setLayout(null);
        setTitle("Lägg till en övning");
        setResizable(true);
        setLocationRelativeTo(null);    // Start in the middle of the screen.
        AddActivityPanel addActivityPanel = new AddActivityPanel(this, mainFrame);
        setContentPane(addActivityPanel);
        pack();
        setVisible(true);
    }

    /**
     * This method closes the Frame
     */
    public void closeWindow() {
        dispose();
    }
}

