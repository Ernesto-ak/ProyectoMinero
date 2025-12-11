package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Producto;
import com.restaurant.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired ProductoRepository productoRepository;
	
	public List<Producto> obtenerTodos(){
		return productoRepository.findAll();
	}
	
	public List<Producto> buscarPorRestaurant(Integer resturantId){
		return productoRepository.findByRestaurantId(resturantId);
	}
	
	public List<Producto> buscarPorCategoria(Integer categoria){
		return productoRepository.findByCategoriaId(categoria);
	}
	
	public Optional<Producto> obtenerPorId(Integer id) {
		return productoRepository.findById(id);
	}
	
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto actualizar(Integer id,Producto productoActualizado) {
		Optional<Producto> optional =  productoRepository.findById(id);
		if(optional.isPresent()) {
			Producto producto = optional.get();
			producto.setNombre(productoActualizado.getNombre());
			producto.setRestaurant(productoActualizado.getRestaurant());
			producto.setCategoria(productoActualizado.getCategoria());
			producto.setLogo(productoActualizado.getLogo());
			producto.setPrecioUnitario(productoActualizado.getPrecioUnitario());
			return productoRepository.save(producto);
		}
		return null;
	}
	
	public void eliminar(Integer id) {
		productoRepository.deleteById(id);
	}
	
	
}
