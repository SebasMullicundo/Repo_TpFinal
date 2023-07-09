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

/**
 * @author Chosco
 * @author Delgado
 * @author Mullicundo
 * @author Ordoñez
 * @author Villalba
 * @version 17
 */

@Service
public class TestimonioServiceImp implements ITestimonioService {

	@Autowired
	private ITestimonioRepository testimonioRepository;

	@Autowired
	private Testimonio testimonio;

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Obtiene una lista de todos los testimonios activos
	 * 
	 * @return lista de testimonios activos.
	 */
	@Override
	public List<Testimonio> getListaTestimonios() {
		return testimonioRepository.findByEstado(true);
	}

	/**
	 * Guarda un nuevo testimonio
	 * 
	 * @param testimonio     testimonio a guardar
	 * @param codigo_usuario usuario autor del testimonio
	 */
	@Override
	public void guardarTestimonio(Testimonio testimonio, String codigo_usuario) {
		testimonio.setEstado(true);
		Usuario usuario = usuarioService.nuevoUsuario();
		usuario = usuarioService.obtenerUsuario(codigo_usuario);
		testimonio.setUsuario(usuario);
		testimonio.setFecha(LocalDate.now());
		testimonio.setNombreImg(testimonio.getUsuario().getSexo());
		testimonioRepository.save(testimonio);
	}

	/**
	 * Busca un objeto testimonio según el id
	 * 
	 * @param id ID del testimonio a buscar
	 * @return testimonio encontrado
	 */
	@Override
	public Testimonio findTestimonioById(Long id) {
		testimonio = testimonioRepository.findById(id).get();
		return testimonio;
	}

	/**
	 * Elimina un testimonio
	 * 
	 * @param testimonio objeto testimonio a eliminar
	 */
	@Override
	public void eliminarTestimonio(Testimonio testimonio) {
		testimonio.setEstado(false);
		testimonioRepository.save(testimonio);
	}

	/**
	 * Crea un nuevo objeto Testimonio
	 * 
	 * @return objeto testimonio inicializado
	 */
	@Override
	public Testimonio nuevoTestimonio() {
		return new Testimonio();
	}
}
