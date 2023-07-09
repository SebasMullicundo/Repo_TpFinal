package ar.edu.unju.fi.controller;

import java.time.LocalDate;
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

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IImcService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/imc")
public class ImcController {
	
	/**
	 * Servicios */
	@Autowired
	private IImcService imcService;
	@Autowired
	private IUsuarioService usuarioService;
	/**
	 * este controlador maneja una solicitud GET, y responde a
	 * la ruta especificada. obtiene una variable desde la url
	 * "opcion" la cual es utilizada como valor booleano para otro
	 * controlador del cual le sera de utilidad. "imcPrincipal"
	 */
	@GetMapping("/principal/{opcion}")
	public String getPrincipalPage(@PathVariable(value="opcion")Boolean opcion,Model model) {
		model.addAttribute("error", "");
		model.addAttribute("opcion", String.valueOf(opcion));
		return "imcPrincipal";
	}
	
	/** al ingresar el id del usuario, lista en la pagina listadoPrincipal
	 * el indice corporal y los datos del usuario, tambien como parametro
	 * recibe a "opcion" que viene desde una pagina html y es utilizada
	 * en este controlador para identificar la pagina a la cual se
	 * esta intentado utilizar. habilitando para la misma distintos
	 * objetos al model necesarios en dichas paginas, asi mismo si
	 * el "id" no corresponde a ningun usuario este retorla a la misma pagina
	 * donde se hizo la solicitud*/
	@GetMapping("/usuarioIngreso")
	public String getUserPage(@Valid @RequestParam("id")String id,@RequestParam("opcion")boolean opcion, Model model) {
	  	  Usuario user = usuarioService.obtenerUsuario(id);
	  	if(user!=null && user.isEstado() != false) {
		if(opcion) { 
			model.addAttribute("listaImc", imcService.getListaImcFiltrado(user, true));
	       model.addAttribute("usuario", user);
	       model.addAttribute("aviso", "");
	       return "imc";
		  }
		  model.addAttribute("usuario", user);
		  model.addAttribute("aviso", usuarioService.pesoIdeal(user, usuarioService.obtenerEdad(user)));
		  model.addAttribute("edad", usuarioService.obtenerEdad(user));
		  return "pesoIdeal";
	  	}
	  	model.addAttribute("opcion", opcion);
	  	model.addAttribute("error", "el codigo de usuario ingresado no existe");
	  	return "imcPrincipal";
    }
	/** 
	 * este controlador recibir como parametros desde la pagina de la solicitud, a "peso" y al "id
	 * del objeto que esta siendo utilizado para realizar ciertas
	 * funciones. con los parametros es posible guardar un nuevo
	 * objeto "imc" a su respectiva tabla de entidad, usando
	 * el parametro de "id" para obtener el objeto y el "peso"
	 * para realizar un calculo y todos los elementos necesitados
	 * para retornar a la nueva pagina son enviados atravez del model
	 * como "listaImc" filtrada con propios objetos relacionados
	 * con dicho usuario*/
	@PostMapping("/GuardarImc")
	public String getGuardarImcPage(@Valid @RequestParam("peso")float peso,@RequestParam("id")String id,Model model){
		
		imcService.guardarImc(usuarioService.obtenerUsuario(id),imcService.calcularIMC(usuarioService.obtenerUsuario(id), peso));
		model.addAttribute("listaImc",imcService.getListaImcFiltrado(usuarioService.obtenerUsuario(id), true));
		model.addAttribute("usuario", usuarioService.obtenerUsuario(id));
		model.addAttribute("aviso", imcService.calcularIMC(usuarioService.obtenerUsuario(id),peso));
		return "imc";
	}
}

