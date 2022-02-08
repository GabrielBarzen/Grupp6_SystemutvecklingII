package server;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Author: Linn Borgström
 * Date: 2022-02-08
 */

public class ActivityTest {
    Activity activity = new Activity();

    @Test
    public void getTime() {
    }

    @Test
    public void getActivityInfo() {
        activity.setActivityInfo("Du har nu tränat lår och rumpa samt ökat din koncentration.");
        assertEquals("Du har nu tränat lår och rumpa samt ökat din koncentration.", activity.getActivityInfo());
    }

    @Test
    public void getActivityName() {
        activity.setActivityName("Hoppa");
        assertEquals(activity.getActivityName(),"Hoppa");
    }

    @Test
    public void getActivityInstruction() {
        activity.setActivityInstruction("Gör 10 knäböj (squats). &Glöm inte att ha tyngden på hälarna och att knäna ska peka i samma riktning som tårna.");
        assertEquals("Gör 10 knäböj (squats). &Glöm inte att ha tyngden på hälarna och att knäna ska peka i samma riktning som tårna.", activity.getActivityInstruction());

    }

    @Test
    public void isCompleted() {
        activity.setCompleted(true);
        assertTrue(activity.isCompleted());
    }

    @Test
    public void creatingActivityImage(){
        activity.createActivityImage("imagesClient/exercise.png");
        ImageIcon imageIcon = new ImageIcon("imagesClient/exercise.png");
        //assertEquals(activity.getActivityImage(),imageIcon);
    }
}