package com.restaurant.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre_usuario", nullable = false)
	private String nombreUsuario;
	@Column(name = "contraseña", nullable = false)
	private String contraseña;

	@Column(name = "tipo_usuario", nullable = false)
	private String tipoUsuario;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Pedidos> pedidos;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private PersonaNatural natural;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private PersonaJuridica juridica;
}
