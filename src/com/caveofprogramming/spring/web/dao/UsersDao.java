package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // To convert hibernate exception to org.springframework exception
@Transactional
@Component("usersDao")
public class UsersDao {

	//private NamedParameterJdbcTemplate jdbc; // Your service no longer needed :(

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * @Autowired // Now, there is no need of setting dataSource because of
	 * Hibernate. 
	 * public void setDataSource(DataSource jdbc) { 
	 * 		this.jdbc = new NamedParameterJdbcTemplate(jdbc); }
	 */
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);

		/*
		 * BeanPropertySqlParameterSource params = new
		 * BeanPropertySqlParameterSource(user);
		 */

		/*
		 * MapSqlParameterSource params = new MapSqlParameterSource();
		 * 
		 * params.addValue("username", user.getUsername());
		 * params.addValue("password",
		 * passwordEncoder.encode(user.getPassword())); params.addValue("email",
		 * user.getEmail()); params.addValue("name", user.getName());
		 * params.addValue("enabled", user.isEnabled());
		 * params.addValue("authority", user.getAuthority());
		 * 
		 * return jdbc.update(
		 * "insert into users (username, password, name, email, enabled, authority) values (:username, :password, :name, :email, :enabled, :authority) "
		 * , params) == 1;
		 */

	}

	public boolean exists(String username) {

		Criteria crit = session().createCriteria(User.class);

		crit.add(Restrictions.idEq(username)); // For primary key comparison.
		// crit.add(Restrictions.eq("username", username));

		User user = (User) crit.uniqueResult();

		return user != null;

		/*
		 * return jdbc.
		 * queryForObject("select count(*) from users where username=:username",
		 * new MapSqlParameterSource("username", username), Integer.class) == 1;
		 */
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		return session().createQuery("from User").list();
		// return jdbc.query("select * from users",
		// BeanPropertyRowMapper.newInstance(User.class));
	}

}
