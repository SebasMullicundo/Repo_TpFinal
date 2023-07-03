package ar.edu.unju.fi.entity;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Receta {
	
	private Long id;
	
	@NotEmpty(message="No puede tener el nombre vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "Solo se permiten caracteres")
	@Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
	@Size(min = 5, message = "El nombre debe tener al menos cinco caracteres")
	private String nombre;
	
	@NotNull(message="Debe seleccionar una categoria")
	private String categoria;
	
	@NotNull(message="Debe seleccionar un ingrediente")
	private List<Ingrediente> ingrediente;
	
	@NotEmpty(message="La clave no puede estar vacía")
	@Pattern(regexp = "^[A-Z].*", message = "El nombre debe comenzar con una letra mayúscula")
	@Size(max=200, message="La clave no puede tener mas de 200 caracteres")
	private String preparacion;
	
	private int numeroImg;
	
	private boolean estado;
	
	public Receta() {
		
	}

	public Receta(String nombre, String categoria, List<Ingrediente> ingrediente, String preparacion, int numeroImg,
			boolean estado) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingrediente = ingrediente;
		this.preparacion = preparacion;
		this.numeroImg = numeroImg;
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

	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public int getNumeroImg() {
		return numeroImg;
	}

	public void setNumeroImg(int numeroImg) {
		this.numeroImg = numeroImg;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
