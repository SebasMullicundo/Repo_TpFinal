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
import jakarta.validation.Valid;

@Controller
@RequestMapping("/imc")
public class ImcController {
	
	@Autowired
	private IImcService imcService;
	
    private Usuario usuario;
	@GetMapping("/principal/{opcion}")
	public String getPrincipalPage(@PathVariable(value="opcion")Boolean opcion,Model model) {
		model.addAttribute("opcion", opcion);
		return "imcPrincipal";
	}
	
	/** al ingresar el id del usuario, lista en la pagina listadoPrincipal
	 * el indice corporal y los datos del usuario*/
	@GetMapping("/usuarioIngreso")
	public String getUserPage(@Valid @RequestParam("id")String id,@RequestParam("opcion")boolean opcion, Model model) {
		  if(opcion) { 
		   model.addAttribute("listaImc", imcService.getListaImcFiltrado(usuario, true));
	       model.addAttribute("usuario", usuario);
	       return "listadoImc";
		  }
		  model.addAttribute("usuario", usuario);
		  return "pesoIdeal";
    }
	
	@PostMapping("/GuardarImc")
	public String getGuardarImcPage(@Valid @RequestParam("peso")float peso,@ModelAttribute("usuario")Usuario usuario,@RequestParam("listaImc")List<IMC> listaImc, BindingResult result,Model model){
		if(result.hasErrors()) {
	      model.addAttribute("listaImc",listaImc);
		  model.addAttribute("usuario", usuario);	
		  return "listadoImc"; 
		}
		IMC imc = imcService.getImc();
		imcService.guardarImc(imc, usuario);
	    listaImc = imcService.getListaImcFiltrado(usuario, true);
		model.addAttribute("listaImc",listaImc);
		model.addAttribute("usuario", usuario);
		model.addAttribute("imc", imc);
		return "listadoImc";
	}
}

