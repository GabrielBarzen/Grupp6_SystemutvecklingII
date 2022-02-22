package server;

import javax.swing.*;
import java.io.Serializable;
import java.util.Calendar;


/**
 * This class handles the information about an Activity object.
 *
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 * @version 1.0
 */

public class Activity implements Serializable {
    private static final long serialVersionUID = 200428L;
    private String activityName;
    private String activityInstruction;
    private String activityInfo;
    private boolean isCompleted = false;
    private String activityUser;
    private ImageIcon activityImage;
    private boolean isNew = false;

    public Activity () {}

    public String getTime() {
        Calendar cal=Calendar.getInstance();
        String datum=cal.getTime().getHours()+":"+cal.getTime().getMinutes();
        return datum;

    }

    public Activity(String activityName) {
        this.activityName=activityName;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public String setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
        return activityInfo;
    }

    public String setActivityName(String activityName) {
        this.activityName=activityName;
        return activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityInstruction() {
        return activityInstruction;
    }

    public String setActivityInstruction(String activityInstruction) {
        this.activityInstruction = activityInstruction;
        return activityInstruction;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean setCompleted(boolean completed) {
        isCompleted = completed;
        return completed;
    }

    public String getActivityUser() {
        return activityUser;
    }

    public String setActivityUser(String activityUser) {
        this.activityUser = activityUser;
        return activityUser;
    }

    public ImageIcon setActivityImage(ImageIcon icon) {
        activityImage = icon;
        return icon;
    }

    public ImageIcon getActivityImage() {
        return activityImage;
    }

    public ImageIcon createActivityImage(String fileName) {
        activityImage = new ImageIcon(fileName);
        return activityImage;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew() {
        isNew = true;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", activityInstruction='" + activityInstruction + '\'' +
                ", activityInfo='" + activityInfo + '\'' +
                ", isCompleted=" + isCompleted +
                ", activityUser='" + activityUser + '\'' +
                ", activityImage=" + activityImage +
                ", isNew=" + isNew +
                '}';
    }
}


