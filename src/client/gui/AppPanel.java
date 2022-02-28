package client.gui;

import server.Activity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.List;
import java.io.File;
import java.util.*;
import java.awt.event.*;
import java.util.Timer;

/**
 * This is the panel in the frame that contains pretty much all the components in the GUI.
 *
 * @version 1.0
 * @author Oscar Kareld, Chanon Borgstrom, Carolin Nordstrom
 */
public class AppPanel extends JPanel implements ActionListener {
    private MainPanel mainPanel;

    private String[] interval;
    private JLabel lblTimerInfo;
    private JTextArea taActivityInfo;
    private JComboBox cmbTimeLimit;
    private LinkedList<Activity> activities;
    private JList activityList;
    private Vector list = new Vector();

    private JButton btnRemoveActivity;
    private JButton btnLogOut;
    private JButton btnInterval;
    private JPanel intervalPnl;
    private JLabel lblInterval;
    private JButton btnAddExercise;
    private TrayIcon trayIcon;

    private BorderLayout borderLayout = new BorderLayout();
    //private ActionListener listener = new ButtonListener();
    private DefaultListModel listModel;

    private String className = "Class: AppPanel: ";
    private Color clrPanels = new Color(142, 166, 192);
    private Color clrMidPanel = new Color(127, 140, 151, 151);

    private Timer timer;
    private int minuteInterval;
    private int secondInterval;
    private Activity currentActivity;


    public AppPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setupPanel();
        createComponents();
        activities = new LinkedList<>();
    }

    public void setupPanel() {
        setSize(new Dimension(819, 438));
    }

    public void createComponents() {
        setLayout(borderLayout);

        createActivityList();
        createTAActivityInfo();
        createCBTimeLimit();
        createIntervalPanel();

        btnLogOut = new JButton("Logga ut");

        add(activityList, BorderLayout.CENTER);
        add(btnLogOut, BorderLayout.SOUTH);
        add(taActivityInfo, BorderLayout.EAST);
        add(intervalPnl, BorderLayout.WEST);

        btnLogOut.addActionListener(this);
        btnInterval.addActionListener(this);
        btnRemoveActivity.addActionListener(this);
        addActivityListener();
        /*btnLogOut.addActionListener(listener);
        btnInterval.addActionListener(listener);
        btnRemoveActivity.addActionListener(listener);
        addActivityListener();*/
    }

    /**
     * @author Satya Singh
     * This method creates the interval panel on the left side of the GUI and
     * populates it with componenets
     */
    private void createIntervalPanel() {
        intervalPnl = new JPanel();
        intervalPnl.setLayout(new BorderLayout());
        intervalPnl.setBackground(clrPanels);
        intervalPnl.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY));

        btnRemoveActivity = new JButton("Ta bort utförd aktivitet");

        btnInterval = new JButton("Ändra intervall");
        btnAddExercise = new JButton("Lägg till övning");
        btnAddExercise.addActionListener(this);
        lblInterval = new JLabel();
        lblTimerInfo = new JLabel();
        startTimer(Integer.parseInt((String) cmbTimeLimit.getSelectedItem()), 59);
        updateLblInterval();

        JPanel centerPnl = new JPanel();
        centerPnl.setSize(new Dimension(intervalPnl.getWidth(), intervalPnl.getHeight()));
        centerPnl.setBackground(clrPanels);
        centerPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        centerPnl.add(cmbTimeLimit, c);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        centerPnl.add(btnInterval, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        centerPnl.add(btnAddExercise, c);

        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 0;
        centerPnl.add(btnRemoveActivity,c);

        intervalPnl.add(lblInterval, BorderLayout.NORTH);
        intervalPnl.add(centerPnl, BorderLayout.CENTER);
        intervalPnl.add(lblTimerInfo, BorderLayout.SOUTH);

    }

    public void updateLblInterval() {
        int interval;
        interval = Integer.parseInt((String) cmbTimeLimit.getSelectedItem());
        lblInterval.setText("Aktivt tidsintervall: " + interval + " minuter");
    }

    public void createCBTimeLimit() {
        interval = new String[]{"1", "5", "15", "30", "45", "60"};
        cmbTimeLimit = new JComboBox<>(interval);
        cmbTimeLimit.setSelectedIndex(3);
    }

    public void startTimer(int minutes, int seconds) {
        minuteInterval = minutes - 1;
        secondInterval = seconds;
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                String time;
                if (secondInterval < 10) {
                    time = String.format("timer: %d:0%d", minuteInterval, secondInterval);
                } else {
                    time = String.format("timer: %d:%d", minuteInterval, secondInterval);
                }
                lblTimerInfo.setText(time);
                decreaseInterval();
            }
        }, delay, period);
    }

    public void decreaseInterval() {
        secondInterval --;
        if (secondInterval == -1) {
            minuteInterval--;
            if (minuteInterval == -1) {
                stopTimer();
                showWindowsNotification();
            }
            secondInterval = 59;
        }
    }

    public void countTimerInterval(int chosenInterval) {
        int difference = 0;
        if (minuteInterval > chosenInterval) { //Vi var på 15 (minuteInterval), sedan ändrade vi till 5 (chosenInterval)
            difference = minuteInterval - chosenInterval;
            System.out.println("if-satsen: Difference: " + difference);
            minuteInterval = minuteInterval - difference - 1; //-1
            System.out.println("minuteInterval: " + minuteInterval);
        } else {
            difference = chosenInterval - minuteInterval;
            System.out.println("Else-satsen: Difference: " + difference);
            minuteInterval = minuteInterval + difference - 1;
            System.out.println("minuteInterval: " + minuteInterval);
        }
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void createTAActivityInfo() {
        taActivityInfo = new JTextArea();
        taActivityInfo.setBackground(clrPanels);
        taActivityInfo.setPreferredSize(new Dimension(200, 80));
        taActivityInfo.setLineWrap(true);
        taActivityInfo.setWrapStyleWord(true);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        taActivityInfo.setFont(font);
        taActivityInfo.setEditable(false);
    }

    public void createActivityList() {
        listModel = new DefaultListModel();
        activityList = new JList<>(listModel);
        //activityList = new JList();
        activityList.setPreferredSize(new Dimension(400, 320));
        activityList.setBorder(BorderFactory.createTitledBorder("Avklarade aktiviteter"));
        activityList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        activityList.setFont(font);
    }

    public void addActivityListener() {
        activityList.addListSelectionListener(e -> {
            String activityName = (String) activityList.getSelectedValue();
            String newActivityName = splitActivityNameAndTime(activityName);
            for (Activity activity : activities) {
                if (activity.getActivityName().equals(newActivityName)) {
                    showActivityInfo(activity.getActivityInfo());

                }
            }
        });
    }

    public String splitActivityNameAndTime(String activityName) {
        String[] activityNameParts = activityName.split("-");
        activityName = activityNameParts[0].trim();
        return activityName;
    }

    public void updateActivityList(Activity activity) {
        stopTimer();
        startTimer(Integer.parseInt((String) cmbTimeLimit.getSelectedItem()), 59);
        activities.add(activity);
        list.add(activity.getActivityName() + activity.getTime());
        activityList.setListData(list);
        String newActivityName = splitActivityNameAndTime(activity.getActivityName());
        activity.setActivityName(newActivityName);
        updateUI();
    }
    private void updateActivityListWithOneRemoved() {
        int activityToRemoveIndex = activityList.getSelectedIndex();
        String activityToRemove = String.valueOf(activityList.getSelectedValue());
        System.out.println("Remove: " + activityToRemove);
        /*for (Activity a:activities ) {
            String titleTime = a.getActivityName() + " - " + a.getTime();
            if(titleTime.equals(activityToRemove)){
                activityList.remove(activityToRemoveIndex);
                System.out.println("Removed: "+ a.getActivityName());
            }

        }*/
        activityList.remove(activityToRemoveIndex);
        activityList.setSelectedIndex(0);
        //activityList.removeListSelectionListener(listner);
    }

    public void showActivityInfo(String activityInfo) {
        String[] lines = activityInfo.split("&");
        StringBuilder info = new StringBuilder();
        for(String s: lines) {
            info.append(s + " ");
        }
        taActivityInfo.setText(info.toString());
    }

    public ImageIcon createActivityIcon(Activity activity) {
        ImageIcon activityIcon = activity.getActivityImage();
        Image image = activityIcon.getImage();
        Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public void showNotification(Activity activity) {

        Toolkit.getDefaultToolkit().beep();
        ImageIcon activityIcon = createActivityIcon(activity);
        String[] buttons = {"Jag har gjort aktiviteten!", "Påminn mig om fem minuter",};
        String[] instructions = activity.getActivityInstruction().split("&");

        int answer = welcomePane.showOptionDialog(null, instructions, activity.getActivityName(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, activityIcon, buttons, buttons[0]);
        if (answer == 0) {
            updateAppPanelCompletedActivity(activity);
        } else {
            updateAppPanelSnooze(activity);
        }
    }

    private void updateAppPanelSnooze(Activity activity) {
        stopTimer();
        startTimer(5, 59);
        activity.setCompleted(false);
        mainPanel.sendActivityFromGUI(activity);
    }

    private void updateAppPanelCompletedActivity(Activity activity) {
        activity.setCompleted(true);
        mainPanel.sendActivityFromGUI(activity);
        updateActivityList(activity);
    }

    /**
     * @author Satya Singh
     * This function checks to see if the window is minimized and if so,
     * displays a Windows notification to the user informing them that it's time to do an exercise.
     */
    public void showWindowsNotification() {
        if(mainPanel.checkIfMinimized()) {
            try {
                SystemTray systemTray = SystemTray.getSystemTray();

                Image image = ImageIO.read(new File("imagesClient/exercise.png"));

                trayIcon = new TrayIcon(image, "Motion dags");
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip("EDIM");
                systemTray.add(trayIcon);
                trayIcon.displayMessage("Dags att göra en övning", "Every Day In Motion", TrayIcon.MessageType.NONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendNotification(Activity activity) {
        currentActivity = activity;
        showNotification(activity);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object click = e.getSource();
        int interval;
        if (click == btnLogOut) {
            mainPanel.logOut();
        }
        if (click == btnInterval) {
            interval = Integer.parseInt((String) cmbTimeLimit.getSelectedItem());
            countTimerInterval(interval);
            mainPanel.sendChosenInterval(interval);
            updateLblInterval();
        }
        if(click == btnAddExercise) {
            new AddActivityFrame(mainPanel);
        }
        if(click == btnRemoveActivity){
            updateActivityListWithOneRemoved();

        }

    }


    public class welcomePane extends JOptionPane {
        @Override
        public int getMaxCharactersPerLineCount() {
            return 10;
        }
    }

    public void showWelcomeMessage(String userName) {
        ImageIcon welcomeIcon = new ImageIcon("imagesClient/exercise.png");
        Image image = welcomeIcon.getImage();
        Image newImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        JOptionPane.showMessageDialog(null, "Välkommen " + userName + "!" + "\nEDIM kommer skicka notiser till dig med jämna mellanrum,\n" +
                "med en fysisk aktivitet som ska utföras.\n" +
                "Hur ofta du vill ha dessa notiser kan du ställa in själv.", "Välkommen till Edim ", 2, new ImageIcon(newImg));
    }



}
