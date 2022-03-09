package client_dummy_v2.view;

import javax.swing.*;
import java.awt.*;

public abstract class EDIMPanel extends JPanel {

    private int width;
    private int height;

    public EDIMPanel(LayoutManager layout) {
        super(layout);
    }

    public EDIMPanel() {

    }

    public abstract Dimension defaultDimension();

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
