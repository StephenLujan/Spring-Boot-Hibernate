package com.catalystdevworks.slujan.service;

import java.util.List;

import com.catalystdevworks.slujan.domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}
