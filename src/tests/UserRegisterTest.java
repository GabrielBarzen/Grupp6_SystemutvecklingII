package tests;
//Benny
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.User;
import server.UserRegister;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    UserRegister user;
    User user2;
    HashMap hash;
    LinkedList linkedList;
    @BeforeEach
    void setUp() {
        user=new UserRegister();
        user2=new User("benny");
        hash=new HashMap();
        linkedList=new LinkedList();
    }

    @Test
    void getUserHashMap() {
        hash= (HashMap) user.getUserHashMap();
        assertEquals(hash,user.getUserHashMap());
    }

    @Test
    void setUserHashMap() {
        assertEquals(hash,user.setUserHashMap(hash));
    }

    @Test
    void getUserLinkedList() {
        linkedList=user.getUserLinkedList();
        assertEquals(linkedList,user.getUserLinkedList());
    }

    @Test
    void setUserLinkedList() {
        assertEquals(linkedList,user.setUserLinkedList(linkedList));
    }

    @Test
    void updateUser() {
        user.updateUser(user2);
        assertTrue(user.updateUser(user2));

        //testet är rätt för den visar samma user, men behöver förändras för att visa Linkedlistversionen.
    }
}