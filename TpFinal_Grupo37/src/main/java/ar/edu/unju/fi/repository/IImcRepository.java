package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
@Repository
public interface IImcRepository extends CrudRepository<IMC,Long> {
   public List<IMC> findbyEstado(boolean estado);
   public List<IMC> findByUsuario(Usuario usuario,boolean estado,Sort fechaDesc);
}
