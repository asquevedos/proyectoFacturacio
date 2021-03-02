package edu.ucacue.facturacion.controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion.infraestructura.repositorio.PersonaRepositorio;
import edu.ucacue.facturacion.modelo.FacturaCabecera;
import edu.ucacue.facturacion.modelo.Persona;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

@Controller
public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextArea taDatos;

	@Autowired
	PersonaRepositorio personaRepositorio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		contentPane.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		contentPane.add(lblNewLabel_1);
		
		txtApellido = new JTextField();
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cédula");
		contentPane.add(lblNewLabel);
		
		txtCedula = new JTextField();
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Persona p = new Persona(txtNombre.getText(), txtApellido.getText(), txtCedula.getText());
				personaRepositorio.save(p);
				borrarDatos();
			}
		});
		contentPane.add(btnGuardar);
		
		taDatos = new JTextArea();
		contentPane.add(taDatos);
	}
	
	
	public void borrarDatos() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
	}
	
	public void llenarDatos()
	{
		for (Persona persona : personaRepositorio.findAll()) {
			taDatos.append(persona.toString()+"\n");
		}
	}
	
	public void generarFactura()
	{
		Persona p = personaRepositorio.findById(16).get();
		
		FacturaCabecera fC = new FacturaCabecera();
		
		fC.setPersona(p);
		fC.setNumeroFactura(1);
		fC.setFechaEmision(new Date());
		
		
		
		
		
	}
	

}
