package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class IMC {
    private Long id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar la fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    private LocalDate fechaIMC;
    @NotBlank(message= "Debe seleccionar un usuario")
    private Usuario usuario;
    private boolean estado;

    public IMC(Long id, @NotNull(message = "Debe seleccionar una fecha") LocalDate fechaIMC, Usuario usuario, boolean estado) {
        this.id = id;
        this.fechaIMC = fechaIMC;
        this.usuario = usuario;
        this.estado = estado;
    }

    public Long getId() {
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
