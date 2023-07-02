package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

public class Testimonio {
    private long id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar la fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    private String fecha;
    @NotBlank(message= "Debe seleccionar un usuario")
    private Usuario usuario;
    @NotBlank(message= "Debe ingresar un comentario")
    private String comentario;
    private boolean estado;

    public Testimonio(long id, @NotNull(message = "Debe seleccionar la fecha") String fecha, Usuario usuario, String comentario, boolean estado) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.comentario = comentario;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
