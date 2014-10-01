package com.cooksys.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.models.TUsers;

@Component
@Scope("session")
public class UserRegistration {

	@Autowired
	private UsersDAO usersDao;

	private String userName;
	private String userPw;
	private String userNick;
	

	public void registerUser() {
		TUsers user = new TUsers();
		user.setUserRole("user");
		user.setUserNickname(userNick);
		user.setUserName(userName);
		user.setUserPw(userPw);
		usersDao.addUser(user);
		
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
