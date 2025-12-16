package com.unu.practicandoweb.modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.unu.practicandoweb.beans.Restaurante;
import com.unu.practicandoweb.util.conexion;

public class restauranteModelo extends conexion {

	CallableStatement cs;
	ResultSet rs;

	public List<Restaurante> listaRestaurante() {
		List<Restaurante> lista = new ArrayList<Restaurante>();
		try {
			String sql = "CALL sp_listarRestaurante()";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			rs = cs.executeQuery();
			Restaurante restaurante;
			while (rs.next()) {
				restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setRazonSocial(rs.getString("razonSocial"));
				restaurante.setRUC(rs.getString("RUC"));
				restaurante.setSerie(rs.getString("serie"));
				lista.add(restaurante);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			e.printStackTrace();
		}
		return null;
	}

	public int insertarRestaurante(Restaurante restaurante) {

		try {
			String sql = "CALL sp_insertarRestaurante(?,?,?)";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			cs.setString(1, restaurante.getRazonSocial());
			cs.setString(2, restaurante.getRUC());
			cs.setString(3, restaurante.getSerie());
			int filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			this.cerrarConexion();
			e.printStackTrace();
		}
		return 0;
	}

	public int eliminarRestaurante(int idRest) {
		try {
			String sql = "CALL sp_eliminarRestaurante(?)";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			cs.setInt(1, idRest);
			int filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			e.printStackTrace();
		}
		return 0;
	}

	public Restaurante obtenerRestaurante(int idRest) {
		Restaurante restaurante = new Restaurante();
		try {
			String sql = "CALL sp_obtenerRestaurante(?)";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			cs.setInt(1, idRest);
			rs = cs.executeQuery();
			while (rs.next()) {
				restaurante.setIdRestaurante(idRest);
				restaurante.setRazonSocial(rs.getString("razonSocial"));
				restaurante.setRUC(rs.getString("RUC"));
				restaurante.setSerie(rs.getString("serie"));
			}
			this.cerrarConexion();
			return restaurante;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			e.printStackTrace();
		}
		return null;
	}

	public int modificarRestaurante(Restaurante restaurante) {
		try {
			String sql = "CALL sp_modificarRestaurante(?,?,?,?)";
			this.abrirConexion();
			cs = con.prepareCall(sql);
			cs.setInt(1, restaurante.getIdRestaurante());
			cs.setString(2, restaurante.getRazonSocial());
			cs.setString(3, restaurante.getRUC());
			cs.setString(4, restaurante.getSerie());
			int filasAfectas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectas;

		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			e.printStackTrace();
		}
		return 0;
	}
}
