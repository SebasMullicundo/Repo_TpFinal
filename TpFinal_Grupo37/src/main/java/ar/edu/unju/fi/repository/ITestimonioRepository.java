package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Testimonio;

/**
 * @author Chosco
 * @author Delgado
 * @author Mullicundo
 * @author Ordoñez
 * @author Villalba
 * @version 17
 */

public interface ITestimonioRepository extends CrudRepository<Testimonio, Long> {
	/**
	 * Recupera una lista de Testimonio basada en el estado especificado.
	 * 
	 * @param estado valor de estado para filtrar las entidades.
	 * @return retorna una lista de Testimonio que coinciden con el estado
	 *         especificado.
	 */
	public List<Testimonio> findByEstado(boolean estado);

}
