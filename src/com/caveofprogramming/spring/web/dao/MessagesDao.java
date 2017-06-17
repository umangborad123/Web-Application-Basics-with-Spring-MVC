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
@Component("messagesDao")
public class MessagesDao {

	// private NamedParameterJdbcTemplate jdbc; // Your service is no longer
	// needed :(

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * @Autowired public void setDataSource(DataSource jdbc) { this.jdbc = new
	 * NamedParameterJdbcTemplate(jdbc); }
	 */

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria crit = session().createCriteria(Message.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.eq("username", username));
		return crit.list();
	}

	@Transactional
	public void saveOrUpdate(Message messages) {
		System.out.println(messages);
		session().saveOrUpdate(messages);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Message where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Message getMessages(int id) {
		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.idEq(id));
		return (Message) crit.uniqueResult();
	}

}
