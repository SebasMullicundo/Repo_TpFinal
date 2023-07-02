package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;


public class Usuario {
    private long id;
    private String codigo_usuario;
    @NotBlank(message= "Debe tener un nombre")
    private String nombre;
    @NotBlank(message= "Debe tener un apellido")
    private String apellido;
    @NotBlank(message = "El gmail no puede estar vacío")
    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar una fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    private String fecha_nacimiento;
    @NotBlank(message = "El telefono no puede estar vacío")
    private String telefono;
    @NotBlank(message = "Seleccione una opcion")
    private String sexo;
    @NotBlank(message = "La estatura no puede estar vacia")
    @Min(value = 0, message = "No puede ser negativo")
    private long estatura;
    @NotBlank(message = "Seleccione una opcion")
    private String rol;
    private boolean estado;

    public Usuario(long id, String codigo_usuario, String nombre, String apellido, String email,
                   @NotNull(message = "La fecha no puede ser null") String fecha_nacimiento, String telefono, String sexo, long estatura, String rol, boolean estado) {
        this.id = id;
        this.codigo_usuario = codigo_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
        this.estatura = estatura;
        this.rol = rol;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getEstatura() {
        return estatura;
    }

    public void setEstatura(long estatura) {
        this.estatura = estatura;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}


