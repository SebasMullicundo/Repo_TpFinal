package ar.edu.unju.fi.service.imp;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
		int numeroImagen;
		Random random = new Random();
		numeroImagen = random.nextInt(3) + 1;
		receta.setNombreImg(String.valueOf(numeroImagen));
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
		int numeroImagen;
		Random random = new Random();
		numeroImagen = random.nextInt(3) + 1;
		
		receta.setNombreImg(String.valueOf(numeroImagen));
		recetaRepository.save(receta);
	}

	@Override
	public Receta nuevaReceta() {
		
		return new Receta();
	}

	@Override
	public List<Receta> filtroRecetaCategoria(String categoria) {
		return recetaRepository.findByCategoriaAndEstado(categoria, true);
	}

	@Override
    public List<String> generarLista(String cadena) {
        String[] subcadenas = cadena.split("\\.");
        List<String> listaSubcadenas = Arrays.asList(subcadenas);
        return listaSubcadenas;
    }

	@Override
	public Set<String> getCategoriasUnicas(List<Receta> recetas) {
		Set<String> categorias = new HashSet<>();
		for(Receta receta : recetas) {
			categorias.add(receta.getCategoria());
		}
		return categorias;
	}
}
