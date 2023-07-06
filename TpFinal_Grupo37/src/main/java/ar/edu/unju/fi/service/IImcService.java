package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.IMC;
import ar.edu.unju.fi.entity.Usuario;
public interface IImcService {
	/**
     * @method Devuelve una lista de todos los IMC
     */
    List<IMC> getListaImc();
    /**
     * @method Guarda un nuevo IMC
     */
    void guardarImc(IMC imc,Usuario usuario);
    /**
     * @method  Devuelve el IMC cuyo id coincide con el parámetro id.
     */
    IMC buscarImc(Long id);
    /**
     * @method  Devuelve el IMC cuyo codigo coincide con el parámetro codigo.
     */

    void modificarImc(IMC imc);
    /**
     * @method Elimina un Imc existente
     */
    void eliminarImc(IMC imcEncontrado);
    /**
     * @method Devuelve un nuevo objeto Imc
     */
    IMC getImc();
    
    List<IMC> getListaImcFiltrado(Usuario usuario,boolean estado);
}
