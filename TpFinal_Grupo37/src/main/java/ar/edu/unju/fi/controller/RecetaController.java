package ar.edu.unju.fi.controller;



import java.util.List;

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

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

	@Autowired
	private IRecetaService recetaService;
	
	@Autowired
	private IIngredienteService ingredienteService;
	
	@GetMapping("/vista")
	public String listaRecetas(Model model) {
		model.addAttribute("recetas", recetaService.getListaRecetas());
		return "receta";
	}
	
	@GetMapping("/listado")
	public String mostrarRecetas(Model model) {
		model.addAttribute("recetas", recetaService.getListaRecetas());
	    return "recetas";
	}
	
	@GetMapping("/nueva_receta")
	public String getNuevaReceta(Model model) {
	    boolean edicion = false;	    
	    
	    model.addAttribute("receta", recetaService.nuevaReceta());
	    model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
	    model.addAttribute("edicion", edicion);
	    
	    return "nueva_receta";
	}
	
	@PostMapping("/guardar_receta")
	public ModelAndView getGuardarReceta(@Valid @ModelAttribute("receta") Receta receta, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView("recetas");
		if(result.hasErrors()) {
			modelView.setViewName("nueva_receta");
			modelView.addObject("receta", receta);
			modelView.addObject("ingredientes", ingredienteService.getListaIngredientes());
			return modelView;
		}
		
		recetaService.guardarReceta(receta);
		modelView.addObject("ingredientes",ingredienteService.getListaIngredientes());
		
		return modelView;
	}
	
	
	@GetMapping("/modificar/{id}")
	public String getModificarRecetaPage(Model model, @PathVariable(value="id") Long id) {

		boolean edicion=true;
		model.addAttribute("receta", recetaService.findRecetaById(id));
		model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}

	
	@PostMapping("/modificar_receta")
	public String modificarReceta(@Valid @ModelAttribute("receta") Receta receta,BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("receta", receta);
			model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
			model.addAttribute("edicion", true);
			return "nueva_receta";
		}
		System.out.println("id de la receta: "+receta.getId());
		recetaService.modificarReceta(receta);
		return "redirect:/recetas/listado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarReceta(@PathVariable(value="id")Long id) {
		recetaService.eliminarReceta(recetaService.findRecetaById(id));
		return "redirect:/recetas/listado";
	}
	
	@GetMapping("/detalle/{id}")
	public String geDetalleRecetaPage(Model model, @PathVariable(value="id") Long id) {

		Receta receta = recetaService.nuevaReceta();
		receta = recetaService.findRecetaById(id);
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", receta.getIngredientes());
		return "receta_detalle";
	}
	
	@GetMapping("/filtro/{categoria}")
	public String getfiltoServicioCorte(Model model, @PathVariable(value="categoria")String categoria) {
		
		//obtiene las recetas de acuerdo a la categoria correspondiente
		model.addAttribute("recetas", recetaService.filtroRecetaCategoria(categoria));

		return "receta";
	}
	@GetMapping("/categoria/{categoria}")
	public String getRecetasPorCategoria(Model model, @PathVariable String categoria) {
	    // Obtener la lista de recetas por categoría
	    List<Receta> recetas = recetaService.filtroRecetaCategoria(categoria);
	    model.addAttribute("recetas", recetas);
	    model.addAttribute("categoria", categoria);
	    return "recetas_por_categoria";
	}

}
