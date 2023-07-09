package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;

@Service
public class IngredienteServiceImp implements IIngredienteService{
	
	@Autowired
	private IIngredienteRepository ingredienteRepository;

	/**
	 * Guarda un ingrediente.
	 *
	 * @param ingrediente, objeto ingrediente a guardar.
	 */
	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
	    ingredienteRepository.save(ingrediente);
	}

	/**
	 * Obtiene la lista de ingredientes.
	 *
	 * @return regresa una lista de ingredientes activos.
	 */
	@Override
	public List<Ingrediente> getListaIngredientes() {
	    return ingredienteRepository.findByEstado(true);
	}

	/**
	 * Busca un ingrediente por su ID.
	 *
	 * @param id del ingrediente a buscar.
	 * @return regresa el ingrediente encontrado.
	 */
	@Override
	public Ingrediente findIngredienteById(Long id) {
	    return ingredienteRepository.findById(id).get();
	}

	/**
	 * Elimina un ingrediente de manera logica.
	 *
	 * @param ingrediente a eliminar.
	 */
	@Override
	public void eliminarIngrediente(Ingrediente ingrediente) {
	    ingrediente.setEstado(false);
	    ingredienteRepository.save(ingrediente);
	}

	/**
	 * Modifica un ingrediente existente.
	 *
	 * @param ingrediente modificado.
	 */
	@Override
	public void modificarIngrediente(Ingrediente ingrediente) {
	    ingrediente.setEstado(true);
	    ingredienteRepository.save(ingrediente);
	}

	/**
	 * Crea un nuevo objeto de tipo Ingrediente.
	 *
	 * @return nuevo objeto Ingrediente.
	 */
	@Override
	public Ingrediente nuevoIngrediente() {
	    return new Ingrediente();
	}


}
