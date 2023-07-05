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

	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public List<Ingrediente> getListaIngredientes() {
		return ingredienteRepository.findByEstado(true);
	}

	@Override
	public Ingrediente findIngredienteById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	@Override
	public void eliminarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(false);
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public void modificarIngrediente(Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public Ingrediente nuevoIngrediente() {
		return new Ingrediente();
	}

}
