package edu.ucacue.facturacion;

import java.io.FileNotFoundException;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.ucacue.facturacion.controlador.Principal;
import edu.ucacue.facturacion.controlador.PrincipalUI;
import edu.ucacue.facturacion.controlador.persona.PersonaUI;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class FacturacionApplication {

	public static void main(String[] args) throws FileNotFoundException, JRException {

		ConfigurableApplicationContext contexto = new SpringApplicationBuilder(FacturacionApplication.class)
	    .headless(false)
	    .web(WebApplicationType.NONE)
	    .run(args);

		PrincipalUI principalUI= contexto.getBean(PrincipalUI.class);
		principalUI.setVisible(true);
	}

}
