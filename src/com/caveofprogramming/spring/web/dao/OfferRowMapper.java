package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setAuthority(rs.getString("authority"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setEnabled(true);

		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);
		return offer;
	}

}
