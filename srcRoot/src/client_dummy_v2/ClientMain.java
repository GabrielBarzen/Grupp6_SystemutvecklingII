package client_dummy_v2;

import client_dummy_v2.view.EDIMPanels.ActivityEditorPanel;
import client_dummy_v2.view.EDIMPanels.LoginPanel;
import client_dummy_v2.view.MainFrame;
import client_dummy_v2.view.EDIMPanels.MainPanel;

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
    }
    public void setupActivityEditor(ActivityEditorPanel panel) {
    }
}
