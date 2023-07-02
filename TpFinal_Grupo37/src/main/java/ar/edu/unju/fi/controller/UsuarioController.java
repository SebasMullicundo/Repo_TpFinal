package ar.edu.unju.fi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping("/registro")
    public String getNuevoRegistroPage(Model model) {
        return "nuevo_registro";
    }


}
