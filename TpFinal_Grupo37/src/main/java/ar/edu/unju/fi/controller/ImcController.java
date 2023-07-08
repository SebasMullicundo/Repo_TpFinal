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

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IImcService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/imc")
public class ImcController {
	
	@Autowired
	private IImcService imcService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/principal/{opcion}")
	public String getPrincipalPage(@PathVariable(value="opcion")Boolean opcion,Model model) {
		model.addAttribute("error", "");
		model.addAttribute("opcion", String.valueOf(opcion));
		return "imcPrincipal";
	}
	
	/** al ingresar el id del usuario, lista en la pagina listadoPrincipal
	 * el indice corporal y los datos del usuario*/
	@GetMapping("/usuarioIngreso")
	public String getUserPage(@Valid @RequestParam("id")String id,@RequestParam("opcion")boolean opcion, Model model) {
	  	  Usuario user = usuarioService.obtenerUsuario(id);
	  	if(user!=null) {
		if(opcion) { 
			model.addAttribute("listaImc", imcService.getListaImcFiltrado(user, true));
	       model.addAttribute("usuario", user);
	       model.addAttribute("aviso", "");
	       return "imc";
		  }
		  model.addAttribute("usuario", user);
		  model.addAttribute("aviso", 0.0F);
		  return "pesoIdeal";
	  	}
	  	model.addAttribute("opcion", opcion);
	  	model.addAttribute("error", "el codigo de usuario ingresado no existe");
	  	return "imcPrincipal";
    }
	
	@PostMapping("/GuardarImc")
	public String getGuardarImcPage(@Valid @RequestParam("peso")float peso,@RequestParam("id")String id,Model model){
		
		imcService.guardarImc(usuarioService.obtenerUsuario(id));
		model.addAttribute("listaImc",imcService.getListaImcFiltrado(usuarioService.obtenerUsuario(id), true));
		model.addAttribute("usuario", usuarioService.obtenerUsuario(id));
		model.addAttribute("aviso", imcService.calcularIMC(usuarioService.obtenerUsuario(id),peso));
		return "imc";
	}
	@GetMapping("/edadIngreso")
	public String getPesoIdealPage(@RequestParam("edad")int edad,@RequestParam(value="id")String id,Model model){
		model.addAttribute("aviso", usuarioService.pesoIdeal(usuarioService.obtenerUsuario(id), edad));
		model.addAttribute("usuario", usuarioService.obtenerUsuario(id));
		return "pesoIdeal";
	}
	
}

