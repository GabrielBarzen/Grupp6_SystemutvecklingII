package com.grp6.edim.server;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;
import com.grp6.edim.shared.Activity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ActivityManager {
    private ArrayList<Activity> activityList;

    public Activity getRandomActivity(){
        Random random = new Random();
        return activityList.get(random.nextInt(activityList.size()));
    }

    public Activity getActivity(int index){
        return activityList.get(index);
    }

    public ArrayList<Activity> getActivities(){
        return activityList;
    }


    public ActivityManager(String file) {
        this.activityList = buildRegister(file);
    }

    public ArrayList<Activity> buildRegister(String file) {
        ArrayList<Activity> activityList = new ArrayList<Activity>();

        try  {
            try (BufferedReader reader = new BufferedReader(new FileReader("files/activities.dat"))) {
                String current = null;
                Activity activity = null;

                while ((current = reader.readLine()) != null) {
                    String[] split = current.split(":");
                    switch (split[0]) {
                        case "title" -> {
                            if (activity == null){
                                activity = new Activity(split[1]);
                            }
                        }
                        case "instruction" -> {
                            if (activity != null){
                                activity.setActivityInstruction(split[1]);
                            }
                        }
                        case "description" -> {
                            if (activity != null){
                                activity.setDescription(split[1]);
                            }
                        }
                        case "image_path" -> {
                            if (activity != null){
                                activity.setActivityImage(new ImageIcon(split[1]));
                                activityList.add(activity);
                                activity = null;
                            }
                        }
                        default -> Logger.log("Invalid option in switch case : " + split[0], LogLevel.Warning);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activityList;
    }


    public void saveActivity(Activity data) {

        if (data.getActivityName() == null) {
            return;
        }
        if (data.getActivityInstruction() == null) {
            return;
        }
        if (data.getActivityInfo() == null) {
            return;
        }
        if (data.getActivityImage() == null) {
            return;
        }

        try  {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/activities.dat"))) {
                Activity activity = null;

                writer.write("title:" + activity.getActivityName());
                writer.write("instruction:" + activity.getActivityInstruction());
                writer.write("description:" + activity.getActivityInfo());

                ImageIcon icon = activity.getActivityImage();
                String imagePath = "images_server/" + activity.getActivityName() + ".jpg";
                BufferedImage image = (BufferedImage) icon.getImage();

                try {
                    if(image != null) {
                        File file = new File(imagePath);
                        ImageIO.write(image, "jpg", file);
                    }

                }catch(Exception e) {    }

                writer.write("image_path:" + imagePath);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
