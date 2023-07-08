package ar.edu.unju.fi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@NotNull(message="Debe seleccionar la fecha")
    @Column(name ="Imc_fecha")
    private LocalDateTime fechaIMC;
	@NotBlank(message = "no puede estar vacio")
	@Column(name = "Imc_registro")
	private String registro;
    
    @ManyToOne
    @JoinColumn(name="usr_id")
    private Usuario usuario;

    @Column(name="Imc_estado")
    private boolean estado;

    public IMC(Long id, @NotNull(message = "Debe seleccionar una fecha") LocalDateTime fechaIMC, Usuario usuario, boolean estado) {
        this.id = id;
        this.fechaIMC = fechaIMC;
        this.usuario = usuario;
        this.estado = estado;
    }
    public IMC() {
    	
    }
    
    
    
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaIMC() {
        return fechaIMC;
    }

    public void setFechaIMC(LocalDateTime fechaIMC) {
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
    public String getRegistro() {
    	return this.registro;
    }
    public void setRegistro(String registro) {
    	this.registro = registro;
    }

}
