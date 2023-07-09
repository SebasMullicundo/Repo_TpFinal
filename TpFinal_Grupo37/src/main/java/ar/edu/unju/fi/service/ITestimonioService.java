package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

/**
 * @author Chosco
 * @author Delgado
 * @author Mullicundo
 * @author Ordoñez
 * @author Villalba
 * @version 17
 */

public interface ITestimonioService {
	/**
	 * Guarda un objeto Testimonio
	 * 
	 * @param testimonio     objeto testimonio a guardar
	 * @param codigo_usuario codigo del usuario autor del testimonio
	 */
	public void guardarTestimonio(Testimonio testimonio, String codigo_usuario);

	/**
	 * Lista de testimonios
	 * 
	 * @return una lista de objetos Testimonio
	 */
	public List<Testimonio> getListaTestimonios();

	/**
	 * Obtiene un objeto testimonio por su ID
	 * 
	 * @param id ID del testimonio a buscar
	 * @return retorna el objeto testimonio encontrado o null en el caso de no
	 *         encontrado
	 */
	public Testimonio findTestimonioById(Long id);

	/**
	 * Elimina un objeto testimonio
	 * 
	 * @param testimonio testimonio a eliminar
	 */
	public void eliminarTestimonio(Testimonio testimonio);

	/**
	 * Obtiene un objeto Testimonio
	 * 
	 * @return un objeto Testimonio
	 */
	public Testimonio nuevoTestimonio();
}
