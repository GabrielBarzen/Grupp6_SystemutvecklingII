package com.grp6.edim.client;

import com.grp6.edim.client.view.EDIMPanels.ActivityEditorPanel;
import com.grp6.edim.client.view.EDIMPanels.LoginPanel;
import com.grp6.edim.client.view.MainFrame;
import com.grp6.edim.client.view.EDIMPanels.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMain {

    MainFrame frame;

    public static void main(String[] args) {
        new ClientMain();
    }

    ClientMain() {
        startClient();
    }

    private void startClient() {
        frame = new MainFrame(this);
    }

    public void setupLogin(LoginPanel loginPanel) {
        JButton login = loginPanel.getLoginButton();
        JButton cancel = loginPanel.getCancelButton();
        JTextField textField = loginPanel.getUsernameField();

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Add login logic, username info : " + textField.getText()); //TODO add login logic
                frame.swapPanel(new MainPanel());
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                exit();
            }
        });
    }


    public static void exit() {
        System.exit(0);
    }

    public void setupMainPanel(MainPanel panel) {

        panel.getCreateNewActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.swapPanel(new ActivityEditorPanel());
            }
        });
        System.out.println("SETUP MAIN PANEL"); //TODO action listeners for panel
    }
    public void setupActivityEditor(ActivityEditorPanel panel) {
        System.out.println("SETUP ACTIVITY EDITOR PANEL"); //TODO action listeners for panel

    }
}
