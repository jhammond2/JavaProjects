package com.cooksys.beans;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.models.TFollowing;
import com.cooksys.models.TTweets;
import com.cooksys.models.TUsers;

@Component
@Scope("session")
public class UserAuthenticate {

	@Autowired
	private UsersDAO usersDao;

	private String nameTest = "";
	private String pwTest = "";
	private boolean authenticated = false;
	private List<TUsers> userList;
	private String currentUser;
	private TUsers userMain;
	private String selectedUser;
	private String tweet = "";
	private String userNick;
	private Date timeStamp;
	private Integer userId;
	private TTweets data;
	private TFollowing follower;
	private boolean adminRole = false;
	private boolean userRole = false;
	private boolean superRole = false;
	private boolean follow = true;
	private boolean unfollow = false;
	private List<TTweets> multi;

	public String logoff() {
		/*
		 * prevents user from accessing secured sights once "logged off"
		 */

		authenticated = false;
		nameTest = "";
		pwTest = "";
		return "/hello.xhtml";
	}

	private void getDetails() {

	}


	public void addFollow() {
		TFollowing newFoll = new TFollowing();

		newFoll.setTUsers(userMain);
		newFoll.setUserName(currentUser);
		newFoll.setUserFollowing(selectedUser);
		if (follow) {
			usersDao.follow(newFoll);
		}
		follow = false;
		unfollow = true;
	}
	public void deleteFollow() {
		usersDao.unfollow(follower);;
	}
	public void getMultiFollow() {

		multi = usersDao.getTweetsByAllFollowing(currentUser);
		
	}
	public void followCheck() {
		List<TFollowing> check = usersDao.getFollowerList(currentUser);
		System.out.println("starting follow check");
		System.out.println("currentUser: " + currentUser);
		System.out.println("selectedUser: " + getSelectedUser());
		follow = true;
		unfollow = false;
		if (!check.equals(null)) {
			for (TFollowing ind : check) {
				System.out.println("ind.user: " + ind.getUserName());
				System.out.println("ind.follower: " + ind.getUserFollowing());
				if (ind.getUserName().equals(currentUser)
						&& ind.getUserFollowing().equals(selectedUser)) {
					follow = false;
					unfollow = true;
					follower = ind;
					System.out.println("follower set to: " + follower.getUserName()+ " " + follower.getUserFollowing());
					System.out.println("found, follow:" + follow + " unfollow: "
							+ unfollow);
					break;
				} else {
					follow = true;
					unfollow = false;
					System.out.println("didn't find, follow:" + follow + " unfollow: "
							+ unfollow);
				}
			}
		} 
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void submitTweet() {
		TTweets newTweet = new TTweets();
		newTweet.setTweet(tweet);
		newTweet.setTUsers(userMain);
		newTweet.setUserNick(userNick);
		newTweet.setTimeStamp(new java.util.Date());
		usersDao.addTweet(newTweet);
	}

	public void findUser() {
		authenticated = false;
		userList = usersDao.getUsers();
		for (TUsers user : userList) {
			if (user.getUserName().equals(nameTest)
					&& user.getUserPw().equals(pwTest)) {
				System.out.println("findUser: " + user.getUserName());
				currentUser = user.getUserName();
				userMain = user;
				authenticated = true;
				userNick = user.getUserNickname();
				// roleCheck(user);
				System.out.println(authenticated);
				// getDetails();

			}
		}
		System.out.println(currentUser);
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

	public String getPwTest() {
		return pwTest;
	}

	public void setPwTest(String pwTest) {
		this.pwTest = pwTest;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public TUsers getUserMain() {
		return userMain;
	}

	public boolean isAdminRole() {
		return adminRole;
	}

	public boolean isUserRole() {
		return userRole;
	}

	public boolean isSuperRole() {
		return superRole;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSelectedUser() {
		System.out.println("getting: " + selectedUser);
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
		System.out.println(selectedUser);
		this.selectedUser = selectedUser;
	}

	public boolean isFollow() {
		return follow;
	}

	public boolean isUnfollow() {
		return unfollow;
	}

	public List<TTweets> getMulti() {
		return multi;
	}

}
