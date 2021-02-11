package edu.ucacue.facturacion.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ucacue.facturacion.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
	
	List<Persona> findByNombre(String nombre);
	List<Persona> findByApellido(String apellido);
	List<Persona> findByNombreAndApellido(String nombre, String apellido);
	List<Persona> findByNombreLike(String nombre);
	
	//jpql Java persistence query lenguaje 
	@Query("select p from Persona p where p.nombre = :nombre")
	List<Persona> buscarPorNombre(@Param("nombre") String nombre);
	
	@Query("select p from Persona p where p.apellido = :apellido")
	List<Persona> buscarPorApellido(@Param("apellido") String apellido);
	
	@Query("select p from Persona p where p.apellido = :apellido and p.nombre = :nombre")
	List<Persona> buscarPorApellidoYnombre(@Param("apellido") String apellido, @Param("nombre") String nombre);	



}
