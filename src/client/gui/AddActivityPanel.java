package client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author: Satya Singh
 * This class makes up the panel where the user is able to fill in the new activity details
 */
public class AddActivityPanel extends JPanel {
    private JTextField nameTxtField;
    private JLabel nameLabel;
    private JTextArea descriptionTxtArea;
    private JLabel descriptionLabel;
    private JTextArea instructionTxtArea;
    private JLabel instructionLabel;
    private JButton addImageBtn;
    private JLabel imageLabel;
    private JButton enterBtn;
    private JButton exitBtn;
    private BufferedImage bufferedImage;
    private AddActivityFrame addActivityFrame;

    /**
     *
     * @param addActivityFrame Frame housing this JPanel extension class
     */
    public AddActivityPanel(AddActivityFrame addActivityFrame) {
        this.addActivityFrame = addActivityFrame;
        initComponents();
    }

    /**
     * This method is responsible for instantiating the global variables in the class, and also calling the
     * initLayout() method
     */
    public void initComponents() {
        nameLabel = new JLabel("Övningens namn");
        nameTxtField = new JTextField();
        nameTxtField.setPreferredSize(new Dimension(100, 30));
        descriptionLabel = new JLabel("Information om övningen");
        descriptionTxtArea = new JTextArea();
        instructionLabel = new JLabel("Instruktioner");
        instructionTxtArea = new JTextArea();
        addImageBtn = new JButton("Lägg till en bild");
        imageLabel = new JLabel();
        imageLabel.setMaximumSize(new Dimension(40, 50));

        enterBtn = new JButton("Lägg till övning");
        exitBtn = new JButton("Avbryt");

        initLayout();
    }

    /**
     * This method sets up the GridBag Layout on the panel
     */
    public void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        c.gridx = 0;
        c.gridy = 0;
        add(nameLabel, c);
        c.gridx = 1;
        add(nameTxtField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(instructionLabel, c);
        c.gridx = 1;
        c.gridheight = 4;
        c.ipady = 40;
        JScrollPane instructionPane = new JScrollPane(instructionTxtArea);
        instructionPane.setPreferredSize(new Dimension(100,60));
        add(instructionPane, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.ipady = 0;
        add(descriptionLabel, c);
        c.gridx = 1;
        c.ipady = 40;
        c.gridheight = 4;
        JScrollPane descriptionPane = new JScrollPane(descriptionTxtArea);
        descriptionPane.setPreferredSize(new Dimension(100,60));
        add(descriptionPane, c);

        c.gridy = 9;
        c.gridx = 0;
        c.ipady = 0;
        c.gridheight = 1;
        add(addImageBtn, c);
        c.gridx = 1;
        add(imageLabel, c);

        c.gridx = 0;
        c.gridy = 14;
        c.gridheight = 1;
        c.ipady = 0;
        add(enterBtn, c);
        c.gridx = 1;
        add(exitBtn, c);

        addListeners();
    }

    /**
     * This method adds Action Listeners to the buttons on the panel
     */
    public void addListeners() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addActivityFrame.dispose();
                    Thread.sleep(500);
                } catch (InterruptedException ex) { ex.printStackTrace();  }
            }
        });
        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActivity();
            }
        });
        addImageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               selectImage();
            }
        });
    }

    /**
     * This method facilitates the selecting of an image through JFileChoose
     */
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        File file = null;
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                ImageIcon icon = new ImageIcon(scaleImage(120,120, ImageIO.read(new File(file.getAbsolutePath()))));
                imageLabel.setIcon(icon);
            } catch(Exception e) {}
        }
    }

    /**
     *
     * @param w width of scaled image
     * @param h height of scaled image
     * @param img image to scale
     * @return scaled buffered image
     * This method scales and returns an image to fit on a JLabel
     */
    private BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bufferedImage;
        bufferedImage = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        this.bufferedImage = bufferedImage;
        return bufferedImage;
    }

    /**
     * Have to change directory to imagesServer, and maybe also format to .jpg
     *
     * This method saves the image of the exercise selected by the user
     */
    private void saveImage() {
        String path[] = nameTxtField.getText().split(" ");
        String imagePath = "imagesServer/" + path[0] + ".png";

        try {
            if(bufferedImage != null) {
                File file = new File(imagePath);
                ImageIO.write(bufferedImage, "png", file);
            }

        }catch(Exception e) {    }
    }

    /**
     * Have to change file to activities.txt
     *
     * This method saves the name, instructions and description of the exercise
     * to the activities.txt file
     */
    public void saveExerciseDetails() {
        String path[] = nameTxtField.getText().split(" ");
        String imagePath = "imagesServer/" + path[0] + ".png";


        StringBuilder instructionsString = new StringBuilder();
        StringBuilder descriptionString = new StringBuilder();
        for(String line : instructionTxtArea.getText().split("\\n")) {
            instructionsString.append(line + "\t&");
        }
        for(String line : descriptionTxtArea.getText().split("\\n")) {
            descriptionString.append(line + "\t&");
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("files/activities.txt", true));
            writer.println(nameTxtField.getText());
            writer.println(instructionsString);
            writer.println(descriptionString);
            writer.println(imagePath);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the addActivity button is clicked
     * It calls the methods that save the image and exercise details
     */
    public void addActivity() {
        if(nameTxtField.getText().isBlank() || instructionTxtArea.getText().isBlank()
                || descriptionTxtArea.getText().isBlank() || imageLabel.getIcon() == null) {
            JOptionPane.showMessageDialog(null, "Fill in all fields before adding exercise");
        } else {
            JOptionPane.showMessageDialog(null, "You added " + nameTxtField.getText());
            saveImage();
            saveExerciseDetails();

            try {
                addActivityFrame.dispose();
                Thread.sleep(500);
            } catch (Exception ex) {
            }
        }
    }
}
