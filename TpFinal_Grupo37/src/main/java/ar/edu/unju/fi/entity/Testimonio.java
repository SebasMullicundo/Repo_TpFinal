package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Chosco
 * @author Delgado
 * @author Mullicundo
 * @author Ordonez
 * @author Villalba
 * @version 17
 */

@Component
@Entity
@Table(name = "testimonios")
public class Testimonio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testimonio_id")
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "testimonio_fecha")
	private LocalDate fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_id")
	private Usuario usuario;

	@Column(name = "testimonio_comentario")
	private String comentario;

	@Column(name = "testimonio_img")
	private String testImg;

	@Column(name = "testimonio_estado")
	private boolean estado;

	public Testimonio() {
	}

	public Testimonio(LocalDate fecha, Usuario usuario, String comentario) {
		this.fecha = fecha;
		this.usuario = usuario;
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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

	public String getTestImg() {
		return testImg;
	}

	public void setNombreImg(String testImg) {
		this.testImg = testImg;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setTestImg(String testImg) {
		this.testImg = testImg;
	}
}
