package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CImplementacion;
import controlador.Controlador;
import modelo.MImplementacion;
import modelo.Modelo;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VAlumnos extends JFrame implements Vista {

	private JPanel contentPane;
	private CImplementacion controladorImplementacion;
	private MImplementacion modeloImplementacion;
	private JTable table;
	private JTextField txtTelefono;
	private JTextField txtDNI;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JComboBox comboBoxNacionalidad;
	private JButton btnBorrar;
	private JButton btnActualizar;
	private JButton btnCopiarAUn;
	private JButton btnBorrarTodo;
	private JButton btnSubirDelFichero;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}


	public int getTxtTelefono() {
		try{
			return Integer.parseInt(txtTelefono.getText());
		}catch(Exception e){
			controladorImplementacion.generarErrorNumerico();
			return 0;
		}
		
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public String getTxtDNI() {
		return txtDNI.getText();
	}

	public void setTxtDNI(JTextField txtDNI) {
		this.txtDNI = txtDNI;
	}

	public String getTxtApellido() {
		return txtApellido.getText();
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public String getTxtNombre() {
		return txtNombre.getText();
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	/**
	 * Launch the application.
	 */

	public VAlumnos() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAlumnos = new JLabel("ALUMNOS");
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 32));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAadir = new JButton("A\u00D1ADIR");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.recogerDatosAlumno();
			}
		});

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setEnabled(false);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.borrarRegistro();
			}
		});

		JLabel lblAadirAlumno = new JLabel("A\u00D1ADIR ALUMNO:");

		JLabel lblNewLabel = new JLabel("Nombre:");

		JLabel lblApellido = new JLabel("Apellido:");

		JLabel lblDni = new JLabel("DNI:");

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.actualizarRegistro();
			}
		});
		btnActualizar.setEnabled(false);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		comboBoxNacionalidad = new JComboBox();
		comboBoxNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"ESPA\u00D1OLA", "OTRO"}));
		
		btnCopiarAUn = new JButton("COPIAR A UN FICHERO");
		btnCopiarAUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.guardarTabla();
			}
		});
		
		btnBorrarTodo = new JButton("BORRAR TODO");
		btnBorrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.borrarTodosAlumnos();
			}
		});
		
		btnSubirDelFichero = new JButton("SUBIR DEL FICHERO A LA BBDD");
		btnSubirDelFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImplementacion.subirFichero();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlumnos)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addComponent(lblAadirAlumno)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDni, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNacionalidad, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(comboBoxNacionalidad, 0, 264, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAadir)
							.addGap(18)
							.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnActualizar)
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSubirDelFichero, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(btnBorrarTodo)
									.addGap(18)
									.addComponent(btnCopiarAUn)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAlumnos)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAadirAlumno)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNacionalidad)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxNacionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelfono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(btnSubirDelFichero)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAadir)
						.addComponent(btnBorrar)
						.addComponent(btnActualizar)
						.addComponent(btnBorrarTodo)
						.addComponent(btnCopiarAUn))
					.addContainerGap())
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnBorrar.setEnabled(true);
				btnActualizar.setEnabled(true);
				controladorImplementacion.registroSeleccionado();
			}
		});

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	



	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public String getComboBoxNacionalidad() {
		return comboBoxNacionalidad.getSelectedItem().toString();
	}

	public void setComboBoxNacionalidad(JComboBox comboBoxNacionalidad) {
		this.comboBoxNacionalidad = comboBoxNacionalidad;
	}

	@Override
	public void setModelo(Modelo modelo) {
		this.modeloImplementacion = (MImplementacion) modelo;

	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controladorImplementacion = (CImplementacion) controlador;

	}

	public void actualizarTabla() {
		System.out.println("borrar");
		controladorImplementacion.crearTablaAlumnos();
		
	}

	public void devolverDatosRegistro() {
		txtDNI.setText(modeloImplementacion.getDni());
		txtNombre.setText(modeloImplementacion.getNombre());
		txtApellido.setText(modeloImplementacion.getApellido());
		comboBoxNacionalidad.setSelectedItem(modeloImplementacion.getNacionalidad());
		txtTelefono.setText(modeloImplementacion.getTelefono());
	}
}
