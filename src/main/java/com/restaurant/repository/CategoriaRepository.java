package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.model.Categoria;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	List<Categoria> findByDescripcion(String descripcion);

}
