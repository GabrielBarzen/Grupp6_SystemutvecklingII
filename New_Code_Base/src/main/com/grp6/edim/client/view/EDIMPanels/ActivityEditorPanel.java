package com.grp6.edim.client.view.EDIMPanels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ActivityEditorPanel extends EDIMPanel {


    private JTextField titleInputField;
    private JTextArea descriptionTextArea;
    private JTextArea instructionTextArea;
    private JButton imageSubmitButton;
    private JButton cancelButton;
    private JButton saveButton;
    private BufferedImage image;


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

    public void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
        File file = null;
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                //image = scaleImage(120,120, ImageIO.read(file));
                image = ImageIO.read(file);
                System.out.println("image = " + image);
                //imageLabel.setIcon(icon);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bufferedImage;
        bufferedImage = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        return bufferedImage;
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

    public BufferedImage getImage() {
        return image;
    }
}
