package service;

import java.util.List;

import domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}
