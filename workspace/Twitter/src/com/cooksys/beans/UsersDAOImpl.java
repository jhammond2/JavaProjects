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

	// @Override
	// public void addUser(Users user) {
	// sessionFactory.getCurrentSession().save(user);
	//
	// }
	//
	// @Override
	// public void addUserData(UserData userData) {
	// sessionFactory.getCurrentSession().save(userData);
	//
	// }
	//
	// @Override
	// public void updateUser(Users user) {
	// sessionFactory.getCurrentSession().update(user);
	//
	// }
	//
	// @Override
	// public void deleteUser(Users user) {
	// sessionFactory.getCurrentSession().delete(user);
	//
	// }
	//
	// @Override
	// public Users getUserById(String userName) {
	// @SuppressWarnings("unchecked")
	// List<Users> list = getSessionFactory().getCurrentSession()
	// .createQuery("from Users where user_name=?")
	// .setParameter(0, userName).list();
	// return (Users) list.get(0);
	//
	// }
	//
	// @Override
	// public List<Users> getUsers() {
	//
	// List<Users> list = sessionFactory.getCurrentSession()
	// .createQuery("from Users").list();
	//
	// return list;
	// }
	//
	// @Override
	// public List<Person> getList() {
	//
	// List<Person> list = sessionFactory.getCurrentSession()
	// .createQuery("from Person").list();
	//
	// return list;
	// }
	//
	// @Override
	// public void addPerson(Person person) {
	// sessionFactory.getCurrentSession().save(person);
	//
	// }
	//
	// @Override
	// public void updatePerson(Person selectedPerson, String fName, String
	// lName) {
	// Session session = sessionFactory.getCurrentSession();
	// Person person = (Person) session.get(Person.class,
	// selectedPerson.getPersonId());
	// person.setFirstName(fName);
	// person.setLastName(lName);
	// session.update(person);
	//
	// }
	//
	// @Override
	// public void deletePerson(Person person) {
	// Session session = sessionFactory.getCurrentSession();
	//
	// if (person != null) {
	//
	// session.delete(person);
	// } else {
	// System.out.println("Selected value is null");
	// }
	//
	// }

	@Override
	public void addUser(TUsers user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public void addUserData(TTweets userData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(TUsers user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(TUsers user) {
		// TODO Auto-generated method stub

	}

	@Override
	public TUsers getUserById(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTweet(TTweets tweet) {
		sessionFactory.getCurrentSession().save(tweet);

	}

	@Override
	public void updateTweet(TUsers user, String tweet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTweet(TUsers user) {
		// TODO Auto-generated method stub

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
		List<TTweets> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from TTweets where user_nick='" + user
								+ "'order by time_stamp DESC").list();
		return list;
	}

	@Override
	public List<TTweets> getTweetsByAllFollowing(String user) {
		
		List<TTweets> list = sessionFactory.getCurrentSession().createQuery("from TFollowing inner join TTweets where TFollowing.userName='" + user.trim() + "' and TFollowing.userFollowing = TTweets.userNick").list();
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
