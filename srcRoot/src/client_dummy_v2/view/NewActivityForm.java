package client_dummy_v2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewActivityForm {
    private JButton sparaAktivitetButton;
    private JButton avbrytButton;
    private JButton läggTillBildButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public NewActivityForm() {
        ActionListener listener = actionEvent -> {

        };
        läggTillBildButton.addActionListener(listener);
        avbrytButton.addActionListener(listener);
        sparaAktivitetButton.addActionListener(listener);
    }

}
