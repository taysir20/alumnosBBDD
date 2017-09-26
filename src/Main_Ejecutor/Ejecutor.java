package Main_Ejecutor;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import controlador.CImplementacion;
import controlador.Controlador;
import modelo.MImplementacion;
import modelo.Modelo;
import vista.VAlumnos;

public class Ejecutor {

	public static void main(String[] args) {
		Modelo modelo = new MImplementacion();
		Controlador controlador = new CImplementacion();
		controlador.setModelo(modelo);
		((CImplementacion) controlador).getBBDD();
		VAlumnos vistaAlumnos = new VAlumnos();
		vistaAlumnos.setControlador(controlador);
		vistaAlumnos.setModelo(modelo);
		controlador.setVista(vistaAlumnos);
		modelo.setVista(vistaAlumnos);
		((CImplementacion) controlador).crearTablaAlumnos();
		vistaAlumnos.setVisible(true);

	}
}
