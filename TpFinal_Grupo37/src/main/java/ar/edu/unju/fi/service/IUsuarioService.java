package ar.edu.unju.fi.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.fi.entity.Usuario;

public interface IUsuarioService {
	
	/**
	 * Calcula el peso ideal de una persona basado en su fecha de nacimiento.
	 *
	 * @param fecha de nacimiento de la persona.
	 * @return El peso ideal de la persona.
	 */
	//public float calcularPesoIdeal(LocalDate fecha_nacimiento);

	/**
	 * Verifica si un usuario existe basado en su código de usuario.
	 *
	 * @param código de usuario a verificar.
	 * @return true si el usuario existe, false en caso contrario.
	 */
	public boolean verificarUsuario(String codigo_usuario);

	/**
	 * Obtiene un usuario basado en su código de usuario.
	 *
	 * @param código de usuario del usuario a obtener.
	 * @return objeto de usuario correspondiente al código especificado.
	 */
	public Usuario obtenerUsuario(String codigo);

	/**
	 * Guarda un nuevo usuario.
	 *
	 * @param objeto de usuario a guardar.
	 */
	public void guardarUsuario(Usuario usuario);

	/**
	 * Regresa la lista de todos los usuarios.
	 *
	 * @return lista de usuarios.
	 */
	public List<Usuario> getListaUsuarios();

	/**
	 * Busca un usuario basado en su ID.
	 *
	 * @param ID del usuario a buscar.
	 * @return objeto de usuario correspondiente al ID especificado.
	 */
	public Usuario findUsuarioById(Long id);

	/**
	 * Elimina logicamente un usuario.
	 *
	 * @param objeto de usuario a eliminar.
	 */
	public void eliminarUsuario(Usuario usuario);

	/**
	 * Modifica un usuario existente.
	 *
	 * @param objeto de usuario modificado.
	 */
	void modificarUsuario(Usuario usuario);

	/**
	 * Crea un nuevo objeto usuario.
	 *
	 * @return nuevo objeto usuario.
	 */
	public Usuario nuevoUsuario();

	/**
	 * Calcula el peso ideal de un usuario basado en su edad.
	 *
	 * @param usuario para el cálculo del peso ideal.
	 * @param edad    del usuario.
	 * @return peso ideal del usuario.
	 */
	public float pesoIdeal(Usuario usuario, int edad);

}
