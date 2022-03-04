import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.Activity;
import server.ServerController;
import server.User;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerControllerTest {

    ServerController controller = new ServerController(4343);

    @Test
    public void createUserTimer() {
        assertEquals("John Doe", controller.createUserTimer(new User("John Doe")).getUser().getUsername());
    }

    @Test
    public void saveActivity() {
        Activity activity = new Activity();
        activity.setActivityInfo("test activity, delete");
        activity.setActivityName("test activity, delete");
        activity.setActivityUser("N/A");
        activity.setActivityInstruction("test activity, delete");
        BufferedImage image = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.pink);
        g2d.fillRect(0,0,image.getWidth(),image.getHeight());
        ImageIcon img = new ImageIcon();
        img.setImage(image);
        activity.setActivityImage(img);
        assertEquals(activity, controller.saveActivityDetails(activity));
    }
}
