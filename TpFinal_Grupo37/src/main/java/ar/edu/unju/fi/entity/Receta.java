package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 @author Chosco
 @author Delgado
 @author Mullicundo
 @author Ordonez
 @author Villalba
 @version 17
 */

@Component
@Entity
@Table(name = "recetas")
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rect_id")
	private Long id;
	
	@NotEmpty(message="No puede tener el nombre vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
	@Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
	@Size(min = 4, message = "El nombre debe tener al menos cuatro caracteres")
	@Column(name = "rect_nombre")
	private String nombre;
	
	@NotEmpty(message="Debe seleccionar una categoria")
	@Column(name = "rect_categoria")
	private String categoria;
	
	@NotNull(message="Debe seleccionar un ingrediente")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "recetas_ingredientes",
	joinColumns = @JoinColumn(name = "rect_id"),
	inverseJoinColumns = @JoinColumn(name = "ingr_id"))
	private List<Ingrediente> ingredientes;
	
	@NotEmpty(message="El campo no puede estar vacía")
	@Pattern(regexp = "^[A-Z].*", message = "Debe comenzar con una letra mayúscula")
	@Size(max=600, message="La clave no puede tener mas de 600 caracteres")
	@Column(columnDefinition = "TEXT", name = "rest_preparacion")
	private String preparacion;
	
	@Column(name = "rect_img")
	private String nombreImg;
	
	@Column(name = "rect_estado")
	private boolean estado;
	
	private List<String> listaPreparaciones;
	
	public Receta() {
		
	}

	/**
	 Constructor de la clase Receta.
	 @param nombre
	 @param categoria
	 @param ingredientes
	 @param preparacion
	 @param nombreImg
	 @param estado
	 */
	public Receta(String nombre, String categoria, List<Ingrediente> ingredientes, String preparacion, String nombreImg,
			boolean estado) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
		this.nombreImg = nombreImg;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public String getNombreImg() {
		return nombreImg;
	}

	public void setNombreImg(String nombreImg) {
		this.nombreImg = nombreImg;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public List<String> getListaPreparaciones() {
		return listaPreparaciones;
	}

	public void setListaPreparaciones(List<String> listaPreparaciones) {
		this.listaPreparaciones = listaPreparaciones;
	}
}
