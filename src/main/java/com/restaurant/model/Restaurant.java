package com.restaurant.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "productos" })
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "razon_social", nullable = false, length = 45)
	private String razonSocial;

	@Column(nullable = false, length = 45)
	private String email;
	@Column(nullable = false, length = 45)
	private String telefono;
	@Column(nullable = false, length = 45)
	private String direccion;
	@Column(name = "horario_apertura")
	private String horarioApertura;

	@Column(name = "horario_cierre")
	private String horarioCierre;

	@Lob
	@Column(name = "logo", columnDefinition = "VARBINARY(MAX)")
	private byte[] logo;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.MERGE, orphanRemoval = false)
	private List<Producto> productos;
}
