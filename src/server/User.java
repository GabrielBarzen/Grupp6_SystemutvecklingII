package server;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class handles the information about a user-object.
 *
 * @version 1.0
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 42L; //489241266336029083L;//-6356381908430432467L;
    private LinkedList<Activity> completedActivities;
    private String username;
    private int age;
    private int notificationInterval = 45;
    private boolean isOnline;
    private UserType userType;
    private Activity delayedActivity;
    private String className="Class: User ";

    public User(String username) {
        this.username = username;
        completedActivities=new LinkedList<>();
    }

    public LinkedList<Activity> getCompletedActivities() {
        return completedActivities;
    }

    public void addActivityToList(Activity activity) {
        completedActivities.add(activity);
    }

    public void setCompletedActivities(LinkedList<Activity> completedActivities) {
        this.completedActivities = completedActivities;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public int getAge() {
        return age;
    }

    public int setAge(int age) {
        this.age = age;
        return age;
    }

    public int getNotificationInterval() {
        return notificationInterval;
    }

    public int setNotificationInterval(int notificationInterval) {
        this.notificationInterval = notificationInterval;
        return notificationInterval;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserType setUserType(UserType userType) {
        this.userType = userType;
        return userType;
    }

    public Activity getDelayedActivity() {
        return delayedActivity;
    }

    public Activity setDelayedActivity(Activity delayedActivity) {
        this.delayedActivity = delayedActivity;
        return delayedActivity;
    }

    @Override
    public String toString() {
        return "User{" +
                "completedActivities=" + completedActivities +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", notificationInterval=" + notificationInterval +
                ", isOnline=" + isOnline +
                ", userType=" + userType +
                ", delayedActivity=" + delayedActivity +
                ", className='" + className + '\'' +
                '}';
    }
}
