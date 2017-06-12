package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("umangborad", "Umang Borad", "letmein", true, "ROLE_ADMIN", "umang@nowhere.com");
	private User user2 = new User("johnwpurcell", "John Purcell", "letmein", true, "ROLE_USER", "john@nowhere.com");
	private User user3 = new User("richardhanny", "Richard Hanny", "letmein", true, "ROLE_USER", "richard@nowhere.com");
	private User user4 = new User("sueblack", "Sue Black", "letmein", false, "user", "sue@nowhere.com");

	@Before        // This will run before every single test.
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		// jdbc.execute("delete from authorities");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);

		List<User> users1 = usersDao.getAllUsers();
		assertEquals("One user should have been created and retrieve", 1, users1.size());

		assertEquals("Inserted user should retrieved.", user1, users1.get(0));
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		List<User> users2 = usersDao.getAllUsers();
		assertEquals("Four users should have been created and retrieve", 4, users2.size());
	}

	@Test
	public void testExists() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("User should not exist.", usersDao.exists("sdfdsf"));
	}

}
