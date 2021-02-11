package edu.ucacue.facturacion.infraestructura.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;


import edu.ucacue.facturacion.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	
	Producto findByNombre(String nombre);

}
