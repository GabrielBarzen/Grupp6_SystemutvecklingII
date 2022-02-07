package client.gui;

import server.Activity;

import static org.junit.Assert.*;

public class AppPanelTest {
AppPanel appPanel=new AppPanel();
    @org.junit.Test
    public void updateLblInterval() { //should return the time value chosen by the user in exakt numbers as boolean.
        assertEquals(appPanel.updateLblInterval(2),2);
    }

        @org.junit.Test
    public void shownotifikation() { //should return the time value chosen by the user in exakt numbers as boolean.
            int x=0;
            int y=0;
        assertEquals(appPanel.showNotification(new Activity(),1000,1000),2000);
    }
    }


