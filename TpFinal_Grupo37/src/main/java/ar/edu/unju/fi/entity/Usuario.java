package ar.edu.unju.fi.entity;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class Usuario {
    private Long id;
    private String codigo_usuario;
    @NotBlank(message= "Debe tener un nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
    @Size(min = 5, message = "El nombre debe tener al menos cinco caracteres")
    private String nombre;
    @NotBlank(message= "Debe tener un apellido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
    @Pattern(regexp = "^[A-Z].*", message = "El apellido debe comenzar con una letra mayúscula")
    @Size(min = 5, message = "El apellido debe tener al menos cinco caracteres")
    private String apellido;
    @NotBlank(message = "El gmail no puede estar vacío")
    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar una fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    private LocalDate fecha_nacimiento;
    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(min = 10, message = "El telefono debe tener al menos diez caracteres")
    private String telefono;
    @NotBlank(message = "Seleccione una opcion")
    private String sexo;
    @NotBlank(message = "La estatura no puede estar vacia")
    @Min(value = 0, message = "No puede ser negativo")
    private Long estatura;
    @NotBlank(message = "Seleccione una opcion")
    private String rol;
    private boolean estado;

    public Usuario(Long id, String codigo_usuario, String nombre, String apellido, String email,
                   @NotNull(message = "La fecha no puede ser null") LocalDate fecha_nacimiento, String telefono, String sexo, Long estatura, String rol, boolean estado) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
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

    public Long getEstatura() {
        return estatura;
    }

    public void setEstatura(Long estatura) {
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


