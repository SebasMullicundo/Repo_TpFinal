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
	 * Retorna el listado de recetas segun su estado
	 * @param estado
	 * @return List<Receta>
	 */
	public List<Receta> findByEstado(boolean estado);

	/**
	 * Retorna el listado de recetas segun el filtro de su categoria y estado
	 * @param categoria, estado
	 * @return List<Receta>
	 */
	public List<Receta> findByCategoriaAndEstado(String categoria, boolean estado);
}
