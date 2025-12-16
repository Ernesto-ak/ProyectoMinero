package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.DetallePedidos;
import com.restaurant.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService {
	
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	
	public void guardar(DetallePedidos detallePedidos) {
		detallePedidoRepository.save(detallePedidos);
	}
}
