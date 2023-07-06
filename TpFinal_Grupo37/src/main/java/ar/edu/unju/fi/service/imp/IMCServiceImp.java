package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IImcRepository;
import ar.edu.unju.fi.service.IImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IMCServiceImp implements IImcService {
   
    @Autowired
    private IImcRepository imcRepository;
    @Autowired
    private IMC imc;
    @Override
    public List<IMC> getListaImc() {
    	return imcRepository.findbyEstado(true);
    }

    @Override
    public void guardarImc(IMC imc,Usuario usuario) {
        imc.setEstado(true);
        imc.setFechaIMC(LocalDate.now());
        imc.setUsuario(usuario);
        imcRepository.save(imc);
    }

    @Override
    public IMC buscarImc(Long id){
    	return imcRepository.findById(id).get();
    	}
    @Override
    public void modificarImc(IMC imc) {imcRepository.save(imc);}

    @Override
    public void eliminarImc(IMC imc) {
        imc.setEstado(false);
        imcRepository.save(imc);
    }
    /**
     * devuelve un objeto
     */
    @Override
    public IMC getImc() {return imc;}
    @Override
    public List<IMC> getListaImcFiltrado(Usuario usuario,boolean estado){
    	Sort sortByFechaDesc = Sort.by(Sort.Direction.DESC, "fechaIMC");
    	return imcRepository.findByUsuario(usuario, estado, sortByFechaDesc);
    }
}