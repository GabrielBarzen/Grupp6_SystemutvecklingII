package client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

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
    private File imageFile;

    private AddActivityFrame addActivityFrame;
    private MainFrame mainFrame;

    public AddActivityPanel(AddActivityFrame addActivityFrame, MainFrame mainFrame) {
        this.addActivityFrame = addActivityFrame;
        this.mainFrame = mainFrame;
        initComponents();
    }

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
        add(descriptionLabel, c);
        c.gridx = 1;
        c.gridheight = 4;
        c.ipady = 40;
        add(descriptionTxtArea, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.ipady = 0;
        add(instructionLabel, c);
        c.gridx = 1;
        c.ipady = 40;
        c.gridheight = 4;
        add(instructionTxtArea, c);

        c.gridy = 9;
        c.gridx = 0;
        c.ipady = 0;
        c.gridheight = 1;
        add(addImageBtn, c);
        c.gridx = 1;
//        c.ipady = 40;
//        c.gridheight = 4;
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
               imageFile = selectImage();
            }
        });
    }

    private File selectImage() {
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
        return file;
    }

    private BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bi;
        bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        return bi;
    }
//    private void saveImage() {
//        try {
//            BufferedImage bufferedImage = new BufferedImage(imageFile.getAbsolutePath());
//            bufferedImage.get
//
//        }catch(Exception e) { }
//    }

    public void addActivity() {
        JOptionPane.showMessageDialog(null, "You added " + nameTxtField.getText());

        try {
            addActivityFrame.dispose();
            Thread.sleep(500);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }
}
