package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.*;

@Service("offersService")
public class OffersService {

	@Autowired
	private OffersDao offersDao;

	/*
	 * public void setOffersDao(OffersDAO offersDao) { this.offersDao =
	 * offersDao; }
	 */

	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	
	public List<Offer> getCurrentAdmin() {
		return offersDao.getOffersAdmin();
	}
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(Offer offer) {
		offersDao.saveOrUpdate(offer);
	}

	public boolean hasOffer(String username) {

		if (username == null)
			return false;

		List<Offer> offers = offersDao.getOffers(username);

		if (offers.size() == 0)
			return false;

		return true;
	}

	public Offer getOffer(String username) {

		if (username == null)
			return null;

		List<Offer> offers = offersDao.getOffers(username);
		if (offers.size() == 0)
			return null;

		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {
		
		offersDao.saveOrUpdate(offer);
		
		/*if (offer.getId() != 0) {
			offersDao.update(offer);
		} else {
			offersDao.saveOrUpdate(offer);
		}*/
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		offersDao.delete(id);
	}

}
