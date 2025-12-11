package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.model.Categoria;
import com.restaurant.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired CategoriaRepository categoriaRepository;
	
	public List<Categoria> obtenerTodos(){
		return categoriaRepository.findAll();
	}
	public Optional<Categoria> obtenerPorId(Integer id){
		return categoriaRepository.findById(id);
	}
	public Categoria guardar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	public Categoria actualizar(Integer id,Categoria categoriaActualizado) {
		Optional<Categoria> optional =  categoriaRepository.findById(id);
		if(optional.isPresent()) {
			Categoria categoria = optional.get();
			categoria.setDescripcion(categoriaActualizado.getDescripcion());
			return categoriaRepository.save(categoria);
		}
		return null;
	}
	
	public void eliminar(Integer id) {
		categoriaRepository.deleteById(id);
	}
}
