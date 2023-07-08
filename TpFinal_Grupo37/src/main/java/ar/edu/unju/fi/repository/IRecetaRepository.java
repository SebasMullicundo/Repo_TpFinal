package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Receta;

/**
 @author Chosco
 @author Delgado
 @author Mullicundo
 @author Ordoñez
 @author Villalba
 @version 17
 */


@Repository
public interface IRecetaRepository extends CrudRepository<Receta, Long>{

	/**
	 * Retona el listado de recetas segun su estado
	 * @param estado
	 * @return
	 */
	public List<Receta> findByEstado(boolean estado);

	/**
	 * Retona el listado de recetas segun su categoria
	 * @param categoria
	 * @return
	 */
	public List<Receta> findByCategoria(String categoria);
}
