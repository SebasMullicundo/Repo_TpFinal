package ar.edu.unju.fi.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private Usuario usuario;

	/**
	 * Guarda un nuevo usuario.
	 *
	 * @param objeto de usuario a guardar.
	 */
	@Override
	public void guardarUsuario(Usuario usuario) {
	    usuario.setEstado(true);
	    //se guarda para generar el id
	    usuarioRepository.save(usuario);
	    //contanenacion del String con el id de usuario
	    usuario.setCodigo("usr" + usuario.getId().toString());
	    //actualizacion con el codigo de usuario generado
	    usuarioRepository.save(usuario);
	}

	/**
	 * Obtiene la lista de todos los usuarios activos.
	 *
	 * @return lista de usuarios.
	 */
	@Override
	public List<Usuario> getListaUsuarios() {
	    return usuarioRepository.findByEstado(true);
	}

	/**
	 * Busca un usuario en el sistema basado en su ID.
	 *
	 * @param id El ID del usuario a buscar.
	 * @return El objeto de usuario correspondiente al ID especificado.
	 */
	@Override
	public Usuario findUsuarioById(Long id) {
	    usuario = usuarioRepository.findById(id).get();
	    return usuario;
	}

	/**
	 * Elimina logicamente un usuario.
	 *
	 * @param objeto de usuario a eliminar.
	 */
	@Override
	public void eliminarUsuario(Usuario usuario) {
	    usuario.setEstado(false);
	    usuarioRepository.save(usuario);
	}

	/**
	 * Modifica un usuario existente.
	 *
	 * @param objeto usuario modificado.
	 */
	@Override
	public void modificarUsuario(Usuario usuario) {
	    usuario.setEstado(true);
	    usuarioRepository.save(usuario);
	    usuario.setCodigo("usr" + usuario.getId().toString());
	    usuarioRepository.save(usuario);
	}

	/**
	 * Crea un nuevo objeto de usuario.
	 *
	 * @return nuevo objeto usuario.
	 */
	@Override
	public Usuario nuevoUsuario() {
	    Usuario usuario = new Usuario();
	    usuario.setRol(false);
	    return usuario;
	}

	/**
	 * Verifica si un usuario existe basado en su código de usuario.
	 *
	 * @param código de usuario a verificar.
	 * @return true si el usuario existe, false en caso contrario.
	 */
	@Override
	public boolean verificarUsuario(String codigo_usuario) {
	    usuario = usuarioRepository.findByCodigo(codigo_usuario);
	    return usuario != null;
	}

	/**
	 * Obtiene un usuario basado en su código de usuario.
	 *
	 * @param código de usuario del usuario a obtener.
	 * @return usuario correspondiente al código especificado.
	 */
	@Override
	public Usuario obtenerUsuario(String codigo) {
	    return usuarioRepository.findByCodigo(codigo);
	}


	/*@Override
	public float calcularPesoIdeal(LocalDate fecha_nacimineto) {
		
		LocalDate fechaActual = LocalDate.now();
		
		Period periodo = Period.between(fecha_nacimineto, fechaActual);
		
		int edad = periodo.getYears();
		
		float pesoIdeal = (float) (usuario.getEstatura() - 100 + (((float) edad / 10) * 0.9));
		
		return pesoIdeal;
	}*/
	
	/**
	 * Calcula el peso ideal de un usuario basado en su estatura y edad.
	 *
	 * @param objeto usuario para el cual se calculará el peso ideal.
	 * @param edad del usuario.
	 * @return peso ideal del usuario.
	 */
	public float pesoIdeal(Usuario usuario, int edad) {
		return (usuario.getEstatura() - 100 + (( (float) edad / 10) * 0.9F));
	}
	
	/**
	 * este metodo de implementacion obtiene la fecha de nacimiento
	 * del usuario que llega como parametro y la fecha actual
	 * y mediante la resta se obtiene los años de la persona y
	 * se obtiene la edad con condiciones "si no se cumplio
	 * el mes y el dia de aniversario se le resta -1 a la resta anterior
	 * @param usuario para obtener la fecha de nacimiento 
	 * @return retorna la edad del usuario ingresado
	 */
	public int obtenerEdad(Usuario usuario) {
		LocalDate fechaActual = LocalDate.now();
	    LocalDate fechaNacimiento = usuario.getFecha_nacimiento();
	    int edad = fechaActual.getYear() - fechaNacimiento.getYear(); 
		if (fechaNacimiento.getMonthValue() > fechaActual.getMonthValue()
		        || (fechaNacimiento.getMonthValue() == fechaActual.getMonthValue()
		        && fechaNacimiento.getDayOfMonth() > fechaActual.getDayOfMonth())) {
		    edad--; // Restar 1 al resultado si el cumpleaños no ha ocurrido aún
		}
		return edad;
	}
	
}
