package util;

import java.util.ArrayList;
import java.util.List;

import domain.User;

public class UserUtil {

    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "test@test.com";

    private UserUtil() {
    }

    public static User createUser() {
        return User.createUser(ID, EMAIL, PASSWORD);
    }

    public static List<User> createUserList(int howMany) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userList.add(User.createUser(ID + "#" + i, EMAIL, PASSWORD));
        }
        return userList;
    }

}
