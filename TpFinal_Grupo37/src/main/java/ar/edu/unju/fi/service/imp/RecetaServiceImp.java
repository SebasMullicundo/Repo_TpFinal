package ar.edu.unju.fi.service.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;

/**
 @author Chosco
 @author Delgado
 @author Mullicundo
 @author Ordoñez
 @author Villalba
 @version 17
 */


@Service
public class RecetaServiceImp implements IRecetaService{

	@Autowired
	private IRecetaRepository recetaRepository;

	/**
	 * Guarda una receta en la base de datos.
	 * @param receta la receta a guardar.
	 */
	@Override
	public void guardarReceta(Receta receta) {
		receta.setEstado(true);
		receta.setNombreImg(receta.getCategoria());
		recetaRepository.save(receta);
	}

	/**
	 * Obtiene una lista de recetas de la base de datos.
	 * @return una lista de recetas.
	 */
	@Override
	public List<Receta> getListaRecetas() {
		List<Receta> recetas =recetaRepository.findByEstado(true);
		for (Receta receta : recetas) {
			receta.setListaPreparaciones(generarLista(receta.getPreparacion()));
		}
		return recetas ;
	}

	/**
	 * Busca una receta en la base de datos por su id.
	 * @param id el id de la receta a buscar.
	 * @return la receta encontrada.
	 */
	@Override
	public Receta findRecetaById(Long id) {
		Receta receta=recetaRepository.findById(id).get();
		receta.setListaPreparaciones(generarLista(receta.getPreparacion()));
		return receta;
	}

	/**
	 * Elimina una receta de la base de datos.
	 * @param receta la receta a eliminar.
	 */
	@Override
	public void eliminarReceta(Receta receta) {
		receta.setEstado(false);
		recetaRepository.save(receta);
	}

	/**
	 * Modifica una receta en la base de datos.
	 * @param receta la receta a modificar.
	 */
	@Override
	public void modificarReceta(Receta receta) {
		receta.setEstado(true);
		receta.setNombreImg(receta.getCategoria());
		recetaRepository.save(receta);
	}

	/**
	 * Crea una nueva receta.
	 * @return la nueva receta creada.
	 */
	@Override
	public Receta nuevaReceta() {

		return new Receta();
	}

	/**
	 * Filtra las recetas de la base de datos por categoría.
	 * @param categoria la categoría por la que filtrar las recetas.
	 * @return una lista de recetas filtradas por categoría.
	 */
	@Override
	public List<Receta> filtroRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

	/**
	 * Genera una lista de subcadenas a partir de una cadena dada.
	 * @param cadena la cadena a dividir en subcadenas.
	 * @return una lista de subcadenas.
	 */
	@Override
	public List<String> generarLista(String cadena) {
		String[] subcadenas = cadena.split("\\.");
		List<String> listaSubcadenas = Arrays.asList(subcadenas);
		return listaSubcadenas;
	}

}