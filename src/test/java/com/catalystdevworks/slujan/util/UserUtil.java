package com.catalystdevworks.slujan.util;

import java.util.ArrayList;
import java.util.List;

import com.catalystdevworks.slujan.domain.User;

public class UserUtil
{

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "test@test.com";

	private UserUtil()
	{
	}

	public static User createUser()
	{
		return User.createUser(USERNAME, EMAIL, PASSWORD);
	}

	public static List<User> createUserList(int howMany)
	{
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < howMany; i++)
		{
			userList.add(User.createUser(USERNAME + "#" + i, EMAIL, PASSWORD));
		}
		return userList;
	}

}
