package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioRepository extends CrudRepository<Testimonio, Long>{
	public List<Testimonio> findByEstado(boolean estado);
	
}
