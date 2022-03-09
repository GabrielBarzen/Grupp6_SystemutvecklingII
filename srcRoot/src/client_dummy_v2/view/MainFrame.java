package client_dummy_v2.view;

import client_dummy_v2.ClientMain;
import client_dummy_v2.view.EDIMPanels.ActivityEditorPanel;
import client_dummy_v2.view.EDIMPanels.LoginPanel;
import client_dummy_v2.view.EDIMPanels.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {

    EDIMPanel panel = new EmptyPanel();
    ClientMain controller;

    public MainFrame(ClientMain clientMain) {
        this.controller = clientMain;
        this.add(panel);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        swapPanel(new LoginPanel());

    }

    public void swapPanel(EDIMPanel panel) {
        this.remove(this.panel);
        this.panel = panel;
        this.add(this.panel);
        this.setSize(this.panel.defaultDimension());
        this.setVisible(true);

        if (this.panel instanceof LoginPanel) {
            controller.setupLogin((LoginPanel) this.panel);
        }
        if (this.panel instanceof ActivityEditorPanel) {
            controller.setupActivityEditor((ActivityEditorPanel) this.panel);
        }
        if (this.panel instanceof MainPanel) {
            controller.setupMainPanel((MainPanel) this.panel);
        }
    }
}
