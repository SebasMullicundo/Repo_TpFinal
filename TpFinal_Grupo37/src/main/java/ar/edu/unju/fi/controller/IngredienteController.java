package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.service.IIngredienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	private IIngredienteService ingredienteService;
	
	/**
	 * Muestra la lista de ingredientes.
	 * @param modelo utilizado para pasar la lista a la vista.
	 * @return vista "ingredientes" que muestra la lista de ingredientes.
	 */
	@GetMapping("/listado")
	public String mostrarIngredientes(Model model) {
		model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
	    return "ingredientes";
	}
	
	/**
	 * Muestra el formulario para crear un nuevo ingrediente.
	 * @param modelo utilizado para pasar el nuevo ingrediente a la vista.
	 * @return vista "nuevo_ingrediente" que muestra el formulario para crear un nuevo ingrediente.
	 */
	@GetMapping("/nuevo_ingrediente")
	public String getNuevoIngrediente(Model model) {
		boolean edicion = false;
		
		model.addAttribute("ingrediente", ingredienteService.nuevoIngrediente());
		model.addAttribute("edicion", edicion);
		
		return "nuevo_ingrediente";
	}
	
	/**
	 * Guarda un nuevo ingrediente.
	 * @param objeto de tipo Ingrediente a guardar.
	 * @param result objeto BindingResult que contiene los resultados de la validación.
	 * @return modelo y vista "ingredientes" que muestra la lista actualizada de los ingredientes.
	 */
	@PostMapping("/guardar_ingrediente")
	public ModelAndView getGuardarIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView("ingredientes");
		if(result.hasErrors()) {
			modelView.setViewName("nuevo_ingrediente");
			modelView.addObject("ingrediente", ingrediente);
			return modelView;
		}
		ingredienteService.guardarIngrediente(ingrediente);
		modelView.addObject("ingredientes",ingredienteService.getListaIngredientes());
		
		return modelView;
	}
	
	/**
	 * Muestra para modificar un ingrediente.
	 * @param modelo utilizado para pasar el ingrediente encontrado a la vista.
	 * @param id del ingrediente a modificar.
	 * @return vista "nuevo_ingredientea" que muestra el formulario para modificar un ingrediente.
	 */
	@GetMapping("/modificar/{id}")
	public String getModificarIngrediente(Model model, @PathVariable(value="id") Long id) {
		boolean edicion = true;
		
		model.addAttribute("ingrediente", ingredienteService.findIngredienteById(id));
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	/**
	 * Modifica un ingrediente existente.
	 * @param ingredienteModificado objeto de tipo Ingrediente con los datos modificados.
	 * @param result objeto BindingResult que contiene los resultados de la validación.
	 * @param model modelo utilizado para pasar datos a la vista.
	 * @return redirección a la lista de ingredientes.
	 */
	@PostMapping("/modificar_ingrediente")
	public String modificarIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingredienteModificado, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("ingrediente", ingredienteModificado);
			model.addAttribute("edicion", true);
			return "nuevo_ingrediente";
		}
		
		ingredienteService.modificarIngrediente(ingredienteModificado);
		
		return "redirect:/ingredientes/listado";
	}
	
	/**
	 * Elimina un ingrediente.
	 * @param id del ingrediente a eliminar.
	 * @return redirección a la lista de ingredientes.
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value="id") Long id) {
		
		ingredienteService.eliminarIngrediente(ingredienteService.findIngredienteById(id));
		
		return "redirect:/ingredientes/listado";
	}
}
