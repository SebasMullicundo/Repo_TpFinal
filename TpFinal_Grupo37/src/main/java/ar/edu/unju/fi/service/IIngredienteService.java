package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Ingrediente;

public interface IIngredienteService {
	
	public void guardarIngrediente(Ingrediente ingrediente);
	 
	public List<Ingrediente> getListaIngredientes();
	
	public Ingrediente findIngredienteById(Long id);
	
	public void eliminarIngrediente(Ingrediente ingrediente);
	
	void modificarIngrediente(Ingrediente ingrediente);

	public Ingrediente nuevoIngrediente();
}
