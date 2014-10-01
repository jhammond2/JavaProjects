package com.cooksys.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
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
	private TFollowing follower;
	private boolean adminRole = false;
	private boolean userRole = false;
	private boolean superRole = false;
	private boolean follow = true;
	private boolean unfollow = false;
	private List<TTweets> multi;
	private List<TFollowing> followers;

	public String logoff() {
		/*
		 * prevents user from accessing secured pages once "logged off" by
		 * setting authenticated to false
		 */

		authenticated = false;
		nameTest = "";
		pwTest = "";
		return "/index.xhtml?faces-redirect=true";
	}

	private void resetTweet() {
		/*
		 * reseting the inputtextarea
		 */
		RequestContext.getCurrentInstance().reset("form:tweet");
		tweet = "";
		System.out.println(tweet);
	}

	public List<TFollowing> getFollowersSmall() {
		List<TFollowing> list = usersDao.getFollowerList(currentUser);
		return list;
	}

	public List<TTweets> getFollowerTweets() {
		ArrayList<TTweets> results = new ArrayList<TTweets>();
		List<TTweets> list1 = usersDao.getList();
		List<TFollowing> list2 = usersDao.getFollowerList(currentUser);
		followers = list2;

		/*
		 * iterates a tweet list and a follower list. checks each iteration from
		 * both lists and adds matching entries to results arraylist, finally
		 * sets multi to equal the results.
		 */
		for (TTweets single : list1) {
			for (TFollowing foll : list2) {
				if (single.getUserNick().equals(foll.getUserFollowing())) {
					try {
						results.add(single);

						multi = results;
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(results.size());
				}
			}
		}

		return multi;
	}

	public void addFollow() {
		TFollowing newFoll = new TFollowing();

		newFoll.setTUsers(userMain);
		newFoll.setUserName(currentUser);
		newFoll.setUserFollowing(selectedUser);
		/*
		 * check to prevent multiple follows of the same user
		 */
		if (follow) {
			usersDao.follow(newFoll);
		}
		follow = false;
		unfollow = true;
	}

	public void deleteFollow() {
		usersDao.unfollow(follower);

	}

	public void followCheck() {
		List<TFollowing> check = usersDao.getFollowerList(currentUser);
		// ensures baseline values are follow=true, and unfollow=false
		follow = true;
		unfollow = false;
		/**
		 * hibernate query can return null if the user isn't following anyone,
		 * this check is to ensure that it only runs the code if it returns
		 * anything, otherwise the baseline values stay the same
		 */
		if (!check.equals(null)) {
			for (TFollowing ind : check) {
				// compares the current users with the user name field in the
				// table
				// if there is a record that already exists, follow=false,
				// unfollow=true
				if (ind.getUserName().equals(currentUser)
						&& ind.getUserFollowing().equals(selectedUser)) {
					if (ind.getUserFollowing().equals(userNick)) {
						// if userfollowing and the user nickname match, then
						// neither the follow or unfollow will be true
						follow = false;
						unfollow = false;
						break;
					}
					follow = false;
					unfollow = true;
					follower = ind;
					break;

				} else {

					follow = true;
					unfollow = false;
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
		resetTweet();
	}

	public void findUser() {
		authenticated = false;
		userList = usersDao.getUsers();
		/**
		 * iterates through the user database to find a username and pw that
		 * matches what has been entered if it finds one, then the user gets
		 * authenticated
		 * 
		 */
		for (TUsers user : userList) {
			if (user.getUserName().equals(nameTest)
					&& user.getUserPw().equals(pwTest)) {
				currentUser = user.getUserName();
				userMain = user;
				authenticated = true;
				userNick = user.getUserNickname();
				pwTest = "";
			}
		}
		System.out.println(currentUser);
	}

	// ----------------------------------------------------------------------------------------------------
	// Getters and Setters
	// ----------------------------------------------------------------------------------------------------

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
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
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

	public List<TFollowing> getFollowers() {
		return followers;
	}

	public void setFollowers(List<TFollowing> followers) {
		this.followers = followers;
	}

}
