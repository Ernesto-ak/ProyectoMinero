package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Pedidos;
import com.restaurant.model.Usuario;
import com.restaurant.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedidos> obtenerTodo() {
		return pedidoRepository.findAll();
	}

	public List<Pedidos> buscarPorUsuario(String usuario) {
		return pedidoRepository.findByUsuario(usuario);
	}

	public Optional<Pedidos> buscarPorId(Integer Id) {
		return pedidoRepository.findById(Id);
	}

	public Pedidos guardar(Pedidos pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedidos actualizar(Integer id, Pedidos pedidoActualizado) {
		Optional<Pedidos> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			Pedidos pedido = optional.get();
			pedido.setTotal(pedidoActualizado.getTotal());
			return pedidoRepository.save(pedido);
		}
		return null;
	}

	public void eliminar(Integer Id) {
		pedidoRepository.deleteById(Id);
	}

	public Pedidos buscarPorUsuarioEstado(Usuario usuario, String estado) {
		return pedidoRepository.findByUsuarioAndEstado(usuario, estado);
	}
}
