package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddActivityPanel extends JPanel {
    private JTextField nameTxtField;
    private JLabel nameLabel;
    private JTextArea descriptionTxtArea;
    private JLabel descriptionLabel;
    private JTextArea instructionTxtArea;
    private JLabel instructionLabel;
    private JButton enterBtn;
    private JButton exitBtn;

    private AddActivityFrame addActivityFrame;
    private MainFrame mainFrame;

    public AddActivityPanel(AddActivityFrame addActivityFrame, MainFrame mainFrame) {
        this.addActivityFrame = addActivityFrame;
        this.mainFrame = mainFrame;
        initComponents();
    }

    public void initComponents() {
        nameLabel = new JLabel("Övningens namn");
        nameTxtField = new JTextField();
        nameTxtField.setPreferredSize(new Dimension(100, 30));
        enterBtn = new JButton("Lägg till övning");
        exitBtn = new JButton("Avsluta");
        setLayout(new FlowLayout(4));
        add(nameLabel, FlowLayout.LEFT);
        add(nameTxtField, FlowLayout.CENTER);
        add(enterBtn);
        add(exitBtn);


//        nameTxtField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { addActivity();  }
//        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActivityFrame.dispose();
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);

            }
        });
        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActivity();
            }
        });


    }
    public void addActivity() {
        JOptionPane.showConfirmDialog(null, "You did it");
        try {
            addActivityFrame.dispose();
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
