package client.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddActivityFrame extends JFrame {

    public AddActivityFrame(MainFrame mainFrame) {
        setupFrame(mainFrame);
    }

    public void setupFrame(MainFrame mainFrame) {
        setBounds(0, 0, 450, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        setLayout(null);
        setTitle("Lägg till en övning");
        setResizable(false);            // Prevent user from changing the size of the frame.
        setLocationRelativeTo(null);    // Start in the middle of the screen.
        AddActivityPanel addActivityPanel = new AddActivityPanel(this, mainFrame);
        setContentPane(addActivityPanel);
        pack();
        setVisible(true);
    }

    public void closeWindow() {
        dispose();
    }
}

