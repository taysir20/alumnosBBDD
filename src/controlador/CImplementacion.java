package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.MImplementacion;
import modelo.Modelo;
import vista.VAlumnos;
import vista.Vista;

public class CImplementacion implements Controlador {

	private MImplementacion modelo;
	private VAlumnos vistaAlumnos;
	private String cod;

	public void getBBDD() {
		modelo.ConexionBBDD();
	}

	@Override
	public void setModelo(Modelo modelo) {
		this.modelo = (MImplementacion) modelo;

	}

	@Override
	public void setVista(Vista vista) {
		this.vistaAlumnos = (VAlumnos) vista;

	}

	public void recogerDatosAlumno() {
		if(this.vistaAlumnos.getTxtNombre().isEmpty() || this.vistaAlumnos.getTxtApellido().isEmpty() || this.vistaAlumnos.getTxtDNI().isEmpty() || this.vistaAlumnos.getComboBoxNacionalidad().isEmpty() || this.vistaAlumnos.getTxtTelefono()==0){
			JOptionPane.showMessageDialog(null, "Todos los campos han de estar rellenos.");
		}else{
			this.modelo.actualizarAlumnos(this.vistaAlumnos.getTxtNombre(), this.vistaAlumnos.getTxtApellido(), this.vistaAlumnos.getTxtDNI(), this.vistaAlumnos.getComboBoxNacionalidad(), this.vistaAlumnos.getTxtTelefono());
			this.vistaAlumnos.getBtnActualizar().setEnabled(false);
			this.vistaAlumnos.getBtnBorrar().setEnabled(false);
		}
	}

	public void crearTablaAlumnos() {
		ArrayList<String[]> resultadosAlumnos = modelo.recogerDatosBBDD();
		String[][] alumnos = new String[resultadosAlumnos.size()][6];
		for (int i = 0; i < resultadosAlumnos.size(); i++) {
			alumnos[i] = resultadosAlumnos.get(i);
			for (int e = 0; e < resultadosAlumnos.get(i).length; e++) {
				this.vistaAlumnos.getTable().setModel(new DefaultTableModel(alumnos,
						new String[] { "COD", "DNI", "NOMBRE", "APELLIDO", "NACIONALIDAD", "TELEFONO" }));

			}

		}

	}

	public void generarError() {
		JOptionPane.showMessageDialog(null, "No se ha podido establecer conexión con la base de Datos.");
		
	}

	public void generarErrorNumerico() {
		JOptionPane.showMessageDialog(null, "El número de teléfono ha de ser un número.");
		
	}

	public void registroSeleccionado() {
		cod=String.valueOf(this.vistaAlumnos.getTable().getValueAt(this.vistaAlumnos.getTable().getSelectedRow(), 0));
		modelo.obtenerDatosRegistroBBDD(cod);
	}

	public void borrarRegistro() {
		modelo.borrarRegistroBBDD(cod);
		this.vistaAlumnos.getBtnActualizar().setEnabled(false);
		this.vistaAlumnos.getBtnBorrar().setEnabled(false);
		
	}

	public void actualizarRegistro() {
		if(this.vistaAlumnos.getTxtNombre().isEmpty() || this.vistaAlumnos.getTxtApellido().isEmpty() || this.vistaAlumnos.getTxtDNI().isEmpty() || this.vistaAlumnos.getComboBoxNacionalidad().isEmpty() || this.vistaAlumnos.getTxtTelefono()==0){
			JOptionPane.showMessageDialog(null, "Todos los campos han de estar rellenos.");
		}else{
			modelo.actualizarRegistroBBDD(this.vistaAlumnos.getTxtDNI(),this.vistaAlumnos.getTxtNombre(),this.vistaAlumnos.getTxtApellido(),this.vistaAlumnos.getComboBoxNacionalidad(),this.vistaAlumnos.getTxtTelefono(),cod);
			this.vistaAlumnos.getBtnActualizar().setEnabled(false);
			this.vistaAlumnos.getBtnBorrar().setEnabled(false);
		}
		
		
	}


}
