package com.cooksys.beans;

import java.util.List;

import com.cooksys.models.TFollowing;
import com.cooksys.models.TTweets;
import com.cooksys.models.TUsers;

public interface UsersDAO {

	public void addUser(TUsers user);

	public void updateUser(TUsers user);

	public void deleteUser(TUsers user);

	public TUsers getUserById(String userName);

	public List<TUsers> getUsers();
	
	public List<TTweets> getList();
	
	public void addTweet(TTweets tweet);
	
	public void updateTweet(TUsers user, String tweet);
	
	public void deleteTweet(TUsers user);
	
	public List<TTweets> getTweetsBySingleUser(String user);
	
	public void follow(TFollowing follow); 
	
	public void unfollow(TFollowing follow);
	
	public List<TFollowing> getFollowerList(String user);
		
	

}
