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
    	return imcRepository.findByEstado(true);
    }

    @Override
    public void guardarImc(Usuario usuario) {
    	IMC imc = new IMC();
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
    	return imcRepository.findByUsuarioAndEstado(usuario, estado,sortByFechaDesc);
    }
    public String calcularIMC(Usuario usuario,float peso) {
    	double resultado = (float) peso/(((float) usuario.getEstatura()/100)*( (float) usuario.getEstatura()/100));
    	if(resultado < 18.5){
    		return "Está por debajo de su peso ideal";
    	}else {
    		 if(resultado >= 18.5 && resultado <= 25) {
    			 return "esta en su peso normal";
    		 }
    	}
    	return "tiene sobrepeso";
    }
}