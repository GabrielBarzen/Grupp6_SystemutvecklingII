
//Benny

import org.junit.jupiter.api.Test;
import server.Activity;
import server_v2.User;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private LinkedList<Activity> completedActivities=new LinkedList<>();

    private User user=new User("Benny");

    @Test
    public void getCompletedActivities() {
/*       completedActivities.add();
        user.setCompletedActivities(completedActivities);
        user.getCompletedActivities();*/
    }

    @Test
    public void addActivityToList() {
    }

    @Test
    public void setCompletedActivities() {
    }

    @Test
    public void getUsername() {
        assertEquals("Benny",user.getUsername());
    }

    @Test
    public void setUsername() {
        assertEquals("Laban",user.setUsername("Laban"));
    }

    @Test
    public void getAge() {
        user.setAge(12);
        assertEquals(12,user.getAge());
    }

    @Test
    public void setAge() {
        assertEquals(13,user.setAge(13));
    }

    @Test
    public void getNotificationInterval() {
        user.setNotificationInterval(40);
        assertEquals(40,user.getNotificationInterval());
    }

    @Test
    public void setNotificationInterval() {
        assertEquals(38,user.setNotificationInterval(38));
    }

    @Test
    public void isOnline() {
        //Todo Verkar aldrig vara skapad
    }

    @Test
    public void setOnline() {
        //Todo Verkar aldrig vara skapad
    }

    @Test
    public void getUserType() {
        //Todo Bara en enum
    }

    @Test
    public void setUserType() {
        //Todo Bara en enum
    }

    @Test
    public void getDelayedActivity() {
    }

    @Test
    public void setDelayedActivity() {
    }
}