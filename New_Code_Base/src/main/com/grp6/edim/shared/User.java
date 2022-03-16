
package com.grp6.edim.shared;



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
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
