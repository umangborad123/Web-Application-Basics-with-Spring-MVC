package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/caveofprogramming/spring/web/test/config/datasource.xml",
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("umangborad", "Umang Borad", "letmein", true, "ROLE_ADMIN", "umang@nowhere.com");
	private User user2 = new User("johnwpurcell", "John Purcell", "letmein", true, "ROLE_USER", "john@nowhere.com");
	private User user3 = new User("richardhanny", "Richard Hanny", "letmein", true, "ROLE_USER", "richard@nowhere.com");
	private User user4 = new User("sueblack", "Sue Black", "letmein", false, "user", "sue@nowhere.com");
	
	private Offer offer1 = new Offer(user1, "This is a test offer.");
	private Offer offer2 = new Offer(user1, "This is another test offer.");
	private Offer offer3 = new Offer(user2, "This is yet another test offer.");
	private Offer offer4 = new Offer(user3, "This is a test offer once again.");
	private Offer offer5 = new Offer(user3, "Here is an interesting offer of some kind.");
	private Offer offer6 = new Offer(user3, "This is just a test offer.");
	private Offer offer7 = new Offer(user4, "This is a test offer for a user that is not enabled.");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

		// jdbc.execute("delete from authorities");
	}
	
	public void createUsers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}
	
	public void createOffers() {
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
	}
	
	@Test
	public void testDelete() {
		createUsers();
		createOffers();
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		assertNotNull("Offer with id " + offer2.getId() + " should not be null.", retrieved1);
		
		offersDao.delete(offer2.getId());
		
		Offer retrieved2 = offersDao.getOffer(offer2.getId());
		assertNull("Offer with id " + offer2.getId() + " should be null.", retrieved2);
	}
	
	@Test
	public void testGetById() {
		createUsers();
		createOffers();
		
		Offer retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("Offers should match.", offer1, retrieved1);
		
		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Should not retrieve offer for disabled user.", retrieved2);
	}
	
	@Test
	public void testCreateRetrieve() {
		
		createUsers();
		offersDao.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be one offer.", 1, offers1.size());
		assertEquals("Retrieved offer should equal inserted offer.", offer1, offers1.get(0));
		
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be six offers for enabled users.", 6, offers2.size());
	}
	
	@Test
	public void testUpdate() {
		createUsers();
		createOffers();
		
		offer3.setText("This offer has updated text.");
		offersDao.saveOrUpdate(offer3);
		
		Offer retrieved = offersDao.getOffer(offer3.getId());
		assertEquals("Retrieved offer should be updated!", offer3, retrieved);
		System.out.println(retrieved.getText());
		
	}
	
	@Test
	public void testGetUsername() {
		createUsers();
		createOffers();
		
		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be three offers for this user.", 3, offers1.size());
		
		List<Offer> offers2 = offersDao.getOffers("dfdfsd");
		assertEquals("Should be zero offer for this user.", 0, offers2.size());
		
		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be one offer for this user.", 1, offers3.size());
	}

}
