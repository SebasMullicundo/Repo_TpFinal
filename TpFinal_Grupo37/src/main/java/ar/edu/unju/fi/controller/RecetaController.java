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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

/**
 @author Chosco
 @author Delgado
 @author Mullicundo
 @author Ordonez
 @author Villalba
 @version 17
 */

@Controller
@RequestMapping("/recetas")
public class RecetaController {

	@Autowired
	private IRecetaService recetaService;
	
	@Autowired
	private IIngredienteService ingredienteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Usuario usuario;

	/**
	 * Muestra la lista de recetas.
	 * @param model el modelo utilizado para pasar atributos a la vista
	 * @return la vista "control"
	 */
	@GetMapping("/acceso")
	public String contraseñaAcesso(Model model) {
		model.addAttribute("recetas", true);
		return "control";
	}

	/**
	 * Verificar si el usuario existe y obtenerlo
	 *  Si el usuario tiene el rol correcto, redirigir a la lista de recetas,sino
	 *  el usuario no tiene el rol correcto, mostrar mensaje de error
	 *  @param model utilizado para pasar la lista a la vista.
	 *  @return String.
	 */
	@PostMapping("/control")
	public String contraseñaControl(Model model, @RequestParam("codigo") String codigo) {

		if(usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			if(usuario.getRol()) {
				return "redirect:/recetas/listado";
			}
			else {
				model.addAttribute("recetas", true);
				model.addAttribute("mensaje2", true);
				return "control";
			}
		} else {
			model.addAttribute("recetas", true);
			model.addAttribute("mensaje1", true);
			return "control";
		}
	}

	/**
	 * Muestra la vista de categorías de recetas.
	 * @param model el modelo utilizado para pasar atributos a la vista
	 * @return la vista "categorias_recetas" que muestra la lista de categorías de recetas
	 */
	@GetMapping("/categoria")
	public String vistaCategorias(Model model) {
		model.addAttribute("categorias", recetaService.getCategoriasUnicas(recetaService.getListaRecetas()));
		return "categorias_recetas";
	}

	/**
	 * Muestra la lista de recetas.
	 * @param model utilizado para pasar la lista a la vista.
	 * @return vista "recetas" que muestra la lista de recetas.
	 */
	@GetMapping("/vista")
	public String listaRecetas(Model model) {
		model.addAttribute("recetas", recetaService.getListaRecetas());
		return "receta";
	}

	/**
	 * Muestra la lista de recetas.
	 * @param model utilizado para pasar la lista a la vista.
	 * @return vista "recetas" que muestra la lista de recetas.
	 */
	@GetMapping("/listado")
	public String mostrarRecetas(Model model) {
		model.addAttribute("recetas", recetaService.getListaRecetas());
	    return "recetas";
	}

	/**
	 * Muestra el formulario para crear una nuevo receta.
	 * @param model utilizado para pasar la nuevo receta a la vista.
	 * @return vista "nuevo_receta" que muestra el formulario para crear una nuevo receta.
	 */
	@GetMapping("/nueva_receta")
	public String getNuevaReceta(Model model) {
	    boolean edicion = false;	    
	    
	    model.addAttribute("receta", recetaService.nuevaReceta());
	    model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
	    model.addAttribute("edicion", edicion);
	    
	    return "nueva_receta";
	}

	/**
	 * Guarda una nuevo receta.
	 * @param objeto de tipo receta a guardar.
	 * @param result objeto BindingResult que contiene los resultados de la validación.
	 * @return modelo y vista "recetas" que muestra la lista actualizada de las recetas.
	 */
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
		modelView.addObject("recetas", recetaService.getListaRecetas());
		return modelView;
	}

	/**
	 * Muestra el formulario para modificar una receta existente.
	 * @param model utilizado para pasar la receta a modificar y los ingredientes a la vista.
	 * @param id  de la receta a modificar.
	 * @return vista "nueva_receta" que muestra el formulario para modificar una receta existente.
	 */
	
	@GetMapping("/modificar/{id}")
	public String getModificarRecetaPage(Model model, @PathVariable(value="id") Long id) {

		boolean edicion=true;
		model.addAttribute("receta", recetaService.findRecetaById(id));
		model.addAttribute("ingredientes", ingredienteService.getListaIngredientes());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}

	/**
	 * Modifica una receta existente.
	 * @param receta objeto de tipo Receta a modificar.
	 * @param result objeto BindingResult que contiene los resultados de la validación.
	 * @param model utilizado para pasar la receta y los ingredientes a la vista.
	 * @return redirección a la lista de recetas si la modificación es exitosa.
	 */
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

	/**
	 * Elimina una receta.
	 * @param id  de la receta a eliminar.
	 * @return redirección a la lista de recetas.
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarReceta(@PathVariable(value="id")Long id) {
		recetaService.eliminarReceta(recetaService.findRecetaById(id));
		return "redirect:/recetas/listado";
	}

	/**
	 * Muestra los detalles de una receta.
	 * @param model utilizado para pasar la receta y los ingredientes a la vista.
	 * @param id  de la receta a mostrar los detalles.
	 * @return vista "receta_detalle" que muestra los detalles de la receta.
	 */
	@GetMapping("/detalle/{id}")
	public String geDetalleRecetaPage(Model model, @PathVariable(value="id") Long id) {

		Receta receta = recetaService.nuevaReceta();
		receta = recetaService.findRecetaById(id);
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", receta.getIngredientes());
		return "receta_detalle";
	}

	/**
	 * Filtra las recetas por categoría.
	 * @param model utilizado para pasar las recetas filtradas a la vista.
	 * @param categoria Categoría por la cual se van a filtrar las recetas.
	 * @return vista "receta" que muestra las recetas filtradas.
	 */
	@GetMapping("/filtro/{categoria}")
	public String getfiltoServicioCorte(Model model, @PathVariable(value="categoria")String categoria) {
		
		//obtiene las recetas de acuerdo a la categoria correspondiente
		model.addAttribute("recetas", recetaService.filtroRecetaCategoria(categoria));

		return "receta";
	}

	/**
	 * Muestra las recetas por categoría.
	 * @param model utilizado para pasar las recetas y la categoría a la vista.
	 * @param categoria Categoría por la cual se van a filtrar las recetas.
	 * @return vista "recetas_por_categoria" que muestra las recetas por categoría.
	 */
	@GetMapping("/categoria/{categoria}")
	public String getRecetasPorCategoria(Model model, @PathVariable String categoria) {
	    // Obtener la lista de recetas por categoría
	    List<Receta> recetas = recetaService.filtroRecetaCategoria(categoria);
	    model.addAttribute("recetas", recetas);
	    model.addAttribute("categoria", categoria);
	    return "recetas_por_categoria";
	}

}
