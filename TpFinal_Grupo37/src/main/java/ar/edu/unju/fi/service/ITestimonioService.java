package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {
	
	public void guardarTestimonio(Testimonio testimonio, String codigo_usuario);
	
	public List<Testimonio> getListaTestimonios();
	
	public Testimonio findTestimonioById(Long id);
	
	public void eliminarTestimonio(Testimonio testimonio);
	
	void modificarTestimonio(Testimonio testimonio);

	public Testimonio nuevoTestimonio();
}
