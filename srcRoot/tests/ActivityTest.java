


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Activity;


import javax.swing.*;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Author: Linn Borgström
 * Date: 2022-02-08
 */

class ActivityTest {
    Activity activity = new Activity();

    /**
     * This doesn't work therefor I make a new object on row 23
     */
    @BeforeEach
    public void setUp(){
        activity = new Activity();
    }

    /**
     * Tests that the time is returned correctly
     */
    @Test
    public void getTime() {
        Date date = new Date();
        String todaysDate = date.getHours() + ":" + date.getMinutes();
        assertEquals(todaysDate,activity.getTime());
    }

    /**
     * Tests that the method returned correctly
     */
    @Test
    public void getActivityInfo() {
        activity.setActivityInfo("Du har nu tränat lår och rumpa samt ökat din koncentration.");
        assertEquals("Du har nu tränat lår och rumpa samt ökat din koncentration.", activity.getActivityInfo());
    }

    /**
     * Tests that the method sets the activity info correctly
     */
    @Test
    public void setActivityInfo(){
        assertEquals("Du har nu tränat lår och rumpa samt ökat din koncentration.",activity.setActivityInfo("Du har nu tränat lår och rumpa samt ökat din koncentration."));
    }
    /**
     * Tests that the method sets the activity as completed
     */
    @Test
    public void setCompleted(){
        assertTrue(activity.setCompleted(true));

    }
    /**
     * Tests that the method gets the activity user is returned correctly
     */
    @Test
    public void getActivityUser(){
        activity.setActivityUser("Kalle");
        assertEquals("Kalle",activity.getActivityUser());

    }
    /**
     * Tests that the method sets the activity user is set correctly
     */
    @Test
    public void setActivityUser(){
        assertEquals("Kalle",activity.setActivityUser("Kalle"));

    }
    /**
     * Tests that the method sets the activity image is set correctly
     */
    @Test
    public void setActivityImage(){
        ImageIcon imageIcon = new ImageIcon("Ursprungliga Projektfiler/EDIM/imagesClient/exercise.png");
        assertEquals(imageIcon,activity.setActivityImage(imageIcon));

    }
    /**
     * Tests that the method gets the activity image is returned correctly
     */
    @Test
    public void getActivityImage(){
        ImageIcon imageIcon = new ImageIcon("Ursprungliga Projektfiler/EDIM/imagesClient/exercise.png");
        activity.setActivityImage(imageIcon);
        assertEquals(imageIcon,activity.getActivityImage());

    }
    /**
     * Tests that the method creates an object
     */
    @Test
    public void createActivityImage(){
        assertNotNull(activity.createActivityImage("Ursprungliga Projektfiler/EDIM/imagesClient/exercise.png"));

    }
    /**
     * Tests the name of the Activity returns the right name
     */
    @Test
    public void getActivityName() {
        activity.setActivityName("Hoppa");
        assertEquals("Hoppa",activity.getActivityName());
    }
    /**
     * Tests that the method sets the activity name is set correctly
     */
    @Test
    public void setActivityName(){
        assertEquals("Hoppa",activity.setActivityName("Hoppa"));
    }

    /**
     * Tests the instruction of the activity returns the right instruction
     */
    @Test
    public void getActivityInstruction() {
         activity.setActivityInstruction("Hoppa upp och ner som en tok");
         assertEquals("Hoppa upp och ner som en tok", activity.getActivityInstruction());

    }
    /**
     * Tests the instruction of the activity sets it in the right way
     */
    @Test
    public void setActivityInstruction(){
        activity.setActivityInstruction("Hoppa upp och ner som en tok");

    }

    /**
     * Tests that the activity returns that it's completed
     */
    @Test
    public void isCompleted() {
        activity.setCompleted(true);
        assertTrue(activity.isCompleted());
    }


}