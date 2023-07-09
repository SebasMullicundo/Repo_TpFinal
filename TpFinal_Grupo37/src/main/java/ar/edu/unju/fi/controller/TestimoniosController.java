package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;

/**
 * @author Chosco
 * @author Delgado
 * @author Mullicundo
 * @author Ordonez
 * @author Villalba
 * @version 17
 */

@Controller
@RequestMapping("/testimonios")
public class TestimoniosController {

	@Autowired
	private ITestimonioService testimonioService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private Usuario usuario;

	/**
	 * Solicitud GET para mostrar la página del ingreso de usuario
	 * 
	 * @param model utilizado para pasar los datos a la vista
	 * @return retorna la vista "ingreso_usuario"
	 */
	@GetMapping("/acceso")
	public String contraseñaAcesso(Model model) {
		model.addAttribute("testimonios", true);

		return "ingreso_usuario";
	}

	/**
	 * Solicitud POST para verificar la existencia del usuario Si existe redirige al
	 * registro del nuevo testimonio, Si no, el usuario no accede y muestra mensaje
	 * de error
	 * 
	 * @param model  utilizado para enviar los datos a la vista
	 * @param codigo utilizado para verificar la identidad del usuario
	 * @return si ingresa correctamente retorna la vista "testimonio_registro", si
	 *         no retorna la vista "ingreso_usuario"
	 */
	@PostMapping("/control")
	public String contraseñaControl(Model model, @RequestParam("codigo") String codigo) {
		boolean edicion = false;
		// Verifica si existe el usuario mediante el codigo ingresado
		if (usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			// Verifica el estado del usuario
			if (usuario.isEstado()) {
				usuario = usuarioService.obtenerUsuario(codigo);
				model.addAttribute("usuario", usuario);
				model.addAttribute("testimonio", testimonioService.nuevoTestimonio());
				model.addAttribute("estado", edicion);

				return "testimonio_registro";
			} else {
				model.addAttribute("testimonios", true);
				model.addAttribute("mensaje", true);

				return "ingreso_usuario";
			}

		} else {
			model.addAttribute("testimonios", true);
			model.addAttribute("mensaje", true);

			return "ingreso_usuario";
		}
	}

	/**
	 * Método que obtiene la lista de testimonios
	 * 
	 * @param model utilizado para pasar los datos a la vista
	 * @return retorna la vista "testimonios"
	 */

	@GetMapping("/listado")
	public String mostrarTestimonio(Model model) {
		model.addAttribute("testimonios", testimonioService.getListaTestimonios());
		model.addAttribute("guardar", true);

		return "testimonios";
	}

	/**
	 * Solicitud GET para mostrar la pagina de creacion de nuevo testimonio
	 * 
	 * @param model utilizado para pasar los datos a la vista
	 * @return retorna la vista "testimonio_registro"
	 */
	@GetMapping("/nuevo-testimonio")
	public String getRegistroTestimonioPage(Model model) {
		boolean edicion = false;

		model.addAttribute("testimonio", testimonioService.nuevoTestimonio());
		model.addAttribute("estado", edicion);

		return "testimonio_registro";
	}

	/**
	 * Solicitud POST para guardar un testimonio
	 * 
	 * @param testimonio contiene los datos a guardar
	 * @param codigo     contiene el codigo de usuario a guardar
	 * @return retorna un objeto ModelAndView que representa la vista "testimonios"
	 */
	@PostMapping("/guardar_testimonio")
	public ModelAndView getGuardarTestimonio(@ModelAttribute("testimonio") Testimonio testimonio,
			@RequestParam("codigo") String codigo) {
		ModelAndView modelView = new ModelAndView("testimonios");
		testimonioService.guardarTestimonio(testimonio, codigo);
		modelView.addObject("testimonios", testimonioService.getListaTestimonios());
		modelView.addObject("guardar", true);

		return modelView;
	}

	/**
	 * Solicitud GET para eliminar un testimonio
	 * 
	 * @param id identificador del testimonio a eliminar
	 * @return redirige a la pagina "testimonios"
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value = "id") Long id) {
		testimonioService.eliminarTestimonio(testimonioService.findTestimonioById(id));

		return "redirect:/testimonios/gestion-testimonio";
	}

	/**
	 * Solicitud GET para mostrar la pagina de testimonios desde el rol de
	 * administrador
	 * 
	 * @param model utilizado para pasar los datos a la vista
	 * @return retorna la pagina "testimonios"
	 */
	@GetMapping("/gestion-testimonio")
	public String getGestionTestimonios(Model model) {
		model.addAttribute("testimonios", testimonioService.getListaTestimonios());
		model.addAttribute("acciones", true);
		model.addAttribute("salir", true);

		return "testimonios";
	}

	/**
	 * Solicitud GET para acceder a la pagina como admnistrador
	 * 
	 * @param model utilizado para pasar los datos a la vista
	 * @return retorna la pagina "control"
	 */
	@GetMapping("/acceso_administrador")
	public String contraseñaAdministrador(Model model) {
		model.addAttribute("testimonios", true);

		return "control";
	}

	/**
	 * Solicitud POST para verificar el rol de administrador del usuario Si existe y
	 * es administrador redirige al la pagina "testimonios" como administrador Si
	 * no, el usuario no accede y muestra mensaje de error
	 * 
	 * @param model  utilizado para enviar los datos a la vista
	 * @param codigo utilizado para verificar si existe el usuario y si es
	 *               administrador
	 * @return si ingresa correctamente y el usuario es administrador retorna la
	 *         vista "testimonios", si no retorna la vista "control"
	 */
	@PostMapping("/administrador_control")
	public String administradorControl(Model model, @RequestParam("codigo") String codigo) {
		// Verifica si existe el usuario mediante el codigo ingresado
		if (usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			// Verifica el rol del usuario
			if (usuario.getRol()) {
				model.addAttribute("testimonios", testimonioService.getListaTestimonios());
				model.addAttribute("acciones", true);
				model.addAttribute("salir", true);

				return "testimonios";
			} else {
				model.addAttribute("testimonios", true);
				model.addAttribute("mensaje2", true);

				return "control";
			}
		} else {
			model.addAttribute("testimonios", true);
			model.addAttribute("mensaje1", true);

			return "control";
		}
	}
}
