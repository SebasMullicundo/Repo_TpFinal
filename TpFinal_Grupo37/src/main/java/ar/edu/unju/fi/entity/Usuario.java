package ar.edu.unju.fi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
    private Long id;
	
	@Column(name = "usr_cod")
    private String codigo;
    
	@NotEmpty(message="No puede tener el nombre vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
	@Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
	@Size(min = 4, message = "El nombre debe tener al menos cuatro caracteres")
    @Column(name = "usr_nombre")
	private String nombre;
    
	@NotEmpty(message="No puede tener el nombre vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
	@Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
	@Size(min = 4, message = "El nombre debe tener al menos cuatro caracteres")
    @Column(name = "usr_apellido")
	private String apellido;
    
	@NotEmpty(message="El email no puede estar vacío")
	@Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message="Ingrese un correo electrónico válido. Ej:ejemplo123@gmail.com")
    @Column(name = "usr_gmail")
	private String email;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Debe seleccionar una fecha")
    @Past(message="La fecha debe ser menor a la fecha actual")
    @Column(name = "usr_nac")
    private LocalDate fecha_nacimiento;
    
    @NotBlank(message = "El telefono no puede estar vacío")
    @Column(name = "usr_telef")
    private String telefono;
    
    @NotBlank(message = "Seleccione una opcion")
    @Column(name = "sexo")
    private String sexo;
    
    @NotNull(message = "La estatura no puede estar vacia")
    //@Pattern(regexp = "^[0-9]+$", message = "La estatura debe debe ser en centimetros")
    @Column(name = "usr_altura")
    private float estatura;
    
    @Column(name = "usr_rol")
    private boolean rol;
    
    @Column(name = "usr_estado")
    private boolean estado;
    
    @OneToMany(mappedBy = "usuario")
    private List<IMC> imc;
    public Usuario() {
	}

    public Usuario(Long id, String codigo, String nombre, String apellido, String email, LocalDate fecha_nacimiento, String telefono, String sexo,float estatura, boolean rol, boolean estado) {
        this.id = id;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public boolean getRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}


