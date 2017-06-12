package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository // To convert hibernate exception to org.springframework exception
@Component("offersDao")
public class OffersDao {

	//private NamedParameterJdbcTemplate jdbc;  // Your service is no longer needed :(
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/*@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}*/

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return crit.list();

		/*return jdbc.query(
				"select * from users u inner join offers o on u.username = o.username where u.enabled = true",
				new OfferRowMapper());*/
	}
	
	@SuppressWarnings("unchecked")
	public List<Offer> getOffersAdmin() {
		
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u", JoinType.RIGHT_OUTER_JOIN);		
		return crit.list();

		/*MapSqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc.query(
				"select * from users u left join offers o on u.username = o.username",
				new OfferRowMapper());*/
	}
	
	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {
		
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));
		return crit.list();
		
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);

		return jdbc.query(
				"select * from offers, users where offers.username = users.username and users.enabled = true and users.username = :username",
				params, new OfferRowMapper());*/
	}

	/*public void update(Offer offer) {
		
		session().update(offer);
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text = :text where id = :id ", params) == 1;
	}*/

	@Transactional
	public void saveOrUpdate(Offer offer) {
		
		session().saveOrUpdate(offer);
		
		/*BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("insert into offers (username, text) values (:username, :text)", params) == 1;*/
	}
	

	/*@Transactional
	public int[] create(List<Offer> offers) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", params);

		// return jdbc.batchUpdate("insert into offers (name, email, text)
		// values (:name, :email, :text) ", params);
	}*/

	public boolean delete(int id) {
		
		Query query =  session().createQuery("delete from Offer where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
		
		/*MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id = :id", params) == 1;*/
	}

	public Offer getOffer(int id) {
		
		Criteria crit = session().createCriteria(Offer.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		return (Offer) crit.uniqueResult();
		
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject(
				"select * from offers, users where offers.username = users.username and users.enabled = true and id = :id",
				params, new OfferRowMapper());*/
	}

}
