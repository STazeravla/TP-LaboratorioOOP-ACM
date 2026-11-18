package TP_Fatal_Final.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import TP_Fatal_Final.dao.BComplejosHabitacionalesDAO;
import TP_Fatal_Final.dao.BTrazadoFormalDAO;
import TP_Fatal_Final.dao.BUrbanizacionPlanificadaDAO;
import TP_Fatal_Final.modelo.Barrio;

import TP_Fatal_Final.modelo.BTrazadoFormal;
import TP_Fatal_Final.modelo.BComplejosHabitacionales;
import TP_Fatal_Final.modelo.BUrbanizacionPlanificada;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BarrioAltaModificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreBarrio;
	private JTextField txtSuperficie;
	private JComboBox<String> cbTipoBarrio;
	
	// Componentes específicos: Trazado Formal
	private JLabel lblEdificiosPH;
	private JTextField txtEdificiosPH;
	private JLabel lblAsfalto;
	private JTextField txtAsfalto;
	
	// Componentes específicos: Urbanización Planificada
	private JLabel lblLeyIntegracion;
	private JTextField txtLeyIntegracion;
	private JCheckBox chkAguaPotable;
	
	// Componentes específicos: Complejos Habitacionales
	private JLabel lblMonoblocks;
	private JTextField txtMonoblocks;
	private JCheckBox chkConsorcio;
	
	private JButton btnGuardarModificar;

	/**
	 * Create the panel.
	 * @param barrioAModificar Objeto barrio en caso de modificación, o null si es alta.
	 */
	public BarrioAltaModificacion(Barrio barrioAModificar) {
		setLayout(null);

		JLabel lblTitulo = new JLabel(barrioAModificar == null ? "Agregar Barrio" : "Modificar Barrio");
		lblTitulo.setBounds(160, 20, 150, 20);
		add(lblTitulo);

		// --- Datos Comunes ---
		JLabel lblNombre = new JLabel("Nombre del barrio");
		lblNombre.setBounds(20, 60, 130, 20);
		add(lblNombre);

		txtNombreBarrio = new JTextField();
		txtNombreBarrio.setBounds(160, 60, 150, 20);
		add(txtNombreBarrio);

		JLabel lblSuperficie = new JLabel("Superficie (ha)");
		lblSuperficie.setBounds(20, 95, 130, 20);
		add(lblSuperficie);

		txtSuperficie = new JTextField();
		txtSuperficie.setBounds(160, 95, 150, 20);
		add(txtSuperficie);

		JLabel lblTipo = new JLabel("Tipo de barrio");
		lblTipo.setBounds(20, 130, 130, 20);
		add(lblTipo);

		cbTipoBarrio = new JComboBox<>();
		cbTipoBarrio.setModel(new DefaultComboBoxModel<>(new String[] {
			"Trazado Formal", 
			"Urbanización Planificada", 
			"Complejo Habitacional"
		}));
		cbTipoBarrio.setBounds(160, 130, 180, 22);
		add(cbTipoBarrio);

		// --- Campos Específicos: Trazado Formal ---
		lblEdificiosPH = new JLabel("Edificios de PH:");
		lblEdificiosPH.setBounds(20, 175, 130, 20);
		add(lblEdificiosPH);

		txtEdificiosPH = new JTextField();
		txtEdificiosPH.setBounds(160, 175, 150, 20);
		add(txtEdificiosPH);

		lblAsfalto = new JLabel("% Asfalto calles:");
		lblAsfalto.setBounds(20, 210, 130, 20);
		add(lblAsfalto);

		txtAsfalto = new JTextField();
		txtAsfalto.setBounds(160, 210, 150, 20);
		add(txtAsfalto);

		// --- Campos Específicos: Urbanización Planificada ---
		lblLeyIntegracion = new JLabel("Nro. Ley Integración:");
		lblLeyIntegracion.setBounds(20, 175, 130, 20);
		add(lblLeyIntegracion);

		txtLeyIntegracion = new JTextField();
		txtLeyIntegracion.setBounds(160, 175, 150, 20);
		add(txtLeyIntegracion);

		chkAguaPotable = new JCheckBox("Conexión Red Agua Potable");
		chkAguaPotable.setBounds(160, 210, 200, 20);
		add(chkAguaPotable);

		// --- Campos Específicos: Complejos Habitacionales ---
		lblMonoblocks = new JLabel("Total Monoblocks:");
		lblMonoblocks.setBounds(20, 175, 130, 20);
		add(lblMonoblocks);

		txtMonoblocks = new JTextField();
		txtMonoblocks.setBounds(160, 175, 150, 20);
		add(txtMonoblocks);

		chkConsorcio = new JCheckBox("Consorcio Activo");
		chkConsorcio.setBounds(160, 210, 200, 20);
		add(chkConsorcio);

		// Listener para actualizar la visibilidad de los campos según la opción del ComboBox
		ActionListener listenerTipo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarCamposDinamicos();
			}
		};
		cbTipoBarrio.addActionListener(listenerTipo);
		cbTipoBarrio.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					actualizarCamposDinamicos();
				}
			}
		});

		// Si se pasó un objeto para modificar, completamos los campos
		if (barrioAModificar != null) {
			txtNombreBarrio.setText(barrioAModificar.getNombreBarrio());
			txtSuperficie.setText(String.valueOf(barrioAModificar.getSuperficie()));
			// cbTipoBarrio.setSelectedItem(barrioAModificar.getTipoDeBarrio());
			// Cargar campos específicos correspondientes...
		}

		// Botón Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BarrioConsulta());
				marco.validate();
			}
		});
		btnVolver.setBounds(190, 275, 90, 25);
		add(btnVolver);
		
		// Botón Guardar / Modificar
		btnGuardarModificar = new JButton();
		btnGuardarModificar.setText(barrioAModificar == null ? "Guardar" : "Modificar");
		btnGuardarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoSeleccionado = (String) cbTipoBarrio.getSelectedItem();
				
				// Dentro del botón guardar/modificar de BarrioAltaModificacion:

				if ("Trazado Formal".equals(tipoSeleccionado)) {
				    BTrazadoFormal btf = new BTrazadoFormal();
				    btf.setNombreBarrio(txtNombreBarrio.getText());
				    btf.setSuperficie(Integer.valueOf(txtSuperficie.getText()));
				    btf.setCantidadDeEdificiosHorizontales(Integer.valueOf(txtEdificiosPH.getText()));
				    btf.setPorAsfaltoEnCalles(Integer.valueOf(txtAsfalto.getText()));
				    
				    BTrazadoFormalDAO dao = new BTrazadoFormalDAO();
				    if (barrioAModificar == null) {
				        dao.guardar(btf);
				    } else {
				        // 👈 Le pasamos el nombre original para que el DAO haga el UPDATE por nombreBarrio
				        dao.actualizar(btf, barrioAModificar.getNombreBarrio());
				    }
				    
				} else if ("Complejo Habitacional".equals(tipoSeleccionado)) {
				    BComplejosHabitacionales bch = new BComplejosHabitacionales();
				    bch.setNombreBarrio(txtNombreBarrio.getText());
				    bch.setSuperficie(Integer.valueOf(txtSuperficie.getText()));
				    bch.setCantidadDeMonoblocks(Integer.valueOf(txtMonoblocks.getText()));
				    bch.setTieneAdministracionActiva(chkConsorcio.isSelected());
				    
				    BComplejosHabitacionalesDAO dao = new BComplejosHabitacionalesDAO();
				    if (barrioAModificar == null) {
				        dao.guardar(bch);
				    } else {
				        dao.actualizar(bch, barrioAModificar.getNombreBarrio());
				    }
				    
				} else if ("Urbanización Planificada".equals(tipoSeleccionado)) {
				    BUrbanizacionPlanificada bup = new BUrbanizacionPlanificada();
				    bup.setNombreBarrio(txtNombreBarrio.getText());
				    bup.setSuperficie(Integer.valueOf(txtSuperficie.getText()));
				    bup.setNumeroDeLey(Integer.valueOf(txtLeyIntegracion.getText()));
				    bup.setTieneAguaPotable(chkAguaPotable.isSelected());
				    
				    BUrbanizacionPlanificadaDAO dao = new BUrbanizacionPlanificadaDAO();
				    if (barrioAModificar == null) {
				        dao.guardar(bup);
				    } else {
				        dao.actualizar(bup, barrioAModificar.getNombreBarrio());
				    }
				}

				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BarrioConsulta());
				marco.validate();
			}
		});
		btnGuardarModificar.setBounds(90, 275, 90, 25);
		add(btnGuardarModificar);

		// Establecer visibilidad inicial
		actualizarCamposDinamicos();
	}

	/**
	 * Muestra u oculta los componentes visuales según el tipo de barrio elegido.
	 */
	private void actualizarCamposDinamicos() {
		String tipoSeleccionado = (String) cbTipoBarrio.getSelectedItem();

		// Ocultar todos los campos específicos por defecto
		lblEdificiosPH.setVisible(false);
		txtEdificiosPH.setVisible(false);
		lblAsfalto.setVisible(false);
		txtAsfalto.setVisible(false);

		lblLeyIntegracion.setVisible(false);
		txtLeyIntegracion.setVisible(false);
		chkAguaPotable.setVisible(false);

		lblMonoblocks.setVisible(false);
		txtMonoblocks.setVisible(false);
		chkConsorcio.setVisible(false);

		// Mostrar únicamente los correspondientes a la selección
		if ("Trazado Formal".equals(tipoSeleccionado)) {
			lblEdificiosPH.setVisible(true);
			txtEdificiosPH.setVisible(true);
			lblAsfalto.setVisible(true);
			txtAsfalto.setVisible(true);
		} else if ("Urbanización Planificada".equals(tipoSeleccionado)) {
			lblLeyIntegracion.setVisible(true);
			txtLeyIntegracion.setVisible(true);
			chkAguaPotable.setVisible(true);
		} else if ("Complejo Habitacional".equals(tipoSeleccionado)) {
			lblMonoblocks.setVisible(true);
			txtMonoblocks.setVisible(true);
			chkConsorcio.setVisible(true);
		}
	}
}