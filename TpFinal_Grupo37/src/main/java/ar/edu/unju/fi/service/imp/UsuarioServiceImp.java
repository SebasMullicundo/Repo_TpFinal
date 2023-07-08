package ar.edu.unju.fi.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private Usuario usuario;

	@Override
	public void guardarUsuario(Usuario usuario) {
		usuario.setEstado(true);
		
		usuarioRepository.save(usuario);
		usuario.setCodigo("usr"+usuario.getId().toString());
		usuarioRepository.save(usuario);
		
	}

	@Override
	public List<Usuario> getListaUsuarios() {
		
		return usuarioRepository.findByEstado(true);
	}

	@Override
	public Usuario findUsuarioById(Long id) {
	
		usuario = usuarioRepository.findById(id).get();
		return usuario;
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		
		usuario.setEstado(false);
		usuarioRepository.save(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		usuario.setEstado(true);
		
		usuarioRepository.save(usuario);
		usuario.setCodigo("usr"+usuario.getId().toString());
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario nuevoUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setRol(false);
		return usuario;
	}

	@Override
	public boolean verificarUsuario(String codigo_usuario) {
		
		usuario = usuarioRepository.findByCodigo(codigo_usuario);
		
		if(usuario==null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Usuario obtenerUsuario(String codigo) {
		
		return usuarioRepository.findByCodigo(codigo);
	}

	@Override
	public float calcularPesoIdeal(LocalDate fecha_nacimineto) {
		
		System.out.println("fecha de nacimiento: "+fecha_nacimineto);
		
		LocalDate fechaActual = LocalDate.now();
		
		System.out.println("fecha actual: "+fechaActual);
		
		Period periodo = Period.between(fecha_nacimineto, fechaActual);
		
		int edad = periodo.getYears();
		
		System.out.println("edad: "+edad);
		
		float pesoIdeal = (float) (usuario.getEstatura() - 100 + (((float) edad / 10) * 0.9));
		
		return pesoIdeal;
	}
	
	public float pesoIdeal(Usuario usuario, int edad) {
		return (usuario.getEstatura() - 100 + (( (float) edad / 10) * 0.9F));
	}
}
