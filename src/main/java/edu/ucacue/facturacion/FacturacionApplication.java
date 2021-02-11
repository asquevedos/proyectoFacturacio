package edu.ucacue.facturacion;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.ucacue.facturacion.controlador.Principal;
import edu.ucacue.facturacion.controlador.PrincipalUI;

@SpringBootApplication
public class FacturacionApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturacionApplication.class)
	    .headless(false)
	    .web(WebApplicationType.NONE)
	    .run(args);
		//PrincipalUI ventana = contexto.getBean(PrincipalUI.class);
		Principal principal= contexto.getBean(Principal.class);
		/* Para insertar
		Persona persona2= new Persona("Ana", "Perez", "888");
		Persona persona3= new Persona("Santiago","Lazo","123446");
		p.insertarPersona(persona2);
		p.insertarPersona(persona3);
		*/
		//p.listarPersonas();
		
		//ventana.setVisible(true);
		//ventana.llenarDatos();
		//principal.generarFactura();
		//principal.recuperarFactura();
		//principal.buscarByNombre();
		principal.buscar2();
		principal.interfaz();
		
	}

}
