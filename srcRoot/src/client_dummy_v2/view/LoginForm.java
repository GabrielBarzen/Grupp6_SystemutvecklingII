package client_dummy_v2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel loginPanel;
    private JButton exitButton;
    private JButton loginButton;
    private JTextField usernameInputField;

    public LoginForm() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };
        exitButton.addActionListener(listener);
        loginButton.addActionListener(listener);
    }

}
