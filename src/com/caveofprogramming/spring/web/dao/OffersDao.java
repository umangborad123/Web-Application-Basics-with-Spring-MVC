package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // To convert hibernate exception to org.springframework exception
@Component("offersDao")
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {

		MapSqlParameterSource params = new MapSqlParameterSource();
		// params.addValue("name", "Sue");

		// select * from offers where name = :name
		return jdbc.query(
				"select * from users u inner join offers o on u.username = o.username",
				new OfferRowMapper());
	}
	
	public List<Offer> getOffersAdmin() {

		MapSqlParameterSource params = new MapSqlParameterSource();
		// params.addValue("name", "Sue");

		// select * from offers where name = :name
		return jdbc.query(
				"select * from users u left join offers o on u.username = o.username",
				new OfferRowMapper());
	}

	public List<Offer> getOffers(String username) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);

		return jdbc.query(
				"select * from offers, users where offers.username = users.username and users.enabled = true and users.username = :username",
				params, new OfferRowMapper());
	}

	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text = :text where id = :id ", params) == 1;
	}

	public boolean create(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("insert into offers (username, text) values (:username, :text)", params) == 1;
	}

	@Transactional
	public int[] create(List<Offer> offers) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", params);

		// return jdbc.batchUpdate("insert into offers (name, email, text)
		// values (:name, :email, :text) ", params);
	}

	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id = :id", params) == 1;
	}

	// For returning single row

	public Offer getOffer(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject(
				"select * from offers, users where offers.username = users.username and users.enabled = true and id = :id",
				params, new OfferRowMapper());
	}

}
