package ar.edu.unju.fi.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Service
public class TestimonioServiceImp implements ITestimonioService{
	
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Testimonio> getListaTestimonios() {
		return testimonioRepository.findByEstado(true);
	}

	@Override
	public void guardarTestimonio(@Valid Testimonio testimonio, String codigo_usuario) {
		testimonio.setEstado(true);
		Usuario usuario = usuarioService.nuevoUsuario();
		usuario = usuarioService.obtenerUsuario(codigo_usuario);
		testimonio.setUsuario(usuario);
		testimonio.setFecha(LocalDate.now());
		//testimonio.setUsuario(usuarioRepository.buscarPorCodigoUsuario(codigo).get(0));
		testimonio.setNombreImg(testimonio.getUsuario().getSexo());
		testimonioRepository.save(testimonio);
	}
	
	@Override
	public Testimonio findTestimonioById(Long id) {
		testimonio = testimonioRepository.findById(id).get();
		return testimonio;
	}

	@Override
	public void modificarTestimonio(Testimonio testimonio) {
		testimonio.setEstado(true);
		testimonio.setNombreImg(testimonio.getUsuario().getSexo());
		testimonioRepository.save(testimonio);
	}

	@Override
	public void eliminarTestimonio(Testimonio testimonio) {
		testimonio.setEstado(false);
		testimonioRepository.save(testimonio);
	}
	
	@Override
	public Testimonio nuevoTestimonio() {
		return new Testimonio();
	}
}
