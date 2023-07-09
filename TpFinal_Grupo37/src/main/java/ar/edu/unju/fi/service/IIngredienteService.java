package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Ingrediente;

public interface IIngredienteService {
	
	
	//Guarda un ingrediente en el sistema.
	public void guardarIngrediente(Ingrediente ingrediente);

	
	//Obtiene la lista de ingredientes almacenados en el sistema.
	public List<Ingrediente> getListaIngredientes();

	//Busca un ingrediente por su ID.
	public Ingrediente findIngredienteById(Long id);

	//Elimina un ingrediente
	public void eliminarIngrediente(Ingrediente ingrediente);

	// Modifica un ingrediente existente.
	void modificarIngrediente(Ingrediente ingrediente);

	// Crea un nuevo objeto de tipo Ingrediente.
	public Ingrediente nuevoIngrediente();

}
