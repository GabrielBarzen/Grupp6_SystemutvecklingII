import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Activity;
import server.ActivityRegister;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: Linn Borgström
 * Date: 2022-02-15
 */
public class ActivityRegisterTest {
    private ActivityRegister activityRegister;
    private Activity activity;
    private LinkedList<Activity> activityLinkedList;

    @BeforeEach
    public void setUp(){
        activityRegister = new ActivityRegister("files/activities.txt");
        activity = new Activity();
    }

    /**
     * Tests that the method makes the same list that is created in the class
     */
    @Test
    public void getActivityRegister() {
        activityLinkedList = new LinkedList<>();
        activityLinkedList = activityRegister.createRegister("files/activities.txt");
        assertEquals(activityLinkedList,activityRegister.getActivityRegister());
    }

    /**
     * Tests that the method created an object
     */
    @Test
    public void createActivityRegister() {
        assertNotNull(activityRegister.createRegister("files/activities.txt"));
    }

    /**
     * @author Satya Singh
     * Invalid value (non existent file) test for creating an activity register
     */
    @Test
    public void createActivityRegisterInvalidFile() {
        assertNotNull(activityRegister.createRegister("nonExistent.txt"));
    }
}