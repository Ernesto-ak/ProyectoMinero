package com.unu.practicandoweb.beans;

public class Login {
	private String user;
	private String password;

	protected enum e {
		admin, usuario
	};

	private e rol;

	public Login(String user, String password, e rol) {
		super();
		this.user = user;
		this.password = password;
		this.rol = rol;
	}

	public Login() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public e getRol() {
		return rol;
	}

	public void setRol(e rol) {
		this.rol = rol;
	}

}
