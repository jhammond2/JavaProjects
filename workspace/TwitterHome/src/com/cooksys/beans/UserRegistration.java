package com.cooksys.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.models.TFollowing;
import com.cooksys.models.TUsers;

@Component
@Scope("session")
public class UserRegistration {

	@Autowired
	private UsersDAO usersDao;
	private List<TUsers> userCheck;
	private String userName;
	private String userPw;
	private String userNick;
	private String userExist;
	private boolean exist = false;

	public void registerUser() {
		//check to see if there is already a user with that user name in the database. 
		//if there is, set boolean exist to true to prevent duplicate usernames
		exist = false;
		userCheck = usersDao.getUsers();
		for (TUsers user2 : userCheck) {
			if (user2.getUserName().trim().equals(userName.trim())) {
				System.out.println(user2.getUserName());
				System.out.println(userName);
				exist = true;
				break;
			}
		}
		System.out.println(userExist);
		System.out.println(exist);
		if (!exist) {
			TUsers user = new TUsers();
			user.setUserRole("user");
			user.setUserNickname(userNick);
			user.setUserName(userName);
			user.setUserPw(userPw);
			usersDao.addUser(user);
			userExist = "user " + userName + " created.";
			TFollowing newFoll = new TFollowing();
			newFoll.setTUsers(user);
			newFoll.setUserName(userName);
			newFoll.setUserFollowing(userNick);
			usersDao.follow(newFoll);
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

}
