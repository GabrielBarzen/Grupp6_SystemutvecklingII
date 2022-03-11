package com.grp6.edim.client.view.EDIMPanels;

import javax.swing.*;
import java.awt.*;

public class ActivityEditorPanel extends EDIMPanel {


    private JTextField titleInputField;
    private JTextArea descriptionTextArea;
    private JTextArea instructionTextArea;
    private JButton imageSubmitButton;
    private JButton cancelButton;
    private JButton saveButton;

    public ActivityEditorPanel(){
        super(new GridBagLayout());
        super.setHeight(600);
        super.setWidth(700);
        GridBagConstraints c = new GridBagConstraints();


        titleInputField = new JTextField();
        descriptionTextArea = new JTextArea();
        instructionTextArea = new JTextArea();
        imageSubmitButton = new JButton();
        cancelButton = new JButton();
        saveButton = new JButton();

        JPanel titleInputPanel = new JPanel();

        JPanel descriptionInputPanel = new JPanel();
        descriptionInputPanel.setPreferredSize(new Dimension(super.getHeight()-30,150));
        JPanel instructionInputPanel = new JPanel();
        instructionInputPanel.setPreferredSize(new Dimension(super.getHeight()-30,150));
        JPanel imageInputPanel = new JPanel();
        imageInputPanel.setPreferredSize(new Dimension(super.getHeight()-30,100));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(super.getHeight()-30,100));


        JLabel titleLabel = new JLabel("AktivitetsNamn : ");

        titleInputPanel.add(titleLabel);
        titleInputField.setPreferredSize(new Dimension(super.getWidth()/2-60,30));
        titleInputPanel.add(titleInputField);


        JLabel descriptionLabel = new JLabel("Description : ");

        descriptionInputPanel.add(descriptionLabel);
        descriptionTextArea.setPreferredSize(new Dimension(super.getWidth()/2-60,60));
        descriptionInputPanel.add(descriptionTextArea);


        JLabel instructionLabel = new JLabel("Instruktion : ");

        instructionInputPanel.add(instructionLabel);
        instructionTextArea.setPreferredSize(new Dimension(super.getWidth()/2-60,60));
        instructionInputPanel.add(instructionTextArea);


        imageSubmitButton.setText("Välj bild");

        imageInputPanel.add(imageSubmitButton);

        cancelButton.setText("Avbryt");
        buttonPanel.add(cancelButton);

        saveButton.setText("Spara Aktivitet");
        buttonPanel.add(saveButton);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(titleInputPanel,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(descriptionInputPanel,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(instructionInputPanel,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(imageInputPanel,c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel,c);


        this.setVisible(true);
    }

    @Override
    public Dimension defaultDimension() {
        return new Dimension(super.getWidth(),super.getHeight());
    }

    public JTextField getTitleInputField() {
        return titleInputField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JTextArea getInstructionTextArea() {
        return instructionTextArea;
    }

    public JButton getImageSubmitButton() {
        return imageSubmitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
