package com.grp6.edim.client.view.EDIMPanels;



import com.grp6.edim.shared.Activity;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class MainPanel extends EDIMPanel {

    private JButton exitButton;
    private JButton createNewActivityButton;
    private JButton setTimeButton;
    private JButton removeActivityFromCompletedList;
    private JSpinner activityTimerMinutesSpinner;
    private JList<Activity> activityList;
    private JLabel activityInfoLabel;
    private JLabel timeRemainingLabel;
    private JPanel listPanel;
    private DefaultListModel model;

    private int panelWidth = 1280;
    private int panelHeight = 720;

    public MainPanel(){
        super(new GridBagLayout());
        super.setWidth(1280);
        super.setHeight(720);
        GridBagConstraints c = new GridBagConstraints();

        JPanel controlPanel = new JPanel(new GridBagLayout());
        JPanel controlPannerInner = new JPanel();
        controlPannerInner.setLayout(new BoxLayout(controlPannerInner,BoxLayout.PAGE_AXIS));
        listPanel = new JPanel(new FlowLayout());
        JPanel dataPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        controlPanel.setBackground(Color.green);
        listPanel.setBackground(Color.red);
        dataPanel.setBackground(Color.blue);
        buttonPanel.setBackground(Color.black);

        exitButton = new JButton();
        createNewActivityButton = new JButton();
        setTimeButton = new JButton();
        removeActivityFromCompletedList = new JButton();
        activityTimerMinutesSpinner = new JSpinner();
        activityList = new JList<>();
        activityInfoLabel = new JLabel();
        timeRemainingLabel = new JLabel();


        controlPanel.setPreferredSize(new Dimension(panelWidth/3-30,panelHeight-100));
        c.anchor = GridBagConstraints.CENTER;
        controlPanel.add(controlPannerInner, c);

        JPanel setTimerPanel = new JPanel(new FlowLayout());
        setTimerPanel.add(activityTimerMinutesSpinner);
        setTimerPanel.add(setTimeButton);
        controlPannerInner.add(setTimerPanel);
        controlPannerInner.add(createNewActivityButton);
        controlPannerInner.add(timeRemainingLabel);

        activityTimerMinutesSpinner.setPreferredSize(new Dimension(60,30));
        activityTimerMinutesSpinner.setValue(30);
        setTimeButton.setText("Ställ tid");
        setTimeButton.setPreferredSize(new Dimension(80,30));
        createNewActivityButton.setText("Skapa ny aktivitet");
        createNewActivityButton.setPreferredSize(new Dimension(140,30));

        activityTimerMinutesSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                Integer value = (Integer) activityTimerMinutesSpinner.getValue();

                if(value >= 60) {
                    activityTimerMinutesSpinner.setValue(60);
                } if(value <= 1) {
                    activityTimerMinutesSpinner.setValue(1);
                }
            }
        });

        activityList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                activityInfoLabel.setText(activityList.getSelectedValue().getInfo());
            }
        });

        model = new DefaultListModel();
        activityList = new JList<>(model);

        activityList.setPreferredSize(new Dimension(panelWidth/3-30,panelHeight-100));
        listPanel.add(activityList);


        activityInfoLabel.setPreferredSize(new Dimension(panelWidth/3-30,panelHeight-100));
        dataPanel.add(activityInfoLabel);

        removeActivityFromCompletedList.setText("Ta bort aktivitet");
        removeActivityFromCompletedList.setPreferredSize(new Dimension(140,30));
        buttonPanel.add(removeActivityFromCompletedList);

        exitButton.setText("Avsluta");
        exitButton.setPreferredSize(new Dimension(80,30));
        buttonPanel.add(exitButton);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(controlPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(listPanel, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(dataPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel, c);

        this.setVisible(true);
    }

    @Override
    public Dimension defaultDimension() {
        return new Dimension(super.getWidth(),super.getHeight());
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getCreateNewActivityButton() {
        return createNewActivityButton;
    }

    public JButton getSetTimeButton() {
        return setTimeButton;
    }

    public JButton getRemoveActivityFromCompletedList() {
        return removeActivityFromCompletedList;
    }

    public JSpinner getActivityTimerMinutesSpinner() {
        return activityTimerMinutesSpinner;
    }

    public JList<Activity> getActivityList() {
        return activityList;
    }

    public JLabel getActivityInfoLabel() {
        return activityInfoLabel;
    }

    public JLabel getTimeRemainingLabel() {
        return timeRemainingLabel;
    }

    public DefaultListModel getListModel() {
        return model;
    }
}
