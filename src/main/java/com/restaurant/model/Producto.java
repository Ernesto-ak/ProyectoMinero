package com.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "restaurant", "categoria" })
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 45)
	private String nombre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurant restaurant;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	@Lob
	@Column(name = "logo", columnDefinition = "VARBINARY(MAX)")
	private byte[] logo;

	@Column(name = "precio_unitario", nullable = false)
	private Double precioUnitario;
}
