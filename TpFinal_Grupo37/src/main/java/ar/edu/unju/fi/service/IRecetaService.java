package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Set;

import ar.edu.unju.fi.entity.Receta;

public interface IRecetaService {
	
	public void guardarReceta(Receta receta);
	 
	public List<Receta> getListaRecetas();
	
	public Receta findRecetaById(Long id);
	
	public void eliminarReceta(Receta receta);
	
	void modificarReceta(Receta receta);

	public Receta nuevaReceta();
	
	public List<Receta> filtroRecetaCategoria(String categoria);
	
	List<String> generarLista(String cadena);
	
	Set<String> getCategoriasUnicas(List<Receta> recetas);
}
