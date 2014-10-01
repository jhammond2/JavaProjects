package com.cooksys.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cooksys.models.TFollowing;
import com.cooksys.models.TTweets;

@Component
@Scope("session")
public class IndexTweets {
	
	
	
	@Autowired
	private UsersDAO userDao;
	
	public List<TTweets> tweets;
	
	public List<TTweets> getTweets() {
		tweets = userDao.getList();
		
		
		return tweets;
	}
	
	public List<TTweets> getUserTweets(String user) {
		tweets = userDao.getTweetsBySingleUser(user);
		
		return tweets;
	}
	

}
