package ar.edu.unju.fi.service.imp;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;

@Service
public class RecetaServiceImp implements IRecetaService{

	@Autowired
	private IRecetaRepository recetaRepository;
	
	@Override
	public void guardarReceta(Receta receta) {
		receta.setEstado(true);
		receta.setNombreImg(receta.getCategoria());
		recetaRepository.save(receta);
	}

	@Override
    public List<Receta> getListaRecetas() {
        List<Receta> recetas =recetaRepository.findByEstado(true);
        for (Receta receta : recetas) {
            receta.setListaPreparaciones(generarLista(receta.getPreparacion()));
        }
        return recetas ;
    }

	@Override
	public Receta findRecetaById(Long id) {
		return recetaRepository.findById(id).get();
	}

	@Override
	public void eliminarReceta(Receta receta) {
		receta.setEstado(false);
		recetaRepository.save(receta);
	}

	@Override
	public void modificarReceta(Receta receta) {
		receta.setEstado(true);
		receta.setNombreImg(receta.getCategoria());
		recetaRepository.save(receta);
	}

	@Override
	public Receta nuevaReceta() {
		
		return new Receta();
	}

	@Override
	public List<Receta> filtroRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

	@Override
    public List<String> generarLista(String cadena) {
        String[] subcadenas = cadena.split("\\.");
        List<String> listaSubcadenas = Arrays.asList(subcadenas);
        return listaSubcadenas;
    }

}
