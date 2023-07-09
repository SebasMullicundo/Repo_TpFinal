package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/testimonios")
public class TestimoniosController {
	
	@Autowired
	private ITestimonioService testimonioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Usuario usuario;
	
	
	@GetMapping("/acceso")
	public String contraseñaAcesso(Model model) {
		model.addAttribute("testimonios", true);
		return "ingreso_usuario";
	}
	
	@PostMapping("/control")
	public String contraseñaControl(Model model, @RequestParam("codigo") String codigo) {
		boolean edicion = false;
		
		//System.out.println("el codigo enviado es: "+codigo);
		if(usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			if(usuario.isEstado()) {
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
	
	@GetMapping("/listado")
	public String mostrarTestimonio(Model model) {
		model.addAttribute("testimonios", testimonioService.getListaTestimonios());
		model.addAttribute("guardar", true);
	    return "testimonios";
	}
	
	@GetMapping("/nuevo-testimonio")
    public String getRegistroTestimonioPage(Model model) {
    	boolean edicion = false;
    	
    	model.addAttribute("testimonio", testimonioService.nuevoTestimonio());
    	model.addAttribute("estado", edicion);
    	
        return "testimonio_registro";
    }
	
	@PostMapping("/guardar_testimonio")
	public ModelAndView getGuardarTestimonio(@ModelAttribute("testimonio") Testimonio testimonio, @RequestParam("codigo") String codigo) {
		ModelAndView modelView = new ModelAndView("testimonios");
		testimonioService.guardarTestimonio(testimonio,codigo);
		modelView.addObject("testimonios", testimonioService.getListaTestimonios());
		modelView.addObject("guardar", true);
		return modelView;
	}
	
	@GetMapping("/modificar/{id}")
	public String getModificarTestimonio(Model model, @PathVariable(value="id") Long id) {
		boolean edicion = true;
		model.addAttribute("testimonio", testimonioService.findTestimonioById(id));
		model.addAttribute("edicion", edicion);
		return "testimonio_registro";
	}
	
	@PostMapping("/modificar_testimonio")
	public String modificarTestimonio(@Valid @ModelAttribute("testimonio") Testimonio testimonioModificado, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("testimonio", testimonioModificado);
			model.addAttribute("edicion", true);
			return "testimonio_registro";
		}
		
		testimonioService.modificarTestimonio(testimonioModificado);
		
		return "redirect:/testimonios/listado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id") Long id) {
		
		testimonioService.eliminarTestimonio(testimonioService.findTestimonioById(id));
		
		return "redirect:/testimonios/gestion-testimonio";
	}
	
	@GetMapping("/gestion-testimonio")
	public String getGestionTestimonios(Model model) {
		model.addAttribute("testimonios", testimonioService.getListaTestimonios());
		model.addAttribute("acciones", true);
		model.addAttribute("salir", true);
		return "testimonios";
	}
	
	@GetMapping("/acceso_administrador")
	public String contraseñaAdministrador(Model model) {
		model.addAttribute("testimonios", true);
		return "control";
	}
	
	@PostMapping("/administrador_control")
	public String administradorControl(Model model, @RequestParam("codigo") String codigo) {
		
		if(usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			if(usuario.getRol()) {
				model.addAttribute("testimonios", testimonioService.getListaTestimonios());
				model.addAttribute("acciones", true);
				model.addAttribute("salir", true);
				return "testimonios";
			}
			else {
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

