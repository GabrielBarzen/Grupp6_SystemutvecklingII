
//Benny
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server_v2.User;
import server.UserRegister;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    UserRegister userRegister;
    User user;
    HashMap map;
    LinkedList linkedList;
    @BeforeEach
    void setUp() {
        userRegister = new UserRegister();
        user = new User("benny");
        map = new HashMap();
        linkedList = new LinkedList();
    }

    @Test
    void getUserHashMap() {
        map = (HashMap) userRegister.getUserHashMap();
        assertEquals(map, userRegister.getUserHashMap());
    }

    @Test
    void setUserHashMap() {
        assertEquals(map, userRegister.setUserHashMap(map));
    }

    @Test
    void getUserLinkedList() {
        linkedList= userRegister.getUserLinkedList();
        assertEquals(linkedList, userRegister.getUserLinkedList());
    }

    @Test
    void setUserLinkedList() {
        assertEquals(linkedList, userRegister.setUserLinkedList(linkedList));
    }

    @Test
    void updateUser() {
        userRegister.updateUser(user);
        assertTrue(userRegister.updateUser(user));

    }
}