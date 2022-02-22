package server;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class creates a register that handles Activity objects.
 *
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 * @version 1.0
 */

public class ActivityRegister {
    private LinkedList<Activity> activityRegister;
    private String className="Class: ActivityRegister ";

    public ActivityRegister(String file) {
        createRegister(file);
    }


    /**
     * @author Satya Singh
     * Rewrote the old method to work with the 'Add new activity' functionality.
     * This method reads the lines in the text file and creates a
     * Linked List with the activities in the file.
     * @param file is the text file containing the activities
     */
    public LinkedList<Activity> createRegister(String file) {

        activityRegister=new LinkedList<Activity>();
        Scanner myReader;

        try  {
            myReader = new Scanner(new File(file));
            while(myReader.hasNextLine()) {
                Activity activity=new Activity();
                activity.setActivityName(myReader.nextLine());
                activity.setActivityInstruction(myReader.nextLine());
                activity.setActivityInfo(myReader.nextLine());
                activity.createActivityImage(myReader.nextLine());
                activityRegister.add(activity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return activityRegister;
    }

    public void updateRegister(String filePath) {
        createRegister(filePath);
    }

    public LinkedList<Activity> getActivityRegister() {
        return activityRegister;
    }
}
