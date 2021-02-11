package edu.ucacue.facturacion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion.infraestructura.repositorio.FacturaCabeceraRepositorio;
import edu.ucacue.facturacion.infraestructura.repositorio.PersonaRepositorio;
import edu.ucacue.facturacion.infraestructura.repositorio.ProductoRepositorio;
import edu.ucacue.facturacion.modelo.DetalleFactura;
import edu.ucacue.facturacion.modelo.FacturaCabecera;
import edu.ucacue.facturacion.modelo.Persona;
import edu.ucacue.facturacion.modelo.Producto;

@Controller
public class Principal {

	@Autowired
	PersonaRepositorio personaRepositorio;

	@Autowired
	FacturaCabeceraRepositorio fcRepositorio;
	
	@Autowired
	ProductoRepositorio productoRepositorio;

	// FacturaCabeceraRepositorio fcRepositorio;

	public Principal() {

	}

	public void interfaz() {
		Scanner input = new Scanner(System.in);
		boolean mainLoop = true;

		int choice;
		while (true) {
			System.out.println("Menu\n");
			System.out.print("1.) Guardar \n");
			System.out.print("2.) Listar \n");
			System.out.print("3.) Eliminar.\n");
			System.out.print("4.) Salir.\n");
			System.out.print("5.) Generar Factura \n");

			System.out.print("\n Ingrese la opcion del Menú: ");

			choice = input.nextInt();

			switch (choice) {

			case 1:

				String nombre, apellido, cedula;
				System.out.print("Ingrese Nombre: ");
				nombre = input.next();
				System.out.print("Ingrese Apellido: ");
				apellido = input.next();
				System.out.print("Ingrese cedula: ");
				cedula = input.next();

				Persona p = new Persona(nombre, apellido, cedula);

				personaRepositorio.save(p);
				break;

			case 2:

				List<Persona> personas = personaRepositorio.findAll();

				for (Persona persona : personas) {
					System.out.println(persona);
				}
				break;

			case 3:
				// Implementar método para eliminar
				break;

			case 4:
				System.exit(0);

			case 5:
				System.exit(0);

			default:

				System.out.print("Ingrese en id de la persona que facturará: ");
				int id = input.nextInt();
				Persona pF = personaRepositorio.findById(id).get();
				if (pF == null) {
					System.out.println("Persona no existe");
					break;
				}
				FacturaCabecera fc = new FacturaCabecera();
				fc.setPersona(pF);// Es equivalente a registrar a la persona en la cabecera de la factura

				System.out.print("Ingrese el número de factura: ");
				int nF = input.nextInt();
				fc.setNumeroFactura(nF);

				fc.setFechaEmision(new Date());
				// ***********************Sección de la factura cabecera
				// ************************

				// *********************** Gestionar Los detalles de la factura
				// ************************

				System.out.println("Cuantos productos comprará");
				int numProductos = input.nextInt();
				List<DetalleFactura> detalles = new ArrayList();
				
				for (int i = 0; i < numProductos; i++) {
					DetalleFactura dF=new DetalleFactura();
					System.out.println("Ingrese el producto a comprar");
					String nombreProducto= input.nextLine();
					Producto productoEncontrado= productoRepositorio.findByNombre(nombreProducto);
					
					dF.setProducto(productoEncontrado);
					dF.setFacturaCabecera(fc);
					
					System.out.println("Ingrese la cantidad de productos a comprar");
					dF.setCantidad(input.nextInt());
					
					detalles.add(dF);
				}
				
				fc.setDetalles(detalles);
				
				fcRepositorio.save(fc);
				

				break;
			}
		}

	}

	public void insertarPersona(Persona p) {
		try {
			personaRepositorio.save(p);
		} catch (Exception e) {
			System.out.println("Error al insertar los datos");
		}
	}

	public void listarPersonas() {
		List<Persona> personas = personaRepositorio.findAll();

		for (Persona persona : personas) {
			System.out.println(persona);
		}
	}

	public void listarPersonasById(int id) {
		try {
			Persona persona = personaRepositorio.findById(id).get();
			System.out.println(persona);
		} catch (Exception e) {
			System.out.println("No se encuentra a la persona");
		}

	}

	public void generarFactura() {
		Persona p = personaRepositorio.findById(16).get();// Este id tienen que ver en la BD

		FacturaCabecera fc = new FacturaCabecera();

		fc.setPersona(p);
		fc.setNumeroFactura(1001);
		fc.setFechaEmision(new Date());
		fc.setDetalles(null);

		/*
		 * DetalleFactura df1 = new DetalleFactura(3, 1.25, "pan", fc); DetalleFactura
		 * df2 = new DetalleFactura(1, 0.25, "cola", fc); DetalleFactura df3 = new
		 * DetalleFactura(5, 1, "huevos", fc); DetalleFactura df4 = new
		 * DetalleFactura(6, 1.25, "leche", fc);
		 * 
		 * List<DetalleFactura> dfacturas = new ArrayList<>(); dfacturas.add(df1);
		 * dfacturas.add(df2); dfacturas.add(df3); dfacturas.add(df4);
		 * fc.setDetalles(dfacturas);
		 */

		fcRepositorio.save(fc);

	}

	public void recuperarFactura() {
		FacturaCabecera fc = fcRepositorio.findById(2).get();// Este id tienen que ver en la BD
		System.out.println(fc);
	}

	public void buscarByNombre() {
		List<Persona> personas = personaRepositorio.buscarPorNombre("Juan");
		for (Persona persona : personas) {
			System.out.println(persona);
		}
	}

	public void buscar2() {
		List<Persona> personas = personaRepositorio.findByNombreAndApellido("Juan", "perez");
		for (Persona persona : personas) {
			System.out.println(persona);
		}
	}

}
