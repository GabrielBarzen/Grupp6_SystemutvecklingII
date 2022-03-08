package server_v2;

import server.Activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ActivityManager {
    private static ArrayList<Activity> activityList;

    public static Activity getRandomActivity(){
        Random random = new Random();
        return activityList.get(random.nextInt(activityList.size()));
    }

    public static Activity getActivity(int index){
        return activityList.get(index);
    }

    public static ArrayList<Activity> getActivities(){
        return activityList;
    }


    public ActivityManager(String file) {
        buildRegister(file);
    }

    public static ArrayList<Activity> buildRegister(String file) {

        activityList = new ArrayList<Activity>();
        Scanner myReader;

        try  {
            myReader = new Scanner(new File(file));
            while(myReader.hasNextLine()) {
                Activity activity=new Activity();
                activity.setActivityName(myReader.nextLine());
                activity.setActivityInstruction(myReader.nextLine());
                activity.setActivityInfo(myReader.nextLine());
                activity.createActivityImage(myReader.nextLine());
                activityList.add(activity);
                System.out.println(activity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return activityList;
    }

    public static void updateRegister(String filePath) {
        buildRegister(filePath);
    }

}
