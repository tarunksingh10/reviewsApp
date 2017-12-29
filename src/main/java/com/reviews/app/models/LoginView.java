package com.reviews.app.models;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "loginView", eager = true)
@RequestScoped
public class LoginView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";
	private String username;
	private String password;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateUsernamePassword() {

		if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
			message = "Logging In..";
			return "datatable";
		} else {
			message = "Wrong credentials.";
			return "login";
		}

	}

	public String onTextChange() {
		message = "";
		return null;
	}
}
