package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Set;

import ar.edu.unju.fi.entity.Receta;

/**
 @author Chosco
 @author Delgado
 @author Mullicundo
 @author Ordoñez
 @author Villalba
 @version 17
 */


public interface IRecetaService {

	/**
	 * Guarda una nueva receta en la base de datos.
	 * @param receta la receta a guardar.
	 */
	public void guardarReceta(Receta receta);

	/**
	 * Devuelve una lista de todas las recetas existentes en la base de datos.
	 * @return una lista de objetos Receta.
	 */
	public List<Receta> getListaRecetas();

	/**
	 * Busca una receta en la base de datos por su ID.
	 * @param id el ID de la receta a buscar.
	 * @return la receta encontrada, o null si no se encuentra ninguna receta con ese ID.
	 */
	public Receta findRecetaById(Long id);

	/**
	 * Elimina una receta existente de la base de datos.
	 * @param receta la receta a eliminar.
	 */
	public void eliminarReceta(Receta receta);

	/**
	 * Modifica una receta existente en la base de datos.
	 * @param receta la receta a modificar.
	 */
	void modificarReceta(Receta receta);

	/**
	 * Crea una nueva instancia de la clase Receta.
	 * @return una nueva instancia de la clase Receta.
	 */
	public Receta nuevaReceta();

	/**
	 * Filtra las recetas existentes en la base de datos por categoría.
	 * @param categoria la categoría por la que filtrar las recetas.
	 * @return una lista de objetos Receta que pertenecen a la categoría especificada.
	 */
	public List<Receta> filtroRecetaCategoria(String categoria);

	/**
	 * Genera una lista de cadenas de texto a partir de una cadena dada.
	 * @param cadena la cadena de texto a procesar.
	 * @return una lista de cadenas de texto generada a partir de la cadena dada.
	 */
	List<String> generarLista(String cadena);

	/**
	 * Obtiene un conjunto de categorías únicas a partir de una lista de recetas.
	 * @param recetas la lista de recetas de donde se obtendrán las categorías únicas
	 * @return un conjunto de categorías únicas obtenidas de la lista de recetas
	 */
	Set<String> getCategoriasUnicas(List<Receta> recetas);
}
