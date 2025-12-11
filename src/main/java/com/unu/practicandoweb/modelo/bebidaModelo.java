package com.unu.practicandoweb.modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.unu.practicandoweb.beans.Bebida;
import com.unu.practicandoweb.util.conexion;

public class bebidaModelo extends conexion {
	CallableStatement cs;
	ResultSet rs;
	String listar = "CALL sp_listarBebidas()";

	public List<Bebida> listarBebidas() {
		List<Bebida> listaBebida = new ArrayList<Bebida>();
		try {
			this.abrirConexion();
			cs = con.prepareCall(listar);
			rs = cs.executeQuery();
			while (rs.next()) {
				Bebida bebida = new Bebida();
				bebida.setIdBebida(rs.getInt("idBebida"));
				bebida.setNombreBebida(rs.getString("nombreBebida"));
				bebida.setCantidadBebida(rs.getInt("cantidad"));
				bebida.setPrecioBebida(rs.getDouble("precio"));
				listaBebida.add(bebida);
			}
			this.cerrarConexion();
			return listaBebida;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
