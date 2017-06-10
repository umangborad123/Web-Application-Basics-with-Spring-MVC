package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.caveofprogramming.spring.web.validation.ValidEmail;

public class Offer {

	private int id;

	/*
	 * @Size(min=5, max=100,
	 * message="Name must be between 5 and 100 characters.") private String
	 * name;
	 * 
	 * @NotNull //@Pattern(regexp=".*@.*\\..*", message =
	 * "This does not appear to be a valid email address.")
	 * 
	 * @ValidEmail(min=6) private String email;
	 */

	@Size(min = 5, max = 100, message = "Text must be between 5 and 100 characters.")
	private String text;

	private User user;

	public Offer() {
		this.user = new User();
	}

	public Offer(User user, String text) {
		this.text = text;
		this.user = user;
	}

	public Offer(int id, User user, String text) {
		this.id = id;
		this.user = user;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [text=" + text + ", user=" + user + "]";
	}

}
