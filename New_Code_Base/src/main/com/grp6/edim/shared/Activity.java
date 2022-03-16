package com.grp6.edim.shared;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;


/**
 * This class handles the information about an Activity object.
 *
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 * @version 1.0
 */

public class Activity implements Serializable {
    private static final long serialVersionUID = 200428L;
    private String name;
    private String instruction;
    private String info;
    private ImageIcon image;

    public String getTime() {
        Date date = new Date();
        String datum= " - "+date.toLocaleString();
        return datum;

    }

    public Activity(String activityName) {
        this.name =activityName;
    }

    public String getInfo() {
        return info;
    }

    public String setDescription(String activityInfo) {
        this.info = activityInfo;
        return activityInfo;
    }

    public String setActivityName(String activityName) {
        this.name =activityName;
        return activityName;
    }

    public String getName() {
        return name;
    }

    public String getInstruction() {
        return instruction;
    }

    public String setInstruction(String activityInstruction) {
        this.instruction = activityInstruction;
        return instruction;
    }

    public ImageIcon setImage(ImageIcon icon) {
        image = icon;
        return image;
    }

    public ImageIcon getImage() {
        return image;
    }

    public ImageIcon createActivityImage(String fileName) {
        image = new ImageIcon(fileName);
        return image;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + name + '\'' +
                ", activityInstruction='" + instruction + '\'' +
                ", activityInfo='" + info + '\'' +
                ", activityImage=" + image +
                '}';
    }
}


