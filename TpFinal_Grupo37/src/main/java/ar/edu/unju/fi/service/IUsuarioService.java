package ar.edu.unju.fi.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.fi.entity.Usuario;

public interface IUsuarioService {
	
	public float calcularPesoIdeal(LocalDate fecha_nacimineto);
	
	public boolean verificarUsuario(String codigo_usuario);
	
	public Usuario obtenerUsuario(String codigo);
	
	public void guardarUsuario(Usuario usuario);
	 
	public List<Usuario> getListaUsuarios();
	
	public Usuario findUsuarioById(Long id);
	
	public void eliminarUsuario(Usuario usuario);
	
	void modificarUsuario(Usuario usuario);

	public Usuario nuevoUsuario();
}
