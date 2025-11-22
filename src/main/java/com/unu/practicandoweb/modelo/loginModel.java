package com.unu.practicandoweb.modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import com.unu.practicandoweb.util.conexion;

public class loginModel extends conexion {
	CallableStatement cs;
	ResultSet rs;

	public void Autenticar(String usuario, String contraseña) {
		try {
			String sql = "CALL sp_autenticar(?,?)";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			cs.setString(1, usuario);
			cs.setString(2, contraseña);
			this.cerrarConexion();
		} catch (Exception e) {
			e.printStackTrace();
			this.cerrarConexion();
		}
	}
}
