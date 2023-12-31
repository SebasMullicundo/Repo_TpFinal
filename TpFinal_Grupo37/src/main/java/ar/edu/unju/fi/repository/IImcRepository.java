package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
@Repository
public interface IImcRepository extends CrudRepository<IMC,Long> {
   public List<IMC> findByEstado(boolean estado);
   public List<IMC> findByUsuarioAndEstado(Usuario usuario,boolean estado,Sort sortByFechaDesc);
}
