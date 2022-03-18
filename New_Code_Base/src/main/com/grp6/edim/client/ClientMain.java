package com.grp6.edim.client;


import com.grp6.edim.client.controller.CommunicationControllerClient;
import com.grp6.edim.client.view.EDIMPanels.ActivityEditorPanel;
import com.grp6.edim.client.view.EDIMPanels.LoginPanel;
import com.grp6.edim.client.view.EDIMPanels.MainPanel;
import com.grp6.edim.client.view.MainFrame;
import com.grp6.edim.shared.Activity;
import com.grp6.edim.shared.Message;
import com.grp6.edim.shared.MessageType;
import com.grp6.edim.shared.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    private MainFrame frame;
    private int port = 4343;
    private ActivityEditorPanel panel;
    private Message message;
    CommunicationControllerClient controllerClient;
    private User user;
    private MainPanel mainPanel = new MainPanel();
    private Socket socket;



    public static void main(String[] args) {
        new ClientMain();
    }

    ClientMain() {
        startClient();
    }

    private void startClient() {
        frame = new MainFrame(this);


        try {
            socket = new Socket("localhost", port);

        } catch (IOException e) {
            e.printStackTrace();
        }
        controllerClient = new CommunicationControllerClient(this, socket);


    }

    public void setupLogin(LoginPanel loginPanel) {
        JButton login = loginPanel.getLoginButton();
        JButton cancel = loginPanel.getCancelButton();
        JTextField textField = loginPanel.getUsernameField();

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Add login logic, username info : " + textField.getText()); //TODO add login logic


                user = new User(textField.getText());
                controllerClient.sendObject(new Message(null, user, MessageType.Login));
                controllerClient.setUser(user);
                frame.swapPanel(mainPanel);


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


        panel.getSetTimeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = (Integer) panel.getActivityTimerMinutesSpinner().getValue();
                controllerClient.stopActivityTimer();
                controllerClient.startActivityTimer(value * 10000);
                System.out.println(value);
            }
        });

        panel.getRemoveActivityFromCompletedList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.getListModel().remove(mainPanel.getActivityList().getSelectedIndex());
            }
        });

        panel.getCreateNewActivityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.swapPanel(new ActivityEditorPanel());
            }
        });

        System.out.println("SETUP MAIN PANEL"); //TODO action listeners for panel

        panel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


        public void setupActivityEditor(ActivityEditorPanel panel) {
        panel.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Activity activity = new Activity(panel.getTitleInputField().getText());
                activity.setDescription(panel.getDescriptionTextArea().getText());
                activity.setInstruction(panel.getInstructionTextArea().getText());
                activity.setImage(panel.getIcon());
                message = new Message(activity, user, MessageType.SaveActivity);
                controllerClient.sendObject(message);
                frame.swapPanel(mainPanel);
            }
        });

        panel.getImageSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.selectImage();
            }
        });

        panel.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.swapPanel(mainPanel);
            }
        });

        System.out.println("SETUP ACTIVITY EDITOR PANEL"); //TODO action listeners for panel

    }

    public Message getMessage() {
        return message;
    }

    public MainFrame getFrame() {
        return frame;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

}
