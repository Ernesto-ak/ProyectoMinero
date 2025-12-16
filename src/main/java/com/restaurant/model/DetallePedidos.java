package com.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer cantidad;
	private Double subtotal;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedidos pedido;
}
