package com.cooksys.beans;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooksys.models.TFollowing;
import com.cooksys.models.TTweets;
import com.cooksys.models.TUsers;

@Component
@Transactional
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private List<TTweets> multiResult;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(TUsers user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public void updateUser(TUsers user) {
		// method for further functionality at a future date

	}

	@Override
	public void deleteUser(TUsers user) {
		// method for further functionality at a future date

	}

	@Override
	public TUsers getUserById(String userName) {
		// method for further functionality at a future date
		return null;
	}

	@Override
	public void addTweet(TTweets tweet) {
		sessionFactory.getCurrentSession().save(tweet);

	}

	@Override
	public void updateTweet(TUsers user, String tweet) {
		// method for further functionality at a future date

	}

	@Override
	public void deleteTweet(TUsers user) {
		// method for further functionality at a future date

	}

	@Override
	public List<TUsers> getUsers() {
		List<TUsers> list = sessionFactory.getCurrentSession()
				.createQuery("from TUsers").list();

		return list;
	}

	@Override
	public List<TTweets> getList() {
		List<TTweets> list = sessionFactory.getCurrentSession()
				.createQuery("from TTweets order by time_stamp DESC").list();
		return list;
	}

	@Override
	public List<TTweets> getTweetsBySingleUser(String user) {
		List<TTweets> list = sessionFactory.getCurrentSession()
				.createQuery("from TTweets where user_nick='" + user
								+ "'order by time_stamp DESC").list();
		return list;
	}


	@Override
	public void follow(TFollowing follow) {
		sessionFactory.getCurrentSession().save(follow);

	}

	@Override
	public void unfollow(TFollowing follow) {
		sessionFactory.getCurrentSession().delete(follow);

	}

	@Override
	public List<TFollowing> getFollowerList(String user) {
		List<TFollowing> list = sessionFactory.getCurrentSession()
				.createQuery("from TFollowing where user_name='" + user + "'")
				.list();
		return list;
	}

}
