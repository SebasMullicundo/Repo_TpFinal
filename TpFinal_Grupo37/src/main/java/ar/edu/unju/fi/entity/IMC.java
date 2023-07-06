package ar.edu.unju.fi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="IMC")
public class IMC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Imc_id")
    private Long id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar la fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    @Column(name ="Imc_fecha")
    private LocalDate fechaIMC;
    @NotBlank(message= "Debe seleccionar un usuario")
    @Column(name="Imc_usuario")
    private Usuario usuario;
    @Column(name="Imc_estado")
    private boolean estado;

    public IMC(Long id, @NotNull(message = "Debe seleccionar una fecha") LocalDate fechaIMC, Usuario usuario, boolean estado) {
        this.id = id;
        this.fechaIMC = fechaIMC;
        this.usuario = usuario;
        this.estado = estado;
    }
    public IMC() {
    	
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

    public void setId(Long id) {
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
