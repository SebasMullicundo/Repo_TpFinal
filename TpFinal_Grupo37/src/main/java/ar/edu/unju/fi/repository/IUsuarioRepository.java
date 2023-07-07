package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.Usuario;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public List<Usuario> findByEstado(boolean estado);
	
	public Usuario findByCodigo(String codigoUsuario);

}
