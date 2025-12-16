package com.restaurant.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurant.model.Restaurant;
import com.restaurant.service.RestaurantService;

@Controller
@RequestMapping("/web/restaurants")
public class RestaurantWebController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public String obtenerTodos(@RequestParam(required = false) String nombre, Model model) {
		List<Restaurant> restaurantes;
		if (nombre != null && !nombre.isEmpty()) {
			restaurantes = restaurantService.buscarPorNombre(nombre);
		} else {
			restaurantes = restaurantService.obtenerTodos();
		}
		model.addAttribute("restaurants", restaurantes);
		// model.addAttribute("pedidos", );
		return "restaurants/listaRestaurant";
	}

	// 2. FORMULARIO NUEVO (GET /web/restaurants/nuevo)
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		// ⭐ MODIFICADO: Devolver la plantilla 'restaurants/formulario.html'
		return "restaurants/formulario";
	}

	// 3. FORMULARIO EDITAR (GET /web/restaurants/{id}/editar)
	@GetMapping("/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		Optional<Restaurant> optional = restaurantService.obtenerPorId(id);
		if (optional.isPresent()) {
			model.addAttribute("restaurant", optional.get());
			// ⭐ MODIFICADO: Devolver la plantilla 'restaurants/formulario.html'
			return "restaurants/formulario";
		}
		return "redirect:/web/restaurants";
	}

	// 4. GUARDAR / ACTUALIZAR (POST /web/restaurants)
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Restaurant restaurant, @RequestParam(required = false) MultipartFile logoFile,
			RedirectAttributes redirectAttributes) {

		// Si está editando
		if (restaurant.getId() != null) {
			Optional<Restaurant> opt = restaurantService.obtenerPorId(restaurant.getId());
			if (opt.isPresent()) {
				Restaurant original = opt.get();

				// Mantener logo anterior si no subieron uno nuevo
				if (logoFile.isEmpty()) {
					restaurant.setLogo(original.getLogo());
				} else {
					try {
						restaurant.setLogo(logoFile.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			// Nuevo registro
			if (!logoFile.isEmpty()) {
				try {
					restaurant.setLogo(logoFile.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		restaurantService.guardar(restaurant);
		redirectAttributes.addFlashAttribute("success", "Restaurant guardado exitosamente.");
		return "redirect:/web/restaurants";
	}

	// 5. ELIMINAR (GET /web/restaurants/{id}/eliminar)
	@GetMapping("/{id}/eliminar")
	public String eliminarRestaurant(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		restaurantService.eliminar(id);
		redirectAttributes.addFlashAttribute("success", "Restaurant eliminado exitosamente.");
		return "redirect:/web/restaurants";
	}

	// 6. SERVIR LOGO (GET /web/restaurants/logo/{id})
	@GetMapping("/logo/{id}")
	public ResponseEntity<byte[]> obtenerLogo(@PathVariable Integer id) {
		Optional<Restaurant> optionalRest = restaurantService.obtenerPorId(id);

		if (optionalRest.isPresent() && optionalRest.get().getLogo() != null) {
			byte[] logo = optionalRest.get().getLogo();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentLength(logo.length);

			return new ResponseEntity<>(logo, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}