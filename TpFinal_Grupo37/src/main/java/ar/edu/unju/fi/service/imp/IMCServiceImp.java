package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IImcRepository;
import ar.edu.unju.fi.service.IImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * este metodo de implementacion guarda un Imc en la tabla,
     * para ello, necesita recibir como parametro el respectivo
     * registro obtenido del IMC y el usuario al cual estara
     * vinculado.
     */
    @Override
    public void guardarImc(Usuario usuario,String registro) {
    	IMC imc = new IMC();
        imc.setEstado(true);
        imc.setFechaIMC(LocalDateTime.now());
        imc.setUsuario(usuario);
        imc.setRegistro(registro);
        imcRepository.save(imc);
    }
    /**
     * busca un Imc de la tabla respecto a un "id"
     */
    @Override
    public IMC buscarImc(Long id){
    	return imcRepository.findById(id).get();
    	}
    /**
     * modifica un imc de la tabla con respecto al objeto Imc
     */
    @Override
    public void modificarImc(IMC imc) {imcRepository.save(imc);}

    /**
     * cambia el estado de un objeto imc de la tabla
     */
    @Override
    public void eliminarImc(IMC imc) {
        imc.setEstado(false);
        imcRepository.save(imc);
    }
    /**
     * devuelve un objeto imc
     */
    @Override
    public IMC getImc() {return imc;}
    /**
     * obtiene una lista filtrada de la tabla Imc con respecto a un
     * usuario y su estado activo, ademas la forma en la cual
     * se va almacenando en la lista depende de la fecha y el tiempo
     * de forma descendiente.
     */
    @Override
    public List<IMC> getListaImcFiltrado(Usuario usuario,boolean estado){
    	Sort sortByFechaDesc = Sort.by(Sort.Direction.DESC, "fechaIMC");
    	return imcRepository.findByUsuarioAndEstado(usuario, estado,sortByFechaDesc);
    }
    /**
     * devuelve un String informativo del imc de un usuario
     */
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