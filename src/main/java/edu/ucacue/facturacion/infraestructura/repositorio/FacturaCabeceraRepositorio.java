package edu.ucacue.facturacion.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion.modelo.FacturaCabecera;


public interface FacturaCabeceraRepositorio extends JpaRepository<FacturaCabecera, Integer> {

}
