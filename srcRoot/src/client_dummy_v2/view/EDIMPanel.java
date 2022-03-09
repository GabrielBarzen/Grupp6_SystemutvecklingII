package client_dummy_v2.view;

import javax.swing.*;
import java.awt.*;

public abstract class EDIMPanel extends JPanel {

    public EDIMPanel(LayoutManager layout) {
        super(layout);
    }

    public EDIMPanel() {

    }

    public abstract Dimension defaultDimension();

}
