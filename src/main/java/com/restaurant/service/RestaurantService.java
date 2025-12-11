package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired RestaurantRepository restaurantRepository;
	
	public List<Restaurant> obtenerTodos(){
		return restaurantRepository.findAll();
	}
	
	public Optional<Restaurant> obtenerPorId(Integer id){
		return restaurantRepository.findById(id);
	}
	
	public Restaurant guardar(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	
	public Restaurant actualizar(Integer id,Restaurant restaurantActualizado) {
		Optional<Restaurant> optional =  restaurantRepository.findById(id);
		if(optional.isPresent()) {
			Restaurant restaurant  = optional.get();
			restaurant.setRazonSocial(restaurantActualizado.getRazonSocial());
			restaurant.setEmail(restaurantActualizado.getEmail());
			restaurant.setHorarioApertura(restaurantActualizado.getHorarioApertura());
			restaurant.setHorarioCierre(restaurantActualizado.getHorarioCierre());
			restaurant.setLogo(restaurantActualizado.getLogo());
			return restaurantRepository.save(restaurant);
		}
		return null;
	}
	
	public void eliminar(Integer id) {
		restaurantRepository.deleteById(id);
	}
	
	public List<Restaurant> buscarPorNombre(String nombre) {
		return restaurantRepository.findByRazonSocialContainingIgnoreCase(nombre);
	}
	

}
