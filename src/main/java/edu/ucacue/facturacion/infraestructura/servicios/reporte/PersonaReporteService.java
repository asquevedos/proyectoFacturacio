package edu.ucacue.facturacion.infraestructura.servicios.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import edu.ucacue.facturacion.infraestructura.repositorio.PersonaRepositorio;
import edu.ucacue.facturacion.modelo.Persona;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@Service
public class PersonaReporteService {
	
	@Autowired
	private PersonaRepositorio personaRepositorio;
	
	public String generarReporte(String formato) throws FileNotFoundException, JRException
	{
		List<Persona> personas = personaRepositorio.findAll();
		
		//Hay que mapear las personas al reporte
		File file= ResourceUtils.getFile("classpath:reportes\\Personas.jrxml");

		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		
		
		JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(personas);
		
		Map<String,Object> parametros= new HashedMap();
		parametros.put("Creado por","Sebastian Quevedo");
		
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parametros, dataSource);
		if(formato.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"c:\\personas.html");
		}
		if(formato.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,"c:\\personas.pdf");
		}
		return "Reporte generado correctamente";
	}

}
