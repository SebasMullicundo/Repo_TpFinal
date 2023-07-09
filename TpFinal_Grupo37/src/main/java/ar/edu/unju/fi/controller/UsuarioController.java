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
	
	/**
	 * Método que redirecciona a la vista y controlar el usuario.
	 * Establece el atributo "ingredientes" en el modelo con el valor "true" y activar el formulario 
	 * correspondiente a los ingredientes.
	 *
	 * @return La vista "control".
	 */
	@GetMapping("/acceso_administrador")
	public String contraseñaAdministrador(Model model) {
		model.addAttribute("usuarios", true);
		return "control";
	}
	
	/**
	 * Realiza el control del codigo de usuario.
	 * Verifica el código enviado, el estado del usuario y su rol.
	 *
	 * @param codigo El código de usuario enviado como parámetro.
	 * @return La vista correspondiente según el resultado de las verificaciones.
	 */
	@PostMapping("/administrador_control")
	public String administradorControl(Model model, @RequestParam("codigo") String codigo) {
			
			/*
			 * verifica si el usuario existe
			 * si no existe regresa a la vista del control activando el formulario y mensaje correspondiente
			 */
		    if (!usuarioService.verificarUsuario(codigo)) {
		        model.addAttribute("usuarios", true);
		        model.addAttribute("mensaje1", true);
		        return "control";
		    }
		    
		    usuario = usuarioService.obtenerUsuario(codigo);
		    
		    /*
			 * verifica si el estado usuario en caso de estar eliminado logicamente
			 * si no esta activo regresa a la vista del control activando el formulario y mensaje correspondiente
			 */
		    if (!usuario.isEstado()) {
		        model.addAttribute("usuarios", true);
		        model.addAttribute("mensaje1", true);
		        return "control";
		    }
		    
		    /*
			 * verifica el rol del usuario
			 * si es administrador redirecciona a la direccion correspondiente a la lista de usuarios
			 */
		    if (usuario.getRol()) {
		        return "redirect:/usuarios/listado";
		    }
		    
		    //si el usuario no tiene el rol administador regresa a la vista del control activando el formulario y mensaje correspondiente
		    model.addAttribute("usuarios", true);
		    model.addAttribute("mensaje2", true);
		    return "control";
	}
	
	/**
	 * Muestra la lista de usuarios.
	 *
	 * @return nombre de la vista de usuarios.
	 */
	@GetMapping("/listado")
	public String mostrarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioService.getListaUsuarios());
	    return "usuarios";
	}
	
	/**
	 * Devuelve la página de registro de nuevo usuario.
	 *
	 * @param modelo que envia el nuevo usuario para el registro.
	 * @return nombre de la vista de nuevo registro.
	 */
    @GetMapping("/registro")
    public String getNuevoRegistroPage(Model model) {
    	boolean edicion = false;
    	
    	model.addAttribute("usuario", usuarioService.nuevoUsuario());
    	model.addAttribute("estado", edicion);
    	
        return "nuevo_registro";
    }
    
    /**
     * Guarda un nuevo usuario.
     *
     * @param objeto usuario a guardar.
     * @param objeto de resultados de validación.
     * @return vista del registro en la página de nuevo usuario.
     */
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
    
    /**
     * Regresa la página de modificación de un usuario junto con el objeto usuario.
     *
     * @param modelo utilizado para pasar el usuario y la edicion a la vista.
     * @param id    El ID del usuario a modificar.
     * @return nombre de la vista de nuevo registro.
     */
    @GetMapping("/modificar/{id}")
	public String getModificarUsuario(Model model, @PathVariable(value="id") Long id) {
		boolean edicion = true;
		
		model.addAttribute("usuario", usuarioService.findUsuarioById(id));
		model.addAttribute("edicion", edicion);
		return "nuevo_registro";
	}
    
    /**
     * Modifica un usuario existente en el sistema.
     *
     * @param usuarioModificado objeto de usuario modificado.
     * @param result            objeto de resultados de validación.
     * @param model             modelo utilizado para pasar el usuario a la vista en caso de error.
     * @return nombre de la vista de nuevo registro si hay errores, o redirecciona a la página de usuarios.
     */
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
    
    /**
     * Elimina logicamente un usuario.
     *
     * @param ID del usuario a eliminar.
     * @return Redirecciona a la página de usuarios.
     */
    @GetMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable(value="id") Long id) {
		
		usuarioService.eliminarUsuario(usuarioService.findUsuarioById(id));
		
		return "redirect:/usuarios/listado";
	}


}
