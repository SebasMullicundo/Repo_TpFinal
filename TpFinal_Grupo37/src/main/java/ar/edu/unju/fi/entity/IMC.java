package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class IMC {
    private long id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar la fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    private LocalDate fechaIMC;
    @NotBlank(message= "Debe seleccionar un usuario")
    private Usuario usuario;
    private boolean estado;

    public IMC(long id, @NotNull(message = "Debe seleccionar una fecha") LocalDate fechaIMC, Usuario usuario, boolean estado) {
        this.id = id;
        this.fechaIMC = fechaIMC;
        this.usuario = usuario;
        this.estado = estado;
    }
    
    public String calcularIMC(Usuario usuario,float peso){
    	double resultado = peso/(usuario.getEstatura()*usuario.getEstatura());
    	if(resultado < 18.5){
    		return "Está por debajo de su peso ideal";
    	}else {
    		 if(resultado >= 18.5 && resultado <= 25) {
    			 return "esta en su peso normal";
    		 }
    	}
    	return "tiene sobrepeso";
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaIMC() {
        return fechaIMC;
    }

    public void setFechaIMC(LocalDate fechaIMC) {
        this.fechaIMC = fechaIMC;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEstado() {
        return estado;
        
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
