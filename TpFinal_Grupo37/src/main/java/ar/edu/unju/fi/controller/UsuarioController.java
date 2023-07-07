package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Usuario usuario;
	
	@GetMapping("/acceso")
	public String contraseñaAcesso(Model model) {
		model.addAttribute("peso", true);
		return "acceso";
	}
	
	@PostMapping("/control")
	public String contraseñaControl(Model model, @RequestParam("codigo") String codigo) {
		
		System.out.println("el codigo enviado es: "+codigo);
		if(usuarioService.verificarUsuario(codigo)) {
			usuario = usuarioService.obtenerUsuario(codigo);
			System.out.println("usuario obtenido: "+ usuario.getNombre());
			model.addAttribute("usuario", usuarioService.obtenerUsuario(codigo));
			model.addAttribute("peso", usuarioService.calcularPesoIdeal(usuario.getFecha_nacimiento()));
			return "peso";
		} else {
			model.addAttribute("imc", true);
			model.addAttribute("mensaje", true);
			return "acceso";
		}
	}
	
	@GetMapping("/listado")
	public String mostrarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioService.getListaUsuarios());
	    return "usuarios";
	}
	
    @GetMapping("/registro")
    public String getNuevoRegistroPage(Model model) {
    	boolean edicion = false;
    	
    	model.addAttribute("usuario", usuarioService.nuevoUsuario());
    	model.addAttribute("estado", edicion);
    	
        return "nuevo_registro";
    }
    
    @PostMapping("/guardar_usuario")
	public ModelAndView getGuardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView("nuevo_usuario");
		if(result.hasErrors()) {
			modelView.setViewName("nuevo_registro");
			modelView.addObject("usuario", usuario);
			return modelView;
		}
		usuarioService.guardarUsuario(usuario);
		modelView.addObject("codigoUsuario", usuario.getCodigo());
		modelView.addObject("nombreUsuario", usuario.getNombre());
		return modelView;
	}
    
    @GetMapping("/modificar/{id}")
	public String getModificarUsuario(Model model, @PathVariable(value="id") Long id) {
		boolean edicion = true;
		
		model.addAttribute("usuario", usuarioService.findUsuarioById(id));
		model.addAttribute("edicion", edicion);
		return "nuevo_registro";
	}
    
    
    @PostMapping("/modificar_usuario")
	public String modificarUsuario(@Valid @ModelAttribute("usuario") Usuario usuarioModificado, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("usuario", usuarioModificado);
			model.addAttribute("edicion", true);
			return "nuevo_registro";
		}
		
		usuarioService.modificarUsuario(usuarioModificado);
		
		return "redirect:/usuarios/listado";
	}
    
    
    @GetMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(value="id") Long id) {
		
		usuarioService.eliminarUsuario(usuarioService.findUsuarioById(id));
		
		return "redirect:/usuarios/listado";
	}


}
