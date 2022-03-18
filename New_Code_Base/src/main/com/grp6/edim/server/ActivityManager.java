package com.grp6.edim.server;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;
import com.grp6.edim.shared.Activity;
import com.grp6.edim.shared.Buffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
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
                                activity.setInstruction(split[1]);
                            }
                        }
                        case "description" -> {
                            if (activity != null){
                                activity.setDescription(split[1]);
                            }
                        }
                        case "image_path" -> {
                            if (activity != null){

                                activity.setImage(ImageIO.read(new File(split[1])));
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


    public String saveActivity(Activity data) {

        if (data.getName() == null) {
            return null;
        }
        if (data.getInstruction() == null) {
            return null;
        }
        if (data.getInfo() == null) {
            return null;
        }
        try {
            if (data.getImage() == null) {
                return "Needs to include an image";
            }
        } catch (Exception e) {
            return "Needs to include an image";
        }

        activityList.add(data);

        try  {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/activities.dat"))) {
                for (Activity activity: activityList) {
                    writer.write("title:" + activity.getName());
                    writer.newLine();
                    writer.write("instruction:" + activity.getInstruction());
                    writer.newLine();
                    writer.write("description:" + activity.getInfo());
                    writer.newLine();
                    String imagePath = "images_server/" + activity.getName() + ".jpeg";
                    writer.write(imagePath);
                    writer.newLine();
                }

                BufferedImage bufferedImage = data.getImage();
                ImageIO.write(bufferedImage,"jpeg",new File("images_server/" + data.getName() + ".jpeg"));


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
