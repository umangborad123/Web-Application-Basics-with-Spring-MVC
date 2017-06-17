package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.MessagesDao;
import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	/*
	 * private User user1 = new User("umangborad", "Umang Borad", "letmein",
	 * true, "ROLE_ADMIN", "umang@nowhere.com"); private User user2 = new
	 * User("johnwpurcell", "John Purcell", "letmein", true, "ROLE_USER",
	 * "john@nowhere.com");
	 */

	private User user1 = new User("johnwpurcell", "John Purcell", "letmein", true, "ROLE_USER",
			"john@caveofprogramming.com");
	private User user2 = new User("richardhannay", "Richard Hannay", "the39steps", true, "ROLE_ADMIN",
			"richard@caveofprogramming.com");

	private Message message1 = new Message("Test Subject 1", "Test content 1", "Isaac Newton",
			"isaac@caveofprogramming.com", user1.getUsername());
	private Message message2 = new Message("Test Subject 2", "Test content 2", "Isaac Newton",
			"isaac@caveofprogramming.com", user1.getUsername());
	private Message message3 = new Message("Test Subject 3", "Test content 3", "Isaac Newton",
			"isaac@caveofprogramming.com", user2.getUsername());

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSaveRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);

		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);

		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		assertEquals(2, messages.size());

		messages = messagesDao.getMessages(user2.getUsername());
		assertEquals(1, messages.size());
	}

	@Test
	public void testRetrieveById() {
		usersDao.create(user1);
		usersDao.create(user2);

		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);

		List<Message> messages = messagesDao.getMessages(user1.getUsername());

		for (Message message : messages) {
			Message retrieved = messagesDao.getMessages(message.getId());
			assertEquals(message, retrieved);
		}
	}

	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);

		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);

		List<Message> messages = messagesDao.getMessages(user1.getUsername());

		for (Message message : messages) {
			Message retrieved = messagesDao.getMessages(message.getId());
			assertEquals("Messages should be same.", message, retrieved);
		}

		Message toDelete = messages.get(1);

		assertNotNull("This message not deleted yet.", messagesDao.getMessages(toDelete.getId()));

		messagesDao.delete(toDelete.getId());

		assertNull("This message was deleted.", messagesDao.getMessages(toDelete.getId()));
	}

}
