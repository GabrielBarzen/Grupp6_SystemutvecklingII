package client_dummy_v2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel panel1;
    private JButton exitButton;
    private JList completedActivityList;
    private JButton removeActivityFromListButton;
    private JButton interwallChangeButton;
    private JButton newActivitybutton;
    private JSpinner minuteSpinner;
    private JLabel highlightedActivityDescription;
    private JLabel timeLabel;

    public MainForm() {

        ActionListener listener = actionEvent -> {

        };
        interwallChangeButton.addActionListener(listener);
        newActivitybutton.addActionListener(listener);
        removeActivityFromListButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

}
