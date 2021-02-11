package edu.ucacue.facturacion.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_factura")
public class DetalleFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "integer default 200")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="factura_cabecera_fk")
	private FacturaCabecera facturaCabecera;
	
	@ManyToOne
	@JoinColumn(name="producto_fk")
	private Producto producto;
	
	
	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public DetalleFactura(int cantidad, FacturaCabecera facturaCabecera, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.facturaCabecera = facturaCabecera;
		this.producto = producto;
	}


	public DetalleFactura() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public FacturaCabecera getFacturaCabecera() {
		return facturaCabecera;
	}
	public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}
	
	
	
	
}
