package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/imc")
public class ImcController {
    private Usuario usuario;
    private IMC imc;
	@GetMapping("/principal")
	public String getPrincipalPage(Model model) {
		
		return "imcPrincipal";
	}
	
	/** al ingresar el id del usuario, lista en la pagina listadoPrincipal
	 * el indice corporal y los datos del usuario*/
	@GetMapping("/usuarioIngreso")
	public String getUserPage(@RequestParam("id")Long id, Model model) {
	       model.addAttribute("listaImc", imc);
	       model.addAttribute("usuario", usuario);
	       model.addAttribute("id", id);
		return "listadoPrincipal"; 
    }
	
	@PostMapping("/GuardarImc")
	public String getGuardarImcPage(@Valid @ModelAttribute("peso")float peso,@ModelAttribute("id")Long id, BindingResult result,Model model){
		if(result.hasErrors()) {
		  return "redirect:/imc/usuarioIngreso"; 
		}
		//puede que se necesite un arreglo
	    //imc.guardarRegistro(peso)  -> imc.getby().getlista().add(registro)
		return "redirect:/imc/usuarioIngreso";
	}
}
