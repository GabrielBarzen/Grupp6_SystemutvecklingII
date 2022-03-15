package com.grp6.edim.client.view.EDIMPanels;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends EDIMPanel {


    private JLabel usernameLabel;
    private JTextField usernameField;
    private JButton loginButton;
    private JButton cancelButton;
    public LoginPanel(){
        super(new GridBagLayout());
        super.setWidth(640);
        super.setHeight(120);

        GridBagConstraints c = new GridBagConstraints();


        JPanel usernameInputPanel = new JPanel(new FlowLayout());
        JPanel loginButtonPanel = new JPanel(new FlowLayout());
        JPanel cancelButtonPanel = new JPanel(new FlowLayout());

        usernameLabel = new JLabel();
        usernameField = new JTextField();
        loginButton = new JButton();
        cancelButton = new JButton();

        usernameInputPanel.add(usernameLabel);
        usernameInputPanel.add(usernameField);
        loginButtonPanel.add(loginButton);
        cancelButtonPanel.add(cancelButton);

        usernameField.setMinimumSize(new Dimension(200,usernameField.getHeight()));
        usernameLabel.setText("Username : ");
        loginButton.setText("Login");
        cancelButton.setText("Cancel");
        usernameField.setPreferredSize(new Dimension(200,30));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(usernameInputPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(cancelButtonPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(loginButtonPanel, c);

        this.setVisible(true);
    }

    @Override
    public Dimension defaultDimension() {
        return new Dimension(super.getWidth(),super.getHeight());
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

}
