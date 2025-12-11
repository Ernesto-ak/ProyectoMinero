package com.unu.practicandoweb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
	protected Connection con;
	String user = "root";
	String pass = "123456";
	String url = "jdbc:mysql://localhost:3306/bdrestaurante";

	public void abrirConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
