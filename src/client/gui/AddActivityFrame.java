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
     * A constructor that calls the setupFrame function
     */
    public AddActivityFrame() {
        setupFrame();
    }

    /**
     * This method sets up the frame and creates an instance of the AddActivityPanel class
     */
    public void setupFrame() {
        setMinimumSize(new Dimension(500,400));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        setLayout(null);
        setTitle("Lägg till en övning");
        setResizable(false);
        setLocationRelativeTo(null);    // Start in the middle of the screen.
        AddActivityPanel addActivityPanel = new AddActivityPanel(this);
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

