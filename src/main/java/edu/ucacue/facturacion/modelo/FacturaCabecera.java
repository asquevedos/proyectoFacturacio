package edu.ucacue.facturacion.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="facturas_cabeceras")
public class FacturaCabecera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "integer default 10")
	private int numeroFactura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEmision;
	
	@ManyToOne
	@JoinColumn(name="persona_fk")
	private Persona persona;
	
	@OneToMany(mappedBy = "facturaCabecera", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<DetalleFactura> detalles;
	

	
	public FacturaCabecera() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<DetalleFactura> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	
	
	
}
