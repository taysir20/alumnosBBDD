package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import vista.VAlumnos;
import vista.Vista;

public class MImplementacion implements Modelo {
	private VAlumnos vistaAlumnos;
	private Connection conexion;
	private String Usuario;
	private String Pass;
	private String bbdd;
	private String dni;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private String telefono;

	public void getBBDD() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File miFichero = new File("conexionBBDD.ini");
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				// cargamos el archivo de propiedades
				propiedades.load(entrada);
				// obtenemos las propiedades y las imprimimos
				this.Usuario = propiedades.getProperty("Usuario");
				this.Pass = propiedades.getProperty("Pass");
				this.bbdd = propiedades.getProperty("Url");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String ConexionBBDD() {
		String aviso;
		try {
			getBBDD();
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(this.bbdd, this.Usuario, this.Pass);
			System.out.println(" - Conexión con MySQL establecida -");
			aviso = "¡Conexión satisfactoria!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha podido establecer conexión con la base de Datos.");
			System.out.println(" – Error de Conexión con MySQL -");
			aviso = "¡Conexión Fallida!";
			e.printStackTrace();
		}
		return aviso;

	}

	public ArrayList<String[]> recogerDatosBBDD() {
		ArrayList<String[]> resultados = new ArrayList<String[]>();
		try {
			if (conexion == null) {
				System.out.println("NO EXISTE");
				System.exit(-1);
			}
			PreparedStatement pstmt = conexion.prepareStatement("SELECT * FROM alumnos");
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();

			while (rset.next()) {
				String fila[] = new String[rsmd.getColumnCount()];
				fila[0] = rset.getString("cod");
				fila[1] = rset.getString("dni");
				fila[2] = rset.getString("nombre");
				fila[3] = rset.getString("apellido");
				fila[4] = rset.getString("nacionalidad");
				fila[5] = rset.getString("telefono");
				resultados.add(fila);
			}

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return resultados;
	}

	@Override
	public void setVista(Vista vista) {
		this.vistaAlumnos = (VAlumnos) vista;

	}

	public void actualizarAlumnos(String txtNombre, String txtApellido, String txtDNI, String comboBoxNacionalidad,
			int txtTelefono) {
		try {
			PreparedStatement pstmt;
			String query = "INSERT INTO `alumnos` (`dni`, `nombre`, `apellido`, `telefono`, `nacionalidad`) VALUES ( ?,?,?,?,? )";
			pstmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, txtDNI.toUpperCase());
			pstmt.setString(2, txtNombre.toUpperCase());
			pstmt.setString(3, txtApellido.toUpperCase());
			pstmt.setInt(4, txtTelefono);
			pstmt.setString(5, comboBoxNacionalidad.toUpperCase());
			if (pstmt.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Nuevo registro almacenado satisfactoriamente.");
				vistaAlumnos.actualizarTabla();
			}else{
				JOptionPane.showMessageDialog(null, "Se ha producido un error, vuelva a intentarlo.");
			}
		} catch (SQLException e) {
			System.out.println("error");
			JOptionPane.showMessageDialog(null, "Este DNI ya se encuentra en la base de datos.");
			e.printStackTrace();
		}

	}
	
	public void borrarRegistroBBDD(String cod) {
		try {
			if (conexion == null) {
				System.out.println("NO EXISTE");
				System.exit(-1);
			}
			PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM `alumnos` WHERE `alumnos`.`cod` =" + cod);
			if (pstmt.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Registro borrado satisfactoriamente.");
				vistaAlumnos.actualizarTabla();
			}else{
				JOptionPane.showMessageDialog(null, "Se ha producido un error, vuelva a intentarlo.");
			}

		} catch (SQLException s) {
			s.printStackTrace();
		}

		vistaAlumnos.actualizarTabla();

	}

	public void obtenerDatosRegistroBBDD(String cod) {
		try {
			if (conexion == null) {
				System.out.println("NO EXISTE");
				System.exit(-1);
			}
			PreparedStatement pstmt = conexion.prepareStatement(
					"SELECT * FROM alumnos WHERE alumnos.cod= "
							+ cod);
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				dni = rset.getString("dni");
				nombre = rset.getString("nombre");
				apellido = rset.getString("apellido");
				nacionalidad = rset.getString("nacionalidad");
				telefono = rset.getString("telefono");
			}
			

		} catch (SQLException s) {
			s.printStackTrace();
		}

		this.vistaAlumnos.devolverDatosRegistro();
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void actualizarRegistroBBDD(String txtDNI, String txtNombre, String txtApellido, String comboBoxNacionalidad,
			int txtTelefono, String cod) {
		try {
			PreparedStatement pstmt = conexion.prepareStatement("UPDATE `alumnos` SET `dni` = '" + txtDNI + "', `nombre` = '"  + txtNombre + "', `apellido` = '"+txtApellido+"', `nacionalidad` = '"+comboBoxNacionalidad+"', `telefono` = '"+txtTelefono+"' WHERE `alumnos`.`cod` = '" + cod + "'");
			pstmt.executeUpdate();        
			if (pstmt.executeUpdate() == 1) {
				JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente.");
				vistaAlumnos.actualizarTabla();
			}else{
				JOptionPane.showMessageDialog(null, "Se ha producido un error, vuelva a intentarlo.");
			}
		} catch (SQLException e) {
			System.out.println("error");
			JOptionPane.showMessageDialog(null, "Este DNI ya se encuentra en la base de datos.");
			e.printStackTrace();
		}

		vistaAlumnos.actualizarTabla();
	}
		
}
